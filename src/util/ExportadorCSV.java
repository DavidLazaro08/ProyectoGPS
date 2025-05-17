package util;

import modelo.GPSData;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/** Clase que exporta los datos GPS generados a un archivo CSV.
 *
 * Forma parte de la fase de Captura/Generación del ciclo del dato.
 * Permite guardar los registros en formato texto estructurado (CSV),
 * compatible con hojas de cálculo y otras herramientas. */

public class ExportadorCSV {

    // ----------------------------------------------------
    // MÉTODO: guardar lista de datos en archivo CSV
    // ----------------------------------------------------
    public static void guardar(ArrayList<GPSData> lista, String nombreArchivo) {
        try {
            FileWriter escritor = new FileWriter(nombreArchivo);

            // Escribimos una línea de cabecera (encabezados de columna)
            escritor.write("busId,timestamp,latitude,longitude,speed\n");

            // Recorremos todos los objetos GPSData y los volcamos en formato CSV
            for (GPSData dato : lista) {
                escritor.write(dato.toString() + "\n");
            }

            escritor.close();
            System.out.println("\nArchivo CSV guardado correctamente como: " + nombreArchivo);

        } catch (IOException e) {
            System.out.println("❌ Error al guardar el archivo CSV.");
            e.printStackTrace();
        }
    }
}
