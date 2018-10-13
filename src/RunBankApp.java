package capstone;


public class RunBankApp extends Authenticate {

    public static BankingGui bg;
    // calls an instance of this class, which is the main run of the app

    public static void main(String[] args) {

       // Input testInput = new Input();
RunBankApp grp4Final = new RunBankApp();
bg = new BankingGui();
      // System.out.println(testInput.getAmount());
       // System.out.println(testInput.getTransactionType());
        ///System.out.println(testInput.getAccountType());
        //System.out.println(testInput.getDate());
       // System.out.println(testInput.getUserID());
    }
    // todo - should this be a call to Interest or should Interest be
    // implemented in this class?

  //  Interest grp4FinalInterest = new Interest();
}
