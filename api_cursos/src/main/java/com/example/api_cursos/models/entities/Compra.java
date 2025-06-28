package com.example.api_cursos.models.entities;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class Compra {
    @Positive
    private int idCurso;
    
    private int idUsuario;
    @NotBlank
    private String numeroTarjeta;
}
