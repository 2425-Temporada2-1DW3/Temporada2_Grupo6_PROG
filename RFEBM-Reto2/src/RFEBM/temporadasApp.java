package RFEBM;

import Classes.añoTemporadaApp;
import Classes.EstadoTemporada;
import Classes.TemporadaApp;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class temporadasApp extends JFrame implements ActionListener,WindowListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel lblNewLabel;JLabel lblNewLabel_1;JLabel lblNombre;
	JPanel panel;JPanel panel_1;JPanel panel_2;JPanel panel_3;JPanel panel_4;JPanel panel_5;JPanel panel_6;JPanel panel_7;JPanel panel_8;JPanel panel_9;JPanel panel_10;JPanel panel_11;JPanel panel_12;JPanel panel_13;JPanel panel_14;JPanel panel_15;JPanel panel_16;JPanel panel_17;JPanel panel_18;JPanel panel_19;JPanel panel_20;JPanel panel_21;JPanel panel_22;JPanel panel_23;JPanel panel_24;JPanel panel_25;JPanel panel_26;JPanel panel_27;JPanel panel_28;
	JButton btnGClasificacion;
	JButton btnGEquipos;
	JButton btnGPartidos;
	JButton btnATemporada;
	private JTextField txtNombre;
	private JTextField txtAñofinal;
	private JLabel lblAñofinal;
	JList<TemporadaApp> lstTemporadas;
	DefaultListModel<TemporadaApp> dlm;
	private JLabel lblNewLabel_4;
	private JButton btnBorrar;
	private JButton btnGuardar;
	private JButton btnModificar;
	private JTextField txtAñoinicio;
	private JLabel lblAñoinicio;
	private JLabel lblNewLabel_6;
	private JButton btnIniciar;
	private JButton btnFinalizar;
	private JLabel lblTotalElementosValor;
	boolean cambiodatos = false;
	static temporadasApp frame;
	private JButton btnVolver;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new temporadasApp();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public temporadasApp() {
		setBounds(100, 100, 800, 500);
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);
		panel.setLayout(new BorderLayout(0, 0));
		
		panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.WEST);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		panel_20 = new JPanel();
		panel_3.add(panel_20, BorderLayout.CENTER);
		panel_20.setLayout(new BorderLayout(0, 0));
		
		panel_16 = new JPanel();
		panel_2.add(panel_16, BorderLayout.CENTER);
		panel_16.setLayout(new BorderLayout(0, 0));
		
		panel_10 = new JPanel();
		panel_16.add(panel_10, BorderLayout.EAST);
		panel_10.setLayout(new BorderLayout(0, 0));
		
		panel_21 = new JPanel();
		panel_10.add(panel_21, BorderLayout.CENTER);
		panel_21.setLayout(new BorderLayout(0, 0));
		
		panel_19 = new JPanel();
		panel_16.add(panel_19, BorderLayout.CENTER);
		panel_19.setLayout(new BorderLayout(0, 0));
		
		panel_22 = new JPanel();
		panel_19.add(panel_22, BorderLayout.SOUTH);
		
		btnIniciar = new JButton("Iniciar Temporada");
		panel_22.add(btnIniciar);
		btnIniciar.addActionListener(this);
		
		panel_18 = new JPanel();
		panel_22.add(panel_18);
		
		lblNewLabel_1 = new JLabel("");
		panel_18.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(GestionApp.class.getResource("/resources/logo.png")));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		btnGuardar = new JButton("Añadir");
		panel_18.add(btnGuardar);
		btnGuardar.addActionListener(this);
		
		btnBorrar = new JButton("Eliminar");
		panel_18.add(btnBorrar);
		btnBorrar.addActionListener(this);
		
		btnModificar = new JButton("Modificar");
		panel_18.add(btnModificar);
		btnModificar.addActionListener(this);
		
		lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(temporadasApp.class.getResource("/resources/logo.png")));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_18.add(lblNewLabel_6);
		
		btnFinalizar = new JButton("Finalizar Temporada");
		panel_22.add(btnFinalizar);
		btnFinalizar.addActionListener(this);
		
		panel_6 = new JPanel();
		panel.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		panel_7 = new JPanel();
		panel_6.add(panel_7, BorderLayout.NORTH);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		panel_8 = new JPanel();
		panel_7.add(panel_8, BorderLayout.CENTER);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		panel_9 = new JPanel();
		panel_8.add(panel_9, BorderLayout.CENTER);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		panel_17 = new JPanel();
		panel_9.add(panel_17, BorderLayout.CENTER);
		panel_17.setLayout(new BorderLayout(0, 0));
		
		panel_11 = new JPanel();
		panel_6.add(panel_11, BorderLayout.CENTER);
		panel_11.setLayout(new BorderLayout(0, 0));
		
		
		panel_12 = new JPanel();
		panel_11.add(panel_12, BorderLayout.SOUTH);
		panel_12.setLayout(new BorderLayout(0, 0));
		
		panel_26 = new JPanel();
		panel_12.add(panel_26, BorderLayout.CENTER);
		panel_26.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel_4 = new JLabel("Total temporadas:");
		panel_26.add(lblNewLabel_4, BorderLayout.WEST);
		
		lblTotalElementosValor = new JLabel("0");
		panel_26.add(lblTotalElementosValor, BorderLayout.CENTER);
		
		btnVolver = new JButton("Volver");
		panel_12.add(btnVolver, BorderLayout.WEST);
		btnVolver.addActionListener(this);
		
		panel_28 = new JPanel();
		panel_12.add(panel_28, BorderLayout.NORTH);
		
		panel_27 = new JPanel();
		panel_12.add(panel_27, BorderLayout.SOUTH);
		
		panel_13 = new JPanel();
		panel_11.add(panel_13, BorderLayout.WEST);
		
		panel_14 = new JPanel();
		panel_11.add(panel_14, BorderLayout.EAST);
		
		panel_15 = new JPanel();
		panel_11.add(panel_15, BorderLayout.NORTH);
		panel_15.setLayout(new BorderLayout(0, 0));
		
		panel_23 = new JPanel();
		panel_15.add(panel_23, BorderLayout.WEST);
		
		lblNombre = new JLabel("Nombre");
		panel_23.add(lblNombre);
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		txtNombre = new JTextField();
		panel_23.add(txtNombre);
		txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtNombre.setColumns(10);
		
		panel_24 = new JPanel();
		panel_15.add(panel_24, BorderLayout.EAST);
		
		lblAñofinal = new JLabel("Año Fin");
		panel_24.add(lblAñofinal);
		lblAñofinal.setHorizontalAlignment(SwingConstants.CENTER);
		lblAñofinal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		txtAñofinal = new JTextField();
		panel_24.add(txtAñofinal);
		txtAñofinal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtAñofinal.setColumns(10);
		
		panel_5 = new JPanel();
		panel_15.add(panel_5, BorderLayout.CENTER);
		
		lblAñoinicio = new JLabel("Año Inicio");
		panel_5.add(lblAñoinicio);
		lblAñoinicio.setHorizontalAlignment(SwingConstants.CENTER);
		lblAñoinicio.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		txtAñoinicio = new JTextField();
		panel_5.add(txtAñoinicio);
		txtAñoinicio.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtAñoinicio.setColumns(10);
		
		panel_4 = new JPanel();
		panel_11.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));
		
	
		panel_25 = new JPanel();
		panel_4.add(panel_25, BorderLayout.NORTH);
		
		dlm = new DefaultListModel<TemporadaApp>();
		lstTemporadas = new JList<TemporadaApp>();
		lstTemporadas.setModel(dlm);
		JScrollPane scrollPane = new JScrollPane(lstTemporadas);
		panel_4.add(scrollPane, BorderLayout.CENTER);
	}
	
	private int calcularTotalTemporadas() {
	    int total = dlm.size(); 
	    return total; 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	Object o = e.getSource();

	if (o == btnGuardar){
		 String nombre = txtNombre.getText();
         String añoInicioP = txtAñoinicio.getText();
         String añoFinP = txtAñofinal.getText(); 
         
         
         
         if(añoInicioP.isEmpty() || añoFinP.isEmpty() || nombre.isEmpty() ) {
        	JOptionPane.showMessageDialog(this, "Los campos de texto no pueden estar vacios", "Error", JOptionPane.ERROR_MESSAGE);
     		return; 
         } else {
         int añoInicio = Integer.parseInt(añoInicioP);
         int añoFin = Integer.parseInt(añoFinP);
         
         // Crear la instancia de AñoTemporadaApp y Temporada
         añoTemporadaApp añoTemporada = new añoTemporadaApp(añoInicio, añoFin);
         TemporadaApp temporada = new TemporadaApp(nombre, añoTemporada);
         dlm.addElement(temporada);
         cambiodatos = true;
         }
	}
	if (o == btnBorrar){
		boolean ElementoBorrado = false;
		int[]Seleccionados = lstTemporadas.getSelectedIndices();
		for(int i = Seleccionados.length - 1;i>=0; i--) {
			dlm.removeElementAt(Seleccionados[i]);
			ElementoBorrado = true;
			cambiodatos = true;
	}
	if (ElementoBorrado == false) {
		JOptionPane.showMessageDialog(this, "No se esta borrando nada .", "Error", JOptionPane.ERROR_MESSAGE);
		return;
		}
		lblTotalElementosValor.setText("" + calcularTotalTemporadas());
		}
	
	if (o == btnVolver){
		int respuesta = JOptionPane.showConfirmDialog(
                frame, 
                "Seguro que quieres salir?", 
                "Salida", 
                JOptionPane.YES_NO_CANCEL_OPTION);
		if (respuesta == JOptionPane.YES_OPTION) {
			 guardarDatos();
			 System.exit(0);
		}
		if (respuesta == JOptionPane.NO_OPTION) {
			 System.exit(0);
		}
	}
	
	if (o == btnIniciar) {
		int seleccion = lstTemporadas.getSelectedIndex();
		if (seleccion == -1) {
	        JOptionPane.showMessageDialog(this, "Por favor, selecciona una temporada.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
		
		TemporadaApp temporadaSeleccionada = dlm.get(seleccion);
		EstadoTemporada estadoActual = temporadaSeleccionada.getEstado();
		
		for (int i = 0; i < dlm.size(); i++) {
	        if (dlm.get(i).getEstado() == EstadoTemporada.Iniciada) {
	            JOptionPane.showMessageDialog(this, "Ya existe una temporada iniciada. Finaliza la temporada actual antes de iniciar una nueva.", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	    }
		
		 switch (estadoActual) {
	        case Sin_Iniciar:
	            temporadaSeleccionada.setEstado(EstadoTemporada.Iniciada); 
	            dlm.set(seleccion, temporadaSeleccionada);                
	            JOptionPane.showMessageDialog(this, "La temporada ha sido iniciada.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	            break;

	        case Iniciada:
	        case Finalizada:
	            JOptionPane.showMessageDialog(this, "No se puede iniciar una temporada que ya está " + estadoActual + ".", "Error", JOptionPane.ERROR_MESSAGE);
	            break;

	        default:
	            JOptionPane.showMessageDialog(this, "Estado desconocido para la temporada seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
	            break;
	    }
	}
}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		try {
			cargarDatos();
			
			if (dlm.size() > 0) {
				cambiodatos = true;
				
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			
		}
	}
	

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		if (cambiodatos) {
			int respuesta = JOptionPane.showConfirmDialog(
                    frame, 
                    "Seguro que quieres salir?", 
                    "Salida", 
                    JOptionPane.YES_NO_CANCEL_OPTION);
			if (respuesta == JOptionPane.YES_OPTION) {
				 guardarDatos();
				 System.exit(0);
			}
			if (respuesta == JOptionPane.NO_OPTION) {
				 System.exit(0);
			}
		}
	}
	

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	//CARGAR LISTA POR ELEMENTOS //
	private void guardarDatos() {
		 try (FileOutputStream fos = new FileOutputStream("temporadas.ser");
			  ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			 
			 for(int i = 0; i < dlm.size(); i++) {
				 TemporadaApp r = dlm.get(i);
				 oos.writeObject(r); 
			 }
				     JOptionPane.showMessageDialog(this, "Temporadas guardadas en " + "temporadas.ser", "Finalizado", JOptionPane.INFORMATION_MESSAGE);
				 } catch (IOException ae) {
				     JOptionPane.showMessageDialog(this, "Error en el guardado de datos:" + ae.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				 }
			 System.exit(0);
	}

	
	private void cargarDatos() throws IOException {
		 try (FileInputStream fis = new FileInputStream("temporadas.ser");
				  ObjectInputStream ois = new ObjectInputStream(fis)) {
			 TemporadaApp valor = null;
			 while (fis.available() > 0) {
			 valor = (TemporadaApp)ois.readObject();
					 this.dlm.addElement(valor);
		    } 
			 lblTotalElementosValor.setText("" + calcularTotalTemporadas());
			 JOptionPane.showMessageDialog(this, "Temporadas cargadas correctamente", "Carga completada", JOptionPane.INFORMATION_MESSAGE);
			 fis.close();
			 ois.close();
		 }catch (IOException | ClassNotFoundException e) {
		        System.err.println("Error cargando datos: " + e.getMessage());
		    }
		}
}