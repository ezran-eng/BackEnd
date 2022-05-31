package com.backend.backend.repository;

import com.backend.backend.models.Skills;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillsRepo extends JpaRepository<Skills, Long> {
}
