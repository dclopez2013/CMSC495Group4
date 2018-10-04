//Import to schedule class to run
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

	public class Interest extends TimerTask {
		String transactionType = "interest";
	    
	    //hardcoded to test(accountBalance)
	    double accountBalance = 2345;
	    
	    double fixedInterestRate = 0.02;
	    double interest = 0;
	    
	    //This will be used to add date with transaction
	    String date = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
	    
	    //This will calculate the interest (need a run method in a Class that extends TimerTask)
	    public void run() {
	    	
	    //To get the account Balance there will need to be a call to the database
	    //SQL (SELECT account_balance FROM Accounts Table WHERE account_type = "savings")
	    //This will be return as the accountBalance variable
	    	
	    interest = accountBalance * (fixedInterestRate/365);
	    double newSavingsAccountBalance = accountBalance + interest;
	    	
	    //This is where we will call the database to add a row into transaction table
	    //SQL (INSERT INTO Transaction Table VALUES (transactionType, interest, accountType, Date)
	    		
	    //Then another database call will be made to update the savings account
	    //SQL UPDATE ACCOUNT TABLE SET account_balance = newSavingsAccountBalance WHERE account_type = "savings"
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
