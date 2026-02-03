package com.gallinaceas;

public class Gallinaceas {
    private static final String NOMBRE = "Gallinaceas, S.A.";
    private static final String CIF = "A-8888888";
    private static final String SEDE = "Avenida de la Libertad, 28 - 28028 Madrid";
    private int num_emp = 12;

    public int getNum_emp() {
        return num_emp;
    }

    public int total_emp(int nemp) { 
        if (nemp < 0) {
            throw new IllegalArgumentException("No se pueden añadir empleados negativos");
        }
        return num_emp += nemp;
    }

    public String toString() {
        return String.format(
            "EMPRESA: %s\n" +
            "CIF:     %s\n" +
            "SEDE:    %s\n" +
            "STAFF:   %d empleados",
            NOMBRE, CIF, SEDE, num_emp
        );
    }
}