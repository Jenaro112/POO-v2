package ar.edu.Facultad.tp3.ejercicios;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Clase principal que agrupa todos los ejercicios del TP3.
 * Contiene un menú para ejecutar cada ejercicio de forma individual.
 */
public class Trabajo3 {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n\n-----------------------------------------");
                System.out.println("  SELECCIONE UN EJERCICIO DEL TP3");
                System.out.println("-----------------------------------------");
                System.out.println("1. Producto o suma de dos números.");
                System.out.println("2. Calificación de nota.");
                System.out.println("3. Verificador de palíndromos.");
                System.out.println("4. Operaciones con un array de enteros.");
                System.out.println("5. Propiedades de un número entero.");
                System.out.println("6. Gestión de empleados.");
                System.out.println("0. Salir.");
                System.out.println("-----------------------------------------");
                System.out.print("Ingrese su opción: ");

                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea

                switch (opcion) {
                    case 1 -> ejercicio1();
                    case 2 -> ejercicio2();
                    case 3 -> ejercicio3();
                    case 4 -> ejercicio4();
                    case 5 -> ejercicio5();
                    case 6 -> ejercicio6();
                    case 0 -> {
                        System.out.println("Saliendo del programa. ¡Hasta luego!");
                        return;
                    }
                    default -> System.out.println("Opción no válida. Por favor, intente de nuevo.");
                }
            }
        }
    }

    /**
     * Ejercicio 1: Lee tres números. Si el primero es positivo, calcula el producto de los otros dos.
     * Si no, calcula la suma.
     */
    public static void ejercicio1() {
        System.out.println("\n--- Ejecutando Ejercicio 1 ---");
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Ingrese el primer número: ");
            int num1 = scanner.nextInt();

            System.out.print("Ingrese el segundo número: ");
            int num2 = scanner.nextInt();

            System.out.print("Ingrese el tercer número: ");
            int num3 = scanner.nextInt();

            if (num1 > 0) {
                int producto = num2 * num3;
                System.out.println("El primer número es positivo. El producto de los otros dos es: " + producto);
            } else {
                int suma = num2 + num3;
                System.out.println("El primer número no es positivo. La suma de los otros dos es: " + suma);
            }
        }
    }

    /**
     * Ejercicio 2: Lee una nota del 1 al 10 y la clasifica como "Desaprobado", "Aprobado" o "Sobresaliente".
     */
    public static void ejercicio2() {
        System.out.println("\n--- Ejecutando Ejercicio 2 ---");
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Ingrese la Nota (del 1 al 10):");
            int nota = scanner.nextInt();

            if (nota > 9) {
                System.out.println("Sobresaliente");
            } else if (nota > 6) { // Simplificado de 'nota < 9 & nota > 6'
                System.out.println("Aprobado");
            } else {
                System.out.println("Desaprobado");
            }
        }
    }

    /**
     * Ejercicio 3: Lee una palabra y determina si es un palíndromo.
     */
    public static void ejercicio3() {
        System.out.println("\n--- Ejecutando Ejercicio 3 ---");
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Ingrese una palabra:");
            String palabra = scanner.nextLine();

            // Ignorar mayúsculas/minúsculas y espacios para una mejor verificación
            String palabraProcesada = palabra.replaceAll("\\s+", "").toLowerCase();
            String palindromo = new StringBuilder(palabraProcesada).reverse().toString();

            if (palabraProcesada.equals(palindromo)) {
                System.out.println("La palabra '" + palabra + "' ES un palíndromo.");
            } else {
                System.out.println("La palabra '" + palabra + "' NO es un palíndromo.");
            }
        }
    }

    /**
     * Ejercicio 4: Realiza varias operaciones sobre un array de números predefinido.
     */
    public static void ejercicio4() {
        System.out.println("\n--- Ejecutando Ejercicio 4 ---");
        ArrayDeEnteros ae = new ArrayDeEnteros();

        System.out.println("--------------------------------------------------------------");
        System.out.println("Array original: " + Arrays.toString(ae.getNumeros()));
        System.out.println("Cantidad de números: " + ae.cantidadNumeros());
        System.out.println("--------------------------------------------------------------");
        System.out.println("Número mayor: " + ae.mayorNumero());
        System.out.println("--------------------------------------------------------------");
        System.out.println("Promedio: " + ae.promedio());

        System.out.println("--------------------------------------------------------------");
        System.out.print("Orden ascendente: ");
        ae.imprimirArray(ae.ordenarAscendente());

        System.out.println("--------------------------------------------------------------");
        System.out.print("Orden descendente: ");
        ae.imprimirArray(ae.ordenarDescendente());
    }

    /**
     * Ejercicio 5: Muestra las propiedades de un número (cuadrado, par/impar, factorial, primo).
     */
    public static void ejercicio5() {
        System.out.println("\n--- Ejecutando Ejercicio 5 ---");
        Entero miEntero = new Entero(7);
        System.out.println("El numero es: " + miEntero.getNumero());
        System.out.println("El cuadrado es: " + miEntero.cuadrado());
        miEntero.ParImpar();
        System.out.println("El factorial es: " + miEntero.factorial());
        System.out.println("¿Es primo? " + miEntero.esPrimo());
    }

    /**
     * Ejercicio 6: Gestiona una lista de empleados, permitiendo registrar nombres y sueldos.
     */
    public static void ejercicio6() {
        System.out.println("\n--- Ejecutando Ejercicio 6 ---");
        GestorEmpleados gestor = new GestorEmpleados();
        gestor.cargarEmpleados();
        gestor.mostrarResultados();
    }
}

// --- CLASES AUXILIARES PARA LOS EJERCICIOS ---

/**
 * Clase para el Ejercicio 4. Contiene un array de enteros y métodos para operarlo.
 */
class ArrayDeEnteros {
    private final int[] numeros = { 4, 2, 3, 8, 1 };

    public int[] getNumeros() {
        return numeros;
    }

    public int cantidadNumeros() {
        return numeros.length;
    }

    public int mayorNumero() {
        int mayor = numeros[0];
        for (int num : numeros) {
            if (num > mayor) {
                mayor = num;
            }
        }
        return mayor;
    }

    public double promedio() {
        int suma = 0;
        for (int num : numeros) {
            suma += num;
        }
        return (double) suma / numeros.length;
    }

    public int[] ordenarAscendente() {
        int[] copia = Arrays.copyOf(numeros, numeros.length);
        Arrays.sort(copia);
        return copia;
    }

    public int[] ordenarDescendente() {
        int[] copia = ordenarAscendente();
        for (int i = 0; i < copia.length / 2; i++) {
            int temp = copia[i];
            copia[i] = copia[copia.length - 1 - i];
            copia[copia.length - 1 - i] = temp;
        }
        return copia;
    }

    public void imprimirArray(int[] array) {
        System.out.println(Arrays.toString(array));
    }
}

/**
 * Clase para el Ejercicio 5. Representa un número entero y sus propiedades.
 */
class Entero {
    private int numero;

    public Entero(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public long cuadrado() {
        return (long) numero * numero;
    }

    public void ParImpar() {
        if (numero % 2 == 0) {
            System.out.println("El numero " + numero + " es par.");
        } else {
            System.out.println("El numero " + numero + " es impar.");
        }
    }

    public long factorial() {
        if (numero < 0) {
            System.out.println("Factorial no definido para números negativos.");
            return -1;
        }
        if (numero == 0) {
            return 1;
        }
        long resultado = 1;
        for (int i = 1; i <= numero; i++) {
            resultado *= i;
        }
        return resultado;
    }

    public boolean esPrimo() {
        if (numero <= 1) return false;
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) return false;
        }
        return true;
    }
}

/**
 * Clase para el Ejercicio 6. Representa a un empleado con nombre y sueldo.
 */
class Empleado {
    private final String nombre;
    private final double sueldo;

    public Empleado(String nombre, double sueldo) {
        this.nombre = nombre;
        this.sueldo = sueldo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getSueldo() {
        return sueldo;
    }
}

/**
 * Clase para el Ejercicio 6. Gestiona el registro y consulta de empleados.
 */
class GestorEmpleados {
    private final Map<String, Empleado> empleados = new HashMap<>();
    private final String RESET = "\u001B[0m";
    private final String AMARILLO = "\u001B[33m";
    private final String VERDE = "\u001B[32m";

    public void cargarEmpleados() {
        try (Scanner entrada = new Scanner(System.in)) {
            System.out.print("¿Cuántos empleados va a ingresar? ");
            int cantidad = entrada.nextInt();
            entrada.nextLine(); // Consumir el salto de línea

            for (int i = 0; i < cantidad; i++) {
                System.out.println("--------------------------------");
                System.out.print("Ingrese el nombre del empleado #" + (i + 1) + ": ");
                String nombre = entrada.nextLine();
                System.out.print("Ingrese el sueldo del empleado #" + (i + 1) + ": ");
                double sueldo = entrada.nextDouble();
                entrada.nextLine(); // Consumir el salto de línea

                empleados.put(nombre, new Empleado(nombre, sueldo));
            }
        }
    }

    public void mostrarResultados() {
        if (empleados.isEmpty()) {
            System.out.println("No se ingresaron empleados.");
            return;
        }

        double suma = 0;
        double maximo = -1;
        String nombreMax = "";

        for (Empleado empleado : empleados.values()) {
            double sueldo = empleado.getSueldo();
            suma += sueldo;

            if (sueldo > maximo) {
                maximo = sueldo;
                nombreMax = empleado.getNombre();
            }
        }

        double promedio = suma / empleados.size();

        System.out.println("--------------------------------");
        System.out.println(VERDE + "El empleado que más gana es:" + RESET);
        System.out.println("-   Nombre: " + nombreMax);
        System.out.println("-   Sueldo: " + maximo);
        System.out.println("--------------------------------");
        System.out.println(AMARILLO + "El promedio del sueldo es: $" + String.format("%.2f", promedio) + RESET);
    }
}
