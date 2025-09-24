package ar.edu.Facultad.tp8.ejercicio3;
//|---------------------------------------------------------------|
//|                       Ejercicio Tres                          |
//|---------------------------------------------------------------|
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import ar.edu.Facultad.tp8.ejercicio2.TareaMultiplicacion; // Corrected package name
public class MultiplicacionMatricesExecutor {

    // Colores
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static void main(String[] args) {
        final int Tamano_matriz = 10;           //!NO ZARPARSE, CASI SE ME EXPLOTA LA PC :(
        Random random = new Random();           //Usamos el random para rellenar las matrices :)

        // Crear y rellenar matrices con valores aleatorios
        int[][] matrizA = new int[Tamano_matriz][Tamano_matriz];
        int[][] matrizB = new int[Tamano_matriz][Tamano_matriz];

        fillRandomMatrix(matrizA, random);
        fillRandomMatrix(matrizB, random);

        System.out.println("|---------------------------------------------------------------|");
        System.out.println("Matrices A y B de " + Tamano_matriz + "x" + Tamano_matriz + " generadas con valores aleatorios.");

        if (matrizA[0].length != matrizB.length) {
            System.out.println("Dimensiones no compatibles.");                      // No se pueden multiplicar
            return;
        }

        int filasResultado = matrizA.length;
        int columnasResultado = matrizB[0].length;
        int[][] matrizResultado = new int[filasResultado][columnasResultado];

        //? 1. Crear el ExecutorService con un número de hilos igual al de procesadores disponibles
        int numProcesadores = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(numProcesadores);

        System.out.println(ANSI_YELLOW + "Enviando " + (filasResultado * columnasResultado) + " tareas a un pool de " + numProcesadores + " hilos..." + ANSI_RESET);

        //? 2. Enviar las Tareas al pool
        for (int i = 0; i < filasResultado; i++) {
            for (int j = 0; j < columnasResultado; j++) {
                TareaMultiplicacion tarea = new TareaMultiplicacion(matrizA, matrizB, matrizResultado, i, j);
                executor.submit(tarea);             // Enviamos la tarea al executor para que la ejecute cuando un hilo esté libre
            }
        }

        //? 3. Manejar el Apagado del Executor
        executor.shutdown(); // No acepta más tareas, pero termina las que están en cola.
        try {
            // Espera a que todas las tareas terminen, con un tiempo de espera máximo.
            if (!executor.awaitTermination(120, TimeUnit.SECONDS)) {  //! Revisar, creo que no funciona
                System.err.println("Las tareas no terminaron en el tiempo esperado. Forzando apagado.");
                executor.shutdownNow();                                     // Intenta detener las tareas en ejecución.
            }
        } catch (InterruptedException e) {
            System.err.println("El hilo principal fue interrumpido mientras esperaba. Forzando apagado.");
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println();
        System.out.println(ANSI_GREEN + "Todas las tareas han finalizado. La multiplicación está completa." + ANSI_RESET);
        System.out.println("Matriz Resultado (A * B):");
        System.out.println();
        imprimirMatriz(matrizResultado);
    }

    public static void imprimirMatriz(int[][] matriz) {
        for (int[] fila : matriz) {
            for (int valor : fila) {
                System.out.print(valor + "\t");
            }
            System.out.println();
        }
    }

    private static void fillRandomMatrix(int[][] matrix, Random random) {
        for (int[] matrix1 : matrix) {
            for (int j = 0; j < matrix1.length; j++) {
                matrix1[j] = random.nextInt(51);
            }
        }
    }
}


/* 
|---------------------------------------------------------------|
1. Rendimiento: ¿Qué ventaja de rendimiento ofrece un ExecutorService?
La principal ventaja es la reutilización de hilos. Crear un hilo (new Thread()) es una operación costosa para el sistema operativo, ya que implica reservar memoria para el stack del hilo y realizar cambios de contexto. Un ExecutorService crea un número limitado de hilos una sola vez y los mantiene "vivos" en un pool. Cuando se envía una tarea, un hilo libre del pool la toma y la ejecuta. Al terminar, el hilo no muere, sino que vuelve al pool, listo para la siguiente tarea. Esto elimina por completo el costo de creación/destrucción de hilos para cada una de las miles o millones de tareas, resultando en un rendimiento drásticamente superior.

|---------------------------------------------------------------|

2. Recursos: ¿Qué ocurriría con matrices de 2000x2000?
Una multiplicación de matrices de 2000x2000 requiere calcular 4,000,000 de celdas.

Método del Ejercicio 2 (new Thread() por tarea): 
El programa intentaría crear 4 millones de hilos. Esto colapsaría casi con total seguridad la JVM y el sistema operativo, lanzando un java.lang.OutOfMemoryError: unable to create new native thread. Cada hilo consume una cantidad significativa de memoria (típicamente de 256KB a 1MB solo para su stack), por lo que crear tantos hilos agotaría rápidamente la memoria disponible.

Método del Ejercicio 3 (ExecutorService): El programa manejaría la situación de forma robusta y eficiente. Crearía 4 millones de objetos TareaMultiplicacion, que son muy ligeros en memoria. Luego, los encolaría para ser procesados por un pequeño número de hilos (ej. 8, 12 o 16, según los procesadores de tu máquina). El sistema se mantendría estable, y el rendimiento estaría limitado por la capacidad de la CPU para realizar los cálculos, no por la gestión de hilos.

|---------------------------------------------------------------|

3. Tipos de Pools: Diferencias
Executors ofrece varios métodos para crear pools con diferentes comportamientos:

Executors.newFixedThreadPool(int nThreads):
-Qué hace: Crea un pool con un número fijo de hilos. Si se envían más tareas que hilos disponibles, las tareas extra esperan en una cola.
-Cuándo usarlo: Es la opción más común y segura. Ideal para tareas que hacen un uso intensivo de la CPU (como nuestro caso), donde tener más hilos que núcleos de procesador no aporta beneficios y puede incluso degradar el rendimiento por el cambio de contexto.

Executors.newCachedThreadPool():
-Qué hace: Crea un pool que crea nuevos hilos según sea necesario y reutiliza los que están libres. Si un hilo permanece inactivo durante 60 segundos, es eliminado del pool.
-Cuándo usarlo: Bueno para ejecutar una gran cantidad de tareas cortas y asíncronas (ej. peticiones de red). Es peligroso si las tareas son largas, ya que puede crear un número ilimitado de hilos y agotar los recursos del sistema, similar al problema de new Thread().

Executors.newSingleThreadExecutor():
-Qué hace: Crea un pool con un único hilo.
-Cuándo usarlo: Garantiza que todas las tareas se ejecutan de forma secuencial, en el orden en que fueron enviadas. Es útil cuando necesitas procesar tareas en segundo plano pero sin concurrencia entre ellas (ej. escribir logs en un archivo en un orden específico).
|---------------------------------------------------------------|
 */
