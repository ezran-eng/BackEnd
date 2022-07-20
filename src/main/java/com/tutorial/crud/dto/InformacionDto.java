package com.tutorial.crud.dto;

import javax.validation.constraints.NotBlank;

public class InformacionDto {
    @NotBlank
    private String nombre;
    @NotBlank
    private String titulo;
    @NotBlank
    private String descripcion;
    @NotBlank
    private String imagen;

    public InformacionDto() {
    }

    public InformacionDto(@NotBlank String nombre, @NotBlank String titulo, @NotBlank String descripcion, @NotBlank String imagen) {
        this.nombre = nombre;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
