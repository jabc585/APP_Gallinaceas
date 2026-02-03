package com.gallinaceas;

import java.util.Scanner;

public class MainAppConsole {
    private final Gallinaceas empresa;
    private final Carne gestionCarne;
    private final Huevos gestionHuevos;
    private final Scanner scanner;
    private boolean ejecutando;
    
    public MainAppConsole() {
        this.empresa = new Gallinaceas();
        this.gestionCarne = new Carne();
        this.gestionHuevos = new Huevos();
        this.scanner = new Scanner(System.in);
        this.ejecutando = true;
    }
    
    public void iniciar() {
        System.out.println("========================================");
        System.out.println("   GALLINACEAS S.A. - SISTEMA CONSOLA   ");
        System.out.println("========================================\n");
        
        while (ejecutando) {
            mostrarMenu();
            procesarOpcion();
        }
        
        scanner.close();
        System.out.println("\nSistema cerrado. ¡Hasta pronto!");
    }
    
    private void mostrarMenu() {
        System.out.println("\n=== MENÚ PRINCIPAL ===");
        System.out.println("1. Información de la Empresa");
        System.out.println("2. Gestión de Carne");
        System.out.println("3. Gestión de Huevos");
        System.out.println("4. Ver Resumen General");
        System.out.println("5. Salir");
        System.out.print("\nSeleccione una opción (1-5): ");
    }
    
    private void procesarOpcion() {
        try {
            int opcion = Integer.parseInt(scanner.nextLine().trim());
            
            switch (opcion) {
                case 1 -> gestionarEmpresa();
                case 2 -> gestionarCarne();
                case 3 -> gestionarHuevos();
                case 4 -> mostrarResumen();
                case 5 -> ejecutando = false;
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un número.");
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
    
    private void gestionarEmpresa() {
        System.out.println("\n=== INFORMACIÓN DE LA EMPRESA ===");
        System.out.println(empresa.getInfo());
        
        System.out.println("\n1. Añadir empleados");
        System.out.println("2. Reducir empleados");
        System.out.println("3. Volver al menú principal");
        System.out.print("Seleccione opción: ");
        
        try {
            int opcion = Integer.parseInt(scanner.nextLine().trim());
            
            switch (opcion) {
                case 1 -> {
                    System.out.print("Cantidad de empleados a añadir: ");
                    int cantidad = Integer.parseInt(scanner.nextLine().trim());
                    empresa.añadirEmpleados(cantidad);
                    System.out.println("Empleados añadidos. Total: " + empresa.getNumEmpleados());
                }
                case 2 -> {
                    System.out.print("Cantidad de empleados a reducir: ");
                    int cantidad = Integer.parseInt(scanner.nextLine().trim());
                    boolean exito = empresa.reducirEmpleados(cantidad);
                    if (exito) {
                        System.out.println("Empleados reducidos. Total: " + empresa.getNumEmpleados());
                    } else {
                        System.out.println("No hay suficientes empleados para reducir.");
                    }
                }
                case 3 -> { /* Volver */ }
                default -> System.out.println("Opción no válida.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private void gestionarCarne() {
        System.out.println("\n=== GESTIÓN DE CARNE ===");
        System.out.println(gestionCarne.getInfo());
        
        System.out.println("\nLotes activos:");
        gestionCarne.getInfoLotes().forEach(System.out::println);
        
        System.out.println("\n1. Añadir stock");
        System.out.println("2. Retirar stock");
        System.out.println("3. Ver detalle de lotes");
        System.out.println("4. Volver al menú principal");
        System.out.print("Seleccione opción: ");
        
        try {
            int opcion = Integer.parseInt(scanner.nextLine().trim());
            
            switch (opcion) {
                case 1 -> {
                    System.out.print("Kilos de carne a añadir: ");
                    int kilos = Integer.parseInt(scanner.nextLine().trim());
                    gestionCarne.añadirStock(kilos);
                    System.out.println("Stock añadido. Total disponible: " + 
                        gestionCarne.getStockDisponible() + " kg");
                }
                case 2 -> {
                    System.out.print("Kilos de carne a retirar: ");
                    int kilos = Integer.parseInt(scanner.nextLine().trim());
                    boolean exito = gestionCarne.retirarStock(kilos);
                    if (exito) {
                        System.out.println("Stock retirado. Total disponible: " + 
                            gestionCarne.getStockDisponible() + " kg");
                    } else {
                        System.out.println("No hay suficiente stock disponible.");
                    }
                }
                case 3 -> {
                    System.out.println("\n=== DETALLE DE LOTES ===");
                    gestionCarne.getInfoLotes().forEach(System.out::println);
                }
                case 4 -> { /* Volver */ }
                default -> System.out.println("Opción no válida.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private void gestionarHuevos() {
        System.out.println("\n=== GESTIÓN DE HUEVOS ===");
        System.out.println(gestionHuevos.getInfo());
        
        if (gestionHuevos.estaCaducado()) {
            System.out.println("⚠️  ¡ADVERTENCIA! Los huevos han caducado.");
        }
        
        System.out.println("\n1. Registrar nueva puesta");
        System.out.println("2. Consultar puesta específica");
        System.out.println("3. Ver total de huevos");
        System.out.println("4. Volver al menú principal");
        System.out.print("Seleccione opción: ");
        
        try {
            int opcion = Integer.parseInt(scanner.nextLine().trim());
            
            switch (opcion) {
                case 1 -> registrarPuesta();
                case 2 -> consultarPuesta();
                case 3 -> System.out.println("Total de huevos registrados: " + 
                    gestionHuevos.getTotalHuevos());
                case 4 -> { /* Volver */ }
                default -> System.out.println("Opción no válida.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private void registrarPuesta() {
        System.out.println("\n=== REGISTRAR NUEVA PUESTA ===");
        
        try {
            System.out.print("Tipo de gallina (0-3): ");
            int tipo = Integer.parseInt(scanner.nextLine().trim());
            
            System.out.print("Provincia (0-99): ");
            int provincia = Integer.parseInt(scanner.nextLine().trim());
            
            System.out.print("Ciudad (0-999): ");
            int ciudad = Integer.parseInt(scanner.nextLine().trim());
            
            System.out.print("Granja (0-99): ");
            int granja = Integer.parseInt(scanner.nextLine().trim());
            
            System.out.print("Cantidad de huevos: ");
            int unidades = Integer.parseInt(scanner.nextLine().trim());
            
            int total = gestionHuevos.añadirHuevos(tipo, provincia, ciudad, granja, unidades);
            
            System.out.println("\n✅ Puesta registrada exitosamente:");
            System.out.println("Ubicación: Tipo " + tipo + ", Provincia " + provincia + 
                ", Ciudad " + ciudad + ", Granja " + granja);
            System.out.println("Unidades añadidas: " + unidades);
            System.out.println("Total acumulado en esta ubicación: " + total);
            System.out.println("Total general de huevos: " + gestionHuevos.getTotalHuevos());
            
        } catch (NumberFormatException e) {
            System.out.println("Error: Todos los valores deben ser números enteros.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private void consultarPuesta() {
        System.out.println("\n=== CONSULTAR PUESTA ===");
        
        try {
            System.out.print("Tipo de gallina (0-3): ");
            int tipo = Integer.parseInt(scanner.nextLine().trim());
            
            System.out.print("Provincia (0-99): ");
            int provincia = Integer.parseInt(scanner.nextLine().trim());
            
            System.out.print("Ciudad (0-999): ");
            int ciudad = Integer.parseInt(scanner.nextLine().trim());
            
            System.out.print("Granja (0-99): ");
            int granja = Integer.parseInt(scanner.nextLine().trim());
            
            int cantidad = gestionHuevos.obtenerPuesta(tipo, provincia, ciudad, granja);
            
            System.out.println("\n📊 Resultado de la consulta:");
            System.out.println("Ubicación: Tipo " + tipo + ", Provincia " + provincia + 
                ", Ciudad " + ciudad + ", Granja " + granja);
            System.out.println("Huevos registrados: " + cantidad);
            
        } catch (NumberFormatException e) {
            System.out.println("Error: Todos los valores deben ser números enteros.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private void mostrarResumen() {
        System.out.println("\n=== RESUMEN GENERAL ===");
        System.out.println("\n" + empresa.getInfo());
        System.out.println("\n" + gestionCarne.getInfo());
        System.out.println("\n" + gestionHuevos.getInfo());
        
        int stockCarne = gestionCarne.getStockDisponible();
        int totalHuevos = gestionHuevos.getTotalHuevos();
        
        System.out.println("\n📈 ESTADÍSTICAS:");
        System.out.println("• Empleados: " + empresa.getNumEmpleados());
        System.out.println("• Carne disponible: " + stockCarne + " kg");
        System.out.println("• Huevos registrados: " + totalHuevos);
        
        if (stockCarne < 500) {
            System.out.println("⚠️  Alerta: Stock de carne bajo.");
        }
        if (gestionHuevos.estaCaducado()) {
            System.out.println("⚠️  Alerta: Los huevos han caducado.");
        }
    }
    
    public static void main(String[] args) {
        MainAppConsole app = new MainAppConsole();
        app.iniciar();
    }
}