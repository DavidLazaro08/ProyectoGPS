import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/* Esta clase se encarga de generar los datos GPS simulados de varios autobuses.
 * Forma parte de la fase de Captura/Generación del proyecto.
 * Generaremos datos ficticios para 3 autobuses, durante 60 minutos,
 * con un registro por minuto para cada uno. */

public class GeneradorDatosBus {

    // MÉTODO ESTÁTICO: genera los datos y devuelve una lista con todos los registros simulados.
    public static ArrayList<GPSData> generar() {

        // Lo primero será crear una lista donde guardaremos todos los datos generados.
        ArrayList<GPSData> listaDatos = new ArrayList<>();

        // Definimos los id de los tres autobuses simulados.
        String[] buses = {"BUS01", "BUS02", "BUS03"};

        /* En qué minutos exactos consideraremos que cada autobús hace una parada.
        *  Es simplemente una simulación (no realista al 100%) para que al menos cada bus tenga
        *  varias paradas en su trayecto. Tras varias pruebas con el uso del random, aparecían
        *  1, 2, 3 paradas (velocidad 0) y decidí probar a hacerlo de este modo.
        *  Elegimos 5 momentos fijos para todos los autobuses, aunque en la realidad esto sería más aleatorio. */

        int[] minutosParada = {5, 15, 25, 35, 45};

        // Por último, definimos la hora de inicio de la simulación (día y hora concretos).
        LocalDateTime inicio = LocalDateTime.of(2025, 4, 17, 8, 0); // 17 de abril, 08:00

        // ------------------------------------------

        // A partir de aquí, recorremos cada autobús.
        for (String busId : buses) {
            LocalDateTime marcaTiempo = inicio;

            // Para cada bus, generamos 60 registros (uno por minuto como se nos pide).
            for (int i = 0; i < 60; i++) {

                // Formato de fecha y hora en el estilo ISO 8601 que ya mencionamos.
                String timestamp = marcaTiempo.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

                // Coordenadas simuladas (zona aproximada de Sevilla).
                double latitude = 37.38 + Math.random() * 0.01;
                double longitude = -5.99 + Math.random() * 0.01;

                // Velocidad simulada en km/h.
                // En los minutos que marcamos como "paradas", la velocidad será 0.
                // En el resto, se generará una velocidad aleatoria entre 10 y 50 km/h.
                double speed = 10 + Math.random() * 40; // velocidad por defecto

                for (int minuto : minutosParada) {
                    if (i == minuto) {
                        speed = 0;
                        break;
                    }
                }

                // Creamos el objeto GPSData con los datos de ese minuto.
                GPSData dato = new GPSData(busId, timestamp, latitude, longitude, speed);

                // Lo añadimos a nuestra lista.
                listaDatos.add(dato);

                // Sumamos un minuto para el siguiente registro.
                marcaTiempo = marcaTiempo.plusMinutes(1);
            }
        }

        // Finalmente, devolvemos la lista completa con todos los datos generados.
        return listaDatos;
    }
}
