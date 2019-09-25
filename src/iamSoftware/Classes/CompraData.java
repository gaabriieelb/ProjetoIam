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
public class CompraData {
    
    double total;
    int idCompra;
    String produto;
    double quantidade;
    String formaPagamento;
    String dataPagamento;
    double valor;
    int idCliente;
    String cliente;
    String status;

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
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

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
        
    public void cadastrarCompra() throws SQLException{
    
     Connection conn = ConexaoBD.Conectar();    
        
        PreparedStatement stmt = null;
        
        stmt = conn.prepareStatement("INSERT INTO compras (total)VALUES(?)");
        
        stmt.setDouble(1,getTotal());     
               
        stmt.executeUpdate();
        stmt.close();
    }
    
    public void cadastrarItensCompra() throws SQLException{
    
     Connection conn = ConexaoBD.Conectar();    
        
        PreparedStatement stmt = null;
        
        stmt = conn.prepareStatement("INSERT INTO itenscompras (idcompra,produto,quantidade)VALUES(?,?,?)");
        
        stmt.setInt(1,getIdCompra());
        stmt.setString(2,getProduto());
        stmt.setDouble(3,getQuantidade());
               
        stmt.executeUpdate();
        stmt.close();
    }
    
    public void cadastrarContasReceber() throws SQLException{
    
     Connection conn = ConexaoBD.Conectar();    
        
        PreparedStatement stmt = null;
        
        stmt = conn.prepareStatement("INSERT INTO contasreceber (idcompra,formapagamento,idcliente,cliente,datapagamento,valor,status)VALUES(?,?,?,?,?,?,?)");
        
        stmt.setInt(1,getIdCompra());
        stmt.setString(2,getFormaPagamento());
        stmt.setInt(3,getIdCliente());
        stmt.setString(4, getCliente());
        stmt.setString(5, getDataPagamento());
        stmt.setDouble(6, getValor());
        stmt.setString(7, getStatus());
               
        stmt.executeUpdate();
        stmt.close();
    }
    
    
    
    
}
