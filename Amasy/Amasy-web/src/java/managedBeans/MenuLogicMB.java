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
        switch(view){
            case "/admin/index.xhtml":
                return "on-hover";
            case "/teacher/index.xhtml":
                return "on-hover";
            case "/student/index.xhtml":
                return "on-hover";
            default: return "";
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
            case "/admin/studentMaintainer/enrollStudentOnCourse.xhtml":
                return "on-hover";
            default:
                return "";
        }
    }

    public String isCourseViewTeacher(String view){
        switch (view) {
            case "/teacher/courses/viewAllCoursesOfTeacher.xhtml":
                return "on-hover";       
            case "/teacher/courses/configureCourse.xhtml":
                return "on-hover";
            case "/teacher/courses/viewAllStudentsOfCourse.xhtml":
                return "on-hover";
            case "/teacher/courses/viewAssistanceOfStudent.xhtml":
                return "on-hover";
            case "/teacher/courses/courseOfStudent.xhtml":
                return "on-hover";                
            case "/teacher/courses/workgroupManagement.xhtml":
                return "on-hover";
            case "/teacher/courses/viewAssistanceOfCourse.xhtml":
                return "on-hover";                
            default:
                return "";
        }
    }
    
    public String isTakeAttendanceViewTeacher(String view){
        switch (view) {
            case "/teacher/takeAttendance/viewCourseList.xhtml":
                return "on-hover";       
            case "/teacher/takeAttendance/takeAttendance.xhtml":
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
            case "/admin/teacherMaintainer/editTeacher.xhtml":
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
            case "/admin/teacherMaintainer/editTeacher.xhtml":
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
        if ("/admin/teacherMaintainer/editTeacher.xhtml".equals(view)) {
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
            case "/teacher/courses/viewAllCoursesOfTeacher.xhtml":
                return "";
            case "/teacher/courses/configureCourse.xhtml":
                return "";
            case "/teacher/courses/viewAllStudentsOfCourse.xhtml":
                return "";
            case "/teacher/courses/viewAssistanceOfStudent.xhtml":
                return "";
            case "/teacher/courses/courseOfStudent.xhtml":
                return "";
            case "/teacher/courses/workgroupManagement.xhtml":
                return "";
            case "/teacher/courses/viewAssistanceOfCourse.xhtml":
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
    public String isNotTakeAttendanceTeacher(String view) {   
        switch (view) {
            case "/teacher/takeAttendance/takeAttendance.xhtml":
               return "";
            case "/teacher/takeAttendance/viewCourseList.xhtml":
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
    public String isCourseViewAdmin(String view) {
        switch (view) {
            case "/admin/courseMaintainer/viewAllCourse.xhtml":
                return "on-hover";
            case "/admin/courseMaintainer/addCourse.xhtml":
                return "on-hover";            
            case "/admin/courseMaintainer/editCourse.xhtml":
                return "on-hover";
            case "/admin/courseMaintainer/allocateBlockclassesoToCourse.xhtml":
                return "on-hover";
            case "/teacher/courses/viewAllStudentsOfCourse.xhtml":
                return "on-hover";
            case "/teacher/courses/viewAssistanceOfStudent.xhtml":
                return "on-hover";
            case "/teacher/courses/viewAssistanceOfCourse.xhtml":
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

    public String isViewListCourseTeacher(String view){
        if ("/teacher/courses/viewAllCoursesOfTeacher.xhtml".equals(view)) {
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
            case "/admin/courseMaintainer/editCourse.xhtml":
                return "";
            case "/admin/courseMaintainer/allocateBlockclassesoToCourse.xhtml":
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
    public String isAdminView(String view) {
        switch (view) {
            case "/admin/adminMaintainer/viewAllAdmin.xhtml":
                return "on-hover";
            case "/admin/adminMaintainer/addAdmin.xhtml":
                return "on-hover";
            case "/admin/adminMaintainer/editAdmin.xhtml":
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
    public String isNotAdminView(String view) {
        switch (view) {
            case "/admin/adminMaintainer/viewAllAdmin.xhtml":
                return "";
            case "/admin/adminMaintainer/addAdmin.xhtml":
                return "";
            case "/admin/adminMaintainer/editAdmin.xhtml":
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
    public String isViewAdmin(String view) {
        if ("/admin/adminMaintainer/viewAllAdmin.xhtml".equals(view)) {
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
    public String isAddAdmin(String view) {
        if ("/admin/adminMaintainer/addAdmin.xhtml".equals(view)) {
            return "on-hover";
        } else {
            return "";
        }
    }
    
    public String isUniversityView(String view) {
        switch (view) {
            case "/admin/universityMaintainer/viewAllUniversity.xhtml":
                return "on-hover";
            case "/admin/universityMaintainer/addUniversity.xhtml":
                return "on-hover";
            case "/admin/universityMaintainer/editUniversity.xhtml":
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
    public String isNotUniversityView(String view) {
        switch (view) {
            case "/admin/universityMaintainer/viewAllUniversity.xhtml":
                return "";
            case "/admin/universityMaintainer/addUniversity.xhtml":
                return "";
            case "/admin/universityMaintainer/editUniversity.xhtml":
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
    public String isViewUniversity(String view) {
        if ("/admin/universityMaintainer/viewAllUniversity.xhtml".equals(view)) {
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
    public String isAddUniversity(String view) {
        if ("/admin/universityMaintainer/addUniversity.xhtml".equals(view)) {
            return "on-hover";
        } else {
            return "";
        }
    }
    
    public String profileView(String user, String targetUser){
        if(user.equals(targetUser)) return "";
        else return "display: none";
    }
}
