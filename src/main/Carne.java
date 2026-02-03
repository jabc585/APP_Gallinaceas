package com.gallinaceas;

public class Carne extends Producto {

    private static final int DIAS_CADUCIDAD_CARNE = 4;
    private int stockKg;


    public Carne(int kilosAñadidos) {
        super(DIAS_CADUCIDAD_CARNE);
        this.stockKg = 1000 + kilosAñadidos;
    }

    public int getStockKg() {
        return stockKg;
    }

    @Override
    public String toString() {
        return String.format(
            "--- DETALLES CARNE ---\n" +
            "Fecha envasado: %s\n" +
            "Fecha caducidad: %s\n" +
            "Stock total: %d kg",
            getFechaEnvasadoFormateada(),
            getFechaCaducidadFormateada(),
            stockKg
        );
    }
}
