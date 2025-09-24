#!/bin/bash

# Script para compilar y ejecutar ejercicios de Java
# Uso: ./compile-and-run.sh tp8 ejercicio1

if [ $# -ne 2 ]; then
    echo "Uso: $0 <tp> <ejercicio>"
    echo "Ejemplo: $0 tp8 ejercicio1"
    exit 1
fi

TP=$1
EJERCICIO=$2
PACKAGE_PATH="ar/edu/universidad/$TP/$EJERCICIO"
SOURCE_PATH="src/$PACKAGE_PATH"

if [ ! -d "$SOURCE_PATH" ]; then
    echo "Error: No existe el directorio $SOURCE_PATH"
    exit 1
fi

echo "Compilando $TP $EJERCICIO..."

# Compilar
javac -d . src/$PACKAGE_PATH/*.java

if [ $? -eq 0 ]; then
    echo "Compilación exitosa. Ejecutando..."
    # Ejecutar
    java ar.edu.universidad.$TP.$EJERCICIO.Main
else
    echo "Error en la compilación"
fi
