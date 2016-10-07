package samples.usermgmt.sl;

import java.util.*;
import java.rmi.*;
import javax.ejb.*;

public interface UserManagement extends EJBObject {

	public User addUser(String userName)throws RemoteException, UserMgmtException;

	public List listUser(String userNamePattern) throws RemoteException, UserMgmtException;

	public void deleteUser(long userId) throws RemoteException, UserMgmtException;

}