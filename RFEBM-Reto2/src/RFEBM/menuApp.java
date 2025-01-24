package RFEBM;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;

import Classes.RolApp;
import log.log;

public class menuApp extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel panel_1;
    private JPanel panel_2;
    private JPanel panel_3;
    private JPanel panel_4;
    private JButton btnEquipos;
    private JButton btnClasificacion;
    private JLabel lblmenu;
    private JPanel panel_5;
    private JLabel lblNewLabel;
    private JPanel panel_6;
    private JLabel lblNewLabel_1;
    private JButton btnLogout;
    private JButton btnGestion;
    Logger LOG = log.getLogger(menuApp.class);


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    menuApp frame = new menuApp();
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
    public menuApp() {


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 560, 409);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);

        panel_5 = new JPanel();
        panel.add(panel_5);

        lblNewLabel = new JLabel("");
        panel_5.add(lblNewLabel);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setIcon(new ImageIcon(menuApp.class.getResource("/images/logos/logo.png")));

        lblmenu = new JLabel("MENU");
        lblmenu.setFont(new Font("Tahoma", Font.PLAIN, 20));
        panel.add(lblmenu);

        panel_6 = new JPanel();
        panel.add(panel_6);

        lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(menuApp.class.getResource("/images/logos/logo.png")));
        panel_6.add(lblNewLabel_1);

        panel_1 = new JPanel();
        contentPane.add(panel_1, BorderLayout.SOUTH);
        panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

        btnLogout = new JButton("Cerrar sesión");
        panel_1.add(btnLogout);
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea cerrar sesión?", "Cerrar sesión", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (respuesta == JOptionPane.YES_OPTION) {
                    new inicioApp().setVisible(true);
                    dispose();
                }
            }
        });

        panel_2 = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
        flowLayout.setVgap(75);
        contentPane.add(panel_2, BorderLayout.WEST);

        panel_3 = new JPanel();
        FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
        flowLayout_1.setVgap(75);
        contentPane.add(panel_3, BorderLayout.EAST);

        panel_4 = new JPanel();
        FlowLayout flowLayout_2 = (FlowLayout) panel_4.getLayout();
        flowLayout_2.setVgap(75);
        contentPane.add(panel_4, BorderLayout.CENTER);

        btnClasificacion = new JButton("Clasificación");
        panel_4.add(btnClasificacion);
        btnClasificacion.addActionListener(this);
        
        btnEquipos = new JButton("Plantillas");
        btnEquipos.addActionListener(this);
        panel_4.add(btnEquipos);

        btnGestion = new JButton("Gestión");
        btnGestion.addActionListener(this);
        panel_4.add(btnGestion);

        // Configurar botones según el rol del usuario
        configurarMenuSegunRol(inicioApp.rolUsuario);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object o = ae.getSource();
        if (o == btnEquipos) {
            new equipoApp().setVisible(true);
            dispose();
        }
        if (o == btnGestion) {
        	LOG.info("Info: El usuario "+inicioApp.usuario +" ha entrado a gestion.");
        	new GestionApp().setVisible(true);
            dispose();
        }
        if (o == btnClasificacion) {
			try {
				new clasificacionApp().setVisible(true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            dispose();
        }
    }

    // Este es el método que configura los botones según el rol
    private void configurarMenuSegunRol(RolApp rolUsuario) {
        // Configurar acceso a los botones según el rol del usuario
        switch (rolUsuario) {
            case Usuario: // Usuario (Rol Usuario)
                btnClasificacion.setEnabled(true);  // El usuario puede ver la clasificación
                btnEquipos.setEnabled(true);  // El usuario puede ver los equipos
                btnGestion.setEnabled(false);  // El usuario no puede gestionar usuarios
                btnGestion.setVisible(false);
                break;

            case Entrenador: // Admin (Rol Entrenador)
                btnClasificacion.setEnabled(true);  // El admin puede ver la clasificación
                btnEquipos.setEnabled(true);  // El admin puede ver los equipos
                btnGestion.setEnabled(false);  // El admin puede gestionar usuarios
                break;
                
            case Arbitro: // Admin (Rol Arbitro)
                btnClasificacion.setEnabled(true);  // El admin puede ver la clasificación
                btnEquipos.setEnabled(true);  // El admin puede ver los equipos
                btnGestion.setEnabled(false);  // El admin puede gestionar usuarios
                break;
                
            case Admin: // Admin (Rol Admin)
                btnClasificacion.setEnabled(true);  // El admin puede ver la clasificación
                btnEquipos.setEnabled(true);  // El admin puede ver los equipos
                btnGestion.setEnabled(true);  // El admin puede gestionar usuarios
                break;

            case SuperAdmin: // Super Admin (Rol Super Admin)
                btnClasificacion.setEnabled(true);  // El super admin puede ver la clasificación
                btnEquipos.setEnabled(true);  // El super admin puede ver los equipos
                btnGestion.setEnabled(true);  // El super admin puede gestionar usuarios
                break;

            default:
                // Si no se reconoce el rol, deshabilitar todo
                btnClasificacion.setEnabled(false);
                btnEquipos.setEnabled(false);
                btnGestion.setEnabled(false);
        }
    }
}
