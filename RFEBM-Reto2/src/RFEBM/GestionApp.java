package RFEBM;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

public class GestionApp extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel lblNewLabel;
	JPanel panel;
	JButton btnGClasificacion;
	JButton btnGEquipos;
	JButton btnGPartidos;
	JButton btnATemporada;
	
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
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(GestionApp.class.getResource("/resources/logo.png")));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JButton btnATemporada = new JButton("Añadir Temporada");
		btnATemporada.addActionListener(this);
		panel.add(btnATemporada);
		
		
		JButton btnGEquipos = new JButton("Gestion Equipos");
		btnGEquipos.addActionListener(this);
		panel.add(btnGEquipos);
		
		JButton btnGPartidos = new JButton("Gestion Partidos");
		btnGPartidos.addActionListener(this);
		panel.add(btnGPartidos);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
Object o = e.getSource();
if(o==btnATemporada) {
	
}
	}

	

}
