
package menu;

import java.util.ArrayList;
import java.util.Scanner;

import modelo.GPSData;
import modelo.ParadasInfo;
import modelo.RutaTussam;
import servicio.AnalizadorDatos;
import servicio.ProcesadorDatos;
import util.*;

/** Clase que gestiona los menús principales del sistema GPS Bus.
 *  Incluye menús para el usuario (seguimiento de autobuses) y técnico (análisis y simulación).
 *  Aquí se controla el flujo general del programa y se centraliza el acceso a los datos cargados. */

public class MenuGPS {

    // Lista que almacena todos los datos GPS cargados o generados
    private static ArrayList<GPSData> datosLeidos = new ArrayList<>();

    // ----------------------------------------------------
    // MÉTODO PRINCIPAL DEL MENÚ
    // ----------------------------------------------------
    public static void mostrarMenuPrincipal() {
        Scanner leer = new Scanner(System.in);
        int opcion;

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║   Bienvenido al sistema de GPS Bus   ║");
        System.out.println("╚══════════════════════════════════════╝");

        do {
            System.out.println("\n--- MENÚ PRINCIPAL GPS ---");
            System.out.println("1. 👤 Usuario: Seguimiento de autobuses");
            System.out.println("2. 🛠️  Técnico: Análisis y gestión");
            System.out.println("3. Salir");
            System.out.print("Introduce una opción: ");
            opcion = Depurar.leerOpcionMenu(leer);

            switch (opcion) {
                case 1:
                    mostrarMenuUsuario(leer);
                    break;
                case 2:
                    mostrarMenuTecnico(leer);
                    break;
                case 3:
                    System.out.println("¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 3);
    }

    // ----------------------------------------------------
    // MÉTODO AUXILIAR PARA ESPERAR ENTER
    // ----------------------------------------------------
    private static void esperarEnter(Scanner leer) {
        System.out.println("\nPulsa ENTER para continuar...");
        leer.nextLine();
    }

    // ----------------------------------------------------
    // MÉTODO PARA ACTUALIZAR LOS DATOS LEÍDOS
    // ----------------------------------------------------
    public static void setDatosLeidos(ArrayList<GPSData> nuevosDatos) {
        datosLeidos = nuevosDatos;
    }

    // ----------------------------------------------------
    // MENÚ PARA EL USUARIO FINAL
    // ----------------------------------------------------
    private static void mostrarMenuUsuario(Scanner leer) {
        int opcion;
        boolean volver = false;

        do {
            System.out.println("\n--- SEGUIMIENTO DE LOS AUTOBUSES ---");
            System.out.println("1. Ver seguimiento en tiempo real (últimos movimientos)");
            System.out.println("2. Consultar una línea concreta");
            System.out.println("3. Volver al menú principal");
            System.out.print("Introduce una opción: ");
            opcion = Depurar.leerOpcionMenu(leer);

            switch (opcion) {
                case 1:
                    if (datosLeidos.isEmpty()) {
                        System.out.println("⚠️  No hay datos cargados. Usa primero la opción 1 del menú técnico para generar los datos.");
                        esperarEnter(leer);
                        break;
                    }

                    System.out.println("\nÚltimos 3 movimientos de cada autobús:\n");

                    for (String busId : new String[]{"BUS22", "BUS5", "BUS27"}) {
                        ArrayList<GPSData> filtrados = ProcesadorDatos.filtrarPorBus(datosLeidos, busId);
                        if (!filtrados.isEmpty()) {
                            System.out.println("→ " + busId + ":");
                            for (int i = Math.max(0, filtrados.size() - 3); i < filtrados.size(); i++) {
                                System.out.println(filtrados.get(i));
                            }
                            System.out.println();
                        }
                    }

                    esperarEnter(leer);
                    break;

                case 2:
                    if (datosLeidos.isEmpty()) {
                        System.out.println("⚠️  No hay datos cargados. Usa primero la opción 1 del menú técnico para generar los datos.");
                        esperarEnter(leer);
                        break;
                    }

                    boolean volverSubmenu = false;
                    while (!volverSubmenu) {
                        System.out.println("\nSelecciona la línea que quieres consultar:");
                        System.out.println("1. BUS22");
                        System.out.println("2. BUS5");
                        System.out.println("3. BUS27");
                        System.out.println("4. Volver");
                        System.out.print("Opción: ");
                        String opcionLinea = leer.nextLine().trim();

                        String busIdElegido = "";

                        switch (opcionLinea) {
                            case "1":
                                busIdElegido = "BUS22";
                                break;
                            case "2":
                                busIdElegido = "BUS5";
                                break;
                            case "3":
                                busIdElegido = "BUS27";
                                break;
                            case "4":
                                volverSubmenu = true;
                                continue;
                            default:
                                System.out.println("❌ Opción no válida. Inténtalo de nuevo.");
                                continue;
                        }

                        ArrayList<GPSData> lineaSeleccionada = ProcesadorDatos.filtrarPorBus(datosLeidos, busIdElegido);
                        if (lineaSeleccionada.isEmpty()) {
                            System.out.println("⚠️  No se encontraron datos para " + busIdElegido);
                            System.out.println("Es posible que se hayan generado datos ficticios (BUS01-03).");
                            System.out.println("Prueba a generar antes las rutas reales desde el menú técnico, opción 1 → tipo 2.");
                        } else {
                            // Mostramos las paradas asociadas a esa línea
                            ParadasInfo.mostrarParadas(busIdElegido);
                            System.out.println("\n📍 Datos registrados:");

                            // Mostramos los datos GPS, marcando si es parada
                            for (GPSData d : lineaSeleccionada) {
                                System.out.print(d);
                                if (d.getSpeed() == 0.0) {
                                    System.out.print("  ← 🅿️ Parada");
                                }
                                System.out.println();
                            }
                        }

                        esperarEnter(leer);
                    }
                    break;

                case 3:
                    System.out.println("Volviendo al menú principal...");
                    volver = true;
                    break;

                default:
                    System.out.println("Opción no válida.");
                    esperarEnter(leer);
            }

        } while (!volver);
    }

    // ----------------------------------------------------
    // MENÚ TÉCNICO COMPLETO
    // ----------------------------------------------------
    private static void mostrarMenuTecnico(Scanner leer) {
        int opcion;
        boolean volver = false;

        do {
            System.out.println("\n--- ANÁLISIS Y GESTIÓN TÉCNICA ---");
            System.out.println("1. Generar datos y guardar CSV");
            System.out.println("2. Leer archivo CSV");
            System.out.println("3. Analizar recorrido entre dos horas (por rango horario)");
            System.out.println("4. Calcular velocidad media por bus");
            System.out.println("5. Contar paradas por bus");
            System.out.println("6. Exportar última posición (JSON)");
            System.out.println("7. Simular cambio de recorrido de un autobús");
            System.out.println("8. Archivar archivos CSV antiguos");
            System.out.println("9. Volver al menú principal");
            System.out.print("Introduce una opción: ");
            opcion = Depurar.leerOpcionMenu(leer);

            switch (opcion) {
                case 1:
                    System.out.println("\n¿Qué tipo de datos quieres generar?");
                    System.out.println("1. Datos aleatorios (BUS01, BUS02, BUS03)");
                    System.out.println("2. Rutas reales TUSSAM (BUS22, BUS5, BUS27)");
                    System.out.print("Opción: ");
                    String tipoDatos = leer.nextLine().trim();

                    ArrayList<GPSData> datos = new ArrayList<>();

                    switch (tipoDatos) {
                        case "1":
                            datos = GeneradorDatosBus.generar();
                            ExportadorCSV.guardar(datos, "gps_data.csv");
                            setDatosLeidos(datos);
                            System.out.println("✔ Datos aleatorios generados y guardados.");
                            break;
                        case "2":
                            datos.addAll(RutaTussam.generarRutaLinea22(7));
                            datos.addAll(RutaTussam.generarRutaLinea5(7));
                            datos.addAll(RutaTussam.generarRutaLinea27(7));
                            ExportadorCSV.guardar(datos, "gps_data.csv");
                            setDatosLeidos(datos);
                            System.out.println("✔ Rutas TUSSAM reales generadas y guardadas.");
                            break;
                        default:
                            System.out.println("❌ Opción no válida.");
                            break;
                    }

                    esperarEnter(leer);
                    break;

                case 2:
                    System.out.println("\nLeyendo datos desde 'gps_data.csv'...");
                    datosLeidos = LectorCSV.leer("gps_data.csv");
                    if (!datosLeidos.isEmpty()) {
                        System.out.println("Mostrando los primeros registros:");
                        for (int i = 0; i < 5 && i < datosLeidos.size(); i++) {
                            System.out.println(datosLeidos.get(i));
                        }
                    } else {
                        System.out.println("No se encontraron datos para mostrar.");
                    }
                    esperarEnter(leer);
                    break;

                case 3:
                    if (datosLeidos.isEmpty()) {
                        System.out.println("⚠️  No hay datos cargados. Usa primero la opción 2 del menú técnico para leer el archivo CSV.");
                        esperarEnter(leer);
                        break;
                    }
                    String inicio, fin;
                    do {
                        System.out.print("\nIntroduce hora de inicio (formato 2025-04-17T08:10:00): ");
                        inicio = leer.nextLine();
                    } while (!Depurar.timestampValido(inicio));
                    do {
                        System.out.print("Introduce hora final (formato 2025-04-17T08:30:00): ");
                        fin = leer.nextLine();
                    } while (!Depurar.timestampValido(fin));
                    ArrayList<GPSData> filtrados = ProcesadorDatos.filtrarPorRangoHorario(datosLeidos, inicio, fin);
                    if (filtrados.isEmpty()) {
                        System.out.println("No hay datos en ese rango horario.");
                    } else {
                        for (GPSData dato : filtrados) {
                            System.out.println(dato);
                        }
                    }
                    esperarEnter(leer);
                    break;

                case 4:
                    if (datosLeidos.isEmpty()) {
                        System.out.println("⚠️  No hay datos cargados. Usa primero la opción 2 del menú técnico para leer el archivo CSV.");
                        esperarEnter(leer);
                        break;
                    }
                    System.out.println("\nCalculando velocidad media por bus:");
                    for (String busId : new String[]{"BUS22", "BUS5", "BUS27"}) {
                        double media = AnalizadorDatos.velocidadMediaBus(datosLeidos, busId);
                        System.out.printf("Velocidad media de %s: %.2f km/h%n", busId, media);
                    }
                    esperarEnter(leer);
                    break;

                case 5:
                    if (datosLeidos.isEmpty()) {
                        System.out.println("⚠️  No hay datos cargados. Usa primero la opción 2 del menú técnico para leer el archivo CSV.");
                        esperarEnter(leer);
                        break;
                    }

                    System.out.println("\n📊 Paradas detectadas por cada autobús (velocidad = 0):");
                    System.out.println("(No se cuenta la parada inicial de subida)\n");

                    for (String busId : new String[]{"BUS22", "BUS5", "BUS27"}) {
                        int contador = 0;
                        System.out.println("🚌 " + busId + " — paradas registradas:");

                        for (GPSData dato : datosLeidos) {
                            if (dato.getBusId().equals(busId) && dato.getSpeed() == 0.0) {
                                contador++;
                                System.out.printf("  • %s → (%.6f, %.6f)%n", dato.getTimestamp(), dato.getLatitude(), dato.getLongitude());
                            }
                        }

                        System.out.printf("  → Total: %d paradas (sin contar la de inicio)%n%n", contador);
                    }

                    esperarEnter(leer);
                    break;


                case 6:
                    if (datosLeidos.isEmpty()) {
                        System.out.println("⚠️  No hay datos cargados. Usa primero la opción 2 del menú técnico para leer el archivo CSV.");
                        esperarEnter(leer);
                        break;
                    }
                    System.out.println("\nExportando última posición conocida de cada autobús a JSON...");
                    ExportadorJSON.exportarUltimaPosicion(datosLeidos);
                    System.out.println("Archivos JSON generados correctamente.");
                    esperarEnter(leer);
                    break;

                case 7:
                    if (datosLeidos.isEmpty()) {
                        System.out.println("⚠️  No hay datos cargados. Usa primero la opción 2 del menú técnico para leer el archivo CSV.");
                        esperarEnter(leer);
                        break;
                    }
                    System.out.print("\nIntroduce el ID del autobús para modificar su recorrido (ej: BUS22): ");
                    String busId = leer.nextLine().trim().toUpperCase();
                    ProcesadorDatos.simularCambioRecorrido(datosLeidos, busId);
                    esperarEnter(leer);
                    break;

                case 8:
                    System.out.println("Iniciando archivado de archivos CSV...");
                    Archivador.archivarCSV();
                    esperarEnter(leer);
                    break;

                case 9:
                    System.out.println("Volviendo al menú principal...");
                    volver = true;
                    break;

                default:
                    System.out.println("Opción no válida.");
                    esperarEnter(leer);
            }

        } while (!volver);
    }
}
