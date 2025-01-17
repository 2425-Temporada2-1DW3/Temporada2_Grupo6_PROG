package RFEBM;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GEquipoApp extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final String InfoJugador = "jugadores.csv";
    private JTable jugadoresTable;
    private DefaultTableModel tableModel;

    private static final String[] EQUIPOS = {"Barcelona", "Cáceres", "Madrid", "Sevilla", "Murcia", "Bilbao"};

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                GEquipoApp frame = new GEquipoApp();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public GEquipoApp() {
        setTitle("Gestión de Equipos y Jugadores");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        JPanel panel = new JPanel(new BorderLayout());
        getContentPane().add(panel);

        tableModel = new DefaultTableModel(new String[]{
                "Nombre", "Apellido", "DNI", "Edad", "Posición", "Equipo", "Foto"
        }, 0);
        jugadoresTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(jugadoresTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Añadir Jugador");
        JButton editButton = new JButton("Editar Jugador");
        JButton deleteButton = new JButton("Eliminar Jugador");
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        cargarJugadores();

        addButton.addActionListener(e -> abrirFormularioJugador(null));

        editButton.addActionListener(e -> {
            int selectedRow = jugadoresTable.getSelectedRow();
            if (selectedRow >= 0) {
                abrirFormularioJugador(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, selecciona un jugador para editar.");
            }
        });

        deleteButton.addActionListener(e -> eliminarJugador());
    }

    private void cargarJugadores() {
        tableModel.setRowCount(0);
        try (BufferedReader br = new BufferedReader(new FileReader(InfoJugador))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 6) {
                    String nombre = datos[0].trim();
                    String apellido = datos[1].trim();
                    String dni = datos[2].trim();
                    String edadOFecha = datos[3].trim();
                    String posicion = datos[4].trim();
                    String equipo = datos[5].trim();
                    String foto = datos.length >= 7 ? datos[6].trim() : "";

                    String edad;
                    if (esNumero(edadOFecha)) {
                        edad = edadOFecha;
                    } else {
                        edad = calcularEdad(edadOFecha);
                    }

                    tableModel.addRow(new Object[]{nombre, apellido, dni, edad, posicion, equipo, foto});
                } else {
                    System.err.println("Línea ignorada por formato incorrecto: " + linea);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los jugadores: " + e.getMessage());
        }
    }

    private void eliminarJugador() {
        int selectedRow = jugadoresTable.getSelectedRow();
        if (selectedRow >= 0) {
            int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas eliminar este jugador?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    List<String[]> jugadores = leerJugadores();
                    jugadores.remove(selectedRow);
                    escribirJugadores(jugadores);

                    cargarJugadores();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, "Error al eliminar el jugador: " + e.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un jugador para eliminar.");
        }
    }

    private boolean esNumero(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private List<String[]> leerJugadores() throws IOException {
        List<String[]> jugadores = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(InfoJugador))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                jugadores.add(linea.split(","));
            }
        }
        return jugadores;
    }

    private void escribirJugadores(List<String[]> jugadores) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(InfoJugador))) {
            for (String[] jugador : jugadores) {
                bw.write(String.join(",", jugador));
                bw.newLine();
            }
        }
    }

    private String calcularEdad(String fechaNacimiento) {
        try {
            LocalDate fechaNac = LocalDate.parse(fechaNacimiento, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return String.valueOf(Period.between(fechaNac, LocalDate.now()).getYears());
        } catch (Exception e) {
            return "Desconocida";
        }
    }

    private void abrirFormularioJugador(Integer rowIndex) {
        JDialog dialog = new JDialog(this, "Formulario de Jugador", true);
        dialog.setSize(400, 400);
        dialog.setLayout(new GridLayout(8, 2));

        JTextField nombreField = new JTextField();
        JTextField apellidoField = new JTextField();
        JTextField dniField = new JTextField();
        JTextField fechaNacField = new JTextField();
        JTextField posicionField = new JTextField();
        JComboBox<String> equipoBox = new JComboBox<>(EQUIPOS);
        JTextField fotoField = new JTextField();

        dialog.add(new JLabel("Nombre:"));
        dialog.add(nombreField);
        dialog.add(new JLabel("Apellido:"));
        dialog.add(apellidoField);
        dialog.add(new JLabel("DNI:"));
        dialog.add(dniField);
        dialog.add(new JLabel("Edad:"));
        dialog.add(fechaNacField);
        dialog.add(new JLabel("Posición:"));
        dialog.add(posicionField);
        dialog.add(new JLabel("Equipo:"));
        dialog.add(equipoBox);
        dialog.add(new JLabel("Foto:"));
        dialog.add(fotoField);

        JButton saveButton = new JButton("Guardar");
        dialog.add(saveButton);

        if (rowIndex != null) {
            for (int i = 0; i < jugadoresTable.getColumnCount(); i++) {
                String value = (String) jugadoresTable.getValueAt(rowIndex, i);
                switch (i) {
                    case 0 -> nombreField.setText(value);
                    case 1 -> apellidoField.setText(value);
                    case 2 -> dniField.setText(value);
                    case 3 -> fechaNacField.setText("");
                    case 4 -> posicionField.setText(value);
                    case 5 -> equipoBox.setSelectedItem(value);
                    case 6 -> fotoField.setText(value);
                }
            }
        }

        saveButton.addActionListener(e -> {
            try {
                String[] nuevoJugador = {
                        nombreField.getText(),
                        apellidoField.getText(),
                        dniField.getText(),
                        fechaNacField.getText(),
                        posicionField.getText(),
                        (String) equipoBox.getSelectedItem(),
                        fotoField.getText()
                };

                List<String[]> jugadores = leerJugadores();
                if (rowIndex == null) {
                    jugadores.add(nuevoJugador);
                } else {
                    jugadores.set(rowIndex, nuevoJugador);
                }
                escribirJugadores(jugadores);

                cargarJugadores();
                dialog.dispose();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar los datos del jugador: " + ex.getMessage());
            }
        });

        dialog.setVisible(true);
    }
}
