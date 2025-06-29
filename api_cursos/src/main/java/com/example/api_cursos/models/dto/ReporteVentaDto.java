package com.example.api_cursos.models.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ReporteVentaDto {

 
    public ReporteVentaDto(long idUsuario2, int idCurso) {
        //TODO Auto-generated constructor stub
    }
    private Long idCompra;
    private long idUsuario;
    private String nombreCurso;
    private LocalDate fecha;
    private Double monto;
}
