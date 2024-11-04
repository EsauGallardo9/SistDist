
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Servidor implements CalculadoraRemota {
    public Servidor() {}
    
    @Override
    public double sumar(double a, double b) throws RemoteException {
        System.out.println("Servidor: Realizando suma " + a + " + " + b);
        return a + b;
    }
    
    @Override
    public double restar(double a, double b) throws RemoteException {
        System.out.println("Servidor: Realizando resta " + a + " - " + b);
        return a - b;
    }
    
    @Override
    public double multiplicar(double a, double b) throws RemoteException {
        System.out.println("Servidor: Realizando multiplicación " + a + " * " + b);
        return a * b;
    }
    
    @Override
    public double dividir(double a, double b) throws RemoteException {
        if (b == 0) {
            throw new RemoteException("Error: División por cero");
        }
        System.out.println("Servidor: Realizando división " + a + " / " + b);
        return a / b;
    }
    
    public static void main(String[] args) {
        try {
            // Establecer la dirección IP del servidor
            String ipAddress = "148.204.88.62"; // Cambiar a tu IP
            System.setProperty("java.rmi.server.hostname", ipAddress);
            
            // Crear una instancia del servidor
            Servidor servidor = new Servidor();
            
            // Exportar el objeto remoto
            CalculadoraRemota stub = (CalculadoraRemota) UnicastRemoteObject.exportObject(servidor, 0);
            
            // Iniciar el registro RMI en el puerto 1099
            Registry registry = LocateRegistry.createRegistry(1099);
            
            // Registrar el stub
            registry.bind("CalculadoraRemota", stub);
            
            System.out.println("Servidor de Calculadora RMI iniciado en " + ipAddress + ":1099");
            
        } catch (Exception e) {
            System.err.println("Error en el servidor: " + e.toString());
            e.printStackTrace();
        }
    }
}

