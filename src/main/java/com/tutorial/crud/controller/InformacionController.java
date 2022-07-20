package com.tutorial.crud.controller;


import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.dto.InformacionDto;

import com.tutorial.crud.entity.Informacion;
import com.tutorial.crud.service.InformacionService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/informacion")
@CrossOrigin(origins = "*")
public class InformacionController {
    @Autowired
    InformacionService informacionService;

    @GetMapping("/lista")
    public ResponseEntity<List<Informacion>> list() {
        List<Informacion> list = informacionService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Informacion> getById(@PathVariable("id") int id) {
        if (!informacionService.existsById(id))
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.NOT_FOUND);
        Informacion informacion = informacionService.getOne(id).get();
        return new ResponseEntity(informacion, HttpStatus.OK);
    }

    @GetMapping("/detailname/{nombre}")
    public ResponseEntity<Informacion> getByNombre(@PathVariable("nombre") String nombre) {
        if (!informacionService.existsByNombre(nombre))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Informacion informacion = informacionService.getByNombre(nombre).get();
        return new ResponseEntity(informacion, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody InformacionDto informacionDto) {
        if (StringUtils.isBlank(informacionDto.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre de la informacion es obligatorio"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(informacionDto.getTitulo()))
            return new ResponseEntity(new Mensaje("el titulo de la informacion es obligatoria"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(informacionDto.getDescripcion()))
            return new ResponseEntity(new Mensaje("la descripcion de la informacion es obligatoria"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(informacionDto.getImagen()))
            return new ResponseEntity(new Mensaje("la url de la imagen es obligatoria"), HttpStatus.BAD_REQUEST);

        if (informacionService.existsByNombre(informacionDto.getNombre()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        Informacion informacion = new Informacion(informacionDto.getNombre(), informacionDto.getTitulo(), informacionDto.getDescripcion(), informacionDto.getImagen());
        informacionService.save(informacion);
        return new ResponseEntity(new Mensaje("informacion creado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody InformacionDto informacionDto) {
        if (!informacionService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if (informacionService.existsByNombre(informacionDto.getNombre()) && informacionService.getByNombre(informacionDto.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(informacionDto.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(informacionDto.getTitulo()))
            return new ResponseEntity(new Mensaje("el titulo es obligatoria"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(informacionDto.getDescripcion()))
            return new ResponseEntity(new Mensaje("la descripcion es obligatoria"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(informacionDto.getImagen()))
            return new ResponseEntity(new Mensaje("la url de la imagen es obligatoria"), HttpStatus.BAD_REQUEST);

        Informacion informacion = informacionService.getOne(id).get();
        informacion.setNombre(informacionDto.getNombre());
        informacion.setTitulo(informacionDto.getTitulo());
        informacion.setDescripcion(informacionDto.getDescripcion());
        informacion.setImagen(informacionDto.getImagen());
        informacionService.save(informacion);
        return new ResponseEntity(new Mensaje("informacion actualizado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!informacionService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        informacionService.delete(id);
        return new ResponseEntity(new Mensaje("informacion eliminada"), HttpStatus.OK);
    }

}
