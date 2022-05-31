package com.backend.backend.controller;


import com.backend.backend.models.Proyecto;
import com.backend.backend.services.ProyectoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proyecto")
public class ProyectoController {

    private final ProyectoService proyectoService;

    public ProyectoController(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Proyecto>> obtenerProyecto(){
        List <Proyecto> proyectos = proyectoService.traerProyecto();
        return new ResponseEntity<>(proyectos, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Proyecto> actualizarProyecto(@RequestBody Proyecto proyecto){
        Proyecto updateProyecto = proyectoService.actualizarProyecto(proyecto);
        return new ResponseEntity<>(updateProyecto, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Proyecto> agregarProyecto(@RequestBody Proyecto proyecto){
        Proyecto addProyecto = proyectoService.addProyecto(proyecto);
        return new ResponseEntity<>(addProyecto, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Proyecto> eliminarProyecto(@PathVariable Long id){
        proyectoService.borrarProyecto(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
