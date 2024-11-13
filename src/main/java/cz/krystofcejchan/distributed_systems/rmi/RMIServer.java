package cz.krystofcejchan.distributed_systems.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {

    public static void main(String[] args) {
        try {
            // Vytvoření instance vzdáleného objektu
            var obj = new RemoteInterfaceImpl();

            // Vytvoření RMI registru na portu 8080
            Registry registry = LocateRegistry.createRegistry(8080);

            // Registrace vzdáleného objektu pod jménem "Hello"
            registry.rebind("RemoteInterface", obj);

            System.out.println("Server je připraven.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

