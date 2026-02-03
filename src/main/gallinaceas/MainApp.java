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
    
    // Instancias de las nuevas clases
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
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

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
        infoArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);
        panel.add(new JScrollPane(infoArea), BorderLayout.CENTER);

        JButton btnActualizar = new JButton("Registrar Nuevos Empleados");
        btnActualizar.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, "Número de nuevas altas:");
            if (input != null && !input.trim().isEmpty()) {
                try {
                    int nuevos = Integer.parseInt(input.trim());
                    if (nuevos < 0) throw new IllegalArgumentException("No puede ser negativo");
                    empresa.total_emp(nuevos);
                    infoArea.setText(empresa.toString());
                    logger.info("Registrados {} nuevos empleados", nuevos);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Número inválido", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(btnActualizar, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createCarnePanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel inputPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        inputPanel.add(new JLabel("Introduzca kilos a añadir al stock:"));
        JTextField txtKilos = new JTextField();
        inputPanel.add(txtKilos);

        JTextArea resArea = new JTextArea(gestionCarne.toString());
        resArea.setEditable(false);

        JButton btnCalcular = new JButton("Actualizar Stock de Carne");
        btnCalcular.addActionListener(e -> {
            try {
                String texto = txtKilos.getText().trim();
                if (texto.isEmpty()) return;
                int kilos = Integer.parseInt(texto);
                if (kilos < 0) throw new IllegalArgumentException("Los kilos no pueden ser negativos");
                gestionCarne.stock_carne(kilos);
                resArea.setText(gestionCarne.toString());
                logger.info("Actualizado stock de carne: +{} kg", kilos);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Introduzca un número válido", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(resArea), BorderLayout.CENTER);
        panel.add(btnCalcular, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createHuevosPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel grid = new JPanel(new GridLayout(5, 2, 5, 5));
        grid.add(new JLabel("Tipo Gallina (0-3):"));
        JTextField txtTipo = new JTextField();
        grid.add(txtTipo);
        
        grid.add(new JLabel("Provincia (0-99):"));
        JTextField txtProv = new JTextField();
        grid.add(txtProv);
        
        grid.add(new JLabel("Ciudad (0-999):"));
        JTextField txtCiudad = new JTextField();
        grid.add(txtCiudad);
        
        grid.add(new JLabel("Granja (0-99):"));
        JTextField txtGranja = new JTextField();
        grid.add(txtGranja);
        
        grid.add(new JLabel("Unidades:"));
        JTextField txtUds = new JTextField();
        grid.add(txtUds);

        JTextArea resArea = new JTextArea("Registros de puesta de huevos podrán verse aquí.");
        resArea.setEditable(false);

        JButton btnGenerar = new JButton("Registrar Puesta");
        btnGenerar.addActionListener(e -> {
            try {
                int t = Integer.parseInt(txtTipo.getText().trim());
                int p = Integer.parseInt(txtProv.getText().trim());
                int c = Integer.parseInt(txtCiudad.getText().trim());
                int g = Integer.parseInt(txtGranja.getText().trim());
                int u = Integer.parseInt(txtUds.getText().trim());
                
                if (u < 0) throw new IllegalArgumentException("Unidades no pueden ser negativas");
                
                int totalEnPuesto = gestionHuevos.setHuevos(t, p, c, g, u);
                resArea.setText("Puesta registrada.\nTotal acumulado en este puesto: " + totalEnPuesto + 
                                "\nFecha Envasado: " + gestionHuevos.fecha_envasado + 
                                "\nCaducidad (Día): " + gestionHuevos.dia_caducidad);
                logger.info("Registrada puesta en [{}][{}][{}][{}]: {} uds", t, p, c, g, u);
            } catch (ArrayIndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(this, "Índice fuera de rango: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(grid, BorderLayout.NORTH);
        panel.add(new JScrollPane(resArea), BorderLayout.CENTER);
        panel.add(btnGenerar, BorderLayout.SOUTH);

        return panel;
    }

    public static void main(String[] args) {
        try {
            Properties props = new Properties();
            props.put("logoString", "Gallinas S.A.");
            com.jtattoo.plaf.mcwin.McWinLookAndFeel.setCurrentTheme(props);
            UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
        } catch (Exception e) {
            logger.error("Error cargando LookAndFeel", e);
        }

        SwingUtilities.invokeLater(() -> {
            new MainApp().setVisible(true);
        });
    }
}

