import java.sql.SQLException;

public class Account extends dbConnect {
	
	protected boolean checkAccountBalance(String uid, String accType) {
		float accountBalance = 0;
		
		try {
			accountBalance = checkBalance(uid, accType);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (accountBalance > 0) {
			return false;
		} else {
			return true;
		}
	}
	
	protected boolean preformDeposit(String uid,float ammount, String accType) {
		try {
			desposit(uid, ammount, accType);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	protected void preformWithdraw(String uid,float ammount, String accType)  {
		try {
			withdraw(uid, ammount, accType);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return;
	}
	
	protected float getAccountBalance(String uid, String accType) {
		float accountBalance = 0;
		
		try {
			accountBalance = checkBalance(uid, accType);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return accountBalance;
	}
}
