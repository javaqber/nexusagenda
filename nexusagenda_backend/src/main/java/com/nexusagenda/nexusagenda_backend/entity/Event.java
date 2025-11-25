package com.nexusagenda.nexusagenda_backend.entity;

import com.nexusagenda.nexusagenda_backend.model.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El título no puede estar vacio.")
    @Size(max = 100, message = "El título no puede exceder los 100 caracteres.")
    private String title;

    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres.")
    private String description;

    @NotNull(message = "La fecha y hora del evento no pueden ser nulas.")
    @FutureOrPresent(message = "La fecha y hora deben ser en el presente o futuro.")
    private LocalDateTime dateTime; // Campo para fecha y hora

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}