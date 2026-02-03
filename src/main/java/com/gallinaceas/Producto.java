package com.gallinaceas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public abstract class Producto {
    private final LocalDate fechaEnvasado;
    private final LocalDate fechaCaducidad;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    protected Producto(int diasCaducidad) {
        this.fechaEnvasado = LocalDate.now();
        this.fechaCaducidad = this.fechaEnvasado.plusDays(diasCaducidad);
    }
    
    public long diasHastaCaducidad() {
        return ChronoUnit.DAYS.between(LocalDate.now(), fechaCaducidad);
    }
    
    public boolean estaCaducado() {
        return LocalDate.now().isAfter(fechaCaducidad);
    }
    
    // Getters
    public String getFechaEnvasadoFormateada() {
        return fechaEnvasado.format(FORMATTER);
    }
    
    public String getFechaCaducidadFormateada() {
        return fechaCaducidad.format(FORMATTER);
    }
    
    @Override
    public abstract String toString();
}