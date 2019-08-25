/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iamSoftware;

import iamSoftware.Interfaces.Inicial;
import iamSoftware.Interfaces.Login;
import iamSoftware.Interfaces.PDVCaixa;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ga_br
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main( String[] args ) throws SQLException {
        
        Inicial inicial = new Inicial();
        inicial.setVisible(true);
        /*
        
        //create connection for a server installed in localhost, with a user "root" with no password
        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/", "root", null)) {
            // create a Statement
            try (Statement stmt = conn.createStatement()) {
                //execute query
                try (ResultSet rs = stmt.executeQuery("SELECT 'Hello World!'")) {
                    //position result to first
                    rs.first();
                    System.out.println(rs.getString(1)); //result is "Hello World!"
                }
            }
        }

           */
    }       
}
    

