package managedBeans.courseMaintainerForTeacher;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import sessionBeans.courseManagement.CourseManagementSBLocal;

/**
 *
 * @author Pingeso
 */
@Named(value = "workgroupsOfCourseMB")
@RequestScoped
public class WorkgroupsOfCourseMB {
    
    public WorkgroupsOfCourseMB() {
    }
    
    
    
}
