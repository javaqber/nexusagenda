package com.nexusagenda.nexusagenda_backend.controller;

import com.nexusagenda.nexusagenda_backend.model.User;
import com.nexusagenda.nexusagenda_backend.service.JwtService;
import com.nexusagenda.nexusagenda_backend.service.UserService;
import com.nexusagenda.nexusagenda_backend.dto.JwtResponse;
import com.nexusagenda.nexusagenda_backend.service.UserDetailsServiceImpl;

import jakarta.validation.Valid;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder; // Inyecta el encriptador de contraseña

    @Autowired
    private JwtService jwtService; // Inyecta el servicio JWT

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user) {
        try {
            User savedUser = userService.registerUser(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginUser(@Valid @RequestBody User user) {
        // Busca al usuario por email
        Optional<User> existingUserOptional = userService.findByEmail(user.getEmail());

        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            if (passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
                // Genera el token con el email
                UserDetails userDetails = userDetailsService.loadUserByUsername((existingUser.getEmail()));
                String token = jwtService.generateToken(userDetails);
                return new ResponseEntity<>(new JwtResponse(token), HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }
}
