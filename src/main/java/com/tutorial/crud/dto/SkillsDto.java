package com.tutorial.crud.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class SkillsDto {
    @NotBlank
    private String nombre;
    @Min(0)
    private Float porcentaje;

    public SkillsDto() {
    }

    public SkillsDto(@NotBlank String nombre, @Min(0) Float porcentaje) {
        this.nombre = nombre;
        this.porcentaje = porcentaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Float porcentaje) {
        this.porcentaje = porcentaje;
    }
}
