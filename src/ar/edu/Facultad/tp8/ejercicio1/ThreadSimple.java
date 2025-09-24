package ar.edu.Facultad.tp8.ejercicio1;
//|---------------------------------------------------------------|
//|                       Ejercicio Uno                           |
//|---------------------------------------------------------------|
class ThreadSimple extends Thread {

    public ThreadSimple(String str) {
        super(str);
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {                  //reducido a 20 para pruebas más rápidas
            System.out.println(i + " " + getName());
            try {
                sleep((int) (Math.random() * 1000));    // Dormir entre 0 y 999 ms
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Hilo interrumpido: " + e.getMessage());
            }
        }
        System.out.println("HECHO! " + getName());
    }
}
