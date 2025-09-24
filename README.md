# Trabajos de POO - Facultad

Repositorio para organizar todos los trabajos prácticos de Programación Orientada a Objetos.

## Estructura del proyecto

```
src/
└── ar/
    └── edu/
        └── universidad/
            ├── tp8/
            │   ├── ejercicio1/
            │   └── ejercicio2/
            ├── tp9/
            │   ├── ejercicio1/
            │   └── ejercicio2/
            └── ...
```

## Compilación y ejecución

Para compilar y ejecutar desde la raíz del proyecto:

```bash
# Compilar
javac -d . src/ar/edu/universidad/tp8/ejercicio1/*.java

# Ejecutar
java ar.edu.universidad.tp8.ejercicio1.Main
```

## Organización

- **src/**: Código fuente organizado por paquetes
- **docs/**: Enunciados y documentación
- **recursos/**: Archivos de datos, imágenes, etc.

## Convención de nombres

- Packages: `ar.edu.universidad.tpX.ejercicioY`
- Clases: PascalCase
- Métodos y variables: camelCase
