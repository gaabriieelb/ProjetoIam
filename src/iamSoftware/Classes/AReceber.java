/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iamSoftware.Classes;

/**
 *
 * @author ga_br
 */
public class AReceber {
    
    String formapagamento;
    String valor;

    public AReceber(String formapagamento, String valor) {
        this.formapagamento = formapagamento;
        this.valor = valor;
    }

    public String getFormapagamento() {
        return formapagamento;
    }

    public void setFormapagamento(String formapagamento) {
        this.formapagamento = formapagamento;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
    
           
    
}
