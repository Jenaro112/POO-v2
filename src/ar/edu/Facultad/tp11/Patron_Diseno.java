package ar.edu.Facultad.tp11;

import ar.edu.Facultad.utils.MiLibreria;
/**
 * Interfaz que define el contrato para un objeto.
 * En nuestro ejemplo, es un sistema de logging.
 */
interface ILogger {
    void log(String message);
}

/**
 * Implementación real que realiza una acción.
 * Escribe el mensaje en la consola.
 */
class FileLogger implements ILogger {
    @Override
    public void log(String message) {
        System.out.println(MiLibreria.VERDE + "[-]  " + MiLibreria.ROJO + "[LOG REAL] (sin Null Object): " + MiLibreria.RESET + message);
    }
}

//* Implementar un Null Object para ILogger
/**
 * Implementación del Patrón Null Object.
 * Implementa la misma interfaz, pero su método no hace nada.
 * Proporciona un comportamiento por defecto y seguro.
 */
class NullLogger implements ILogger {
    @Override
    public void log(String message) {
        // No hace nada. Es un objeto "nulo" con comportamiento vacío.
    }
}

/**
 * Clase cliente que utiliza un ILogger.
 * Gracias al patrón, esta clase no necesita comprobar si el logger es null.
 */
class Servicio {
    private final ILogger logger;

    // El constructor recibe una dependencia ILogger. Nunca será null.
    public Servicio(ILogger logger) {
        // Se asegura de que el logger nunca sea null internamente.
        // Si se pasara null, se podría asignar un NullLogger por defecto,
        // aunque lo ideal es que el creador del Servicio decida.
        this.logger = logger;
    }

    public void hacerAlgoImportante() {
        System.out.println(MiLibreria.VERDE + "\n[-]  Iniciando una operación importante..." + MiLibreria.RESET);

        // Comprobación de null obligatoria antes de usar el logger
        if (logger != null) {
            logger.log(MiLibreria.VERDE + "La operación ha comenzado." + MiLibreria.RESET);
        }

        // ... Lógica de negocio ...
        System.out.println(MiLibreria.VERDE + "[-]  Procesando datos..." + MiLibreria.RESET);

        // De nuevo, se necesita otra comprobación. Este código es repetitivo y propenso a errores.
        if (logger != null) {
            logger.log(MiLibreria.VERDE + "La operación ha finalizado con éxito." + MiLibreria.RESET);
        }

        System.out.println(MiLibreria.VERDE + "[-]  Operación completada." + MiLibreria.RESET);
    }
}


public class Patron_Diseno {
    public static void main(String[] args) {

        MiLibreria.printHeader("Patrón Null Object");
        System.out.println(MiLibreria.CYAN + "\n--- Escenario 1: Usando el Logger Real ---" + MiLibreria.RESET);
        // Creamos el servicio con un logger que sí registra.
        ILogger loggerReal = new FileLogger();
        Servicio servicioConLogging = new Servicio(loggerReal);
        servicioConLogging.hacerAlgoImportante();

        System.out.println("\n=============================================\n");

        System.out.println(MiLibreria.CYAN + "--- Escenario 2: Usando el Null Object Logger ---" + MiLibreria.RESET);
        // Creamos el servicio con un logger que no hace nada.
        // En lugar de pasar `null`, pasamos una instancia segura.
        ILogger loggerNulo = new NullLogger();
        Servicio servicioSinLogging = new Servicio(loggerNulo);
        servicioSinLogging.hacerAlgoImportante();

        System.out.println("\n=============================================\n");

        System.out.println(MiLibreria.CYAN + "--- Escenario 3: Sin patrón (usando null directamente) ---" + MiLibreria.RESET);
        // Creamos el servicio pasando null. El servicio debe estar preparado para manejarlo.
        Servicio servicioConNull = new Servicio(null);
        servicioConNull.hacerAlgoImportante();
        System.out.println(MiLibreria.PURPURA + "(Observa que no se imprime ningún log, pero el programa no falla gracias a los 'if != null')" + MiLibreria.RESET);
    }
}
