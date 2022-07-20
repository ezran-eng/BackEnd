package com.tutorial.crud.service;


import com.tutorial.crud.entity.Experiencia;
import com.tutorial.crud.repository.ExperienciaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ExperienciaService {
    @Autowired
    ExperienciaRepository experienciaRepository;

    public List<Experiencia> list(){
        return experienciaRepository.findAll();
    }

    public Optional<Experiencia> getOne(int id){
        return experienciaRepository.findById(id);
    }

    public Optional<Experiencia> getByNombre(String nombre){
        return experienciaRepository.findByNombre(nombre);
    }

    public void  save(Experiencia producto){
        experienciaRepository.save(producto);
    }

    public void delete(int id){
        experienciaRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return experienciaRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre){
        return experienciaRepository.existsByNombre(nombre);
    }
}
