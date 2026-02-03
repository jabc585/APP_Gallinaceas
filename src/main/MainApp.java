package com.gallinaceas;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MainApp extends JFrame {

    private static final Logger logger = LoggerFactory.getLogger(MainApp.class);
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 450;
    private static final String APP_VERSION = "v1.0.1";
    private final Empresa empresa;

    public MainApp() {
        this.empresa = new Empresa();
        initUI();
    }

    private void initUI() {
        setTitle("Gallinaceas S.A. - Gestión de Producción");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal con pestañas
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Empresa", createEmpresaPanel());
        tabbedPane.addTab("Gestión de Carne", createCarnePanel());
        tabbedPane.addTab("Gestión de Huevos", createHuevosPanel());

        add(tabbedPane);
    }

    private JPanel createEmpresaPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextArea infoArea = new JTextArea(empresa.toString());
        infoArea.setEditable(false);
        infoArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        panel.add(new JScrollPane(infoArea), BorderLayout.CENTER);

        JButton btnActualizar = new JButton("Registrar Nuevos Empleados");
        btnActualizar.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, "Número de nuevas altas:");
            if (input != null) {
                try {
                    int nuevos = Integer.parseInt(input.trim());
                    empresa.registrarNuevosEmpleados(nuevos);
                    infoArea.setText(empresa.toString());
                    logger.info("Registrados {} nuevos empleados", nuevos);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Número inválido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(btnActualizar, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createCarnePanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblKilos = new JLabel("Introduzca kilos a añadir:");
        JTextField txtKilos = new JTextField();
        JButton btnCalcular = new JButton("Generar Lote de Carne");
        JTextArea resArea = new JTextArea();
        resArea.setEditable(false);

        btnCalcular.addActionListener(e -> {
            try {
                int kilos = Integer.parseInt(txtKilos.getText().trim());
                Carne carne = new Carne(kilos);
                resArea.setText(carne.toString());
                logger.info("Generado lote de carne: {} kg", kilos);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Introduzca un número válido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(lblKilos);
        panel.add(txtKilos);
        panel.add(btnCalcular);
        panel.add(new JScrollPane(resArea));

        return panel;
    }

    private JPanel createHuevosPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel topPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        topPanel.add(new JLabel("Número de puestos:"));
        JTextField txtPuestos = new JTextField();
        topPanel.add(txtPuestos);
        topPanel.add(new JLabel("Código Marcado:"));
        JTextField txtCodigo = new JTextField();
        topPanel.add(txtCodigo);

        JButton btnGenerar = new JButton("Procesar Huevos");
        JTextArea resArea = new JTextArea();
        resArea.setEditable(false);

        btnGenerar.addActionListener(e -> {
            try {
                int puestos = Integer.parseInt(txtPuestos.getText().trim());
                if (puestos < 1 || puestos > 3) {
                    JOptionPane.showMessageDialog(this, "El número de puestos debe estar entre 1 y 3.", "Validación", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                String codigo = txtCodigo.getText().trim();
                int[] unidades = new int[puestos];
                
                for(int i=0; i<puestos; i++) {
                    String val = JOptionPane.showInputDialog(this, "Unidades puesto " + (i+1));
                    unidades[i] = Integer.parseInt(val.trim());
                }

                Huevos huevos = new Huevos(unidades, codigo);
                resArea.setText(huevos.toString());
                logger.info("Procesados huevos con código: {}", codigo);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error en los datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(resArea), BorderLayout.CENTER);
        panel.add(btnGenerar, BorderLayout.SOUTH);

        return panel;
    }

    public static void main(String[] args) {
        // Configurar el Look and Feel de JTattoo
        try {
            // Diferentes estilos que puedes usar:
            // "com.jtattoo.plaf.acryl.AcrylLookAndFeel"
            // "com.jtattoo.plaf.aero.AeroLookAndFeel"
            // "com.jtattoo.plaf.aluminium.AluminiumLookAndFeel"
            // "com.jtattoo.plaf.bernstein.BernsteinLookAndFeel"
            // "com.jtattoo.plaf.fast.FastLookAndFeel"
            // "com.jtattoo.plaf.graphite.GraphiteLookAndFeel"
            // "com.jtattoo.plaf.hifi.HiFiLookAndFeel"
            // "com.jtattoo.plaf.luna.LunaLookAndFeel"
            // "com.jtattoo.plaf.mcwin.McWinLookAndFeel"
            // "com.jtattoo.plaf.mint.MintLookAndFeel"
            // "com.jtattoo.plaf.noire.NoireLookAndFeel"
            // "com.jtattoo.plaf.smart.SmartLookAndFeel"
            // "com.jtattoo.plaf.texture.TextureLookAndFeel"
            
            Properties props = new Properties();
            props.put("logoString", "Producción Gallinas");
            com.jtattoo.plaf.mcwin.McWinLookAndFeel.setCurrentTheme(props);
            
            UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
            logger.info("Look and Feel McWin cargado correctamente");
        } catch (Exception e) {
            logger.error("No se pudo cargar el Look and Feel de JTattoo", e);
        }

        SwingUtilities.invokeLater(() -> {
            new MainApp().setVisible(true);
        });
    }
}
 