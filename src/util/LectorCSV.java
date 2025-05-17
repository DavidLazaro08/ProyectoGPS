package util;

import modelo.GPSData;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;

/* Clase que permite leer los datos GPS previamente guardados en un archivo CSV.
 * Forma parte de la fase de procesamiento del ciclo del dato.
 * A partir de un nombre de archivo, devuelve una lista de objetos GPSData. */

public class LectorCSV {

    // ----------------------------------------------------
    // MÉTODO PRINCIPAL: LECTURA DEL ARCHIVO CSV
    // ----------------------------------------------------
    public static ArrayList<GPSData> leer(String nombreArchivo) {
        ArrayList<GPSData> listaLeida = new ArrayList<>();

        File archivo = new File(nombreArchivo);
        if (!archivo.exists()) {
            System.out.println("❌ El archivo '" + nombreArchivo + "' no existe.");
            System.out.println("Usa primero la opción para generar los datos y crear el CSV.");
            return listaLeida;
        }

        try {
            BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo));
            String linea;

            // Saltamos la cabecera del archivo (primera línea)
            lector.readLine();

            // Leemos el resto del archivo línea a línea
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(",");

                // Comprobamos que haya exactamente 5 columnas por línea
                if (partes.length != 5) {
                    System.out.println("⚠️ Línea malformada ignorada: " + linea);
                    continue;
                }

                try {
                    String busId = partes[0];
                    String timestamp = partes[1];
                    double latitude = Double.parseDouble(partes[2]);
                    double longitude = Double.parseDouble(partes[3]);
                    double speed = Double.parseDouble(partes[4]);

                    GPSData dato = new GPSData(busId, timestamp, latitude, longitude, speed);
                    listaLeida.add(dato);

                } catch (NumberFormatException e) {
                    System.out.println("⚠️ Error al convertir valores numéricos en: " + linea);
                }
            }

            lector.close();

        } catch (IOException e) {
            System.out.println("❌ Error al leer el archivo CSV.");
            e.printStackTrace();
        }

        return listaLeida;
    }
}
