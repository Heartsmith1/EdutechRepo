package com.example.api_cursos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api_cursos.models.entities.Curso;
import com.example.api_cursos.models.request.CursoCreate;
import com.example.api_cursos.services.CursoService;

import jakarta.validation.Valid;

@RequestMapping("/curso")
@RestController
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @GetMapping("")
    public List<Curso> todos(){
        return cursoService.obtenerTodos();
    }
    
    @GetMapping("{id}")
    public Curso listarUno(@PathVariable int id){
        return cursoService.obtenerPorId(id);

    }

    @PostMapping("")
    public Curso cursoNuevo(@Valid @RequestBody CursoCreate cuerpo){
        return cursoService.crearNuevo(cuerpo);

    }
}
