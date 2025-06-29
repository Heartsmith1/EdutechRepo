package com.example.api_cursos.controllers;

import com.example.api_cursos.models.dto.ReporteVentaDto;
import com.example.api_cursos.services.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReporteController {
     @Autowired
    private ReporteService reporteService;

    @GetMapping("/ventas")
    public ResponseEntity<ByteArrayResource> generarReporteVentas() {
        try {
            List<ReporteVentaDto> ventas = reporteService.obtenerVentas();
            byte[] archivo = reporteService.generarExcelVentas(ventas);

            ByteArrayResource resource = new ByteArrayResource(archivo);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporte_ventas.xlsx")
                    .contentLength(archivo.length)
                    .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
