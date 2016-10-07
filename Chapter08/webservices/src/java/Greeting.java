package samples.usermgmt;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Greeting extends Remote {

	public String sayGreeting(String to) throws RemoteException;
}