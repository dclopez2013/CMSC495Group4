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
    
    //database info
    String URL = "jdbc:oracle::1521:";
    String USER = "username";
    String PASS = "password";
    private boolean isConnected = false;
    private static Connection con;
            
    //transaction holder variables
    String tempUid = null;
    String tempAccType = null;
    float tempAmmount = null; 
    float tempBalance= null;

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
    
    private void withdraw(String uid,float ammount, String accType) throws SQLException{
       
        tempUid = uid;
        tempAmmount = ammount;
        tempAccType = accType;
        //deposit using prepared statement
        PreparedStatment stmt = con.prepareStatment("select ? FROM Accounts WEHRE UID =?");
        stmt.setString(1, accType);
        stmt.setString(2, uid)
        ResultSet resultSet = stmt.executeQuery();
        if(resultSet.next()){
        tempBalance = resultSet.getFloat(tempAccType.toString());


                if(checkBalance()>=tempBalance){
                    tempBalance -= ammount;

                    PreparedStatment stmtUpdate = con.prepareStatment("UPDATE Accounts SET ? = ? WHERE UID =?");
                    stmtUpdate.setString(1, accType);
                    stmtUpdate.setFloat(2, tempBalance);
                    stmtUpdate.setString(3,tempUid);
                    stmt.closeOnCompletion();
                    stmtUpdate.closeOnCompletion();
                }

        }
        else{
           
        }
       /* String tempUid = uid;
        float tempAmmount = ammount;
        String tempAccType  = accType;
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery ("");
        stmt.closeOnCompletion();*/
    }
    
    private void desposit(String uid,float ammount, String accType) throws SQLException{   
        tempUid = uid;
        tempAmmount = ammount;
        tempAccType = accType;
        //deposit using prepared statement
        PreparedStatment stmt = con.prepareStatment("select ? FROM Accounts WEHRE UID =?");
        stmt.setString(1, accType);
        stmt.setString(2, uid)
        ResultSet resultSet = stmt.executeQuery();
        if(resultSet.next()){
        tempBalance = resultSet.getFloat(tempAccType.toString());
        tempBalance += ammount;

        PreparedStatment stmtUpdate = con.prepareStatment("UPDATE Accounts SET ? = ? WHERE UID =?");
        stmtUpdate.setString(1, accType);
        stmtUpdate.setFloat(2, tempBalance);
        stmtUpdate.setString(3,tempUid);
        /*
        "UPDATE Registration " +
        "SET age = 30 WHERE id in (100, 101)";
        */
            stmt.closeOnCompletion();
            stmtUpdate.closeOnCompletion();

        }
        else{
           
        }

        /*INSERT using regular statement
        Statement stmt = con.createStatement();  
         ResultSet rs = stmt.executeQuery ("");
         stmt.closeOnCompletion();   
         */
    }
    
    private String getUid(String uid) throws SQLException{
         //TODO UID retrieval logic
        tempUid = uid;
        PreparedStatment stmt = con.prepareStatment("select count (uid) FROM Accounts WEHRE UID =?");
        stmt.setString(1, tempUid);
        ResultSet resultSet = stmt.executeQuery(); 
       
        if(resultSet.next()){
        return tempUid;
        }
        else{
        return "Uid Not Found";
        }
    }
    
    private float checkBalance(String uid, String accType) throws SQLException{
       /* Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery ("");
         stmt.closeOnCompletion();
         return balance;
         */

        tempUid = uid;
        tempAccType = accType;
        //deposit using prepared statement
        PreparedStatment stmt = con.prepareStatment("select ? FROM Accounts WEHRE UID =?");
        stmt.setString(1, accType);
        stmt.setString(2, uid)
        ResultSet resultSet = stmt.executeQuery();
        if(resultSet.next()){
        tempBalance = resultSet.getFloat(tempAccType.toString());
        }
        else{
           
        }
    return tempBalance;
    }
    
    private void disconnectDB() throws SQLException{
       con.close();
    }
      
}
