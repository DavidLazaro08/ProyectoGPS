package modelo;

/* Clase que representa una parada de autobús.
 * Cada parada tiene un nombre, una latitud y una longitud.
 * Se utiliza principalmente en la generación de rutas reales en RutaTussam. */

public class Parada {

    // ----------------------------------------------------
    // ATRIBUTOS DE LA CLASE
    // ----------------------------------------------------

    private String nombre;     // Nombre de la parada
    private double latitud;    // Latitud en coordenadas decimales
    private double longitud;   // Longitud en coordenadas decimales

    // ----------------------------------------------------
    // CONSTRUCTOR
    // ----------------------------------------------------

    //Constructor que crea una nueva parada con su nombre y coordenadas.

    public Parada(String nombre, double latitud, double longitud) {
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    // ----------------------------------------------------
    // MÉTODOS GETTER
    // ----------------------------------------------------

    public String getNombre() {
        return nombre;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }
}
