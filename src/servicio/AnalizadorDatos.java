package servicio;

import modelo.GPSData;
import java.util.ArrayList;

/* Clase que analiza los datos GPS ya filtrados o cargados.
 * Forma parte de la fase de análisis del ciclo del dato.
 *
 * Permite hacer operaciones como:
 * - Calcular la velocidad media de un autobús
 * - Contar cuántas paradas (velocidad 0) ha hecho un autobús */

public class AnalizadorDatos {

    // ----------------------
    // Cálculo de velocidad media de un autobús
    // ----------------------
    public static double velocidadMediaBus(ArrayList<GPSData> lista, String busId) {
        double suma = 0;
        int contador = 0;

        for (GPSData dato : lista) {
            if (dato.getBusId().equals(busId)) {
                suma += dato.getSpeed();
                contador++;
            }
        }

        if (contador > 0) {
            return suma / contador;
        } else {
            // Si no hay datos para ese bus, devolvemos 0
            return 0;
        }
    }

    // ----------------------
    // Conteo de paradas (velocidad = 0)
    // ----------------------
    public static int contarParadasBus(ArrayList<GPSData> lista, String busId) {
        int paradas = 0;

        for (GPSData dato : lista) {
            if (dato.getBusId().equals(busId) && dato.getSpeed() == 0) {
                paradas++;
            }
        }

        return paradas;
    }
}
