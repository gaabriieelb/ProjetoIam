/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iamSoftware.Classes;

import static iamSoftware.Interfaces.Produtos.tblProdutos;
import static iamSoftware.Interfaces.Produtos.tblstatic;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ga_br
 */
public class teste {
    
    public static void atualizar() throws SQLException{
        //pega todos nomes de produtos
        ArrayList<String> produtos = new ArrayList<>();
        ArrayList<Float> quantidade = new ArrayList<>();
        ArrayList<Double> vendido = new ArrayList<>();
        float soma = 0;
        double soma2 = 0;
        
        String sql = "SELECT nome FROM `produtos`";
        
        
        
        Connection conn = ConexaoBD.Conectar();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
              
        
        while (rs.next()) {            
            produtos.add(rs.getString("nome"));            
        }
        
        System.out.println(produtos);
        
        //soma todas quantidades de cada produtos
                
        for(int i = 0; i < produtos.size(); i++){
            String sql2 = "SELECT quantidade FROM `notas` WHERE nomeproduto='"+produtos.get(i)+"'";
            stmt = conn.prepareStatement(sql2);
            rs = stmt.executeQuery();
            while (rs.next()) {            
                soma+=rs.getFloat("quantidade");            
            }
            quantidade.add(i,soma);
            soma = 0;
            
            String sql3 = "SELECT quantidade FROM `itenscompras` WHERE produto='"+produtos.get(i)+"'";
            stmt = conn.prepareStatement(sql3);
            rs = stmt.executeQuery();
            while (rs.next()) {            
                soma2+=rs.getDouble("quantidade");            
            }
            vendido.add(i,soma2);
            soma2 = 0;
        }
        System.out.println(quantidade);
        System.out.println(vendido);
    }
    
}
