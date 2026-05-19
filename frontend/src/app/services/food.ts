import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class FoodService {
  private url = 'http://localhost:8080/api/menus';

  constructor(private http: HttpClient) {}

  getAll(): Observable<any[]> {
    return this.http.get<any[]>(this.url);
  }

  getByMonth(month: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.url}/month/${month}`);
  }

  getByMonthAndYear(month: string, year: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.url}/filter?month=${month}&year=${year}`);
  }

  add(item: any): Observable<any> {
    return this.http.post<any>(this.url, item);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${this.url}/${id}`);
  }
}
