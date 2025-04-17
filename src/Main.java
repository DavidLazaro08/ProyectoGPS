import java.util.ArrayList;

/* Esta clase contiene el método main, que sirve como punto de inicio del programa.
 * Llama al generador de datos, muestra una parte por consola y guarda el resultado en un archivo CSV. */

public class Main {

    public static void main(String[] args) {

        // Llamamos al método que genera los datos simulados
        ArrayList<GPSData> listaDatos = GeneradorDatosBus.generar();

        // Mostramos por consola el total de registros generados.
        System.out.println("Total de datos generados: " + listaDatos.size());

        // ----------------------------------------------------------------

        // MOSTRAMOS LOS PRIMEROS 5 REGISTROS DE CADA AUTOBÚS (Prueba).
        System.out.println("\nPrimeros 5 registros por autobús:\n");

        int contador = 0;
        String busActual = "";

        // Recorremos toda la lista de datos generados.
        for (GPSData dato : listaDatos) {

            // Cuando detectamos que cambia el autobús, reiniciamos contador y lo indicamos.
            if (!dato.getBusId().equals(busActual)) {
                busActual = dato.getBusId();
                contador = 0;
                System.out.println("\nDatos para " + busActual + ":");
            }

            // Muestro los primeros 5 registros por autobús para probar por consola.
            if (contador < 5) {
                // Mostramos cada campo por separado para que se vea mejor en consola.
                // Pensaba cambiar el toString pero parece no ser recomendable de cara al CSV.
                System.out.println("  Hora: " + dato.getTimestamp() +
                        " | Lat: " + dato.getLatitude() +
                        " | Lon: " + dato.getLongitude() +
                        " | Velocidad: " + dato.getSpeed() + " km/h");
                contador++;
            }
        }

        // ----------------------------------------------------------------

        // GUARDAMOS EL ARCHIVO CSV
        // Usamos el método toString() porque ya está preparado en formato CSV.
        // Así es más fácil exportarlo y abrirlo con herramientas como Excel o LibreOffice.
        ExportadorCSV.guardar(listaDatos, "gps_data.csv");

        // ----------------------------------------------------------------

        // LECTURA DEL CSV (Prueba)
        // Leemos el archivo CSV guardado para comprobar que los datos pueden recuperarse correctamente.
        System.out.println("\nLeemos el CSV para comprobar que se carga correctamente:\n");

        ArrayList<GPSData> datosLeidos = LectorCSV.leer("gps_data.csv");

        // Mostramos los primeros 3 registros leídos desde el archivo
        for (int i = 0; i < 3 && i < datosLeidos.size(); i++) {
            GPSData dato = datosLeidos.get(i);
            System.out.println("Bus: " + dato.getBusId() +
                    " | Hora: " + dato.getTimestamp() +
                    " | Velocidad: " + dato.getSpeed() + " km/h");
        }
    }
}
