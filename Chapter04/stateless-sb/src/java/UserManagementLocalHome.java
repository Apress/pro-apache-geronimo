package samples.usermgmt.sl;

import javax.ejb.*;

public interface UserManagementLocalHome extends EJBLocalHome {

  public UserManagement create() throws CreateException ;
}
