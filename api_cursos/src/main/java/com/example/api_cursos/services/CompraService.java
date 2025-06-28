package com.example.api_cursos.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.api_cursos.models.entities.Compra;
import com.example.api_cursos.repositories.CompraRepository;

@Service
public class CompraService {
     private final CompraRepository compraRepository;

    public CompraService(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    public Compra registrarCompra(Long usuarioId, Long cursoId) {
        Compra compra = new Compra(usuarioId, cursoId, LocalDateTime.now());
        return compraRepository.save(compra);
    }

    public List<Compra> obtenerComprasPorUsuario(Long usuarioId) {
        return compraRepository.findByUsuarioId(usuarioId);
    }
}
