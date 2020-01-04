/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iamSoftware.Classes;

import static iamSoftware.Interfaces.ContasReceber.tblFornecedores;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ga_br
 */
public class ContasReceberData {
    
    
    public void Liquidar(int id) throws SQLException{
        //"UPDATE `contaspagar` SET `status` = 'Liquidado' WHERE `contaspagar`.`id` = 4"
        
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy"); 		 
        Date dataDoSistema = new Date();		
        String dataEmTexto = formatador.format(dataDoSistema);
        
        Connection conn = ConexaoBD.Conectar();    
        //Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/iamsoftware", "root", null);
        
        PreparedStatement stmt = null;
        
        stmt = conn.prepareStatement("UPDATE `contasreceber` SET `status` = 'Liquidado', `datapagamento` = '"+dataEmTexto+"' WHERE `contasreceber`.`id` ="+id);
               
        stmt.executeUpdate();
        stmt.close();
    }
    
    public void Remover(int id) throws SQLException{
        
                
        String sql = "SELECT * FROM `contasreceber` WHERE id ="+id;
        
        Connection conn = ConexaoBD.Conectar();           
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        int idcompra = 0;
        
        while(rs.next()){
            idcompra = rs.getInt("idcompra");
        }       
        
       
        
        
        stmt = conn.prepareStatement("DELETE FROM contasreceber WHERE idcompra="+idcompra);
        stmt.executeUpdate();
        
        stmt = conn.prepareStatement("DELETE FROM itenscompras WHERE idcompra="+idcompra);
        stmt.executeUpdate();
        
        stmt = conn.prepareStatement("DELETE FROM compras WHERE id="+idcompra);
        stmt.executeUpdate();
        
        stmt.close();
    
    }
}
