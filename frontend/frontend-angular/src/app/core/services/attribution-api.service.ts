import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { AttributionRunRequest, AttributionRunResponse, AttributionResult } from '../../shared/models/attribution.model';
import { PagedResponse } from '../../shared/models/candidat.model';

@Injectable({ providedIn: 'root' })
export class AttributionApiService {
  private readonly baseUrl = `${environment.apiBaseUrl}/api/attribution`;

  constructor(private http: HttpClient) {}

  run(payload: AttributionRunRequest): Observable<AttributionRunResponse> {
    return this.http.post<AttributionRunResponse>(`${this.baseUrl}/run`, payload);
  }

  getResults(runId = '', page = 0, size = 20): Observable<PagedResponse<AttributionResult>> {
    let params = new HttpParams().set('page', page).set('size', size).set('sort', 'dateConvocation,asc');
    if (runId) params = params.set('runId', runId);
    return this.http.get<PagedResponse<AttributionResult>>(`${this.baseUrl}/results`, { params });
  }

  getResult(id: number): Observable<AttributionResult> {
    return this.http.get<AttributionResult>(`${this.baseUrl}/results/${id}`);
  }
}