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
public class Composicao {
    String idproduto;
    String idcomposicao;
    String composicao;

    public Composicao() {
    }

    public String getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(String idproduto) {
        this.idproduto = idproduto;
    }

    public String getIdcomposicao() {
        return idcomposicao;
    }

    public void setIdcomposicao(String idcomposicao) {
        this.idcomposicao = idcomposicao;
    }

    public String getComposicao() {
        return composicao;
    }

    public void setComposicao(String composicao) {
        this.composicao = composicao;
    }
    
    public void Cadastrar() throws SQLException{
        Connection conn = ConexaoBD.Conectar();    
        //Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/iamsoftware", "root", null);
        
        PreparedStatement stmt = null;
        
        stmt = conn.prepareStatement("INSERT INTO composicao (id_produto, id_composicao, nome)VALUES(?,?,?)");
        stmt.setString(1,getIdproduto());
        stmt.setString(2,getIdcomposicao());        
        stmt.setString(3,getComposicao());       
        
        
        stmt.executeUpdate();
        stmt.close();
        //ConexaoBD.Fechar(conn);       
    }
    
    public void Remover(int id) throws SQLException{
        Connection conn = ConexaoBD.Conectar();    
        PreparedStatement stmt = null;
        stmt = conn.prepareStatement("DELETE FROM composicao WHERE id_produto="+id);
        stmt.executeUpdate();
        stmt.close();
    
    }
}
