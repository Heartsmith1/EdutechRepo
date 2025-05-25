package com.example.api_cursos.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="contenido")
public class Contenido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String titulo;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String descripcion;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String urlVideo;

    @ManyToOne
    @JoinColumn(name= "curso_id", nullable = false)
    private Curso curso;
}
