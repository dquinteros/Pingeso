package managedBeans.courseMaintainerForTeacher;

import DTOs.AnswerDTO;
import DTOs.CourseDTO;
import DTOs.GroupStudentPerCourseDTO;
import DTOs.ListUserDTO;
import DTOs.UserDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import managedBeans.UtilitiesMB;
import org.primefaces.model.DualListModel;
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
    private ListUserDTO studentsWithoutGroup;
    private DualListModel<UserDTO> studentsWithoutGroupPL;
    private CourseDTO course;

    public WorkgroupsOfCourseMB() {
    }

    @PostConstruct
    void init() {
        idCourse = courseMaintainerOfTeacherConversation.getIdCourse();
        course = courseManagementSB.getCourseById(idCourse);
        listGroup = courseManagementSB.getAllGroupsOfCourse(idCourse).getListGroup();
        listGroupName = createStringList(listGroup);
        studentsWithoutGroup = courseManagementSB.getAllStudentsWithoutGroup(idCourse);
        List<UserDTO> withoutGroup = (List<UserDTO>) studentsWithoutGroup.getListUser();
        List<UserDTO> withoutGroupTarget = new ArrayList<>();
        studentsWithoutGroupPL = new DualListModel<>(withoutGroup, withoutGroupTarget);
    }

    public void createGroup() {
        AnswerDTO ans = courseManagementSB.createGroup(idCourse, groupName);
        if (ans.getIdError() == 0) {
            listGroup = courseManagementSB.getAllGroupsOfCourse(idCourse).getListGroup();
            listGroupName = createStringList(listGroup);
            groupName = "";
        }
        UtilitiesMB.showFeedback(ans);
    }

    public void deleteGroup() {
        AnswerDTO ans = courseManagementSB.deleteGroup(selectedGroup, idCourse);
        listGroup = courseManagementSB.getAllGroupsOfCourse(idCourse).getListGroup();
        listGroupName = createStringList(listGroup);
        studentsWithoutGroup = courseManagementSB.getAllStudentsWithoutGroup(idCourse);
        List<UserDTO> withoutGroup = (List<UserDTO>) studentsWithoutGroup.getListUser();
        List<UserDTO> withoutGroupTarget = new ArrayList<>();
        studentsWithoutGroupPL = new DualListModel<>(withoutGroup, withoutGroupTarget);
        selectedGroup = null;
        UtilitiesMB.showFeedback(ans);
    }

    public void showStudentsOfGroup() {
        studentsWithoutGroupPL.setTarget(courseManagementSB.getStudentsOfGroup(selectedGroup, idCourse));
    }

    public void updateGroup() {
        List<Long> listIdUser = new ArrayList();
        for (int i = 0; i < studentsWithoutGroupPL.getTarget().size(); i++) {
            Object temp = studentsWithoutGroupPL.getTarget().get(i);
            listIdUser.add(Long.parseLong(temp.toString()));
        }
        AnswerDTO ans = courseManagementSB.updateGroup(listIdUser, selectedGroup, idCourse);
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

    public ListUserDTO getStudentsWithoutGroup() {
        return studentsWithoutGroup;
    }

    public void setStudentsWithoutGroup(ListUserDTO studentsWithoutGroup) {
        this.studentsWithoutGroup = studentsWithoutGroup;
    }

    public DualListModel<UserDTO> getStudentsWithoutGroupPL() {
        return studentsWithoutGroupPL;
    }

    public void setStudentsWithoutGroupPL(DualListModel<UserDTO> studentsWithoutGroupPL) {
        this.studentsWithoutGroupPL = studentsWithoutGroupPL;
    }

    public CourseDTO getCourse() {
        return course;
    }

    public void setCourse(CourseDTO course) {
        this.course = course;
    }
    
    private List<String> createStringList(List<GroupStudentPerCourseDTO> listGroup) {
        List<String> list = new ArrayList();
        for (GroupStudentPerCourseDTO it : listGroup) {
            list.add(it.getName());
        }
        return list;
    }
}
