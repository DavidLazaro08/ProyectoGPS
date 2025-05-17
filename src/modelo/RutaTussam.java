package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/* Clase RutaTussam
 *
 * Contiene rutas simuladas basadas en líneas reales de autobús urbano de Sevilla
 * que personalmente suelo usar: BUS22, BUS5 y BUS27.
 *
 * Cada método genera un recorrido GPS completo entre distintas paradas,
 * incluyendo pasos intermedios con una velocidad constante de 30 km/h
 * y paradas fijas con velocidad 0 km/h.
 *
 * Las coordenadas han sido seleccionadas manualmente desde Google Maps
 * y se corresponden con paradas reales que suelo utilizar en Sevilla. */

public class RutaTussam {

    // ----------------------------------------------------
    // RUTA PARA LÍNEA 22: Parque Alcosa → Ponce de León
    // ----------------------------------------------------
    public static List<GPSData> generarRutaLinea22(int pasosEntreParadas) {
        List<GPSData> ruta = new ArrayList<>();

        List<Parada> paradas = List.of(
                new Parada("Andalucía Residencial", 37.395175, -5.900131),
                new Parada("Av. de las Ciencias (Centro de Salud)", 37.389800, -5.912000),
                new Parada("Av. de Andalucía (Los Arcos)", 37.382500, -5.970000),
                new Parada("Av. de Andalucía (TUSSAM)", 37.377204, -5.977693),
                new Parada("Eduardo Dato (Estadio Sánchez Pizjuán)", 37.385000, -5.970000),
                new Parada("Gran Plaza", 37.383000, -5.965000),
                new Parada("Luis Montoto (Nervión)", 37.386975, -5.973944),
                new Parada("Puerta de Carmona", 37.387905, -5.983290),
                new Parada("Ponce de León", 37.392323, -5.987849)
        );

        LocalDateTime tiempo = LocalDateTime.of(2025, 5, 17, 8, 0);
        DateTimeFormatter formato = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        for (int i = 0; i < paradas.size() - 1; i++) {
            Parada origen = paradas.get(i);
            Parada destino = paradas.get(i + 1);

            double lat1 = origen.getLatitud();
            double lon1 = origen.getLongitud();
            double lat2 = destino.getLatitud();
            double lon2 = destino.getLongitud();

            for (int paso = 0; paso < pasosEntreParadas; paso++) {
                double lat = lat1 + (lat2 - lat1) * paso / pasosEntreParadas;
                double lon = lon1 + (lon2 - lon1) * paso / pasosEntreParadas;

                ruta.add(new GPSData("BUS22", tiempo.format(formato), lat, lon, 30));
                tiempo = tiempo.plusMinutes(1);
            }

            ruta.add(new GPSData("BUS22", tiempo.format(formato), lat2, lon2, 0));
            tiempo = tiempo.plusMinutes(1);
        }

        return ruta;
    }

    // ----------------------------------------------------
    // RUTA PARA LÍNEA 5: Torre Sevilla → Santa Aurelia
    // ----------------------------------------------------
    public static List<GPSData> generarRutaLinea5(int pasosEntreParadas) {
        List<GPSData> ruta = new ArrayList<>();

        List<Parada> paradas = List.of(
                new Parada("Camino de los Descubrimientos (Torre Sevilla)", 37.398056, -6.003611),
                new Parada("Ronda de Triana (Manuel Arellano)", 37.384722, -6.003333),
                new Parada("República Argentina (Plaza de Cuba)", 37.377778, -6.001389),
                new Parada("Palos de la Frontera (Teatro Lope de Vega)", 37.377222, -5.987222),
                new Parada("Av. de Andalucía (TUSSAM)", 37.377204, -5.977693),
                new Parada("La Plata", 37.374030, -5.973383),
                new Parada("Ronda del Tamarguillo", 37.368716, -5.964876),
                new Parada("Av. de la Paz", 37.363133, -5.949992),
                new Parada("Santa Aurelia", 37.358569, -5.937757)
        );

        LocalDateTime tiempo = LocalDateTime.of(2025, 5, 17, 9, 0);
        DateTimeFormatter formato = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        for (int i = 0; i < paradas.size() - 1; i++) {
            Parada origen = paradas.get(i);
            Parada destino = paradas.get(i + 1);

            double lat1 = origen.getLatitud();
            double lon1 = origen.getLongitud();
            double lat2 = destino.getLatitud();
            double lon2 = destino.getLongitud();

            for (int paso = 0; paso < pasosEntreParadas; paso++) {
                double lat = lat1 + (lat2 - lat1) * paso / pasosEntreParadas;
                double lon = lon1 + (lon2 - lon1) * paso / pasosEntreParadas;

                ruta.add(new GPSData("BUS5", tiempo.format(formato), lat, lon, 30));
                tiempo = tiempo.plusMinutes(1);
            }

            ruta.add(new GPSData("BUS5", tiempo.format(formato), lat2, lon2, 0));
            tiempo = tiempo.plusMinutes(1);
        }

        return ruta;
    }

    // ----------------------------------------------------
    // RUTA PARA LÍNEA 27: Sevilla Este → Museo
    // ----------------------------------------------------
    public static List<GPSData> generarRutaLinea27(int pasosEntreParadas) {
        List<GPSData> ruta = new ArrayList<>();

        List<Parada> paradas = List.of(
                new Parada("Sevilla Este (IES Séneca)", 37.397200, -5.883900),
                new Parada("Av. de las Ciencias (Centro Cívico)", 37.395000, -5.892000),
                new Parada("Av. Aeronáutica (Andalucía Residencial)", 37.395175, -5.900131),
                new Parada("Palacio de Congresos", 37.392238, -5.928694),
                new Parada("Luis Montoto (Nervión)", 37.386975, -5.973944),
                new Parada("Puerta de Carmona", 37.387905, -5.983290),
                new Parada("Estación de San Bernardo", 37.382200, -5.984000),
                new Parada("Plaza del Duque", 37.392930, -5.995634),
                new Parada("Plaza del Museo", 37.391200, -6.000000)
        );

        LocalDateTime tiempo = LocalDateTime.of(2025, 5, 17, 10, 0);
        DateTimeFormatter formato = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        for (int i = 0; i < paradas.size() - 1; i++) {
            Parada origen = paradas.get(i);
            Parada destino = paradas.get(i + 1);

            double lat1 = origen.getLatitud();
            double lon1 = origen.getLongitud();
            double lat2 = destino.getLatitud();
            double lon2 = destino.getLongitud();

            for (int paso = 0; paso < pasosEntreParadas; paso++) {
                double lat = lat1 + (lat2 - lat1) * paso / pasosEntreParadas;
                double lon = lon1 + (lon2 - lon1) * paso / pasosEntreParadas;

                ruta.add(new GPSData("BUS27", tiempo.format(formato), lat, lon, 30));
                tiempo = tiempo.plusMinutes(1);
            }

            ruta.add(new GPSData("BUS27", tiempo.format(formato), lat2, lon2, 0));
            tiempo = tiempo.plusMinutes(1);
        }

        return ruta;
    }
}
