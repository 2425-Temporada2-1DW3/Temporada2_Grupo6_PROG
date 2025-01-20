package RFEBM;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
import java.awt.event.ActionEvent;

public class inicioApp extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panel, panel_1, panel_2, panel_3, panel_4, panel_5;
	private JTextField leerUsuario;
	private JPasswordField leerContra;
	private JLabel lblLogo, lblUsuario, lblTexto, lblContrasena;
	private JButton btnAceptar;
	public static int rol = 0;
	
	
	
	
	//Definir usuarios y contraseñas
		String usuariocorrecto = "user";
		String contrasenausuario = "1234";
		
		String admincorrecto = "admin";
		String contrasenaadmin = "5678";
		
		String entrenadorcorrecto = "entrenador"; 
		String contrasenaentrenador = "2345";
		
		String arbitrocorrecto = "arbitro";
		String contrasenaarbitro = "4321";
	/**
	 * Launch the application.
	 */
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
		lblLogo.setIcon(new ImageIcon(inicioApp.class.getResource("/resources/logo.png")));
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
		//Leer usuario y contraseña
		 String usuario = leerUsuario.getText();
	     String contrasena = new String(leerContra.getPassword());
	      //Comprueba si esta intentando entrar como usuario
	     if (usuario.equals(usuariocorrecto) && contrasena.equals(contrasenausuario)) {
	    	 JOptionPane.showMessageDialog(null, "Bienvenido usuario", "Bienvenido a la aplicación", JOptionPane.INFORMATION_MESSAGE);
	    	 rol = 1;
	    	 new menuApp().setVisible(true);
	    	 dispose();
	    	//Comprueba si esta intentando entrar como administrador
	     } else if (usuario.equals(admincorrecto) && contrasena.equals(contrasenaadmin)) {
	    	 JOptionPane.showMessageDialog(null, "Bienvenido administrador", "Bienvenido a la aplicación", JOptionPane.INFORMATION_MESSAGE);
	    	 rol= 2;
	    	 new menuApp().setVisible(true);
	    	 dispose();
	    	 //comprueba si el ususario es entrenador
	     } else if (usuario.equals(entrenadorcorrecto) && contrasena.equals(contrasenaentrenador)) {
	    	 JOptionPane.showMessageDialog(null, "Bienvenido entrenador", "Bienvenido a la aplicación", JOptionPane.INFORMATION_MESSAGE);
	    	 rol= 3;
	    	 new menuApp().setVisible(true);
	    	 dispose();
	    	 //comprueba si el usuario es arbitro
	     } else if (usuario.equals(arbitrocorrecto) && contrasena.equals(contrasenaarbitro)) {
	    	 JOptionPane.showMessageDialog(null, "Bienvenido arbitro", "Bienvenido a la aplicación", JOptionPane.INFORMATION_MESSAGE);
	    	 rol= 4;
	    	 new menuApp().setVisible(true);
	    	 dispose();
	    	 //En caso de que no se haya introducido ni nombre de usuario ni contraseña
	     }else if(usuario.equals("") && contrasena.equals("")){
	    	 lblTexto.setText("Introduzca usuario y contraseña");
	    	 //En caso de que no se haya introducido nombre de usuario
	     }else if(usuario.equals("")){
	    	 lblTexto.setText("Introduzca usuario");
	    	 //En caso de que no se haya introducido contraseña
	     }else if(contrasena.equals("")){
	    	 lblTexto.setText("Introduzca contraseña");
	    	 // En caso de que cualquiera de las 2 opciones sean incorrectas
	     }else {
	    	 lblTexto.setText("Usuario o contraseña incorrectos");
	     }
	}
	
	

    
    
}
