package com.tutorial.crud.controller;

import com.tutorial.crud.dto.ExperienciaDto;
import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.entity.Experiencia;
import com.tutorial.crud.service.ExperienciaService;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/experiencia")
@CrossOrigin(origins = "*")
public class ExperienciaController {
    @Autowired
    ExperienciaService experienciaService;

    @GetMapping("/lista")
    public ResponseEntity<List<Experiencia>> list() {
        List<Experiencia> list = experienciaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Experiencia> getById(@PathVariable("id") int id) {
        if (!experienciaService.existsById(id))
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.NOT_FOUND);
        Experiencia experiencia = experienciaService.getOne(id).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }

    @GetMapping("/detailname/{nombre}")
    public ResponseEntity<Experiencia> getByNombre(@PathVariable("nombre") String nombre) {
        if (!experienciaService.existsByNombre(nombre))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Experiencia experiencia = experienciaService.getByNombre(nombre).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ExperienciaDto experienciaDto) {
        if (StringUtils.isBlank(experienciaDto.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre de la experiencia es obligatorio"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(experienciaDto.getDescripcion()))
            return new ResponseEntity(new Mensaje("la descripcion de la experiencia es obligatoria"), HttpStatus.BAD_REQUEST);

        if (experienciaService.existsByNombre(experienciaDto.getNombre()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        Experiencia experiencia = new Experiencia(experienciaDto.getNombre(), experienciaDto.getDescripcion(), experienciaDto.getAnio());
        experienciaService.save(experiencia);
        return new ResponseEntity(new Mensaje("experiencia creado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody ExperienciaDto experienciaDto) {
        if (!experienciaService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if (experienciaService.existsByNombre(experienciaDto.getNombre()) && experienciaService.getByNombre(experienciaDto.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(experienciaDto.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(experienciaDto.getDescripcion()))
            return new ResponseEntity(new Mensaje("la descripcion es obligatoria"), HttpStatus.BAD_REQUEST);

        Experiencia experiencia = experienciaService.getOne(id).get();
        experiencia.setNombre(experienciaDto.getNombre());
        experiencia.setDescripcion(experienciaDto.getDescripcion());
        experiencia.setAnio(experienciaDto.getAnio());
        experienciaService.save(experiencia);
        return new ResponseEntity(new Mensaje("experiencia actualizado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!experienciaService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        experienciaService.delete(id);
        return new ResponseEntity(new Mensaje("experiencia eliminada"), HttpStatus.OK);
    }
}
