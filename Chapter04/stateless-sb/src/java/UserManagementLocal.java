package samples.usermgmt.sl;

import java.util.*;
import javax.ejb.*;

public interface UserManagementLocal extends EJBLocalObject {

    public User addUser(String userName)throws UserMgmtException;

    public List listUser(String userNamePattern) throws UserMgmtException;

    public void deleteUser(long userId) throws UserMgmtException;

}
