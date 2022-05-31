package com.backend.backend.services;

import com.backend.backend.models.Experiencia;
import com.backend.backend.models.Proyecto;
import com.backend.backend.repository.ProyectoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProyectoService {
    private final ProyectoRepo proyectoRepo;

    @Autowired
    public ProyectoService(ProyectoRepo proyectoRepo) {
        this.proyectoRepo = proyectoRepo;
    }

    public Proyecto addProyecto(Proyecto proyecto){
        return proyectoRepo.save(proyecto);
    }

    public List<Proyecto> traerProyecto(){
        return proyectoRepo.findAll();
    }

    public Proyecto actualizarProyecto(Proyecto proyecto){
        return proyectoRepo.save(proyecto);
    }

    public void borrarProyecto(Long id){
        proyectoRepo.deleteById(id);
    }
}
