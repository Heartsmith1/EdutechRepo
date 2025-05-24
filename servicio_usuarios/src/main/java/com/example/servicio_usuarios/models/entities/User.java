package com.example.servicio_usuarios.models.entities;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="usuario")
@Data
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true,nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    
    private String name;

    private String phone;

    @Column(name="fecha_creacion")
    private Date creationDate;

    @Column(nullable = false)
    private Boolean active;
}
