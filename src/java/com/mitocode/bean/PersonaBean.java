package com.mitocode.bean;

import com.mitocode.dao.PersonaDAO;
import com.mitocode.model.Persona;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author HBK
 */
@ManagedBean
@RequestScoped
public class PersonaBean {

    private Persona persona = new Persona();

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    
    public void registrar() throws Exception{
        PersonaDAO dao;
        try{
            dao = new PersonaDAO();
            dao.registrar(persona);
        }catch (Exception e){
            throw e;
        }
    }
}
