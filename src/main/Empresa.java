package com.gallinaceas;

/**
 * Representa la entidad Empresa con sus datos corporativos.
 */
public class Empresa {
    private final String nombre;
    private final String cif;
    private final String sede;
    private int empleados;

    /**
     * Constructor por defecto que inicializa la empresa con valores corporativos estándar.
     */
    public Empresa() {
        this.nombre = "Gallinaceas, S.A.";
        this.cif = "A-88888888";
        this.sede = "Avenida de la Libertad, 28 - 2828 Madrid";
        this.empleados = 12;
    }

    /**
     * Incrementa el número de empleados tras validar que la cantidad es positiva.
     * @param cantidad Número de empleados a sumar.
     */
    public void registrarNuevosEmpleados(int cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad de empleados no puede ser negativa");
        }
        this.empleados += cantidad;
    }

    public int getEmpleados() {
        return empleados;
    }

    /**
     * @return El nombre legal de la empresa.
     */
    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return String.format(
            "--- DATOS DE LA EMPRESA ---\n" +
            "Nombre: %s\n" +
            "CIF: %s\n" +
            "Sede: %s\n" +
            "Total empleados: %d",
            nombre, cif, sede, empleados
        );
    }
}
