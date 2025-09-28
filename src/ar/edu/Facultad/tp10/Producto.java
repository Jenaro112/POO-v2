package ar.edu.Facultad.tp10;

import ar.edu.Facultad.utils.MiLibreria;

public class Producto {

    private String nombre;
    private String categoria;
    private double precio;
    private int stock;

    public Producto(String nombre, String categoria, double precio, int stock) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        // Un toString simple es más útil para logging y depuración.
        // Para una vista bonita en tabla, usa MiLibreria.imprimirObjetoEnTabla(this)
        // o MiLibreria.imprimirListaEnTabla(listaDeProductos).
        return "Producto{" + MiLibreria.AMARILLO + "nombre='" + MiLibreria.RESET + nombre + '\''
                + MiLibreria.AMARILLO + ",categoria='" + MiLibreria.RESET + categoria + '\''
                + MiLibreria.AMARILLO + ", precio=" + MiLibreria.RESET + precio
                + MiLibreria.AMARILLO + ", stock=" + MiLibreria.RESET + stock + '}';
    }
}
