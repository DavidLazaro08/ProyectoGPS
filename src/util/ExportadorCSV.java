package util;

import modelo.GPSData;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/* Esta clase se encargará de exportar los datos simulados a un archivo CSV.
 * Se utiliza en la fase de Captura/Generación para guardar los registros generados. */

public class ExportadorCSV {

    // MÉTODO ESTÁTICO que guarda la lista de datos en un archivo CSV
    public static void guardar(ArrayList<GPSData> lista, String nombreArchivo) {
        try {
            FileWriter escritor = new FileWriter(nombreArchivo);

            // Escribimos una cabecera para facilitar la lectura posterior.
            escritor.write("busId,timestamp,latitude,longitude,speed\n");

            // Escribimos cada línea con los datos en formato CSV.
            for (GPSData dato : lista) {
                escritor.write(dato.toString() + "\n");
            }

            escritor.close();
            System.out.println("\nArchivo CSV guardado correctamente como: " + nombreArchivo);

        } catch (IOException e) {
            System.out.println("Error al guardar el archivo CSV.");
            e.printStackTrace();
        }
    }
}

