/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import entity.User;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import sessionBeans.UserManagementSBLocal;

/**
 *
 * @author Pingeso
 */
@Stateless
@LocalBean
public class UserDTO implements Serializable{
    @EJB
    private UserManagementSBLocal userManagementSB;

    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String homePhone;
    private String cellPhone;
    private int rut;
    private String userType;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.homePhone = user.getHomePhone();
        this.cellPhone = user.getCellPhone();
        this.rut = user.getRut();
        this.userType = user.getUserType().getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public int getRut() {
        return rut;
    }

    public void setRut(int rut) {
        this.rut = rut;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
    
 
    public Object getRowKey(UserDTO user) {  
        return user.getRut();
    } 

    
    public UserDTO getRowData(String string) {
        return userManagementSB.findUserByRut(Integer.parseInt(string));
    }
    
}
