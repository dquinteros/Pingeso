package managedBeans.teacherMaintainer;

import DTOs.AnswerDTO;
import DTOs.NewUserDTO;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import managedBeans.UtilitiesMB;
import sessionBeans.teacherManagement.TeacherManagementSBLocal;

@Named(value = "addTeacherMB")
@RequestScoped
public class AddTeacherMB {
    @EJB
    private TeacherManagementSBLocal teacherManagementSB;
    private NewUserDTO newTeacher;

    public AddTeacherMB() {
    }
    
    @PostConstruct
    public void init() {
        newTeacher = new NewUserDTO();
    }
    
    public void insertNewTeacher(){
        AnswerDTO r = new AnswerDTO();
        newTeacher.setRut(parseRut(newTeacher.getRut()));
        if(validateRut(newTeacher.getRut())){
            if(newTeacher.getRut().contains("K")) newTeacher.setRut(newTeacher.getRut().replace("K",""));
            else newTeacher.setRut(newTeacher.getRut().substring(0, newTeacher.getRut().length()-1));
            r = teacherManagementSB.insertNewTeacher(newTeacher);
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

    public NewUserDTO getNewTeacher() {
        return newTeacher;
    }

    public void setNewTeacher(NewUserDTO newTeacher) {
        this.newTeacher = newTeacher;
    }
    
}
