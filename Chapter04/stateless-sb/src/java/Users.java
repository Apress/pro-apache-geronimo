package samples.usermgmt.sl;

import java.util.*;

// A simple in-memory database of users - works for single JVM case only
public class Users {

  private List users;
  private static Users instance;

  static { instance=new Users(); }

  private Users(){
    users=new ArrayList();
  }

  public static Users getInstance(){
    return instance;
  }

  public User addUser(String userName) throws UserMgmtException {

    if(null==userName){
      throw new UserMgmtException("Invalid User name");
    }

     User user=new User(userName);
     user.setId(users.size());
     users.add(user);
     return user;
  }

  public List listUser(String userNamePattern) throws  UserMgmtException {
      List results=new ArrayList();
      for(int i=0;i<users.size();i++){
        User user=(User)users.get(i);
        if (user.getName().matches(userNamePattern)){
          results.add(user);
        }
      }
      return results;
  }

  public void deleteUser(int userId) throws  UserMgmtException {
      users.remove(userId);
  }

}
