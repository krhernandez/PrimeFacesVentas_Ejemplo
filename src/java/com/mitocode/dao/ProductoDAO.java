package com.mitocode.dao;

import com.mitocode.model.Producto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HBK
 */
public class ProductoDAO extends DAO {

    public void registrar(Producto pro) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("INSERT INTO producto (nombre,precio) values (?,?)");
            st.setString(1, pro.getNombre());
            st.setDouble(2, pro.getPrecio());
            st.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public List<Producto> listar() throws Exception {
        List<Producto> lista;
        ResultSet rs;
        try {
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("SELECT * FROM producto");
            rs = st.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                Producto pro = new Producto();
                pro.setCodigo(rs.getInt("codigo"));
                pro.setNombre(rs.getString("nombre"));
                pro.setPrecio(rs.getDouble("precio"));                

                lista.add(pro);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return lista;
    }

    public Producto leerID(Producto pro) throws Exception {
        Producto pros = null;
        ResultSet rs;
        try {
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("SELECT * FROM producto WHERE codigo = ?");
            st.setInt(1, pro.getCodigo());
            rs = st.executeQuery();
            while (rs.next()) {
                pros = new Producto();
                pros.setCodigo(rs.getInt("codigo"));
                pros.setNombre(rs.getString("nombre"));
                pros.setPrecio(rs.getDouble("precio"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return pros;
    }
    
    public void modificar(Producto pro) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("UPDATE producto SET nombre = ?, precio = ? WHERE codigo = ?");
            st.setString(1, pro.getNombre());
            st.setDouble(2, pro.getPrecio());
            st.setInt(3, pro.getCodigo());
            st.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }
    
    public void eliminar(Producto pro) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("DELETE FROM producto WHERE codigo = ?");
            st.setInt(1, pro.getCodigo());
            st.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

}
