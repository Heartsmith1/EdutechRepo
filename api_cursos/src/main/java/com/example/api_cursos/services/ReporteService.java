package com.example.api_cursos.services;

import com.example.api_cursos.models.dto.ReporteVentaDto;
import com.example.api_cursos.repositories.CompraRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReporteService {
        @Autowired
    private CompraRepository compraRepository;

    public List<ReporteVentaDto> obtenerVentas() {
        return compraRepository.findAll().stream()
                .map(compra -> new ReporteVentaDto(
                        compra.getIdUsuario(),
                        compra.getIdCurso()
                ))
                .collect(Collectors.toList());
    }

    public byte[] generarExcelVentas(List<ReporteVentaDto> ventas) throws Exception {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Ventas");

            // Encabezado
            Row header = sheet.createRow(0);
            String[] columnas = {"ID Compra", "Usuario", "Curso", "Fecha", "Monto"};
            for (int i = 0; i < columnas.length; i++) {
                header.createCell(i).setCellValue(columnas[i]);
            }

            // Datos
            int rowIdx = 1;
            for (ReporteVentaDto dto : ventas) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(dto.getIdCompra());
                row.createCell(1).setCellValue(dto.getIdUsuario());
                row.createCell(2).setCellValue(dto.getNombreCurso());
                row.createCell(3).setCellValue(dto.getFecha().toString());
                row.createCell(4).setCellValue(dto.getMonto());
            }

            // Exportar a byte[]
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            return out.toByteArray();
        }
    }
}
