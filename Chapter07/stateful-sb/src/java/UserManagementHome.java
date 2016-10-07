package samples.usermgmt.sf;

import javax.ejb.*;
import java.rmi.*;

public interface UserManagementHome extends EJBHome {

  public UserManagement create() throws RemoteException, CreateException ;
}
