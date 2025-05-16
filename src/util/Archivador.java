package util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/* Vi un ejemplo en el que se usaba "lastModified()" para mover solo
   los archivos .csv antiguos (según su fecha), pero he optado por una versión
   más sencilla donde básicamente se mueven todos los archivos que ya estén en
   la carpeta al iniciar el programa.
 *
 * Por un lado, porque considero que si el archivo existe antes de ejecutar, es
   porque fue generado en una sesión anterior, así que lo trato como "antiguo",
   y por otro, porque honestamente no tengo idea de cómo va el tema, y prefiero
   centrarme en simular esta fase de archivado sin complicar el código de forma absurda. */

public class Archivador {

        public static void archivarCSV() {
        File carpetaActual = new File(".");
        File carpetaArchivados = new File("archivados");

        // Crear la carpeta "archivados" si no existe
        if (!carpetaArchivados.exists()) {
            boolean creada = carpetaArchivados.mkdir();
            if (creada) {
                System.out.println("Carpeta 'archivados' creada.");
            }
        }

        // Mover todos los archivos .csv a "archivados"
        File[] archivosCSV = carpetaActual.listFiles((dir, nombre) -> nombre.endsWith(".csv"));
        if (archivosCSV == null) return;

        for (File archivo : archivosCSV) {
            try {
                Files.move(
                        archivo.toPath(),
                        new File(carpetaArchivados, archivo.getName()).toPath(),
                        StandardCopyOption.REPLACE_EXISTING
                );
                System.out.println(" Archivado: " + archivo.getName());
            } catch (IOException e) {
                System.err.println("❌ Error al archivar " + archivo.getName() + ": " + e.getMessage());
            }
        }
    }
}

