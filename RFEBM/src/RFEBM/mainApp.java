package RFEBM;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
 

	

public class mainApp  extends JFrame {
	
	//Crear arrays con las jornadas y los goles
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
		{"0", "0", "0"},
		{"20", "15", "31"},
		{"20", "10", "28"},
		{"18", "24", "20"},
		{"22", "14", "30"},
		{"28", "24", "31"},
		{"12", "35", "17"},
		{"24", "10", "31"},
		{"12", "19", "30"},
		{"14", "6",  "20"}
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
		{"0", "0", "0"},
		{"19", "12", "33"},
		{"35", "8",  "29"},
		{"16", "24", "22"},
		{"21", "39", "35"},
		{"32", "20", "30"},
		{"10", "37", "15"},
		{"25", "6",  "34"},
		{"11", "18", "31"},
		{"13", "4",  "23"}
	};
	//Crear array con los datos de la tabla
	public static String[] columnNames = {"Club", "Pts", "PJ", "PG","PE","PP","GF","GC","DG"};
    public static String[][] tableData = {
    		{"Madrid"   ,"0","0","0","0","0","0","0","0"},
    		{"Cáceres"  ,"0","0","0","0","0","0","0","0"},
    		{"Bilbao"   ,"0","0","0","0","0","0","0","0"},
    		{"Barcelona","0","0","0","0","0","0","0","0"},
    		{"Murcia"   ,"0","0","0","0","0","0","0","0"},
    		{"Sevilla"  ,"0","0","0","0","0","0","0","0"}

    };

	static int jornadaSeleccionada = 0;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel txtClasif; 
	private JPanel panel; JPanel panel1; JPanel panel4; JPanel panel5; JPanel panel_1; JPanel panel_2; JPanel panel_3; JPanel panel_4; JPanel panel_5; JPanel panel_6; JPanel panel_7; JPanel panel_8; JPanel panel_9; JPanel panel_10; JPanel panel_11; JPanel panel_12; JPanel panel_13; JPanel panel_14;
	private JButton btnAplicarCambios; JButton btnLogout; JButton btnAtras; JButton btnAlante;
	private JLabel JornadaTxt; JLabel lblNewLabel_1; JLabel lblNewLabel_2; static JLabel lblTextoCambios; JScrollPane scrollPane;
	private JComboBox <String> JornadaDesplegable;
	private static JTable table;
	private static JLabel EquipoLoc1; static JLabel EquipoLoc2; static JLabel EquipoLoc3;
	private static JLabel EquipoVis1; static JLabel EquipoVis2; static JLabel EquipoVis3;
	private static JTextField EquipoLocGol1; static JTextField EquipoLocGol2; static JTextField EquipoLocGol3;
	private static JTextField EquipoVisGol1; static JTextField EquipoVisGol2; static JTextField EquipoVisGol3;
	private JLabel VS1; JLabel VS2; JLabel VS3;

	
	
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				mainApp frame = new mainApp();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public mainApp() {
		if (inicioApp.rol==1) {
			setTitle("Menú usuario");
		} if (inicioApp.rol==2) {
			setTitle("Menú administrador");
		} if (inicioApp.rol==3) {
			setTitle("Menú entrenador"); // solo acceso a clase equipos
		} if (inicioApp.rol==4) {
			setTitle("Menú arbitro");
		}
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
		lblNewLabel_2.setIcon(new ImageIcon(mainApp.class.getResource("/resources/logo.png")));
		panel_11.add(lblNewLabel_2);
	
		panel1 = new JPanel();
		contentPane.add(panel1, BorderLayout.SOUTH);
		panel1.setLayout(new BorderLayout(0, 0));
	
		btnLogout = new JButton("Cerrar Sesión");
		btnLogout.addActionListener(new ActionListener() {
		
			
			public void actionPerformed(ActionEvent e) {
				jornadaSeleccionada = 0;
				int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea cerrar sesión?", "Cerrar sesión", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if (respuesta == JOptionPane.YES_OPTION) {
						new inicioApp().setVisible(true);
				dispose();
				}
			}
		});
		panel1.add(btnLogout, BorderLayout.EAST);
	
		btnAplicarCambios = new JButton("Aplicar Cambios");
		if (inicioApp.rol==1||inicioApp.rol==3) {
			btnAplicarCambios.setEnabled(false);
		}
		btnAplicarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblTextoCambios.setText("INFO : Cambios aplicados");
				limpiarValores();
				jornadasGolLoc[jornadaSeleccionada][0] = EquipoLocGol1.getText();
				jornadasGolLoc[jornadaSeleccionada][1] = EquipoLocGol2.getText();
				jornadasGolLoc[jornadaSeleccionada][2] = EquipoLocGol3.getText();
				jornadasGolVis[jornadaSeleccionada][0] = EquipoVisGol1.getText();
				jornadasGolVis[jornadaSeleccionada][1] = EquipoVisGol2.getText();
				jornadasGolVis[jornadaSeleccionada][2] = EquipoVisGol3.getText();
				updateTable();// Calcula Tabla Al Pulsar Boton

				

			}
		});
		panel1.add(btnAplicarCambios, BorderLayout.WEST);
	
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
			}
		});
		panel_2.add(JornadaDesplegable, BorderLayout.CENTER);
		
		panel_12 = new JPanel();
		panel_2.add(panel_12, BorderLayout.NORTH);
		
			JornadaTxt = new JLabel("Seleccione jornada");
			panel_12.add(JornadaTxt);
			JornadaTxt.setFont(new Font("Arial", Font.PLAIN, 13));
		
		btnAtras = new JButton("🡨");
		btnAtras.setVisible(false);
		
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jornadaSeleccionada --;
				JornadaDesplegable.setSelectedIndex(jornadaSeleccionada);
				cargarDatosJornada();
				lblTextoCambios.setText("INFO : Jornada " + (jornadaSeleccionada + 1) + " Seleccionada");
				
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
		
		if (inicioApp.rol==1 || inicioApp.rol==3) {
			EquipoLocGol1.setEditable(false);
			EquipoLocGol1.setEnabled(false);
		}
		if (inicioApp.rol==1 ||inicioApp.rol==3) {
			EquipoVisGol1.setEditable(false);
			EquipoVisGol1.setEnabled(false);
		}
		panel_9 = new JPanel();
		panel_8.add(panel_9, BorderLayout.CENTER);
		panel_9.setLayout(new BorderLayout(0, 0));
	
		panel_10 = new JPanel();
		panel_9.add(panel_10, BorderLayout.NORTH);
		panel_10.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
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
							EquipoVisGol2.addKeyListener(golesfilter);
			EquipoLocGol2.addKeyListener(golesfilter);
			if (inicioApp.rol==1 || inicioApp.rol==3) {
				EquipoLocGol2.setEditable(false);
				EquipoLocGol2.setEnabled(false);
			}
			if (inicioApp.rol==1 || inicioApp.rol==3) {
				EquipoVisGol2.setEditable(false);
				EquipoVisGol2.setEnabled(false);
			}
		
	
		lblTextoCambios = new JLabel("");
		lblTextoCambios.setFont(new Font("Calibri", Font.BOLD, 12));
		panel_9.add(lblTextoCambios, BorderLayout.SOUTH);

		panel_13 = new JPanel();
		panel_9.add(panel_13, BorderLayout.CENTER);
		panel_13.setLayout(new BorderLayout(0, 0));
		
		panel_14 = new JPanel();
		panel_13.add(panel_14, BorderLayout.NORTH);
		
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
							EquipoVisGol3.addKeyListener(golesfilter);
			EquipoLocGol3.addKeyListener(golesfilter);
			if (inicioApp.rol==1 || inicioApp.rol==3) {
				EquipoLocGol3.setEditable(false);
				EquipoLocGol3.setEnabled(false);
			}
			if (inicioApp.rol==1 || inicioApp.rol==3) {
				EquipoVisGol3.setEditable(false);
				EquipoVisGol3.setEnabled(false);
			}
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
		
		// Datos Por Defecto
		updateTable(); // Calcula Tabla Al iniciar
		cargarDatosJornada(); // Carga Datos Jornada 1
		lblTextoCambios.setText("INFO : Mostrando Jornada " + (jornadaSeleccionada + 1));
		

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
		
		// Resetea Valores De tableData
        for (int i = 0; i < tableData.length; i++) {
            for (int j = 1; j < tableData[i].length; j++) { 
                tableData[i][j] = "0";
            }
        }
	
        // Calcula Quien Gana y cuantos puntos tiene
		for (int i = 0; i <= 9; ++i) {
			for (int j = 0; j <= 2; ++j) {
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
		
        for (int j = 0; j < 6; ++j) {
        	if (tableData[j][0].equals(equipo)) {
        		tableData[j][1] = Integer.toString(Pts+ Integer.parseInt(tableData[j][1]));
        		tableData[j][2] = Integer.toString(PJ + Integer.parseInt(tableData[j][2]));
        		tableData[j][3] = Integer.toString(PG + Integer.parseInt(tableData[j][3]));
        		tableData[j][4] = Integer.toString(PE + Integer.parseInt(tableData[j][4]));
        		tableData[j][5] = Integer.toString(PP + Integer.parseInt(tableData[j][5]));
        		tableData[j][6] = Integer.toString(GF + Integer.parseInt(tableData[j][6]));
        		tableData[j][7] = Integer.toString(GC + Integer.parseInt(tableData[j][7]));
        		tableData[j][8] = Integer.toString((GF-GC) + Integer.parseInt(tableData[j][8]));;

        		
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
}