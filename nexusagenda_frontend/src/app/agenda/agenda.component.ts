import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { EventService } from '../event/event.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-agenda',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './agenda.component.html',
  styleUrl: './agenda.component.css'
})
export class Agenda implements OnInit {

  events: any[] = [];
  newEvent = {
    title: '',
    description: '',
    dateTime: ''
  };

  editingEvent: any = null;

  constructor(
    private eventService: EventService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.getEvents();
  }

  getEvents(): void {
    this.eventService.getEvents().subscribe({
      next: (data) => {
        this.events = data;
      },
      error: (error) => {
        console.error('Error al recuperar eventos:', error);
      }
    });
  }

  createEvent(): void {
    if (this.editingEvent) {
      this.updateEvent();
    } else {
      this.eventService.createEvent(this.newEvent).subscribe({
        next: (response) => {
          this.events.push(response);
          this.newEvent = { title: '', description: '', dateTime: '' };
        },
        error: (error) => {
          console.error('Error al crear evento:', error);
        }
      });
    }
  }

  deleteEvent(id: number): void {
    this.eventService.deleteEvent(id).subscribe({
      next: () => {
        this.events = this.events.filter(event => event.id !== id);
      },
      error: (error) => {
        console.error('Error al eliminar evento:', error);
      }
    });
  }

  editEvent(event: any): void {
    this.editingEvent = event;
    this.newEvent = { ...event };
  }

  updateEvent(): void {
    this.eventService.updateEvent(this.editingEvent.id, this.newEvent).subscribe({
      next: (response) => {
        const index = this.events.findIndex(e => e.id === response.id);
        if (index > -1) {
          this.events[index] = response;
        }
        this.newEvent = { title: '', description: '', dateTime: '' };
        this.editingEvent = null;
      },
      error: (error) => {
        console.error('Error al actualizar evento:', error);
      }
    });
  }

  logout(): void {
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }
}