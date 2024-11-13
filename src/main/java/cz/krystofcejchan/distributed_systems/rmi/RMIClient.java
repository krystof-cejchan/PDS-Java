package cz.krystofcejchan.distributed_systems.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {

    public static void main(String[] args) {
        try {
            // Získání RMI registru na hostu "localhost" a portu 8080
            Registry registry = LocateRegistry.getRegistry("localhost", 8080);

            var stub = (RemoteInterface) registry.lookup("RemoteInterface");

            // Volání vzdálené metody
            String response = stub.sayHello("Světe");
            System.out.println("Odezva ze serveru: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
