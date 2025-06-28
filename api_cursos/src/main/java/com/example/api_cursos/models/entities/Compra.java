package com.example.api_cursos.models.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "compras")
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long usuarioId;
    private Long cursoId;
    private LocalDateTime fecha;

    public Compra() {}

    public Compra(Long usuarioId, Long cursoId, LocalDateTime fecha) {
        this.usuarioId = usuarioId;
        this.cursoId = cursoId;
        this.fecha = fecha;
    }
}
