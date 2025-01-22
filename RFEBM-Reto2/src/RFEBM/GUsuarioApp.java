package RFEBM;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JList;

public class GUsuarioApp extends JFrame {

    private static final long serialVersionUID = 1L;

    // Variables de la interfaz
    private JPanel contentPane, panel, panel_1, panel_6, panel_2, panel_3, panel_5, panel_4, panel_7, panel_8, panel_9, panel_10, panel_11, panel_12, panel_13, panel_14, panel_15, panel_16, panel_17, panel_18, panel_19, panel_20, panel_21, panel_22, panel_23, panel_24, panel_25, panel_26, panel_27, panel_28, panel_29, panel_30, panel_31, panel_32, panel_33, panel_34;
    private JTextField txtNombre, txtContraseña;
    private JComboBox ComboRol;
    private JButton BtnGuardar, btnEliminar, btnModificar, btnVolver;
    private JList lstUsuarios;

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
        setContentPane(contentPane);
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

        ComboRol = new JComboBox();
        panel_16.add(ComboRol, BorderLayout.WEST);

        panel_17 = new JPanel();
        panel_16.add(panel_17, BorderLayout.NORTH);

        panel_18 = new JPanel();
        panel_15.add(panel_18, BorderLayout.CENTER);
        panel_18.setLayout(new BorderLayout(0, 0));

        panel_19 = new JPanel();
        panel_18.add(panel_19, BorderLayout.NORTH);
        panel_19.setLayout(new BorderLayout(0, 0));

        BtnGuardar = new JButton("Guardar");
        panel_19.add(BtnGuardar, BorderLayout.WEST);

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

        panel_28 = new JPanel();
        panel_19.add(panel_28, BorderLayout.SOUTH);

        panel_27 = new JPanel();
        panel_18.add(panel_27, BorderLayout.CENTER);
        panel_27.setLayout(new BorderLayout(0, 0));

        lstUsuarios = new JList();
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

        panel_34 = new JPanel();
        panel_33.add(panel_34, BorderLayout.NORTH);
    }
}
