import { AfterViewInit, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Chart, registerables } from 'chart.js';
import { AttributionApiService } from '../../../core/services/attribution-api.service';
import { AttributionResult } from '../../../shared/models/attribution.model';

Chart.register(...registerables);

@Component({ selector: 'app-attribution-results', templateUrl: './attribution-results.component.html' })
export class AttributionResultsComponent implements OnInit, AfterViewInit {
  @ViewChild('provinceChart') provinceChartRef!: ElementRef<HTMLCanvasElement>;
  @ViewChild('centreChart') centreChartRef!: ElementRef<HTMLCanvasElement>;

  results: AttributionResult[] = [];
  runId = '';

  constructor(private api: AttributionApiService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.runId = this.route.snapshot.queryParamMap.get('runId') || '';
    this.api.getResults(this.runId).subscribe((res) => {
      this.results = res.content;
      this.renderCharts();
    });
  }

  ngAfterViewInit(): void { this.renderCharts(); }

  private renderCharts(): void {
    if (!this.provinceChartRef || !this.centreChartRef || this.results.length === 0) return;

    const provinceMap = new Map<string, number>();
    const centreMap = new Map<string, number>();

    this.results.forEach((item) => {
      provinceMap.set(item.provinceNom, (provinceMap.get(item.provinceNom) || 0) + item.nombreCandidats);
      centreMap.set(item.centreNom, (centreMap.get(item.centreNom) || 0) + item.nombreCandidats);
    });

    new Chart(this.provinceChartRef.nativeElement, {
      type: 'bar',
      data: { labels: [...provinceMap.keys()], datasets: [{ label: 'Par province', data: [...provinceMap.values()] }] }
    });

    new Chart(this.centreChartRef.nativeElement, {
      type: 'pie',
      data: { labels: [...centreMap.keys()], datasets: [{ label: 'Par centre', data: [...centreMap.values()] }] }
    });
  }
}