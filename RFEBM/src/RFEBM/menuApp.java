package RFEBM;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class menuApp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel_1;
	private JButton btnLogout;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JButton btnequipos;
	private JButton btnClasificaciones;
	private JButton btnModificaciones;
	private JLabel lblmenu;
	private JPanel panel_5;
	private JLabel lblNewLabel;
	private JPanel panel_6;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menuApp frame = new menuApp();
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
		FlowLayout flowLayout_3 = (FlowLayout) panel_5.getLayout();
		panel.add(panel_5);
		
		lblNewLabel = new JLabel("");
		panel_5.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(menuApp.class.getResource("/resources/logo.png")));
		
		lblmenu = new JLabel("MENU");
		lblmenu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lblmenu);
		
		panel_6 = new JPanel();
		panel.add(panel_6);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(menuApp.class.getResource("/resources/logo.png")));
		panel_6.add(lblNewLabel_1);
		
		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		btnLogout = new JButton("Cerrar sesion");
		panel_1.add(btnLogout);
		
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
		
		btnClasificaciones = new JButton("Clasificaiones");
		panel_4.add(btnClasificaciones);
		
		btnequipos = new JButton("Plantillas");
		panel_4.add(btnequipos);
		
		btnModificaciones = new JButton("Modificaciones");
		panel_4.add(btnModificaciones);
	}

}
