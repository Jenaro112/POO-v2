package ar.edu.Facultad.tp7.ejercicio1;

class App {

    @SuppressWarnings({"deprecation", "UnnecessaryBoxing", "BoxedPrimitiveConstruction"}) // Se usa para suprimir advertencias de new Integer()
    public static void main(String[] args) {
        System.out.println("------------------------------------------");
        System.out.println("--- Iniciando pruebas de MatrizObjetos ---");
        System.out.println("------------------------------------------");

        // Prueba 1: Intentar crear una matriz con dimensiones inválidas (negativas)
        System.out.println("\n--- Prueba 1: Creando matriz con dimensiones inválidas (-2, 5) ---");
        try {
            @SuppressWarnings("unused")
            MatrizObjetos matriz = new MatrizObjetos(3, 3);
        } catch (MatrizException e) {
            System.out.println("Excepción capturada correctamente: " + e);
        }

        // Prueba 2: Intentar usar setRowCol con un índice de fila incorrecto
        System.out.println("\n--- Prueba 2: Usando setRowCol con índice de fila inválido (fila 10 en matriz 3x3) ---");
        try {
            MatrizObjetos matrizValida = new MatrizObjetos(3, 3);
            System.out.println("Matriz 3x3 creada exitosamente.");
            matrizValida.SetRowCol(2, 2, "Dato de prueba");
        } catch (MatrizException e) {
            System.out.println("Excepción capturada correctamente: " + e);
        }

        // Prueba 3: Intentar usar getRowCol con un índice de columna incorrecto
        System.out.println("\n--- Prueba 3: Usando getRowCol con índice de columna inválido (col 5 en matriz 2x2) ---");
        try {
            MatrizObjetos matrizValida2 = new MatrizObjetos(2, 2);
            System.out.println("Matriz 2x2 creada exitosamente.");
            matrizValida2.GetRowCol(2, 2);
        } catch (MatrizException e) {
            System.out.println("Excepción capturada correctamente: " + e);
        }

        // Prueba 4: Demostración de un caso de uso exitoso
        System.out.println("\n--- Prueba 4: Demostración de operación exitosa ---");
        try {
            MatrizObjetos matrizOk = new MatrizObjetos(2, 2);
            matrizOk.SetRowCol(1, 1, Integer.valueOf(123));
            Object valor = matrizOk.GetRowCol(1, 1);
            System.out.println("Operación exitosa. Valor obtenido en [1,1]: " + valor);
        } catch (MatrizException e) {
            System.out.println("Se produjo una excepción inesperada: " + e);
        }
    }
}
