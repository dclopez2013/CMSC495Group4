package capstone;


import java.time.LocalDateTime;

public class Input {
        
    BankingGui bg = RunBankApp.bg;
    // initiate variables needed in methods
    String userID = null;
    String accountType = null;
    String transactionType = null;
    String amountText = null;
    LocalDateTime date = null;
    public double amount = 0.0;


    public String getTransactionType() {

        // if the radio button labels were an array in Gui, then I could
        // use the array in a for-each here instead of hardcoding values
        if (bg.withdrawRadio.isSelected()) {
            transactionType = "withdraw";
            return transactionType;
        } else if (bg.makeDepositRadio.isSelected()) {
            transactionType = "deposit";
            return transactionType;
        } else if (bg.viewBalanceRadio.isSelected()) {
            transactionType = "checkBalance";
            return transactionType;
        }

        System.out.println("input class chose "+transactionType);
        return transactionType;
    }

    public String getAccountType() {

        accountType = (String)bg.selectAccount.getSelectedItem();

        return accountType;
    }

    public double getAmount() {

        // todo - still needs work
        // this is going to pull the text from the checking deposit text area
        // waiting for shanea to see if using one jtextarea for all screens works
        amount = Double.parseDouble(bg.transactAmount.getText());
        return amount;
    }

    public LocalDateTime getDate() {

        // todo - not worked on yet, still waiting on gui
        LocalDateTime ldt = LocalDateTime.now();
        return ldt;
    }

    public String getUserID() {

        // todo - needs notes still
        //userID = bg.auth.getText();
        return "user";
    }

    public static void main(String[] args) {

        Input testInput = new Input();

        System.out.println(testInput.getAmount());
        System.out.println(testInput.getTransactionType());
        System.out.println(testInput.getAccountType());
        System.out.println(testInput.getDate());
        System.out.println(testInput.getUserID());
    }
}