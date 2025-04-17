import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/* Esta clase permite leer los datos previamente guardados en un archivo CSV.
 * Nos servirá en la fase de procesamiento para cargar la información desde disco. */

public class LectorCSV {

    // MÉTODO PARA LEER EL CSV Y DEVOLVER UNA LISTA DE OBJETOS GPSData
    public static ArrayList<GPSData> leer(String nombreArchivo) {
        ArrayList<GPSData> listaLeida = new ArrayList<>();

        try {
            BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo));
            String linea;

            // Leemos la primera línea (cabecera) y la ignoramos
            lector.readLine();

            // Leemos el resto línea a línea
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(",");

                if (partes.length == 5) {
                    String busId = partes[0];
                    String timestamp = partes[1];
                    double latitude = Double.parseDouble(partes[2]);
                    double longitude = Double.parseDouble(partes[3]);
                    double speed = Double.parseDouble(partes[4]);

                    GPSData dato = new GPSData(busId, timestamp, latitude, longitude, speed);
                    listaLeida.add(dato);
                }
            }

            lector.close();

        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV.");
            e.printStackTrace();
        }

        return listaLeida;
    }
}
