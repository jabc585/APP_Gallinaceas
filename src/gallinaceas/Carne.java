package com.gallinaceas;

public class Carne extends Producto {
    private int stock;
    
    public Carne() {
        super(4); // Caduca en 4 días
        this.stock = 1000;
    }
    
    public void agregarStock(int kilos) {
        if (kilos < 0) {
            throw new IllegalArgumentException("Los kilos no pueden ser negativos");
        }
        this.stock += kilos;
    }
    
    public boolean retirarStock(int kilos) {
        if (kilos <= 0 || kilos > stock) {
            return false;
        }
        this.stock -= kilos;
        return true;
    }
    
    public int getStock() {
        return stock;
    }
    
    @Override
    public String toString() {
        return String.format(
            "Carne [Envasado: %s | Caduca: %s | Stock: %d kg | Días restantes: %d]",
            getFechaEnvasadoFormateada(),
            getFechaCaducidadFormateada(),
            stock,
            diasHastaCaducidad()
        );
    }
}