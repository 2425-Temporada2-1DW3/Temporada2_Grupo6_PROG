package RFEBM;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classes.RolApp;
import Classes.UsuarioApp;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class GUsuarioApp extends JFrame implements ActionListener,WindowListener{

    private static final long serialVersionUID = 1L;

    // Variables de la interfaz
    private JPanel contentPane, panel, panel_1, panel_6, panel_2, panel_3, panel_5, panel_4, panel_7, panel_8, panel_9, panel_10, panel_11, panel_12, panel_13, panel_14, panel_15, panel_16, panel_17, panel_18, panel_19, panel_20, panel_21, panel_22, panel_23, panel_24, panel_25, panel_26, panel_27, panel_28, panel_29, panel_30, panel_31, panel_32, panel_33, panel_34;
    private JTextField txtNombre, txtContraseña;
    private JComboBox<RolApp> ComboRol;
    private JButton btnGuardar, btnEliminar, btnModificar, btnVolver;
    private JList<UsuarioApp> lstUsuarios;
    private DefaultListModel<UsuarioApp> userModel;
    boolean cambiodatos;
    static temporadasApp frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUsuarioApp frame = new GUsuarioApp();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public GUsuarioApp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);

        // Inicialización de paneles
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setContentPane(contentPane);
		addWindowListener(this);
        contentPane.setLayout(new BorderLayout(0, 0));
        

        panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout(0, 0));

        panel_1 = new JPanel();
        panel.add(panel_1, BorderLayout.NORTH);
        panel_1.setLayout(new BorderLayout(0, 0));

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(GUsuarioApp.class.getResource("/images/logos/logo.png")));
        panel_1.add(lblNewLabel, BorderLayout.WEST);

        JLabel lblNewLabel_1 = new JLabel("Gestion Usuarios");
        lblNewLabel_1.setFont(new Font("Britannic Bold", Font.BOLD, 38));
        panel_1.add(lblNewLabel_1, BorderLayout.CENTER);

        panel_6 = new JPanel();
        panel_1.add(panel_6, BorderLayout.SOUTH);

        panel_2 = new JPanel();
        panel.add(panel_2, BorderLayout.CENTER);
        panel_2.setLayout(new BorderLayout(0, 0));

        panel_3 = new JPanel();
        panel_2.add(panel_3, BorderLayout.NORTH);
        panel_3.setLayout(new BorderLayout(0, 0));

        JLabel lblUsuario = new JLabel("Nombre de usuario");
        lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
        panel_3.add(lblUsuario, BorderLayout.WEST);

        panel_5 = new JPanel();
        panel_3.add(panel_5, BorderLayout.CENTER);
        panel_5.setLayout(new BorderLayout(0, 0));

        panel_4 = new JPanel();
        panel_5.add(panel_4);
        panel_4.setLayout(new BorderLayout(0, 0));

        panel_7 = new JPanel();
        panel_4.add(panel_7, BorderLayout.WEST);

        panel_8 = new JPanel();
        panel_4.add(panel_8, BorderLayout.CENTER);
        panel_8.setLayout(new BorderLayout(0, 0));

        txtNombre = new JTextField();
        panel_8.add(txtNombre, BorderLayout.WEST);
        txtNombre.setColumns(10);

        panel_9 = new JPanel();
        panel_2.add(panel_9, BorderLayout.CENTER);
        panel_9.setLayout(new BorderLayout(0, 0));

        panel_10 = new JPanel();
        panel_9.add(panel_10, BorderLayout.NORTH);
        panel_10.setLayout(new BorderLayout(0, 0));

        JLabel lblContraseña = new JLabel("Contraseña");
        lblContraseña.setFont(new Font("Tahoma", Font.PLAIN, 16));
        panel_10.add(lblContraseña, BorderLayout.WEST);

        panel_11 = new JPanel();
        panel_10.add(panel_11, BorderLayout.NORTH);

        panel_12 = new JPanel();
        panel_10.add(panel_12, BorderLayout.CENTER);
        panel_12.setLayout(new BorderLayout(0, 0));

        panel_13 = new JPanel();
        panel_12.add(panel_13, BorderLayout.WEST);

        panel_14 = new JPanel();
        panel_12.add(panel_14, BorderLayout.CENTER);
        panel_14.setLayout(new BorderLayout(0, 0));

        txtContraseña = new JTextField();
        panel_14.add(txtContraseña, BorderLayout.WEST);
        txtContraseña.setColumns(10);

        panel_15 = new JPanel();
        panel_9.add(panel_15, BorderLayout.CENTER);
        panel_15.setLayout(new BorderLayout(0, 0));

        panel_16 = new JPanel();
        panel_15.add(panel_16, BorderLayout.NORTH);
        panel_16.setLayout(new BorderLayout(0, 0));

        ComboRol = new JComboBox<>(RolApp.values());
        panel_16.add(ComboRol, BorderLayout.WEST);


        panel_17 = new JPanel();
        panel_16.add(panel_17, BorderLayout.NORTH);

        panel_18 = new JPanel();
        panel_15.add(panel_18, BorderLayout.CENTER);
        panel_18.setLayout(new BorderLayout(0, 0));

        panel_19 = new JPanel();
        panel_18.add(panel_19, BorderLayout.NORTH);
        panel_19.setLayout(new BorderLayout(0, 0));

        btnGuardar = new JButton("Guardar");
        panel_19.add(btnGuardar, BorderLayout.WEST);
        btnGuardar.addActionListener(this);

        panel_20 = new JPanel();
        panel_19.add(panel_20, BorderLayout.NORTH);

        panel_21 = new JPanel();
        panel_19.add(panel_21, BorderLayout.CENTER);
        panel_21.setLayout(new BorderLayout(0, 0));

        panel_22 = new JPanel();
        panel_21.add(panel_22, BorderLayout.WEST);

        panel_23 = new JPanel();
        panel_21.add(panel_23, BorderLayout.CENTER);
        panel_23.setLayout(new BorderLayout(0, 0));

        btnEliminar = new JButton("Eliminar");
        panel_23.add(btnEliminar, BorderLayout.WEST);
        btnEliminar.addActionListener(this);

        panel_24 = new JPanel();
        panel_23.add(panel_24, BorderLayout.CENTER);
        panel_24.setLayout(new BorderLayout(0, 0));

        panel_25 = new JPanel();
        panel_24.add(panel_25, BorderLayout.WEST);

        panel_26 = new JPanel();
        panel_24.add(panel_26, BorderLayout.CENTER);
        panel_26.setLayout(new BorderLayout(0, 0));

        btnModificar = new JButton("Modificar");
        panel_26.add(btnModificar, BorderLayout.WEST);
        btnModificar.addActionListener(this);

        panel_28 = new JPanel();
        panel_19.add(panel_28, BorderLayout.SOUTH);

        panel_27 = new JPanel();
        panel_18.add(panel_27, BorderLayout.CENTER);
        panel_27.setLayout(new BorderLayout(0, 0));

        userModel = new DefaultListModel<>();
        lstUsuarios = new JList<>(userModel);
        panel_27.add(lstUsuarios, BorderLayout.CENTER);

        panel_29 = new JPanel();
        panel_27.add(panel_29, BorderLayout.WEST);

        panel_30 = new JPanel();
        panel_27.add(panel_30, BorderLayout.EAST);

        panel_31 = new JPanel();
        panel_27.add(panel_31, BorderLayout.SOUTH);
        panel_31.setLayout(new BorderLayout(0, 0));

        panel_32 = new JPanel();
        panel_31.add(panel_32, BorderLayout.WEST);

        panel_33 = new JPanel();
        panel_31.add(panel_33, BorderLayout.CENTER);
        panel_33.setLayout(new BorderLayout(0, 0));

        btnVolver = new JButton("Volver");
        panel_33.add(btnVolver, BorderLayout.WEST);
        btnVolver.addActionListener(this);


        panel_34 = new JPanel();
        panel_33.add(panel_34, BorderLayout.NORTH);
    }
    public void actionPerformed(ActionEvent e) {
    	Object o = e.getSource();

    	if (o == btnGuardar){
            String nombre = txtNombre.getText();
            String contraseña = txtContraseña.getText();
            RolApp rolSeleccionado = (RolApp) ComboRol.getSelectedItem();

            // Validar campos
            if (nombre.isEmpty() || contraseña.isEmpty() || rolSeleccionado == null) {
                JOptionPane.showMessageDialog(this, "Por favor, rellene todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Crear nuevo objeto UsuarioApp
            UsuarioApp nuevoUsuario = new UsuarioApp(nombre, contraseña, rolSeleccionado);

            // Verificar si el usuario ya existe en el modelo
            for (int i = 0; i < userModel.size(); i++) {
                UsuarioApp usuarioExistente = userModel.get(i);
                if (usuarioExistente.getNombreUsuario().equals(nombre)) {
                    JOptionPane.showMessageDialog(this, "El usuario ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Añadir el usuario al modelo y limpiar los campos
            userModel.addElement(nuevoUsuario);
            JOptionPane.showMessageDialog(this, "Usuario añadido correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            txtNombre.setText("");
            txtContraseña.setText("");
            ComboRol.setSelectedIndex(0); // Reiniciar selección
            cambiodatos = true;
            ordenarUsuariosPorRol();
        }
    	if (o == btnEliminar){
    		UsuarioApp usuarioSeleccionado = lstUsuarios.getSelectedValue();
    		if (usuarioSeleccionado.getRol() == RolApp.SuperAdmin) {
    	        JOptionPane.showMessageDialog(this, "No se puede eliminar a un usuario con rol de SuperAdmin.", "Error", JOptionPane.ERROR_MESSAGE);
    	        return;
    	    }
    		boolean ElementoBorrado = false;
    		int[]Seleccionados = lstUsuarios.getSelectedIndices();
    		for(int i = Seleccionados.length - 1;i>=0; i--) {
    			userModel.removeElementAt(Seleccionados[i]);
    			ElementoBorrado = true;
    			cambiodatos = true;
    	}
    	if (ElementoBorrado == false) {
    		JOptionPane.showMessageDialog(this, "No se esta borrando nada .", "Error", JOptionPane.ERROR_MESSAGE);
    		return;
    		}
    	ordenarUsuariosPorRol();
    		}
    	if (o == btnModificar) {
    		if (o == btnModificar) {
    		    int seleccion = lstUsuarios.getSelectedIndex();
    		    if (seleccion == -1) {
    		        JOptionPane.showMessageDialog(this, "Por favor, selecciona una temporada para modificar.", "Error", JOptionPane.ERROR_MESSAGE);
    		        return;
    		    }

    		    // Obtener los valores de los campos de texto
    		    String nuevoNombre = txtNombre.getText();
    		    String contraseña = txtContraseña.getText();
                RolApp rolSeleccionado = (RolApp) ComboRol.getSelectedItem();

    		    // Validar que los campos no estén vacíos
                if (nuevoNombre.isEmpty() || contraseña.isEmpty() || rolSeleccionado == null) {
                    JOptionPane.showMessageDialog(this, "Por favor, rellene todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

    		    try {
    		        // Obtener la temporada seleccionada
    		        UsuarioApp usuarioSeleccionado = userModel.get(seleccion);
    		        // Crear un nuevo objeto añoTemporadaApp con los nuevos valores

    		        usuarioSeleccionado.setNombreUsuario(nuevoNombre);
    		        usuarioSeleccionado.setContraseña(contraseña);
    		        usuarioSeleccionado.setRol(rolSeleccionado);

    		        // Actualizar la lista
    		        userModel.set(seleccion, usuarioSeleccionado);
    		        ordenarUsuariosPorRol();

    		        JOptionPane.showMessageDialog(this, "Usuario modificado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    		    } catch (NumberFormatException ex) {
    		        JOptionPane.showMessageDialog(this, "Los campos de año deben ser números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
    		    }
    		    cambiodatos = true;
    		    
    		}
    	}
    	
    	if (o == btnVolver){
    		int respuesta = JOptionPane.showConfirmDialog(
    				this, 
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
}
 	@Override
	public void windowOpened(WindowEvent e) {
		try {
			cargarDatos();
			
			if (userModel.size() > 0) {
				cambiodatos = true;
				
			}
		} catch (IOException e1) {
			
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {
		if (cambiodatos) {
			int respuesta = JOptionPane.showConfirmDialog(
                    this, 
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
  		 try (FileOutputStream fos = new FileOutputStream("resources/datos/usuarios.ser");
  			  ObjectOutputStream oos = new ObjectOutputStream(fos)) {
  			 
  			 for(int i = 0; i < userModel.size(); i++) {
  				 UsuarioApp r = userModel.get(i);
  				 oos.writeObject(r); 
  			 }
  				     JOptionPane.showMessageDialog(this, "Usuarios guardados en " + "usuarios.ser", "Finalizado", JOptionPane.INFORMATION_MESSAGE);
  				 } catch (IOException ae) {
  				     JOptionPane.showMessageDialog(this, "Error en el guardado de datos:" + ae.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
  				 }
  	}

  	
  	private void cargarDatos() throws IOException {
  		 try (FileInputStream fis = new FileInputStream("resources/datos/usuarios.ser");
  				  ObjectInputStream ois = new ObjectInputStream(fis)) {
  			 UsuarioApp valor = null;
  			 while (fis.available() > 0) {
  			 valor = (UsuarioApp)ois.readObject();
  					 this.userModel.addElement(valor);
  		    } 
  			 ordenarUsuariosPorRol();
  			 JOptionPane.showMessageDialog(this, "Usuarios cargados correctamente", "Carga completada", JOptionPane.INFORMATION_MESSAGE);
  			 fis.close();
  			 ois.close();
  		 }catch (IOException | ClassNotFoundException e) {
  		        System.err.println("Error cargando datos: " + e.getMessage());
  		    }
  		}
  	public void ordenarUsuariosPorRol() {
  		ArrayList<UsuarioApp> listaUsuarios = new ArrayList<>();
		    for (int i = 0; i < userModel.size(); i++) {
		        listaUsuarios.add(userModel.get(i));
		    }

		    // Ordenar la lista por el valor ordinal de RolApp
		    Collections.sort(listaUsuarios, (u1, u2) -> Integer.compare(u1.getRol().ordinal(), u2.getRol().ordinal()));

		    // Limpiar el DefaultListModel
		    userModel.clear();

		    // Volver a agregar los usuarios ordenados al DefaultListModel
		    for (UsuarioApp usuario : listaUsuarios) {
		        userModel.addElement(usuario);
		    }

}
}
