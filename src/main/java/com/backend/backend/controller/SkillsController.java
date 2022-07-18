package com.backend.backend.controller;

import com.backend.backend.models.Skills;
import com.backend.backend.services.SkillsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
public class SkillsController {
    private final SkillsService skillsService;

    public SkillsController(SkillsService skillsService) {
        this.skillsService = skillsService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Skills>> obtenerSkills(){
        List <Skills> skills = skillsService.traerSkills();
        return new ResponseEntity<>(skills, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<Skills> actualizarSkills(@RequestBody Skills skills){
        Skills updateSkills = skillsService.actualizarSkills(skills);
        return new ResponseEntity<>(updateSkills, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<Skills> agregarSkills(@RequestBody Skills skills){
        Skills addSkills = skillsService.addSkills(skills);
        return new ResponseEntity<>(addSkills, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Skills> eliminarSkills(@PathVariable Long id){
        skillsService.borrarSkills(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
