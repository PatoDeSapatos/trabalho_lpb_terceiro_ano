package model.user;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserVO {
    private String id;
    private String login;
    private String password;
    private String name;
    private String cpf;
    private String email;
    private String phoneNumber;

    public UserVO(String id, String login, String password, String name, String cpf, String email, String phoneNumber) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    
    public UserVO(String login, String password, String name, String cpf, String email, String phoneNumber) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

      public UserVO(ResultSet result) throws SQLException {
        this.id = result.getString(1); 
        this.login = result.getString(2);
        this.password = result.getString(3);
        this.name = result.getString(4);
        this.cpf = result.getString(5);
        this.email = result.getString(6);
        this.phoneNumber = result.getString(7);
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    
}
