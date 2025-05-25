package com.example.api_cursos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.api_cursos.models.entities.Curso;
import com.example.api_cursos.repositories.CursoRepository;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    public Curso obtenerPorId(int id){
        Curso curso = cursoRepository.findById(id).orElse(null);
        if (curso==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Curso no encontrado");
        }
        return curso;
    }
}
