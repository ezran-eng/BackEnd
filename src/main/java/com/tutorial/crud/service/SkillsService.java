package com.tutorial.crud.service;

import com.tutorial.crud.entity.Producto;
import com.tutorial.crud.entity.Skills;
import com.tutorial.crud.repository.ProductoRepository;
import com.tutorial.crud.repository.SkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SkillsService {
    @Autowired
    SkillsRepository skillsRepository;

    public List<Skills> list(){
        return skillsRepository.findAll();
    }

    public Optional<Skills> getOne(int id){
        return skillsRepository.findById(id);
    }

    public Optional<Skills> getByNombre(String nombre){
        return skillsRepository.findByNombre(nombre);
    }

    public void  save(Skills producto){
        skillsRepository.save(producto);
    }

    public void delete(int id){
        skillsRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return skillsRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre){
        return skillsRepository.existsByNombre(nombre);
    }

}
