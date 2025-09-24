package ar.edu.Facultad.tp9.ejercicio2;
// Define una clase genérica llamada 'Par' con dos parámetros de tipo: T y U.
// T y U son marcadores de posición para tipos de datos que se especificarán al crear una instancia de la clase.
// Esto permite que la clase 'Par' trabaje con cualquier par de tipos de objetos (String, Integer; String, Double; etc.).
public class Par<T, U> {

    private T primero;
    private U segundo;

    // Constructor de la clase.
    // Recibe dos argumentos, uno de tipo T y otro de tipo U, para inicializar los elementos del par.
    public Par(T primero, U segundo) {
        this.primero = primero;
        this.segundo = segundo;
    }

    // Devuelve el primer elemento del par. El tipo de retorno es T.
    public T getPrimero() {
        return primero;
    }

    // Establece o actualiza el primer elemento del par.
    public void setPrimero(T primero) {
        this.primero = primero;
    }

    // Devuelve el segundo elemento del par. El tipo de retorno es U.
    public U getSegundo() {
        return segundo;
    }

    // Establece o actualiza el segundo elemento del par.
    public void setSegundo(U segundo) {
        this.segundo = segundo;
    }

    // Sobrescribe el método toString() para proporcionar una representación en String del objeto Par.
    @Override
    public String toString() {
        return "Par(" + primero + ", " + segundo + ")";
    }

    // Método principal para demostrar el uso de la clase genérica Par.
    public static void main(String[] args) {
// ... (código del ejercicio 1)
        System.out.println("\n--- Ejercicio Clase Genérica ---\n");

        // Se crea una instancia de Par.
        // Se especifica que T será String y U será Integer.
        // 'producto' es un par que contiene un String y un Integer.
        Par<String, Integer> producto = new Par<>("Laptop Gamer", 101);
        System.out.println("Producto: " + producto);

        // Se crea otra instancia de Par.
        // Esta vez, se especifica que T será String y U será Double.
        // 'estudiante' es un par que contiene un String y un Double.
        Par<String, Double> estudiante = new Par<>("Ana Pérez", 9.5);
        System.out.println("Estudiante: " + estudiante); // Imprime: Par(Ana Pérez, 9.5)

        // Demostración de la SEGURIDAD DE TIPOS que ofrecen los genéricos.
        // El compilador sabe que producto.getSegundo() devuelve un Integer.
        // Por lo tanto, el resultado se puede asignar directamente a una variable Integer sin necesidad de un cast (conversión de tipo).
        Integer idProducto = producto.getSegundo();
        System.out.println("ID recuperado (ya es Integer, no necesita cast): " + idProducto);
    }
}

/*  
! ¡Esto dará un ERROR DE COMPILACIÓN!
El compilador sabe que el segundo elemento de 'producto' debe ser de tipo Integer.
Si intentas asignarle un String, el código no compilará. Esto previene errores en tiempo de ejecución.
descomente la siguiente línea para ver el error.
producto.setSegundo("ciento-uno");
 */
