# Proyecto GPS (Simulación de autobuses)

Este proyecto es para la asignatura de Digitalización. La idea es simular cómo funcionaría un sistema de seguimiento de autobuses usando datos GPS, pero todo generado desde Java.

## ¿Qué hace por ahora?

- Genera datos simulados para 3 autobuses, durante 60 minutos (1 registro por minuto).
- Cada bus tiene 5 paradas (velocidad 0).
- Se guardan todos los datos en un archivo CSV llamado `gps_data.csv`.
- Después se puede leer ese archivo y cargar los datos de nuevo.

## Archivos que forman parte del proyecto:

- `GPSData.java`: Clase con los atributos de cada dato GPS (hora, bus, latitud, etc.)
- `GeneradorDatosBus.java`: Se encarga de generar todos los datos simulados.
- `ExportadorCSV.java`: Guarda los datos en un archivo CSV.
- `LectorCSV.java`: Lee los datos desde el CSV.
- `Main.java`: Ejecuta el programa y muestra una pequeña parte por consola.

## Estado actual

Este sería el primer paso del proyecto. Más adelante se añadirán otras fases (procesamiento, análisis, etc.)

---

Desarrollado en Java usando IntelliJ IDEA. Probado con éxito.  

David Gutiérrez  
[Perfil GitHub](https://github.com/DavidLazaro08)