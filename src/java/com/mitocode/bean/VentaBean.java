package com.mitocode.bean;

import com.mitocode.dao.VentaDAO;
import com.mitocode.model.DetalleVenta;
import com.mitocode.model.Producto;
import com.mitocode.model.Venta;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author HBK
 */
@ManagedBean
@ViewScoped
public class VentaBean {
    private Venta venta = new Venta();
    private Producto producto = new Producto();
    private int cantidad;
    private List<DetalleVenta> lista = new ArrayList();

    public List<DetalleVenta> getLista() {
        return lista;
    }

    public void setLista(List<DetalleVenta> lista) {
        this.lista = lista;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProdcuto() {
        return producto;
    }

    public void setProdcuto(Producto prodcuto) {
        this.producto = prodcuto;
    }
    
    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
    
    public void agregar(){
        DetalleVenta det = new DetalleVenta();
        det.setCantidad(cantidad);
        det.setProducto(producto);
        this.lista.add(det);
    }
    
    public void registrar() throws Exception{
        VentaDAO dao;
        double monto = 0;
        try{
            for(DetalleVenta det : lista){
                monto += det.getProducto().getPrecio()*det.getCantidad();
            }
            dao = new VentaDAO();
            venta.setMonto(monto);            
            dao.registrar(venta, lista);
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "¡Venta realizada con éxito!"));
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "¡¡¡Error!!!"));
        }finally{
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }
    }
}
