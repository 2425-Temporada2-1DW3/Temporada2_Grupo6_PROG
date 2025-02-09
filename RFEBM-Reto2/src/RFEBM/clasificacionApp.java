package RFEBM;

import java.awt.BorderLayout;



import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.BaseColor;

import org.apache.log4j.Logger;

import Classes.EstadoTemporada;
import Classes.RolApp;
import Classes.TemporadaApp;
import log.log;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.JScrollPane;

public class clasificacionApp extends JFrame implements ActionListener{
    private static final long serialVersionUID = -4093081654081064634L;

    // Declarar arrays para las jornadas y los goles
    public static String[][] jornadasLoc = {
        {"Barcelona", "Madrid", "Murcia"},
        {"Cáceres", "Sevilla", "Bilbao"},
        {"Barcelona", "Cáceres", "Bilbao"},
        {"Murcia", "Madrid", "Sevilla"},
        {"Cáceres", "Madrid", "Sevilla"},
        {"Bilbao", "Barcelona", "Murcia"},
        {"Barcelona", "Murcia", "Sevilla"},
        {"Bilbao", "Madrid", "Cáceres"},
        {"Barcelona", "Madrid", "Murcia"},
        {"Sevilla", "Bilbao", "Cáceres"}
    };

    public static String[][] jornadasGolLoc = {
            {"", "", ""},
            {"", "", ""},
            {"", "", ""},
            {"", "", ""},
            {"", "", ""},
            {"", "", ""},
            {"", "", ""},
            {"", "", ""},
            {"", "", ""},
            {"", "", ""},
    };

    public static String[][] jornadasVis = {
        {"Cáceres", "Sevilla", "Bilbao"},
        {"Barcelona", "Madrid", "Murcia"},
        {"Murcia", "Madrid", "Sevilla"},
        {"Barcelona", "Cáceres", "Bilbao"},
        {"Bilbao", "Barcelona", "Murcia"},
        {"Cáceres", "Madrid", "Sevilla"},
        {"Bilbao", "Madrid", "Cáceres"},
        {"Barcelona", "Murcia", "Sevilla"},
        {"Sevilla", "Bilbao", "Cáceres"},
        {"Barcelona", "Madrid", "Murcia"}
    };

    public static String[][] jornadasGolVis = {
    	    {"", "", ""},
            {"", "", ""},
            {"", "", ""},
            {"", "", ""},
            {"", "", ""},
            {"", "", ""},
            {"", "", ""},
            {"", "", ""},
            {"", "", ""},
            {"", "", ""},
    };

    // Agregar datos de la tabla
    public static String[] columnNames = {"Club", "Pts", "PJ", "PG", "PE", "PP", "GF", "GC", "DG"};
    public static String[][] tableData = {
        {"Madrid", "0", "0", "0", "0", "0", "0", "0", "0"},
        {"Cáceres", "0", "0", "0", "0", "0", "0", "0", "0"},
        {"Bilbao", "0", "0", "0", "0", "0", "0", "0", "0"},
        {"Barcelona", "0", "0", "0", "0", "0", "0", "0", "0"},
        {"Murcia", "0", "0", "0", "0", "0", "0", "0", "0"},
        {"Sevilla", "0", "0", "0", "0", "0", "0", "0", "0"}
    };

    static Object TEMPORADA_ACTUAL = new Object();
	static int jornadaSeleccionada = 0;
	private JPanel contentPane;
	private JLabel txtClasif; 
	private JPanel panel; JPanel panel1; JPanel panel4; JPanel panel5; JPanel panel_1; JPanel panel_2; JPanel panel_3; JPanel panel_4; JPanel panel_5; JPanel panel_6; JPanel panel_7; JPanel panel_8; JPanel panel_9; JPanel panel_10; JPanel panel_11; JPanel panel_12; JPanel panel_13; JPanel panel_14;
	private JButton btnAplicarCambios; JButton btnVolver; JButton btnAtras; JButton btnAlante;JLabel lblNewLabel_1; JLabel lblNewLabel_2; static JLabel lblTextoCambios; JScrollPane scrollPane;
	private JComboBox <String> JornadaDesplegable;
	private static JTable table;
	private static JLabel EquipoLoc1; static JLabel EquipoLoc2; static JLabel EquipoLoc3;
	private static JLabel EquipoVis1; static JLabel EquipoVis2; static JLabel EquipoVis3;
	private static JTextField EquipoLocGol1; static JTextField EquipoLocGol2; static JTextField EquipoLocGol3;
	private static JTextField EquipoVisGol1; static JTextField EquipoVisGol2; static JTextField EquipoVisGol3;
	private JLabel VS1; JLabel VS2; JLabel VS3;
	private JPanel panel_15;
 	static JComboBox<String> comboTemporada;
 	Logger LOG = log.getLogger(clasificacionApp.class);
 	List<TemporadaApp> temporadas; // Variable de clase para almacenar las temporadas
 	

	
	
	/**
	 * Launch the application.
	 */

 public static void main(String[] args) {
	    EventQueue.invokeLater(new Runnable() {
	        public void run() {
	            try {
	                clasificacionApp frame = new clasificacionApp();
	                frame.setVisible(true);
	                frame.setResizable(false);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    });
	}

	public clasificacionApp() throws IOException {
		setTitle("Clasificación - " + TEMPORADA_ACTUAL);
        // Continuar con la configuración de la interfaz
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		golesfilter golesfilter = new golesfilter();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
	
	
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
	
		panel4 = new JPanel();
		panel.add(panel4, BorderLayout.EAST);
		panel4.setLayout(new BorderLayout(0, 0));
	
		panel5 = new JPanel();
		panel4.add(panel5);
		panel5.setLayout(new BorderLayout(0, 0));
	
		txtClasif = new JLabel("CLASIFICACIÓN");
		txtClasif.setHorizontalAlignment(SwingConstants.CENTER);
		panel5.add(txtClasif);
		txtClasif.setFont(new Font("Impact", Font.PLAIN, 42));
	
		panel_11 = new JPanel();
		panel.add(panel_11, BorderLayout.WEST);
	
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(clasificacionApp.class.getResource("/images/logos/logo.png")));
		panel_11.add(lblNewLabel_2);
		
		panel_15 = new JPanel();
		panel.add(panel_15, BorderLayout.CENTER);
			panel_15.setLayout(new BorderLayout(0, 0));
			
			panel_16 = new JPanel();
			panel_15.add(panel_16, BorderLayout.SOUTH);
			
			 

		
	
		panel1 = new JPanel();
		contentPane.add(panel1, BorderLayout.SOUTH);
		panel1.setLayout(new BorderLayout(0, 0));
	
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
		
			
			public void actionPerformed(ActionEvent e) {
				jornadaSeleccionada = 0;
				comboTemporada.setSelectedIndex(0);
				guardarClasificacion();
				new menuApp().setVisible(true);
				dispose();
				}
		});
		panel1.add(btnVolver, BorderLayout.EAST);
	
		btnAplicarCambios = new JButton("Aplicar Cambios");
		
		btnAplicarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblTextoCambios.setText("INFO : Cambios aplicados");
				limpiarValores();
				  if (jornadaSeleccionada < jornadasGolLoc.length && jornadaSeleccionada < jornadasGolVis.length) {
				jornadasGolLoc[jornadaSeleccionada][0] = EquipoLocGol1.getText();
				jornadasGolLoc[jornadaSeleccionada][1] = EquipoLocGol2.getText();
				jornadasGolLoc[jornadaSeleccionada][2] = EquipoLocGol3.getText();
				jornadasGolVis[jornadaSeleccionada][0] = EquipoVisGol1.getText();
				jornadasGolVis[jornadaSeleccionada][1] = EquipoVisGol2.getText();
				jornadasGolVis[jornadaSeleccionada][2] = EquipoVisGol3.getText();
				  } else {
			            lblTextoCambios.setText("ERROR: Jornada seleccionada fuera de los límites.");
			            return; // Evita procesar datos si hay un error
			        }
				updateTable();// Calcula Tabla Al Pulsar Boton

				  // Guardar datos en archivo
		        try {
		        	clasificacionApp.saveData(jornadasLoc, jornadasVis, jornadasGolLoc, jornadasGolVis, tableData);
		        } catch (IOException ex) {
		            lblTextoCambios.setText("ERROR: No se pudieron guardar los cambios.");
		            ex.printStackTrace();
		        }  
			}
		});
		panel1.add(btnAplicarCambios, BorderLayout.WEST);
		
		panel_17 = new JPanel();
		panel1.add(panel_17, BorderLayout.CENTER);
		panel_17.setLayout(new BorderLayout(0, 0));
		
		btnPDF = new JButton("Exportar a PDF");
		panel_17.add(btnPDF, BorderLayout.EAST);
		btnPDF.addActionListener(this);	
		
		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new BorderLayout(0, 0));
	
		panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));
	
		String[] jornadasList = new String[10];
		for (int i = 0; i < jornadasLoc.length; i++)
			jornadasList[i] = "Jornada " + (i + 1);
	
		JornadaDesplegable = new JComboBox<>(jornadasList);
		JornadaDesplegable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jornadaSeleccionada = JornadaDesplegable.getSelectedIndex();
				  if (jornadaSeleccionada == 0) {
						btnAlante.setVisible(true);
						btnAtras.setVisible(false);
					}  if (jornadaSeleccionada != 0) {
							btnAlante.setVisible(true);
							btnAtras.setVisible(true);
					} if (jornadaSeleccionada == 9) {
						btnAlante.setVisible(false);
						btnAtras.setVisible(true);
					}
				cargarDatosJornada(); // Carga Datos Jornada
				lblTextoCambios.setText("INFO : Jornada " + (jornadaSeleccionada + 1) + " Seleccionada");
				loadShields();
			}
		});
		panel_2.add(JornadaDesplegable, BorderLayout.CENTER);
		
		panel_12 = new JPanel();
		panel_2.add(panel_12, BorderLayout.NORTH);
		
		btnAtras = new JButton("🡨");
		btnAtras.setVisible(false);
		
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jornadaSeleccionada --;
				JornadaDesplegable.setSelectedIndex(jornadaSeleccionada);
				cargarDatosJornada();
				lblTextoCambios.setText("INFO : Jornada " + (jornadaSeleccionada + 1) + " Seleccionada");
				loadShields();
			}
		});
		panel_2.add(btnAtras, BorderLayout.WEST);
		
		btnAlante = new JButton("🡪");
		btnAlante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jornadaSeleccionada ++;
				JornadaDesplegable.setSelectedIndex(jornadaSeleccionada);
				cargarDatosJornada();
				lblTextoCambios.setText("INFO : Jornada " + (jornadaSeleccionada + 1) + " Seleccionada");
				loadShields();
			}
		});
		panel_2.add(btnAlante, BorderLayout.EAST);
	
		panel_4 = new JPanel();
		panel_1.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));
	
		panel_3 = new JPanel();
		panel_4.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));
	
		panel_5 = new JPanel();
		panel_3.add(panel_5, BorderLayout.NORTH);
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
			lblNewLabel_1 = new JLabel("PARTIDOS");
			panel_5.add(lblNewLabel_1);
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setFont(new Font("Impact", Font.PLAIN, 32));

	
		panel_6 = new JPanel();
		panel_3.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new BorderLayout(0, 0));
	
		panel_8 = new JPanel();
		panel_6.add(panel_8, BorderLayout.CENTER);
		panel_8.setLayout(new BorderLayout(0, 0));
	
		panel_7 = new JPanel();
		panel_8.add(panel_7, BorderLayout.NORTH);
		panel_7.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			lblEquipoLoc1 = new JLabel("");
			panel_7.add(lblEquipoLoc1);
		
			EquipoLocGol1 = new JTextField();
			panel_7.add(EquipoLocGol1);
			EquipoLocGol1.addKeyListener(golesfilter);
			EquipoLocGol1.setColumns(3);
			
			EquipoLoc1 = new JLabel("");
			panel_7.add(EquipoLoc1);
			EquipoLoc1.setFont(new Font("Arial", Font.PLAIN, 12));
			
			VS1 = new JLabel("VS");
			panel_7.add(VS1);
			VS1.setFont(new Font("Arial", Font.PLAIN, 14));
			
			EquipoVis1 = new JLabel("EquipoLoc");
			panel_7.add(EquipoVis1);
			EquipoVis1.setFont(new Font("Arial", Font.PLAIN, 12));
		
			EquipoVisGol1 = new JTextField();
			panel_7.add(EquipoVisGol1);
			EquipoVisGol1.addKeyListener(golesfilter);
			EquipoVisGol1.setColumns(3);
			
			lblEquipoVis1 = new JLabel("");
			panel_7.add(lblEquipoVis1);
	
		panel_9 = new JPanel();
		panel_8.add(panel_9, BorderLayout.CENTER);
		panel_9.setLayout(new BorderLayout(0, 0));
	
		panel_10 = new JPanel();
		panel_9.add(panel_10, BorderLayout.NORTH);
		panel_10.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			lblEquipoLoc2 = new JLabel("");
			panel_10.add(lblEquipoLoc2);
		
			EquipoLocGol2 = new JTextField();
			panel_10.add(EquipoLocGol2);
			EquipoLocGol2.setColumns(3);
			
				EquipoLoc2 = new JLabel("EquipoVis");
				panel_10.add(EquipoLoc2);
				EquipoLoc2.setFont(new Font("Arial", Font.PLAIN, 12));
				
					VS2 = new JLabel("VS");
					panel_10.add(VS2);
					VS2.setFont(new Font("Arial", Font.PLAIN, 14));
					
						EquipoVis2 = new JLabel("EquipoLoc");
						panel_10.add(EquipoVis2);
						EquipoVis2.setFont(new Font("Arial", Font.PLAIN, 12));
						
							EquipoVisGol2 = new JTextField();
							panel_10.add(EquipoVisGol2);
							EquipoVisGol2.setColumns(3);
							
							lblEquipoVis2 = new JLabel("");
							panel_10.add(lblEquipoVis2);
							EquipoVisGol2.addKeyListener(golesfilter);
			EquipoLocGol2.addKeyListener(golesfilter);
	
		lblTextoCambios = new JLabel("");
		lblTextoCambios.setFont(new Font("Calibri", Font.BOLD, 12));
		panel_9.add(lblTextoCambios, BorderLayout.SOUTH);

		panel_13 = new JPanel();
		panel_9.add(panel_13, BorderLayout.CENTER);
		panel_13.setLayout(new BorderLayout(0, 0));
		
		panel_14 = new JPanel();
		panel_13.add(panel_14, BorderLayout.NORTH);
			
			lblEquipoLoc3 = new JLabel("");
			panel_14.add(lblEquipoLoc3);
		
			EquipoLocGol3 = new JTextField();
			panel_14.add(EquipoLocGol3);
			EquipoLocGol3.setColumns(3);
			
				EquipoLoc3 = new JLabel("EquipoVis");
				panel_14.add(EquipoLoc3);
				EquipoLoc3.setFont(new Font("Arial", Font.PLAIN, 12));
				
					VS3 = new JLabel("VS");
					panel_14.add(VS3);
					VS3.setFont(new Font("Arial", Font.PLAIN, 14));
					
						EquipoVis3 = new JLabel("EquipoLoc");
						panel_14.add(EquipoVis3);
						EquipoVis3.setFont(new Font("Arial", Font.PLAIN, 12));
						
							EquipoVisGol3 = new JTextField();
							panel_14.add(EquipoVisGol3);
							EquipoVisGol3.setColumns(3);
							
							lblEquipoVis3 = new JLabel("");
							panel_14.add(lblEquipoVis3);
							EquipoVisGol3.addKeyListener(golesfilter);
			EquipoLocGol3.addKeyListener(golesfilter);
			
        // Crear Tabla
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		DefaultTableModel tableModel = new DefaultTableModel(tableData, columnNames);
		table = new JTable(tableModel);
		table.setEnabled(false);
		table.setFont(new Font("Calibri", Font.PLAIN, 14));
		table.setDefaultEditor(Object.class, null);
		scrollPane.setViewportView(table);
		table.setRowHeight(30);
		
		
		comboTemporada = new JComboBox<>();
		comboTemporada.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String temporadaSeleccionada = (String) comboTemporada.getSelectedItem();

		        // Buscar el estado de la temporada seleccionada
		        EstadoTemporada estadoTemporadaActual = null;
		        for (TemporadaApp temporada : temporadas) {
		            if (temporada.getNombre().equals(temporadaSeleccionada)) {
		                estadoTemporadaActual = temporada.getEstado();
		                break; // Salir del bucle una vez que se encuentra la temporada
		            }
		        }

		        // Habilitar o deshabilitar botones según el estado
		       

		        // Cargar datos de la temporada
		        loadData(temporadaSeleccionada);
		        String archivo = "resources/datos/Jornada" + temporadaSeleccionada + ".csv";
		        try {
		            cargarPartidosDesdeCSV(archivo);
		        } catch (IOException e1) {
		            e1.printStackTrace(); 
		        }
		        cargarDatosJornada();
		        loadShields();
		        updateTable();
		        if (inicioApp.rolUsuario == RolApp.Usuario) {
		            // Si el rol es Usuario, no se revisa el estado de la temporada
		            return; // Salir del método sin hacer nada
		        }
		        if (estadoTemporadaActual == EstadoTemporada.Sin_Iniciar) {
		        	EquipoLocGol1.setEditable(false); EquipoVisGol1.setEditable(false);
					EquipoLocGol1.setEnabled(false);  EquipoVisGol1.setEnabled(false);
	            	EquipoLocGol2.setEditable(false); EquipoVisGol2.setEditable(false);
					EquipoLocGol2.setEnabled(false);  EquipoVisGol2.setEnabled(false);
					EquipoLocGol3.setEditable(false); EquipoVisGol3.setEditable(false);
					EquipoLocGol3.setEnabled(false);  EquipoVisGol3.setEnabled(false);	
					btnAplicarCambios.setEnabled(false);
		        } else if (estadoTemporadaActual == EstadoTemporada.Iniciada) {
		        	EquipoLocGol1.setEditable(true); EquipoVisGol1.setEditable(true);
					EquipoLocGol1.setEnabled(true);  EquipoVisGol1.setEnabled(true);
	            	EquipoLocGol2.setEditable(true); EquipoVisGol2.setEditable(true);
					EquipoLocGol2.setEnabled(true);  EquipoVisGol2.setEnabled(true);
					EquipoLocGol3.setEditable(true); EquipoVisGol3.setEditable(true);
					EquipoLocGol3.setEnabled(true);  EquipoVisGol3.setEnabled(true);	
		            btnAplicarCambios.setEnabled(true);
		        } else if (estadoTemporadaActual == EstadoTemporada.Finalizada) {
		        	EquipoLocGol1.setEditable(false); EquipoVisGol1.setEditable(false);
					EquipoLocGol1.setEnabled(false);  EquipoVisGol1.setEnabled(false);
	            	EquipoLocGol2.setEditable(false); EquipoVisGol2.setEditable(false);
					EquipoLocGol2.setEnabled(false);  EquipoVisGol2.setEnabled(false);
					EquipoLocGol3.setEditable(false); EquipoVisGol3.setEditable(false);
					EquipoLocGol3.setEnabled(false);  EquipoVisGol3.setEnabled(false);	
					btnAplicarCambios.setEnabled(false);
		        }
		    }
		    
		    
		});
		  panel_16.add(comboTemporada);
		  cargarTemporadasDesdeArchivo();
		  TEMPORADA_ACTUAL = comboTemporada.getSelectedItem();
		  loadData(TEMPORADA_ACTUAL);
		  String archivo = (String) comboTemporada.getSelectedItem();
		  updateTable();
		  
	    Temporada = "resources/datos/Jornada"+archivo+".csv";
		  
	    cargarPartidosDesdeCSV(Temporada);
		

		// Datos Por Defecto
		updateTable(); // Calcula Tabla Al iniciar
		cargarDatosJornada(); // Carga Datos Jornada 1
		lblTextoCambios.setText("INFO : Mostrando Jornada " + (jornadaSeleccionada + 1));
		loadShields();
		configurarMenuSegunRol(inicioApp.rolUsuario);
		guardarClasificacion();
		comboTemporada.setSelectedIndex(0);
		
	}

	
private static void loadData(Object temporadaActual) {
		
		
	}
	class golesfilter extends KeyAdapter {
		@Override
	    public void keyTyped(KeyEvent e) {
	        char c = e.getKeyChar();
	        // Solo permitir caracteres numéricos
	        if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
	            e.consume(); // Evita que caracteres no numéricos se muestren
				lblTextoCambios.setText("ERROR : Solo Valores Numericos");

	        }
		}
		
	}
	public static void updateTable() {
		
		  // Resetea valores de tableData
	    for (int i = 0; i < tableData.length; i++) {
	        for (int j = 1; j < tableData[i].length; j++) { 
	            tableData[i][j] = "0";
            }
        }
	
        // Calcula Quien Gana y cuantos puntos tiene
	    for (int i = 0; i < jornadasLoc.length; ++i) {
	        for (int j = 0; j < jornadasLoc[i].length; ++j) {
				if ((jornadasGolLoc[i][j]).equals("") || (jornadasGolVis[i][j]).equals("") ) {
					updateTableData(jornadasLoc[i][j],0,0,0,0,0,0,0);
					updateTableData(jornadasVis[i][j],0,0,0,0,0,0,0);
	
				} else if (Integer.valueOf(jornadasGolLoc[i][j]) > Integer.valueOf(jornadasGolVis[i][j])) {
			        // Equipo local Gana, Visitante Pierde
					updateTableData(jornadasLoc[i][j],3,1,1,0,0,Integer.valueOf(jornadasGolLoc[i][j]),Integer.valueOf(jornadasGolVis[i][j]));
					updateTableData(jornadasVis[i][j],1,1,0,0,1,Integer.valueOf(jornadasGolVis[i][j]),Integer.valueOf(jornadasGolLoc[i][j]));
	
			    } else if (jornadasGolLoc[i][j].equals(jornadasGolVis[i][j])) {
			    	// Empatan
					updateTableData(jornadasLoc[i][j],2,1,0,1,0,Integer.valueOf(jornadasGolLoc[i][j]),Integer.valueOf(jornadasGolVis[i][j]));
					updateTableData(jornadasVis[i][j],2,1,0,1,0,Integer.valueOf(jornadasGolVis[i][j]),Integer.valueOf(jornadasGolLoc[i][j]));
			    } else {
			        // Equipo local Pierde, Visitante Gana
					updateTableData(jornadasLoc[i][j],1,1,0,0,1,Integer.valueOf(jornadasGolLoc[i][j]),Integer.valueOf(jornadasGolVis[i][j]));
					updateTableData(jornadasVis[i][j],3,1,1,0,0,Integer.valueOf(jornadasGolVis[i][j]),Integer.valueOf(jornadasGolLoc[i][j]));
			    }
			}
	
		} 
		for (int i = 0; i < 6 - 1; i++) {
		    for (int j = 0; j < 6 - 1 - i; j++) {
				if (Integer.parseInt(tableData[j][1]) < Integer.parseInt(tableData[j + 1][1])) {
				    String[] temp = tableData[j];
				    tableData[j] = tableData[j + 1];
				    tableData[j + 1] = temp;}
				if (Integer.parseInt(tableData[j][1]) == Integer.parseInt(tableData[j + 1][1])) {
				    String[] temp = tableData[j];
				    tableData[j] = tableData[j + 1];
				    tableData[j + 1] = temp;
					if (Integer.parseInt(tableData[j][8]) < Integer.parseInt(tableData[j + 1][8])) {
					    temp = tableData[j];
					    tableData[j] = tableData[j + 1];
					    tableData[j + 1] = temp;
					}
		        }
		    }
		}
	    // Actualiza la JTable
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    model.setDataVector(tableData, columnNames);
	    model.fireTableDataChanged();
	}
	public static void updateTableData(String equipo, int Pts , int PJ ,   int PG , int PE,   int PP,   int GF,    int GC ) {
		//							   NOMBRE EQUIPO  PUNTOS    P JUGADOS  P GANA   P EMPATE  P PIERDE  GOL FAVOR  GOL CONTRA 
		
		for (int j = 0; j < tableData.length; ++j) {
	        if (tableData[j][0].equals(equipo)) {
	            // Validación para evitar errores al sumar valores
	            try {
	                tableData[j][1] = Integer.toString(Pts + Integer.parseInt(tableData[j][1]));
	                tableData[j][2] = Integer.toString(PJ + Integer.parseInt(tableData[j][2]));
	                tableData[j][3] = Integer.toString(PG + Integer.parseInt(tableData[j][3]));
	                tableData[j][4] = Integer.toString(PE + Integer.parseInt(tableData[j][4]));
	                tableData[j][5] = Integer.toString(PP + Integer.parseInt(tableData[j][5]));
	                tableData[j][6] = Integer.toString(GF + Integer.parseInt(tableData[j][6]));
	                tableData[j][7] = Integer.toString(GC + Integer.parseInt(tableData[j][7]));
	                tableData[j][8] = Integer.toString((GF - GC) + Integer.parseInt(tableData[j][8]));
	            } catch (NumberFormatException ex) {
	                System.out.println("ERROR: Datos corruptos en la tabla.");
	            }
	        }
	    }
	}
	public static void cargarDatosJornada() { // Pone Los Valores Apropiados en Los lblTexto y Jtextfields
		EquipoLoc1.setText(jornadasLoc[jornadaSeleccionada][0]);
		EquipoLoc2.setText(jornadasLoc[jornadaSeleccionada][1]);
		EquipoLoc3.setText(jornadasLoc[jornadaSeleccionada][2]);
		
		EquipoVis1.setText(jornadasVis[jornadaSeleccionada][0]);
		EquipoVis2.setText(jornadasVis[jornadaSeleccionada][1]);
		EquipoVis3.setText(jornadasVis[jornadaSeleccionada][2]);
		
		EquipoLocGol1.setText(jornadasGolLoc[jornadaSeleccionada][0]);
		EquipoLocGol2.setText(jornadasGolLoc[jornadaSeleccionada][1]);
		EquipoLocGol3.setText(jornadasGolLoc[jornadaSeleccionada][2]);
		
		EquipoVisGol1.setText(jornadasGolVis[jornadaSeleccionada][0]);
		EquipoVisGol2.setText(jornadasGolVis[jornadaSeleccionada][1]);
		EquipoVisGol3.setText(jornadasGolVis[jornadaSeleccionada][2]);
	}
	
    public static void limpiarValores() { // Limpia Los valores especificados en esta funcion
        limpiarValorEspecifico(EquipoLocGol1);
        limpiarValorEspecifico(EquipoLocGol2);
        limpiarValorEspecifico(EquipoLocGol3);
        limpiarValorEspecifico(EquipoVisGol1);
        limpiarValorEspecifico(EquipoVisGol2);
        limpiarValorEspecifico(EquipoVisGol3);
    }
    
    private static void limpiarValorEspecifico(JTextField textField) { // Limpia un solo valor
    	
        if (!isNumeric(textField.getText()) && textField.getText().isEmpty() == false ){lblTextoCambios.setText("ERROR : Valor Invalido");textField.setText("");}
        if (!textField.getText().isEmpty() == false) {lblTextoCambios.setText("INFO : Campo Vacio, Partido No Contado");}
		if (textField.getText().isEmpty()==false && Double.parseDouble(textField.getText()) > 99) {textField.setText("99");lblTextoCambios.setText("ERROR : Valor Mayor De 99");}

    }
    
    public static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()){ return false;}
        try {Double.parseDouble(str);      return true; }
        catch (NumberFormatException e)  { return false;}
    }
  
 
	static String Temporada = "resources/datos/JornadaTemporada 2023-2024.csv";
	private JLabel lblEquipoLoc1;
	private JLabel lblEquipoVis1;
	private JLabel lblEquipoLoc2;
	private JLabel lblEquipoVis2;
	private JLabel lblEquipoLoc3;
	private JLabel lblEquipoVis3;
	private JPanel panel_16;

    public static void saveData(String[][] jornadasLoc, String[][] jornadasVis, String[][] jornadasGolLoc, String[][] jornadasGolVis,String[][] tableData) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Temporada))) {
            for (int i = 0; i < jornadasLoc.length; i++) {
                for (int j = 0; j < jornadasLoc[i].length; j++) {
                    writer.write(jornadasLoc[i][j] + "," + jornadasVis[i][j] + "," + jornadasGolLoc[i][j] + "," + jornadasGolVis[i][j]);
                    writer.newLine();
                }
            }
        }
    }
    private void configurarMenuSegunRol(RolApp rolUsuario) {
        // Configurar acceso a los botones según el rol del usuario
        switch (rolUsuario) {
            case Usuario : // Usuario (Rol Usuario)
            	EquipoLocGol1.setEditable(false); EquipoVisGol1.setEditable(false);
				EquipoLocGol1.setEnabled(false);  EquipoVisGol1.setEnabled(false);
            	EquipoLocGol2.setEditable(false); EquipoVisGol2.setEditable(false);
				EquipoLocGol2.setEnabled(false);  EquipoVisGol2.setEnabled(false);
				EquipoLocGol3.setEditable(false); EquipoVisGol3.setEditable(false);
				EquipoLocGol3.setEnabled(false);  EquipoVisGol3.setEnabled(false);	
				btnAplicarCambios.setEnabled(false);
				setTitle("Menú usuario");
				
				break;

            case Entrenador: // Admin (Rol Entrenador)
            	EquipoLocGol1.setEditable(false); EquipoVisGol1.setEditable(false);
				EquipoLocGol1.setEnabled(false);  EquipoVisGol1.setEnabled(false);
            	EquipoLocGol2.setEditable(false); EquipoVisGol2.setEditable(false);
				EquipoLocGol2.setEnabled(false);  EquipoVisGol2.setEnabled(false);
				EquipoLocGol3.setEditable(false); EquipoVisGol3.setEditable(false);
				EquipoLocGol3.setEnabled(false);  EquipoVisGol3.setEnabled(false);	
				btnAplicarCambios.setEnabled(false);
				setTitle("Menú entrenador");
                break;

            default:
                // Si no se reconoce el rol, deshabilitar todo
              
        }
    }
    public void loadShields() {
    	lblEquipoLoc1.setIcon(new ImageIcon(clasificacionApp.class.getResource("/images/logos/"+EquipoLoc1.getText()+"Mini.png")));
    	lblEquipoLoc2.setIcon(new ImageIcon(clasificacionApp.class.getResource("/images/logos/"+EquipoLoc2.getText()+"Mini.png")));
    	lblEquipoLoc3.setIcon(new ImageIcon(clasificacionApp.class.getResource("/images/logos/"+EquipoLoc3.getText()+"Mini.png")));
    	lblEquipoVis1.setIcon(new ImageIcon(clasificacionApp.class.getResource("/images/logos/"+EquipoVis1.getText()+"Mini.png")));
    	lblEquipoVis2.setIcon(new ImageIcon(clasificacionApp.class.getResource("/images/logos/"+EquipoVis2.getText()+"Mini.png")));
    	lblEquipoVis3.setIcon(new ImageIcon(clasificacionApp.class.getResource("/images/logos/"+EquipoVis3.getText()+"Mini.png")));
    }
    
    final int NUM_JORNADAS = 10; // Número de jornadas (esto depende de tu torneo)
    final int NUM_PARTIDOS = 3;  // Número de partidos por jornada (ajusta según sea necesario)
    private JPanel panel_17;
    private JButton btnPDF;
    
    public void cargarPartidosDesdeCSV(String archivo) throws IOException {
        // Inicializar las jornadas y goles a valores vacíos
        for (int i = 0; i < jornadasLoc.length; i++) {
            for (int j = 0; j < jornadasLoc[i].length; j++) {
                jornadasLoc[i][j] = "";
                jornadasVis[i][j] = "";
                jornadasGolLoc[i][j] = "";
                jornadasGolVis[i][j] = "";
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String line;
            int jornadaIndex = 0;
            int partidoIndex = 0;

            // Leer cada línea del archivo CSV
            while ((line = reader.readLine()) != null) {
                // Separar la línea en los valores correspondientes
                String[] parts = line.split(",");

                // Asegurarse de que la línea tiene los datos correctos
                if (parts.length >= 2) { // Asegúrate de que hay al menos dos partes (local y visitante)
                    // Asignar los nombres de los equipos
                    jornadasLoc[jornadaIndex][partidoIndex] = parts[0];  // Equipo Local
                    jornadasVis[jornadaIndex][partidoIndex] = parts[1];  // Equipo Visitante

                    // Asignar los goles si están disponibles
                    if (parts.length >= 4) {
                        jornadasGolLoc[jornadaIndex][partidoIndex] = parts[2];  // Goles Equipo Local
                        jornadasGolVis[jornadaIndex][partidoIndex] = parts[3];  // Goles Equipo Visitante
                    } else {
                        // Si no hay goles, asignar valores vacíos
                        jornadasGolLoc[jornadaIndex][partidoIndex] = "";
                        jornadasGolVis[jornadaIndex][partidoIndex] = "";
                    }

                    // Aumentar el índice del partido
                    partidoIndex++;

                    // Si llegamos al final de los partidos de la jornada, pasamos a la siguiente jornada
                    if (partidoIndex == NUM_PARTIDOS) {
                        partidoIndex = 0;
                        jornadaIndex++;
                    }
                }
            }
        } catch (IOException e) {
            // Manejo de excepciones para errores de lectura
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        // Llamar a updateTable para actualizar la tabla después de cargar los datos
        updateTable();
    }
  

    @SuppressWarnings("unchecked")
	private void cargarTemporadasDesdeArchivo() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("resources/datos/temporadas.ser"))) {
            // Leer toda la lista de temporadas
        
            temporadas = (List<TemporadaApp>) ois.readObject();
            
            // Limpiar los elementos previos del combo
            comboTemporada.removeAllItems();
            
            // Agregar solo el nombre de cada temporada al ComboBox
            for (TemporadaApp temporada : temporadas) { // Cambiado de 'temporadas' a 'temporadas1'
                comboTemporada.addItem(temporada.getNombre());
            }
            
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
       
       private void guardarClasificacion() {
    	   try {
    	       
    	        int numTemporadas = comboTemporada.getItemCount();
    	       
    	        for (int i = 0; i < numTemporadas; i++) {
    	          
    	            String temporadaActual = comboTemporada.getItemAt(i).toString();
    	            comboTemporada.setSelectedIndex(i);
    	            updateTable();
    	            String rutaArchivo = "resources/datos/Clasificacion" + temporadaActual + ".csv";
    	            guardarClasificacion(rutaArchivo, tableData);
    	          
    	        }

    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    }
    	}

    	    public static void guardarClasificacion(String rutaArchivo, String[][] data) throws IOException {
    	        try (FileWriter writer = new FileWriter(rutaArchivo)) {
    	            for (int i = 0; i < columnNames.length; i++) {
    	                writer.append(columnNames[i]);
    	                if (i < columnNames.length - 1) {
    	                    writer.append(",");
    	                }
    	            }
    	            writer.append("\n");

    	            for (String[] row : data) {
    	                for (int i = 0; i < row.length; i++) {
    	                    writer.append(row[i]);
    	                    if (i < row.length - 1) {
    	                        writer.append(",");
    	                    }
    	                }
    	                writer.append("\n");
    	            }
    	        }
    	    }
    	    public static void exportarTablaAPdf(JTable table, String filePath) {
    	        try {
    	            // Crear el documento PDF
    	            Document document = new Document();
    	            PdfWriter.getInstance(document, new FileOutputStream(filePath));
    	            document.open();

    	            // Crear una tabla PDF que tenga el mismo número de columnas que la JTable
    	            PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
    	            pdfTable.setWidthPercentage(100); // Para que ocupe todo el ancho de la página

    	            // Agregar las cabeceras de la tabla
    	            for (int i = 0; i < table.getColumnCount(); i++) {
    	                PdfPCell cell = new PdfPCell(new Phrase(table.getColumnName(i)));
    	                cell.setBackgroundColor(BaseColor.LIGHT_GRAY); // Color de fondo para las cabeceras
    	                pdfTable.addCell(cell);
    	            }

    	            // Agregar las filas de la tabla
    	            for (int row = 0; row < table.getRowCount(); row++) {
    	                for (int col = 0; col < table.getColumnCount(); col++) {
    	                    Object value = table.getValueAt(row, col);
    	                    String cellValue = value != null ? value.toString() : "";
    	                    pdfTable.addCell(cellValue);
    	                }
    	            }

    	            // Añadir la tabla al documento
    	            document.add(pdfTable);
    	            document.close();

    	            // Mostrar mensaje de éxito
    	            JOptionPane.showMessageDialog(null, "¡El PDF se ha creado correctamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    	        } catch (Exception e) {
    	            e.printStackTrace();
    	            JOptionPane.showMessageDialog(null, "Error al exportar la tabla a PDF: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    	        }
    	    }

			@Override
			public void actionPerformed(ActionEvent e) {
				Object o = e.getSource();
				
				if (o == btnPDF) {
					// Mostrar un diálogo para elegir la ubicación y el nombre del archivo PDF
			        JFileChooser fileChooser = new JFileChooser();
			        fileChooser.setDialogTitle("Guardar PDF");
			        fileChooser.setSelectedFile(new File("clasificacion.pdf")); // Nombre por defecto

			        int userSelection = fileChooser.showSaveDialog(this);
			        if (userSelection == JFileChooser.APPROVE_OPTION) {
			            File fileToSave = fileChooser.getSelectedFile();
			            String filePath = fileToSave.getAbsolutePath();
			            if (!filePath.endsWith(".pdf")) {
			                filePath += ".pdf"; // Asegurarse de que el archivo tenga la extensión .pdf
			            }
			            // Llamar a la función para exportar la tabla a PDF
			            exportarTablaAPdf(table, filePath);
			        }
			    }	
				}
				
	}