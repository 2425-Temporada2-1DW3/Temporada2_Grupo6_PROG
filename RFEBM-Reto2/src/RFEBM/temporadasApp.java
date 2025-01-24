package RFEBM;

import Classes.añoTemporadaApp;
import log.log;
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

import org.apache.log4j.Logger;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
	Logger LOG = log.getLogger(temporadasApp.class);
	String nombre;
	
	
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
		lblNewLabel_1.setIcon(new ImageIcon(GestionApp.class.getResource("/images/logos/logo.png")));
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
		lblNewLabel_6.setIcon(new ImageIcon(temporadasApp.class.getResource("/images/logos/logo.png")));
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
		panel_26.add(btnVolver, BorderLayout.EAST);
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
		lstTemporadas.addListSelectionListener(e -> {
		    if (!e.getValueIsAdjusting()) { // Verificamos que la selección haya terminado
		        int[] seleccion = lstTemporadas.getSelectedIndices();
		        if (seleccion.length > 1) {
		        	txtAñoinicio.setText("");
		        	txtAñofinal.setText("");
		        	txtNombre.setText("");
		        }
		        else if (seleccion.length == 1){
		            // Obtener el objeto TemporadaApp de la lista
		            TemporadaApp temporadaSeleccionada = dlm.get(seleccion[0]);

		            // Poner los datos en los campos de texto
		            txtNombre.setText(temporadaSeleccionada.getNombre());
		            txtAñoinicio.setText(String.valueOf(temporadaSeleccionada.getAñoTemporada().getAñoInicio()));
		            txtAñofinal.setText(String.valueOf(temporadaSeleccionada.getAñoTemporada().getAñoFinal()));
		        }
		    }
		});
		
	}
	
	private int calcularTotalTemporadas() {
	    int total = dlm.size(); 
	    return total; 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	Object o = e.getSource();

	if (o == btnGuardar){
		 	nombre = txtNombre.getText();
		    // Obtén los valores de año de inicio y fin de los campos de texto
		    int añoInicio = Integer.parseInt(txtAñoinicio.getText());
		    int añoFin = Integer.parseInt(txtAñofinal.getText());

		    // Crear la nueva temporada con los valores obtenidos
		    if (añoInicio < añoFin) {
		    	
		    }
		    añoTemporadaApp nuevaTemporada = new añoTemporadaApp(añoInicio, añoFin);
		    
		    GenPartidos generador = new GenPartidos();
	        List<List<String[]>> partidos = generador.generarPartidos();
	        
	        // Guardar los partidos en CSV
	        String archivo = "resources/datos/Jornada" + nombre + ".csv";
	        guardarPartidosEnCSV(partidos, archivo);

		    // Verificar si ya existe una temporada con los mismos años
		    for (int i = 0; i < dlm.size(); i++) {
		        TemporadaApp temporadaExistente = dlm.get(i);
		        
		        // Obtener el objeto AñoTemporadaApp de la temporada existente
		        añoTemporadaApp temporadaAñosExistente = temporadaExistente.getAñoTemporada();
		        
		        // Compara usando el método equals
		        if (nuevaTemporada.equals(temporadaAñosExistente)) {
		            JOptionPane.showMessageDialog(this, "Ya existe una temporada con el mismo año de inicio y fin.", "Error", JOptionPane.ERROR_MESSAGE);
		            return;  // Salir del método sin añadir la nueva temporada
		        }
		    }

		    // Si no hay conflicto, añadir la nueva temporada a la lista
		     añoTemporadaApp añoTemporada = new añoTemporadaApp(añoInicio, añoFin);
	         TemporadaApp temporada = new TemporadaApp(nombre, añoTemporada);
	         dlm.addElement(temporada);
	         LOG.info("Info: Una temporada ha sido añadido.");
	         cambiodatos = true;
		    JOptionPane.showMessageDialog(this, "La temporada ha sido añadida correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		}
	if (o == btnBorrar){
		boolean ElementoBorrado = false;
		int[]Seleccionados = lstTemporadas.getSelectedIndices();
		for(int i = Seleccionados.length - 1;i>=0; i--) {
			dlm.removeElementAt(Seleccionados[i]);
			ElementoBorrado = true;
			cambiodatos = true;
			LOG.warn("Warn: Una temporada ha sido eliminada.");
	}
	if (ElementoBorrado == false) {
		JOptionPane.showMessageDialog(this, "No se esta borrando nada .", "Error", JOptionPane.ERROR_MESSAGE);
		return;
		}
		lblTotalElementosValor.setText("" + calcularTotalTemporadas());
		}
	if (o == btnModificar) {
		if (o == btnModificar) {
		    int seleccion = lstTemporadas.getSelectedIndex();
		    if (seleccion == -1) {
		        JOptionPane.showMessageDialog(this, "Por favor, selecciona una temporada para modificar.", "Error", JOptionPane.ERROR_MESSAGE);
		        return;
		    }

		    // Obtener los valores de los campos de texto
		    String nuevoNombre = txtNombre.getText();
		    String nuevoAñoInicioStr = txtAñoinicio.getText();
		    String nuevoAñoFinStr = txtAñofinal.getText();

		    // Validar que los campos no estén vacíos
		    if (nuevoNombre.isEmpty() || nuevoAñoInicioStr.isEmpty() || nuevoAñoFinStr.isEmpty()) {
		        JOptionPane.showMessageDialog(this, "Los campos no pueden estar vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
		        return;
		    }

		    try {
		        int nuevoAñoInicio = Integer.parseInt(nuevoAñoInicioStr);
		        int nuevoAñoFin = Integer.parseInt(nuevoAñoFinStr);

		        // Obtener la temporada seleccionada
		        TemporadaApp temporadaSeleccionada = dlm.get(seleccion);
		        // Crear un nuevo objeto añoTemporadaApp con los nuevos valores
		        añoTemporadaApp nuevoAñoTemporada = new añoTemporadaApp(nuevoAñoInicio, nuevoAñoFin);
		        // Actualizar los valores en el objeto TemporadaApp
		        temporadaSeleccionada.setNombre(nuevoNombre);
		        temporadaSeleccionada.setAñoTemporada(nuevoAñoTemporada);

		        // Actualizar la lista
		        dlm.set(seleccion, temporadaSeleccionada);
		        LOG.info("Info: Una temporada ha sido modificada.");

		        JOptionPane.showMessageDialog(this, "Temporada modificada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		    } catch (NumberFormatException ex) {
		        JOptionPane.showMessageDialog(this, "Los campos de año deben ser números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
		    }
		}
	}
	
	if (o == btnVolver){
		int respuesta = JOptionPane.showConfirmDialog(
                frame, 
                "Seguro que quieres salir?", 
                "Salida", 
                JOptionPane.YES_NO_CANCEL_OPTION);
		if (respuesta == JOptionPane.YES_OPTION) {
			 guardarDatos();
			 new GestionApp().setVisible(true);
				dispose();
		}
		if (respuesta == JOptionPane.NO_OPTION) {
			 new GestionApp().setVisible(true);
			 dispose();
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
	        }else if (dlm.get(i).getEstado() == EstadoTemporada.Finalizada) {
	        	JOptionPane.showMessageDialog(this, "La temporada se encuentra finalizada.", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	    }
		
		 switch (estadoActual) {
		 case Sin_Iniciar:
	            // Primer mensaje de confirmación
	            int respuesta1 = JOptionPane.showConfirmDialog(
	                this,
	                "¿Estás seguro de que deseas iniciar esta temporada?",
	                "Confirmación",
	                JOptionPane.YES_NO_OPTION
	            );

	            if (respuesta1 == JOptionPane.YES_OPTION) {
	                // Segunda confirmación
	                int respuesta2 = JOptionPane.showConfirmDialog(
	                    this,
	                    "¡Atención! Al iniciar la temporada, no podrás deshacer esta acción. ¿Estás absolutamente seguro?",
	                    "Confirmación Final",
	                    JOptionPane.YES_NO_OPTION
	                );

	                if (respuesta2 == JOptionPane.YES_OPTION) {
	                    temporadaSeleccionada.setEstado(EstadoTemporada.Iniciada); 
	                    dlm.set(seleccion, temporadaSeleccionada);                
	                    JOptionPane.showMessageDialog(this, "La temporada ha sido iniciada.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	                } else {
	                    JOptionPane.showMessageDialog(this, "La temporada no ha sido iniciada.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
	                }
	            } else {
	                JOptionPane.showMessageDialog(this, "La temporada no ha sido iniciada.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
	            }
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
	if (o == btnFinalizar) {
	    int seleccion = lstTemporadas.getSelectedIndex();
	    if (seleccion == -1) {
	        JOptionPane.showMessageDialog(this, "Por favor, selecciona una temporada.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    TemporadaApp temporadaSeleccionada = dlm.get(seleccion);
	    EstadoTemporada estadoActual = temporadaSeleccionada.getEstado();

	    // Verificar si la temporada está en estado Finalizada
	    if (estadoActual == EstadoTemporada.Finalizada) {
	        JOptionPane.showMessageDialog(this, "La temporada ya está finalizada.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    // Verificar si la temporada está en estado Sin Iniciar
	    if (estadoActual == EstadoTemporada.Sin_Iniciar) {
	        JOptionPane.showMessageDialog(this, "Para poder finalizar la temporada, debe estar iniciada.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    // Si está en estado Iniciada, pedir confirmación
	    if (estadoActual == EstadoTemporada.Iniciada) {
	        // Primer mensaje de confirmación
	        int respuesta1 = JOptionPane.showConfirmDialog(
	            this,
	            "¿Estás seguro de que deseas finalizar esta temporada?",
	            "Confirmación",
	            JOptionPane.YES_NO_OPTION
	        );

	        if (respuesta1 == JOptionPane.YES_OPTION) {
	            // Segunda confirmación
	            int respuesta2 = JOptionPane.showConfirmDialog(
	                this,
	                "¡Atención! Al finalizar la temporada, no podrás deshacer esta acción. ¿Estás absolutamente seguro?",
	                "Confirmación Final",
	                JOptionPane.YES_NO_OPTION
	            );

	            if (respuesta2 == JOptionPane.YES_OPTION) {
	                // Cambiar el estado a Finalizada
	                temporadaSeleccionada.setEstado(EstadoTemporada.Finalizada);
	                dlm.set(seleccion, temporadaSeleccionada);                
	                JOptionPane.showMessageDialog(this, "La temporada ha sido finalizada.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	            } else {
	                JOptionPane.showMessageDialog(this, "La temporada no ha sido finalizada.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
	            }
	        } else {
	            JOptionPane.showMessageDialog(this, "La temporada no ha sido finalizada.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
	        }
	    }
	}
}

	@Override
	public void windowOpened(WindowEvent e) {
		try {
			cargarDatos();
			
			if (dlm.size() > 0) {
				cambiodatos = true;
				
			}
		} catch (IOException e1) {
			
		}
	}
	

	@Override
	public void windowClosing(WindowEvent e) {
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
	    try (FileOutputStream fos = new FileOutputStream("resources/datos/temporadas.ser");
	         ObjectOutputStream oos = new ObjectOutputStream(fos)) {

	        // Guardar todas las temporadas completas (el objeto completo)
	        for (int i = 0; i < dlm.size(); i++) {
	            TemporadaApp t = dlm.get(i);
	            oos.writeObject(t); // Guardamos el objeto completo
	        }

	        JOptionPane.showMessageDialog(this, "Temporadas guardadas en " + "temporadas.ser", "Finalizado", JOptionPane.INFORMATION_MESSAGE);
	    } catch (IOException ae) {
	        JOptionPane.showMessageDialog(this, "Error en el guardado de datos:" + ae.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}

	
	private void cargarDatos() throws IOException {
		 try (FileInputStream fis = new FileInputStream("resources/datos/temporadas.ser");
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
	public class GenPartidos {

	    public List<List<String[]>> generarPartidos() {
	        // Definimos las jornadas predeterminadas (cada jornada con 3 partidos)
	        List<String[]> jornada1 = new ArrayList<>();
	        jornada1.add(new String[]{"Barcelona", "Bilbao"});
	        jornada1.add(new String[]{"Madrid", "Murcia"});
	        jornada1.add(new String[]{"Sevilla", "Cáceres"});

	        List<String[]> jornada2 = new ArrayList<>();
	        jornada2.add(new String[]{"Barcelona", "Madrid"});
	        jornada2.add(new String[]{"Bilbao", "Sevilla"});
	        jornada2.add(new String[]{"Murcia", "Cáceres"});

	        List<String[]> jornada3 = new ArrayList<>();
	        jornada3.add(new String[]{"Barcelona", "Murcia"});
	        jornada3.add(new String[]{"Bilbao", "Cáceres"});
	        jornada3.add(new String[]{"Madrid", "Sevilla"});

	        List<String[]> jornada4 = new ArrayList<>();
	        jornada4.add(new String[]{"Barcelona", "Sevilla"});
	        jornada4.add(new String[]{"Bilbao", "Murcia"});
	        jornada4.add(new String[]{"Madrid", "Cáceres"});

	        List<String[]> jornada5 = new ArrayList<>();
	        jornada5.add(new String[]{"Barcelona", "Cáceres"});
	        jornada5.add(new String[]{"Bilbao", "Madrid"});
	        jornada5.add(new String[]{"Murcia", "Sevilla"});

	        // Creamos una lista con todas las jornadas
	        List<List<String[]>> jornadas = new ArrayList<>();
	        jornadas.add(jornada1);
	        jornadas.add(jornada2);
	        jornadas.add(jornada3);
	        jornadas.add(jornada4);
	        jornadas.add(jornada5);

	        // Desordenamos el orden de las jornadas (pero no los partidos dentro de cada jornada)
	        Collections.shuffle(jornadas);

	        // Crear las jornadas de vuelta (invertir los partidos de cada jornada)
	        List<List<String[]>> todasLasJornadas = new ArrayList<>();

	        // Añadir las jornadas de ida
	        todasLasJornadas.addAll(jornadas);

	        // Crear las jornadas de vuelta invirtiendo los partidos de ida
	        List<List<String[]>> jornadasVuelta = new ArrayList<>();
	        for (List<String[]> jornada : jornadas) {
	            List<String[]> jornadaVuelta = new ArrayList<>();
	            for (String[] partido : jornada) {
	                jornadaVuelta.add(new String[]{partido[1], partido[0]}); // Invertir el enfrentamiento
	            }
	            jornadasVuelta.add(jornadaVuelta);
	        }

	        // Añadir las jornadas de vuelta
	        todasLasJornadas.addAll(jornadasVuelta);

	        return todasLasJornadas; // Retornamos todas las jornadas (ida y vuelta)
	    } 
	}
	
	String archivo = "resources/datos/Jornada"+nombre+".csv";
	public static void guardarPartidosEnCSV(List<List<String[]>> partidos, String archivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (List<String[]> jornada : partidos) {
                for (String[] partido : jornada) {
                   
                    int golesEquipo1 = 0;  
                    int golesEquipo2 = 0;  
                    
                    // Escribir los partidos en el formato "Equipo1,Equipo2,GolesEquipo1,GolesEquipo2"
                    writer.write(partido[0] + "," + partido[1] + "," + golesEquipo1 + "," + golesEquipo2);
                    writer.newLine();  // Nueva línea después de cada partido
                }
            }
            System.out.println("Los partidos se han guardado correctamente en el archivo CSV.");
        } catch (IOException e) {
            System.err.println("Error al guardar los partidos en el archivo CSV: " + e.getMessage());
        }
    }
}
	