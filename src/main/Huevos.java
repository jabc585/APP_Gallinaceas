package com.gallinaceas;

import java.util.Arrays;

public class Huevos extends Producto {

    private static final int DIAS_CADUCIDAD_HUEVOS = 7;
    private final int[] unidadesPorPuesto;
    private final String codigoMarcado;

    public Huevos(int[] unidades, String codigo) {
        super(DIAS_CADUCIDAD_HUEVOS);
        this.unidadesPorPuesto = Arrays.copyOf(unidades, unidades.length);
        this.codigoMarcado = codigo;
    }

    public int getTotalUnidades() {
        return Arrays.stream(unidadesPorPuesto).sum();
    }

    @Override
    public String toString() {
        return String.format(
            "--- DETALLES HUEVOS ---\n" +
            "Código Marcado: %s\n" +
            "Fecha envasado: %s\n" +
            "Fecha caducidad: %s\n" +
            "Total unidades: %d",
            codigoMarcado,
            getFechaEnvasadoFormateada(),
            getFechaCaducidadFormateada(),
            getTotalUnidades()
        );
    }
}
