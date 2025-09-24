package ar.edu.Facultad.tp8.ejercicio2;
//|---------------------------------------------------------------|
//|                       Ejercicio Dos                           |
//|---------------------------------------------------------------|
import java.util.ArrayList;
import java.util.List;

public class MultiplicacionMatrices {

    // Colores :)
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void main(String[] args) {
        // Definimos las matrices
        int[][] matrizA = {
            {5, 25, 37},
            {42, 53, -61},
            {71, 18, 0}
        };

        int[][] matrizB = {
            {9, 8, 7},
            {6, 5, 4},
            {3, 2, 1}
        };

        // Verificación de dimensiones para la multiplicación
        if (matrizA[0].length != matrizB.length) {
            System.out.println(ANSI_RED + "No se pueden multiplicar las matrices, ya que las dimensiones no son compatibles." + ANSI_RESET);
            return;
        }

        // La matriz resultado tendrá dimensiones: (filas de A) x (columnas de B)
        int filasResultado = matrizA.length;
        int columnasResultado = matrizB[0].length;
        int[][] matrizResultado = new int[filasResultado][columnasResultado];

        // Lista para almacenar los hilos que crearemos
        List<Thread> hilos = new ArrayList<>();

        System.out.println(ANSI_YELLOW + "Creando y lanzando hilos para la multiplicación..." + ANSI_RESET);

        //* División del Problema y Paso de Parámetros
        // Iteramos sobre cada celda de la matriz resultado para crear una tarea por cada una.
        for (int i = 0; i < filasResultado; i++) {          // Recorremos las filas de la matriz A      I = fila
            for (int j = 0; j < columnasResultado; j++) {   // Recorremos las columnas de la matriz B   J = columna
                // Creamos la tarea específica para la celda (i, j)
                TareaMultiplicacion tarea = new TareaMultiplicacion(matrizA, matrizB, matrizResultado, i, j);

                // Creamos un hilo (trabajador) para esa tarea y lo iniciamos
                Thread hilo = new Thread(tarea);
                hilo.start();

                // Guardamos el hilo en nuestra lista para poder esperar por él más tarde
                hilos.add(hilo);
            }
        }

        // Sincronización y Agregación de Resultados
        // Esperamos a que todos los hilos terminen su trabajo.
        for (Thread hilo : hilos) {
            try {
                hilo.join();                // El método join() bloquea la ejecución del hilo principal (main) hasta que el hilo sobre el que se llama haya terminado.
            } catch (InterruptedException e) {
                System.err.println("El hilo principal fue interrumpido: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
        System.out.println(ANSI_GREEN + "Todos los hilos han terminado. La multiplicación está completa." + ANSI_RESET);
        System.out.println();
        System.out.println("Matriz Resultado (A * B):");
        imprimirMatriz(matrizResultado);
    }

    // Método de utilidad para imprimir una matriz de forma legible
    public static void imprimirMatriz(int[][] matriz) {
        for (int[] fila : matriz) {
            for (int valor : fila) {
                System.out.print(valor + "\t");
            }
            System.out.println();
        }
    }
}
