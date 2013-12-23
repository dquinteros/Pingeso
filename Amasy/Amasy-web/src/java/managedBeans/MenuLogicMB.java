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

    /**
     *
     * @param view
     * @return
     */
    public String isIndex(String view) {
        if ("/admin/index.xhtml".equals(view)) {
            return "on-hover";
        } else {
            return "";
        }
    }

    /**
     *
     * @param view
     * @return
     */
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

    /**
     *
     * @param view
     * @return
     */
    public String isNotStudentView(String view) {
        switch (view) {
            case "/admin/studentMaintainer/viewAllStudent.xhtml":
                return "";
            case "/admin/studentMaintainer/addStudent.xhtml":
                return "";
            case "/admin/studentMaintainer/editStudent.xhtml":
                return "";
            case "/admin/studentMaintainer/enrollStudentOnCourse.xhtml":
                return "";
            default:
                return "hidden";
        }
    }

    /**
     *
     * @param view
     * @return
     */
    public String isViewStudent(String view) {
        if ("/admin/studentMaintainer/viewAllStudent.xhtml".equals(view)) {
            return "on-hover";
        } else {
            return "";
        }
    }

    /**
     *
     * @param view
     * @return
     */
    public String isAddStudent(String view) {
        if ("/admin/studentMaintainer/addStudent.xhtml".equals(view)) {
            return "on-hover";
        } else {
            return "";
        }
    }

    /**
     *
     * @param view
     * @return
     */
    public String isEditStudent(String view) {
        if ("/admin/studentMaintainer/editStudent.xhtml".equals(view)) {
            return "on-hover";
        } else {
            return "";
        }
    }

    /**
     *
     * @param view
     * @return
     */
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

    /**
     *
     * @param view
     * @return
     */
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

    /**
     *
     * @param view
     * @return
     */
    public String isViewTeacher(String view) {
        if ("/admin/teacherMaintainer/viewAllTeacher.xhtml".equals(view)) {
            return "on-hover";
        } else {
            return "";
        }
    }

    /**
     *
     * @param view
     * @return
     */
    public String isAddTeacher(String view) {
        if ("/admin/teacherMaintainer/addTeacher.xhtml".equals(view)) {
            return "on-hover";
        } else {
            return "";
        }
    }

    /**
     *
     * @param view
     * @return
     */
    public String isEditTeacher(String view) {
        if ("/admin/teacherMaintainer/edirTeacher.xhtml".equals(view)) {
            return "on-hover";
        } else {
            return "";
        }
    }

    /**
     *
     * @param view
     * @return
     */
    public String isCouseListTeacher(String view) {
        if ("/teacher/takeAttendance/viewCourseList.xhtml".equals(view)) {
            return "on-hover";
        } else {
            return "";
        }
    }

    /**
     *
     * @param view
     * @return
     */
    public String isNotCourseViewTeacher(String view) {
        switch (view) {
            default:
                return "hidden";
        }
    }
    
    /**
     *
     * @param view
     * @return
     */
    public String isNotTakeAttendanceTeacher(String view) {
        switch (view) {
            case "/teacher/takeAttendance/takeAttendance.xhtml":
               return "";
            case "/faces/teacher/takeAttendance/viewCourseList.xhtml":
                return "";
            default:
                return "hidden";
        }
    }

    /**
     *
     * @param view
     * @return
     */
    public String isTakeAttendanceTeacher(String view) {
        if ("/teacher/takeAttendance/takeAttendance.xhtml".equals(view)) {
            return "on-hover";
        } else {
            return "";
        }
    }
    
    /**
     *
     * @param view
     * @return
     */
    public String isCourseViewAdmin(String view) {
        switch (view) {
            case "/admin/courseMaintainer/viewAllCourse.xhtml":
                return "on-hover";
            case "/admin/courseMaintainer/addCourse.xhtml":
                return "on-hover";
            case "/admin/courseMaintainer/edirCourse.xhtml":
                return "on-hover";
            default:
                return "";
        }
    }
    
    /**
     *
     * @param view
     * @return
     */
    public String isViewCourseAdmin(String view) {
        if ("/admin/courseMaintainer/viewAllCourse.xhtml".equals(view)) {
            return "on-hover";
        } else {
            return "";
        }
    }

    /**
     *
     * @param view
     * @return
     */
    public String isAddCourseAdmin(String view) {
        if ("/admin/courseMaintainer/addCourse.xhtml".equals(view)) {
            return "on-hover";
        } else {
            return "";
        }
    }
    
    /**
     *
     * @param view
     * @return
     */
    public String isNotCourseViewAdmin(String view) {
        switch (view) {
            case "/admin/courseMaintainer/viewAllCourse.xhtml":
                return "";
            case "/admin/courseMaintainer/addCourse.xhtml":
                return "";
            case "/admin/courseMaintainer/edirCourse.xhtml":
                return "";
            default:
                return "hidden";
        }
    }
}
