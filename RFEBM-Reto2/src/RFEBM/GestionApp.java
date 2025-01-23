package RFEBM;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Classes.RolApp;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

public class GestionApp extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    
    // Declaración de los componentes a nivel de clase
    JLabel lblNewLabel;
    JPanel panel;JPanel panel_1;JPanel panel_2;JPanel panel_3;JPanel panel_4;JPanel panel_5;
    JButton btnGClasificacion;
    JButton btnGEquipos;
    JButton btnGUsuario;
    JButton btnATemporada;
    JButton btnVolver;
    private JPanel panel_6;

    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GestionApp frame = new GestionApp();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public GestionApp() {
        // Establecer las características de la ventana
        setBounds(100, 100, 600, 300);
        
        // Crear la etiqueta con la imagen
        lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(GestionApp.class.getResource("/images/logos/logo.png")));
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblNewLabel, BorderLayout.NORTH);
        
        // Crear el panel
        panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout(0, 0));
        
        panel_1 = new JPanel();
        panel.add(panel_1, BorderLayout.SOUTH);
        panel_1.setLayout(new BorderLayout(0, 0));
        
        panel_3 = new JPanel();
        panel_1.add(panel_3, BorderLayout.CENTER);
        panel_3.setLayout(new BorderLayout(0, 0));
        
        panel_5 = new JPanel();
        panel_3.add(panel_5, BorderLayout.SOUTH);
        
        panel_2 = new JPanel();
        panel_3.add(panel_2, BorderLayout.EAST);
        
        panel_6 = new JPanel();
        panel_3.add(panel_6, BorderLayout.CENTER);
        panel_6.setLayout(new BorderLayout(0, 0));
        
        btnVolver = new JButton("Volver");
        panel_6.add(btnVolver, BorderLayout.EAST);
        btnVolver.addActionListener(this);
        
        panel_4 = new JPanel();
        panel.add(panel_4, BorderLayout.CENTER);
        
        btnGEquipos = new JButton("Gestion Equipos");
        panel_4.add(btnGEquipos);
        
        btnGUsuario = new JButton("Gestion Usuarios");
        panel_4.add(btnGUsuario);
    
        btnATemporada = new JButton("Añadir Temporada");
        panel_4.add(btnATemporada);
        
        
        //ActionListener en los botones
        btnATemporada.addActionListener(this);
        btnGUsuario.addActionListener(this);
        btnGEquipos.addActionListener(this);
        
        
        configurarMenuSegunRol(inicioApp.rolUsuario);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o == btnATemporada) {
            new temporadasApp().setVisible(true);
            dispose();
        }
        if (o == btnGUsuario) {
            new GUsuarioApp().setVisible(true);
            dispose();
        }
        if (o == btnGEquipos) {
            new GEquipoApp().setVisible(true);
            dispose();
        }
        if (o == btnVolver) {
            new menuApp().setVisible(true);
            dispose();
        }
    }
    private void configurarMenuSegunRol(RolApp rolUsuario) {
        // Configurar acceso a los botones según el rol del usuario
        switch (rolUsuario) {
            case Entrenador: // Entrenador
            	btnATemporada.setEnabled(false); 
            	btnATemporada.setVisible(false);
            	btnGUsuario.setEnabled(false);
            	btnGUsuario.setVisible(false);
                break;

            case Admin: // Admin (Rol Admin)
            	btnGUsuario.setEnabled(false);
            	btnGUsuario.setVisible(false);
                break;

            default:
            	btnATemporada.setEnabled(true); 
            	btnGUsuario.setEnabled(true);
            	btnGEquipos.setEnabled(true); 
        }
    }
}