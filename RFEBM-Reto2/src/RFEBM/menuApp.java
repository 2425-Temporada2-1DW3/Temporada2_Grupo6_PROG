package RFEBM;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import Classes.RolApp;
import Classes.TemporadaApp;
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
    private JButton btnXML;


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

        btnXML = new JButton("Generar XML");
        panel_1.add(btnXML);
        btnXML.addActionListener(this);
        
        btnLogout = new JButton("Cerrar sesión");
        panel_1.add(btnLogout);
        btnLogout.addActionListener(this);

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
            try {
				new equipoApp().setVisible(true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
        if (o == btnXML) {
			GenerarXML(null);
        }
        if (o == btnLogout) {
        	  int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea cerrar sesión?", "Cerrar sesión", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
              if (respuesta == JOptionPane.YES_OPTION) {
                  new inicioApp().setVisible(true);
                  dispose();
              }
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
                btnXML.setEnabled(false); 
                btnXML.setVisible(false); 
                break;

            case Entrenador: // Admin (Rol Entrenador)
                btnClasificacion.setEnabled(true);  // El admin puede ver la clasificación
                btnEquipos.setEnabled(true);  // El admin puede ver los equipos
                btnGestion.setEnabled(false);  // El admin puede gestionar usuarios
                btnGestion.setVisible(false);
                btnXML.setEnabled(false); 
                btnXML.setVisible(false); 
                break;
                
            case Arbitro: // Admin (Rol Arbitro)
                btnClasificacion.setEnabled(true);  // El admin puede ver la clasificación
                btnEquipos.setEnabled(true);  // El admin puede ver los equipos
                btnGestion.setEnabled(false);  // El admin puede gestionar usuarios
                btnGestion.setVisible(false);
                btnXML.setEnabled(false); 
                btnXML.setVisible(false); 
                break;
                
            case Admin: // Admin (Rol Admin)
                btnClasificacion.setEnabled(true);  // El admin puede ver la clasificación
                btnEquipos.setEnabled(true);  // El admin puede ver los equipos
                btnGestion.setEnabled(true);  // El admin puede gestionar usuarios
                btnXML.setEnabled(false); 
                btnXML.setVisible(false); 
                break;

            case SuperAdmin: // Super Admin (Rol Super Admin)
                btnClasificacion.setEnabled(true);  // El super admin puede ver la clasificación
                btnEquipos.setEnabled(true);  // El super admin puede ver los equipos
                btnGestion.setEnabled(true);  // El super admin puede gestionar usuarios
                btnXML.setEnabled(true); 
                break;

            default:
                // Si no se reconoce el rol, deshabilitar todo
                btnClasificacion.setEnabled(false);
                btnEquipos.setEnabled(false);
                btnGestion.setEnabled(false);
                btnGestion.setVisible(false);
                btnXML.setEnabled(false); 
                btnXML.setVisible(false); 
        }
    }
    public static void GenerarXML(String[] args) {
        try {
            // Leer las temporadas desde el archivo serializado
            String archivoTemporadas = "resources/datos/temporadas.ser";
            List<String> temporadas = TemporadaApp.leerTemporadasDesdeArchivo(archivoTemporadas);

            if (temporadas == null || temporadas.isEmpty()) {
                System.out.println("No se encontraron temporadas en " + archivoTemporadas);
                return;
            }

            // Crear documento XML
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            
            // Elemento raíz
            Element rootElement = doc.createElement("LigaBalonmano");
            doc.appendChild(rootElement);

            // Procesar cada temporada
            for (String temporada : temporadas) {
                String jornadaPath = "resources/datos/Jornada" + temporada + ".csv";
                String plantillaPath = "resources/datos/Plantilla" + temporada + ".csv";
                String clasificacionPath = "resources/datos/Clasificacion" + temporada + ".csv";

                // Verificar si existen archivos
                if (!Files.exists(Paths.get(jornadaPath)) || 
                    !Files.exists(Paths.get(plantillaPath)) || 
                    !Files.exists(Paths.get(clasificacionPath))) {
                    System.out.println("Faltan archivos para la temporada: " + temporada);
                    continue;
                }

                List<String> jornadas = leerArchivo(jornadaPath);
                List<String> plantilla = leerArchivo(plantillaPath);
                List<String> clasificacion = leerArchivo(clasificacionPath);

             // Crear nodo de la temporada
                Element temporadaElement = doc.createElement("Temporada");

                // Asumimos que `temporada` es algo como "Temporada 2023-2024"
                String temporadaNombre = temporada.split(" ")[1];  // Esto extrae el rango de la temporada, como "2023-2024"

                temporadaElement.setAttribute("id", "Temporada_" + temporada);
                temporadaElement.setAttribute("nombre", temporada);
                temporadaElement.setAttribute("fecha", temporadaNombre);  // Aquí asignamos "2023-2024" a 'fecha'

                rootElement.appendChild(temporadaElement);

             // Añadir las jornadas
                Element jornadasElement = doc.createElement("Jornadas");
                temporadaElement.appendChild(jornadasElement);

                // Contador para las jornadas
                int jornadaCounter = 1; // Para controlar el número de jornada

                // Iteramos sobre las jornadas
                for (int i = 0; i < jornadas.size(); i++) {
                    String[] datos = jornadas.get(i).split(",");
                    
                    // Asegurarse de que hay suficientes datos para procesar
                    if (datos.length < 4) continue;

                    // Crear el elemento de la jornada (Jornada_X)
                    Element jornadaElement = doc.createElement("Jornada_" + jornadaCounter);
                    
                    // Crear 3 partidos por jornada (hay que asegurarse de que se usan datos diferentes)
                    for (int z = 0; z < 3; z++) {
                        // Si hay más de 3 partidos por jornada en el archivo, debemos extraerlos adecuadamente
                        int partidoIndex = i * 3 + z; // Esto asegura que se extraen partidos diferentes
                        
                        // Asegurarse de que hay suficientes partidos
                        if (partidoIndex < jornadas.size()) {
                            String[] partidoDatos = jornadas.get(partidoIndex).split(",");
                            
                            // Crear el nodo de cada partido
                            Element partidoElement = doc.createElement("Partido");
                            partidoElement.appendChild(doc.createTextNode(partidoDatos[0] + "," + partidoDatos[1]));

                            // Crear el nodo de resultado
                            Element resultadoElement = doc.createElement("Resultado");
                            resultadoElement.appendChild(doc.createTextNode(partidoDatos[2] + "," + partidoDatos[3]));

                            // Agregar el partido y el resultado a la jornada
                            jornadaElement.appendChild(partidoElement);
                            jornadaElement.appendChild(resultadoElement);
                        }
                    }

                    // Agregar la jornada completa a las jornadas de la temporada
                    jornadasElement.appendChild(jornadaElement);

                    // Incrementar el contador de jornadas
                    jornadaCounter++;

                    // Si llegamos a la jornada 10, terminamos de procesar las jornadas
                    if (jornadaCounter > 10) break;
                }


                // Añadir la plantilla
                Element plantillaElement = doc.createElement("Plantilla");
                temporadaElement.appendChild(plantillaElement);
                for (String jugador : plantilla) {
                    Element jugadorElement = doc.createElement("Jugador");
                    jugadorElement.appendChild(doc.createTextNode(jugador));
                    plantillaElement.appendChild(jugadorElement);
                }

                // Añadir la clasificación
                Element clasificacionElement = doc.createElement("Clasificacion");
                temporadaElement.appendChild(clasificacionElement);
                for (String equipo : clasificacion) {
                    Element equipoElement = doc.createElement("Equipo");
                    equipoElement.appendChild(doc.createTextNode(equipo));
                    clasificacionElement.appendChild(equipoElement);
                }
            }

            // Guardar el archivo XML
            String fileName = "../../Temporada2_Grupo6_LM/liga_balonmano.xml";
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty("indent", "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(doc);
            try (FileWriter writer = new FileWriter(fileName)) {
                StreamResult result = new StreamResult(writer);
                transformer.transform(source, result);
            }

            System.out.println("Archivo XML generado: " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static List<String> leerArchivo(String rutaArchivo) throws IOException {
        List<String> lineas = new ArrayList<>();
        File archivo = new File(rutaArchivo);

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
        }
        return lineas;
    }
    
}
