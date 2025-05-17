package util;

import modelo.GPSData;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/* Clase ExportadorJSON
 *
 * Esta clase se encarga de exportar la última posición conocida de cada autobús
 * en un archivo con formato JSON. Representa la fase final del ciclo del dato:
 * ➤ Distribución o Compartición de la información. */

public class ExportadorJSON {

    // ---------------------- MÉTODO PRINCIPAL ----------------------
    // Recorre la lista de datos (ordenada por fecha) y guarda la última posición de cada bus.

    public static void exportarUltimaPosicion(ArrayList<GPSData> lista) {
        String busActual = "";
        GPSData ultimoDato = null;

        for (GPSData dato : lista) {
            if (!dato.getBusId().equals(busActual)) {
                if (ultimoDato != null) {
                    guardarJSON(ultimoDato);
                }
                busActual = dato.getBusId();
            }
            ultimoDato = dato;
        }

        // Al salir del bucle, guardamos el último dato
        if (ultimoDato != null) {
            guardarJSON(ultimoDato);
        }
    }

    // ---------------------- MÉTODO AUXILIAR ----------------------
    // Crea un archivo JSON con los datos de un único GPSData

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
