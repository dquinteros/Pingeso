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

    /**
     *
     */
    public UserDTO() {
    }

    /**
     *
     * @param user
     */
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

    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public String getHomePhone() {
        return homePhone;
    }

    /**
     *
     * @param homePhone
     */
    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    /**
     *
     * @return
     */
    public String getCellPhone() {
        return cellPhone;
    }

    /**
     *
     * @param cellPhone
     */
    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    /**
     *
     * @return
     */
    public int getRut() {
        return rut;
    }

    /**
     *
     * @param rut
     */
    public void setRut(int rut) {
        this.rut = rut;
    }

    /**
     *
     * @return
     */
    public String getUserType() {
        return userType;
    }

    /**
     *
     * @param userType
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }
    
 
    /**
     *
     * @param user
     * @return
     */
    public Object getRowKey(UserDTO user) {  
        return user.getRut();
    } 

    
    /**
     *
     * @param string
     * @return
     */
    public UserDTO getRowData(String string) {
        return userManagementSB.findUserByRut(Integer.parseInt(string));
    }
    
}
