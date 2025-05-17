package modelo;

import java.util.List;

/* Clase ParadasInfo
 *
 * Contiene las paradas reales para cada línea de autobús del sistema.
 * Se utiliza para mostrar información sobre las rutas simuladas de TUSSAM
 * (BUS22, BUS5 y BUS27), y enlazar coordenadas con nombres de paradas reales. */

public class ParadasInfo {

    // ----------------------------------------------------
    // MÉTODO PARA OBTENER LAS PARADAS DE UNA LÍNEA
    // ----------------------------------------------------
    public static List<Parada> getParadasLinea(String busId) {
        return switch (busId) {
            case "BUS22" -> List.of(
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
            case "BUS5" -> List.of(
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
            case "BUS27" -> List.of(
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
            default -> List.of(); // Línea no definida
        };
    }

    // ----------------------------------------------------
    // MÉTODO PARA MOSTRAR LAS PARADAS POR PANTALLA
    // ----------------------------------------------------
    public static void mostrarParadas(String busId) {
        List<Parada> paradas = getParadasLinea(busId);

        if (paradas.isEmpty()) {
            System.out.println("⚠️  No hay paradas registradas para la línea " + busId);
            return;
        }

        System.out.println("\nParadas asociadas a la línea " + busId + ":");
        for (Parada p : paradas) {
            System.out.printf("• %s → (%.6f, %.6f)%n", p.getNombre(), p.getLatitud(), p.getLongitud());
        }
    }
}
