/*
Author : Darian Lopez
Class : CMSC 495
Project: Capstone
Date : 27 Sep 18
Purpose : this class provides connection to the database and queries
the database
 */


/*
TODO: verify SQL statements
      CREATE DB With Tables and test data.
*/



//package capstone;

//IMPORTS
import java.sql.*;
//END IMPORTS

//DATABASE CONNECT CLASS
public class dbConnect extends BankingGui{


    //BEGIN VARIABLES
    //database info
    private String URL = "jdbc:oracle::1521:";
    private String USER = "username";
    private String PASS = "password";
    private boolean isConnected = false;
    private static Connection con;

    //transaction holder variables
    private String tempUid = "";
    private String tempAccType = "";
    private double tempAmount = 0.0;
    private double tempBalance= 0.0;


    //ERROR Strings
    private static final String errorUID = "UID NOT FOUND";


    //END VARIABLES


    //BEGIND METHODS


     //dynamically load the driver's class file into memory

    protected void InitDB() throws SQLException{
    try{
         Class.forName("org.apache.derby.jdbc.ClientDriver"); //TODO : MATCH DRIVER WITH NEEDED DB TYPE
      } catch(ClassNotFoundException e) {
         System.out.println("Class not found "+ e);
      }

    //CONNECT TO DB WITH CREDENTIALS
    //TODO : insert correct ddb name and path with parameters
    con = DriverManager.getConnection(URL, USER, PASS);
    }

    //check connection method
    protected boolean isClose() throws SQLException{
        return con.isClosed();
    }

    //DISCONNECT FROM DB
    protected void disconnectDB() throws SQLException{
        con.close();
     }


     //WITHDRAW METHOD
    protected void withdraw(String uid,double amount, String accType) throws SQLException{

        tempUid = uid;
        tempAmount = amount;
        tempAccType = accType;
        //deposit using prepared statement
        PreparedStatement stmt = con.prepareStatement("select ? FROM Accounts WEHRE UID =?");//TODO : MATCH SQL WITH ACTUAL DB
        stmt.setString(1, accType);
        stmt.setString(2, uid);
        ResultSet resultSet = stmt.executeQuery();
        if(resultSet.next()){
        tempBalance = resultSet.getFloat(tempAccType.toString());


                if(checkBalance(uid, accType)>=tempBalance){
                    tempBalance -= amount;

                    PreparedStatement stmtUpdate = con.prepareStatement("UPDATE Accounts SET ? = ? WHERE UID =?");//TODO : MATCH SQL WITH ACTUAL DB
                    stmtUpdate.setString(1, accType);
                    stmtUpdate.setDouble(2, tempBalance);
                    stmtUpdate.setString(3,tempUid);
                    stmt.closeOnCompletion();
                    stmtUpdate.closeOnCompletion();
                }

        }
        else{

        }

        //SKELETON SQL STATEMENT
       /* String tempUid = uid;
        float tempAmount = amount;
        String tempAccType  = accType;
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery ("");
        stmt.closeOnCompletion();*/
    }

    protected void deposit(String uid, double amount, String accType) throws SQLException{
        tempUid = uid;
        tempAmount = amount;
        tempAccType = accType;

        //deposit using prepared statement
        PreparedStatement stmt = con.prepareStatement("select ? FROM Accounts WEHRE UID =?"); //TODO : MATCH SQL WITH ACTUAL DB
        //INSERTS DATA
        stmt.setString(1, accType);

        stmt.setString(2, uid);
        ResultSet resultSet = stmt.executeQuery();
        if(resultSet.next()){
        tempBalance = resultSet.getFloat(tempAccType.toString());
        tempBalance += amount;

        PreparedStatement stmtUpdate = con.prepareStatement("UPDATE Accounts SET ? = ? WHERE UID =?"); //TODO : MATCH SQL WITH ACTUAL DB
        stmtUpdate.setString(1, accType);
        stmtUpdate.setDouble(2, tempBalance);
        stmtUpdate.setString(3,tempUid);

        stmt.closeOnCompletion();
        stmtUpdate.closeOnCompletion();

        this.clearAll();
        }
        else{

        }

        //SKELETON SQL STATEMENT
        /*INSERT using regular statement
        Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery ("");
         stmt.closeOnCompletion();
         */
    }

    protected String getUid(String uid) throws SQLException{
         //TODO UID retrieval logic
        tempUid = uid;
        PreparedStatement stmt = con.prepareStatement("select count (uid) FROM Accounts WEHRE UID =?"); //TODO : MATCH SQL WITH ACTUAL DB
        stmt.setString(1, tempUid);
        ResultSet resultSet = stmt.executeQuery();

        if(resultSet.next()){
        return tempUid;
        }
        else{
        return errorUID;
        }
    }


    //CHECKS BALANCE OF ONE ACCOUNT AT A TIME
    protected double checkBalance(String uid, String accType) throws SQLException{


        //SKELETON SQL STATEMENT
       /* Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery ("");
         stmt.closeOnCompletion();
         return balance;
         */

        tempUid = uid;
        tempAccType = accType;
        //deposit using prepared statement
        PreparedStatement stmt = con.prepareStatement("select ? FROM Accounts WEHRE UID =?"); //TODO : MATCH SQL WITH ACTUAL DB
        stmt.setString(1, accType);
        stmt.setString(2, uid);
        ResultSet resultSet = stmt.executeQuery();
        if(resultSet.next()){
        tempBalance = resultSet.getFloat(tempAccType.toString());
        }
        else{

        }
    return tempBalance;
    }


    //NULLIFY/CLEAR FIELDS
    private void clearUid(){
        this.tempUid ="";
    }
    private void clearAccType(){
        this.tempAccType="";
    }
    private void clearAmount(){
        this.tempAmount=0.00;
    }
    private void clearBalance(){
        this.tempBalance=0.00;
    }

    private void clearAll(){
        this.clearUid();
        this.clearAccType();
        this.clearAmount();
        this.clearBalance();
    }

    //END METHODS
}
//END CLASS
