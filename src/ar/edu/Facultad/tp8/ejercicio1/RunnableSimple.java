package ar.edu.Facultad.tp8.ejercicio1;
//|---------------------------------------------------------------|
//|                       Ejercicio Uno                           |
//|---------------------------------------------------------------|
import java.util.concurrent.ThreadLocalRandom;

public class RunnableSimple implements Runnable {

    @Override
    public void run() {
        // Obtenemos el nombre del hilo que está ejecutando esta tarea
        String threadName = Thread.currentThread().getName();
        for (int i = 0; i < 10; i++) {                                  // Reducido a 10 para pruebas más rápidas
            System.out.println(i + " " + threadName);
            try {
                // Usamos el método estático de la clase Thread

                Thread.sleep(ThreadLocalRandom.current().nextInt(100, 1001));      // Dormir entre 100 y 1000 ms
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();                     // Se restaura el estado de interrupción
                System.err.println("Hilo interrumpido: " + e.getMessage());
            }
        }
        System.out.println("HECHO! " + threadName);
    }

}
