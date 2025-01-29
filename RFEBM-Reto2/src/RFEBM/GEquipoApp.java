package RFEBM;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.log4j.Logger;

import Classes.TemporadaApp;
import log.log;

import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class GEquipoApp extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final String INFO_JUGADOR = "resources/datos/jugadores.csv";
    private static final String IMAGE_DIR = "resources/images/jugadores";
    private JTable jugadoresTable;
    private DefaultTableModel tableModel;
    JComboBox<String> comboTemporada;
    Logger LOG = log.getLogger(GEquipoApp.class);
  
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

    private List<String[]> jugadoresEliminados = new ArrayList<>();
   
    
    
    public GEquipoApp() {
        setTitle("Gestión de Equipos y Jugadores");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(901, 600);

        JPanel panel = new JPanel(new BorderLayout());
        getContentPane().add(panel);

        tableModel = new DefaultTableModel(new String[]{
                "Nombre", "Apellido", "DNI", "Edad", "Posición", "Equipo", "Foto"
        }, 0);
        jugadoresTable = new JTable(tableModel);
        jugadoresTable.getColumnModel().getColumn(6).setCellRenderer(new ImageRenderer());
        jugadoresTable.setRowHeight(40);
        JScrollPane scrollPane = new JScrollPane(jugadoresTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout(0, 0));
        panel.add(buttonPanel, BorderLayout.SOUTH);    
        
        JPanel panel_1 = new JPanel();
        buttonPanel.add(panel_1, BorderLayout.EAST);
        
        JPanel panel_2 = new JPanel();
        buttonPanel.add(panel_2, BorderLayout.CENTER);
        panel_2.setLayout(new BorderLayout(0, 0));
        
        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	            new GestionApp().setVisible(true);
        	            dispose();
        	}
        });
        panel_2.add(btnVolver, BorderLayout.EAST);
        
        JPanel panel_3 = new JPanel();
        panel_2.add(panel_3, BorderLayout.CENTER);
        
        comboTemporada = new JComboBox<>();
        comboTemporada.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		 actualizarRutasFotos();
        		 ordenarPorEquipo();
        	}
        });
        panel_3.add(comboTemporada);
        cargarTemporadasDesdeArchivo();
        cargarPlantilla();
       
        
        JButton freeAgentsButton = new JButton("Agentes Libres");
        panel_3.add(freeAgentsButton);
        
        freeAgentsButton.addActionListener(e -> mostrarVentanaEliminados());
        JButton editButton = new JButton("Editar Jugador");
        panel_3.add(editButton);        JButton deleteButton = new JButton("Eliminar Jugador");
        panel_3.add(deleteButton);
        
        JPanel panel_4 = new JPanel();
        panel_2.add(panel_4, BorderLayout.SOUTH);
        
        JPanel panel_5 = new JPanel();
        panel_2.add(panel_5, BorderLayout.NORTH);
        
                deleteButton.addActionListener(e -> eliminarJugador());
        
                editButton.addActionListener(e -> {
                    int selectedRow = jugadoresTable.getSelectedRow();
                    if (selectedRow >= 0) {
                        abrirFormularioJugador(selectedRow);
                    } else {
                        JOptionPane.showMessageDialog(this, "Por favor, selecciona un jugador para editar.");
                    }
                });
                
        
        cargarJugadores();
        ordenarPorEquipo();
   // Código de inicialización...
        
           // Llamar a cargarPlanificacion para cargar los datos desde los archivos CSV
        // Continuar con el resto del código de inicialización...
        // Llamar a guardarPlanificacion automáticamente al iniciar
        guardarPlantilla();  // Guarda los datos de la planificación al iniciar la aplicación

     

    }
    
    	


private void cargarJugadores() {
    tableModel.setRowCount(0);
    try {
        // Usamos el método leerJugadores para obtener la lista de jugadores
        List<String[]> jugadores = leerJugadores();
        for (String[] datos : jugadores) {
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

                // Añadimos la fila al tableModel
                tableModel.addRow(new Object[]{nombre, apellido, dni, edad, posicion, equipo, foto});
            } else {
                System.err.println("Línea ignorada por formato incorrecto: " + String.join(",", datos));
            }
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar los jugadores: " + e.getMessage());
    }
}

    private void eliminarJugador() {
    	int[] selectedRows = jugadoresTable.getSelectedRows(); // Obtener todas las filas seleccionadas
        if (selectedRows.length > 0) {
            int confirm = JOptionPane.showConfirmDialog(
                this,
                "¿Estás seguro de que deseas eliminar los jugadores seleccionados?",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    List<String[]> jugadores = leerJugadores();

                    // Agregar los jugadores eliminados a la lista de eliminados
                    for (int i = selectedRows.length - 1; i >= 0; i--) {
                        jugadoresEliminados.add(jugadores.get(selectedRows[i]));
                        jugadores.remove(selectedRows[i]); // Eliminar de la lista principal
                    }

                    escribirJugadores(jugadores); // Guardar los cambios
                    cargarJugadores(); // Recargar la tabla principal
                    JOptionPane.showMessageDialog(this, "Jugadores eliminados correctamente.");
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, "Error al eliminar los jugadores: " + e.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona uno o más jugadores para eliminar.");
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
        File file = new File("resources/datos/jugadores.csv"); // Ruta relativa desde el directorio base del proyecto
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                jugadores.add(linea.split(","));
            }
        }
        return jugadores;
    }
    
    
    private void escribirJugadores(List<String[]> jugadores) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(INFO_JUGADOR))) {
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
        dialog.getContentPane().setLayout(new GridLayout(8, 2));

        JTextField nombreField = new JTextField();
        JTextField apellidoField = new JTextField();
        JTextField dniField = new JTextField();
        JTextField fechaNacField = new JTextField();
        JTextField posicionField = new JTextField();
        JComboBox<String> equipoBox = new JComboBox<>(EQUIPOS);
        JTextField fotoField = new JTextField();

        JButton browseButton = new JButton("Examinar...");
        browseButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser(new File(IMAGE_DIR));
            fileChooser.setDialogTitle("Seleccionar Imagen");
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                    "Archivos de Imagen (jpg, png, gif)", "jpg", "png", "gif"));

            int result = fileChooser.showOpenDialog(dialog);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                
              
                String equipo = (String) equipoBox.getSelectedItem(); 
                String imageFileName = selectedFile.getName();
                
                // Construir la ruta relativa de la imagen
                String relativePath = "images/jugadores/" + equipo + "/" + imageFileName;

                // Establecer la ruta relativa en el campo de texto
                fotoField.setText(relativePath);
            }
        });
        dialog.getContentPane().add(new JLabel("Nombre:"));
        dialog.getContentPane().add(nombreField);
        dialog.getContentPane().add(new JLabel("Apellido:"));
        dialog.getContentPane().add(apellidoField);
        dialog.getContentPane().add(new JLabel("DNI:"));
        dialog.getContentPane().add(dniField);
        dialog.getContentPane().add(new JLabel("Edad:"));
        dialog.getContentPane().add(fechaNacField);
        dialog.getContentPane().add(new JLabel("Posición:"));
        dialog.getContentPane().add(posicionField);
        dialog.getContentPane().add(new JLabel("Equipo:"));
        dialog.getContentPane().add(equipoBox);
        dialog.getContentPane().add(new JLabel("Foto:"));
        dialog.getContentPane().add(fotoField);
        dialog.getContentPane().add(browseButton);

        JButton saveButton = new JButton("Guardar");
        dialog.getContentPane().add(saveButton);

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

    class ImageRenderer extends DefaultTableCellRenderer {
        private static final long serialVersionUID = 1L;

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (value instanceof String) {
                String imagePath = (String) value; // Ruta relativa de la imagen, por ejemplo: "images/jugadores/Barcelona/jugador1.png"
                String fullPath = "resources/" + imagePath; // Ruta completa a la imagen en el proyecto

                File imageFile = new File(fullPath);
                if (imageFile.exists()) {
                    ImageIcon imageIcon = new ImageIcon(fullPath);
                    Image image = imageIcon.getImage().getScaledInstance(130, 50, Image.SCALE_SMOOTH);
                    setIcon(new ImageIcon(image));
                } else {
                   
                    setIcon(null);
                }
            }
            return this;
        }
    }
    
 // Método para mostrar la ventana de jugadores eliminados
    private void mostrarVentanaEliminados() {
        JDialog dialog = new JDialog(this, "Agentes Libres", true);
        dialog.setSize(600, 400);
        dialog.getContentPane().setLayout(new BorderLayout());

        // Tabla para mostrar los jugadores eliminados
        DefaultTableModel eliminadosTableModel = new DefaultTableModel(
            new String[]{"Nombre", "Apellido", "DNI", "Edad", "Posición", "Equipo"}, 0);
        JTable eliminadosTable = new JTable(eliminadosTableModel);

        // Llenar la tabla con los jugadores eliminados
        for (String[] jugador : jugadoresEliminados) {
            eliminadosTableModel.addRow(jugador);
        }

        JScrollPane scrollPane = new JScrollPane(eliminadosTable);
        dialog.getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Panel inferior para botones
        JPanel buttonPanel = new JPanel();

        // Botón para eliminar permanentemente
        JButton eliminarPermanentementeButton = new JButton("Eliminar Permanentemente");
        eliminarPermanentementeButton.addActionListener(e -> {
            int[] selectedRows = eliminadosTable.getSelectedRows();
            if (selectedRows.length > 0) {
                int confirm = JOptionPane.showConfirmDialog(
                    dialog,
                    "¿Estás seguro de que deseas eliminar permanentemente los jugadores seleccionados?",
                    "Confirmar Eliminación Permanente",
                    JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    for (int i = selectedRows.length - 1; i >= 0; i--) {
                        jugadoresEliminados.remove(selectedRows[i]); // Eliminar de la lista
                        eliminadosTableModel.removeRow(selectedRows[i]); // Actualizar la tabla
                    }
                    JOptionPane.showMessageDialog(dialog, "Jugadores eliminados permanentemente.");
                }
            } else {
                JOptionPane.showMessageDialog(dialog, "Por favor, selecciona uno o más jugadores para eliminar permanentemente.");
            }
        });
        
       
    
        // Botón para añadir jugadores de vuelta al panel principal
        JButton añadirJugadorButton = new JButton("Añadir Jugador");
        añadirJugadorButton.addActionListener(e -> {
            int[] selectedRows = eliminadosTable.getSelectedRows();
            if (selectedRows.length > 0) {
                for (int i = selectedRows.length - 1; i >= 0; i--) {
                    String[] jugador = jugadoresEliminados.get(selectedRows[i]);
                    jugadoresEliminados.remove(selectedRows[i]); // Eliminar de la lista de eliminados
                    eliminadosTableModel.removeRow(selectedRows[i]); // Actualizar la tabla de eliminados

                    // Añadir al modelo principal
                    tableModel.addRow(jugador);

                    // Añadir de vuelta al archivo
                    try {
                        List<String[]> jugadores = leerJugadores();
                        jugadores.add(jugador);
                        escribirJugadores(jugadores);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(this, "Error al añadir el jugador: " + ex.getMessage());
                    }
                }
                JOptionPane.showMessageDialog(dialog, "Jugadores añadidos correctamente.");
            } else {
                JOptionPane.showMessageDialog(dialog, "Por favor, selecciona uno o más jugadores para añadir.");
            }
        });
        
     // Añadir botones al panel
        buttonPanel.add(añadirJugadorButton);
        buttonPanel.add(eliminarPermanentementeButton);
        dialog.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    
        
    }
    private void cargarTemporadasDesdeArchivo() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("resources/datos/temporadas.ser"))) {

            comboTemporada.removeAllItems();
            
            TemporadaApp temporada;
            
            while ((temporada = (TemporadaApp) ois.readObject()) != null) {
                // Agregamos solo el nombre al ComboBox
                comboTemporada.addItem(temporada.getNombre());
            }
        } catch (EOFException e) {
            return;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void ordenarPorEquipo() {
        List<String[]> jugadores = new ArrayList<>();

        // Obtener todos los datos de la tabla actual
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String[] jugador = new String[tableModel.getColumnCount()];
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                jugador[j] = (String) tableModel.getValueAt(i, j);
            }
            jugadores.add(jugador);
        }

        // Ordenar la lista de jugadores por la columna "Equipo" (índice 5)
        jugadores.sort((j1, j2) -> j1[5].compareToIgnoreCase(j2[5]));

        // Vaciar el modelo de la tabla y volver a llenarlo con los datos ordenados
        tableModel.setRowCount(0);
        for (String[] jugador : jugadores) {
            tableModel.addRow(jugador);
        }
    }
    private String generarRutaFoto(String nombre, String apellido, String equipo) {
        // Obtener el número de temporada seleccionado
        int temporadaSeleccionada = comboTemporada.getSelectedIndex() + 1;
        
        // Formatear la ruta según el estándar requerido
        return "images/jugadores/" + equipo + "/" 
                + nombre + apellido + "." + temporadaSeleccionada + ".png";
    }
    private void actualizarRutasFotos() {
        try {
            List<String[]> jugadores = leerJugadores(); // Cargar jugadores desde el archivo
            
            for (String[] jugador : jugadores) {
                if (jugador.length >= 6) {
                    String nombre = jugador[0].trim();
                    String apellido = jugador[1].trim();
                    String equipo = jugador[5].trim();
                    
                    // Generar la ruta de la foto según el formato
                    String fotoActualizada = generarRutaFoto(nombre, apellido, equipo);
                    
                    // Actualizar la ruta de la foto en los datos del jugador
                    if (jugador.length == 6) { 
                        // Si originalmente no tiene el campo de foto, añadirlo
                        jugador = Arrays.copyOf(jugador, 7);
                    }
                    jugador[6] = fotoActualizada;
                }
            }

            // Guardar las modificaciones en el archivo
            escribirJugadores(jugadores);
            cargarJugadores(); // Recargar la tabla para reflejar los cambios

            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar las rutas de las fotos: " + e.getMessage());
        }
    }

    
    private void guardarPlantilla() {
        // Ruta donde se guardará el archivo CSV
        String temporada = comboTemporada.getSelectedItem().toString();
        String rutaArchivo = "resources/datos/Plantilla" + temporada + ".csv";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            // Escribir los encabezados de la tabla (nombres de las columnas)
            bw.write("Nombre,Apellido,DNI,Edad,Posición,Equipo,Foto");
            bw.newLine();  // Nueva línea después de los encabezados

            // Obtener el modelo de la tabla (para acceder a los datos)
            TableModel modelo = jugadoresTable.getModel();
            int rowCount = modelo.getRowCount();
            int columnCount = modelo.getColumnCount();

            // Recorrer las filas y columnas de la tabla para obtener los datos
            for (int i = 0; i < rowCount; i++) {
                StringBuilder row = new StringBuilder();
                for (int j = 0; j < columnCount; j++) {
                    Object value = modelo.getValueAt(i, j);
                    // Si el valor es null, reemplazarlo por una cadena vacía
                    if (value == null) {
                        value = "";
                    }
                    // Agregar el valor de la celda a la fila (separado por coma)
                    row.append(value.toString());

                    // Si no es la última columna, agregar una coma
                    if (j < columnCount - 1) {
                        row.append(",");
                    }
                }
                // Escribir la fila completa en el archivo
                bw.write(row.toString());
                bw.newLine();  // Nueva línea después de cada fila
            }

            System.out.println("Plantilla guardada correctamente en " + rutaArchivo);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar la plantilla: " + e.getMessage());
        }
    }

    private void cargarPlantilla() {
        try {
            int numTemporadas = comboTemporada.getItemCount();
            for (int i = 0; i < numTemporadas; i++) {
                String temporadaActual = comboTemporada.getItemAt(i).toString();
                comboTemporada.setSelectedIndex(i);

                String rutaArchivo = "resources/datos/Plantilla" + temporadaActual + ".csv";
                cargarDatosDesdeArchivo(rutaArchivo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar la plantilla: " + e.getMessage());
        }
    }

    private void cargarDatosDesdeArchivo(String rutaArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Omite la primera línea, ya que son los encabezados
                if (linea.startsWith("Nombre")) continue;

                // Procesar cada línea de datos y agregarla a la tabla
                String[] datos = linea.split(",");
                if (datos.length == 7) { // Verifica que la línea tenga 7 columnas
                    String nombre = datos[0].trim();
                    String apellido = datos[1].trim();
                    String dni = datos[2].trim();
                    String edad = datos[3].trim();
                    String posicion = datos[4].trim();
                    String equipo = datos[5].trim();
                    String foto = datos[6].trim();

                    // Añadir los datos a la tabla
                    tableModel.addRow(new Object[]{nombre, apellido, dni, edad, posicion, equipo, foto});
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al leer el archivo: " + e.getMessage());
        }
    }

 
    
}
