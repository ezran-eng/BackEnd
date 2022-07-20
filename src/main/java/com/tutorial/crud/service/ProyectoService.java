package com.tutorial.crud.service;

import com.tutorial.crud.entity.Producto;
import com.tutorial.crud.entity.Proyecto;
import com.tutorial.crud.repository.ProductoRepository;
import com.tutorial.crud.repository.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProyectoService {
    @Autowired
    ProyectoRepository proyectoRepository;

    public List<Proyecto> list(){
        return proyectoRepository.findAll();
    }

    public Optional<Proyecto> getOne(int id){
        return proyectoRepository.findById(id);
    }

    public Optional<Proyecto> getByNombre(String nombre){
        return proyectoRepository.findByNombre(nombre);
    }

    public void  save(Proyecto producto){
        proyectoRepository.save(producto);
    }

    public void delete(int id){
        proyectoRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return proyectoRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre){
        return proyectoRepository.existsByNombre(nombre);
    }
}
