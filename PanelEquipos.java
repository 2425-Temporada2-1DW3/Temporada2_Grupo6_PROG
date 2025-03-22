package com.structure;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;

import com.logic.Equipo;
import com.logic.Fecha;
import com.logic.Jugador;
import com.logic.Temporada;

import net.miginfocom.swing.MigLayout;

public class PanelEquipos extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private int userType;
	private Color colorbg;
	private Color colortxt;
	private String userName;
	private main parentFrame;
	JPanel PanelContenedor;
	JPanel PanelDatosJugador;
	JPanel Cabecera;
	JLabel lblTituloDatosJugador;
	JPanel panel;
	JButton btnModificarEquipo;
	JPanel PanelContenidoJugador;
	JPanel PanelListEquipos;
	JPanel Cabecera2;
	JLabel lblTituloTablaJugadores;
	JPanel panel_1;
	JButton btnGuardarCambios;
	JTextField txtNumID;
	JLabel lblNroID;
	private JLabel lblNombreEquipo;
	private JTextField txtNombre;
	private JTextField txtEntrenador;
	private JLabel lblEntrenador;
	private JLabel lblFechaFundacion;
	private JTextField txtFechaFundacion;
	private JPanel panel_2;
	private JPanel panel_3;
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel panel_4;
	private JComboBox<Temporada> combxFiltrarTempo;
//	private JComboBox<String> combFiltrarJugador;
	JLabel labelImagen;
	ImageIcon icon;

    private ArrayList<Temporada> listTemporadas; // Lista de temporadas
    private ArrayList<Equipo> listEquipos; // Lista de equipos para combobox 
    private JButton btnCambiarFoto;
    private JLabel lblTituloTempo;
    
    
	/**
	 * Create the panel.
	 */


	public PanelEquipos(main parentFrame) {
		// guarda los valores fuera de esta funcion por si se necesitan acceder en otro sitio
		this.parentFrame = parentFrame;
	    userType = parentFrame.userType;
	    colorbg = parentFrame.colorbg;
	    colortxt = parentFrame.colortxt;
	    userName = parentFrame.userName;
	    this.listTemporadas = new ArrayList<>();
	    this.listEquipos = new ArrayList<>();
	    cargarTemporadasDesdeArchivo();
	    
		// Cambia color del Jpanel
		setBackground(colorbg);
		setLayout(new BorderLayout(0, 0));
		
		PanelContenedor = new JPanel();
		add(PanelContenedor);
		PanelContenedor.setLayout(new BoxLayout(PanelContenedor, BoxLayout.X_AXIS));
		
		PanelDatosJugador = new JPanel();
		PanelDatosJugador.setPreferredSize(new Dimension(200, 600)); // Tamaño deseado
		PanelContenedor.add(PanelDatosJugador);
		PanelDatosJugador.setLayout(new BorderLayout(0, 0));
		
		Cabecera = new JPanel();
		PanelDatosJugador.add(Cabecera, BorderLayout.NORTH);
		
		lblTituloDatosJugador = new JLabel("INFORMACIÓN DEL EQUIPO");
		Cabecera.add(lblTituloDatosJugador);
		
		panel = new JPanel();
		PanelDatosJugador.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// Botón "Modificar Jugador"
		btnModificarEquipo = new JButton("Modificar Datos");
		parentFrame.buttonCreate(btnModificarEquipo, panel, parentFrame.colorBlue);
		btnModificarEquipo.addActionListener(this);
		
		btnCambiarFoto = new JButton("Cambiar Fotografía");
		parentFrame.buttonCreate(btnCambiarFoto, panel, parentFrame.colorYellow);
		btnCambiarFoto.addActionListener(this);
		
		PanelContenidoJugador = new JPanel();
		PanelContenidoJugador.setPreferredSize(new Dimension(600, 600)); // Tamaño deseado
		PanelDatosJugador.add(PanelContenidoJugador, BorderLayout.CENTER);
		PanelContenidoJugador.setLayout(new MigLayout("", "[40px][70px][85px]", "[150px][19px][19px][19px][19px]"));
		
		lblNroID = new JLabel("Nº ID:");
		lblNroID.setHorizontalAlignment(SwingConstants.RIGHT);
		PanelContenidoJugador.add(lblNroID, "cell 1 1,grow");
		
		txtNumID = new JTextField();
		txtNumID.setEditable(false);
		txtNumID.setEnabled(false);
		txtNumID.setText("0");
		PanelContenidoJugador.add(txtNumID, "cell 2 1,growx,aligny top");
		txtNumID.setColumns(10);
		
		lblNombreEquipo = new JLabel("NOMBRE:");
		lblNombreEquipo.setHorizontalAlignment(SwingConstants.RIGHT);
		PanelContenidoJugador.add(lblNombreEquipo, "cell 1 2,grow");
		
		txtNombre = new JTextField();
		txtNombre.setText("Nombre Equipo");
		txtNombre.setColumns(10);
		PanelContenidoJugador.add(txtNombre, "cell 2 2,growx,aligny top");
		
		txtEntrenador = new JTextField();
		txtEntrenador.setText("Entrenador");
		txtEntrenador.setColumns(10);
		PanelContenidoJugador.add(txtEntrenador, "cell 2 3,growx,aligny top");
		
		lblEntrenador = new JLabel("ENTRENADOR:");
		lblEntrenador.setHorizontalAlignment(SwingConstants.RIGHT);
		PanelContenidoJugador.add(lblEntrenador, "cell 1 3,grow");
		
		lblFechaFundacion = new JLabel("F. FUNDACIÓN:");
		lblFechaFundacion.setHorizontalAlignment(SwingConstants.RIGHT);
		PanelContenidoJugador.add(lblFechaFundacion, "cell 1 4,alignx left,growy");
		
		txtFechaFundacion = new JTextField();
		txtFechaFundacion.setText("AAAA");
		txtFechaFundacion.setColumns(10);
		PanelContenidoJugador.add(txtFechaFundacion, "cell 2 4,growx,aligny top");
		
		icon = new ImageIcon("C:/xampp/htdocs/imagenes/equipos/idFotodefault.png");
		Image image = icon.getImage();
		Image newImage = image.getScaledInstance(79, 93, Image.SCALE_SMOOTH);
		icon = new ImageIcon(newImage);
		labelImagen = new JLabel();
		PanelContenidoJugador.add(labelImagen, "cell 2 0,grow");
		labelImagen.setIcon(icon);
		DefaultComboBoxModel<String> modelPosicion = new DefaultComboBoxModel<>();
		modelPosicion.addElement("Armador");
		modelPosicion.addElement("Opuesto");
		modelPosicion.addElement("Receptor1");
		modelPosicion.addElement("Receptor2");
		modelPosicion.addElement("Central");
		modelPosicion.addElement("Libero");
		
		PanelListEquipos = new JPanel();
		PanelContenedor.add(PanelListEquipos);
		PanelListEquipos.setLayout(new BorderLayout(0, 0));
		
		Cabecera2 = new JPanel();
		PanelListEquipos.add(Cabecera2, BorderLayout.NORTH);
		
		lblTituloTablaJugadores = new JLabel("LISTA DE EQUIPOS POR TEMPORADA");
		Cabecera2.add(lblTituloTablaJugadores);
		
		panel_1 = new JPanel();
		PanelListEquipos.add(panel_1, BorderLayout.SOUTH);
		
		btnGuardarCambios = new JButton("Guardar Cambios");
		parentFrame.buttonCreate(btnGuardarCambios, panel_1, parentFrame.colorBlue);
		btnGuardarCambios.addActionListener(this);
		
		panel_2 = new JPanel();
		PanelListEquipos.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
		
		// Crear el modelo de tabla para jugadores
		EquiposTableModel equipoTableModel = new EquiposTableModel(listEquipos);

		// Crear la tabla y asignar el modelo
		table = new JTable(equipoTableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Selección de una sola fila
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    @Override
		    public void valueChanged(ListSelectionEvent e) {
		        if (!e.getValueIsAdjusting()) {
		            ReflejarSeleccionEquipo(); // Llama al método cuando se selecciona una fila
		        }
		    }
		});
		
		// Configurar el ancho de las columnas
		TableColumnModel columnModel = table.getColumnModel();

		// Establecer el ancho preferido de cada columna
		columnModel.getColumn(0).setPreferredWidth(25);  // "Nº ID"
		columnModel.getColumn(1).setPreferredWidth(150); // "Nombre"
		columnModel.getColumn(2).setPreferredWidth(80);  // "F. Fundacion"
		columnModel.getColumn(3).setPreferredWidth(150); // "Entrenador"
		columnModel.getColumn(4).setPreferredWidth(75);  // "Total Jugadores"
		
		
		// Formatear la tabla si tienes un método para eso (opcional)
		parentFrame.formatearTabla(table); // Si no tienes este método, puedes omitir esta línea

		// Agregar la tabla al JScrollPane
		scrollPane = new JScrollPane(table);
		scrollPane.getViewport().setBackground(colorbg);

		// Añadir el JScrollPane al panel deseado
		panel_3.add(scrollPane, BorderLayout.CENTER);

		
		panel_4 = new JPanel();
		panel_2.add(panel_4, BorderLayout.NORTH);
		
		lblTituloTempo = new JLabel("Temporada:");
		panel_4.add(lblTituloTempo);
		
		combxFiltrarTempo = new JComboBox<>();
		combxFiltrarTempo.setPreferredSize(new Dimension(150, 25)); // Establece un ancho de 150px y alto de 25px
		panel_4.add(combxFiltrarTempo);
		actualizarComboBox(combxFiltrarTempo, listTemporadas);
		combxFiltrarTempo.addActionListener(e -> {
		    Object selectedItem = combxFiltrarTempo.getSelectedItem();
		    if (selectedItem != null) {
		        actualizarTabla();
		        actualizarEstadoBotones();
		    }
		});
		
		JComponent labelFormat[] = { lblTituloDatosJugador, lblTituloTablaJugadores, lblNroID, lblNombreEquipo, lblEntrenador, lblFechaFundacion };
		JComponent panelFormat[] = { PanelContenedor, PanelDatosJugador, Cabecera, PanelContenidoJugador, PanelListEquipos, Cabecera2, panel, panel_1, panel_2, panel_3, panel_4 };

		// Loop through labelFormat and apply properties
		for (int i = 0; i < labelFormat.length; i++) {
		    labelFormat[i].setBackground(colorbg);
		    labelFormat[i].setForeground(colortxt);
		    labelFormat[i].setFont(parentFrame.fuenteDefecto);
		}

		// Loop through panelFormat and apply properties
		for (int i = 0; i < panelFormat.length; i++) {
		    panelFormat[i].setBackground(colorbg);
		}
		
//		CrearJugadoresPrueba();
	}
	
	
	private Equipo obtenerDatosEquipo() {
		try {
			//obtener el equipo num id y validar
			String Equ_id = txtNumID.getText();
			 if (Equ_id.isEmpty()) {
	                parentFrame.mensaje("El campo de número de equipo está vacío.", 0);
	                return null;
	            }
			//Validar campos obligatorios 
			 String Equ_nombre = txtNombre.getText();
			 String Equ_entrenador = txtEntrenador.getText();
			 String Equ_fundacion = txtFechaFundacion.getText();
			   if (Equ_nombre.isEmpty() || Equ_entrenador.isEmpty() || Equ_fundacion.isEmpty()) {
	                parentFrame.mensaje("Los campos de Equ_nombre, Equ_entrenador y Equ_fundacion son obligatorios.", 0);
	                return null;
	            }
	            // Crear y devolver el jugador con todos los datos
	            return new Equipo();
	        } catch (NumberFormatException e) {
	            parentFrame.mensaje("Error al convertir un campo numérico. Verifique los datos ingresados.", 0);
	            return null;
	        }
		}	
	
	public void CrearEquipo() {
		try {
			//Obtenre los datos del formulario
			Equipo nuevoequipo = obtenerDatosEquipo();
			 if (nuevoequipo == null) return; // Si hay un error en los datos, detener el proceso.

			 // Buscar la temporada y equipo seleccionados
	            int idTemporada = combxFiltrarTempo.getSelectedIndex();
	            Temporada temporadaSeleccionada = listTemporadas.get(idTemporada);

            // **Verificar si ya existe un equipo con el mismo número de id**
            for (Equipo equipo : temporadaSeleccionada.getListEquipos()) {
                if (equipo.getId() == nuevoequipo.getId()) {
                    parentFrame.mensaje(" Ya existe un equipo con el número de ficha '" + nuevoequipo.getId() + "'.", 0);
                    return; // Detener el proceso si el jugador ya existe
                }
            }
            
            // Actualizar la tabla de equipos
            ((EquiposTableModel) table.getModel()).fireTableDataChanged();
            parentFrame.changes = true;

            //consulta para crear equipo
            String consulta = "INSERT into equipos VALUES (?, ?, ?,?)";
            try( Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/reto2", "root", "");
                    PreparedStatement st = conexion.prepareStatement(consulta);)
            {
            	int id = nuevoequipo.getId();
            	String nom = nuevoequipo.getNombre();
            	String entrenador = nuevoequipo.getEntrenador();
            	Date fundacion = nuevoequipo.getFechaFundEq();
            	int CantidadJugadores = nuevoequipo.getCantidadJugadores();
            	
            	st.setInt(0, id);
            	st.setString(1, nom);
            	st.setString(2, entrenador);
            	st.setDate(3, fundacion);
            	st.setInt(4, CantidadJugadores);
            	
            	int filas = st.executeUpdate();
            	if (filas>0) {
            		System.out.println("añadido equipo");
            }else {
            	System.out.println("error");
        }
            }catch (SQLException e) {
            	parentFrame.mensaje(" Error al crear el equipo:" , 0);
            }
            actualizarArchivo();  
		} catch (Exception e) {
            parentFrame.mensaje("Error inesperado: " + e.getMessage(), 0);
            e.printStackTrace();
        }
	}
	
	private void actualizarEquiposConCantidadJugadores() {
	    // Consulta SQL para obtener la cantidad de jugadores por cada equipo
	    String consulta = "SELECT id_Equipo, COUNT(id_Jugador) AS cantidad_jugadores " +
	                      "FROM participantes "  +
	                      "GROUP BY equipos.id_Equipo";
	   
	    // Crear una lista para almacenar los equipos con la cantidad de jugadores
	    ArrayList<Equipo> listaEquipos = new ArrayList<>();
	    
	    try (Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/reto2", "root", "");
	         PreparedStatement st = conexion.prepareStatement(consulta);
	         ResultSet rs = st.executeQuery()) {
	        
	        // Iterar sobre los resultados y agregar los equipos con su cantidad de jugadores
	        while (rs.next()) {
	            int idEquipo = rs.getInt("Equ_id");
	            String nombreEquipo = rs.getString("Equ_nombre");
	            int CantidadJugadores = rs.getInt("cantidad_jugadores");
	            
	            // Crear un nuevo objeto Equipo
	            Equipo equipo = new Equipo();
	            equipo.setId(idEquipo);
	            equipo.setNombre(nombreEquipo);
	            // Puedes establecer los jugadores o cualquier otro dato
	            // equipo.setListaJUgador(...); Si tienes los jugadores en la misma consulta, o puedes hacer una consulta separada.

	            // Establecer la cantidad de jugadores en el equipo
	            // equipo.setCantidadJugadores(cantidadJugadores);  // Si lo tienes en el modelo de datos

	            listaEquipos.add(equipo);  // Agregar el equipo a la lista
	        }

	        // Establecer la lista de equipos en el modelo de la tabla
	        EquiposTableModel model = (EquiposTableModel) table.getModel();
	        model.setListaEquipos(listaEquipos);

	    } catch (SQLException e) {
	        System.out.println("Error al obtener los equipos: " + e.getMessage());
	        e.printStackTrace();
	    }
	}



	
	private void actualizarEstadoBotones() {
	    int selectedIndex = combxFiltrarTempo.getSelectedIndex();
	    
	    if (selectedIndex >= 0 && !listTemporadas.isEmpty()) {
	        Temporada temporadaSeleccionada = listTemporadas.get(selectedIndex);
	        
	        boolean temporadaIniciada = temporadaSeleccionada.isIniciado();
	        btnGuardarCambios.setVisible(!temporadaIniciada);
	        btnCambiarFoto.setEnabled(!temporadaIniciada);
	        btnModificarEquipo.setEnabled(!temporadaIniciada);
	    }
	}

	
    // Clase interna para el modelo de la tabla de EQUIPOS
    class EquiposTableModel extends AbstractTableModel {
    	ArrayList<Equipo>JTablelistaEquipos;
        private static final long serialVersionUID = 1L;
        private String[] columnNames = {
            "Nº ID", "Nombre", "Fecha Fund.", "Entrenador",
            "Cantid. Jugadores",
        };

        public EquiposTableModel(ArrayList<Equipo> listaEquipos) {
            if (listaEquipos == null) {
                this.JTablelistaEquipos = new ArrayList<>(); // Inicializa una lista vacía
            } else {
                this.JTablelistaEquipos = listaEquipos;
            }
        }
        @Override
        public int getRowCount() {
            return JTablelistaEquipos.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
        	if (rowIndex < 0 || rowIndex >= JTablelistaEquipos.size()) {
                return null; // O lanzar una excepción personalizada
            }
        	Equipo equipo = JTablelistaEquipos.get(rowIndex);
            switch (columnIndex) {
                case 0: return equipo.getId();
                case 1: return equipo.getNombre();
                case 2: return equipo.getFechaFundEq();
                case 3: return equipo.getEntrenador();
                case 4: return equipo.getListJugadores() != null ? equipo.getListJugadores().size() : 0;
                default: return null;
            }
        }
        
        
     // Método para actualizar la lista de equipos
        public void setListaEquipos(ArrayList<Equipo> nuevaLista) {
            this.JTablelistaEquipos = nuevaLista;
            fireTableDataChanged(); // Notifica a la tabla que los datos han cambiado
        }
    }
    
    
    private void ReflejarSeleccionEquipo() {
        // Verificar que haya temporadas y equipos en la lista
        if (listTemporadas == null || listTemporadas.isEmpty()) {
            parentFrame.mensaje("No hay temporadas disponibles", 0);
            return;
        }
        
        // Obtener el índice de la temporada seleccionada y el equipo seleccionado
        int idTemporada = combxFiltrarTempo.getSelectedIndex();  // Índice de la temporada seleccionada
        
        // Verificar que los índices sean válidos
        if (idTemporada == -1 || idTemporada >= listTemporadas.size()) {
            parentFrame.mensaje("Debe seleccionar una temporada y un equipo válidos", 0);
            return;
        }

        // Obtener la temporada y el equipo seleccionados
        Temporada temporadaSeleccionada = listTemporadas.get(idTemporada);

        // Obtener el índice de la fila seleccionada en la tabla
        int rowIndex = table.getSelectedRow();  // Obtiene la fila seleccionada de la tabla

        
        // Verificar si se ha seleccionado una fila válida
        if (rowIndex != -1) {
            try {
                // Obtener el jugador seleccionado de la lista de jugadores del equipo seleccionado
                Equipo EquipoSeleccionado = temporadaSeleccionada.getListEquipos().get(rowIndex); 
             // Suponiendo que EquipoSeleccionado.getFechaFundEq() devuelve un objeto Date
                Date fechaFundacion = EquipoSeleccionado.getFechaFundEq();

                // Creamos un formato de fecha para convertirlo a texto
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");  // Puedes ajustar el formato a tus necesidades

                // Convertimos la fecha a string usando el formato
                String fechaString = sdf.format(fechaFundacion);
                // Asignar los valores a los campos de texto
                
                txtNumID.setText(Integer.toString(EquipoSeleccionado.getId()));
                txtNombre.setText(EquipoSeleccionado.getNombre());
                txtEntrenador.setText(EquipoSeleccionado.getEntrenador());
                txtFechaFundacion.setText(fechaString);               
                
             // **Construir la ruta de la imagen basada en la carpeta externa**
                String rutaImagen = "C:/xampp/htdocs/imagenes/equipos/" + EquipoSeleccionado.getIdFoto() + ".png";

                // Cargar la imagen del jugador o usar una imagen por defecto si no existe
                File archivoImagen = new File(rutaImagen);
                if (!archivoImagen.exists()) {
                    System.err.println("⚠️ Imagen no encontrada: " + rutaImagen);
                    archivoImagen = new File("C:/xampp/htdocs/imagenes/equipos/idFotodefault.png");
                }

                // Verificar si la imagen existe y cargarla
                if (archivoImagen.exists()) {
                    BufferedImage bufferedImage = ImageIO.read(archivoImagen);
                    Image newImage = bufferedImage.getScaledInstance(79, 93, Image.SCALE_SMOOTH);
                    ImageIcon icon = new ImageIcon(newImage);

                    // Asignar la nueva imagen al JLabel
                    labelImagen.setIcon(icon);
                    labelImagen.revalidate();
                    labelImagen.repaint();
                } else {
                    System.err.println("❌ ERROR: No se pudo cargar la imagen por defecto.");
                }
                

            } catch (IOException e) {
                System.err.println("❌ ERROR al cargar la imagen: " + e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                parentFrame.mensaje("No se ha seleccionado un jugador válido", 0);
            }
        }
    }


    public void ModificarDatosEquipo() {
        try {
            // Obtener el ID del equipo y validar
            String idEquipoTexto = txtNumID.getText();
            if (idEquipoTexto.isEmpty()) {
                parentFrame.mensaje("❌ El campo de ID del equipo está vacío.", 0);
                return;
            }
           // int idEquipo = Integer.parseInt(idEquipoTexto);

            // Validar el nombre del equipo
            String nombre = txtNombre.getText();
            if (nombre.isEmpty()) {
                parentFrame.mensaje("❌ El campo de nombre está vacío.", 0);
                return;
            }

            // Validar el entrenador
            String entrenador = txtEntrenador.getText();
            if (entrenador.isEmpty()) {
                parentFrame.mensaje("❌ El campo de entrenador está vacío.", 0);
                return;
            }

            // Validar y obtener el año de fundación
            String fechaFundacionTexto = txtFechaFundacion.getText();
            if (fechaFundacionTexto.isEmpty()) {
                parentFrame.mensaje("❌ El campo de año de fundación está vacío.", 0);
                return;
            }

            // Verificar que solo sea un número de 4 dígitos (AAAA)
            if (!fechaFundacionTexto.matches("\\d{4}")) {
                parentFrame.mensaje("❌ El año de fundación debe tener el formato AAAA.", 0);
                return;
            }

            int anioFundacion = Integer.parseInt(fechaFundacionTexto);

            // Validar la temporada seleccionada
            int idTemporada = combxFiltrarTempo.getSelectedIndex();
            if (idTemporada == -1 || idTemporada >= listTemporadas.size()) {
                parentFrame.mensaje("❌ Debe seleccionar una temporada válida.", 0);
                return;
            }
            
            // Actualizar la tabla de equipos
            ((EquiposTableModel) table.getModel()).fireTableDataChanged();
            parentFrame.changes = true;
	     

	        
            String consulta = "UPDATE equipos SET Equ_Id = ?, Equ_nombre = ?, Equ_Entrenador = ?, Equ_fundacion = ?" + "WHERE Equ_Id = ?";
            try(Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/reto2", "root", "");
                    PreparedStatement st = conexion.prepareStatement(consulta);)
            {
            	//Obtenre los datos del formulario
    			Equipo nuevoequipo = obtenerDatosEquipo();
    			 if (nuevoequipo == null) return; // Si hay un error en los datos, detener el proceso.
    			
    			 
    			 int id = nuevoequipo.getId();
    	        	String nom = nuevoequipo.getNombre();
    	        	String entrenador1 = nuevoequipo.getEntrenador();
    	        	Date fundacion = nuevoequipo.getFechaFundEq();
            	System.out.println("actualiza");
            	//Preparar y ejecutar consultas para actualizar equipos
 
            	
            	st.setInt(0, id);
            	st.setString(1, nom);
            	st.setString(2, entrenador1);
            	st.setDate(3, fundacion);
            	
            	int filas = st.executeUpdate();
            	System.out.println("ejecuta");
            	  if (filas > 0) {
                      System.out.println("Jugador actualizado correctamente en la BD.");
                      // Actualizar la tabla y realizar otras acciones necesarias
                  } else {
                      parentFrame.mensaje("❌ Error al actualizar el jugador en la BD", 0);
                  }

              } catch (SQLException e) {
                  System.out.println("Error al ejecutar la consulta: " + e.getMessage());
                  e.printStackTrace();
              }

          } catch (Exception e) {
              parentFrame.mensaje("❌ Error inesperado: " + e.getMessage(), 0);
              e.printStackTrace();
          }
      }

            
         

    public void CambiarFotografia() {
        int rowIndex = table.getSelectedRow();

        if (rowIndex == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un equipo de la lista.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Obtener equipo seleccionado
        int idTemporada = combxFiltrarTempo.getSelectedIndex();
        Temporada temporadaSeleccionada = listTemporadas.get(idTemporada);
        Equipo equipoSeleccionado = temporadaSeleccionada.getListEquipos().get(rowIndex);

        // Nueva ruta para equipos fuera del JAR
        String rutaBase = "C:/xampp/htdocs/imagenes/equipos/";
        File directorio = new File(rutaBase);
        if (!directorio.exists()) {
            directorio.mkdirs(); // Crea la carpeta si no existe
        }

        // Abrir JFileChooser para seleccionar una nueva imagen
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar Fotografía");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Imágenes (JPG, PNG)", "jpg", "png");
        fileChooser.setFileFilter(filter);

        int seleccion = fileChooser.showOpenDialog(this);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();
            
            // Obtener el nombre del archivo original (sin la extensión)
            String nombreArchivoCompleto = archivoSeleccionado.getName();
            String nombreBase = nombreArchivoCompleto.substring(0, nombreArchivoCompleto.lastIndexOf('.'));

            // Definir la ruta de destino utilizando el nombre base (sin extensión)
            File archivoDestino = new File(rutaBase + nombreBase + ".png"); // Aquí guardamos con extensión .png

            try {
                // Leer la imagen seleccionada
                BufferedImage imagenOriginal = ImageIO.read(archivoSeleccionado);
                if (imagenOriginal == null) {
                    JOptionPane.showMessageDialog(this, "Error al leer la imagen.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Eliminar la imagen antigua (si existe) antes de guardar la nueva
                if (archivoDestino.exists()) {
                    archivoDestino.delete();
                }

                // Guardar la nueva imagen con su nombre base (sin cambiar la extensión)
                ImageIO.write(imagenOriginal, "png", archivoDestino);

                // Actualizar el idFoto del equipo con el nombre base de la imagen (sin extensión)
                equipoSeleccionado.setIdFoto(nombreBase);  // Aquí actualizas el ID con el nuevo nombre sin extensión

                // Forzar la recarga de la imagen para evitar caché
                BufferedImage bufferedImage = ImageIO.read(archivoDestino);
                Image newImage = bufferedImage.getScaledInstance(79, 93, Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(newImage);
                labelImagen.setIcon(icon);

                // Refrescar el JLabel para asegurar que la imagen se actualiza
                labelImagen.revalidate();
                labelImagen.repaint();

                JOptionPane.showMessageDialog(this, "Fotografía del equipo guardada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                
                // Actualizar el indicador de cambios en el parentFrame
                parentFrame.changes = true;

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar la imagen: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void EliminarEquipo() {
        // Obtener la temporada y equipo seleccionados
        int idTemporada = combxFiltrarTempo.getSelectedIndex();
        // Obtener el ID del equipo y validar
        String idEquipoTexto = txtNumID.getText();
        if (idEquipoTexto.isEmpty()) {
            parentFrame.mensaje("❌ El campo de ID del equipo está vacío.", 0);
            return;
        }
        int idEquipo = Integer.parseInt(idEquipoTexto);
        
        // Verificar si se ha seleccionado una temporada y un equipo
        if (idTemporada == -1 || idEquipo == -1) {
            parentFrame.mensaje("❌ Debes seleccionar una temporada y un equipo válidos.", 0);
            return;
        }

        Temporada temporadaSeleccionada = listTemporadas.get(idTemporada);
        Equipo equipoSeleccionado = temporadaSeleccionada.getListEquipos().get(idEquipo);

        // Confirmar eliminación del equipo
        int confirmacion = JOptionPane.showConfirmDialog(
            this, 
            "⚠️ ¿Está seguro de que desea eliminar el equipo '" + equipoSeleccionado.getNombre() + "'?", 
            "Confirmar Eliminación de Equipo", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.WARNING_MESSAGE
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            // Eliminar el equipo de la lista de equipos de la temporada
            temporadaSeleccionada.getListEquipos().remove(idEquipo);

            // Actualizar la tabla de equipos
            ((EquiposTableModel) table.getModel()).fireTableDataChanged();
            parentFrame.changes = true;
            
            // Consulta para eliminar el equipo de la base de datos
            String consulta = "DELETE FROM equipos WHERE Equ_Id=?";

            try (
                Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/reto2", "root", "");
                PreparedStatement st = conexion.prepareStatement(consulta);
            ) {
                System.out.println("Conexión Correcta.");

                // Asignar el id del equipo a la consulta
                st.setInt(1, equipoSeleccionado.getId());

                // Ejecutar la consulta
                int filas = st.executeUpdate();

                if (filas < 1) {
                    System.out.println("⚠️ No se encontró el equipo para eliminar. Filas no afectadas.");
                } else {
                    System.out.println("✅ Equipo eliminado correctamente de la base de datos.");
                }
            } catch (SQLException e) {
                System.out.println("❌ Error al eliminar el equipo: " + e.getMessage());
            }

            // Actualizar archivo con la nueva lista de equipos
            actualizarArchivo();

            // Mensaje de éxito
            parentFrame.mensaje("✅ El equipo '" + equipoSeleccionado.getNombre() + "' ha sido eliminado correctamente.", 2);
        }
    }


    
    private void actualizarTabla() {
        // Validar temporada seleccionada
        int idTemporada = combxFiltrarTempo.getSelectedIndex();
        if (idTemporada == -1 || idTemporada >= listTemporadas.size()) {
            parentFrame.mensaje("Debe seleccionar una temporada válida.", 0);
            return;
        }

        // Obtener la lista de equipos de la Temporada seleccionada
        ArrayList<Equipo> equipos = listTemporadas.get(idTemporada).getListEquipos();
        // Actualizar el modelo de la tabla
        EquiposTableModel model = (EquiposTableModel) table.getModel();
        model.setListaEquipos(equipos);
       
    }
    
    private <T> void actualizarComboBox(JComboBox<T> comboBox, java.util.List<T> listaDatos) {
        comboBox.removeAllItems(); // Eliminar elementos actuales del JComboBox
        for (T elemento : listaDatos) {
            comboBox.addItem(elemento); // Agregar cada elemento de la lista al JComboBox
        }
        // Seleccionar por defecto el primer elemento si la lista no está vacía
        if (!listaDatos.isEmpty()) {
            comboBox.setSelectedIndex(0);
        }
    }


    private void actualizarArchivo() {
		if (parentFrame.changes == true) {
			try (FileOutputStream fos = new FileOutputStream(parentFrame.temporadasFile);
					ObjectOutputStream oos = new ObjectOutputStream(fos)) {
				int length = listTemporadas.size();
				int counter = 0;
				while (counter < length) {
					oos.writeObject(listTemporadas.get(counter));
					counter++;
				}
				parentFrame.mensaje("Cambios guardados.", 2);

				parentFrame.changes = false;

			} catch (IOException e) {
				// TODO Auto-generated catch block
				parentFrame.mensaje("Error al guardar", 0);

			}
		}
    	}
	//metodo para cargar las temporadas
    
    	private void cargarTemporadasDesdeArchivo() {
        	String query = "SELECT e.*, eq.Equ_foto " +
                    "FROM equipos e " +
                    "JOIN equipotemp eq " +
                    "ON e.Equ_id = eq.id_equipo " +
                    "WHERE eq.id_Temporada = ? " +
                    "GROUP BY e.Equ_id, e.Equ_nombre;";
        	
     try (
         Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/reto2", "root", "");
         Statement st = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
         PreparedStatement pst = conexion.prepareStatement(query)
     ) {
         // Si se ha conectado correctamente
         System.out.println("Conexión Correcta.");
         
         // Ejecutamos la primera consulta
         ResultSet rs = st.executeQuery("SELECT * FROM reto2.temporadas;");
         
         // Procesar los resultados de la primera consulta
         while (rs.next()) {
            
             int id = (Integer) rs.getObject("Tem_id");
             String gnombre = (String) rs.getObject("Tem_nombre");
             int cantEqu = (Integer) rs.getObject("Tem_cantidad_equipos");
             Temporada t = new Temporada(id, gnombre, cantEqu);
             // Agregar el objeto Temporada a la lista
             listTemporadas.add(t);
             // Usamos PreparedStatement para la segunda consulta con el parámetro dinámico
             pst.setInt(1, id);  // Asignamos el valor de id al parámetro de la consulta
             ResultSet rs1 = pst.executeQuery();
             ArrayList<Equipo> test = new ArrayList<Equipo>();
             // Procesamos los resultados de la segunda consulta
             while (rs1.next()) {
                 int equId = rs1.getInt("Equ_id");
                 String equNombre = rs1.getString("Equ_nombre");
                 String equfoto = rs1.getString("Equ_foto");
                 String equEntrendador = rs1.getString("Equ_entrenador");
                 Date ano = rs1.getDate("Equ_fundacion");
                
                test.add(new Equipo(equId, equNombre,equEntrendador,ano, equfoto));
                
             }
             t.setListEquipos(test);
             
         }
     } catch (SQLException e) {
         // Si hay un error en la conexión o ejecución
         System.out.println("Error de Conexión");
         e.printStackTrace();  // Esto es útil para ver el detalle del error
     }


        }
    
   

    
    
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		 if (o == btnModificarEquipo) {
	        	ModificarDatosEquipo();
	        }
		 else if (o == btnCambiarFoto) {
			 	CambiarFotografia();
		 }
		 else if (o == btnGuardarCambios){
				actualizarArchivo();
				JOptionPane.showMessageDialog(this, "Equipos guardados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	 
		 }
	}
}