package com.backend.backend.security.service;

import com.backend.backend.security.entity.Persona;
import com.backend.backend.security.entity.PersonaPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    PersonaService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        Persona usuario = usuarioService.getByNombreUsuario(nombreUsuario).get();
        return PersonaPrincipal.build(usuario);
    }
}