/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.dbutill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author adity
 */
public class DBConnection {
     private static Connection conn;
    static
    {
        try{
            Class.forName("oracle.jdbc.OracleDriver");
            
            
            conn=DriverManager.getConnection("jdbc:oracle:thin:@//Jeetendra:1521/XE","evote","evote");
            
            System.out.println("Driver load and Connection Opened Successfully !");
            
            
        }catch(SQLException ex){
            ex.printStackTrace();
            
        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
    }
    
    public static Connection getConnection(){
        return conn;
    }
    public static void closeConnection(){
        try{
            if(conn != null)
                conn.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
}
