package com.nexusagenda.nexusagenda_backend.service;

import com.nexusagenda.nexusagenda_backend.model.User;
import com.nexusagenda.nexusagenda_backend.repository.UserRepository;
import com.nexusagenda.nexusagenda_backend.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca al usuario por email, ya que lo usamos como 'username'
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + username));

        // Devuelve una nueva instancia de CustomUserDetails que envuelve a la entidad
        // User
        return new CustomUserDetails(user);
    }
}