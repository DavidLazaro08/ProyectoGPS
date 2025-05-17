package servicio;

import modelo.GPSData;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/* Clase que procesa los datos GPS ya cargados desde un archivo CSV.
 *
 * Esta forma parte de la fase de procesamiento del dato en el proyecto.
 * Sirve para filtrar, buscar y modificar datos GPS una vez leídos desde el archivo.
 * Todos los métodos son estáticos porque no hace falta crear objetos.
 *
 * ------------------------------------------------------------------
 * 🧠 NOTA PERSONAL:
 *
 * En la actividad se nos pedía también "reflexionar" sobre cómo sería este sistema
 * en un entorno de verdad. Aunque aquí usamos listas y archivos CSV, lo normal sería
 * guardar los datos en una base de datos como MySQL y gestionarlos desde un backend
 * que permitiera consultas más potentes, filtros por zona, fechas, bus... o incluso
 * mostrar los datos en una app móvil o panel de control. */

public class ProcesadorDatos {

    // ----------------------------------------------------
    // FILTRAR DATOS POR ID DE AUTOBÚS (ej: BUS22)
    // ----------------------------------------------------
    public static ArrayList<GPSData> filtrarPorBus(ArrayList<GPSData> lista, String busId) {
        ArrayList<GPSData> resultado = new ArrayList<>();
        for (GPSData dato : lista) {
            if (dato.getBusId().equals(busId)) {
                resultado.add(dato);
            }
        }
        return resultado;
    }

    // ----------------------------------------------------
    // FILTRAR POR RANGO HORARIO (inicio y fin incluidos)
    // ----------------------------------------------------
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

    // ----------------------------------------------------
    // SIMULAR CAMBIO DE RECORRIDO PARA UN BUS CONCRETO
    // ----------------------------------------------------
    public static void simularCambioRecorrido(ArrayList<GPSData> lista, String busId) {
        int cambiosHechos = 0;

        for (int i = 0; i < lista.size(); i++) {
            GPSData dato = lista.get(i);
            if (dato.getBusId().equals(busId)) {

                // Añadimos una pequeña variación a las coordenadas
                double nuevaLat = dato.getLatitude() + 0.002;
                double nuevaLon = dato.getLongitude() - 0.002;

                GPSData modificado = new GPSData(
                        dato.getBusId(),
                        dato.getTimestamp(),
                        nuevaLat,
                        nuevaLon,
                        dato.getSpeed()
                );

                lista.set(i, modificado);
                cambiosHechos++;

                if (cambiosHechos == 10) break;
            }
        }

        System.out.println("✅ Se modificaron " + cambiosHechos + " registros para " + busId);
    }
}
