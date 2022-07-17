package com.backend.backend.repository;

import com.backend.backend.models.Ingreso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IngresoRepo extends JpaRepository<Ingreso, Long> {

    Optional<Ingreso> findByEmail(String email);

}

