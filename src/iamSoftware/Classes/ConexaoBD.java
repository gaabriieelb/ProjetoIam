/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iamSoftware.Classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.objects.NativeDebug;

/**
 *
 * @author ga_br
 */
public class ConexaoBD {
    
    public ConexaoBD(){
    }
    
    
    public static Connection Conectar() throws SQLException{
        
       
        String servidor = "";
        String usuario = "";
        
        String path = System.getProperty("user.dir")+"/";
         int cont = 0;
         
        BufferedReader buffRead = null;
        try {
            
            buffRead = new BufferedReader(new FileReader(path+"dados.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        String linha = "";
        while (true) {
            if (linha != null) {
                cont++;
                
                
                
            } else
                break;
            try {
                linha = buffRead.readLine();
                if(cont == 1){
                   servidor = linha; 
                }
                if(cont == 2){
                   usuario = linha; 
                }
                
            } catch (IOException ex) {
                Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            buffRead.close();
        } catch (IOException ex) {
            Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        Connection conn = DriverManager.getConnection("jdbc:mariadb://"+servidor+"/iamsoftware", usuario, null);
        return conn;
    }
    
    public static void Fechar(Connection conn){
        try {
            if( conn != null){
                conn.close();
            }
        } catch (SQLException ex) {
                Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
