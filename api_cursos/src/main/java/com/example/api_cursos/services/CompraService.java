package com.example.api_cursos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api_cursos.models.entities.Compra;
import com.example.api_cursos.repositories.CompraRepository;

@Service
public class CompraService {
     @Autowired
    private CompraRepository compraRepository;

    public Compra guardarCompra(Compra compra) {
        return compraRepository.save(compra);
    }

    
}
