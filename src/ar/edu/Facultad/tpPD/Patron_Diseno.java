package ar.edu.Facultad.tpPD;

import ar.edu.Facultad.utils.MiLibreria;

// *=======================================================================================
// *                                Patrón Null Object
// *=======================================================================================

interface Plan {                            // Interfaz para los planes de facturación

    //Getters
    String getLevel();                      // Método para obtener el nivel del plan

    double getDiscountPercentage();         // Método para obtener el porcentaje de descuento
}

class BillingPlan implements Plan {         // Implementación concreta de un plan de facturación

    private final String level;             // Nivel del plan (e.g., "Basic", "Premium")
    private final double discount;          // Porcentaje de descuento

    public BillingPlan(String level, double discount) {
        this.level = level;
        this.discount = discount;
    }

    @Override                               // Devuelve el nivel del plan
    public String getLevel() {
        return level;
    }

    @Override                               // Devuelve el porcentaje de descuento
    public double getDiscountPercentage() {
        return discount;
    }
}

class NullPlan implements Plan {            // Implementación nula de un plan de facturación
    // esto permite evitar chequeos nulos en el código cliente, agilisa 

    @Override
    public String getLevel() {              // Devuelve un valor por defecto para el nivel del plan
        return "N/A";
    }

    @Override
    public double getDiscountPercentage() { // Devuelve un valor por defecto para el porcentaje de descuento
        return 0.0;
    }
}

abstract class Customer {                   // Clase abstracta para clientes

    protected String name;                  // Nombre del cliente

    public abstract boolean isNull();       // Método para verificar si es un cliente nulo

    public abstract Plan getPlan();         // Método para obtener el plan de facturación del cliente

    public String getName() {               // Getter para el nombre del cliente
        return name;
    }
}

class RealCustomer extends Customer {       // Implementación concreta de un cliente real

    private final Plan plan;                // Plan de facturación asociado al cliente

    public RealCustomer(String name) {      // Constructor que inicializa el nombre y el plan
        this.name = name;
        // En un caso real, el plan se cargaría de una base de datos.
        // Aquí lo asignamos directamente para el ejemplo.
        this.plan = new BillingPlan("Premium", 15.0);
    }

    @Override
    public boolean isNull() {
        return false;                       // Indica que este no es un cliente nulo
    }

    @Override
    public Plan getPlan() {                 // Devuelve el plan de facturación del cliente
        return plan;
    }
}

class NullCustomer extends Customer {       // Implementación nula de un cliente

    public NullCustomer() {
        this.name = "Invitado";             // Nombre por defecto para un cliente nulo
    }

    @Override
    public boolean isNull() {               // Indica que este es un cliente nulo
        return true;
    }

    @Override
    public Plan getPlan() {                 // Devuelve un objeto nulo para su dependencia, propagando el patrón.
        return new NullPlan();
    }
}

class CustomerRepository {                  // Clase para el repositorio de clientes

    public static Customer getCustomer(String name) {
        // Simula la búsqueda de un cliente.
        if ("Beto".equals(name)) {
            return new RealCustomer(name);  //devuelve un cliente real si el nombre coincide
        }
        // En lugar de devolver null, devuelve el NullCustomer.
        return new NullCustomer();          // devuelve un cliente nulo si no se encuentra
    }
}

public class Patron_Diseno {

    public static void main(String[] args) {
        MiLibreria.printHeader("Patron Null Object");

        // Demostracion del ejemplo usando el patrón Null Object
        System.out.println(MiLibreria.AMARILLO + "Ejemplo 1: Sistema de Clientes y Planes" + MiLibreria.RESET);
        MiLibreria.printSeparator();

        // Escenario A: Cliente existente
        Customer customer1 = CustomerRepository.getCustomer("Beto");
        processCustomer(customer1);

        // Escenario B: Cliente no existente (o invitado)
        Customer customer2 = CustomerRepository.getCustomer("Enrique");
        processCustomer(customer2);
    }

    // Método para procesar y mostrar la información del cliente y su plan
    public static void processCustomer(Customer customer) {                 // Recibe un objeto Customer (puede ser RealCustomer o NullCustomer)
        System.out.println("\nProcesando cliente: " + customer.getName());  // Muestra el nombre del cliente o "Guest" si es NullCustomer
        Plan plan = customer.getPlan();                                     //llamamos al plan del cliente
        double discount = plan.getDiscountPercentage();                     //llamamos al descuento del plan
        double fullPrice = 200.0;                                           // Precio base del servicio
        double finalPrice = fullPrice * (1 - discount / 100);               // Calcula el precio final aplicando el descuento

        //imprime los detalles del cliente y su plan
        System.out.println(" - ¿Es un cliente nulo?: " + (customer.isNull() ? "Sí" : "No"));                // Muestra si es NullCustomer
        System.out.println(" - Nivel del Plan: " + plan.getLevel());                                        // Muestra "N/A" si es NullPlan
        System.out.println(" - Descuento aplicado: " + discount + "%");                                     // Muestra el descuento    
        System.out.println(" - Precio final del servicio: $" + String.format("%.2f", finalPrice));   // Muestra el precio final
    }
}
