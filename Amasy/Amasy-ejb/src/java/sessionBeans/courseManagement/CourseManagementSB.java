/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans.courseManagement;

import DTOs.AnswerDTO;
import DTOs.AssistanceListCourseDTO;
import DTOs.AssistanceListCourseRowDTO;
import DTOs.AssistanceListCourseTitleDTO;
import DTOs.AssistanceListCourseUnitDTO;
import DTOs.BlockClassDTO;
import DTOs.BlockClassListDTO;
import DTOs.CourseDTO;
import DTOs.DayBlockClassDTO;
import DTOs.DayBlockClassListDTO;
import DTOs.ListCourseDTO;
import DTOs.ListGroupStudentPerCourseDTO;
import DTOs.TimeBlockClassDTO;
import DTOs.TimeBlockClassListDTO;
import entity.Assistance;
import entity.AssistanceState;
import entity.DayBlockClass;
import entity.TimeBlockClass;
import DTOs.ListUserDTO;
import DTOs.UserDTO;
import entity.BlockClass;
import entity.Course;
import entity.GroupStudentPerCourse;
import entity.Student;
import entity.Teacher;
import entity.University;
import entity.User;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import sessionBeans.TakeAttendanceSB;

/**
 *
 * @author Pingeso
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class CourseManagementSB implements CourseManagementSBLocal {
    
    @PersistenceContext(unitName = "Amasy-ejbPU")
    private EntityManager em;
    @Resource
    UserTransaction ut;

    /**
     *
     * @param object
     * @return
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public boolean persistInsert(Object object) {
        try {
            ut.begin(); // Start a new transaction
            try {
                em.persist(object);
                ut.commit(); // Commit the transaction
                return true;
            } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | SystemException e) {
                ut.rollback(); // Rollback the transaction
                return false;
            }
        } catch (NotSupportedException | SystemException ex) {
            Logger.getLogger(TakeAttendanceSB.class.getName()).log(Level.SEVERE, null, ex); // Rollback the transaction
            return false;
        }
    }

    /**
     *
     * @param object
     * @return
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public boolean persistUpdate(Object object) {
        try {
            ut.begin(); // Start a new transaction
            try {
                em.merge(object);
                ut.commit(); // Commit the transaction
                return true;
            } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | SystemException e) {
                ut.rollback(); // Rollback the transaction
                System.out.println("rollbakc:" + e);
                return false;
            }
        } catch (NotSupportedException | SystemException ex) {
            Logger.getLogger(TakeAttendanceSB.class.getName()).log(Level.SEVERE, null, ex); // Rollback the transaction
            System.out.println("rollbakc:" + ex);
            return false;
        }
    }

    /**
     *
     * @return
     */
    @Override
    @SuppressWarnings("empty-statement")
    public ListCourseDTO getAllCourse() {
        Collection<Course> result;
        ListCourseDTO exitResult = new ListCourseDTO();
        Query q = this.em.createNamedQuery("Course.getAllCourses");
        try {
            result = (Collection<Course>) q.getResultList();
            return sqlResultToCourseList(result, exitResult);
        } catch (NoResultException nre) {
            System.out.println(nre);
            return null;
        }
    }
    
    private ListCourseDTO sqlResultToCourseList(Collection<Course> result, ListCourseDTO exitResult) {
        CourseDTO courseDTOTemp;
        Collection<CourseDTO> listCourseTemp = new ArrayList<>();
        for (Course iter : result) {
            courseDTOTemp = new CourseDTO(iter);
            courseDTOTemp.setNameUniversity(iter.getUniversity().getName());
            if(iter.getTeacher()!=null){
                courseDTOTemp.setTeacher(new UserDTO(iter.getTeacher().getUser()));
            }                        
            courseDTOTemp.setNameUniversity(iter.getUniversity().getName());
            listCourseTemp.add(courseDTOTemp);            
        }
        exitResult.setListCourse(listCourseTemp);
        exitResult.setAnswerDTO(new AnswerDTO(0));
        return exitResult;
    }

    /**
     *
     * @param object
     */
    public void persist(Object object) {
        em.persist(object);
    }

    /**
     *
     * @param courseDTO
     * @return
     */
    @Override    
    public AnswerDTO insertNewCourse(CourseDTO courseDTO, Long idUser, Long idUniversity) {        
        AnswerDTO existCourseName = validateCourseRegistry(courseDTO);
        if (existCourseName.getIdError() != 0) {
            return existCourseName;
        }
        Course course = newCourse(courseDTO);
        if(idUser!=null){
            Teacher teacher = em.find(User.class, idUser).getTeacher();
            course.setTeacher(teacher);
        }        
        University university = em.find(University.class, idUniversity);
        course.setUniversity(university);
        persistInsert(course);
        return new AnswerDTO(0);
    }
    
    private AnswerDTO validateCourseRegistry(CourseDTO courseDTO) {
        if (courseDTO == null) {
            return new AnswerDTO(109);
        }
        boolean existName = existName(courseDTO.getName());
        if (existName) {
            return new AnswerDTO(125);
        } else {
            return new AnswerDTO(000);
        }
    }
    
    private Course newCourse(CourseDTO courseDTO) {
        Course course = new Course();
        course.setName(courseDTO.getName());
        course.setLevel(courseDTO.getLevel());
        course.setMinutesBeforeClassStart(0);
        course.setMinutesAfterClassStart(30);
        return course;
    }
    
    private boolean existName(String name) {
        Long count;
        Query q = em.createNamedQuery("Course.countCourseByName", Course.class);
        q.setParameter("name", name);
        count = (Long) q.getSingleResult();
        if (count == 0) {
            return false;
        } else {
            return true;
        }
    }
    
    @Override
    public CourseDTO getCourseByName(String courseName) {
        Course course = em.find(Course.class, courseName);
        CourseDTO currentCourse = new CourseDTO(course);
        return currentCourse;
    }
    
    @Override
    @SuppressWarnings("empty-statement")
    public ListCourseDTO getAllCoursesOfTeacher(Long idUser) {
        Collection<Course> result;
        Query q = this.em.createNamedQuery("Course.getAllCoursesOfTeacher");
        q.setParameter("idUser", idUser);
        try {
            result = (Collection<Course>) q.getResultList();
            return sqlResultToCourseList(result, new ListCourseDTO());
        } catch (NoResultException nre) {
            System.out.println(nre);
            return null;
        }
    }
    
    @Override
    public CourseDTO getCourseById(Long courseId) {
        Course course = em.find(Course.class, courseId);
        CourseDTO courseDTO = new CourseDTO(course);
        if(course.getTeacher()!=null){
            courseDTO.setTeacher(new UserDTO(course.getTeacher().getUser()));            
        }
        courseDTO.setNameUniversity(course.getUniversity().getName());
        courseDTO.setIdUniversity(course.getUniversity().getId());
        return courseDTO;
    }
    
    @Override
    public AnswerDTO allocateBlockclassesoToCourse(Long idCourse, BlockClassDTO blockClassDTO) {
        Course course = em.find(Course.class, idCourse);
        BlockClass blockClass = generateBlockClass(course, blockClassDTO);
        course.getListBlockClass().add(blockClass);
        persistInsert(blockClass);
        persistUpdate(course);
        List<Assistance> listAssistance = generateAssistanceToStudent(course, blockClass);
        return new AnswerDTO(0);
    }
    
    private boolean existBlockClass(BlockClassDTO blockClassDTO) {
        Long count;
        Query q = em.createNamedQuery("BlockClass.existBlockClass", BlockClass.class);
        q.setParameter("idDayBlockClass", blockClassDTO.getDayBlockClass());
        q.setParameter("idTimeBlockClass", blockClassDTO.getTimeBlockClass());
        count = (Long) q.getSingleResult();
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    private BlockClass generateBlockClass(Course course, BlockClassDTO blockClassDTO) {
        BlockClass blockClass;
        DayBlockClass dayBlockClass;
        TimeBlockClass timeBlockClass;
        blockClass = new BlockClass();
        blockClass.setDate(blockClassDTO.getDate());
        blockClass.setDone(false);
        dayBlockClass = em.find(DayBlockClass.class, blockClassDTO.getDayBlockClass());
        timeBlockClass = em.find(TimeBlockClass.class, blockClassDTO.getTimeBlockClass());
        blockClass.setDayBlockClass(dayBlockClass);
        blockClass.setTimeBlockClass(timeBlockClass);
        blockClass.setCourse(course);
        blockClass.setComment("");
        blockClass.setListAssistance(new LinkedList<Assistance>());
        return blockClass;
    }
    
    private List<Assistance> generateAssistanceToStudent(Course course, BlockClass blockClass) {
        Query q = this.em.createNamedQuery("Student.getStundentOfCourse");
        q.setParameter("idCourse", course.getId());
        List<Student> listStudent = q.getResultList();
        LinkedList<Assistance> listAssistance = new LinkedList<>();
        Assistance assistance;
        AssistanceState assistanceState = em.find(AssistanceState.class, 1L);
        for (Student student : listStudent) {
            assistance = new Assistance();
            assistance.setBlockClass(blockClass);
            assistance.setStudent(student);
            assistance.setState(assistanceState);
            listAssistance.add(assistance);            
            student.getAssistance().add(assistance);
            persistInsert(assistance);
            persistUpdate(student);
        }
        return listAssistance;
    }
    
    @Override
    public AnswerDTO updateCourse(CourseDTO courseDTO, Long idCourse, Long idUniversity, Long idUser) {
        Course course = em.find(Course.class, idCourse);
        University university = em.find(University.class, idUniversity);
        course.setName(courseDTO.getName());
        course.setLevel(courseDTO.getLevel());
        course.setUniversity(university);
        if(idUser!=null){
            Teacher teacher = em.find(User.class, idUser).getTeacher();
            course.setTeacher(teacher);
        }else{
            course.setTeacher(null);
        }
        if (persistUpdate(course)) {
            return new AnswerDTO(0);
        } else {
            return new AnswerDTO(113);
        }
    }
    
    @Override
    public TimeBlockClassListDTO getAllTimeBlockClass() {
        Collection<TimeBlockClass> result;
        LinkedList<TimeBlockClassDTO> timeBlockClassDTO = new LinkedList<>();
        Query q = this.em.createNamedQuery("TimeBlockClass.gelAllTimeBlockClass");
        result = (Collection<TimeBlockClass>) q.getResultList();
        for (TimeBlockClass it : result) {
            timeBlockClassDTO.add(new TimeBlockClassDTO(it));
        }
        return new TimeBlockClassListDTO(timeBlockClassDTO, new AnswerDTO(0));
    }
    
    @Override
    public DayBlockClassListDTO getAllDayBlockClassDTO() {
        Collection<DayBlockClass> result;
        LinkedList<DayBlockClassDTO> timeBlockClassDTO = new LinkedList<>();
        Query q = this.em.createNamedQuery("DayBlockClass.gelAllDayBlockClass");
        result = (Collection<DayBlockClass>) q.getResultList();
        for (DayBlockClass it : result) {
            timeBlockClassDTO.add(new DayBlockClassDTO(it));
        }
        return new DayBlockClassListDTO(timeBlockClassDTO, new AnswerDTO(0));
    }
    
    @Override
    public BlockClassListDTO getAllBlockClassOfCourse(Long idCourse) {
        Query q = this.em.createNamedQuery("BlockClass.getBlockClassOfCourse");
        q.setParameter("idCourse", idCourse);
        List<BlockClass> result = (List<BlockClass>) q.getResultList();
        result = sortBlockClassByDate(result);
        LinkedList<BlockClassDTO> listBlockClassDTO = new LinkedList<>();
        BlockClassDTO blockClass;
        for (BlockClass it : result) {
            blockClass = new BlockClassDTO();
            blockClass.setDate(it.getDate());
            blockClass.setDayBlockClass(it.getDayBlockClass().getId());
            blockClass.setTimeBlockClass(it.getTimeBlockClass().getId());
            listBlockClassDTO.add(blockClass);
        }
        return new BlockClassListDTO(listBlockClassDTO, new AnswerDTO(0));
    }
    
    private List<BlockClass> sortBlockClassByDate(List<BlockClass> listClasses){
        BlockClass aux;
        for (int i = 0; i < listClasses.size(); i++) {
            for (int j = 0; j < listClasses.size()-1; j++) {
                if(listClasses.get(j+1).getDate().getTime()<listClasses.get(j).getDate().getTime()){
                    aux = listClasses.get(j+1);
                    listClasses.set(j+1, listClasses.get(j));
                    listClasses.set(j, aux);                    
                }
            }
        }
        return listClasses;
    }
    
    @Override
    public AnswerDTO configureAssistanceTimebox(CourseDTO course) {
        Course courseToUpdate = em.find(Course.class, course.getId());
        courseToUpdate.setMinutesBeforeClassStart(course.getMinutesBeforeClassStart());
        courseToUpdate.setMinutesAfterClassStart(course.getMinutesAfterClassStart());
        if (persistUpdate(courseToUpdate)) {
            return new AnswerDTO(0);
        } else {
            return new AnswerDTO(0);
        }
    }
    
    @Override
    public ListUserDTO getAllStudentsFromCourse(Long idCourse) {
        Collection<Student> resultQuery;
        Collection<UserDTO> result = new ArrayList<>();
        
        Query q = this.em.createNamedQuery("Student.getAllStudentFromCourse");
        q.setParameter("idCourse", idCourse);
        try {
            resultQuery = (Collection<Student>) q.getResultList();
            for (Student it : resultQuery) {
                if (it.getUser().isUserStatus()) {
                    result.add(new UserDTO(it.getUser()));
                }
            }
            ListUserDTO exitResult = new ListUserDTO(result, new AnswerDTO());
            return sqlResultToListUserDTO(result, exitResult);
        } catch (NoResultException nre) {
            System.out.println(nre);
            return null;
        }
    }
    
    private ListUserDTO sqlResultToListUserDTO(Collection<UserDTO> result, ListUserDTO exitResult) {
        UserDTO userDTOTemp;
        Collection<UserDTO> listUserTemp = new ArrayList<>();
        for (UserDTO iter : result) {
            userDTOTemp = iter;
            listUserTemp.add(userDTOTemp);
        }
        exitResult.setListUser(listUserTemp);
        exitResult.setAnswerDTO(new AnswerDTO(0));
        return exitResult;
    }
    
    //español: en esta funcion creamos la tabla de asistencia
    @Override
    public AssistanceListCourseDTO assistanceListCourse(Long idCourse) {
        AssistanceListCourseDTO assistanceListCourse = new AssistanceListCourseDTO();        
        assistanceListCourse.setListRow(new LinkedList<AssistanceListCourseRowDTO>());                
        ListUserDTO listStudent = getAllStudentsFromCourse(idCourse);
        List<BlockClass> listBlockClass = em.find(Course.class, idCourse).getListBlockClass();   
        listBlockClass = sortBlockClassByDate(listBlockClass);
        assistanceListCourse.setListTitle(setAssistanceTitle(listBlockClass));        
        assistanceListCourse.setListRow(setAssistanceRow(listStudent, listBlockClass, idCourse));                
        return assistanceListCourse;
    }
    
    //se crean los titulos, en caso que quieras pasar campos a los titulos de las columnas debes editar la clase AssistanceListCourseTitleDTO y agregar campos
    private List<AssistanceListCourseTitleDTO> setAssistanceTitle(List<BlockClass> listBlockClass) {
        List<AssistanceListCourseTitleDTO> assistanceTitleList = new ArrayList<>();        
        AssistanceListCourseTitleDTO assistanceTitle = new AssistanceListCourseTitleDTO();
        
        assistanceTitle.setTitle("RUT");
        assistanceTitle.setSubTitle("");
        assistanceTitle.setId(0);        
        assistanceTitleList.add(assistanceTitle);        
        
        assistanceTitle = new AssistanceListCourseTitleDTO();
        assistanceTitle.setTitle("Nombre");
        assistanceTitle.setSubTitle("");
        assistanceTitle.setId(1);        
        assistanceTitleList.add(assistanceTitle);                        
        for (BlockClass it2 : listBlockClass) {
            assistanceTitle = new AssistanceListCourseTitleDTO();
            assistanceTitle.setTitle(dateFormat(it2.getDate()));            
            assistanceTitle.setSubTitle(it2.getDayBlockClass().getAbbreviation()+" "+it2.getTimeBlockClass().getStartHour());
            assistanceTitle.setId(assistanceTitleList.size());
            assistanceTitleList.add(assistanceTitle);
        }
        return assistanceTitleList;
    }
    
    //se crean las filas donde el primer campo es el rut y el segundo el nombre, el resto son la asistencia, si quieres colocar mas datos en las casillas edita AssistanceListCourseUnitDTO
    private LinkedList<AssistanceListCourseRowDTO> setAssistanceRow(ListUserDTO listStudent, List<BlockClass> listBlockClass, Long idCourse){
        LinkedList<AssistanceListCourseRowDTO> assistanceRowList = new LinkedList<>();
        AssistanceListCourseRowDTO assistanceListCourseRow;
        AssistanceListCourseUnitDTO unit;
        for (UserDTO it : listStudent.getListUser()) {
            assistanceListCourseRow = new AssistanceListCourseRowDTO();
            //set rut
            assistanceListCourseRow.setListUnit(new ArrayList<AssistanceListCourseUnitDTO>());            
            unit = new AssistanceListCourseUnitDTO();
            unit.setText(Integer.toString(it.getRut()));
            unit.setTextRutName(true);            
            assistanceListCourseRow.getListUnit().add(unit);
            //set name
            unit = new AssistanceListCourseUnitDTO();
            unit.setText(it.getFirstName()+" "+it.getLastName());
            unit.setTextRutName(true);            
            assistanceListCourseRow.getListUnit().add(unit);
            for (BlockClass it2 : listBlockClass) {
                assistanceListCourseRow = assistanceStudentToCourse(idCourse, it.getId(), listBlockClass, assistanceListCourseRow);
            }
            assistanceRowList.add(assistanceListCourseRow);
        }
        return assistanceRowList;   
   }
    
    
    //busca los estudiantes por cursos  
    private AssistanceListCourseRowDTO assistanceStudentToCourse(Long idCourse, Long idUser, List<BlockClass> listBlockClass, AssistanceListCourseRowDTO assistanceListCourseRow) {                
        AssistanceListCourseUnitDTO unit;        
        Query q;
        q = em.createNamedQuery("Assistance.getAssistanceStudentToCourse");
        q.setParameter("idCourse", idCourse);
        q.setParameter("idUser", idUser);
        LinkedList<Assistance> listAssistance = new LinkedList<>((Collection<Assistance>) q.getResultList());        
        AssistanceState assistanceAbsent = em.find(AssistanceState.class, 1L);
        for (BlockClass it : listBlockClass) {
            Assistance assistance = findAssistanceToBlockClass(listAssistance, it);            
            unit = new AssistanceListCourseUnitDTO();
            unit.setTextAssistance(true);
            if (assistance != null) {
                unit.setText(assistance.getState().getName());
                if (assistance.getState().getId() != 1L) {
                    unit.setPresent(true);
                }
                unit.setIdBlockClass(it.getId());
            } else {  
                unit.setText("Ausente");
                unit.setPresent(false);
                unit.setIdBlockClass(it.getId());
            }            
            assistanceListCourseRow.getListUnit().add(unit);
        }        
        return assistanceListCourseRow;
    }
    
    
    public static String dateFormat(Date date) {
        if (null == date) {
            return "";
        } else {
            String formato = "dd/MM/yyyy";
            SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
            return dateFormat.format(date);

        }
    }
    
    private Assistance findAssistanceToBlockClass(LinkedList<Assistance> listAssistance, BlockClass blockClass) {
        for (Assistance it : listAssistance) {
            if (blockClass.equals(it.getBlockClass())) {
                return it;
            }
        }
        return null;
    }
    
    @Override
    public AnswerDTO createGroup(Long idCourse, String groupName){
        Query q = em.createNamedQuery("GroupStudentPerCourse.countGroupOfCourseByGroupName");
        q.setParameter("groupName", groupName);
        q.setParameter("idCourse", idCourse);
        Long count = (Long) q.getSingleResult();
        if(count>0){
            return new AnswerDTO(133);
        }
        GroupStudentPerCourse group = new GroupStudentPerCourse();
        Course course = em.find(Course.class, idCourse);
        group.setCourse(course);
        group.setName(groupName);
        course.getListGroup().add(group);
        group.setGroupStatus(true);
        persistInsert(group);
        persistUpdate(course);
        return new AnswerDTO(0);
    }
    
    @Override
    public ListGroupStudentPerCourseDTO getAllGroupsOfCourse(Long idCourse){
        Query q = em.createNamedQuery("GroupStudentPerCourse.getAllGroupsOfCourseByIdCourse");
        q.setParameter("idCourse", idCourse);
        Collection<GroupStudentPerCourse> resultQuery = (Collection<GroupStudentPerCourse>) q.getResultList();
        return new ListGroupStudentPerCourseDTO(resultQuery, new AnswerDTO(0));
    }
    
    @Override
    public AnswerDTO deleteGroup(String groupName, Long idCourse){
        if(groupName == null){
            return new AnswerDTO(135);
        }
        Query q = em.createNamedQuery("GroupStudentPerCourse.getGroupOfCourseByGroupName");
        q.setParameter("groupName", groupName);
        q.setParameter("idCourse", idCourse);
        GroupStudentPerCourse group = (GroupStudentPerCourse) q.getSingleResult();
        group.setGroupStatus(false);
        persistUpdate(group);
        return new AnswerDTO(0);
    }
    
    @Override
    public ListUserDTO getAllStudentsWithGroup(Long idCourse){
        Query q = em.createNamedQuery("GroupStudentPerCourse.getAllStudentsWithGroup");
        q.setParameter("idCourse", idCourse);
        Collection<UserDTO> resultList = (Collection<UserDTO>) q.getResultList();
        return new ListUserDTO(resultList, new AnswerDTO(0));
    }
    
    @Override
    public ListUserDTO getAllStudentsWithoutGroup(Long idCourse){
        ListUserDTO resultList = new ListUserDTO();
        boolean flag = false;
        resultList.setListUser(new ArrayList());
        Course course = em.find(Course.class, idCourse);
        List<Student> listStudent = course.getListStudent();
        for (Student student : listStudent) {
            flag = false;
            for (GroupStudentPerCourse group : student.getListGroup()) {
                if(group.getCourse().getId()==idCourse && group.isGroupStatus()){                    
                    flag = true;
                }
            }
            if(!flag){
                resultList.getListUser().add(new UserDTO(student.getUser()));
            }
        }
        return resultList;
    }
    
    @Override
    public List<UserDTO> getStudentsOfGroup(String groupName, Long idCourse){
        Query q = em.createNamedQuery("GroupStudentPerCourse.getAllStudentsOfGroup");
        q.setParameter("groupName", groupName);
        q.setParameter("idCourse", idCourse);
        List<UserDTO> returnList = new ArrayList();
        Collection<Student> resultList = (Collection<Student>) q.getResultList();
        for (Student student : resultList) {
            returnList.add(new UserDTO(student.getUser()));
        }
        return returnList;
    }
    
    @Override
    public AnswerDTO updateGroup(List<Long> ListIdUser, String groupName, Long idCourse){
        if(groupName == null){
            return new AnswerDTO(136);
        }
        Query q = em.createNamedQuery("GroupStudentPerCourse.getGroupOfCourseByGroupName");
        q.setParameter("groupName", groupName);
        q.setParameter("idCourse", idCourse);
        GroupStudentPerCourse group = (GroupStudentPerCourse) q.getSingleResult();
        cleanGroupListUser(group);        
        group = (GroupStudentPerCourse) q.getSingleResult();
        
        for (Long idUser : ListIdUser) {
            System.out.println("idUser = "+idUser);
            q = em.createNamedQuery("Student.findByIdUser");
            q.setParameter("idUser", idUser);
            Student student = (Student) q.getSingleResult();
            student.getListGroup().add(group);            
            group.getListStudent().add(student);
            persistUpdate(student);            
        }
        persistUpdate(group); 
        return new AnswerDTO(0);
    }
    
    private void cleanGroupListUser(GroupStudentPerCourse group){
        for (Student student : group.getListStudent()) {
            student.getListGroup().remove(group);
            persistUpdate(student);
        }
        group.getListStudent().clear();
        persistUpdate(group);
    }
    
    @Override
    @SuppressWarnings("empty-statement")
    public ListCourseDTO getAllCourseOfStudentInUniversity(Long idUser) {
        User user = em.find(User.class, idUser);
        Collection<Course> result;
        ListCourseDTO exitResult = new ListCourseDTO();
        Query q = this.em.createNamedQuery("getAllCourseOfStudentInUniversity");
        q.setParameter("idUniversity", user.getStudent().getUniversity().getId());
        try {
            result = (Collection<Course>) q.getResultList();
            return sqlResultToCourseList(result, exitResult);
        } catch (NoResultException nre) {
            System.out.println(nre);
            return null;
        }
    }
}
