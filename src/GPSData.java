
/* Esta clase representa un único dato GPS de un autobús.
 * Forma parte de la primera fase del proyecto (Captura/Generación),
 * y sirve para guardar cada registro con su identificador, fecha/hora,
 * coordenadas y velocidad.*/

public class GPSData {

    // DEFINICIÓN DE VARIABLES:

    // Identificador de cada autobús (por ejemplo: "BUS01").
    private String busId;

    /* La fecha y hora en la que se ha registrado un dato.
    * Usaremos el formato "ISO 8601", al ser un estándar que nos
    * ayudará en la lectura, compatibilidad...*/
    private String timestamp;


    // Coordenadas del autobús en cada momento.
    private double latitude;
    private double longitude;

    // La velocidad en km/h.
    private double speed;

    // -----------------------------------------------------------------------------

    // CONSTRUCTOR DE LA CLASE GPSDATA.
    // Lo usaremos para crear el objeto con todos los datos juntos.

    public GPSData(String busId, String timestamp, double latitude, double longitude, double speed) {
        this.busId = busId;
        this.timestamp = timestamp;
        this.latitude = latitude;
        this.longitude = longitude;
        this.speed = speed;
    }

    // -----------------------------------------------------------------------------

    // MÉTODOS GETTER
    // Desde los que acceder a cada dato por separado (si hace falta).

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

    // -----------------------------------------------------------------------------

    // MÉTODO TOSTRING
    // Para mostrar el contenido del objeto como una línea de texto CSV.
    // con los valores separados por comas (formato típico de archivos CSV).

    @Override
    public String toString() {
        return busId + "," + timestamp + "," + latitude + "," + longitude + "," + speed;
    }
}
