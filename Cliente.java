
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {
            // Dirección IP del servidor
            String serverIP = "148.204.88.61"; // Cambiar a la IP del servidor
            
            // Obtener el registro
            Registry registry = LocateRegistry.getRegistry(serverIP, 1099);
            
            // Buscar el servicio remoto
            CalculadoraRemota calculadora = (CalculadoraRemota) registry.lookup("CalculadoraRemota");
            
            // Crear scanner para entrada de usuario
            Scanner scanner = new Scanner(System.in);
            
            while (true) {
                try {
                    // Menú de operaciones
                    System.out.println("\n=== Calculadora Remota ===");
                    System.out.println("1. Suma");
                    System.out.println("2. Resta");
                    System.out.println("3. Multiplicación");
                    System.out.println("4. División");
                    System.out.println("5. Salir");
                    System.out.print("Seleccione una operación (1-5): ");
                    
                    int opcion = scanner.nextInt();
                    if (opcion == 5) break;
                    
                    if (opcion >= 1 && opcion <= 4) {
                        System.out.print("Ingrese el primer número: ");
                        double num1 = scanner.nextDouble();
                        System.out.print("Ingrese el segundo número: ");
                        double num2 = scanner.nextDouble();
                        
                        double resultado = 0;
                        String operacion = "";
                        
                        switch (opcion) {
                            case 1:
                                resultado = calculadora.sumar(num1, num2);
                                operacion = "suma";
                                break;
                            case 2:
                                resultado = calculadora.restar(num1, num2);
                                operacion = "resta";
                                break;
                            case 3:
                                resultado = calculadora.multiplicar(num1, num2);
                                operacion = "multiplicación";
                                break;
                            case 4:
                                resultado = calculadora.dividir(num1, num2);
                                operacion = "división";
                                break;
                        }
                        
                        System.out.printf("El resultado de la %s es: %.2f%n", operacion, resultado);
                    } else {
                        System.out.println("Opción no válida");
                    }
                    
                } catch (Exception e) {
                    System.err.println("Error en la operación: " + e.getMessage());
                    scanner.nextLine(); // Limpiar el buffer
                }
            }
            
            scanner.close();
            
        } catch (Exception e) {
            System.err.println("Error en el cliente: " + e.toString());
            e.printStackTrace();
        }
    }
}
