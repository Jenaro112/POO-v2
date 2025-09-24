package ar.edu.Facultad.tp8.ejercicio2;
//|---------------------------------------------------------------|
//|                       Ejercicio Dos                           |
//|---------------------------------------------------------------|
public class TareaMultiplicacion implements Runnable { //Que sea runnable significa que se puede ejecutar en un hilo

    private final int[][] matrizA;
    private final int[][] matrizB;
    private final int[][] resultado;
    private final int fila;
    private final int columna;

    public TareaMultiplicacion(int[][] matrizA, int[][] matrizB, int[][] resultado, int fila, int columna) {
        this.matrizA = matrizA;
        this.matrizB = matrizB;
        this.resultado = resultado;
        this.fila = fila;
        this.columna = columna;
    }

    @Override
    public void run() {
        resultado[fila][columna] = 0;
        for (int k = 0; k < matrizA[0].length; k++) {                               // matrizA[0].length es igual a matrizB.length
            // Verificamos si el hilo ha sido interrumpido en cada iteración.
            if (Thread.currentThread().isInterrupted()) {
                // Si el executor llamó a shutdownNow(), este hilo será interrumpido.
                // Dejamos de procesar y salimos del método.
                return;
            }
            resultado[fila][columna] += matrizA[fila][k] * matrizB[k][columna];     // multiplicamos las filas de la matriz A por las columnas de la matriz B
        }
    }
}
