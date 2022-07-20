package com.tutorial.crud.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class EducacionDto {
    @NotBlank
    private String nombre;

    @NotBlank
    private String descripcion;

    @Min(2010)
    @Max(2022)
    private int anio;

    public EducacionDto() {
    }

    public EducacionDto(@NotBlank String nombre,@NotBlank String descripcion, int anio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.anio = anio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }
}
