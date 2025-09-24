

/*
* 1.    Defina una clase Figura que tenga los siguientes atributos:
* - Color
* - Coordenada del centro de la forma (Defina una clase Punto con atributos coordenadaX y coordenadaY)
* - Nombre de la forma

* Y, al menos, los siguientes métodos:
* - toString. Sobrescribir el método heredado de Object
* - Obtener y cambiar el color
* - Mover la forma (o sea, su centro)

* Defina una subclase Rectángulo que herede de Figura y tenga los siguientes atributos:
* - Lado menor.
* - Lado mayor.

* Y, al menos, los siguientes métodos:
* - toString. Debe retornar un string mostrando que se trata de un rectángulo, su nombre, color, centro y lado. Debería usarse la función toString de la clase base   para realizar parte de este trabajo.
* - Calcular el área (lado menor * lado mayor).
* - Calcular el perímetro. (2 * lado menor + 2 * lado mayor).
* - Cambiar el tamaño del rectángulo. Recibe como parámetro un factor de escala. Así, por
* ejemplo, si el factor vale 2, el rectángulo duplicará su tamaño y si es 0,5 se reducirá a la mitad. Qué pasaría o cómo deberían manejar un factor de escala negativo o cero?
*
* a)  Realice un programa que pruebe el funcionamiento de estas clases. Debe crear objetos y comprobar el correcto funcionamiento de las funciones miembro.
* b) Defina una subclase Elipse que herede de Figura. Recordatorio: una elipse queda definida por su radio mayor (R) y su radio menor (r), tal que el área de una elipse es igual a π*(R*r). Agregue los métodos toString(), y para calcular el área, perímetro (use una formula simplificada) y cambiar el tamaño, al igual que en Rectángulo.
* c) Defina una clase Cuadrado que herede de la clase Rectángulo. ¿Cómo debería ser el constructor de Cuadrado? Si Rectángulo tuviera métodos como setLadoMenor() y setLadoMayor(), ¿Cómo debería Cuadrado sobrescribirlos para mantener la integridad de un cuadrado (donde ambos lados deben ser iguales)?
* d) Defina una clase Circulo que herede de la clase Elipse. ¿Cómo sería su constructor y cómo se relaciona con el de Elipse? (Radio mayor = Radio menor).
* e) Realice un programa que defina varias figuras diferentes, cree una collection de objetos de la clase Figura. El programa debe realizar un bucle que recorra todas las figuras, las ponga todas del mismo color y las mueva a una determinada posición.
* f) Analice qué ocurre en el ejercicio anterior si se intenta imprimir la información de cada figura (llamando al método toString) y qué sucede si se intenta obtener en ese bucle el área y perímetro de todas las figuras de la collection.
* g) Utilice la técnica de polimorfismo para arreglar los comportamientos anómalos detectados en el paso anterior.
* h) ¿Cómo haría para obligar que todas las clases futuras que hereden de Figura tengan al menos los métodos “área” y “perímetro”. ¿Tiene sentido incluir definir dichos métodos en la clase Figura?
* i) Haga un diagrama de clases que refleja la estructura definida hasta el momento. Añádale las clases: Punto, Línea, Triángulo, Triángulo Rectángulo y
 */

package ar.edu.Facultad.tp5.ejercicio1;

import java.util.*;

// Clase Punto
class Punto {

    private double coordenadaX;
    private double coordenadaY;

    public Punto(double x, double y) {
        this.coordenadaX = x;
        this.coordenadaY = y;
    }

    public double getCoordenadaX() {
        return coordenadaX;
    }

    public double getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaX(double x) {
        this.coordenadaX = x;
    }

    public void setCoordenadaY(double y) {
        this.coordenadaY = y;
    }

    public void mover(int dx, int dy) {
        this.coordenadaX += dx;
        this.coordenadaY += dy;
    }

    @Override
    public String toString() {
        return "(" + coordenadaX + ", " + coordenadaY + ")";
    }
}

// Clase abstracta Figura
abstract class Figura {

    private String color;
    protected Punto centro;
    protected String nombre;

    public Figura(String color, Punto centro, String nombre) {
        this.color = color;
        this.centro = centro;
        this.nombre = nombre;
    }

    //GETTER
    public String getColor() {
        return color;
    }

    //SETTER
    public void setColor(String color) {
        this.color = color;
    }

    public void mover(int dx, int dy) {
        this.centro.mover(dx, dy);
    }

    @Override
    public String toString() {
        return "Figura: " + nombre + ", Color: " + color + ", Centro: " + centro;
    }

    public abstract double area();

    public abstract double perimetro();

    public abstract void cambiarTamaño(double factor);
}

// Rectángulo
class Rectangulo extends Figura {

    protected double ladoMenor;
    protected double ladoMayor;

    public Rectangulo(String color, Punto centro, String nombre, double ladoMenor, double ladoMayor) {
        super(color, centro, nombre);
        this.ladoMenor = ladoMenor;
        this.ladoMayor = ladoMayor;
    }

    public double getLadoMenor() {
        return ladoMenor;
    }

    public double getLadoMayor() {
        return ladoMayor;
    }

    public void setLadoMenor(double ladoMenor) {
        this.ladoMenor = ladoMenor;
    }

    public void setLadoMayor(double ladoMayor) {
        this.ladoMayor = ladoMayor;
    }

    @Override
    public double area() {
        return ladoMenor * ladoMayor;
    }

    @Override
    public double perimetro() {
        return 2 * (ladoMenor + ladoMayor);
    }

    @Override
    public void cambiarTamaño(double factor) {
        if (factor <= 0) {
            System.out.println("El factor de escala debe ser mayor que cero.");
            return;
        }
        this.ladoMenor *= factor;
        this.ladoMayor *= factor;
    }

    @Override
    public String toString() {
        return "Rectángulo: " + super.toString() + ", Lado Menor: " + ladoMenor + ", Lado Mayor: " + ladoMayor;
    }
}

// Elipse
class Elipse extends Figura {

    protected double radioMayor;
    protected double radioMenor;

    public Elipse(String color, Punto centro, String nombre, double radioMayor, double radioMenor) {
        super(color, centro, nombre);
        this.radioMayor = radioMayor;
        this.radioMenor = radioMenor;
    }

    public double getRadioMayor() {
        return radioMayor;
    }

    public double getRadioMenor() {
        return radioMenor;
    }

    public void setRadioMayor(double radioMayor) {
        this.radioMayor = radioMayor;
    }

    public void setRadioMenor(double radioMenor) {
        this.radioMenor = radioMenor;
    }

    @Override
    public double area() {
        return Math.PI * radioMayor * radioMenor;
    }

    @Override
    public double perimetro() {
        double a = radioMayor;
        double b = radioMenor;
        return Math.PI * (3 * (a + b) - Math.sqrt((3 * a + b) * (a + 3 * b)));
    }

    @Override
    public void cambiarTamaño(double factor) {
        if (factor <= 0) {
            System.out.println("El factor de escala debe ser mayor que cero.");
            return;
        }
        this.radioMayor *= factor;
        this.radioMenor *= factor;
    }

    @Override
    public String toString() {
        return "Elipse: " + super.toString() + ", Radio Mayor: " + radioMayor + ", Radio Menor: " + radioMenor;
    }
}

// Cuadrado
class Cuadrado extends Rectangulo {

    public Cuadrado(String color, Punto centro, String nombre, double lado) {
        super(color, centro, nombre, lado, lado);
    }

    @Override
    public void setLadoMenor(double ladoMenor) {
        this.ladoMenor = ladoMenor;
        this.ladoMayor = ladoMenor;
    }

    @Override
    public void setLadoMayor(double ladoMayor) {
        this.ladoMenor = ladoMayor;
        this.ladoMayor = ladoMayor;
    }

    @Override
    public void cambiarTamaño(double factor) {
        if (factor <= 0) {
            System.out.println("El factor de escala debe ser mayor que cero.");
            return;
        }
        this.ladoMenor *= factor;
        this.ladoMayor = this.ladoMenor;
    }

    @Override
    public String toString() {
        return "Cuadrado: " + super.toString();
    }
}

// Círculo
class Circulo extends Elipse {

    public Circulo(String color, Punto centro, String nombre, double radio) {
        super(color, centro, nombre, radio, radio);
    }

    @Override
    public void setRadioMayor(double radio) {
        this.radioMayor = radio;
        this.radioMenor = radio;
    }

    @Override
    public void setRadioMenor(double radio) {
        this.radioMayor = radio;
        this.radioMenor = radio;
    }

    @Override
    public void cambiarTamaño(double factor) {
        if (factor <= 0) {
            System.out.println("El factor de escala debe ser mayor que cero.");
            return;
        }
        this.radioMayor *= factor;
        this.radioMenor = this.radioMayor;
    }

    @Override
    public String toString() {
        return "Círculo: " + super.toString();
    }
}

public class main {

    public static void main(String[] args) {

        //colores :)
        final String RESET = "\u001B[0m";
        final String RED = "\u001B[31m";
        final String GREEN = "\u001B[32m";
        final String YELLOW = "\u001B[33m";
        final String PURPLE = "\u001B[35m";
        final String CYAN = "\u001B[36m";

        List<Figura> figuras = new ArrayList<>();

        figuras.add(new Rectangulo("Azul", new Punto(5, 10), "Rectángulo ", 4, 6));
        figuras.add(new Elipse("Rojo", new Punto(2, 3), "Elipse ", 5, 2));
        figuras.add(new Cuadrado("Verde", new Punto(0, 0), "Cuadrado ", 3));
        figuras.add(new Circulo("Amarillo", new Punto(-2, 7), "Círculo ", 2.5));

        System.out.println("");
        System.out.println(GREEN + "--- Figuras originales ---" + RESET);
        for (Figura f : figuras) {
            System.out.println(RED + "Tipo de figura: " + RESET + f.getClass().getSimpleName());
            System.out.println(YELLOW + "-  Nombre: " + RESET + f.nombre);
            System.out.println(YELLOW + "-  Color: " + RESET + f.getColor());
            System.out.println(YELLOW + "-  Centro: " + RESET + f.centro);

            if (f instanceof Rectangulo r) {
                System.out.println(YELLOW + "-  Lado menor: " + RESET + r.getLadoMenor());
                System.out.println(YELLOW + "-  Lado mayor: " + RESET + r.getLadoMayor());
            }

            if (f instanceof Elipse e) {
                System.out.println(YELLOW + "-  Radio menor: " + RESET + e.getRadioMenor());
                System.out.println(YELLOW + "-  Radio mayor: " + RESET + e.getRadioMayor());
            }

            System.out.println(YELLOW + "-  Área: " + RESET + f.area());
            System.out.println(YELLOW + "-  Perímetro: " + RESET + f.perimetro());
            System.out.println(GREEN + "------------------------------------" + RESET);
        }

        // Punto e
        String nuevoColor = "Magenta";
        int dx = 10, dy = -5;
        for (Figura f : figuras) {
            f.setColor(nuevoColor);
            f.mover(dx, dy);
        }
        System.out.println("");
        System.out.println(GREEN + "--- Figuras después de cambiar color y mover ---" + RESET);
        for (Figura f : figuras) {
            System.out.println(RED + "Tipo de figura: " + RESET + f.getClass().getSimpleName());
            System.out.println(PURPLE + "-   Nombre: " + RESET + f.nombre);
            System.out.println(PURPLE + "-   Nuevo color: " + RESET + f.getColor());
            System.out.println(PURPLE + "-   Nuevo centro: " + RESET + f.centro);
            System.out.println(GREEN + "------------------------------------" + RESET);
        }

        // Cambiar tamaño
        System.out.println("");
        System.out.println(GREEN + "--- Cambiando tamaño de todas las figuras (factor 2) ---" + RESET);
        for (Figura f : figuras) {
            f.cambiarTamaño(2);
            System.out.println(RED + "Tipo de figura: " + RESET + f.getClass().getSimpleName());
            System.out.println(CYAN + "-   Nombre: " + RESET + f.nombre);

            if (f instanceof Rectangulo r) {
                System.out.println(CYAN + "-   Nuevo lado menor: " + RESET + r.getLadoMenor());
                System.out.println(CYAN + "-   Nuevo lado mayor: " + RESET + r.getLadoMayor());
            }

            if (f instanceof Elipse e) {
                System.out.println(CYAN + "-   Nuevo radio menor: " + RESET + e.getRadioMenor());
                System.out.println(CYAN + "-   Nuevo radio mayor: " + RESET + e.getRadioMayor());
            }

            System.out.println(CYAN + "-   Área: " + RESET + f.area());
            System.out.println(CYAN + "-   Perímetro: " + RESET + f.perimetro());
            System.out.println(GREEN + "------------------------------------" + RESET);

            //* Punto h
            //* Para obligar a que todas las clases futuras que hereden de Figura tengan los métodos "área" y "perímetro", se pueden definir estos métodos como abstractos en la clase Figura.
            //* Esto tiene sentido, ya que todas las figuras geométricas tienen un área y un perímetro, y es lógico que estas operaciones sean parte de la interfaz de la clase Figura.
            //* Al definirlos como abstractos, se fuerza a las subclases a implementar estos métodos, garantizando que todas las figuras tendrán una forma de calcular su área y perímetro.
        }
    }
}
