package cz.krystofcejchan.distributed_systems.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteInterfaceImpl extends UnicastRemoteObject implements RemoteInterface {

    protected RemoteInterfaceImpl() throws RemoteException {
        super();
    }

    @Override
    public String sayHello(String name) throws RemoteException {
        return String.format("Hello %s.", name);
    }
}

