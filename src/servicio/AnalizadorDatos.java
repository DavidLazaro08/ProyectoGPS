package servicio;

import modelo.GPSData;
import java.util.ArrayList;

/* Esta clase analizará los datos GPS una vez procesados o filtrados.
 * Se centrará en hacer cálculos útiles como ver cuál es la velocidad media por autobús
 * o el número de paradas (velocidad = 0)
 * Formaría parte de la fase 4 del ciclo del dato: Análisis. */

public class AnalizadorDatos {

    // MÉTODO: Calcula la velocidad media de un autobús concreto
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
            return 0;
        }
    }

    // MÉTODO: Cuenta cuántas paradas (velocidad = 0) ha hecho un autobús concreto
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
