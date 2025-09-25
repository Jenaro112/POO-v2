package ar.edu.Facultad.utils;

import java.io.Console;
import java.io.IOException;

/**
 * Librería personal de utilidades para los trabajos de la facultad
 * 
 * @author Jenaro Galdini
 * @version 2.0
 */
public class MiLibreria {

    // --- CONSTANTES DE ESTILO Y COLOR (CÓDIGOS ANSI) ---

    /** Restablece todo el formato a los valores predeterminados de la terminal. Usar siempre al final. */
    public static final String RESET = "\u001B[0m";

    // --- Estilos de texto ---
    /** Pone el texto en negrita. */
    public static final String NEGRITA = "\u001B[1m";
    /** Pone el texto en subrayado. */
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
            for (int i = 0; i < 50; ++i) System.out.println();
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
}