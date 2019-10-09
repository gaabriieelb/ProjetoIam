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
public class ContasReceberData {
    
    
    public void Liquidar(int id) throws SQLException{
        //"UPDATE `contaspagar` SET `status` = 'Liquidado' WHERE `contaspagar`.`id` = 4"
        
        Connection conn = ConexaoBD.Conectar();    
        //Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/iamsoftware", "root", null);
        
        PreparedStatement stmt = null;
        
        stmt = conn.prepareStatement("UPDATE `contasreceber` SET `status` = 'Liquidado' WHERE `contasreceber`.`id` ="+id);
               
        stmt.executeUpdate();
        stmt.close();
    }
}
