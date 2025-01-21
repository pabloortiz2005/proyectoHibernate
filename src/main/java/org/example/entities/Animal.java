package org.example.entities;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity

public class Animal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = false)
    private String nombre;

    @Column(unique = false)
    private Animales Especie;

    @Column(nullable = false)
    private Integer edad;

    @Column(unique = false)
    private String desc;

    @Column(nullable = false)
    private Estado estado;

    @Column(nullable = true)
    private Estado nombreF;

    @Column(nullable = true)
    private Estado edadF;

    @Column(nullable = true)
    private Estado ciudadF;

    public Animal() {
    }

    public Animal(Integer id, String nombre, Animales especie, Integer edad, String desc, Estado estado, Estado nombreF, Estado edadF, Estado ciudadF) {
        this.id = id;
        this.nombre = nombre;
        Especie = especie;
        this.edad = edad;
        this.desc = desc;
        this.estado = estado;
        this.nombreF = nombreF;
        this.edadF = edadF;
        this.ciudadF = ciudadF;
    }

    public Estado getCiudadF() {
        return ciudadF;
    }

    public void setCiudadF(Estado ciudadF) {
        this.ciudadF = ciudadF;
    }

    public Estado getEdadF() {
        return edadF;
    }

    public void setEdadF(Estado edadF) {
        this.edadF = edadF;
    }

    public Estado getNombreF() {
        return nombreF;
    }

    public void setNombreF(Estado nombreF) {
        this.nombreF = nombreF;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Animales getEspecie() {
        return Especie;
    }

    public void setEspecie(Animales especie) {
        Especie = especie;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", Especie=" + Especie +
                ", edad=" + edad +
                ", desc='" + desc + '\'' +
                ", estado=" + estado +
                ", nombreF=" + nombreF +
                ", edadF=" + edadF +
                ", ciudadF=" + ciudadF +
                '}';
    }
}

