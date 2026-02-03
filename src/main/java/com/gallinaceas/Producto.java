package com.gallinaceas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Producto {
    private final LocalDate fechaEnvasado;
    private final LocalDate fechaCaducidad;
    private final int diasCaducidad;
    private final String nombre;
    
    public Producto(String nombre, int diasCaducidad) {
        this.nombre = nombre;
        this.diasCaducidad = diasCaducidad;
        this.fechaEnvasado = LocalDate.now();
        this.fechaCaducidad = fechaEnvasado.plusDays(diasCaducidad);
    }
    
    public boolean estaCaducado() {
        return LocalDate.now().isAfter(fechaCaducidad);
    }
    
    public int diasHastaCaducidad() {
        return Math.max(0, (int) java.time.temporal.ChronoUnit.DAYS.between(
            LocalDate.now(), fechaCaducidad));
    }
    
    public String getInfo() {
        return String.format("%s [Envasado: %s | Caduca: %s | Días restantes: %d]",
            nombre,
            fechaEnvasado.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
            fechaCaducidad.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
            diasHastaCaducidad());
    }
}