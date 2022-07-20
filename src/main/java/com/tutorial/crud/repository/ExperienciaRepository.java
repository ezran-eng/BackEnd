package com.tutorial.crud.repository;

import com.tutorial.crud.entity.Experiencia;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExperienciaRepository extends JpaRepository<Experiencia, Integer> {
    Optional<Experiencia> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
