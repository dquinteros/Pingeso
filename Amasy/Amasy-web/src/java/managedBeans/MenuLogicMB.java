/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Pingeso
 */
@Named(value = "menuLogicMB")
@Dependent
public class MenuLogicMB {

    /**
     * Creates a new instance of MenuLogicMB
     */
    public MenuLogicMB() {
    }
    
    public String isIndex(String view){
        if("/admin/index.xhtml".equals(view)){
            return "on-hover";
        } else {
            return "";
        }
    }

    public String isStudentView(String view) {
        switch (view) {
            case "/admin/studentMaintainer/viewAllStudent.xhtml":
                return "on-hover";
            case "/admin/studentMaintainer/addStudent.xhtml":
                return "on-hover";
            case "/admin/studentMaintainer/editStudent.xhtml":
                return "on-hover";
            default:
                return "";
        }
    }
    
    public String isNotStudentView(String view) {
        switch (view) {
            case "/admin/studentMaintainer/viewAllStudent.xhtml":
                return "";
            case "/admin/studentMaintainer/addStudent.xhtml":
                return "";
            case "/admin/studentMaintainer/editStudent.xhtml":
                return "";
            default:
                return "hidden";
        }
    }
    
    

    public String isViewStudent(String view) {
        if ("/admin/studentMaintainer/viewAllStudent.xhtml".equals(view)) {
            return "on-hover";
        } else {
            return "";
        }
    }

    public String isAddStudent(String view) {
        if ("/admin/studentMaintainer/addStudent.xhtml".equals(view)) {
            return "on-hover";
        } else {
            return "";
        }
    }

    public String isEditStudent(String view) {
        if ("/admin/studentMaintainer/editStudent.xhtml".equals(view)) {
            return "on-hover";
        } else {
            return "";
        }
    }

    public String isTeacherView(String view) {
        switch (view) {
            case "/admin/teacherMaintainer/viewAllTeacher.xhtml":
                return "on-hover";
            case "/admin/teacherMaintainer/addTeacher.xhtml":
                return "on-hover";
            case "/admin/teacherMaintainer/edirTeacher.xhtml":
                return "on-hover";
            default:
                return "";
        }
    }

    public String isNotTeacherView(String view) {
        switch (view) {
            case "/admin/teacherMaintainer/viewAllTeacher.xhtml":
                return "";
            case "/admin/teacherMaintainer/addTeacher.xhtml":
                return "";
            case "/admin/teacherMaintainer/edirTeacher.xhtml":
                return "";
            default:
                return "hidden";
        }
    }

    public String isViewTeacher(String view) {
        if ("/admin/teacherMaintainer/viewAllTeacher.xhtml".equals(view)) {
            return "on-hover";
        } else {
            return "";
        }
    }

    public String isAddTeacher(String view) {
        if ("/admin/teacherMaintainer/addTeacher.xhtml".equals(view)) {
            return "on-hover";
        } else {
            return "";
        }
    }

    public String isEditTeacher(String view) {
        if ("/admin/teacherMaintainer/edirTeacher.xhtml".equals(view)) {
            return "on-hover";
        } else {
            return "";
        }
    }
}
