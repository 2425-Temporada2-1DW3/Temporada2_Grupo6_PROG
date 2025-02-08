package RFEBM;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Classes.TemporadaApp;

public class equipoApp extends JFrame {

    private static final long serialVersionUID = -4093081654081064634L;
    private JPanel contentPane;
    private JPanel panel;
    private JPanel panel_1;
    private JButton btnLogout;
    private JScrollPane scrollPane;
    private static JTable table;
    private JComboBox<String> temporadaComboBox;
    private JComboBox<String> equipoComboBox;

    private Map<String, List<List<String>>> equiposData = new HashMap<>();

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
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Limpiar la tabla antes de actualizar

        List<List<String>> jugadores = equiposData.getOrDefault(equipoSeleccionado, new ArrayList<>());
        for (List<String> jugador : jugadores) {
            model.addRow(new Object[]{jugador.get(1), jugador.get(0)}); // Foto en la columna 0 y nombre en la columna 1
        }
    }

    // Método para cargar los datos desde el archivo CSV
    private void cargarDatosDesdeCSV(String rutaArchivo) {
        equiposData.clear(); // Limpiar los datos antes de cargar nuevos
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length < 7) continue; // Evitar errores si hay líneas incompletas

                String nombre = datos[0].trim() + " " + datos[1].trim(); // Nombre del jugador
                String equipo = datos[5].trim(); // Equipo del jugador
                String foto = datos[6].trim(); // Ruta de la foto del jugador

                // Añadir el nombre y la foto directamente a la lista
                List<String> jugador = new ArrayList<>();
                jugador.add(nombre); // Primer elemento: nombre
                jugador.add(foto);   // Segundo elemento: ruta de la foto

                // Añadir al mapa de equiposData
                equiposData.putIfAbsent(equipo, new ArrayList<>());
                equiposData.get(equipo).add(jugador); // Añadir el jugador con su foto
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar el archivo CSV: " + e.getMessage());
        }
    }

    // Método para cargar temporadas desde el archivo "temporadas.ser"
    private void cargarTemporadasDesdeArchivo() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("resources/datos/temporadas.ser"))) {
            @SuppressWarnings("unchecked")
            List<TemporadaApp> temporadas = (List<TemporadaApp>) ois.readObject();

            // Limpiar los elementos previos del combo
            temporadaComboBox.removeAllItems();

            // Agregar solo el nombre de cada temporada al ComboBox
            for (TemporadaApp temporada : temporadas) {
                temporadaComboBox.addItem(temporada.getNombre());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Constructor de la clase equipoApp
    public equipoApp() throws IOException {
        setTitle("Equipo - " + "Temporada Actual");
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

        // Panel ComboBox de equipos y temporadas
        JPanel panel_18 = new JPanel();
        panel.add(panel_18, BorderLayout.CENTER);
        panel_18.setLayout(new BorderLayout(0, 0));

        JPanel panel_5 = new JPanel();
        panel_18.add(panel_5, BorderLayout.CENTER);
        panel_5.setLayout(new BorderLayout(0, 0));

        JPanel panel_7 = new JPanel();
        panel_5.add(panel_7, BorderLayout.SOUTH);
        panel_7.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JPanel panel_3 = new JPanel();
        panel_7.add(panel_3);

        JPanel panel_2 = new JPanel();
        panel_7.add(panel_2);
        
        // ComboBox para seleccionar el equipo
        equipoComboBox = new JComboBox<>();
        panel_2.add(equipoComboBox);

        // Agregar equipos al ComboBox
        equipoComboBox.addItem("Barcelona");
        equipoComboBox.addItem("Madrid");
        equipoComboBox.addItem("Cáceres");
        equipoComboBox.addItem("Bilbao");
        equipoComboBox.addItem("Sevilla");
        equipoComboBox.addItem("Murcia");

        // Detectar cambios en la selección del ComboBox de equipos
        equipoComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String equipoSeleccionado = (String) equipoComboBox.getSelectedItem();
                actualizarTabla(equipoSeleccionado);
            }
        });

        // ComboBox para seleccionar la temporada
        temporadaComboBox = new JComboBox<>();
        panel_3.add(temporadaComboBox);
        

        // Cargar temporadas desde el archivo "temporadas.ser"
        cargarTemporadasDesdeArchivo();

        // Detectar cambios en la selección del ComboBox de temporadas
        temporadaComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String temporadaSeleccionada = (String) temporadaComboBox.getSelectedItem();
                setTitle("Equipo - " + temporadaSeleccionada);

                // Cargar los jugadores de la temporada seleccionada
                String rutaArchivo = "resources/datos/Plantilla" + temporadaSeleccionada + ".csv";
                cargarDatosDesdeCSV(rutaArchivo);

                String equipoSeleccionado = (String) equipoComboBox.getSelectedItem();
                       actualizarTabla(equipoSeleccionado);
             
            }
        });

        // Tabla
        String[] columnNames = {"Imagen", "Nombre"};
        DefaultTableModel tableModel = new DefaultTableModel(null, columnNames);
        table = new JTable(tableModel);
        table.setRowHeight(80); // Altura de la fila para las imágenes
        table.setFont(new Font("Calibri", Font.PLAIN, 14));
        table.getColumnModel().getColumn(0).setCellRenderer(new ImageRenderer());
        scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Panel inferior (panel1)
        panel_1 = new JPanel();
        contentPane.add(panel_1, BorderLayout.SOUTH);
        panel_1.setLayout(new BorderLayout(0, 0));

        // Botón de cerrar sesión
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

        // Cargar datos desde el archivo CSV
        cargarDatosDesdeCSV("resources/datos/PlantillaTemporada 2023-2024.csv");

        // Mostrar jugadores del equipo inicial (Barcelona por defecto)
        actualizarTabla("Barcelona");
    }

    // Clase que se usa para renderizar las imágenes en la tabla
    class ImageRenderer extends DefaultTableCellRenderer {
        private static final long serialVersionUID = 1L;

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (column == 0) { // Columna 0: Cargar la imagen
                JLabel label = new JLabel();
                label.setHorizontalAlignment(SwingConstants.CENTER);

                if (value instanceof String) {
                    String imagePath = (String) value; // Ruta de la imagen
                    String fullPath = "resources/" + imagePath; // Ruta completa de la imagen

                    File imageFile = new File(fullPath);
                    if (imageFile.exists()) {
                        ImageIcon imageIcon = new ImageIcon(fullPath);
                        Image image = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Escalar la imagen
                        label.setIcon(new ImageIcon(image));
                    } else {
                        label.setIcon(null); // Si no existe la imagen, dejar vacío
                    }
                }
                return label;
            } else {
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        }
    }
}
