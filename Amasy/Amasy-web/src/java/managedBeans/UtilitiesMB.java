package managedBeans;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import DTOs.AnswerDTO;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import sessionBeans.UserManagementSBLocal;

/**
 *
 * @author Pingeso
 */
@Named(value = "utilitiesMB")
@RequestScoped
public class UtilitiesMB {
    @EJB
    private UserManagementSBLocal userManagementSB;
    
    @Inject
    private LoginSessionMB session;
    
    private String username;
    
    public UtilitiesMB() {
    }

    /**
     *
     * @param url
     */
    public static void redirection(String url) {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            externalContext.redirect(externalContext.getRequestContextPath() + url);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String getResponseServer(AnswerDTO r) {
        switch (r.getIdError()) {
            case 0:
                return "Operación realizada con éxito.";
            case 101:
                return "Nombre de usuario, email y rut ya registrados.";
            case 102:
                return "Email y nombre de usuario ya registrados.";
            case 103:
                return "Nombre de usuario y rut ya registrados.";
            case 104:
                return "Email y rut ya registrados.";
            case 105:
                return "Email ya registrado.";
            case 106:
                return "Nombre de usuario ya registrado.";
            case 107:
                return "Rut ya registrado.";
            case 108:
                return "Usuario o contraseña no válido.";
            case 109:
                return "Error con el registro de estudiante.";
            case 110:
                return "Rut no válido.";
            case 111:
                return "Error al eliminar un estudiante.";
            case 112:
                return "El estudiante ya se encontraba eliminado.";
            case 113:
                return "No se pudo actualizar los datos del estudiante.";
            case 114:
                return "En caso de pertenecer al curso y su huella este registrada repita la operacion.";
            case 115:
                return "Se ha marcado como presente a";
            case 116:
                return "Ya se encontraba presente el alumno.";
            case 117:
                return "Clase sin alumnos inscritos.";
            case 118:
                return "Error con el registro de profesor";
            case 119:;
                return "No se pudo actualizar los datos del profesor";
            case 120:
                return "Error al eliminar un profesor.";
            case 121:
                return "El profesor ya se encontraba eliminado.";
            case 122:
                return "Error al intentar iniciar sesión. Inténtelo nuevamente.";
            case 123:
                return "Nombre de usuario o contraseña incorrecto."; 
            case 124:
                return "El usuario ha sido deshabilitado del sistema.";
            case 125:
                return "Nombre de curso ya registrado."; 
            case 126: 
                return "Error al vincular alumno con curso.";
            case 127:
                return "Alumno ya vinculado con el curso.";
            case 128:
                return "Error al crear el vinculo con los bloques de clase.";
            case 129:
                return "Error al configurar la ventana de tiempo para la toma de asistencia.";
            case 130:
                return "Bloque horario ya registrado.";
            case 131:
                return "Bloques horarios ya registrados.";
            case 132:
                return "No se pueden eliminar más administradores del sistema. Minimo: 2.";
            default:
                return "Error";
        }
    }

    private static String getTypeMessage(AnswerDTO r) {
        switch (r.getIdError()) {
            case 0:
                return "success";
            case 115:
                return "success";
            case 116:
                return "warning";
            case 117:
                return "warning";
            case 123:
                return "warning";
            case 127:
                return "warning";
            case 131:
                return "warning";
            case 132:
                return "warning";
            default:
                return "error";
             
        }
    }

    /**
     *
     * @param answer
     */
    public static void showFeedback(AnswerDTO answer) {
        String message = getResponseServer(answer);
        String typeMessage = getTypeMessage(answer);
        FacesContext context = FacesContext.getCurrentInstance();
        switch (typeMessage) {
            case "success":
                context.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO, message, ""));
                break;
            case "warning":
                context.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_WARN, message, ""));
                break;
            case "error":
                context.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_ERROR, message, ""));
                break;
            default:
                context.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", ""));
        }
    }

    /**
     *
     * @param answer
     * @param parteMensajeExtra
     */
    public static void showFeedback(AnswerDTO answer, String parteMensajeExtra) {
        String message = getResponseServer(answer);
        String typeMessage = getTypeMessage(answer);
        FacesContext context = FacesContext.getCurrentInstance();
        switch (typeMessage) {
            case "success":
                context.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO, message + " " + parteMensajeExtra, ""));
                break;
            case "warning":
                context.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_WARN, message + " " + parteMensajeExtra, ""));
                break;
            case "error":
                context.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_ERROR, message + " " + parteMensajeExtra, ""));
                break;
            default:
                context.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error" + " " + parteMensajeExtra, ""));
        }
    }
    
    public String welcomeUserMessage(){
        return userManagementSB.findUserById(session.getUser().getId()).getFirstName();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}