package capstone;


import java.sql.SQLException;
import java.time.LocalDateTime;

public class Transaction extends Input {
    dbConnect db = new dbConnect();
    
	boolean hasBalance;
	
	public boolean performTransaction(String accountType, String transactionType, double amount, String uid, LocalDateTime localDate) throws SQLException {
		System.out.println("at perform transaction");
                
		if (transactionType == "withdraw") {
                    System.out.println("at withdraw");
                    System.out.println(transactionType);
                    System.out.println("username : "+uid);
		System.out.println("accountType: "+accountType);
		System.out.println("date :"+ localDate);
                    performWithdraw(uid, amount, accountType);
		}	
		if (transactionType == "checkBalance") {
                    System.out.println("at checkbalance");
			Double balance = db.checkBalance(uid,accountType);
		} 
		if (transactionType == "deposit") {
                    System.out.println("deposit");
			performDeposit(uid, amount, accountType, localDate);
		}
		
		return true;
		
	}
	
	public boolean verifyAccountBalance(String uid, String accountType, double amount) {
		double accountBalance = 0;
		
		try {
			accountBalance = db.checkBalance(uid, accountType);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (accountBalance < amount) {
			return false;
		} else {
			return true;
		}
		
	}
	
	public void performDeposit(String uid,double amount, String accountType, LocalDateTime date) {
		
		System.out.println("username : "+uid);
		System.out.println("username : "+amount);
		System.out.println("accountType: "+accountType);
		System.out.println("date :"+ date);
		try {
			//will have to add the date when method parameters are updated
			db.deposit(uid, amount, accountType, date);
			//dbConnect db = new dbConnect();
                        //db.deposit(uid, amount, accountType, date);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void performWithdraw(String uid,double amount, String accountType)  {
		if (amount <= 0) {
			
		}
		
		try {
			System.out.println("username : "+uid);
		System.out.println("username : "+amount);
		System.out.println("accountType: "+accountType);
		System.out.println("date :"+ date);
			db.withdraw(uid, amount, accountType);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	protected double getAccountBalance(String uid, String accountType) {
		double accountBalance = 0;
		
		try {
			accountBalance = db.checkBalance(uid, accountType);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return accountBalance;
	}
}
