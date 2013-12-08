/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.studentMaintainer;

import DTOs.UserListDTO;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import sessionBeans.studentManagement.StudentManagementSBLocal;

/**
 *
 * @author Pingeso
 */
@Named(value = "viewAllStudentMB")
@RequestScoped
public class ViewAllStudentMB {
    @EJB
    private StudentManagementSBLocal studentManagementSB;
    
    private UserListDTO userListDTO;
    private String rut;
    private String firstName;
    private String lastName;
    private int page;
    private int studentsPerPage;
    
    /**
     * Creates a new instance of ViewAllStudent
     */
    
    
    public ViewAllStudentMB() {
    }
    
    @PostConstruct
    void init(){
        getStudent();
    }
    
    public void getStudent(){        
        rut="";
        firstName="";
        lastName="";
        page=1;
        studentsPerPage=10;
        userListDTO = studentManagementSB.getUsersPerTable(rut, firstName, lastName, page, studentsPerPage);
        System.out.println(userListDTO.getTotalPageNumber());
    }

    
    public UserListDTO getUserListDTO() {
        return userListDTO;
    }

    public void setUserListDTO(UserListDTO userListDTO) {
        this.userListDTO = userListDTO;
    }

    

    public int getStudentsPerPage() {
        return studentsPerPage;
    }

    public void setStudentsPerPage(int studentsPerPage) {
        this.studentsPerPage = studentsPerPage;
    }    

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
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

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
