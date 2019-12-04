/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iamSoftware.Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author ga_br
 */
public class Agenda {
    String horario;
    String local;
    String assunto;
    String data;

    public Agenda() {
    }
    
    

    public Agenda(String horario,  String data, String local, String assunto) {
        this.horario = horario;
        this.local = local;
        this.assunto = assunto;
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    
    
    public void Cadastrar() throws SQLException{
        Connection conn = ConexaoBD.Conectar();    
        //Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/iamsoftware", "root", null);
        
        PreparedStatement stmt = null;
        
        stmt = conn.prepareStatement("INSERT INTO agenda (horario,local,assunto,data)VALUES(?,?,?,?)");
        stmt.setString(1,getHorario());
        stmt.setString(2,getLocal());        
        stmt.setString(3,getAssunto());
        stmt.setString(4, getData());
                
        stmt.executeUpdate();
        stmt.close();
        //ConexaoBD.Fechar(conn);       
    }
    
    public void Remover(int id) throws SQLException{
        Connection conn = ConexaoBD.Conectar();    
        PreparedStatement stmt = null;
        stmt = conn.prepareStatement("DELETE FROM agenda WHERE id="+id);
        stmt.executeUpdate();
        stmt.close();
    
    }
    
    
}
