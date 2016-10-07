
package samples.cmp.student;

import java.io.*;

public class StudentPKey implements Serializable {
    public String firstName;

    public StudentPKey(){}
    public StudentPKey(String firstName){this.firstName=firstName;}

    public int hashCode(){return firstName.hashCode();}
    public boolean equals(Object obj){
      boolean ret=false;
      if(obj instanceof StudentPKey){
        ret=((StudentPKey)obj).equals(firstName);
      }
      return ret;
    }

}
