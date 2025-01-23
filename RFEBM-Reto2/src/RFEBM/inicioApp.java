package RFEBM;

import java.awt.EventQueue;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classes.RolApp;
import Classes.UsuarioApp;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.awt.event.ActionEvent;

public class inicioApp extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panel, panel_1, panel_2, panel_3, panel_4, panel_5;
	private JTextField leerUsuario;
	private JPasswordField leerContra;
	private JLabel lblLogo, lblUsuario, lblTexto, lblContrasena;
	private JButton btnAceptar;
	static RolApp rolUsuario = RolApp.Usuario;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inicioApp frame = new inicioApp();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public inicioApp() {
		setTitle("Inicio de sesión");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 436, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(new ImageIcon(inicioApp.class.getResource("/images/logos/logo.png")));
		panel.add(lblLogo, BorderLayout.CENTER);
		
		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_2.add(lblUsuario);
		
		leerUsuario = new JTextField();
		leerUsuario.addActionListener(this);
		panel_2.add(leerUsuario);
		leerUsuario.setColumns(10);
		
		panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		panel_4 = new JPanel();
		panel_3.add(panel_4, BorderLayout.NORTH);
		
		lblContrasena = new JLabel("Contraseña");
		lblContrasena.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_4.add(lblContrasena);
		
		leerContra = new JPasswordField();
		leerContra.setColumns(10);
		leerContra.addActionListener(this);
		panel_4.add(leerContra);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(this);
		panel_3.add(btnAceptar, BorderLayout.SOUTH);
		
		panel_5 = new JPanel();
		panel_3.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		lblTexto = new JLabel("");
		lblTexto.setHorizontalAlignment(SwingConstants.CENTER);
		panel_5.add(lblTexto, BorderLayout.SOUTH);
	}
	
	public void actionPerformed(ActionEvent e) {
	    String usuario = leerUsuario.getText();
	    String contrasena = new String(leerContra.getPassword());

	    // Cargar los usuarios desde el archivo .ser uno por uno
	    boolean usuarioValido = false;

	    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("resources/datos/usuarios.ser"))) {
	        UsuarioApp u;
	        while (true) {
	            try {
	                u = (UsuarioApp) ois.readObject();
	                // Verificar si el usuario y la contraseña coinciden
	                if (u.getNombreUsuario().equals(usuario) && u.getContraseña().equals(contrasena)) {
	                    usuarioValido = true;
	                    rolUsuario = u.getRol(); // Establecer el rol basado en el índice del enum
	                    JOptionPane.showMessageDialog(null, "Bienvenido " + u.getRol(), "Bienvenido a la aplicación", JOptionPane.INFORMATION_MESSAGE);
	                    new menuApp().setVisible(true);
	                    dispose();
	                    break;
	                }
	            } catch (EOFException e1) {
	                // Fin del archivo
	                break;
	            }
	        }
	    } catch (IOException | ClassNotFoundException ex) {
	        ex.printStackTrace();
	    }

	    // Si el usuario no es válido, mostrar un mensaje
	    if (!usuarioValido) {
	        lblTexto.setText("Usuario o contraseña incorrectos");
	    }
	}
	
	
	public class UsuariosCargar {
		 public static void cargarUsuariosDesdeArchivo(String archivo) {
		        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
		            UsuarioApp usuario;
		            while (true) {
		                try {
		                    usuario = (UsuarioApp) ois.readObject();
		                    // Procesar el usuario (por ejemplo, agregarlo a una lista)
		                    System.out.println("Cargando usuario: " + usuario);
		                } catch (EOFException e) {
		                    break;
		                }
		            }
		        } catch (IOException | ClassNotFoundException e) {
		            e.printStackTrace();
		        }
		    }
	}
}
