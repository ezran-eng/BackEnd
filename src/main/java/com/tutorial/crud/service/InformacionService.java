package com.tutorial.crud.service;

import com.tutorial.crud.entity.Informacion;
import com.tutorial.crud.repository.InformacionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InformacionService {

    @Autowired
    InformacionRepository informacionRepository;

    public List<Informacion> list(){
        return informacionRepository.findAll();
    }

    public Optional<Informacion> getOne(int id){
        return informacionRepository.findById(id);
    }

    public Optional<Informacion> getByNombre(String nombre){
        return informacionRepository.findByNombre(nombre);
    }

    public void  save(Informacion informacion){
        informacionRepository.save(informacion);
    }

    public void delete(int id){
        informacionRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return informacionRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre){
        return informacionRepository.existsByNombre(nombre);
    }
}
