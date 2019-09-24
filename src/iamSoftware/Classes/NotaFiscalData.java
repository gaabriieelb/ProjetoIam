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
public class NotaFiscalData {
    
    String numNota;
    String dataEmissao;
    String dataRegistro;
    String nomeFornecedor;
    String cnpj;
    String nomeProduto;
    String quantidade;
    double valorCompra;

    public String getNumNota() {
        return numNota;
    }

    public void setNumNota(String numNota) {
        this.numNota = numNota;
    }

    public String getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(String dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(String dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(double valorCompra) {
        this.valorCompra = valorCompra;
    }
    
    public void Cadastrar() throws SQLException{
        Connection conn = ConexaoBD.Conectar();    
        //Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/iamsoftware", "root", null);
        
        PreparedStatement stmt = null;
        
        stmt = conn.prepareStatement("INSERT INTO notas (numeronota,dataemissao,dataregistro,nomefornecedor,cnpj,nomeproduto,quantidade,valorcompra)VALUES(?,?,?,?,?,?,?,?)");
        
        stmt.setString(1,getNumNota());
        stmt.setString(2,getDataEmissao());
        stmt.setString(3,getDataRegistro());
        stmt.setString(4,getNomeFornecedor());
        stmt.setString(5,getCnpj());
        stmt.setString(6,getNomeProduto());
        stmt.setString(7,getQuantidade());
        stmt.setDouble(8,getValorCompra());
               
        stmt.executeUpdate();
        stmt.close();
        //ConexaoBD.Fechar(conn);
        
    }
    
    
    public void Alterar(int id) throws SQLException{
        Connection conn = ConexaoBD.Conectar();    
        //Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/iamsoftware", "root", null);
        
        PreparedStatement stmt = null;
        String item = String.valueOf(id);
       
        stmt = conn.prepareStatement("UPDATE notas SET numeronota='"+getNumNota()+"',dataemissao ='"+getDataEmissao()+"',dataregistro='"+getDataRegistro()+"', "
                                    + "nomefornecedor='"+getNomeFornecedor()+"', cnpj = '"+getCnpj()+"', nomeproduto ='"+getNomeProduto()+"'"
                                    + ",quantidade='"+getQuantidade()+"',valorcompra='"+getValorCompra()+"'");
           
        stmt.executeUpdate();
        stmt.close();
        //ConexaoBD.Fechar(conn);
        
    }
    
    public void Remover(int id) throws SQLException{
        Connection conn = ConexaoBD.Conectar();    
        PreparedStatement stmt = null;
        stmt = conn.prepareStatement("DELETE FROM notas WHERE id="+id);
        stmt.executeUpdate();
        stmt.close();
    
    }
    
}
