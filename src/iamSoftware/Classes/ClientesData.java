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
public class ClientesData {

    public ClientesData() {
        
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
    String prazo;
    String limiteCred;
    
    //convenio
    String empresa;
    String cnpj;
    String dataAdmissao;
    String autorizacao;

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

    public String getPrazo() {
        return prazo;
    }

    public void setPrazo(String prazo) {
        this.prazo = prazo;
    }

    public String getLimiteCred() {
        return limiteCred;
    }

    public void setLimiteCred(String limiteCred) {
        this.limiteCred = limiteCred;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(String dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public String getAutorizacao() {
        return autorizacao;
    }

    public void setAutorizacao(String autorizacao) {
        this.autorizacao = autorizacao;
    }
    
    
    public void Cadastrar() throws SQLException{
        Connection conn = ConexaoBD.Conectar();    
        //Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/iamsoftware", "root", null);
        
        PreparedStatement stmt = null;
        
        stmt = conn.prepareStatement("INSERT INTO clientes (nome,cpfcnpj,rua,bairro,numero,cidade,cep,email,telefone,celular,"
                                     + "prazo,limitecredito,empresa,cnpjempresa,datadmissao,autorizacao)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
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
        stmt.setString(11,getPrazo());
        stmt.setString(12,getLimiteCred());
        stmt.setString(13,getEmpresa());
        stmt.setString(14,getCnpj());
        stmt.setString(15,getDataAdmissao());
        stmt.setString(16,getAutorizacao());
        
        
        stmt.executeUpdate();
        stmt.close();
        //ConexaoBD.Fechar(conn);
        
    }
    
    public void Remover(int id) throws SQLException{
        Connection conn = ConexaoBD.Conectar();    
        PreparedStatement stmt = null;
        stmt = conn.prepareStatement("DELETE FROM clientes WHERE id="+id);
        stmt.executeUpdate();
        stmt.close();
    
    }
    
    public void Alterar(int id) throws SQLException{
        Connection conn = ConexaoBD.Conectar();    
        //Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/iamsoftware", "root", null);
        
        PreparedStatement stmt = null;
        
        stmt = conn.prepareStatement("UPDATE clientes SET nome='"+getNome()+"',cpfcnpj='"+getCpf_cnpj()+"',rua='"+getRua()+"',bairro='"+getBairro()+"',numero='"+getNumero()+"',cidade='"+getCidade()+"',cep='"+getCep()+"',email='"+getEmail()+"',telefone='"+getTelefone()+"',celular='"+getCelular()+"',prazo='"+getPrazo()+"',limitecredito='"+getLimiteCred()+"',empresa='"+getEmpresa()+"',cnpjempresa='"+getCnpj()+"',datadmissao='"+getDataAdmissao()+"',autorizacao='"+getAutorizacao()+"'WHERE id='"+id+"'");
               
        stmt.executeUpdate();
        stmt.close();
        //ConexaoBD.Fechar(conn);       
    }
   
}
