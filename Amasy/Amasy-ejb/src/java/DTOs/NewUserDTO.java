/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import entity.User;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Pingeso
 */
@Stateless
@LocalBean
public class NewUserDTO {

    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String homePhone;
    private String cellPhone;
    private String fingerprint;
    private String rut;
    private String userType;
    private UniversityDTO universityDTO;

    /**
     *
     */
    public NewUserDTO() {
    }

    /**
     *
     * @param user
     */
    public NewUserDTO(User user) {
        rut = String.valueOf(user.getRut());
        userName = user.getUserName();
        email = user.getEmail();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        cellPhone = user.getCellPhone();
        homePhone = user.getHomePhone();
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
    public String getFingerprint() {
        return fingerprint;
    }

    /**
     *
     * @param fingerprint
     */
    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    /**
     *
     * @return
     */
    public String getRut() {
        return rut;
    }

    /**
     *
     * @param rut
     */
    public void setRut(String rut) {
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

    public UniversityDTO getUniversityDTO() {
        return universityDTO;
    }

    public void setUniversityDTO(UniversityDTO universityDTO) {
        this.universityDTO = universityDTO;
    }
}
