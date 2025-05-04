package util;

import modelo.GPSData;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/* Esta clase se encarga de crear archivos .json que contengan
 * la última posición conocida de cada autobús.
 * Formaría parte de la fase 5 del ciclo del dato: Distribución o Compartición. */

public class ExportadorJSON {

    // MÉTODO: Guarda un archivo JSON con la última posición de cada bus
    public static void exportarUltimaPosicion(ArrayList<GPSData> lista) {

        // Supone que la lista ya viene ordenada por tiempo (como en este proyecto)

        String busActual = "";
        GPSData ultimoDato = null;

        for (GPSData dato : lista) {
            // Cuando cambia de bus, se guarda el anterior
            if (!dato.getBusId().equals(busActual)) {
                // Guardamos la posición anterior si no es la primera vuelta
                if (ultimoDato != null) {
                    guardarJSON(ultimoDato);
                }
                busActual = dato.getBusId();
            }
            ultimoDato = dato;
        }

        // Guardamos el último dato del último bus
        if (ultimoDato != null) {
            guardarJSON(ultimoDato);
        }
    }

    // MÉTODO: Crea el archivo JSON de un único GPSData
    private static void guardarJSON(GPSData dato) {
        String nombreArchivo = dato.getBusId().toLowerCase() + "_status.json";

        String contenido = "{\n" +
                "  \"busId\": \"" + dato.getBusId() + "\",\n" +
                "  \"latitude\": " + dato.getLatitude() + ",\n" +
                "  \"longitude\": " + dato.getLongitude() + ",\n" +
                "  \"timestamp\": \"" + dato.getTimestamp() + "\"\n" +
                "}";

        try {
            FileWriter escritor = new FileWriter(nombreArchivo);
            escritor.write(contenido);
            escritor.close();
            System.out.println("Archivo JSON creado: " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al guardar archivo JSON para " + dato.getBusId());
            e.printStackTrace();
        }
    }
} 