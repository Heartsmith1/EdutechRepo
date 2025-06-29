package com.example.servicio_usuarios.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.servicio_usuarios.models.entities.User;
import com.example.servicio_usuarios.models.request.LoginRequest;
import com.example.servicio_usuarios.models.responses.LoginResponse;
import com.example.servicio_usuarios.services.JwtService;
import com.example.servicio_usuarios.services.UserServices;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AunthenticationController {
    
     @Autowired
    private UserServices userService;

    @Autowired
    private JwtService jwtService;
    
    @PostMapping("token")
    public LoginResponse postMethodName(@RequestBody @Valid LoginRequest body) {

        String token = userService.intentarLogin(body.getEmail(), body.getPassword());

        return new LoginResponse(token);
    }

    @GetMapping("yo")
    @SecurityRequirement(name = "bearerAuth")
    public User misDatos(@Parameter(hidden = true) @RequestHeader("Authorization") String authHeader) {
        User user = jwtService.comprobarToken(authHeader);
        return user;
    }
}
