package samples.usermgmt.sf;

import javax.ejb.*;
import java.util.*;
import java.rmi.*;

public class UserManagementBean implements SessionBean {

  private List users;
  private SessionContext context;
  public void ejbCreate() { users=new ArrayList();}

  public void ejbRemove() { users=null;}

  public void ejbActivate() { }

  public void ejbPassivate() { }

  public void setSessionContext(SessionContext context) {
    this.context=context ;
  }

  public User addUser(String userName) throws RemoteException,
  UserMgmtException {

    if(null==userName){
      throw new UserMgmtException("Invalid User name");
    }

    User user=new User(userName);
    user.setId(users.size());
    users.add(user);
    return user;
  }

  public List listUser(String userNamePattern) throws RemoteException,
  UserMgmtException {

    List results=new ArrayList();
    for(int i=0;i<users.size();i++){
      User user=(User)users.get(i);
      if (user.getName().matches(userNamePattern)){
        results.add(user);
      }
    }
    return results;
  }

  public void deleteUser(int userId) throws RemoteException, UserMgmtException {
    users.remove(userId);
  }

}

