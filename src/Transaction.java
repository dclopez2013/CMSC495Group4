import java.sql.SQLException;

public class Transaction extends Input {
	
	protected boolean verifyAccountBalance(String uid, String accType) {
		double accountBalance = 0;
		
		try {
			accountBalance = checkBalance(uid, accType);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (accountBalance < 0) {
			return false;
		} else {
			return true;
		}
	}
	
	protected boolean performDeposit(String uid,float amount, String accountType, String Date) {
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
	
	protected boolean performWithdraw(String uid,float amount, String accountType, String Date)  {
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
