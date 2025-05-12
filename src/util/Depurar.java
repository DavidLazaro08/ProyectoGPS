package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Depurar {

    // Método para asegurarnos de que el usuario deja un número y no una letra.
    public static int leerOpcionMenu(Scanner leer) {
        int opcion;
        try {
            opcion = Integer.parseInt(leer.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("❌ Eso no es un número válido. Intenta otra vez.");
            opcion = -1;
        }
        return opcion;
    }


    // Valida que un timestamp tenga el formato ISO_LOCAL_DATE_TIME
    public static boolean timestampValido(String timestamp) {
        try {
            LocalDateTime.parse(timestamp, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            return true;
        } catch (Exception e) {
            System.out.println("⚠️  Fecha/hora inválida. Usa el formato correcto: 2025-04-17T08:10:00");
            return false;
        }
    }

    // Comprueba si unas coordenadas están dentro de un rango lógico (zona Sevilla)
    public static boolean coordenadasValidas(double lat, double lon) {
        return lat >= 37.30 && lat <= 37.45 && lon >= -6.05 && lon <= -5.95;
    }

    // Método para pedir datos numéricos o velocidades sin que se rompa el programa.
    public static double leerDoubleValido(Scanner leer, String mensaje) {
        double valor = 0;
        boolean valido = false;
        while (!valido) {
            System.out.print(mensaje);
            try {
                valor = Double.parseDouble(leer.nextLine());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("❌ Eso no es un número decimal válido.");
            }
        }
        return valor;
    }


}

