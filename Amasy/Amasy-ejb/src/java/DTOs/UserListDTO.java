/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import java.util.LinkedList;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Pingeso
 */
@Stateless
@LocalBean
public class UserListDTO {
    private LinkedList<UserDTO> listUser;
    private Long totalPageNumber;
    private int currentPage;
    private int studentsPerPage;

    /**
     *
     * @return
     */
    public LinkedList<UserDTO> getListUser() {
        return listUser;
    }

    /**
     *
     * @param listUser
     */
    public void setListUser(LinkedList<UserDTO> listUser) {
        this.listUser = listUser;
    }

    /**
     *
     * @return
     */
    public Long getTotalPageNumber() {
        return totalPageNumber;
    }

    /**
     *
     * @param totalPageNumber
     */
    public void setTotalPageNumber(Long totalPageNumber) {
        this.totalPageNumber = totalPageNumber;
    }

    /**
     *
     * @return
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     *
     * @param currentPage
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     *
     * @return
     */
    public int getStudentsPerPage() {
        return studentsPerPage;
    }

    /**
     *
     * @param studentsPerPage
     */
    public void setStudentsPerPage(int studentsPerPage) {
        this.studentsPerPage = studentsPerPage;
    }

           
}
