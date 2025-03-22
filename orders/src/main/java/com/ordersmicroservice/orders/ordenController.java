package com.ordersmicroservice.orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ordenes")
public class ordenController {

    private final List<Orden> ordenes = new ArrayList<>();

    public ordenController() {
        // Datos ficticios
        ordenes.add(new Orden(1, new BigDecimal("150.50"), "Sin gluten", "Calle 123", 2, "Pendiente", LocalDateTime.now(), null, null, "Tarjeta", "12345678", "87654321"));
        ordenes.add(new Orden(2, new BigDecimal("250.75"), "Entrega urgente", "Avenida 456", 1, "En Camino", LocalDateTime.now(), null, null, "Efectivo", "23456789", "98765432"));
    }

    @GetMapping
    public List<Orden> getAllOrdenes() {
        return ordenes;
    }

    @GetMapping("/{id}")
    public Orden getOrdenById(@PathVariable int id) {
        Optional<Orden> orden = ordenes.stream().filter(o -> o.getIdOrden() == id).findFirst();
        return orden.orElse(null);
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Orden {
    private int idOrden;
    private BigDecimal precioTotal;
    private String informacionAdicional;
    private String direccionEntrega;
    private int cantidad;
    private String estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaEntregaReal;
    private LocalDateTime fechaEntregaEstimada;
    private String metodoPago;
    private String dniCliente;
    private String dniRepartidor;
}
