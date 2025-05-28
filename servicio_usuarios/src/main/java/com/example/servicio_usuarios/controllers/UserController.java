package com.example.servicio_usuarios.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.servicio_usuarios.models.entities.User;
import com.example.servicio_usuarios.models.request.UserCrear;
import com.example.servicio_usuarios.models.request.UserUpdate;
import com.example.servicio_usuarios.services.UserServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {
    
    @Autowired
    private UserServices userServices;

    @GetMapping("/")
    public List<User> traerTodos(){
        return userServices.obtenerTodos();
    }
    @PostMapping("/")
    public User crear(@Valid@RequestBody UserCrear nuevo){
        return userServices.registrar(nuevo);
    }

    @PutMapping("/")
    public User modificar(@Valid @RequestBody UserUpdate body){
        return userServices.modificar(body);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable int id) {
        userServices.eliminar(id);
        return "ok";
    }

    @PostMapping("/{id}/roles")
    public ResponseEntity<?> asignarRol(@PathVariable int id, @RequestParam String rol){
        userServices.asignarRolAUsuario(id, rol);
        return ResponseEntity.ok("Rol asignado");
    }
}
