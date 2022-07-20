package com.tutorial.crud.controller;

import com.tutorial.crud.dto.ProyectoDto;
import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.entity.Proyecto;
import com.tutorial.crud.service.ProyectoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/proyecto")
@CrossOrigin(origins = "*")
public class ProyectoController {

    @Autowired
    ProyectoService proyectoService;

    @GetMapping("/lista")
    public ResponseEntity<List<Proyecto>> list() {
        List<Proyecto> list = proyectoService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyecto> getById(@PathVariable("id") int id) {
        if (!proyectoService.existsById(id))
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.NOT_FOUND);
        Proyecto informacion = proyectoService.getOne(id).get();
        return new ResponseEntity(informacion, HttpStatus.OK);
    }

    @GetMapping("/detailname/{nombre}")
    public ResponseEntity<Proyecto> getByNombre(@PathVariable("nombre") String nombre) {
        if (!proyectoService.existsByNombre(nombre))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Proyecto informacion = proyectoService.getByNombre(nombre).get();
        return new ResponseEntity(informacion, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProyectoDto proyectoDto) {
        if (StringUtils.isBlank(proyectoDto.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre del proyecto es obligatorio"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(proyectoDto.getDescripcion()))
            return new ResponseEntity(new Mensaje("la descripcion del proyecto es obligatoria"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(proyectoDto.getImagen()))
            return new ResponseEntity(new Mensaje("la url de la imagen es obligatoria"), HttpStatus.BAD_REQUEST);


        if (proyectoService.existsByNombre(proyectoDto.getNombre()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        Proyecto informacion = new Proyecto(proyectoDto.getNombre(), proyectoDto.getDescripcion(), proyectoDto.getImagen());
        proyectoService.save(informacion);
        return new ResponseEntity(new Mensaje("informacion creado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody ProyectoDto proyectoDto) {
        if (!proyectoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if (proyectoService.existsByNombre(proyectoDto.getNombre()) && proyectoService.getByNombre(proyectoDto.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(proyectoDto.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(proyectoDto.getDescripcion()))
            return new ResponseEntity(new Mensaje("la descripcion es obligatoria"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(proyectoDto.getImagen()))
            return new ResponseEntity(new Mensaje("la url de la imagen es obligatoria"), HttpStatus.BAD_REQUEST);

        Proyecto informacion = proyectoService.getOne(id).get();
        informacion.setNombre(proyectoDto.getNombre());
        informacion.setDescripcion(proyectoDto.getDescripcion());
        informacion.setImagen(proyectoDto.getImagen());
        proyectoService.save(informacion);
        return new ResponseEntity(new Mensaje("informacion actualizado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!proyectoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        proyectoService.delete(id);
        return new ResponseEntity(new Mensaje("informacion eliminada"), HttpStatus.OK);
    }


}
