package ar.edu.Facultad.tp8.ejercicio1;
//|---------------------------------------------------------------|
//|                       Ejercicio Uno                           |
//|---------------------------------------------------------------|
class ThreadSimpleTest {

    public static void main(String[] args) {
        // Creas el primer hilo con el nombre "Argentina"
        ThreadSimple t1 = new ThreadSimple("Argentina");
        // Lo inicias. El programa no espera a que termine.
        t1.start();

        // Creas el segundo hilo con el nombre "Uruguay"
        ThreadSimple t2 = new ThreadSimple("Uruguay");
        // Lo inicias. Ahora t1 y t2 se ejecutan al mismo tiempo.
        t2.start();
    }
}
