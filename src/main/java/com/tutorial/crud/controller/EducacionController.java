package com.tutorial.crud.controller;

import com.tutorial.crud.dto.EducacionDto;
import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.entity.Educacion;
import com.tutorial.crud.service.EducacionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/educacion")
@CrossOrigin(origins = "*")
public class EducacionController {

    @Autowired
    EducacionService educacionService;

    @GetMapping("/lista")
    public ResponseEntity<List<Educacion>> list() {
        List<Educacion> list = educacionService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Educacion> getById(@PathVariable("id") int id) {
        if (!educacionService.existsById(id))
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.NOT_FOUND);
        Educacion educacion = educacionService.getOne(id).get();
        return new ResponseEntity(educacion, HttpStatus.OK);
    }

    @GetMapping("/detailname/{nombre}")
    public ResponseEntity<Educacion> getByNombre(@PathVariable("nombre") String nombre) {
        if (!educacionService.existsByNombre(nombre))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Educacion educacion = educacionService.getByNombre(nombre).get();
        return new ResponseEntity(educacion, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody EducacionDto educacionDto) {
        if (StringUtils.isBlank(educacionDto.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre de la educacion es obligatorio"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(educacionDto.getDescripcion()))
            return new ResponseEntity(new Mensaje("la descripcion de la educacion es obligatoria"), HttpStatus.BAD_REQUEST);

        if (educacionService.existsByNombre(educacionDto.getNombre()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        Educacion educacion = new Educacion(educacionDto.getNombre(), educacionDto.getDescripcion(), educacionDto.getAnio());
        educacionService.save(educacion);
        return new ResponseEntity(new Mensaje("educacion creado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody EducacionDto educacionDto) {
        if (!educacionService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if (educacionService.existsByNombre(educacionDto.getNombre()) && educacionService.getByNombre(educacionDto.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(educacionDto.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(educacionDto.getDescripcion()))
            return new ResponseEntity(new Mensaje("la descripcion es obligatoria"), HttpStatus.BAD_REQUEST);

        Educacion experiencia = educacionService.getOne(id).get();
        experiencia.setNombre(educacionDto.getNombre());
        experiencia.setDescripcion(educacionDto.getDescripcion());
        experiencia.setAnio(educacionDto.getAnio());
        educacionService.save(experiencia);
        return new ResponseEntity(new Mensaje("experiencia actualizado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!educacionService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        educacionService.delete(id);
        return new ResponseEntity(new Mensaje("experiencia eliminada"), HttpStatus.OK);
    }

}
