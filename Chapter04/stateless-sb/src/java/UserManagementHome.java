package samples.usermgmt.sl;

import javax.ejb.*;
import java.rmi.*;

public interface UserManagementHome extends EJBHome {

  public UserManagement create() throws RemoteException, CreateException ;
}
