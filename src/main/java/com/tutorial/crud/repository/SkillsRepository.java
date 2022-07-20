package com.tutorial.crud.repository;


import com.tutorial.crud.entity.Skills;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SkillsRepository  extends JpaRepository<Skills, Integer> {
    Optional<Skills> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
