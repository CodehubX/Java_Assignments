package ver1;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Intro_Server {
    public static void main(String args[]) {
        try {
            Registry registry = LocateRegistry
                .createRegistry(Registry.REGISTRY_PORT);
            ServerImpl server = new ServerImpl();
            registry.rebind("PKP", server);
            // Naming.rebind("Counter", myCounter);
            System.out.println("Server started");

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
