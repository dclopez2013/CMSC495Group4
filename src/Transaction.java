import java.sql.SQLException;

public class Transaction extends Input {
	boolean hasBalance;
	
	protected boolean performTransaction(String accountType, String transactionType, double amount, String date, String uid) {
		
		if (transactionType != "withdraw" || transactionType != "deposit") {
			return false;
		}
		
		if (transactionType == "withdraw") {
			hasBalance = verifyAccountBalance(uid,accountType,amount);
		} else if (!hasBalance) {
			return false;
		} else if (hasBalance) {
			performWithdraw(uid, amount, accountType, date);
		} 
		
		if (transactionType == "deposit") {
			performDeposit(uid, amount, accountType, date);
		}
		
		return true;
		
	}
	
	protected boolean verifyAccountBalance(String uid, String accountType, double amount) {
		double accountBalance = 0;
		
		try {
			accountBalance = checkBalance(uid, accountType);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (accountBalance < amount) {
			return false;
		} else {
			return true;
		}
		
	}
	
	protected boolean performDeposit(String uid,double amount, String accountType, String Date) {
		if (amount <= 0) {
			return false;
		}
		
		try {
			//will have to add the date when method parameters are updated
			deposit(uid, amount, accountType);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	protected boolean performWithdraw(String uid,double amount, String accountType, String Date)  {
		if (amount <= 0) {
			return false;
		}
		
		try {
			//will have to add the date when the method parameters are updated
			withdraw(uid, amount, accountType);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	protected double getAccountBalance(String uid, String accountType) {
		double accountBalance = 0;
		
		try {
			accountBalance = checkBalance(uid, accountType);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return accountBalance;
	}
}
