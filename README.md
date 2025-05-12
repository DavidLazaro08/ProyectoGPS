# Proyecto GPS (Simulación de autobuses)

Este proyecto es para la asignatura de Digitalización. La idea es simular cómo funcionaría un sistema de seguimiento de autobuses usando datos GPS, generados y gestionados desde Java.

## ¿Qué hace el programa actualmente?

- Simula el recorrido de 3 autobuses durante una hora, generando un dato por minuto.
- Cada autobús tiene 5 paradas simuladas (velocidad = 0).
- Guarda los datos en un archivo CSV (`gps_data.csv`).
- Lee el archivo CSV y permite hacer distintas operaciones sobre los datos:
  - Ver los últimos movimientos de cada autobús.
  - Consultar una línea concreta.
  - Filtrar registros entre dos horas determinadas.
  - Calcular la velocidad media por autobús.
  - Contar cuántas veces ha parado cada bus.
  - Exportar la última posición conocida de cada autobús a archivos JSON.

## Archivos principales:

- `modelo/GPSData.java`: Representa cada dato GPS.
- `util/GeneradorDatosBus.java`: Crea los datos simulados.
- `util/ExportadorCSV.java`: Guarda los datos en CSV.
- `util/LectorCSV.java`: Carga datos desde un archivo CSV.
- `util/ExportadorJSON.java`: Guarda última posición de cada bus en JSON.
- `util/Depurar.java`: Mejora el manejo de errores de entrada del usuario.
- `servicio/ProcesadorDatos.java`: Filtra y valida los datos.
- `servicio/AnalizadorDatos.java`: Analiza velocidad y paradas.
- `menu/MenuGPS.java`: Contiene el menú del usuario.
- `Main.java`: Lanza el programa.

## Estado del proyecto

+ Actualmente están implementadas varias fases del ciclo del dato: captura, almacenamiento, procesamiento, análisis básico y distribución.
+ Además, se ha añadido validación al leer los datos, control de errores de entrada del usuario mediante una clase `Depurar`, y una opción para modificar el recorrido de un autobús ya cargado, tal como se pide en la parte de mantenimiento de la actividad.
+ Como mejora futura se plantea implementar archivado automático o añadir una interfaz gráfica sencilla si da lugar.

---

Desarrollado en Java con IntelliJ IDEA.

David Gutiérrez  
[Perfil GitHub](https://github.com/DavidLazaro08)