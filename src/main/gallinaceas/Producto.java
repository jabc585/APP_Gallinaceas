package com.gallinaceas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public abstract class Producto {

    private final LocalDate fechaEnvasado;
    private final LocalDate fechaCaducidad;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    protected Producto(int diasParaCaducar) {
        this.fechaEnvasado = LocalDate.now();
        this.fechaCaducidad = this.fechaEnvasado.plusDays(diasParaCaducar);
    }

    public String getFechaEnvasadoFormateada() {
        return fechaEnvasado.format(FORMATTER);
    }

    public String getFechaCaducidadFormateada() {
        return fechaCaducidad.format(FORMATTER);
    }

    public LocalDate getFechaEnvasado() {
        return fechaEnvasado;
    }

    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    @Override
    public abstract String toString();
}
