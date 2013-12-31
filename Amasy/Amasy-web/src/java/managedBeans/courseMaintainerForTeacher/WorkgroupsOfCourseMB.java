package managedBeans.courseMaintainerForTeacher;

import DTOs.AnswerDTO;
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
    private ListUserDTO studentsWithGroup;
    private ListUserDTO studentsWithoutGroup;
    private DualListModel<UserDTO> studentsWithGroupPL;
    private DualListModel<UserDTO> studentsWithoutGroupPL;
    
    public WorkgroupsOfCourseMB() {
    }
    
    @PostConstruct
    void init(){
        idCourse = courseMaintainerOfTeacherConversation.getIdCourse();
        listGroup = courseManagementSB.getAllGroupsOfCourse(idCourse).getListGroup();
        listGroupName = createStringList(listGroup);
        studentsWithGroup = courseManagementSB.getAllStudentsWithGroup(idCourse);
        studentsWithoutGroup = courseManagementSB.getAllStudentsWithoutGroup(idCourse);
        List<UserDTO> withGroup = (List<UserDTO>) studentsWithGroup.getListUser();
        List<UserDTO> withGroupTarget = new ArrayList<>();
        List<UserDTO> withoutGroup = (List<UserDTO>) studentsWithoutGroup.getListUser();
        List<UserDTO> withoutGroupTarget = new ArrayList<>();
        studentsWithGroupPL = new DualListModel<>(withGroup, withGroupTarget);
        studentsWithoutGroupPL = new DualListModel<>(withoutGroup, withoutGroupTarget);
    }
    
    public void createGroup(){
        AnswerDTO ans = new AnswerDTO();
        ans = courseManagementSB.createGroup(idCourse,groupName);
        if(ans.getIdError()==0){
            listGroup = courseManagementSB.getAllGroupsOfCourse(idCourse).getListGroup();
            listGroupName = createStringList(listGroup);
        }
        UtilitiesMB.showFeedback(ans);
    }
    
    public void deleteGroup(){
        AnswerDTO ans = new AnswerDTO();
        ans = courseManagementSB.deleteGroup(selectedGroup, idCourse);
        if(ans.getIdError()==0){
            listGroup = courseManagementSB.getAllGroupsOfCourse(idCourse).getListGroup();
            listGroupName = createStringList(listGroup);
        }
        UtilitiesMB.showFeedback(ans);
    }
    
    public void probandoOnTransfer(){
        System.out.println("ON TRANSFEEEEEER !!!!!");
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

    public ListUserDTO getStudentsWithGroup() {
        return studentsWithGroup;
    }

    public void setStudentsWithGroup(ListUserDTO studentsWithGroup) {
        this.studentsWithGroup = studentsWithGroup;
    }

    public ListUserDTO getStudentsWithoutGroup() {
        return studentsWithoutGroup;
    }

    public void setStudentsWithoutGroup(ListUserDTO studentsWithoutGroup) {
        this.studentsWithoutGroup = studentsWithoutGroup;
    }

    public DualListModel<UserDTO> getStudentsWithGroupPL() {
        return studentsWithGroupPL;
    }

    public void setStudentsWithGroupPL(DualListModel<UserDTO> studentsWithGroupPL) {
        this.studentsWithGroupPL = studentsWithGroupPL;
    }

    public DualListModel<UserDTO> getStudentsWithoutGroupPL() {
        return studentsWithoutGroupPL;
    }

    public void setStudentsWithoutGroupPL(DualListModel<UserDTO> studentsWithoutGroupPL) {
        this.studentsWithoutGroupPL = studentsWithoutGroupPL;
    }
    
    private List<String> createStringList(List<GroupStudentPerCourseDTO> listGroup){
        List<String> list = new ArrayList();
        for (GroupStudentPerCourseDTO it : listGroup) {
            list.add(it.getName());
        }
        return list;
    }
    
}
