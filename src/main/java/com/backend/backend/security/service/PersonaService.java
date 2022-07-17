package com.backend.backend.security.service;


import com.backend.backend.security.entity.Persona;
import com.backend.backend.security.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PersonaService {

    @Autowired
    PersonaRepository personaRepository;

    public Optional<Persona> getByNombreUsuario(String nombreUsuario){
        return personaRepository.findByNombreUsuario(nombreUsuario);
    }

    public boolean existsByNombreUsuario(String nombreUsuario){
        return personaRepository.existsByNombreUsuario(nombreUsuario);
    }

    public boolean existsByEmail(String email){
        return personaRepository.existsByEmail(email);
    }

    public void save(Persona usuario){
        personaRepository.save(usuario);
    }
}
