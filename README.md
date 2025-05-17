# Proyecto GPS (Simulación de autobuses)

Este proyecto corresponde a la asignatura de Digitalización. Su objetivo es simular un sistema de seguimiento de autobuses mediante datos GPS generados y gestionados desde Java.

---

## ¿Qué hace el programa actualmente?

- Simula el recorrido de 3 autobuses durante una hora, generando un dato por minuto.
- Cada autobús tiene 9 paradas simuladas (velocidad = 0), basadas en rutas reales de TUSSAM.
- Guarda los datos en un archivo CSV (`gps_data.csv`).
- Lee el archivo CSV y permite realizar varias operaciones sobre los datos:
  - Ver los últimos movimientos de cada autobús.
  - Consultar una línea concreta.
  - Filtrar registros entre dos horas determinadas.
  - Calcular la velocidad media por autobús.
  - Contar cuántas veces ha parado cada bus.
  - Exportar la última posición conocida de cada autobús a archivos JSON.
- Gestión de errores y validaciones para evitar fallos por entradas inválidas.
- Menú interactivo diferenciado para usuarios y técnicos, con controles para generar, leer, analizar y modificar datos.

---

## Resumen del ciclo de vida del dato

Para un detalle más completo sobre las fases del ciclo del dato implementadas, se puede consultar el archivo [`Resumen_Ciclo_Dato.md`](Resumen_Ciclo_Dato.md). En él se explica paso a paso cómo se ha trabajado en cada fase:

- Captura y generación de datos (ficticios y rutas reales).
- Almacenamiento en formato CSV.
- Procesamiento y filtrado con validaciones.
- Análisis básico de velocidad y paradas.
- Distribución mediante exportación a JSON.
- Visualización en consola con menús.
- Depuración y limpieza del código.

---

## 📂 Archivos principales y su función

### Modelo
- `modelo/GPSData.java`  
  Representa cada registro GPS de un autobús (busId, timestamp, lat/lon, velocidad).
- `modelo/Parada.java`  
  Clase que define una parada (nombre y coordenadas).
- `modelo/ParadasInfo.java`  
  Gestiona el listado de paradas reales por línea y las muestra por pantalla.
- `modelo/RutaTussam.java`  
  Genera las rutas reales simuladas de las líneas BUS22, BUS5 y BUS27.

### Utilidades
- `util/GeneradorDatosBus.java`  
  Genera datos GPS aleatorios para tres autobuses ficticios (BUS01–BUS03).
- `util/ExportadorCSV.java`  
  Exporta una lista de `GPSData` a un archivo CSV.
- `util/LectorCSV.java`  
  Carga de disco un CSV de `GPSData` y lo convierte en objetos Java.
- `util/ExportadorJSON.java`  
  Crea archivos JSON con la última posición de cada autobús.
- `util/Depurar.java`  
  Métodos de validación y lectura segura de entradas de usuario (menús, números, fechas, coordenadas).
- `util/Archivador.java`  
  Mueve los CSV antiguos a la carpeta `archivados/`.

### Servicio
- `servicio/ProcesadorDatos.java`  
  Funciones de filtrado y modificación de listas de `GPSData` (por bus, rango horario, simulación de cambio de ruta).
- `servicio/AnalizadorDatos.java`  
  Métodos de análisis (velocidad media, conteo de paradas).

### Menú e inicio
- `menu/MenuGPS.java`  
  Implementa los menús de usuario y técnico, orquesta el flujo de ejecución.
- `Main.java`  
  Clase principal que arranca la aplicación.


---

## Estado actual y posibles mejoras

- El proyecto cubre las fases básicas del ciclo de vida del dato con un programa funcional y probado.  
- Para un detalle completo de cada fase, consulta [`Resumen_Ciclo_Dato.md`](Resumen_Ciclo_Dato.md).  
- Se ha logrado una experiencia de usuario coherente y clara mediante menús y validaciones.  

Como mejoras futuras se contemplan:
- Añadir una interfaz gráfica sencilla para visualización.
- Implementar conexión a base de datos real (MySQL u otra).
- Automatizar procesos para mejorar usabilidad.
- Añadir funcionalidades adicionales de análisis o simulación.

> **Nota:** Los archivos de salida (`gps_data.csv`, `bus*_status.json`, etc.) se generan en el directorio de trabajo.


---

## Información de contacto

**Desarrollador:** David Gutiérrez  
[GitHub: DavidLazaro08](https://github.com/DavidLazaro08)

---

*Proyecto desarrollado en Java con IntelliJ IDEA para la asignatura de Digitalización.*
