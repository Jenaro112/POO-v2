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

### En macOS/Linux:
```bash
# Compilar
javac -d bin -cp src src/ar/edu/Facultad/tp3/ejercicios/Trabajo3.java

# Ejecutar
java -cp bin ar.edu.Facultad.tp3.ejercicios.Trabajo3

# O usar el script
./run-java-file.sh "src/ar/edu/Facultad/tp3/ejercicios/Trabajo3.java"
```

### En Windows:
```cmd
# Compilar
javac -d bin -cp src "src\ar\edu\Facultad\tp3\ejercicios\Trabajo3.java"

# Ejecutar
java -cp bin ar.edu.Facultad.tp3.ejercicios.Trabajo3

# O usar el script de Windows
run-java-file.bat "src\ar\edu\Facultad\tp3\ejercicios\Trabajo3.java"
```

## Organización

- **src/**: Código fuente organizado por paquetes
- **docs/**: Enunciados y documentación
- **recursos/**: Archivos de datos, imágenes, etc.

## Convención de nombres

- Packages: `ar.edu.universidad.tpX.ejercicioY`
- Clases: PascalCase
- Métodos y variables: camelCase
