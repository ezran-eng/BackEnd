package com.backend.backend.services;

import com.backend.backend.models.Skills;
import com.backend.backend.repository.SkillsRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SkillsService {
    private final SkillsRepo skillsRepo;

    @Autowired
    public SkillsService(SkillsRepo skillsRepo) {
        this.skillsRepo = skillsRepo;
    }

    public Skills addSkills(Skills skills){
        return skillsRepo.save(skills);
    }

    public List<Skills> traerSkills(){
        return skillsRepo.findAll();
    }

    public Skills actualizarSkills(Skills skills){
        return skillsRepo.save(skills);
    }

    public void borrarSkills(Long id){
        skillsRepo.deleteById(id);
    }

}
