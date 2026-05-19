import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ExpenseService {
  private url = 'http://localhost:8080/api/expenses';

  constructor(private http: HttpClient) {}

  getAll(): Observable<any[]> {
    return this.http.get<any[]>(this.url);
  }

  getByMonth(month: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.url}/month/${month}`);
  }

  getTotal(): Observable<number> {
    return this.http.get<number>(`${this.url}/total`);
  }

  add(expense: any): Observable<any> {
    return this.http.post<any>(this.url, expense);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${this.url}/${id}`);
  }
}
