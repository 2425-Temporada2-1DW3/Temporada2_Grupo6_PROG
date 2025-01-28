package RFEBM;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class GeneradorXML {

    public static void main(String[] args) {
        try {
            // Rutas a los archivos (3 por cada temporada: Jornadas, Plantilla, Clasificación)
            String[][] archivosTemporadas = {
                {"resources/datos/JornadaTemporada 2023-2024.csv", "resources/datos/PlantillaTemporada 2023-2024.csv", "resources/datos/ClasificacionTemporada 2023-2024.csv"},
                {"resources/datos/JornadaTemporada 2024-2025.csv", "resources/datos/PlantillaTemporada 2024-2025.csv", "resources/datos/ClasificacionTemporada 2024-2025.csv"},
                {"resources/datos/JornadaTemporada 2025-2026.csv", "resources/datos/PlantillaTemporada 2025-2026.csv", "resources/datos/ClasificacionTemporada 2025-2026.csv"}
            };

            // Crear un documento XML único
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            // Crear el elemento raíz
            Element rootElement = doc.createElement("LigaBalonmano");
            doc.appendChild(rootElement);

            // Procesar cada temporada y añadirla al XML
            for (int i = 0; i < archivosTemporadas.length; i++) {
                String[] archivos = archivosTemporadas[i];
                List<String> jornadas = leerArchivo(archivos[0]);
                List<String> plantilla = leerArchivo(archivos[1]);
                List<String> clasificacion = leerArchivo(archivos[2]);

                Element temporadaElement = doc.createElement("Temporada");
                temporadaElement.setAttribute("id", "Temporada_" + (i + 1));
                rootElement.appendChild(temporadaElement);

                // Añadir las jornadas
                Element jornadasElement = doc.createElement("Jornadas");
                temporadaElement.appendChild(jornadasElement);
                for (String jornada : jornadas) {
                    Element jornadaElement = doc.createElement("Jornada");
                    jornadaElement.appendChild(doc.createTextNode(jornada));
                    jornadasElement.appendChild(jornadaElement);
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

            // Guardar el contenido en un único archivo XML
            String fileName = "liga_balonmano.xml";
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            try (FileWriter writer = new FileWriter(fileName)) {
                StreamResult result = new StreamResult(writer);
                transformer.transform(source, result);
            }

            System.out.println("Archivo XML único generado: " + fileName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para leer archivos CSV y devolver las líneas como una lista
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
