
package controller;

import dao.DEMOimpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.DEMO;

@Named(value = "demoC")
@SessionScoped
public class DemoC implements Serializable {


    DEMO demo = new DEMO();;
    private DEMO selecteDemo;
    private List<DEMO> lstDemo;
   

    @PostConstruct
    public void iniciar() {
        try {
            listarDemo();
        } catch (Exception e) {
        }
    }

    public void limpiarDemo() throws Exception {
        demo = new DEMO();
    }

    public void registrarDemo() throws Exception {
        DEMOimpl dao;
        try {
            dao = new DEMOimpl();
            dao.registrar(demo);
            listarDemo();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado Correctamente", null));
        } catch (Exception e) {
            System.out.println("erorrrr" + e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Registrar", null));
        }
    }

    public void modificarDemo() throws Exception {
        DEMOimpl dao;
        try {
            dao = new DEMOimpl();
            dao.modificar(selecteDemo);
            listarDemo();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado Correctamente", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Modificar" + e, null));
        }
    }

    public void eliminarDemo() throws Exception {
        DEMOimpl dao;
        try {
            dao = new DEMOimpl();
            dao.eliminar(selecteDemo);
            listarDemo();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado Correctamente", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al Eliminar", null));
        }
    }

    public void listarDemo() throws Exception {
        DEMOimpl dao;
        try {
            dao = new DEMOimpl();
            lstDemo = dao.listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public DEMO getDemo() {
        return demo;
    }

    public void setDemo(DEMO demo) {
        this.demo = demo;
    }

    public DEMO getSelecteDemo() {
        return selecteDemo;
    }

    public void setSelecteDemo(DEMO selecteDemo) {
        this.selecteDemo = selecteDemo;
    }

    public List<DEMO> getLstDemo() {
        return lstDemo;
    }

    public void setLstDemo(List<DEMO> lstDemo) {
        this.lstDemo = lstDemo;
    }

}