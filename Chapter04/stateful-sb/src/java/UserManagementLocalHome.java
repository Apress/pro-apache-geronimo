package samples.usermgmt.sf;

import javax.ejb.*;

public interface UserManagementLocalHome extends EJBLocalHome {

  public UserManagement create() throws CreateException ;
}
