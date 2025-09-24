package ar.edu.Facultad.tp10;
import java.util.*;

public class ProductoService {
	
	private final List<Producto> productos;

    public ProductoService(List<Producto> productos) {
		super();
		this.productos = productos;
	}

	// Tarea 1: Filtrar productos por categoría
    public List<Producto> obtenerProductosPorCategoria(String categoria) {
        return null;
    }

    // Tarea 2: Encontrar el producto más caro
    public Optional<Producto> encontrarProductoMasCaro() {
        return null;
    }

    // Tarea 3: Obtener nombres de productos con poco stock
    public List<String> obtenerNombresProductosConPocoStock(int stockMaximo) {
        return null;
    }

    // Tarea 4: Ordenar productos por categoría y luego por precio descendente
    public List<Producto> ordenarProductosPorCategoriaYPrecio() {
        return null;
    }

    // Tarea 5: Agrupar productos por categoría
    public Map<String, List<Producto>> agruparProductosPorCategoria() {
        return null;
    }

    // Tarea 6: Calcular el valor total del inventario
    public double calcularValorTotalInventario() {
        return (Double) null;
    }

    // Tarea 7: Ordenar usando un Comparator explícito y reutilizable
    public List<Producto> obtenerProductosOrdenadosPorStockYNombre() {
        return null;
    }
    
    // Tarea 8: Búsqueda que devuelve un Optional para manejo avanzado
    public Optional<Producto> buscarProductoPorNombre(String nombre) {
        return null;
    }
}