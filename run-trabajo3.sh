#!/bin/bash

# Script para compilar y ejecutar Trabajo3.java
cd "/Users/jena/Desktop/POO v2"

echo "Compilando Trabajo3.java..."
javac -d bin -cp src "src/ar/edu/Facultad/tp3/ejercicios/Trabajo3.java"

if [ $? -eq 0 ]; then
    echo "Compilación exitosa. Ejecutando..."
    echo "================================"
    java -cp bin ar.edu.Facultad.tp3.ejercicios.Trabajo3
else
    echo "Error en la compilación"
fi
