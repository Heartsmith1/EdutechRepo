package com.example.servicio_usuarios.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.servicio_usuarios.models.entities.User;
import com.example.servicio_usuarios.models.entities.UserRol;
import com.example.servicio_usuarios.models.request.UserCrear;
import com.example.servicio_usuarios.models.request.UserUpdate;
import com.example.servicio_usuarios.repositories.RolRepository;
import com.example.servicio_usuarios.repositories.UserRepository;

@Service
public class UserServices {
    
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RolRepository rolRepo;

    @Autowired JwtService jwtService;

    public List<User> obtenerTodos(){
        return userRepo.findAll();
        
    }

    public List<User> obtenerActivos(){
       return userRepo.findByActive(true);
    }
    
    public User obtenerPorId(int id){
        User usuario = userRepo.findById(id).orElse(null);
        if (usuario == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuario no encontrado");
        }
        return usuario;
    }
    public User obtenerPorEmail(String email){
        User usuario = userRepo.findByEmail(email);
        if(usuario == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return usuario;
    }

    public User registrar(UserCrear usuario){
        try {
            User nuevoUser = new User();
        /*Datos de negocio */
        nuevoUser.setActive(true);
        nuevoUser.setCreationDate(new Date());
        /*Datos del usuario */
        nuevoUser.setEmail(usuario.getEmail());
        nuevoUser.setName(usuario.getName());
        nuevoUser.setPassword(generarHash(usuario.getPassword()));
        nuevoUser.setPhone(usuario.getPhone());

        return userRepo.save(nuevoUser);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Usuario Registrado");
        }
        
    }

    public User modificar(UserUpdate modificado){
        User usuario = userRepo.findById(modificado.getId()).orElse(null);
        if (usuario == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"no encontrado");
        }
        if (modificado.getName() != null){
            usuario.setName(modificado.getName());
        }
        if (modificado.getPhone() != null){
            usuario.setPhone(modificado.getPassword());
        }
        if (modificado.getPassword() != null){
            usuario.setPassword((generarHash(modificado.getPassword())));
        }
        return userRepo.save(usuario);
    }

    public String generarHash(String password){
        PasswordEncoder hasheador = new BCryptPasswordEncoder();
        return hasheador.encode(password);
    }

    public void eliminar(int id){
        User usuario = userRepo.findById(id).orElse(null);
        if (usuario == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }
        userRepo.delete(usuario);
    }

    public void asignarRolAUsuario(int id, String nombreRol) {
        User usuario = userRepo.findById(id).orElse(null);
        if (usuario == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }
        UserRol rol = rolRepo.findByNombre(nombreRol);
        if (rol == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Rol no encontrado");
        }
        usuario.getRoles().add(rol);
        userRepo.save(usuario);
        }
    
    public boolean comprobarPassword(String hash,String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(password, hash);

    }
     public String intentarLogin(String email, String password) {
        User user = obtenerPorEmail(email);
        if(user != null){
            boolean passwordCorrecta = comprobarPassword(user.getPassword(),password);

            if(passwordCorrecta){
                return jwtService.generarJwt(user);
            }else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Contraseña invalida");
            }
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Correo no registrado");
            }
        }
}
