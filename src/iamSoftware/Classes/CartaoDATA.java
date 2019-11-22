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
public class CartaoDATA {
    
    String bandeira;
    String prazo;
    String taxaComissao;

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public String getPrazo() {
        return prazo;
    }

    public void setPrazo(String prazo) {
        this.prazo = prazo;
    }

    public String getTaxaComissao() {
        return taxaComissao;
    }

    public void setTaxaComissao(String taxaComissao) {
        this.taxaComissao = taxaComissao;
    }
    
    public void Cadastrar() throws SQLException{
        Connection conn = ConexaoBD.Conectar();    
        //Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/iamsoftware", "root", null);
        
        PreparedStatement stmt = null;
        
        stmt = conn.prepareStatement("INSERT INTO cartao (bandeira, prazo, taxacomissao)VALUES(?,?,?)");
        stmt.setString(1,getBandeira());
        stmt.setString(2,getPrazo());        
        stmt.setString(3,getTaxaComissao());       
        
        stmt.executeUpdate();
        stmt.close();
        //ConexaoBD.Fechar(conn);       
    }
    
    public void Remover(int id) throws SQLException{
        Connection conn = ConexaoBD.Conectar();    
        PreparedStatement stmt = null;
        stmt = conn.prepareStatement("DELETE FROM cartao WHERE id="+id);
        stmt.executeUpdate();
        stmt.close();
    
    }
    
    
     public void Alterar(int id) throws SQLException{
        Connection conn = ConexaoBD.Conectar();    
        //Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/iamsoftware", "root", null);
        
        PreparedStatement stmt = null;
        
        stmt = conn.prepareStatement("UPDATE cartao SET bandeira='"+getBandeira()+"',prazo='"+getPrazo()+"',taxacomissao='"+getTaxaComissao()+"' WHERE id='"+id+"'");
               
        stmt.executeUpdate();
        stmt.close();
        //ConexaoBD.Fechar(conn);       
    }
    
}
