public class Transaction extends Account{


    // initiate variables needed in methods
    String userID = null;
    String accountType = null;
    String transactionType = null;
    String amountText = null;
    String date = null;
    double amount = 0;

    public void performTransaction(accountType,transactionType,amount,date,userID) {
        switch (accountType.toLowerCase()) {

        }



    }


    public static void main(String[] args) {

        Input testInput = new Input();

        System.out.println(testInput.getAmount());
        System.out.println(testInput.getTransactionType());
        System.out.println(testInput.getAccountType());
        System.out.println(testInput.getDate());
        System.out.println(testInput.getUserdID());
    }
}