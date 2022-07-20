package com.tutorial.crud.controller;

import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.dto.SkillsDto;
import com.tutorial.crud.entity.Skills;
import com.tutorial.crud.service.SkillsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/skills")
@CrossOrigin(origins = "*")
public class SkillsController {

    @Autowired
    SkillsService skillsService;

    @GetMapping("/lista")
    public ResponseEntity<List<Skills>> list(){
        List<Skills> list = skillsService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Skills> getById(@PathVariable("id") int id){
        if(!skillsService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Skills skills = skillsService.getOne(id).get();
        return new ResponseEntity(skills, HttpStatus.OK);
    }

    @GetMapping("/detailname/{nombre}")
    public ResponseEntity<Skills> getByNombre(@PathVariable("nombre") String nombre){
        if(!skillsService.existsByNombre(nombre))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Skills skills = skillsService.getByNombre(nombre).get();
        return new ResponseEntity(skills, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody SkillsDto skillsDto){
        if(StringUtils.isBlank(skillsDto.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(skillsDto.getPorcentaje()==null || skillsDto.getPorcentaje()<0 )
            return new ResponseEntity(new Mensaje("el porcentaje debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
        if(skillsService.existsByNombre(skillsDto.getNombre()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        Skills skills = new Skills(skillsDto.getNombre(), skillsDto.getPorcentaje());
        skillsService.save(skills);
        return new ResponseEntity(new Mensaje("skills creado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody SkillsDto skillsDto){
        if(!skillsService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(skillsService.existsByNombre(skillsDto.getNombre()) && skillsService.getByNombre(skillsDto.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(skillsDto.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(skillsDto.getPorcentaje()==null || skillsDto.getPorcentaje()<0 )
            return new ResponseEntity(new Mensaje("el porcentaje debe ser mayor que 0"), HttpStatus.BAD_REQUEST);

        Skills skills = skillsService.getOne(id).get();
        skills.setNombre(skillsDto.getNombre());
        skills.setPorcentaje(skillsDto.getPorcentaje());
        skillsService.save(skills);
        return new ResponseEntity(new Mensaje("skills actualizado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!skillsService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        skillsService.delete(id);
        return new ResponseEntity(new Mensaje("skills eliminado"), HttpStatus.OK);
    }


}
