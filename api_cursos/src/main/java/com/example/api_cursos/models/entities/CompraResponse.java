package com.example.api_cursos.models.entities;

import lombok.Data;

@Data
public class CompraResponse {
    private String idBoleta;
    private String mensaje;
    private Boolean exito;
}
