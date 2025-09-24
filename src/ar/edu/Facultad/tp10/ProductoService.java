package ar.edu.Facultad.tp10;
import java.util.*;
import java.util.stream.Collectors;

public class ProductoService {

        private final List<Producto> productos;

        public ProductoService(List<Producto> productos) {
        this.productos = productos;
        }

        //* Tarea 1: Filtrar productos por categoría

        public List<Producto> obtenerProductosPorCategoria(String categoria) {
        return productos.stream()
                .filter(producto -> producto.getCategoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());
        }

        //* Tarea 2: Encontrar el producto más caro

        public Optional<Producto> encontrarProductoMasCaro() {
        return productos.stream()
                .max(Comparator.comparing(Producto::getPrecio));
        }

        //* Tarea 3: Obtener nombres de productos con poco stock

        public List<String> obtenerNombresProductosConPocoStock(int stockMaximo) {
        return productos.stream()
                .filter(producto -> producto.getStock() < stockMaximo) // Filtra productos con stock bajo
                .map(Producto::getNombre) // Transforma el Stream<Producto> a Stream<String>
                .collect(Collectors.toList()); // Recolecta los nombres en una lista
        }

        //* Tarea 4: Ordenar productos por categoría y luego por precio descendente

        public List<Producto> ordenarProductosPorCategoriaYPrecio() {
        // Creamos un comparador encadenado.
        // 1. Compara por categoría (alfabético, A-Z)
        // 2. Si las categorías son iguales, compara por precio (descendente)
        Comparator<Producto> comparadorCompuesto = Comparator.comparing(Producto::getCategoria) // Compara por categoría
                .thenComparing(Producto::getPrecio, Comparator.reverseOrder()); // Compara por precio (descendente)

        return productos.stream()
                .sorted(comparadorCompuesto)                            // Ordena usando el comparador compuesto
                .collect(Collectors.toList());                          // Recolecta en una lista
        }

        //* Tarea 5: Agrupar productos por categoría

        public Map<String, List<Producto>> agruparProductosPorCategoria() {
        return productos.stream()
                .collect(Collectors.groupingBy(Producto::getCategoria));// Agrupa productos por categoría
        }

        //* Tarea 6: Calcular el valor total del inventario

        public double calcularValorTotalInventario() {
        return productos.stream()
                .mapToDouble(p -> p.getPrecio() * p.getStock())         // Calcula el valor total por producto
                .sum();                                                 // Suma todos los valores para obtener el total del inventario 
        }

        //* Tarea 7: Ordenar usando un Comparator explícito y reutilizable

        public List<Producto> obtenerProductosOrdenadosPorStockYNombre() {
        // Este Comparator se podría definir como una constante en la clase si se usa mucho.
        Comparator<Producto> porStockLuegoNombre = Comparator.comparingInt(Producto::getStock)
                .thenComparing(Producto::getNombre);

        return productos.stream()
                .sorted(porStockLuegoNombre)                            // Ordena por stock ascendente y luego por nombre alfabéticamente
                .collect(Collectors.toList());                          // Recolecta en una lista
        }

        //* Tarea 8: Búsqueda que devuelve un Optional para manejo avanzado

        public Optional<Producto> buscarProductoPorNombre(String nombre) {
        return productos.stream()
                .filter(p -> p.getNombre().equalsIgnoreCase(nombre))    // Búsqueda insensible a mayúsculas/minúsculas
                .findFirst();                                           // Devuelve un Optional<Producto>
        }
}