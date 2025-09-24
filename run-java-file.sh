#!/bin/bash

# Script helper para Code Runner
# Recibe como parámetro el archivo Java a ejecutar

if [ $# -ne 1 ]; then
    echo "Uso: $0 <archivo.java>"
    exit 1
fi

JAVA_FILE="$1"
WORKSPACE_ROOT="/Users/jena/Desktop/POO v2"

# Cambiar al directorio del workspace
cd "$WORKSPACE_ROOT"

# Extraer el package del archivo Java
PACKAGE_LINE=$(grep -E "^package " "$JAVA_FILE" | head -1)
if [ -n "$PACKAGE_LINE" ]; then
    # Extraer el nombre del package
    PACKAGE_NAME=$(echo "$PACKAGE_LINE" | sed 's/package //' | sed 's/;//')
else
    PACKAGE_NAME=""
fi

# Obtener el nombre de la clase (nombre del archivo sin .java)
CLASS_NAME=$(basename "$JAVA_FILE" .java)

# Construir el nombre completo de la clase
if [ -n "$PACKAGE_NAME" ]; then
    FULL_CLASS_NAME="$PACKAGE_NAME.$CLASS_NAME"
else
    FULL_CLASS_NAME="$CLASS_NAME"
fi

echo "Compilando $JAVA_FILE..."
echo "Package: $PACKAGE_NAME"
echo "Class: $CLASS_NAME"
echo "Full class name: $FULL_CLASS_NAME"
echo "================================"

# Compilar
javac -d bin -cp src "$JAVA_FILE"

if [ $? -eq 0 ]; then
    echo "Compilación exitosa. Ejecutando..."
    echo "================================"
    # Ejecutar
    java -cp bin "$FULL_CLASS_NAME"
else
    echo "Error en la compilación"
    exit 1
fi
