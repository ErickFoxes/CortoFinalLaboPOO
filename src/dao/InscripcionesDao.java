/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.Conexion;
import interfaces.metodos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Inscripciones;

/**
 *
 * @author Erick
 */
public class InscripcionesDao implements metodos<Inscripciones>{
    private static final String SQL_INSERT ="INSERT INTO alumnos (carnet,nombres,apellidos,edad,universidad,estado) VALUES (?,?,?,?,?,?)";
    private static final String SQL_UPDATE ="UPDATE alumnos SET nombres =?, apellidos=?,edad = ?,universidad=?, estado =? WHERE carnet=?";
    private static final String SQL_DELETE = "DELETE FROM alumnos WHERE carnet=?";
    private static final String SQL_READ = "SELECT * FROM alumnos WHERE carnet=?";
    private static final String SQL_READALL ="SELECT carnet,nombres,apellidos,universidad,estado FROM alumnos";

    private static final Conexion con=Conexion.conectar();
    @Override
    public boolean create(Inscripciones g) {
        PreparedStatement ps;
        try{
            ps= con.getCnx().prepareStatement(SQL_INSERT);
            ps.setString(1, g.getCarnet());
            ps.setString(2,g.getNombres());
            ps.setString(3,g.getApellidos());
            ps.setInt(4,g.getEdad());
            ps.setString(5,g.getUniversidad());
            ps.setInt(6,g.getEstado());
            if(ps.executeUpdate()>0){
                return true;
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(InscripcionesDao.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean delete(Object key) {
       PreparedStatement ps;
        try{
            ps= con.getCnx().prepareStatement(SQL_DELETE);
            ps.setString(1, key.toString());
            if(ps.executeUpdate()>0){
                return true;
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(InscripcionesDao.class.getName()).log(Level.SEVERE,null,ex);
        
        }finally{
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean update(Inscripciones c) {
        PreparedStatement ps;
        try{
            ps= con.getCnx().prepareStatement(SQL_UPDATE);
           
            ps.setString(1,c.getNombres());
            ps.setString(2,c.getApellidos());
            ps.setInt(3,c.getEdad());
            ps.setString(4,c.getUniversidad());
            ps.setInt(5, c.getEstado());
            ps.setString(6, c.getCarnet());
            if(ps.executeUpdate()>0){
                return true;
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(InscripcionesDao.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public Inscripciones read(Object key) {
        Inscripciones f = null;
        PreparedStatement ps;
        ResultSet rs;
        try{
            ps= con.getCnx().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            rs = ps.executeQuery();
            while(rs.next()){
                f = new Inscripciones(rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6),rs.getInt(7));
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(InscripcionesDao.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            con.cerrarConexion();
        }
        return f;
    }

    @Override
    public ArrayList<Inscripciones> readAll() {
        ArrayList<Inscripciones> all = new ArrayList();
        Statement s;
        ResultSet rs;
        try{
            s = con.getCnx().prepareStatement(SQL_READALL);
            rs = s.executeQuery(SQL_READALL);
            while(rs.next()){
                all.add(new Inscripciones(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5)));
            }
            rs.close();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(Inscripciones.class.getName()).log(Level.SEVERE,null,ex);
        }
        return all;
    }
    
}
