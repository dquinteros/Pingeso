/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Pingeso
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "User.findByRut", query = "SELECT u FROM User u WHERE u.rut = :rut AND u.userStatus = true"),
    @NamedQuery(name = "User.findByUserName", query = "SELECT u FROM User u WHERE u.userName = :username AND u.userStatus = true"),
    @NamedQuery(name = "User.selectAllFingerprint", query = "SELECT u.fingerPrint FROM User u WHERE u.userStatus = true"),
    @NamedQuery(name = "User.findByFingerprint", query = "SELECT u FROM User u WHERE u.fingerPrint = :fingerprint AND u.userStatus = true"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email AND u.userStatus = true"),
    @NamedQuery(name = "User.countUserByRut", query = "SELECT COUNT(u) FROM User u WHERE u.rut = :rut AND u.userStatus = true"),
    @NamedQuery(name = "User.countUserByEmail", query = "SELECT COUNT(u) FROM User u WHERE u.email = :email AND u.userStatus = true"),
    @NamedQuery(name = "User.countUserByUserName", query = "SELECT COUNT(u) FROM User u WHERE u.userName = :username AND u.userStatus = true")
})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String homePhone;
    private String cellPhone;
    private int rut;
    private boolean userStatus = true; //true activo; false inactivo;
    @Lob
    private String fingerPrint;
    @ManyToOne
    private UserType userType;

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
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
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
    public boolean isUserStatus() {
        return userStatus;
    }

    /**
     *
     * @param userStatus
     */
    public void setUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
    }

    /**
     *
     * @return
     */
    public String getFingerPrint() {
        return fingerPrint;
    }

    /**
     *
     * @param fingerPrint
     */
    public void setFingerPrint(String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

    /**
     *
     * @return
     */
    public UserType getUserType() {
        return userType;
    }

    /**
     *
     * @param userType
     */
    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "entity.user[ id=" + id + " ]";
    }
}
