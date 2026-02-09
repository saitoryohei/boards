package bean;

import java.io.Serializable;

public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String login;
    private String password;
    private int role;
    

    // getter/setter
    public int getRole() {
    	return role;
    }
    public void setRole(int role){
    	this.role = role;
    	
    	
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}