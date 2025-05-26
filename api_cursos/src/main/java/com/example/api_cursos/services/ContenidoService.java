package com.example.api_cursos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.api_cursos.models.entities.Contenido;
import com.example.api_cursos.models.entities.Curso;
import com.example.api_cursos.models.request.ContenidoCreate;
import com.example.api_cursos.models.request.ContenidoModificar;
import com.example.api_cursos.repositories.ContenidoRepository;
import com.example.api_cursos.repositories.CursoRepository;

@Service
public class ContenidoService {
    @Autowired
    private ContenidoRepository contenidoRepository;
    @Autowired
    private CursoRepository cursoRepository;

    public List<Contenido> obtenerTodos(){
        return contenidoRepository.findAll();
    }

     public Contenido obtenerPorId(int id){
        Contenido cont = contenidoRepository.findById(id).orElse(null);
        if (cont==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Contenido no encontrado");
        }
        return cont;
    }

    public Contenido crearNuevo(ContenidoCreate solicitud){
        Contenido nuevo = new Contenido();

        nuevo.setDescripcion(solicitud.getDescripcion());
        nuevo.setTitulo(solicitud.getTitulo());
        nuevo.setUrlVideo(solicitud.getUrlVideo());

        Curso cursoAsociado = cursoRepository.findById(solicitud.getIdCurso()).orElse(null);

        nuevo.setCurso(cursoAsociado);
        return contenidoRepository.save(nuevo);
    }

    public void eliminarCont(int id){
        Contenido contenido = obtenerPorId(id);
        contenidoRepository.delete(contenido);

    }

    public Contenido modificarCont(ContenidoModificar solicitud){
        Contenido contenido = obtenerPorId(solicitud.getId())
        if(solicitud.getTitulo()!=null){
            contenido.setTitulo(contenido.getTitulo());
        }
        if(solicitud.getDescripcion()!=null){
            contenido.setDescripcion(contenido.getDescripcion());
        }
        if(solicitud.getUrlVideo()!=null){
            contenido.setUrlVideo(contenido.getUrlVideo());
        }
        return contenidoRepository.save(contenido);
    }
}
