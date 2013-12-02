/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;


import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Pingeso
 */

@NamedQueries( {
    @NamedQuery(name = "Assistance.findStudentAssistance", query = "SELECT COUNT(a) FROM Assistance a WHERE a.blockClass.id = :idBlockClass AND a.student.id = :idStudent")
})

@Entity
public class Assistance implements Serializable {
    private static final long serialVersionUID = 1L;

    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date date;
    
    @Id
    @OneToOne
    private Student student;
    @Id
    @OneToOne
    private BlockClass blockClass;
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    
    

    public BlockClass getBlockClass() {
        return blockClass;
    }

    public void setBlockClass(BlockClass blockClass) {
        this.blockClass = blockClass;
    }
            
    
    
}
