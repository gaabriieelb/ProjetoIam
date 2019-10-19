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
public class APagar {
    String Forncedor;
    String Valor;

    public APagar(String Forncedor, String Valor) {
        this.Forncedor = Forncedor;
        this.Valor = Valor;
    }

    public String getForncedor() {
        return Forncedor;
    }

    public void setForncedor(String Forncedor) {
        this.Forncedor = Forncedor;
    }

    public String getValor() {
        return Valor;
    }

    public void setValor(String Valor) {
        this.Valor = Valor;
    }
    
    
    
}
