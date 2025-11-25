package com.nexusagenda.nexusagenda_backend.repository;

import com.nexusagenda.nexusagenda_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Metodo para el login por email
    Optional<User> findByEmail(String email);

    // Metodo para el servicio de seguridad (UserDetailsServiceImpl)
    Optional<User> findByUsername(String username);
}