package com.nexusagenda.nexusagenda_backend.repository;

import com.nexusagenda.nexusagenda_backend.entity.Event;
import com.nexusagenda.nexusagenda_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    // Método para encontrar todos los eventos de un usuario específico.
    List<Event> findByUser(User user);

    Optional<Event> findById(long id);
}
