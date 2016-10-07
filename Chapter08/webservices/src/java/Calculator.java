
package samples.ws.web;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculator extends Remote {

	public int add(int num1, int num2) throws RemoteException;
}