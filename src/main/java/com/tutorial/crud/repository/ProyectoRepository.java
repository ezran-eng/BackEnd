package com.tutorial.crud.repository;


import com.tutorial.crud.entity.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProyectoRepository  extends JpaRepository<Proyecto, Integer> {
    Optional<Proyecto> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
