import java.sql.SQLException;
import java.time.LocalDateTime;

public class Transaction extends Input {
	boolean hasBalance;

	Input userInput = new Input();
	
	protected boolean performTransaction(String accountType, String transactionType, double amount, String uid, LocalDateTime localDate) {

        accountType = userInput.getAccountType();
        transactionType = userInput.getTransactionType();
        amount = userInput.getAmount();
        uid = userInput.getUserID();
        localDate = userInput.getDate();

		if (transactionType != "withdraw" || transactionType != "deposit") {
			return false;
		}
		
		if (transactionType == "withdraw") {
			hasBalance = verifyAccountBalance(uid,accountType,amount);
		} else if (!hasBalance) {
			return false;
		} else if (hasBalance) {
			performWithdraw(uid, amount, accountType);
		} 
		
		if (transactionType == "deposit") {
			performDeposit(uid, amount, accountType, localDate);
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
	
	protected boolean performDeposit(String uid,double amount, String accountType, LocalDateTime date) {
		if (amount <= 0) {
			return false;
		}
		
		try {
			//will have to add the date when method parameters are updated
			deposit(uid, amount, accountType, date);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	protected boolean performWithdraw(String uid,double amount, String accountType)  {
		if (amount <= 0) {
			return false;
		}
		
		try {
			
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
