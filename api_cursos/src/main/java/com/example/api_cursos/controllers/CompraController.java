package com.example.api_cursos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.api_cursos.models.entities.Compra;
import com.example.api_cursos.services.CompraService;
@RestController
@RequestMapping("/compras")
public class CompraController {
    @Autowired
    private CompraService compraService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    public ResponseEntity<?> realizarCompra(@RequestParam Long usuarioId, @RequestParam Long cursoId) {
        // Validar usuario (servicio externo)
        String urlUsuario = "http://localhost:8080/usuarios/" + usuarioId;
        try {
            ResponseEntity<String> userResponse = restTemplate.getForEntity(urlUsuario, String.class);
            if (userResponse.getStatusCode() != HttpStatus.OK) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al validar el usuario");
        }


        Compra compra = compraService.registrarCompra(usuarioId, cursoId);
        return ResponseEntity.ok(compra);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Compra>> obtenerComprasUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(compraService.obtenerComprasPorUsuario(usuarioId));
    }
}
