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
 * solo usar sus funciones. */

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

    // MÉTODO: Comprueba si unas coordenadas están dentro de un rango lógico (zona Sevilla aproximadamente).
    public static boolean coordenadasValidas(double lat, double lon) {
        return lat >= 37.30 && lat <= 37.45 && lon >= -6.05 && lon <= -5.95;
    }

    // MÉTODO: Comprueba si un timestamp (fecha y hora) tiene el formato correcto ISO 8601.
    public static boolean timestampValido(String timestamp) {
        try {
            LocalDateTime.parse(timestamp, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            return true;
        } catch (Exception e) {
            return false;
        }


    }


}