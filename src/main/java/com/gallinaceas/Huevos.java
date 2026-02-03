package com.gallinaceas;

public class Huevos extends Producto {
    private final int[][][][] registroPuesta = new int[4][100][1000][100];
    
    public Huevos() {
        super(7); // Caduca en 7 días
    }
    
    public int setHuevos(int tipoGallina, int provincia, int ciudad, 
                                int granja, int unidades) {
        validarIndices(tipoGallina, provincia, ciudad, granja);
        
        if (unidades < 0) {
            throw new IllegalArgumentException("Las unidades no pueden ser negativas");
        }
        
        registroPuesta[tipoGallina][provincia][ciudad][granja] += unidades;
        return registroPuesta[tipoGallina][provincia][ciudad][granja];
    }
    
    public int obtenerPuesta(int tipoGallina, int provincia, int ciudad, int granja) {
        validarIndices(tipoGallina, provincia, ciudad, granja);
        return registroPuesta[tipoGallina][provincia][ciudad][granja];
    }
    
    private void validarIndices(int tipoGallina, int provincia, int ciudad, int granja) {
        if (tipoGallina < 0 || tipoGallina >= 4) throw new IllegalArgumentException("Tipo gallina inválido");
        if (provincia < 0 || provincia >= 100) throw new IllegalArgumentException("Provincia inválida");
        if (ciudad < 0 || ciudad >= 1000) throw new IllegalArgumentException("Ciudad inválida");
        if (granja < 0 || granja >= 100) throw new IllegalArgumentException("Granja inválida");
    }
    
    @Override
    public String toString() {
        return String.format(
            "Huevos [Envasado: %s | Caduca: %s | Días restantes: %d]",
            getFechaEnvasadoFormateada(),
            getFechaCaducidadFormateada(),
            diasHastaCaducidad()
        );
    }
}