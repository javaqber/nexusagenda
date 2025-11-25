package com.nexusagenda.nexusagenda_backend.controller;

import com.nexusagenda.nexusagenda_backend.entity.Event;
import com.nexusagenda.nexusagenda_backend.model.User;
import com.nexusagenda.nexusagenda_backend.service.EventService;
import com.nexusagenda.nexusagenda_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Event> createEvent(@Valid @RequestBody Event event) {
        // Obtiene el Email de usuario del token autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currrentUserEmail = authentication.getName(); // Devuelve el Email

        Optional<User> userOptional = userService.findByEmail(currrentUserEmail);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            event.setUser(user);
            Event createdEvent = eventService.createEvent(event);
            return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
        } else {
            // Esto es un fallback, pero no debería ocurrir si el token es válido
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<Event>> getEventsByUser(@PathVariable String username) {
        Optional<User> userOptional = userService.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            List<Event> events = eventService.getEventsByUser(user);
            return new ResponseEntity<>(events, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Optional<Event> eventOptional = eventService.getEventById(id);
        return eventOptional.map(event -> new ResponseEntity<>(event, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @Valid @RequestBody Event updatedEvent) {
        Optional<Event> existingEventOptional = eventService.getEventById(id);
        if (existingEventOptional.isPresent()) {
            Event existingEvent = existingEventOptional.get();
            // Actualiza los campos necesarios
            existingEvent.setTitle(updatedEvent.getTitle());
            existingEvent.setDescription(updatedEvent.getDescription());
            // actualiza el objeto LocalDateTime completo
            existingEvent.setDateTime(updatedEvent.getDateTime());

            Event savedEvent = eventService.updateEvent(existingEvent);
            return new ResponseEntity<>(savedEvent, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        Optional<Event> existingEventOptional = eventService.getEventById(id);
        if (existingEventOptional.isPresent()) {
            eventService.deleteEvent(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}