package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.TAREA;

public class ImplTAREA extends Conexion implements ICRUD<TAREA> {

    @Override
    public void registrar(TAREA tarea) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO TAREA (DATO1,DATO2,DATO3,DATO4) VALUES(?,?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
           
            ps.setString(1, tarea.getDATO1());
            ps.setString(2, tarea.getDATO2());
            ps.setString(3, tarea.getDATO3());
            ps.setString(4, tarea.getDATO4());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void modificar(TAREA tarea) throws Exception {
        try {
            
            this.conectar();
            String sql = "UPDATE TAREA SET DATO1=?, DATO2=?, DATO3=?, DATO4=?  WHERE CODTAR LIKE ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, tarea.getDATO1());
            ps.setString(2, tarea.getDATO2());
            ps.setString(3, tarea.getDATO3());
            ps.setString(4, tarea.getDATO4());
            ps.setString(5, tarea.getCODTAR());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }finally{
        this.cerrar();
        }
}

    @Override
    public void eliminar(TAREA tarea) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE TAREA SET ESTDEM='I' WHERE CODTAR LIKE ?";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, tarea.getCODTAR());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List<TAREA> listar() throws Exception {
        List<TAREA> listaTarea;
        ResultSet rs;
        try {
            this.conectar();
            String sql = "SELECT * FROM TAREA WHERE ESTDEM = 'A' ";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            rs = ps.executeQuery();
            listaTarea = new ArrayList();
            TAREA tarea;
            while (rs.next()) {
                tarea = new TAREA();
                tarea.setCODTAR(rs.getString("CODTAR"));
                tarea.setDATO1(rs.getString("DATO1"));
                tarea.setDATO2(rs.getString("DATO2"));
                tarea.setDATO3(rs.getString("DATO3"));
                tarea.setDATO4(rs.getString("DATO4"));
                listaTarea.add(tarea);
            }
            return listaTarea;
        } catch (Exception e) {
            throw e;
        }finally{
        this.cerrar();
        }
    }

}
