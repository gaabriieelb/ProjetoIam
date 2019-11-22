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
public class ComandaDATA {
    int numeroComanda; 
    int idproduto;
    String codigo;
    String produto;
    double quantidade;
    String valorUnitario;
    String subTotal; 

    public int getNumeroComanda() {
        return numeroComanda;
    }

    public void setNumeroComanda(int numeroComanda) {
        this.numeroComanda = numeroComanda;
    }

    public int getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(int idproduto) {
        this.idproduto = idproduto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public String getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(String valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }
    
    
    public void cadastrarItensCompra() throws SQLException{
    
     Connection conn = ConexaoBD.Conectar();    
        
        PreparedStatement stmt = null;
        
        stmt = conn.prepareStatement("INSERT INTO comanda (numerocomanda, idproduto, codigo, produto, quantidade, valorunitario, subtotal)VALUES(?,?,?,?,?,?,?)");
        
        stmt.setInt(1,getNumeroComanda());
        stmt.setInt(2,getIdproduto());
        stmt.setString(3,getCodigo());
        stmt.setString(4,getProduto());
        stmt.setDouble(5,getQuantidade());
        stmt.setString(6,getValorUnitario());
        stmt.setString(7,getSubTotal());
               
        stmt.executeUpdate();
        stmt.close();
    }
}
