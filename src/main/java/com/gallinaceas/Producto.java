package com.gallinaceas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Producto {
    private final LocalDate fechaEnvasado;
    private final LocalDate fechaCaducidad;
    private final String nombre;
    
    public Producto(String nombre, int diasCaducidad) {
        this.nombre = nombre;
        this.fechaEnvasado = LocalDate.now();
        this.fechaCaducidad = fechaEnvasado.plusDays(diasCaducidad);
    }
    
    public boolean estaCaducado() {
        return LocalDate.now().isAfter(fechaCaducidad);
    }
    
    public int diasHastaCaducidad() {
        return (int) Math.max(0, ChronoUnit.DAYS.between(LocalDate.now(), fechaCaducidad));
    }
    
    public String getInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("%s [Envasado: %s | Caduca: %s | Días restantes: %d]",
            nombre,
            fechaEnvasado.format(formatter),
            fechaCaducidad.format(formatter),
            diasHastaCaducidad());
    }
}
