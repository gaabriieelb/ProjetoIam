/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iamSoftware.Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ga_br
 */
public class CaixaData {
    
    String data;
    Double valor; 

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
    
    public void Cadastrar() throws SQLException{
        Connection conn = ConexaoBD.Conectar();    
        //Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/iamsoftware", "root", null);
        
        PreparedStatement stmt = null;
        
        stmt = conn.prepareStatement("INSERT INTO caixa (data, valor)VALUES(?,?)");
        stmt.setString(1,getData());
        stmt.setDouble(2,getValor());        
                
        stmt.executeUpdate();
        stmt.close();
        //ConexaoBD.Fechar(conn);       
    }
    /*
    public void Remover(int id) throws SQLException{
        Connection conn = ConexaoBD.Conectar();    
        PreparedStatement stmt = null;
        stmt = conn.prepareStatement("DELETE FROM produtos WHERE id="+id);
        stmt.executeUpdate();
        stmt.close();
    
    }
    
    public void Alterar(int id) throws SQLException{
        Connection conn = ConexaoBD.Conectar();    
        //Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/iamsoftware", "root", null);
        
        PreparedStatement stmt = null;
        
        stmt = conn.prepareStatement("UPDATE produtos SET nome='"+getNome()+"',codigo='"+getCod()+"',valorVenda='"+getValorVenda()+
                                      "',medida='"+getUnidade()+"' WHERE id='"+id+"'");
               
        stmt.executeUpdate();
        stmt.close();
        //ConexaoBD.Fechar(conn);       
    }
    */
}
