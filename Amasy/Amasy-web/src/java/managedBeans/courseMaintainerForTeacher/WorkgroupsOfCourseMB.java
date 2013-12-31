package managedBeans.courseMaintainerForTeacher;

import DTOs.AnswerDTO;
import DTOs.CourseDTO;
import DTOs.GroupStudentPerCourseDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import managedBeans.UtilitiesMB;
import sessionBeans.courseManagement.CourseManagementSBLocal;

/**
 *
 * @author Pingeso
 */
@Named(value = "workgroupsOfCourseMB")
@RequestScoped
public class WorkgroupsOfCourseMB {
    @EJB
    private CourseManagementSBLocal courseManagementSB;

    @Inject 
    private CourseMaintainerOfTeacherConversationalMB courseMaintainerOfTeacherConversation;
    
    private Long idCourse;
    private String groupName;
    private List<GroupStudentPerCourseDTO> listGroup;
    private List<String> listGroupName;
    private String selectedGroup;
    
    public WorkgroupsOfCourseMB() {
    }
    
    @PostConstruct
    void init(){
        idCourse = courseMaintainerOfTeacherConversation.getIdCourse();
        listGroup = courseManagementSB.getAllGroupsOfCourse(idCourse).getListGroup();
        listGroupName = createStringList(listGroup);
    }
    
    public void createGroup(){
        AnswerDTO ans = new AnswerDTO();
        ans = courseManagementSB.createGroup(idCourse,groupName);
        UtilitiesMB.showFeedback(ans);
    }
    
    public void deleteGroup(){
        AnswerDTO ans = new AnswerDTO();
        ans = courseManagementSB.deleteGroup(selectedGroup, idCourse);
        UtilitiesMB.showFeedback(ans);
    }

    public Long getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(Long idCourse) {
        this.idCourse = idCourse;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<GroupStudentPerCourseDTO> getListGroup() {
        return listGroup;
    }

    public void setListGroup(List<GroupStudentPerCourseDTO> listGroup) {
        this.listGroup = listGroup;
    }

    public List<String> getListGroupName() {
        return listGroupName;
    }

    public void setListGroupName(List<String> listGroupName) {
        this.listGroupName = listGroupName;
    }

    public String getSelectedGroup() {
        return selectedGroup;
    }

    public void setSelectedGroup(String selectedGroup) {
        this.selectedGroup = selectedGroup;
    }
    
    private List<String> createStringList(List<GroupStudentPerCourseDTO> listGroup){
        List<String> list = new ArrayList();
        for (GroupStudentPerCourseDTO it : listGroup) {
            list.add(it.getName());
        }
        return list;
    }
    
}
