package com.tutorial.crud.dto;

import javax.validation.constraints.NotBlank;

public class ExperienciaDto {

    @NotBlank
    private String nombre;

    @NotBlank
    private String descripcion;


    private int anio;

    public ExperienciaDto() {
    }

    public ExperienciaDto(@NotBlank String nombre,@NotBlank String descripcion, int anio) {
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
