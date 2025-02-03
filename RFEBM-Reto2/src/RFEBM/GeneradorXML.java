package RFEBM;

import Classes.TemporadaApp;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
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
            String fileName = "resources/datos/liga_balonmano.xml";
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

    // Método para leer un archivo CSV
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