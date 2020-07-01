package controller;

import dao.ImplTAREA;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import model.TAREA;

@Named(value = "tareaC")
@SessionScoped
@ManagedBean
public class TareaC implements Serializable {

    TAREA tarea = new TAREA();
    private TAREA selectedTarea;
    private List<TAREA> lstTarea;
    private List<String> selectedOptions;

    @PostConstruct
    public void iniciar() {
        try {
            listarTarea();
        } catch (Exception e) {
        }
    }

    public void limpiarTarea() throws Exception {
        tarea = new TAREA();
    }

    public void registrarTarea() {
        ImplTAREA dao;
        try {
            dao = new ImplTAREA();
            dao.registrar(tarea);
            listarTarea();
            limpiarTarea();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado Correctamente", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Registrar" + e, null));
            System.out.println("errrrorr" + e);
        }
    }

    public void modificarTarea() throws Exception {
        ImplTAREA dao;
        try {
            dao = new ImplTAREA();
            dao.modificar(selectedTarea);
            listarTarea();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado Correctamente", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Modificar" + e, null));
        }

    }

    public void eliminarTarea() throws Exception {
        ImplTAREA dao;
        try {
            dao = new ImplTAREA();
            dao.eliminar(selectedTarea);
            listarTarea();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado Correctamente", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Eliminar" + e, null));
        }
    }

    public void listarTarea() throws Exception {
        ImplTAREA dao;
        try {
            dao = new ImplTAREA();
            lstTarea = dao.listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void validar(FacesContext context, 
            UIComponent toValidate,  
            Object value) {  
        context = FacesContext.getCurrentInstance();
        String texto = (String) value;
        if (!texto.equalsIgnoreCase("1") && !texto.equalsIgnoreCase("2")) {
            ((UIInput) toValidate).setValid(false);
            context.addMessage(toValidate.getClientId(context), new FacesMessage("Es obligatorio optar por los terminos"));
        }
    }

    public List<String> getSelectedOptions() {
        return selectedOptions;
    }

    public void setSelectedOptions(List<String> selectedOptions) {
        this.selectedOptions = selectedOptions;
    }

    public TAREA getTarea() {
        return tarea;
    }

    public void setTarea(TAREA tarea) {
        this.tarea = tarea;
    }

    public TAREA getSelectedTarea() {
        return selectedTarea;
    }

    public void setSelectedTarea(TAREA selectedTarea) {
        this.selectedTarea = selectedTarea;
    }

    public List<TAREA> getLstTarea() {
        return lstTarea;
    }

    public void setLstTarea(List<TAREA> lstTarea) {
        this.lstTarea = lstTarea;
    }

}
