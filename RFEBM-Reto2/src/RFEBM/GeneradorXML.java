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
                
                String nombreArchivo = new File(archivos[0]).getName(); // Extrae solo el nombre del archivo
                String nombreTemporada = nombreArchivo.replace("JornadaTemporada ", "").replace(".csv", ""); // "2023-2024"

                Element temporadaElement = doc.createElement("Temporada");
                temporadaElement.setAttribute("id", "Temporada_" + (i + 1));
                temporadaElement.setAttribute("nombre", "Temporada_" + nombreTemporada);
                rootElement.appendChild(temporadaElement);

             // Añadir las jornadas
                for (int j = 0; j < jornadas.size(); j += 3) { // Incrementar de 3 en 3
                    Element jornadaElement = doc.createElement("Jornada_" + ((j / 3) + 1)); // Crear un nuevo elemento Jornada
                    temporadaElement.appendChild(jornadaElement);

                    // Procesar hasta 3 partidos por jornada
                    for (int k = 0; k < 3 && (j + k) < jornadas.size(); k++) {
                        String[] partidoInfo = jornadas.get(j + k).split(","); // Suponiendo que el formato es "EquipoLocal,EquipoVisitante,GolesLocal,GolesVisitante"
                        String equipoLocal = partidoInfo[0];
                        String equipoVisitante = partidoInfo[1];
                        String golesLocal = partidoInfo[2];
                        String golesVisitante = partidoInfo[3];

                        Element partidoElement = doc.createElement("Partido");
                        partidoElement.appendChild(doc.createTextNode(equipoLocal + "," + equipoVisitante));
                        jornadaElement.appendChild(partidoElement);

                        Element resultadoElement = doc.createElement("Resultado");
                        resultadoElement.appendChild(doc.createTextNode(golesLocal + "," + golesVisitante));
                        jornadaElement.appendChild(resultadoElement);
                    }
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
            String fileName = "resources/datos/liga_balonmano.xml";
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            // Establecer propiedades para la indentación
            transformer.setOutputProperty("indent", "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4"); // 4 espacios de indentación

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
