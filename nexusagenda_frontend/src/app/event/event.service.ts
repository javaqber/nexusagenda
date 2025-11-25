import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EventService {

  private apiUrl = 'http://localhost:8080/api/events';

  constructor(private http: HttpClient) { }

  // Método para obtener los headers con el token JWT
  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('token');
    return new HttpHeaders().set('Authorization', `Bearer ${token}`);
  }

  // GET: Obtener todos los eventos del usuario
  getEvents(): Observable<any> {
    const headers = this.getHeaders();
    return this.http.get(this.apiUrl, { headers });
  }

  // POST: Crear un nuevo evento
  createEvent(eventData: any): Observable<any> {
    const headers = this.getHeaders();
    return this.http.post(this.apiUrl, eventData, { headers });
  }

  // PUT: Actualizar un evento existente
  updateEvent(eventId: number, eventData: any): Observable<any> {
    const headers = this.getHeaders();
    return this.http.put(`${this.apiUrl}/${eventId}`, eventData, { headers });
  }

  // DELETE: Eliminar un evento
  deleteEvent(eventId: number): Observable<any> {
    const headers = this.getHeaders();
    return this.http.delete(`${this.apiUrl}/${eventId}`, { headers });
  }
}