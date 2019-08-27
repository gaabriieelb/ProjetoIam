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
public class ProdutosData {

    String nome;
    String cod;
    double valorCompra;
    double valorVenda;
    double quantidade;
    String unidade;
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public void Cadastrar() throws SQLException{
        Connection conn = ConexaoBD.Conectar();    
        //Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/iamsoftware", "root", null);
        
        PreparedStatement stmt = null;
        
        stmt = conn.prepareStatement("INSERT INTO produtos (nome,codigo,valorCompra,valorVenda,quantidade,medida)VALUES(?,?,?,?,?,?)");
        stmt.setString(1,getNome());
        stmt.setString(2,getCod());
        stmt.setDouble(3,getValorCompra());
        stmt.setDouble(4,getValorVenda());
        stmt.setDouble(5,getQuantidade());
        stmt.setString(6,getUnidade());
        
        stmt.executeUpdate();
        stmt.close();
        //ConexaoBD.Fechar(conn);       
    }
    
    public void Remover(int id) throws SQLException{
    Connection conn = ConexaoBD.Conectar();    
    PreparedStatement stmt = null;
    stmt = conn.prepareStatement("DELETE FROM produtos WHERE id="+id);
    stmt.executeUpdate();
    stmt.close();
    }
}
