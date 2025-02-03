package RFEBM;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class equipoApp extends JFrame {

    private static final long serialVersionUID = -4093081654081064634L;
    final static String EQUIPOACTUAL = "Temporada2024-2025";
    static int equipoSeleccionado = 1;
    private JPanel contentPane;
    private JPanel panel;
    private JPanel panel_1;
    private JButton btnLogout;
    private JLabel lblNewLabel_2;
    private JScrollPane scrollPane;
    private static JTable table;

    private Map<String, List<String>> equiposData = new HashMap<>();

    // Método principal para lanzar la aplicación
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    equipoApp frame = new equipoApp();
                    frame.setVisible(true);
                    frame.setResizable(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Método para actualizar la tabla con los jugadores de un equipo
    private void actualizarTabla(String equipoSeleccionado) {
        // Obtén los nombres de los jugadores del equipo seleccionado
        List<String> jugadores = equiposData.getOrDefault(equipoSeleccionado, new ArrayList<>());

        // Actualiza la tabla con los nombres de los jugadores
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Limpia los datos actuales de la tabla

        // Agregar los jugadores al modelo de la tabla
        for (String jugador : jugadores) {
            model.addRow(new Object[]{null, jugador}); // Columna de imagen como null
        }
    }

    // Método para cargar los datos desde el archivo CSV
    private void cargarDatosDesdeCSV(String rutaArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Divide cada línea por las comas
                String[] datos = linea.split(",");

                // Obtén los datos relevantes
                String nombre = datos[0].trim() + " " + datos[1].trim(); // Nombre completo
                String equipo = datos[5].trim(); // Equipo

                // Agrega el jugador al equipo correspondiente
                equiposData.putIfAbsent(equipo, new ArrayList<>()); // Crea una lista para el equipo si no existe
                equiposData.get(equipo).add(nombre); // Agrega el nombre del jugador a la lista del equipo
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar el archivo CSV: " + e.getMessage());
        }
    }

    // Constructor de la clase equipoApp
    public equipoApp() throws IOException {
        setTitle("Equipo - " + EQUIPOACTUAL);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        // Panel superior
        panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);
        panel.setLayout(new BorderLayout(0, 0));

        // ComboBox de equipos
        JPanel panel_18 = new JPanel();
        panel.add(panel_18, BorderLayout.CENTER);
        panel_18.setLayout(new BorderLayout(0, 0));

        JPanel panel_5 = new JPanel();
        panel_18.add(panel_5, BorderLayout.CENTER);
        panel_5.setLayout(new BorderLayout(0, 0));

        JPanel panel_7 = new JPanel();
        panel_5.add(panel_7, BorderLayout.SOUTH);
        panel_7.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JPanel panel_2 = new JPanel();
        panel_7.add(panel_2);
        JComboBox<String> EquipoDesplegable_1 = new JComboBox<>();
        panel_2.add(EquipoDesplegable_1);

        EquipoDesplegable_1.addItem("Barcelona");
        EquipoDesplegable_1.addItem("Madrid");
        EquipoDesplegable_1.addItem("Cáceres");
        EquipoDesplegable_1.addItem("Bilbao");
        EquipoDesplegable_1.addItem("Sevilla");
        EquipoDesplegable_1.addItem("Murcia");

        // Detectar cambios en la selección del ComboBox
        EquipoDesplegable_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String equipoSeleccionado = (String) EquipoDesplegable_1.getSelectedItem();
                actualizarTabla(equipoSeleccionado);
            }
        });

        JPanel panel_3 = new JPanel();
        panel_7.add(panel_3);

        JComboBox<String> TemporadaDesplegable = new JComboBox<>();
        panel_3.add(TemporadaDesplegable);

        // Agregar las temporadas al ComboBox
        TemporadaDesplegable.addItem("2023-2024");
        TemporadaDesplegable.addItem("2024-2025");
        TemporadaDesplegable.addItem("2025-2026");
        
        TemporadaDesplegable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String temporadaActual = (String) TemporadaDesplegable.getSelectedItem();
                setTitle("Equipo - " + temporadaActual);
                String archivoCSV = "JornadaTemporada " + temporadaActual + ".csv";
                cargarDatosDesdeCSV(archivoCSV);
            }
        });


        JPanel panel_6 = new JPanel();
        panel_18.add(panel_6, BorderLayout.EAST);

        JLabel txtClasif = new JLabel("PLANTILLAS");
        panel_6.add(txtClasif);
        txtClasif.setHorizontalAlignment(SwingConstants.CENTER);
        txtClasif.setFont(new Font("Impact", Font.PLAIN, 42));

        // Tabla
        String[] columnNames = {"Imagen", "Nombre"};
        DefaultTableModel tableModel = new DefaultTableModel(null, columnNames);
        table = new JTable(tableModel);
        table.setRowHeight(30);
        table.setFont(new Font("Calibri", Font.PLAIN, 14));
        scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Panel inferior (panel1)
        panel_1 = new JPanel();
        contentPane.add(panel_1, BorderLayout.SOUTH);
        panel_1.setLayout(new BorderLayout(0, 0));

        btnLogout = new JButton("Cerrar Sesión");
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int respuesta = JOptionPane.showConfirmDialog(
                    null, 
                    "¿Está seguro que desea cerrar sesión?",
                    "Cerrar sesión",
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.INFORMATION_MESSAGE
                );
                if (respuesta == JOptionPane.YES_OPTION) {
                    new inicioApp().setVisible(true);
                    dispose();
                }
            }
        });
        panel_1.add(btnLogout, BorderLayout.EAST);
        
        JPanel panel_4 = new JPanel();
        panel_1.add(panel_4, BorderLayout.CENTER);
        panel_4.setLayout(new BorderLayout(0, 0));
        
        JButton btnVolver = new JButton("Volver");
        panel_4.add(btnVolver, BorderLayout.EAST);
        btnVolver.addActionListener(new ActionListener() {
		
			
			public void actionPerformed(ActionEvent e) {
				new menuApp().setVisible(true);
				dispose();
				}
		});

        // Cargar datos desde el archivo CSV
        cargarDatosDesdeCSV("jugadores.csv");

        // Mostrar jugadores del equipo inicial (Barcelona por defecto)
        actualizarTabla("Barcelona");
    }

    // Método para verificar si una cadena es numérica
    public static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) { return false; }
        try { Double.parseDouble(str); return true; }
        catch (NumberFormatException e) { return false; }
    }

    final static String Temporada = "datos.csv";
    private JPanel panel_16;
    private JPanel panel_17;
    private JPanel panel_18;
    private JComboBox EquipoDesplegable;
    private JPanel panel_5;
    private JLabel txtClasif;
    private JPanel panel_6;
    private JPanel panel_7;
}