/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankappfxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import com.gluonhq.charm.glisten.control.ToggleButtonGroup;
import java.sql.SQLException;
import java.time.LocalDateTime;
import com.gluonhq.charm.glisten.control.ToggleButtonGroup;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
/**
 *
 * @author dclop
 */
public class BankAppController implements Initializable {
    
    public dbConnect db = new dbConnect();
            
    @FXML
    private ToggleButtonGroup groupTrans;
    
    @FXML
    private TextField uid;
    
    @FXML
    private TextField amount;
    
    
    @FXML
    private RadioButton withdraw;
    
    @FXML
    private RadioButton deposit;
    
    @FXML
    private RadioButton checkBalance;
    
    @FXML
    private ChoiceBox accountType;
    
    @FXML
    private TextArea activity;
    
    @FXML
    private Button clearText;
    
    @FXML
    private Button submitButton;
    
    String transactionType="";
    private String logTemp = "User ";
    String uidString ="";
    String transString ="";
    
    LocalDateTime ldt;
    String accType="";
    Double ammount = 0.00;
     
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        accountType.getItems().setAll("CHECKING ACCOUNT","SAVING ACCOUNT");
        withdraw.setId("withdraw");
        deposit.setId("deposit");
        checkBalance.setId("checkbalance");
        checkBalance.setSelected(true);
    }    
    
    
    @FXML
    private void Deposit(String uid, double amt,String accType, LocalDateTime ldt) throws SQLException{
        db.deposit(uid,amt,accType,ldt);
        this.goodTransaction(uid, accType, amt,this.gettransType());
        
    }
    
    
    @FXML
    private void Withdraw(String uid, double amt,String accType, LocalDateTime ldt) throws SQLException{
        db.withdraw(uid,amt,accType);
        this.goodTransaction(uid, accType, amt,this.gettransType());
    }
    
    @FXML
    private void checkBalance(String uid, double amt,String accType, LocalDateTime ldt) throws SQLException{
       // db.checkBalance(uid,accType);
       //this.checkBalance2(uid, accType);
    }
    
    @FXML
    private void submit() throws SQLException{
        //System.out.println("Account Type: "+this.getaccType());
        System.out.println("Transaction Type: "+this.gettransType());
        
        if(this.getUid().isEmpty() || this.getAmount().isNaN() || this.gettransType().contentEquals("none")){
                this.invalidData();
        }
        else{
        if(checkBalance.isSelected()){
          db.InitDB();
          double tempBalance = db.checkBalance(this.getUid(), this.getaccType());
        this.checkBalance2(this.getUid(), this.getaccType(),tempBalance);
        
        this.logIt(this.gettransType());
        }
        else if(deposit.isSelected()){
        this.Deposit(this.getUid(), this.getAmount(), this.getaccType(), this.getDate());
        this.logIt(this.gettransType());
        }
        else if(withdraw.isSelected()){
        this.Withdraw(this.getUid(), this.getAmount(), this.getaccType(), this.getDate());
        this.logIt(this.gettransType());
        }
        }
        //this.logIt(this.gettransType());
    }
    
    private String getUid(){
        
        this.uidString=this.uid.getText();
        return uidString;
    }
    
    private Double getAmount(){
        this.ammount = Double.parseDouble(this.amount.getText());
        return ammount;
    }
    
    private String getaccType(){
        this.accType = accountType.getSelectionModel().getSelectedItem().toString();
        String type ="";
        if(this.accType.contains("CHECKING")){
            type= "CHECKINGACCOUNT";
        }
        else if(this.accType.contains("SAVING")){
            type= "SAVINGACCOUNT";
        }
       // return this.accountType.getSelectionModel().selectedItemProperty().getName();
       return type;
    }
    
    private String gettransType(){
        String tranType= "withdraw";
       // return this.groupTrans.getSelectionType().name();
        if(withdraw.isSelected()){
            tranType="withdraw";
        }
        if(deposit.isSelected()){
            tranType="deposit";
        }
        if(checkBalance.isSelected()){
            tranType="checkBalance";
        }
        
        return tranType;
    }
    
    @FXML
    private void checkBalance2(String uid,String accType,Double money){
        //String placeHolder = "INSERT MONEY";
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Account Balance for "+uid);
        alert.setHeaderText("Checking Account Balance for "+ accType);
        alert.setContentText("Your Account Balance is "+ money);
        
        alert.showAndWait();
        //this.goodTransaction(uid, accType, money, this.gettransType());
    }
    
    
    public void logIt(String action){
        ldt = LocalDateTime.now();
        logTemp += " "+action+" at "+ ldt.getHour()+"/"+ldt.getMinute()+"/"+ldt.getDayOfWeek()+"/"+ldt.getMonth()+"/"+ldt.getYear();
        //this.activity.appendText("Test");
        this.activity.appendText(logTemp+"\n");
        logTemp ="User ";
    }
    
    public LocalDateTime getDate(){
        ldt = LocalDateTime.now();
        return this.ldt;
       }
    
    public void invalidData(){
        String placeHolder = "Invalid Data";
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Invalid Data");
        alert.setHeaderText("Unable to continue");
        alert.setContentText("CHECK TO MAKE SURE YOU HAVE VALID DATA TYPES AND NO FILED IS EMPTY OR NOT SELECTED");
        
        alert.showAndWait();
    }
    
    public void goodTransaction(String uid,String accType,Double money,String trans){
            String placeHolder = "Invalid Data";
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Successful Transaction");
            alert.setHeaderText("Your Transaction");
            alert.setContentText(uid+": You successfully completed your "+ trans +" With the following account: "+accType);     
            alert.showAndWait();
    }
    
    @FXML
    private void clearFields(){
        this.amount.clear();
        this.uid.clear();
    }
}
