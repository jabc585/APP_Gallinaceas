package com.gallinaceas;

public class Gallinaceas {
    private static final String NOMBRE = "Gallinaceas, S.A.";
    private static final String CIF = "A-8888888";
    private static final String SEDE = "Avenida de la Libertad, 28 - 28028 Madrid";
    private int numEmpleados = 12;
    
    public void añadirEmpleados(int cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("No se pueden añadir empleados negativos");
        }
        numEmpleados += cantidad;
    }
    
    public boolean reducirEmpleados(int cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("No se pueden reducir empleados negativos");
        }
        if (cantidad > numEmpleados) {
            return false;
        }
        numEmpleados -= cantidad;
        return true;
    }
    
    public int getNumEmpleados() {
        return numEmpleados;
    }
    
    public String getInfo() {
        return String.format(
            "EMPRESA: %s%nCIF:     %s%nSEDE:    %s%nSTAFF:   %d empleados",
            NOMBRE, CIF, SEDE, numEmpleados);
    }
}