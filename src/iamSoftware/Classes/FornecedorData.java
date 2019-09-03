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
public class FornecedorData {

    public FornecedorData() {
        
    }
    
    //cliente
    String nome;
    String cpf_cnpj;
    String rua;
    String bairro;
    String numero;
    String cidade;
    String cep;
    String email;
    String telefone;
    String celular;
    String ramo;    

    public String getRamo() {
        return ramo;
    }

    //convenio
    public void setRamo(String ramo) {
        this.ramo = ramo;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

   
    
    
    public void Cadastrar() throws SQLException{
        Connection conn = ConexaoBD.Conectar();    
        //Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/iamsoftware", "root", null);
        
        PreparedStatement stmt = null;
        
        stmt = conn.prepareStatement("INSERT INTO fornecedores (nome,cpfcnpj,rua,bairro,numero,cidade,cep,email,"
                                    + "telefone,celular,ramoatividade)VALUES(?,?,?,?,?,?,?,?,?,?,?)");
        
        stmt.setString(1,getNome());
        stmt.setString(2,getCpf_cnpj());
        stmt.setString(3,getRua());
        stmt.setString(4,getBairro());
        stmt.setString(5,getNumero());
        stmt.setString(6,getCidade());
        stmt.setString(7,getCep());
        stmt.setString(8,getEmail());
        stmt.setString(9,getTelefone());
        stmt.setString(10,getCelular());        
        stmt.setString(11,getRamo());
        
        
        stmt.executeUpdate();
        stmt.close();
        //ConexaoBD.Fechar(conn);
        
    }
    
    public void Remover(int id) throws SQLException{
        Connection conn = ConexaoBD.Conectar();    
        PreparedStatement stmt = null;
        stmt = conn.prepareStatement("DELETE FROM fornecedores WHERE id="+id);
        stmt.executeUpdate();
        stmt.close();
    
    }
    
    public void Alterar(int id) throws SQLException{
        Connection conn = ConexaoBD.Conectar();    
        //Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/iamsoftware", "root", null);
        
        PreparedStatement stmt = null;
        
        stmt = conn.prepareStatement("UPDATE fornecedores SET nome='"+getNome()+"',cpfcnpj='"+getCpf_cnpj()+"',rua='"+getRua()+"',bairro='"+getBairro()+"',numero='"+getNumero()+"',cidade='"+getCidade()+"',cep='"+getCep()+"',email='"+getEmail()+"',telefone='"+getTelefone()+"',celular='"+getCelular()+"',ramoatividade='"+getRamo()+"'WHERE id='"+id+"'");
               
        stmt.executeUpdate();
        stmt.close();
        //ConexaoBD.Fechar(conn);       
    }
   
}
