package util;

import modelo.GPSData;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Clase que genera datos GPS simulados para 3 autobuses ficticios (BUS01–BUS03).
 * Forma parte de la fase de captura o generación de datos.
 *
 * Aunque el proyecto se basa ahora principalmente en rutas reales (BUS22, BUS5 y BUS27),
 * mantuve esta clase creada muy al comienzo del proyecto como ejemplo original para simular
 * trayectos aleatorios. Genera 60 minutos de recorrido por bus con coordenadas y paradas ficticias. */

public class GeneradorDatosBus {

    // ----------------------------------------------------
    // MÉTODO PRINCIPAL: GENERA LOS DATOS GPS SIMULADOS
    // ----------------------------------------------------
    public static ArrayList<GPSData> generar() {
        ArrayList<GPSData> listaDatos = new ArrayList<>();

        String[] buses = {"BUS01", "BUS02", "BUS03"};
        int[] minutosParada = {5, 15, 25, 35, 45};
        LocalDateTime inicio = LocalDateTime.of(2025, 4, 17, 8, 0);

        for (String busId : buses) {
            LocalDateTime marcaTiempo = inicio;

            for (int i = 0; i < 60; i++) {
                String timestamp = marcaTiempo.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

                // Coordenadas aleatorias dentro de Sevilla
                double latitude = 37.38 + Math.random() * 0.01;
                double longitude = -5.99 + Math.random() * 0.01;

                // Velocidad entre 10 y 50 km/h, o 0 si es parada
                double speed = 10 + Math.random() * 40;
                for (int minuto : minutosParada) {
                    if (i == minuto) {
                        speed = 0;
                        break;
                    }
                }

                listaDatos.add(new GPSData(busId, timestamp, latitude, longitude, speed));
                marcaTiempo = marcaTiempo.plusMinutes(1);
            }
        }

        return listaDatos;
    }
}
