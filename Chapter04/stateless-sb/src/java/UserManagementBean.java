package samples.usermgmt.sl;

import javax.ejb.*;
import java.util.*;
import java.rmi.*;

public class UserManagementBean implements SessionBean {

  private Users users;

  private SessionContext context;

  public void ejbCreate() { users=Users.getInstance();}

  public void ejbRemove() { users=null;}

  public void ejbActivate() { }

  public void ejbPassivate() { }

  public void setSessionContext(SessionContext context) { this.context=context ; }

  public User addUser(String userName) throws RemoteException,UserMgmtException {

    return users.addUser(userName);
  }

  public List listUser(String userNamePattern) throws RemoteException, UserMgmtException {
      return users.listUser(userNamePattern);
  }

  public void deleteUser(int userId) throws RemoteException, UserMgmtException {
      users.deleteUser(userId);
  }

  public String sayGreeting(String to){
    return "Greetings "+to;
  }
}
