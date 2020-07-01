package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.DEMO;

public class DEMOimpl extends Conexion implements ICRUD<DEMO> {

    @Override
    public void registrar(DEMO demo) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO DEMO (DATO1,DATO2,DATO3,DATO4) VALUES(?,?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, demo.getDATO1());
            ps.setString(2, demo.getDATO2());
            ps.setString(3, demo.getDATO3());
            ps.setString(4, demo.getDATO4());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            this.cerrar();

        }
    }

    @Override
    public void modificar(DEMO demo) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE DEMO SET DEMO1=?, DEMO2=?, DEMO3=? ,DEMO4=? WHERE CODDEM LIKE ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, demo.getDATO1());
            ps.setString(2, demo.getDATO2());
            ps.setString(3, demo.getDATO3());
            ps.setString(4, demo.getDATO4());
            ps.setString(5, demo.getCODDEM());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void eliminar(DEMO demo) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE DEMO SET ESTDEM='I' WHERE CODDEM LIKE ?";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, demo.getESTDEM());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List<DEMO> listar() throws Exception {
        List<DEMO> listaDemo;
        ResultSet rs;
        try {
            this.conectar();
            String sql = "SELECT * FROM DEMO WHERE ESTDEM LIKE 'A'";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            rs = ps.executeQuery();
            listaDemo = new ArrayList();
            DEMO demo;
            while (rs.next()) {
                demo = new DEMO();
                demo.setCODDEM(rs.getString("CODDEM"));
                demo.setDATO1(rs.getString("DATO1"));
                demo.setDATO2(rs.getString("DATO2"));
                demo.setDATO3(rs.getString("DATO3"));
                demo.setDATO4(rs.getString("DATO4"));

                listaDemo.add(demo);
            }
            return listaDemo;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

}
