package com.example.api_cursos.models.entities;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CompraRequest {
    private int idCurso;

    private String nombreCurso;

    private String emailUsuario;

    private long idUsuario;
    

    @NotBlank
    private String numeroTarjeta;



}
