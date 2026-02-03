package com.gallinaceas;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Carne {
    private static class Lote {
        Producto producto;
        int cantidad;
        
        Lote(int cantidad) {
            this.producto = new Producto("Carne", 4);
            this.cantidad = cantidad;
        }
    }
    
    private final List<Lote> lotes;
    
    public Carne() {
        this.lotes = new ArrayList<>();
        this.lotes.add(new Lote(1000)); // Stock inicial
    }
    
    public void añadirStock(int kilos) {
        if (kilos <= 0) {
            throw new IllegalArgumentException("Kilos deben ser positivos");
        }
        lotes.add(new Lote(kilos));
    }
    
    public boolean retirarStock(int kilos) {
        if (kilos <= 0) {
            throw new IllegalArgumentException("Kilos deben ser positivos");
        }
        
        // Primero eliminamos caducados para saber el stock real actual
        eliminarCaducados();
        
        if (getStockDisponible() < kilos) {
            return false;
        }
        
        int pendiente = kilos;
        Iterator<Lote> it = lotes.iterator();
        
        while (it.hasNext() && pendiente > 0) {
            Lote lote = it.next();
            // Ya no hace falta check de caducidad aquí porque acabamos de limpiar
            
            if (lote.cantidad <= pendiente) {
                pendiente -= lote.cantidad;
                it.remove();
            } else {
                lote.cantidad -= pendiente;
                pendiente = 0;
            }
        }
        
        return true;
    }
    
    public int getStockDisponible() {
        eliminarCaducados();
        return lotes.stream().mapToInt(l -> l.cantidad).sum();
    }
    
    public int getStockTotal() {
        return lotes.stream().mapToInt(l -> l.cantidad).sum();
    }
    
    private void eliminarCaducados() {
        lotes.removeIf(lote -> lote.producto.estaCaducado());
    }
    
    public String getInfo() {
        return String.format("Carne [Stock disponible: %d kg | Lotes activos: %d]",
            getStockDisponible(), lotes.size());
    }
    
    public List<String> getInfoLotes() {
        List<String> info = new ArrayList<>();
        eliminarCaducados();
        
        for (int i = 0; i < lotes.size(); i++) {
            Lote lote = lotes.get(i);
            info.add(String.format("Lote %d: %d kg | %s", 
                i + 1, lote.cantidad, lote.producto.getInfo()));
        }
        
        return info;
    }
}