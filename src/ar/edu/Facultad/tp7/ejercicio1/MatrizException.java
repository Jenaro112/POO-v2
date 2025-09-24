package ar.edu.Facultad.tp7.ejercicio1;

public class MatrizException extends Exception {

    //*PUNTO 1 */
    public static final int EXCEEDE_COLUMNAS = 0;                   //Agregue constantes publicas y finales para los tipos de errores
    public static final int EXCEEDE_FILAS = 1;                      //Agregue constantes publicas y finales para los tipos de errores
    public static final int DIMENSIONES_INVALIDAS = 2;              //Agregue constantes publicas y finales para los tipos de errores
    public static final int INDICE_FILA_FUERA_DE_RANGO = 3;         //Agregue constantes publicas y finales para los tipos de errores
    public static final int INDICE_COLUMNA_FUERA_DE_RANGO = 4;      //Agregue constantes publicas y finales para los tipos de errores
    public static final int DIMENSIONES_NO_COINCIDEN = 5;           //Agregue constantes publicas y finales para los tipos de errores

    //*PUNTO 2 */
    private static String Errores[] = {"Excede las columnas", //Modifique el array de mensajes de error para que incluya los nuevos errores
        "Excede las filas",
        "Dimensiones inválidas (negativas o cero)",
        "Índice de fila fuera de rango",
        "Índice de columna fuera de rango",
        "Las dimensiones no coinciden para la operación"};
    private String name;

    public MatrizException(String nom) {
        name = nom;
    }

    public MatrizException(int numError) {
        name = Errores[numError];
    }

    //? Códigos de color ANSI para la consola.
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    @Override
    public String toString() {
        return ANSI_RED + "ERROR MATRIZ : " + name + ANSI_RESET;
    }
}
