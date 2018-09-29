/*
Author : Darian Lopez
Class : CMSC 495
Project: Capstone
Date : 27 Sep 18
Purpose : this class provides connection to the database and queries
the database
 */
package capstone;
import java.sql.*;
import java.math.*;
/**
 *
 * @author dclop
 */
public class dbConnect {
    
    
    String URL = "jdbc:oracle::1521:";
    String USER = "username";
    String PASS = "password";
    private boolean isConnected = false;
    private static Connection con;
            
     //dynamically load the driver's class file into memory, 
     //which automatically registers it. This method is preferable 
     //because it allows you to make the driver registration configurable and portable.     
    
    public void InitDB() throws SQLException{
    try{
         Class.forName("org.apache.derby.jdbc.ClientDriver");
      } catch(ClassNotFoundException e) {
         System.out.println("Class not found "+ e);
      }
    //connect to DB
    //TODO : insert correct ddb name and path with parameters
    con = DriverManager.getConnection(URL, USER, PASS);
    }
    
    private boolean isClose() throws SQLException{
        return con.isClosed();
    }
    
    private void withdraw() throws SQLException{
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery ("");
        stmt.closeOnCompletion();     
    }
    
    private void desposit() throws SQLException{   
        Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery ("");
         stmt.closeOnCompletion();   
    }
    
    private String getUid() throws SQLException{
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery ("");
        stmt.closeOnCompletion(); 
        //TODO UID retrieval logic
        return "UID";
    }
    
    private void checkBalance() throws SQLException{
        Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery ("");
         stmt.closeOnCompletion();
    }
    
    private void disconnectDB() throws SQLException{
       con.close();
    }
      
}
