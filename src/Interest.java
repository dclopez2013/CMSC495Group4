//Import Class
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

	public class Interest extends TimerTask {
		
		String transactionType = "interest";
		String accountType = "savings";
	    double accountBalance = 0;
	    double fixedInterestRate = 0.02;
	    double interest = 0;
	    
	  //This will be used to add date with transaction
	    String date = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
	    
	    //Variables for DB
	    private String URL = "jdbc:oracle::1521:";
	    private String USER = "username";
	    private String PASS = "password";
	    private static Connection con;
	 
	    protected void dbConnection() throws SQLException{
	        try{
	             Class.forName("org.apache.derby.jdbc.ClientDriver"); //TODO : MATCH DRIVER WITH NEEDED DB TYPE
	          } catch(ClassNotFoundException e) {
	             System.out.println("Class not found "+ e);
	          }
	      con = DriverManager.getConnection(URL, USER, PASS);
	    }
	    
	    //This will calculate the interest (need a run method in a Class that extends TimerTask)
	    public void run() {
	    String accountBalanceQuery = "select ? FROM Accounts WHERE account_type =?";
	    
	    try {
	    	PreparedStatement accountBalanceStmt = con.prepareStatement(accountBalanceQuery);
	    	accountBalanceStmt.setString(1, "account_balance");
	    	accountBalanceStmt.setString(2, accountType);
	    	ResultSet resultSet = accountBalanceStmt.executeQuery();
	    	if(resultSet.next()){
	    		accountBalance = resultSet.getDouble("accountBalance");
	    	}
	    } catch(SQLException se) {
	    	se.printStackTrace();
	    }
	    	
	    interest = accountBalance * (fixedInterestRate/365);
	    double newSavingsAccountBalance = accountBalance + interest;
	    
	    String insertTransactionQuery = "INSERT INTO Transaction Table (transaction_type, interest, account_type, date)" + " values (?, ?, ?, ?)";
	    
	    try {
	    	PreparedStatement transactionStmt = con.prepareStatement(insertTransactionQuery);
	    	transactionStmt.setString(1, transactionType);
	    	transactionStmt.setDouble(2, interest);
	    	transactionStmt.setString(3, accountType);
	    	transactionStmt.setString(4, date);
	    	
	    	transactionStmt.execute();
	    } catch(SQLException se) {
	    	se.printStackTrace();
	    }
	    
	    String updateSavingsAccountTotal = "UPDATE Account Table SET account_balance = ? WHERE account_type = ?";
	    
	    try {
	    	PreparedStatement accountUpdateStmt = con.prepareStatement(updateSavingsAccountTotal);
	    	accountUpdateStmt.setDouble(1, newSavingsAccountBalance);
	    	accountUpdateStmt.setString(2,  accountType);
	    	
	    	accountUpdateStmt.execute();
	    } catch(SQLException se) {
	    	se.printStackTrace();
	    }
	    
	   }
	    
	    public static void main(String[] args) {
	    	Calendar today = Calendar.getInstance();
	    	today.set(Calendar.HOUR_OF_DAY, 1);
	    	today.set(Calendar.MINUTE, 0);
	    	today.set(Calendar.SECOND, 0);
	    	
	    	// This will run the task every morning at 1am
	    	Timer timer = new Timer();
	    	timer.schedule(new Interest(), today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)); 
	    }

	}
