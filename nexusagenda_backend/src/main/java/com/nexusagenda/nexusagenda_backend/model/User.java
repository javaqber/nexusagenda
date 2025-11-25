package com.nexusagenda.nexusagenda_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data // Proporciona getters, setters, toString, etc.
@Entity // Mapea esta clase a una tabla de la BD
@Table(name = "users") // Nombre de la tabla en la BD
public class User {

    @Id // Marca el campo como clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera valores de ID automáticamente
    private Long id;

    private String username;

    @NotBlank(message = "La contraseña no puede estar vacia.")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres.")
    private String password;

    @NotBlank(message = "Email no puede estar vacio.")
    @Email(message = "Email debe ser valido.")
    private String email;

    // No necesita validación para el registro login
    private String nombre;

}