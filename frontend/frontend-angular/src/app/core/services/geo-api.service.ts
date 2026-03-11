import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Centre, Province } from '../../shared/models/geo.model';

@Injectable({ providedIn: 'root' })
export class GeoApiService {
  private readonly centresUrl = `${environment.apiBaseUrl}/api/centres`;
  private readonly provincesUrl = `${environment.apiBaseUrl}/api/provinces`;

  constructor(private http: HttpClient) {}

  getCentres(): Observable<Centre[]> { return this.http.get<Centre[]>(this.centresUrl); }
  getCentre(id: number): Observable<Centre> { return this.http.get<Centre>(`${this.centresUrl}/${id}`); }
  createCentre(payload: Partial<Centre>): Observable<Centre> { return this.http.post<Centre>(this.centresUrl, payload); }
  updateCentre(id: number, payload: Partial<Centre>): Observable<Centre> { return this.http.put<Centre>(`${this.centresUrl}/${id}`, payload); }
  deleteCentre(id: number): Observable<void> { return this.http.delete<void>(`${this.centresUrl}/${id}`); }

  getProvinces(): Observable<Province[]> { return this.http.get<Province[]>(this.provincesUrl); }
  getProvince(id: number): Observable<Province> { return this.http.get<Province>(`${this.provincesUrl}/${id}`); }
  createProvince(payload: Partial<Province>): Observable<Province> { return this.http.post<Province>(this.provincesUrl, payload); }
  updateProvince(id: number, payload: Partial<Province>): Observable<Province> { return this.http.put<Province>(`${this.provincesUrl}/${id}`, payload); }
  deleteProvince(id: number): Observable<void> { return this.http.delete<void>(`${this.provincesUrl}/${id}`); }
}