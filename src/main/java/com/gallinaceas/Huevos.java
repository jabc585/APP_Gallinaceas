package com.gallinaceas;

import java.util.HashMap;
import java.util.Map;

public class Huevos {
    private final Producto producto;
    private final Map<String, Integer> registro; // Clave: "tipo-provincia-ciudad-granja"
    private static final int MAX_TIPO = 4;
    private static final int MAX_PROVINCIA = 100;
    private static final int MAX_CIUDAD = 1000;
    private static final int MAX_GRANJA = 100;
    
    public Huevos() {
        this.producto = new Producto("Huevos", 7);
        this.registro = new HashMap<>();
    }
    
    public int añadirHuevos(int tipoGallina, int provincia, int ciudad, 
                           int granja, int unidades) {
        validarIndices(tipoGallina, provincia, ciudad, granja);
        
        if (unidades < 0) {
            throw new IllegalArgumentException("Unidades no pueden ser negativas");
        }
        
        String clave = generarClave(tipoGallina, provincia, ciudad, granja);
        int actual = registro.getOrDefault(clave, 0);
        registro.put(clave, actual + unidades);
        
        return actual + unidades;
    }
    
    public int obtenerPuesta(int tipoGallina, int provincia, int ciudad, int granja) {
        validarIndices(tipoGallina, provincia, ciudad, granja);
        String clave = generarClave(tipoGallina, provincia, ciudad, granja);
        return registro.getOrDefault(clave, 0);
    }
    
    public int getTotalHuevos() {
        return registro.values().stream().mapToInt(Integer::intValue).sum();
    }
    
    private void validarIndices(int tipoGallina, int provincia, int ciudad, int granja) {
        if (tipoGallina < 0 || tipoGallina >= MAX_TIPO) {
            throw new IllegalArgumentException("Tipo gallina debe estar entre 0 y " + (MAX_TIPO-1));
        }
        if (provincia < 0 || provincia >= MAX_PROVINCIA) {
            throw new IllegalArgumentException("Provincia debe estar entre 0 y " + (MAX_PROVINCIA-1));
        }
        if (ciudad < 0 || ciudad >= MAX_CIUDAD) {
            throw new IllegalArgumentException("Ciudad debe estar entre 0 y " + (MAX_CIUDAD-1));
        }
        if (granja < 0 || granja >= MAX_GRANJA) {
            throw new IllegalArgumentException("Granja debe estar entre 0 y " + (MAX_GRANJA-1));
        }
    }
    
    private String generarClave(int tipo, int provincia, int ciudad, int granja) {
        return String.format("%d-%d-%d-%d", tipo, provincia, ciudad, granja);
    }
    
    public String getInfo() {
        return producto.getInfo() + " | Total huevos registrados: " + getTotalHuevos();
    }
    
    public boolean estaCaducado() {
        return producto.estaCaducado();
    }
}