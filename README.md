Nexus Agenda 📅 - Sistema de Gestión de eventos personales

## 📋 Descripción

NexusAgenda es una aplicación web Full-Stack diseñada para la gestión integral de agendas y eventos personales. Este proyecto (desarrollado como TFM) soluciona la problemática de la organización personal centralizando eventos, citas y tareas en una única plataforma segura y accesible desde cualquier lugar.

La aplicación permite a los usuarios registrarse, iniciar sesión de forma segura y gestionar su calendario privado en tiempo real a través de una interfaz moderna y reactiva, asegurando que cada usuario tenga acceso exclusivo a sus propios datos.

## 📍 Demo en Vivo

Puedes probar la aplicación aquí:
[https://nexusagenda.netlify.app/](https://nexusagenda.netlify.app/)
**Usuario de prueba:** userDemo@nexusagenda.com | **Password:** 123456

## 🛠️ Stack Tecnológico

### Backend (API REST)

** Java 21 (JDK 21) **
** Spring Boot 3 **
** Spring Security 6 + JWT **
** Spring Data JPA (Hibernate) **
** MySQL 8 **

### Frontend (SPA)

** Angular 17+ **
** TypeScript **
** HTML5 & CSS3 **
** RxJS **

### Infraestructua y Devops

** Render ** (Despliegue de API)
** Netlify ** (Despliegue de Frontend)
** Aiven ** (Migración a DB remota)

## 🚀 Funcionalidades Principales

🔒 Seguridad y Autenticación

Login y Registro seguros mediante JWT (JSON Web Tokens).

Encriptación de contraseñas con BCrypt.

Autorización a nivel de recurso: Asegura que cada usuario solo pueda acceder, modificar o eliminar sus propios eventos.

📅 Gestión de Agenda

Creación rápida de nuevos eventos con título, descripción, fecha y hora.

Listado de eventos próximos ordenados cronológicamente.

Edición y eliminación de eventos existentes.

🙋‍♂️ Experiencia del Usuario

Interfaz de Usuario (UI) limpia e intuitiva.

Validación de formularios en tiempo real (feedback visual al usuario).

Navegación fluida como Single Page Application (SPA).

## 🧪 Testing de la API (Postman)

El proyecto incluye una colección de endpoints RESTful para probar la comunicación con el backend:

**Autenticación:**
POST /api/auth/register: Registrar un nuevo usuario en el sistema.
POST /api/auth/login: Iniciar sesión y obtener el Token JWT.

**Eventos (Requieren Token Bearer):**
GET /api/events: Obtener el listado de todos los eventos del usuario autenticado.
POST /api/events: Crear un nuevo evento asociado al usuario.
PUT /api/events/{id}: Actualizar un evento existente.
DELETE /api/events/{id}: Eliminar un evento.

## 👤 Autor

- Javier Vaquero Berrocal

Desarrollador Full-Stack (Java/Angular)

[LinkedIn] www.linkedin.com/in/javier-vaquero-dev35b5176

[Portfolio] https://portfoliojvb.netlify.app/

Desarrollado como Trabajo de Fin de Máster (TFM).
