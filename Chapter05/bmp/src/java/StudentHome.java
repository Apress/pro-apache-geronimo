
package samples.bmp.student;

import javax.ejb.*;
import java.rmi.*;
import java.util.*;

public interface StudentHome extends EJBHome {
    // create methods
    public Student create(String firstName)
    						throws CreateException, RemoteException;
    public Student create(String firstName, String lastName)
    						throws CreateException,RemoteException;

    // finder methods
    public Student findByPrimaryKey(StudentPKey pk)
    						throws FinderException,RemoteException;
    public Collection findAll()
    						throws FinderException,RemoteException;

    // home business methods
    public int getPassPercentage() throws RemoteException;
}
