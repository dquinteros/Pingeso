/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.takeAttendance;

import DTOs.AnswerDTO;
import DTOs.NewUserDTO;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import managedBeans.UtilitiesMB;
import sessionBeans.studentManagement.StudentManagementSBLocal;

/**
 *
 * @author Pingeso
 */
@Named(value = "addStudentMB")
@RequestScoped
public class AddStudentMB {
    @EJB
    private StudentManagementSBLocal studentManagementSB;
    private NewUserDTO newStudent;   
    private String rutParaValidar;
    /**
     * Creates a new instance of addStudentMB
     */
    public AddStudentMB() {
    }
    
    @PostConstruct
    public void init() {
        newStudent = new NewUserDTO();
    }
    
    public void insertNewStudent(){
        AnswerDTO r = new AnswerDTO();
        int type = 0;
        newStudent.setRut(parseRut(newStudent.getRut()));
        if(validateRut(newStudent.getRut())){
            if(newStudent.getRut().contains("K")) newStudent.setRut(newStudent.getRut().replace("K",""));
            else newStudent.setRut(newStudent.getRut().substring(0, newStudent.getRut().length()-1));
            r = studentManagementSB.insertNewStudent(newStudent, null);
        }else{
            r.setIdError(110);
            //Aqui hay que hacer que diga que el rut no es valido, en la vista
        }
        System.out.println(r.getIdError());
        UtilitiesMB.showFeedback(r);
        //FacesContext fc = FacesContext.getCurrentInstance();
        //FacesContext.getCurrentInstance().getMessages("asdf")
        //fc.addMessage(, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Hola"," Hubo un error"));
    }
    
    private String parseRut(String rut) {
        rut = rut.toUpperCase();
        rut = rut.replace(".", "");
        rut = rut.replace("-", "");
        return rut;
    }
    
    //http://www.qualityinfosolutions.com/validador-de-rut-chileno-en-java/
    
    public static boolean validateRut(String rut) {
        boolean validacion = false;
        try {
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

            char dv = rut.charAt(rut.length() - 1);

            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validacion = true;
            }
        } catch (java.lang.NumberFormatException e) {
        } catch (Exception e) {
        }
        return validacion;
    }
    
    public NewUserDTO getNewStudent() {
        return newStudent;
    }

    public void setNewStudent(NewUserDTO newStudent) {
        this.newStudent = newStudent;
    }
    
}
