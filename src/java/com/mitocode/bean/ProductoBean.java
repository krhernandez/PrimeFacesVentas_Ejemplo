package com.mitocode.bean;

import com.mitocode.dao.ProductoDAO;
import com.mitocode.model.Producto;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author HBK
 */
@ManagedBean
@ViewScoped
public class ProductoBean {

    private Producto producto = new Producto();
    private List<Producto> lstProductos;
    private String accion;

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.limpiar();
        this.accion = accion;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    private void registrar() throws Exception{
        ProductoDAO dao;
        try{
            dao = new ProductoDAO();
            dao.registrar(producto);
            this.listar();
        }catch (Exception e){
            throw e;
        }
    }
    
    private void modificar() throws Exception{
        ProductoDAO dao;
        try{
            dao = new ProductoDAO();
            dao.modificar(producto);
            this.listar();
        }catch (Exception e){
            throw e;
        }
    }
    
    
    public void operar() throws Exception{
        switch(accion){
            case "Registrar":
                this.registrar();                
                this.limpiar();
                break;
            case "Modificar":
                this.modificar();
                this.limpiar();
                break;
        }
    }
    
    private void limpiar(){
        this.producto.setCodigo(0);
        this.producto.setNombre("");
        this.producto.setPrecio(0);
    }
    
    public void listar() throws Exception{
        ProductoDAO dao;
        try{
            dao = new ProductoDAO();
            lstProductos = dao.listar();
        }catch (Exception e){
            throw e;
        }
    }

    public List<Producto> getLstProductos() {
        return lstProductos;
    }

    public void setLstProductos(List<Producto> lstProductos) {
        this.lstProductos = lstProductos;
    }
    
    public void leerID(Producto per) throws Exception{
        ProductoDAO dao;
        Producto temp;
        try{
            dao = new ProductoDAO();
            temp = dao.leerID(per);
            if(temp != null){
                this.producto = temp;
                this.accion = "Modificar";
            }
        }catch (Exception e){
            throw e;
        }
    }
    
    public void eliminar(Producto per) throws Exception{
        ProductoDAO dao;
        try{
            dao = new ProductoDAO();
            dao.eliminar(per);
            this.listar();
        }catch (Exception e){
            throw e;
        }
    }
    
    
}