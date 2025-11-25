import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../auth.service';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink, ReactiveFormsModule], 
  templateUrl: './register.component.html',
  styleUrls: ['../auth.styles.css', './register.component.css']
})
export class RegisterComponent {
  
  // Se reemplaza el objeto 'user' por un 'FormGroup'
  registerForm: FormGroup;

  constructor(
    private authService: AuthService, 
    private router: Router,
    private fb: FormBuilder
  ){
    // Definimos el formulario con sis validadores
    this.registerForm = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  onSubmit(): void {
    if(this.registerForm.valid){
      this.authService.register(this.registerForm.value).subscribe({
        next: () => {
          alert('Usuario registrado con exito. Ahora puedes iniciar sesión.');
          this.router.navigate(['/login']);
        },
        error: (error) => {
          console.error('Registro fallido.', error);
        }
      });
    }else{
      // Si el formulario no es valido, puede mostrar un mensaje o marcar los campos como 'tocados'
      this.registerForm.markAllAsTouched();
    }
  }
}