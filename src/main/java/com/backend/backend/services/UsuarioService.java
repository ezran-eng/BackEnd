package com.backend.backend.services;

import com.backend.backend.exception.UserNotFoundException;
import com.backend.backend.models.Usuario;
import com.backend.backend.repository.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UsuarioService {
    private final UsuarioRepo usuarioRepo;

    @Autowired
    public UsuarioService(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    public Usuario addUsuario(Usuario educacion){
        return usuarioRepo.save(educacion);
    }

    public List<Usuario> traerUsuario(){
        return usuarioRepo.findAll();
    }

    public Usuario actualizarUsuario(Usuario usuario){
        return usuarioRepo.save(usuario);
    }

    public void borrarUsuario(Long id){
        usuarioRepo.deleteById(id);
    }

    public Usuario getById(Long id){
        return usuarioRepo.findById(id).orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));
    }
}
