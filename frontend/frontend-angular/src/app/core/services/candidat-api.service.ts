import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Candidat, PagedResponse } from '../../shared/models/candidat.model';

@Injectable({ providedIn: 'root' })
export class CandidatApiService {
  private readonly baseUrl = `${environment.apiBaseUrl}/api/candidats`;

  constructor(private http: HttpClient) {}

  uploadExcel(file: File): Observable<{ imported: number }> {
    const formData = new FormData();
    formData.append('file', file);
    return this.http.post<{ imported: number }>(`${this.baseUrl}/import`, formData);
  }

  transform(): Observable<{ transformed: number }> {
    return this.http.post<{ transformed: number }>(`${this.baseUrl}/transform`, {});
  }

  getCandidats(page = 0, size = 20): Observable<PagedResponse<Candidat>> {
    const params = new HttpParams().set('page', page).set('size', size).set('sort', 'id,desc');
    return this.http.get<PagedResponse<Candidat>>(this.baseUrl, { params });
  }

  getCandidat(id: number): Observable<Candidat> {
    return this.http.get<Candidat>(`${this.baseUrl}/${id}`);
  }

  filterCandidats(filters: Record<string, string>, page = 0, size = 20): Observable<PagedResponse<Candidat>> {
    let params = new HttpParams().set('page', page).set('size', size).set('sort', 'nom,asc');
    Object.keys(filters).forEach((key) => {
      if (filters[key]) {
        params = params.set(key, filters[key]);
      }
    });
    return this.http.get<PagedResponse<Candidat>>(`${this.baseUrl}/filter`, { params });
  }
}