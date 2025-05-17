package modelo;

/** Clase que representa un único registro GPS de un autobús.
 * Contiene identificador, fecha/hora, coordenadas y velocidad.
 * Sirve como lugar desde el que obtener información o poder
 * cargar, mostrar o procesar los distintos datos. */

public class GPSData {

    // ----------------------------------------------------
    // ATRIBUTOS
    // ----------------------------------------------------
    private String busId;        // Identificador del autobús (ej. "BUS22")
    private String timestamp;    // Fecha y hora en formato ISO 8601
    private double latitude;     // Latitud del punto GPS
    private double longitude;    // Longitud del punto GPS
    private double speed;        // Velocidad en km/h

    // ----------------------------------------------------
    // CONSTRUCTOR
    // ----------------------------------------------------
    public GPSData(String busId, String timestamp, double latitude, double longitude, double speed) {
        this.busId = busId;
        this.timestamp = timestamp;
        this.latitude = latitude;
        this.longitude = longitude;
        this.speed = speed;
    }

    // ----------------------------------------------------
    // MÉTODOS GETTER
    // ----------------------------------------------------
    public String getBusId() {
        return busId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getSpeed() {
        return speed;
    }

    // ----------------------------------------------------
    // MÉTODO toString
    // ----------------------------------------------------
    /* Devuelve una representación en formato CSV del objeto GPSData.
     * Ejemplo: BUS22,2025-05-17T08:00:00,37.39,-5.90,30.0 */

    @Override
    public String toString() {
        return busId + "," + timestamp + "," + latitude + "," + longitude + "," + speed;
    }
}