/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.studentMaintainer;

import DTOs.UserDTO;
import java.util.LinkedList;
import java.util.List;
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
    
    private LinkedList<UserDTO> userList;
    private String rut;
    private String firstName;
    private String lastName;
    private int page;
    private int studentsPerPage;
    private UserDTO selectedStudent;
    private List<UserDTO> filteredStudents;
    
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
        userList = studentManagementSB.getAllStudent();
        //System.out.println(userListDTO.getTotalPageNumber());
    }

    public LinkedList<UserDTO> getUserList() {
        return userList;
    }

    public void setUserList(LinkedList<UserDTO> userList) {
        this.userList = userList;
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

    public UserDTO getSelectedStudent() {
        return selectedStudent;
    }

    public void setSelectedStudent(UserDTO selectedStudent) {
        this.selectedStudent = selectedStudent;
    }

    public List<UserDTO> getFilteredStudents() {
        return filteredStudents;
    }

    public void setFilteredStudents(List<UserDTO> filteredStudents) {
        this.filteredStudents = filteredStudents;
    }    
    
}
