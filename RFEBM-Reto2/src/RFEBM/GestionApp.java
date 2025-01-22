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

public class GestionApp extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    
    // Declaración de los componentes a nivel de clase
    JLabel lblNewLabel;
    JPanel panel;
    JButton btnGClasificacion;
    JButton btnGEquipos;
    JButton btnGPartidos;
    JButton btnATemporada;  // Solo una declaración a nivel de clase
    
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
        setBounds(100, 100, 800, 500);
        
        // Crear la etiqueta con la imagen
        lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(GestionApp.class.getResource("/images/logos/logo.png")));
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblNewLabel, BorderLayout.NORTH);
        
        // Crear el panel
        panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        
        // Botón Añadir Temporada
        btnATemporada = new JButton("Añadir Temporada");
        btnATemporada.addActionListener(this);
        panel.add(btnATemporada);
        
        // Botón Gestionar Equipos
        btnGEquipos = new JButton("Gestion Equipos");
        btnGEquipos.addActionListener(this);
        panel.add(btnGEquipos);
        
        // Botón Gestionar Partidos
        btnGPartidos = new JButton("Gestion Partidos");
        btnGPartidos.addActionListener(this);
        panel.add(btnGPartidos);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o == btnATemporada) {
            // Abre la ventana de Temporadas y cierra la actual
            new temporadasApp().setVisible(true);
            dispose();
        }
        // Puedes agregar más condiciones aquí para los otros botones si es necesario
    }
}