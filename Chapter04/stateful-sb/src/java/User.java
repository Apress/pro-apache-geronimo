package samples.usermgmt.sf;

import java.io.Serializable;

public class User implements Serializable {

	private int id;
	private String name;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String country; // Add getters and setters

	public User(String userName){
		this.name=userName;
	}

	public void setId(int id){
		this.id=id;
	}

	public int getId(){
		return id;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return name;
	}

}