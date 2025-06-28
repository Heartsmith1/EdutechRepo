package com.example.servicio_usuarios.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.servicio_usuarios.models.entities.Ticket;
import com.example.servicio_usuarios.services.TicketService;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<Ticket> crearTicket(@RequestParam Long usuarioId,
                                              @RequestParam String asunto,
                                              @RequestParam String descripcion) {
        Ticket ticket = ticketService.crearTicket(usuarioId, asunto, descripcion);
        return ResponseEntity.ok(ticket);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Ticket>> listarTicketsUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(ticketService.obtenerTicketsPorUsuario(usuarioId));
    }

    @PutMapping("/{ticketId}/estado")
    public ResponseEntity<String> cambiarEstadoTicket(@PathVariable Long ticketId,
                                                      @RequestParam boolean activo) {
        return ticketService.cambiarEstado(ticketId, activo)
                .map(ticket -> ResponseEntity.ok("Estado actualizado"))
                .orElseGet(() -> ResponseEntity.notFound().build());
                
    }
}
