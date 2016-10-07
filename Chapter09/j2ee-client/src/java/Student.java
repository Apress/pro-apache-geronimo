package samples.cmp.student;

import javax.ejb.*;
import java.rmi.*;

public interface Student extends EJBObject {

  public String getFirstName() throws RemoteException;

  public String getLastName() throws RemoteException;
  public void setLastName(String name)throws RemoteException;

  public int getMarks() throws RemoteException;
  public void setMarks(int marks)throws RemoteException;

  public boolean passed() throws RemoteException;

}
