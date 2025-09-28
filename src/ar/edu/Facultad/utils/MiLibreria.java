package ar.edu.Facultad.utils;

import java.io.Console;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MiLibreria {

    // --- CONSTANTES DE ESTILO Y COLOR (CÓDIGOS ANSI) ---
    /**
     * Restablece todo el formato a los valores predeterminados de la terminal.
     * Usar siempre al final.
     */
    public static final String RESET = "\u001B[0m";

    // --- Estilos de texto ---
    /**
     * Pone el texto en negrita.
     */
    public static final String NEGRITA = "\u001B[1m";
    /**
     * Pone el texto en subrayado.
     */
    public static final String SUBRAYADO = "\u001B[4m";

    // --- Colores de texto (Foreground) ---
    public static final String NEGRO = "\u001B[30m";
    public static final String ROJO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String AMARILLO = "\u001B[33m";
    public static final String AZUL = "\u001B[34m";
    public static final String PURPURA = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String BLANCO = "\u001B[37m";

    // --- Colores de fondo (Background) ---
    public static final String FONDO_NEGRO = "\u001B[40m";
    public static final String FONDO_ROJO = "\u001B[41m";
    public static final String FONDO_VERDE = "\u001B[42m";
    public static final String FONDO_AMARILLO = "\u001B[43m";
    public static final String FONDO_AZUL = "\u001B[44m";
    public static final String FONDO_PURPURA = "\u001B[45m";
    public static final String FONDO_CYAN = "\u001B[46m";
    public static final String FONDO_BLANCO = "\u001B[47m";

    /**
     * Imprime un header bonito para los ejercicios
     */
    public static void printHeader(String titulo) {
        int totalWidth = 50;
        String tituloUpper = titulo.toUpperCase();
        int paddingSize = (totalWidth - tituloUpper.length()) / 2;

        // Asegurarse de que el padding no sea negativo si el título es muy largo
        paddingSize = Math.max(0, paddingSize);

        String padding = " ".repeat(paddingSize);
        System.out.println("=".repeat(totalWidth));
        System.out.println(padding + NEGRITA + AMARILLO + tituloUpper + RESET);
        System.out.println("=".repeat(50));
    }

    /**
     * Imprime un separador
     */
    public static void printSeparator() {
        System.out.println("-".repeat(30));
    }

    /**
     * Limpia la pantalla de la consola.
     * <p>
     * Intenta ejecutar el comando 'clear' en sistemas Unix-like (Linux, macOS)
     * y 'cls' en Windows. Si falla (por ejemplo, en un IDE), imprime varias
     * líneas en blanco como alternativa.
     * </p>
     */
    public static void limpiarConsola() {
        try {
            // Primero, intentamos con el método de Console si está disponible
            Console console = System.console();
            if (console != null && System.getProperty("os.name").contains("Windows")) {
                // En Windows, console.writer().print("\033[H\033[2J") puede no funcionar bien
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                return;
            }

            String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Para Unix-like (Linux, macOS) y como fallback general
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException e) {
            // Si hay un error, imprimir 50 líneas en blanco para simular la limpieza.
            for (int i = 0; i < 50; ++i) {
                System.out.println();
            }
        }
    }

    /**
     * Pausa la ejecución
     */
    @SuppressWarnings("UseSpecificCatch")
    public static void pausar() {
        System.out.print("Presiona Enter para continuar...");
        try {
            System.in.read();
        } catch (Exception e) {
            // Ignorar
        }
    }

    /**
     * Valida si un número está en un rango
     */
    public static boolean enRango(int numero, int min, int max) {
        return numero >= min && numero <= max;
    }

    /**
     * Formatea un número con decimales
     */
    public static String formatearDecimal(double numero, int decimales) {
        return String.format("%." + decimales + "f", numero);
    }

    /**
     * Imprime las propiedades de un único objeto en formato de tabla (Propiedad
     * / Valor). Utiliza reflexión para obtener los nombres y valores de los
     * campos del objeto.
     *
     * @param obj El objeto a inspeccionar y mostrar.
     */
    public static void imprimirObjetoEnTabla(Object obj) {
        if (obj == null) {
            System.out.println("El objeto es nulo.");
            return;
        }

        List<String[]> rows = new ArrayList<>();
        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true); // Permite acceder a campos privados
            try {
                rows.add(new String[]{field.getName(), String.valueOf(field.get(obj))});
            } catch (IllegalAccessException e) {
                rows.add(new String[]{field.getName(), "N/A"});
            }
        }

        if (rows.isEmpty()) {
            System.out.println("El objeto no tiene campos para mostrar.");
            return;
        }

        // Calcular anchos de columna
        int[] maxWidths = {0, 0};
        String[] headers = {"Propiedad", "Valor"};
        maxWidths[0] = headers[0].length();
        maxWidths[1] = headers[1].length();

        for (String[] row : rows) {
            if (row[0].length() > maxWidths[0]) {
                maxWidths[0] = row[0].length();
            }
            if (row[1].length() > maxWidths[1]) {
                maxWidths[1] = row[1].length();
            }
        }

        // Imprimir tabla
        String format = "| %-" + maxWidths[0] + "s | %-" + maxWidths[1] + "s |%n";
        String separator = "+-" + "-".repeat(maxWidths[0]) + "-+-" + "-".repeat(maxWidths[1]) + "-+%n";

        System.out.printf(separator);
        System.out.printf(format, headers[0], headers[1]);
        System.out.printf(separator);
        for (String[] row : rows) {
            System.out.printf(format, row[0], row[1]);
        }
        System.out.printf(separator);
    }

    /**
     * Imprime una lista de objetos en un formato de tabla. Las cabeceras de la
     * tabla se generan a partir de los nombres de los campos de los objetos.
     *
     * @param lista La lista de objetos a imprimir. Debe ser una lista no vacía.
     */
    public static void imprimirListaEnTabla(List<?> lista) {
        if (lista == null || lista.isEmpty()) {
            System.out.println("La lista está vacía o es nula. No hay nada que mostrar.");
            return;
        }

        // Obtener cabeceras desde el primer objeto
        Object firstObj = lista.get(0);
        Field[] fields = firstObj.getClass().getDeclaredFields();
        String[] headers = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            headers[i] = fields[i].getName();
        }

        // Preparar datos de las filas
        List<String[]> rows = new ArrayList<>();
        for (Object obj : lista) {
            String[] row = new String[fields.length];
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true); // Acceder a campos privados
                try {
                    Object value = fields[i].get(obj);
                    row[i] = String.valueOf(value);
                } catch (IllegalAccessException e) {
                    row[i] = "N/A";
                }
            }
            rows.add(row);
        }

        // Calcular anchos de columna
        int[] maxWidths = new int[headers.length];
        for (int i = 0; i < headers.length; i++) {
            maxWidths[i] = headers[i].length();
        }
        for (String[] row : rows) {
            for (int i = 0; i < row.length; i++) {
                if (row[i].length() > maxWidths[i]) {
                    maxWidths[i] = row[i].length();
                }
            }
        }

        // Imprimir tabla
        StringBuilder format = new StringBuilder("|");
        StringBuilder separator = new StringBuilder("+");
        for (int width : maxWidths) {
            format.append(" %-").append(width).append("s |");
            separator.append("-".repeat(width + 2)).append("+");
        }
        format.append("%n");
        separator.append("%n");

        System.out.printf(separator.toString());
        System.out.printf(format.toString(), (Object[]) headers);
        System.out.printf(separator.toString());
        for (String[] row : rows) {
            System.out.printf(format.toString(), (Object[]) row);
        }
        System.out.printf(separator.toString());
    }
}
