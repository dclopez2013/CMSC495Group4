public class Input {

    String userID = null;
    String accountType = null;
    String transactionType = null;
    String amountText = null;
    String date = null;
    float amount = 0;


    public float getAmount() {
        amountText = amountTextField.getText();
        amount = Float.parseFloat(amountText);
        return amount;
    }

    public String getTransactionType() {
        if (withdrawRadioButtion.isSelected()) {
            transactionType = "withdrawal";
        } else if (depositRadioButton.isSelected()) {
            transactionType = "deposit";
        }

        return transactionType;
    }

    public String getAccountType() {
        if (checkingAccountCheckBox.isSelected()) {
            accountType = "Checking";
        } else if (savingAccountCheckBox.isSelected()) {
            accountType = "Savings";
        }

        return accountType;
    }

    public String getDate() {
        date = dateTextField.getText();
        return date;
    }

    public String getUserdID() {
        userID = userIdTextField.getText();
        return userID;
    }

    public static void main(String[] args) {

        Call performTransaction (accountType, TransactionType, amount, date, userId);

        Call confirmationWindow (accountType, TransactionType, amount, date, userId);

    }
}