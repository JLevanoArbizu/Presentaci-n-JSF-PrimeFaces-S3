package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.ManagedBean;


@Named(value = "componentesñ")
@SessionScoped
@ManagedBean
public class Componentesñ implements Serializable {

    private String dia;

    public Componentesñ() {

    }

    public String diaSeleccionado() {
        
        String seleccion = "";
        
        switch (dia) {
            case "Dia1": seleccion="Lunes"; break;        
            case "Dia2": seleccion="Martes"; break;        
            case "Dia3": seleccion="Miercoles"; break;        
            case "Dia4": seleccion="Juevas"; break;        
            case "Dia5": seleccion="Viernes"; break;        
            case "Dia6": seleccion="Sabado"; break;        
            case "Dia7": seleccion="Domingo"; break;        
         
        }
        return seleccion;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

}

