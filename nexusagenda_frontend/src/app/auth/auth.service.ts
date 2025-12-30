import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators'; 
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  //private apiUrl = 'http://localhost:8080/api/users';
  private apiUrl = environment.apiUrl + '/users';

  constructor(private http: HttpClient) { }

  register(userData: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, userData);
  }

  login(credentials: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, credentials)
      .pipe(
        // LÓGICA PARA GUARDAR EL TOKEN
        tap((response: any) => {
          if (response && response.token) {
            localStorage.setItem('token', response.token);
          }
        })
      );
  }
  
  // MÉTODO para obtener el token
  getToken(): string | null {
    return localStorage.getItem('token');
  }

  // MÉTODO para eliminar el token (cerrar sesión)
  logout(): void {
    localStorage.removeItem('token');
  }
}