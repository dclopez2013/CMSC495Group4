public class Input {

    // initiate instance of BankingGUI to use in methods
    BankingGui program = new BankingGui();

    // initiate variables needed in methods
    String userID = null;
    String accountType = null;
    String transactionType = null;
    String amountText = null;
    String date = null;
    double amount = 0;


    public String getTransactionType() {

        // if the radio button labels were an array in Gui, then I could
        // use the array in a for-each here instead of hardcoding values
        if (program.withdrawRadio.isSelected()) {
            transactionType = "withdrawal";
        } else if (program.makeDepositRadio.isSelected()) {
            transactionType = "deposit";
        } else if (program.viewBalanceRadio.isSelected()) {
            transactionType = "viewBalance";
        }

        return transactionType;
    }

    public String getAccountType() {

        accountType = (String)program.selectAccount.getSelectedItem();

        return accountType;
    }

    public double getAmount() {

        // filler value, needs work
        amount = 9.99;
        return amount;
    }

    public String getDate() {

        // not worked on yet

        return date;
    }

    public String getUserdID() {

        // not worked on yet

        return userID;
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