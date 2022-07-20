package com.tutorial.crud.repository;

import com.tutorial.crud.entity.Educacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EducacionRepository extends JpaRepository<Educacion, Integer> {
    Optional<Educacion> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
