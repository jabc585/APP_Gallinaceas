package com.gallinaceas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class MainApp extends JFrame {
    
    private final Gallinaceas empresa;
    private final Carne gestionCarne;
    private final Huevos gestionHuevos;
    
    public MainApp() {
        this.empresa = new Gallinaceas();
        this.gestionCarne = new Carne();
        this.gestionHuevos = new Huevos();
        
        initUI();
    }

    private void initUI() {
        setTitle("Gallinaceas S.A. - Gestión de Producción");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Usar look and feel del sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("No se pudo cargar el look and feel del sistema: " + e.getMessage());
        }

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("🏢 Empresa", crearPanelEmpresa());
        tabbedPane.addTab("🥩 Carne", crearPanelCarne());
        tabbedPane.addTab("🥚 Huevos", crearPanelHuevos());

        add(tabbedPane);
    }

    private JPanel crearPanelEmpresa() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextArea infoArea = new JTextArea(empresa.toString());
        infoArea.setEditable(false);
        infoArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);
        panel.add(new JScrollPane(infoArea), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Nuevos empleados:"));
        JTextField txtEmpleados = new JTextField(10);
        inputPanel.add(txtEmpleados);
        
        JButton btnActualizar = new JButton("Registrar");
        btnActualizar.addActionListener(e -> {
            String input = txtEmpleados.getText().trim();
            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el número de empleados", 
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            try {
                int nuevos = Integer.parseInt(input);
                empresa.total_emp(nuevos);
                infoArea.setText(empresa.toString());
                txtEmpleados.setText("");
                JOptionPane.showMessageDialog(this, 
                    "Empleados registrados correctamente", 
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Número inválido", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        inputPanel.add(btnActualizar);

        panel.add(inputPanel, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel crearPanelCarne() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextArea infoArea = new JTextArea(gestionCarne.toString());
        infoArea.setEditable(false);
        infoArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        panel.add(new JScrollPane(infoArea), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        
        // Añadir stock
        inputPanel.add(new JLabel("Kilos a añadir:"));
        JTextField txtAnadir = new JTextField();
        inputPanel.add(txtAnadir);
        
        JButton btnAnadir = new JButton("Añadir Stock");
        btnAnadir.addActionListener(e -> {
            try {
                String texto = txtAnadir.getText().trim();
                if (texto.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Ingrese los kilos", 
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                int kilos = Integer.parseInt(texto);
                gestionCarne.stock_carne(kilos);
                infoArea.setText(gestionCarne.toString());
                txtAnadir.setText("");
                
                JOptionPane.showMessageDialog(this, 
                    String.format("Se añadieron %d kg\nStock actual: %d kg", 
                        kilos, gestionCarne.getStock()),
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        // Retirar stock
        inputPanel.add(new JLabel("Kilos a retirar:"));
        JTextField txtRetirar = new JTextField();
        inputPanel.add(txtRetirar);
        
        JButton btnRetirar = new JButton("Retirar Stock");
        btnRetirar.addActionListener(e -> {
            try {
                String texto = txtRetirar.getText().trim();
                if (texto.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Ingrese los kilos", 
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                int kilos = Integer.parseInt(texto);
                boolean exito = gestionCarne.retirarStock(kilos);
                
                if (exito) {
                    infoArea.setText(gestionCarne.toString());
                    txtRetirar.setText("");
                    JOptionPane.showMessageDialog(this, 
                        String.format("Se retiraron %d kg\nStock restante: %d kg", 
                            kilos, gestionCarne.getStock()),
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, 
                        "No hay suficiente stock", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
                    
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        buttonPanel.add(btnAnadir);
        buttonPanel.add(btnRetirar);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }

    private JPanel crearPanelHuevos() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextArea infoArea = new JTextArea(gestionHuevos.toString() + "\n\nComplete los datos para registrar una puesta:");
        infoArea.setEditable(false);
        infoArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        panel.add(new JScrollPane(infoArea), BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        
        formPanel.add(new JLabel("Tipo Gallina (0-3):"));
        JTextField txtTipo = new JTextField();
        formPanel.add(txtTipo);
        
        formPanel.add(new JLabel("Provincia (0-99):"));
        JTextField txtProv = new JTextField();
        formPanel.add(txtProv);
        
        formPanel.add(new JLabel("Ciudad (0-999):"));
        JTextField txtCiudad = new JTextField();
        formPanel.add(txtCiudad);
        
        formPanel.add(new JLabel("Granja (0-99):"));
        JTextField txtGranja = new JTextField();
        formPanel.add(txtGranja);
        
        formPanel.add(new JLabel("Unidades:"));
        JTextField txtUnidades = new JTextField();
        formPanel.add(txtUnidades);

        JButton btnRegistrar = new JButton("Registrar Puesta");
        btnRegistrar.addActionListener(e -> {
            try {
                if (txtTipo.getText().trim().isEmpty() || txtProv.getText().trim().isEmpty() || 
                    txtCiudad.getText().trim().isEmpty() || txtGranja.getText().trim().isEmpty() || 
                    txtUnidades.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", 
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                int t = Integer.parseInt(txtTipo.getText().trim());
                int p = Integer.parseInt(txtProv.getText().trim());
                int c = Integer.parseInt(txtCiudad.getText().trim());
                int g = Integer.parseInt(txtGranja.getText().trim());
                int u = Integer.parseInt(txtUnidades.getText().trim());
                
                int total = gestionHuevos.setHuevos(t, p, c, g, u);
                
                String resultado = String.format(
                    " Puesta registrada\n" +
                    " Ubicación: [Tipo:%d][Prov:%d][Ciudad:%d][Granja:%d]\n" +
                    " Unidades: %d\n" +
                    " Total acumulado: %d\n" +
                    " Envasado: %s\n" +
                    " Caduca: %s\n" +
                    " Días restantes: %d",
                    t, p, c, g, u, total,
                    gestionHuevos.getFechaEnvasadoFormateada(),
                    gestionHuevos.getFechaCaducidadFormateada(),
                    gestionHuevos.diasHastaCaducidad()
                );
                
                infoArea.setText(resultado);
                
                // Limpiar campos
                txtTipo.setText("");
                txtProv.setText("");
                txtCiudad.setText("");
                txtGranja.setText("");
                txtUnidades.setText("");
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(btnRegistrar, BorderLayout.SOUTH);
        
        return panel;
    }

    public static void main(String[] args) {
        // Configurar para evitar problemas con X11
        System.setProperty("java.awt.headless", "false");
        
        // Intentar ejecutar la GUI
        try {
            SwingUtilities.invokeLater(() -> {
                MainApp app = new MainApp();
                app.setVisible(true);
            });
        } catch (Exception e) {
            System.err.println("========================================");
            System.err.println("ERROR: No se pudo iniciar la interfaz gráfica");
            System.err.println("Causa: " + e.getMessage());
            System.err.println("========================================");
            System.err.println("\nSOLUCIONES PARA LINUX:");
            System.err.println("1. Verifica que tienes entorno gráfico:");
            System.err.println("   echo $DISPLAY  (debe mostrar :0 o similar)");
            System.err.println("2. Si no hay display, configura:");
            System.err.println("   export DISPLAY=:0");
            System.err.println("3. Dale permisos al X server:");
            System.err.println("   xhost +local:");
            System.err.println("4. Luego ejecuta:");
            System.err.println("   DISPLAY=:0 mvn exec:java");
            System.err.println("\nSi prefieres usar consola:");
            System.err.println("   mvn compile && java -cp target/classes com.gallinaceas.MainAppConsole");
        }
    }
}