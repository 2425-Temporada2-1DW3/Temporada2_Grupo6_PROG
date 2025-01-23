package RFEBM;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

import java.io.IOException;

import javax.swing.JTable;
import javax.swing.JScrollPane;

public class equipoApp extends JFrame {
    private static final long serialVersionUID = -4093081654081064634L;

   
    // Temporada actual
    final static String TEMPORADA_ACTUAL = "Temporada2024-2025";
	static int jornadaSeleccionada = 0;
	private JPanel contentPane;
	private JLabel txtClasif; 
	private JPanel panel; JPanel panel1; JPanel panel4; JPanel panel5; JPanel panel_1; JPanel panel_2; JPanel panel_3; JPanel panel_4; JPanel panel_6; JPanel panel_8; JPanel panel_11; JButton btnVolver; JLabel lblNewLabel_2; JScrollPane scrollPane;
	private static JTable table;
 private JPanel panel_15;
 

	
	
	/**
	 * Launch the application.
	 */

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

	public equipoApp() throws IOException {
		setTitle("Clasificación - " + TEMPORADA_ACTUAL);
        // Continuar con la configuración de la interfaz
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		equipoApp.loadData(TEMPORADA_ACTUAL);
	
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
	
		panel4 = new JPanel();
		panel.add(panel4, BorderLayout.EAST);
		panel4.setLayout(new BorderLayout(0, 0));
	
		panel5 = new JPanel();
		panel4.add(panel5);
		panel5.setLayout(new BorderLayout(0, 0));
	
		txtClasif = new JLabel("PLANTILLAS");
		txtClasif.setHorizontalAlignment(SwingConstants.CENTER);
		panel5.add(txtClasif);
		txtClasif.setFont(new Font("Impact", Font.PLAIN, 42));
	
		panel_11 = new JPanel();
		panel.add(panel_11, BorderLayout.WEST);
	
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(equipoApp.class.getResource("/images/logos/logo.png")));
		panel_11.add(lblNewLabel_2);
		
		panel_15 = new JPanel();
		panel.add(panel_15, BorderLayout.CENTER);
		panel_15.setLayout(new BorderLayout(0, 0));
		
		panel_5 = new JPanel();
		panel_15.add(panel_5, BorderLayout.SOUTH);
		
		panel_9 = new JPanel();
		panel_5.add(panel_9);
		
		lblNewLabel = new JLabel("EQUIPO");
		panel_9.add(lblNewLabel);
		
		panel_7 = new JPanel();
		panel_5.add(panel_7);
		
		comboBox = new JComboBox(tabledata);
		panel_7.add(comboBox);
		
		
	
		panel1 = new JPanel();
		contentPane.add(panel1, BorderLayout.SOUTH);
		panel1.setLayout(new BorderLayout(0, 0));
	
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
		
			
			public void actionPerformed(ActionEvent e) {
				jornadaSeleccionada = 0;
				new menuApp().setVisible(true);
				dispose();
				}
		});
		panel1.add(btnVolver, BorderLayout.EAST);
	
		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new BorderLayout(0, 0));
	
		panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));
	

		
		panel_4 = new JPanel();
		panel_1.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));
	
		panel_3 = new JPanel();
		panel_4.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));

	
		panel_6 = new JPanel();
		panel_3.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new BorderLayout(0, 0));
	
		panel_8 = new JPanel();
		panel_6.add(panel_8, BorderLayout.CENTER);
		panel_8.setLayout(new BorderLayout(0, 0));
			
        // Crear Tabla
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		table = new JTable(new DefaultTableModel(
			new Object[][] {
				{"*Imagen1*", "nombre1"},
				{"*Imagen2*", "nombre2"},
				{"*Imagen3*", "nombre3"},
				{"*Imagen4*", "nombre4"},
				{"*Imagen5*", "nombre5"},
				{"*Imagen6*", "nombre6"},
				{"*Imagen7*", "nombre7"},
				{"*Imagen8*", "nombre8"},
				{"*Imagen9*", "nombre9"},
				{"*Imagen10*", "nombre10"},
				{"*Imagen11*", "nombre11"},
			},
			new String[] {
				"Imagen", "Nombre_Jugador"
			}
		));
		table.setEnabled(false);
		table.setFont(new Font("Calibri", Font.PLAIN, 14));
		table.setDefaultEditor(Object.class, null);
		scrollPane.setViewportView(table);
		table.setRowHeight(30);
		
		equipoApp.loadData();
		// Datos Por Defecto
		updateTable(); // Calcula Tabla Al iniciar
		cargarDatosJornada();
		loadShields();
	}
	private static void loadData() {
		// TODO Auto-generated method stub
		
	}

	private void updateTable() {
		// TODO Auto-generated method stub
		
	}

	private void loadShields() {
		// TODO Auto-generated method stub
		
	}

	private static void loadData(String temporadaActual) {
		
		
	}
	
	public static void updateTableData(String equipo, int Pts , int PJ ,   int PG , int PE,   int PP,   int GF,    int GC ) {
		//							   NOMBRE EQUIPO  PUNTOS    P JUGADOS  P GANA   P EMPATE  P PIERDE  GOL FAVOR  GOL CONTRA 
		
		
	}
	public static void cargarDatosJornada() {
	}
	
   
  
	final static String Temporada = "resources/datos/clasificacion.csv";
	private JPanel panel_5;
	private JPanel panel_7;
	private JPanel panel_9;
	private JLabel lblNewLabel;
	private JComboBox comboBox;

    
   
    
   
    
}