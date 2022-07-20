package com.tutorial.crud.repository;

import com.tutorial.crud.entity.Informacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InformacionRepository extends JpaRepository<Informacion, Integer> {
    Optional<Informacion> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
