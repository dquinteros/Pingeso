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
@NamedQueries( {
    @NamedQuery(name="User.findByRut", query="SELECT u FROM User u WHERE u.rut = :rut"),
    @NamedQuery(name="User.findByUserName", query="SELECT u FROM User u WHERE u.userName = :username"),
    @NamedQuery(name="User.selectAllFingerprint", query="SELECT u.fingerPrint FROM User u"),
    @NamedQuery(name="User.findByFingerprint", query="SELECT u FROM User u WHERE u.fingerPrint = :fingerprint"),
    @NamedQuery(name="User.findByEmail", query="SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name="User.countUserByRut", query="SELECT COUNT(u) FROM User u WHERE u.rut = :rut"),
    @NamedQuery(name="User.countUserByEmail", query="SELECT COUNT(u) FROM User u WHERE u.email = :email"),
    @NamedQuery(name="User.countUserByUserName", query="SELECT COUNT(u) FROM User u WHERE u.userName = :username")
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
    @Lob
    private String fingerPrint;    
    @ManyToOne
    private UserType userType;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    } 
    
    public String getFirstName() {
        return firstName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getFingerPrint() {
        return fingerPrint;
    }

    public void setFingerPrint(String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }    

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

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

    @Override
    public String toString() {
        return "entity.user[ id=" + id + " ]";
    }
    
}
