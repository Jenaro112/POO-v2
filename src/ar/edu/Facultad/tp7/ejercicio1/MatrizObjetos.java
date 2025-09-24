package ar.edu.Facultad.tp7.ejercicio1;

import java.util.ArrayList;

public class MatrizObjetos {

    static int max_rows = 200;
    static int max_cols = 200;
    private ArrayList<Object>[] cuerpo;
    private int filas;
    private int columnas;

    @SuppressWarnings("unchecked")
    MatrizObjetos(int columnas, int filas) throws MatrizException {
        if (filas <= 0 || columnas <= 0) {
            throw new MatrizException(MatrizException.DIMENSIONES_INVALIDAS);
        }
        if (columnas > max_cols) {
            throw new MatrizException(MatrizException.EXCEEDE_COLUMNAS);
        }
        if (filas > max_rows) {
            throw new MatrizException(MatrizException.EXCEEDE_FILAS);
        }

        this.filas = filas;
        this.columnas = columnas;
        // Es necesario un cast porque no se pueden crear arrays de tipos genéricos directamente.
        cuerpo = (ArrayList<Object>[]) new ArrayList[filas];
        for (int i = 0; i < filas; i++) {
            cuerpo[i] = new ArrayList<Object>(columnas);
            // Inicializamos la fila con valores nulos para poder usar set() directamente.
            for (int j = 0; j < columnas; j++) {
                cuerpo[i].add(null);
            }
        }
    }

    public void SetRowCol(int row, int col, Object obj) throws MatrizException {
        if (row < 0 || row >= this.filas) {
            throw new MatrizException(MatrizException.INDICE_FILA_FUERA_DE_RANGO);
        }
        if (col < 0 || col >= this.columnas) {
            throw new MatrizException(MatrizException.INDICE_COLUMNA_FUERA_DE_RANGO);
        }
        cuerpo[row].set(col, obj);
    }

    public Object GetRowCol(int row, int col) throws MatrizException {
        if (row < 0 || row >= this.filas) {
            throw new MatrizException(MatrizException.INDICE_FILA_FUERA_DE_RANGO);
        }
        if (col < 0 || col >= this.columnas) {
            throw new MatrizException(MatrizException.INDICE_COLUMNA_FUERA_DE_RANGO);
        }
        return cuerpo[row].get(col);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.filas; i++) {
            sb.append("[ ");
            for (int j = 0; j < this.columnas; j++) {
                Object obj = cuerpo[i].get(j);
                sb.append(obj == null ? "null" : obj.toString());
                if (j < this.columnas - 1) {
                    sb.append(", ");
                }
            }
            sb.append(" ]\n");
        }
        return sb.toString();
    }
}
