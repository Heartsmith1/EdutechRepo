package com.example.api_cursos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api_cursos.models.entities.Contenido;
import com.example.api_cursos.models.request.ContenidoCreate;
import com.example.api_cursos.models.request.ContenidoModificar;
import com.example.api_cursos.services.ContenidoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/contenido")
public class ContenidoController {
    @Autowired
    private ContenidoService contenidoService;

    @GetMapping("")
    public List<Contenido> listarTodos(){
        return contenidoService.obtenerTodos();
    }

    @PostMapping("")
    public Contenido crearNuevo(@Valid @RequestBody ContenidoCreate body){
        return contenidoService.crearNuevo(body);
    }

    @DeleteMapping("")
    public String eliminarContenido(@PathVariable int id){
        contenidoService.eliminarCont(id);
        return "contenido eliminado";
    }

    @PutMapping("")
    public Contenido modificar(@RequestBody ContenidoModificar body){
        return contenidoService.modificarCont(body);
    }
}
