/*
Author : Darian Lopez
Class : CMSC 495
Project: Capstone
Date : 7 Oct 18
Purpose : this class provides connection to the database and queries
the database
 */


/*
TODO: verify SQL statements
      CREATE DB With Tables and test data.
fix update SQL statements
*/
//IMPORTS
import org.apache.derby.jdbc.ClientDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
//END IMPORTS

//DATABASE CONNECT CLASS
public class dbConnect {


    //BEGIN VARIABLES
    //database info
    //private String URL = "jdbc:oracle::1521:";
    private String USER = "username";
    private String PASS = "password";
    private boolean isConnected = false;
    private static Connection con;
    private ClientDataSource ds;

    //transaction holder variables
    private String tempUid = "";
    private String tempAccType = "";
    private double tempAmount = 0.0;
    private double tempBalance= 0.0;
    private Date tempDate;
    private LocalDateTime LDT = LocalDateTime.now();
    private LocalDate LD;
    private LocalDate tempLD;
    private DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy mm dd");
    private String dateHolder="";

    //ERROR Strings
    private static final String errorUID = "UID NOT FOUND";


    //END VARIABLES


    //BEGIND METHODS


     //dynamically load the driver's class file into memory
        
    protected void InitDB() throws SQLException{
    try{
         Class.forName("org.apache.derby.jdbc.ClientDriver");
          ds = new ClientDataSource();
         ds.setDatabaseName("accountDB");
        ds.setServerName("localhost");
        ds.setPortNumber(1527);
        ds.setUser("username");
        ds.setPassword("password");
        ds.setDataSourceName("jdbc:derby");
        
        //connect to DB
        //TODO : insert correct ddb name and path with parameters
       
        con = ds.getConnection();
        System.out.println("connected");
      } catch(ClassNotFoundException e) {
         System.out.println("Class not found "+ e);
		 
        
      }

    }

    
    //check connection method
    protected boolean isClose() throws SQLException{
        this.InitDB();
        return con.isClosed();
    }

    //DISCONNECT FROM DB
    protected void disconnectDB() throws SQLException{
        this.InitDB();
        con.close();
     }


     //WITHDRAW METHOD
    protected void withdraw(String uid,double amount, String accType) throws SQLException{
        this.InitDB();
        tempUid = uid;
        tempAmount = amount;
        tempAccType = accType;
        //deposit using prepared statement
        PreparedStatement stmt = con.prepareStatement("select * FROM Accounts WHERE UID =?");//TODO : MATCH SQL WITH ACTUAL DB
        //stmt.setString(1, tempAccType);
        stmt.setString(1, tempUid);
        ResultSet resultSet = stmt.executeQuery();
        if(resultSet.next()){
        tempBalance = resultSet.getFloat(tempAccType);


                if(checkBalance(uid, accType)>=tempBalance){
                    tempBalance -= amount;

                    String updateStatement = "UPDATE ACCOUNTS SET "+tempAccType+" = ?"+ "WHERE UID =?";
                    PreparedStatement stmtUpdate = con.prepareStatement(updateStatement);//TODO : MATCH SQL WITH ACTUAL DB
                    stmtUpdate.setDouble(1, tempBalance);
                    stmtUpdate.setString(2,tempUid);
                    stmtUpdate.executeUpdate();
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

    protected void deposit(String uid, double amount, String accType, LocalDateTime ldt) throws SQLException{
        this.InitDB();
        tempUid = uid;
        tempAmount = amount;
        tempAccType = accType;
        LD = ldt.toLocalDate();
        //deposit using prepared statement
        PreparedStatement stmt = con.prepareStatement("select * FROM Accounts WHERE UID =?"); //TODO : MATCH SQL WITH ACTUAL DB
        //INSERTS DATA
        //stmt.setString(1, tempAccType);

        stmt.setString(1, tempUid);
        ResultSet resultSet = stmt.executeQuery();
        if(resultSet.next()){
        //tempBalance = resultSet.getFloat(tempAccType.toString());
        
        tempBalance = resultSet.getDouble(tempAccType);
        System.out.println("current Balance: "+tempBalance);
        tempBalance += tempAmount;
        System.out.println("New current Balance: "+ tempBalance);

        
        String updateStatement = "UPDATE ACCOUNTS SET "+tempAccType+" = ?,Date=?"+ "WHERE UID =?";
        PreparedStatement stmtUpdate = con.prepareStatement(updateStatement);//TODO : MATCH SQL WITH ACTUAL DB
        stmtUpdate.setDouble(1, tempBalance);
        stmtUpdate.setString(2,tempUid);
        stmtUpdate.setString(3, LD.toString());
        stmtUpdate.executeUpdate();
        stmt.closeOnCompletion();
        stmtUpdate.closeOnCompletion();

        this.clearAll();
        System.out.println("deposit done");
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
        this.InitDB();
         //TODO UID retrieval logic
        tempUid = uid;
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM ACCOUNTS WHERE UID =?"); //TODO : MATCH SQL WITH ACTUAL DB
        stmt.setString(1, tempUid);
        ResultSet resultSet = stmt.executeQuery();
        if(resultSet.next()){
        String result = resultSet.getString("UID");
        return result;
        }
        else{
        return errorUID;
        }
    }


    //CHECKS BALANCE OF ONE ACCOUNT AT A TIME
    protected double checkBalance(String uid, String accType) throws SQLException{
           this.InitDB();

        //SKELETON SQL STATEMENT
       /* Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery ("");
         stmt.closeOnCompletion();
         return balance;
         */

        tempUid = uid;
        tempAccType = accType;
        //deposit using prepared statement
        PreparedStatement stmt = con.prepareStatement("select * FROM ACCOUNTS WHERE UID =?"); //TODO : MATCH SQL WITH ACTUAL DB
        //stmt.setString(1, tempAccType);
        stmt.setString(1, tempUid);
        ResultSet resultSet = stmt.executeQuery();
        if(resultSet.next()){
        tempBalance = resultSet.getFloat(tempAccType);
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
    
    
    // method for returning Java time api local date type variable from db
    protected LocalDate getDate(String uid) throws SQLException{
        this.InitDB();
        PreparedStatement stmt = con.prepareStatement("select * FROM ACCOUNTS WHERE UID=?"); //TODO : MATCH SQL WITH ACTUAL DB
        stmt.setString(1, uid);
        ResultSet resultSet = stmt.executeQuery();

        if(resultSet.next()){
          dateHolder= resultSet.getString("DATE");
          tempLD = LocalDate.parse(dateHolder, DateTimeFormatter.ISO_DATE);
        // LD.format(java.time.format.DateTimeFormatter.ofPattern(dateHolder));
       // tempLD = dateHolder;
        }
        else{
        //return errorUID;
        }
        return tempLD;
    }
    
    //TESTING METHODS 
    //TESTING Method to check table for functionality
    protected String checkTable() throws SQLException{
        this.InitDB();
         //TODO UID retrieval logic
        
        PreparedStatement stmt = con.prepareStatement("select * FROM ACCOUNTS"); //TODO : MATCH SQL WITH ACTUAL DB
        //stmt.setString(1, tempUid);
        ResultSet resultSet = stmt.executeQuery();

        if(resultSet.next()){
        return resultSet.getString("UID");
        }
        else{
        return errorUID;
        }
    }

    
    //method tests string-ify of date type for db insertion
    protected void testDate() throws SQLException{
        this.InitDB();
        LocalDate ld = LD.now();
        
        
        String updateStatement = "UPDATE ACCOUNTS SET DATE=?"+ "WHERE UID =?";
        PreparedStatement stmtUpdate = con.prepareStatement(updateStatement);//TODO : MATCH SQL WITH ACTUAL DB
        stmtUpdate.setString(1, ld.toString());
        stmtUpdate.setString(2,"user");
        
        stmtUpdate.executeUpdate();
        //System.out.println(ld.toString());
        System.out.println("Date inserted");
    }
    
    //method tests UNstring-ify of date type for db insertion
    protected void testDate2() throws SQLException{
        this.InitDB();
        LocalDate ld;
        
        
        String updateStatement = "SELECT * FROM ACCOUNTS WHERE UID=?";
        PreparedStatement stmtUpdate = con.prepareStatement(updateStatement);//TODO : MATCH SQL WITH ACTUAL DB
        //stmtUpdate.setString(1, ld.toString());
        stmtUpdate.setString(1,"user");
        
        stmtUpdate.executeUpdate();
        //System.out.println(ld.toString());
        System.out.println("Date inserted");
    }
    
    //END TESTING METHODS
    
    //END METHODS
    
    public static void main(String[] args) {
	 
        try {
            dbConnect db = new dbConnect();
           db.InitDB();
           // String result =db.getUid("user");
           //Double result = db.checkBalance("user", "CHECKINGACCOUNT");
           // String result = db.checkTable();
           // System.out.println("Account Balance is "+result);
           //db.withdraw("user", 500.00, "CHECKINGACCOUNT");
           //db.testDate();
           //LocalDate temp = db.getDate();
           //System.out.println("Date: "+temp);
        } catch (SQLException ex) {
            Logger.getLogger(dbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
    
}
//END CLASS
