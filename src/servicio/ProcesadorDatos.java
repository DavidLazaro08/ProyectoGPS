package servicio;

import modelo.GPSData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/* Esta clase servirá para procesar los datos GPS una vez leídos desde el archivo CSV.
 * Aquí incluiremos funciones útiles como:
 * - Filtrar por autobús específico
 * - Filtrar por un rango horario concreto
 * - Validar si las coordenadas están dentro de un rango aceptable (zona de Sevilla)
 * - Validar si la fecha y hora tienen el formato correcto (ISO 8601)
 *
 * Esta clase formaría parte de la fase de "Procesamiento" del ciclo del dato.
 * Todos sus métodos son estáticos porque no necesitamos crear objetos de esta clase,
 * solo usar sus funciones.
 */

/* Según la actividad del proyecto, también se nos pide que expliquemos cómo se
 * podría almacenar esta información en un sistema real de producción.
 * Por eso, dejo aquí una reflexión sobre cómo funcionaría este mismo sistema si no
 * estuviéramos trabajando solo con archivos y listas en Java.
 *
 * Aunque aquí usamos un archivo CSV como fuente de datos, en una aplicación real
 * los registros GPS se guardarían directamente en bases de datos. Lo habitual sería
 * usar una base como MySQL, con una tabla para gps_data que incluya columnas
 * como busId, timestamp, latitud, longitud y velocidad. También se podrían usar sistemas
 * NoSQL como MongoDB si quisiéramos almacenar los datos como documentos JSON.
 *
 * Esto permitiría trabajar con grandes volúmenes, hacer búsquedas más potentes (por rango
 * horario, zona, bus...) y conectar el sistema con otros servicios como páginas web,
 * aplicaciones móviles o paneles de control.
 */


public class ProcesadorDatos {

    // MÉTODO: Devuelve solo los datos que pertenezcan a un autobús concreto.
    public static ArrayList<GPSData> filtrarPorBus(ArrayList<GPSData> lista, String busId) {
        ArrayList<GPSData> resultado = new ArrayList<>();
        for (GPSData dato : lista) {
            if (dato.getBusId().equals(busId)) {
                resultado.add(dato);
            }
        }
        return resultado;
    }

    // MÉTODO: Devuelve solo los datos que estén entre dos fechas y horas concretas (ambos incluidos).
    // El formato esperado es ISO_LOCAL_DATE_TIME (ej: 2025-04-17T08:10:00)
    public static ArrayList<GPSData> filtrarPorRangoHorario(ArrayList<GPSData> lista, String inicio, String fin) {
        ArrayList<GPSData> resultado = new ArrayList<>();
        DateTimeFormatter formato = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        LocalDateTime desde = LocalDateTime.parse(inicio, formato);
        LocalDateTime hasta = LocalDateTime.parse(fin, formato);

        for (GPSData dato : lista) {
            LocalDateTime momento = LocalDateTime.parse(dato.getTimestamp(), formato);
            if (!momento.isBefore(desde) && !momento.isAfter(hasta)) {
                resultado.add(dato);
            }
        }
        return resultado;
    }

    // MÉTODO: Simula un cambio en el recorrido de un autobús (cambia coordenadas en 10 registros)
    public static void simularCambioRecorrido(ArrayList<GPSData> lista, String busId) {
        int cambiosHechos = 0;

        for (int i = 0; i < lista.size(); i++) {
            GPSData dato = lista.get(i);
            if (dato.getBusId().equals(busId)) {

                // Le aplicamos un pequeño cambio a la latitud y longitud
                double nuevaLat = dato.getLatitude() + 0.002;
                double nuevaLon = dato.getLongitude() - 0.002;

                // Creamos un nuevo objeto con los datos modificados
                GPSData modificado = new GPSData(
                        dato.getBusId(),
                        dato.getTimestamp(),
                        nuevaLat,
                        nuevaLon,
                        dato.getSpeed()
                );

                // Sustituimos en la lista
                lista.set(i, modificado);
                cambiosHechos++;

                if (cambiosHechos == 10) break;
            }
        }

        System.out.println("✅ Se modificaron " + cambiosHechos + " registros para " + busId);
    }

}