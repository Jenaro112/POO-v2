@echo off
REM Script helper para Code Runner en Windows
REM Recibe como parámetro el archivo Java a ejecutar

if "%1"=="" (
    echo Uso: %0 ^<archivo.java^>
    exit /b 1
)

set JAVA_FILE=%1
set WORKSPACE_ROOT=%cd%

REM Cambiar al directorio del workspace
cd /d "%WORKSPACE_ROOT%"

REM Extraer el package del archivo Java (versión simplificada para Windows)
echo Compilando %JAVA_FILE%...
echo ================================

REM Compilar
javac -d bin -cp src "%JAVA_FILE%"

if %errorlevel% equ 0 (
    echo Compilación exitosa. Ejecutando...
    echo ================================
    REM Para Windows, necesitarás especificar manualmente la clase principal
    REM o usar un enfoque diferente para extraer el package
    java -cp bin ar.edu.Facultad.tp3.ejercicios.Trabajo3
) else (
    echo Error en la compilación
    exit /b 1
)