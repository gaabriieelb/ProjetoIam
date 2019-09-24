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
public class ContasPagarData {
    String NumeroDocumento;
    String Fornecedor;
    String Valor;
    String NumeroParcela;
    String Vencimento;
    String Status;

    public String getNumeroDocumento() {
        return NumeroDocumento;
    }

    public void setNumeroDocumento(String NumeroDocumento) {
        this.NumeroDocumento = NumeroDocumento;
    }

    public String getFornecedor() {
        return Fornecedor;
    }

    public void setFornecedor(String Fornecedor) {
        this.Fornecedor = Fornecedor;
    }

    public String getValor() {
        return Valor;
    }

    public void setValor(String Valor) {
        this.Valor = Valor;
    }

    public String getNumeroParcela() {
        return NumeroParcela;
    }

    public void setNumeroParcela(String NumeroParcela) {
        this.NumeroParcela = NumeroParcela;
    }

    public String getVencimento() {
        return Vencimento;
    }

    public void setVencimento(String Vencimento) {
        this.Vencimento = Vencimento;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
    
    
    
    public void Cadastrar() throws SQLException{
        Connection conn = ConexaoBD.Conectar();    
        //Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/iamsoftware", "root", null);
        
        PreparedStatement stmt = null;
        
        stmt = conn.prepareStatement("INSERT INTO contaspagar (numerodocumento, fornecedor, valor, numeroparcela, vencimento, status)VALUES(?,?,?,?,?,?)");
        
        stmt.setString(1,getNumeroDocumento());
        stmt.setString(2,getFornecedor());
        stmt.setString(3,getValor());
        stmt.setString(4,getNumeroParcela());
        stmt.setString(5,getVencimento());
        stmt.setString(6,getStatus());
        
        stmt.executeUpdate();
        stmt.close();
        //ConexaoBD.Fechar(conn);        
    }
    
    public void Liquidar(int id) throws SQLException{
        //"UPDATE `contaspagar` SET `status` = 'Liquidado' WHERE `contaspagar`.`id` = 4"
        
        Connection conn = ConexaoBD.Conectar();    
        //Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/iamsoftware", "root", null);
        
        PreparedStatement stmt = null;
        
        stmt = conn.prepareStatement("UPDATE `contaspagar` SET `status` = 'Liquidado' WHERE `contaspagar`.`id` ="+id);
               
        stmt.executeUpdate();
        stmt.close();
    }
    
    public void Remover(String numeronota) throws SQLException{
        Connection conn = ConexaoBD.Conectar();    
        PreparedStatement stmt = null;
        stmt = conn.prepareStatement("DELETE FROM contaspagar WHERE numerodocumento="+numeronota);
        stmt.executeUpdate();
        stmt.close();
    
    }
            
}
