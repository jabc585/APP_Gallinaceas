package com.gallinaceas;

public class Gallinaceas {

    private final static String NOMBRE = "Gallinaceas, S.A.";
    private final static String CIF = "A-8888888";
    private final static String SEDE = "Avenida de la Libertad, 28 - 28028 Madrid";
    private int num_emp = 12;

    int getNum_emp() {
        return num_emp;
    }

    int total_emp(int nemp) { 
        // Realmente habría que controlar public
        return num_emp += nemp;
    }

    public String toString() { // Realmente habría que controlar public
        return "La empresa " + NOMBRE + " con " + CIF 
                + " tiene la sede en la " + SEDE 
                + ". Y cuenta con un total de "
                + this.num_emp + " empleados";
    }

}
