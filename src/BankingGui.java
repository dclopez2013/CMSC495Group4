/**
@filename: BankingGui.java
@date: 9/24/18
@author skingroberson
@purpose: GUI and main for Banking App
**/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class BankingGui extends JFrame implements ItemListener, ActionListener{

	JPanel mainPane = new JPanel();
	JPanel comboBoxPane = new JPanel();
	JPanel cards = new JPanel(new CardLayout());
	final static String checkViewText = "Check View";
	final static String saveViewText = "Save View";
	final static String checkDepositText = "Check Deposit";
	final static String checkWithdrawText = "Check Withdraw";
	final static String saveDepositText = "Save Deposit";
	final static String saveWithdrawText = "Save Withdraw";
	
	
	//Strings for action listener
	final static String accountButtonName = "Account Button";
	final static String viewRadioName = "View Balance";
	final static String depositRadioName = "Make Deposit";
	final static String withdrawRadioName = "Make Withdraw";
	final static String submitTransactName = "Submit Transaction";
	
	
	JPanel checkViewPanel = new JPanel();//card 1
	JPanel checkingDepositPanel = new JPanel();
	JPanel checkingWithdrawPanel = new JPanel();
	JPanel saveViewPanel = new JPanel();//card 2
	JPanel saveDepositPanel = new JPanel();
	JPanel saveWithdrawPanel = new JPanel();

	
	//combobox pane
	String[] accountType = {"Checking","Savings"};
	JComboBox selectAccount = new JComboBox(accountType);
	String selectedAccount = "Checking";
	JButton changeAccountButton = new JButton("Change Account");
	
	//Main Panel declarations
	JLabel bankingText = new JLabel("Online Banking");
	JLabel authLabel = new JLabel("Enter Username: ");
	JTextField auth = new JTextField(); 
	ButtonGroup optionsGroup = new ButtonGroup();
	JRadioButton viewBalanceRadio = new JRadioButton("View Balance");
	JRadioButton makeDepositRadio = new JRadioButton("Make Deposit");
	JRadioButton withdrawRadio = new JRadioButton("Withdraw"); 
	JLabel accountBalLabel = new JLabel("Account Balance: ");
	JLabel accountBalAmount = new JLabel("$0.00");
	//Create date picker box
	JLabel dateSelectorLabel = new JLabel("Date: ");
	//Date
	JTextField dateTextField = new JTextField("mm/dd/yyyy");
	//Create Amount box
	JLabel transactAmountLabel = new JLabel("Amount: ");
	JTextField transactAmount = new JTextField();
	JLabel activityLabel = new JLabel("Activity");
	JButton submitTransact = new JButton("Submit");	
	
	//Checking Declarations
	//Checking View Panel
	String[] checkColumnNames = {"Date","Transaction","Amount"};
	//check data is the object that should be filled from the database or replaced with another data structure
	String[][] checkData = {{"08/01/2019", "Checking Account Deposit", "$1,000"},
			{"08/30/2019", "Interest Earned","$1.00"}};
	JTable checkTableData = new JTable(checkData, checkColumnNames);
	JScrollPane checkActivityPanel = new JScrollPane(checkTableData);
	
	//Checking Deposit panel
	JTextArea checkDepTest = new JTextArea("check deposit screen test");
	JScrollPane checkDepScrollPanel = new JScrollPane(checkDepTest);
	
	//Checking Withdraw panel
		JTextArea checkWithTest = new JTextArea("check Withdraw screen test");
		JScrollPane checkWithScrollPanel = new JScrollPane(checkWithTest);
	
	
	//Savings Declarations
		//savings view panel
	String[] saveColumnNames = {"Date","Transaction","Amount"};
	//save data is the object that should be filled from the database or replaced with another data structure
	String[][] saveData = {{"08/01/2019", "Savings Account Deposit", "$1,000"},
			{"08/30/2019", "Interest Earned","$1.00"}};
	JTable saveTableData = new JTable(saveData, saveColumnNames);
	JScrollPane saveActivityPanel = new JScrollPane(saveTableData);
	
	//Savings Deposit panel
		JTextArea saveDepTest = new JTextArea("savings deposit screen test");
		JScrollPane saveDepScrollPanel = new JScrollPane(saveDepTest);
		
		//Savings Withdraw panel
			JTextArea saveWithTest = new JTextArea("savings Withdraw screen test");
			JScrollPane saveWithScrollPanel = new JScrollPane(saveWithTest);
			JTextArea saveTestText = new JTextArea(50,50);

	GridBagConstraints optionsPanelCon = new GridBagConstraints();
	GridBagConstraints withdrawCon = new GridBagConstraints();
	GridBagConstraints depositCon = new GridBagConstraints();
	
	public BankingGui() {
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
       
       // main pane
        add(mainPane);
        mainPane.setLayout(new GridBagLayout());
        mainPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        
                
        //Mobile Banking Label
        bankingText.setFont(new Font("Courier New", Font.BOLD, 26));
        optionsPanelCon.fill = GridBagConstraints.HORIZONTAL;
        optionsPanelCon.weightx = 0.5;
        optionsPanelCon.weighty = 0.5;
//        optionsPanelCon.ipady = 50;
        optionsPanelCon.gridx = 1;
        optionsPanelCon.gridy = 0;
        mainPane.add(bankingText,optionsPanelCon);
       
      //Auth Label
        optionsPanelCon.fill = GridBagConstraints.HORIZONTAL;
        optionsPanelCon.weightx = 0.5;
        optionsPanelCon.weighty = 0.5;
//        optionsPanelCon.ipady = 50;
        optionsPanelCon.gridx = 2;
        optionsPanelCon.gridy = 0;
        mainPane.add(authLabel,optionsPanelCon);
        
      //Auth Box
        optionsPanelCon.fill = GridBagConstraints.HORIZONTAL;
        optionsPanelCon.weightx = 0.5;
        optionsPanelCon.weighty = 0.5;
//        optionsPanelCon.ipady = 50;
        optionsPanelCon.gridx = 3;
        optionsPanelCon.gridy = 0;
        mainPane.add(auth,optionsPanelCon);
        
        
        //Select Account Type
        optionsPanelCon.fill = GridBagConstraints.HORIZONTAL;
        optionsPanelCon.weightx = 0.5;
        optionsPanelCon.weighty = 0.5;
        //optionsPanelCon.ipady = 50;
        optionsPanelCon.gridx = 1;
        optionsPanelCon.gridy = 1;
        mainPane.add(selectAccount, optionsPanelCon);
        
      //Change Account Type Button
        optionsPanelCon.fill = GridBagConstraints.HORIZONTAL;
        optionsPanelCon.weightx = 0.5;
        optionsPanelCon.weighty = 0.5;
        //optionsPanelCon.ipady = 50;
        optionsPanelCon.gridx = 2;
        optionsPanelCon.gridy = 1;
        mainPane.add(changeAccountButton, optionsPanelCon);
        
        //View Balance Radio
        optionsPanelCon.fill = GridBagConstraints.HORIZONTAL;
        optionsPanelCon.weightx = 0.5;
        optionsPanelCon.weighty = 0.5;
        optionsPanelCon.gridx = 0;
        optionsPanelCon.gridy = 2;
        mainPane.add(viewBalanceRadio,optionsPanelCon);  
        
        //Make deposit Radio
        optionsPanelCon.fill = GridBagConstraints.HORIZONTAL;
        optionsPanelCon.weightx = 0.5;
        optionsPanelCon.gridx = 1;
        optionsPanelCon.gridy = 2;
        mainPane.add(makeDepositRadio,optionsPanelCon);
        viewBalanceRadio.setSelected(true);
        
        //Withdraw Radio
        optionsPanelCon.fill = GridBagConstraints.HORIZONTAL;
        optionsPanelCon.weightx = 0.5;
        optionsPanelCon.gridx = 2;
        optionsPanelCon.gridy = 2;
        mainPane.add(withdrawRadio,optionsPanelCon);
        
        
        //Account Label
        optionsPanelCon.fill = GridBagConstraints.HORIZONTAL;
        optionsPanelCon.weightx = 0.5;
        optionsPanelCon.gridx = 3;
        optionsPanelCon.gridy = 2;
        mainPane.add(accountBalLabel,optionsPanelCon);
        
        //Account Dollar amount
        optionsPanelCon.fill = GridBagConstraints.HORIZONTAL;
        optionsPanelCon.weightx = 0.5;
        optionsPanelCon.weighty = 0.5;
        optionsPanelCon.gridx = 4;
        optionsPanelCon.gridy = 2;
        mainPane.add(accountBalAmount,optionsPanelCon);
     
        //Date label 
        optionsPanelCon.fill = GridBagConstraints.HORIZONTAL;
        optionsPanelCon.weightx = 0.25;
        optionsPanelCon.weighty = 0.5;
        optionsPanelCon.gridx = 1;
        optionsPanelCon.gridy = 3;
        mainPane.add(dateSelectorLabel,optionsPanelCon);
        
        //Date box 
        optionsPanelCon.fill = GridBagConstraints.HORIZONTAL;
        optionsPanelCon.weightx = 0.25;
        optionsPanelCon.weighty = 0.5;
        optionsPanelCon.gridx = 2;
        optionsPanelCon.gridy = 3;
        mainPane.add(dateTextField,optionsPanelCon);
     
        //Transaction amount label
        optionsPanelCon.fill = GridBagConstraints.HORIZONTAL;
        optionsPanelCon.weightx = 0.25;
        optionsPanelCon.weighty = 0.5;
        optionsPanelCon.gridx = 3;
        optionsPanelCon.gridy = 3;
        mainPane.add(transactAmountLabel,optionsPanelCon);
       
        //Transaction amount 
        optionsPanelCon.fill = GridBagConstraints.HORIZONTAL;
        optionsPanelCon.weightx = 0.25;
        optionsPanelCon.weighty = 0.5;
        optionsPanelCon.gridx = 4;
        optionsPanelCon.gridy = 3;
        mainPane.add(transactAmount,optionsPanelCon);
        
        //Submit amount Button 
        optionsPanelCon.fill = GridBagConstraints.HORIZONTAL;
        optionsPanelCon.weightx = 0.25;
        optionsPanelCon.weighty = 0.5;
        optionsPanelCon.gridx = 5;
        optionsPanelCon.gridy = 3;
        mainPane.add(submitTransact,optionsPanelCon);
        
        //Activity Label
        optionsPanelCon.fill = GridBagConstraints.HORIZONTAL;
        optionsPanelCon.weightx = 1;
        optionsPanelCon.gridx = 1;
        optionsPanelCon.gridy = 4;
        mainPane.add(activityLabel,optionsPanelCon);
        
        //Activity Panel 
        optionsPanelCon.fill = GridBagConstraints.HORIZONTAL;
        optionsPanelCon.ipady = 150;
        optionsPanelCon.weightx = 1;
        optionsPanelCon.gridx = 0;
        optionsPanelCon.gridy = 5;
        optionsPanelCon.gridwidth = 4;
        // Add each card to card pane
      //add checkScrollPanel to checkingDeposit Panel
    	//add checkingDepositPanel to as a card
        checkViewPanel.add(checkActivityPanel);
        checkActivityPanel.setPreferredSize(new Dimension(550,200));
        saveViewPanel.add(saveActivityPanel);
        checkingDepositPanel.add(checkDepScrollPanel);
        checkingWithdrawPanel.add(checkWithScrollPanel);
        saveDepositPanel.add(saveDepScrollPanel);
        saveWithdrawPanel.add(saveWithScrollPanel);
        
        cards.add(checkViewPanel);
        cards.add(saveViewPanel);
        cards.add(checkingDepositPanel);
        cards.add(checkingWithdrawPanel);
        cards.add(saveDepositPanel);
        cards.add(saveWithdrawPanel);
        CardLayout cardLayout = (CardLayout)(cards.getLayout());
        cardLayout.addLayoutComponent(checkViewPanel,checkViewText);
        cardLayout.addLayoutComponent(saveViewPanel,saveViewText);
        cardLayout.addLayoutComponent(checkingDepositPanel, checkDepositText);
        cardLayout.addLayoutComponent(checkingWithdrawPanel,checkWithdrawText);
        cardLayout.addLayoutComponent(saveDepositPanel, saveDepositText);
        cardLayout.addLayoutComponent(saveWithdrawPanel,saveWithdrawText);
        mainPane.add(cards, optionsPanelCon);

          ButtonGroup group = new ButtonGroup();
        group.add(viewBalanceRadio);
        group.add(makeDepositRadio);
        group.add(withdrawRadio);
  
        // Adding elements to savingsViewPanel
        saveViewPanel.add(saveTestText);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);  

        //Select Account Listener
        changeAccountButton.addActionListener(this);
        viewBalanceRadio.addActionListener(this);
        makeDepositRadio.addActionListener(this); 
        withdrawRadio.addActionListener(this);
        submitTransact.addActionListener(this);
        
       
        
        //Name some elements
        changeAccountButton.setName(accountButtonName);
        viewBalanceRadio.setName(viewRadioName);
        makeDepositRadio.setName(depositRadioName);
        withdrawRadio.setName(withdrawRadioName);
        submitTransact.setName(submitTransactName);
        
        
        //clear text when date is clicked
        dateTextField.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
            	dateTextField.setText("");
            }
        });
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();
		String sourceName = source.getName();
		System.out.println();
		String selectedAccount = (String)selectAccount.getSelectedItem();
		boolean isSavings = selectedAccount.equals("Savings");
		
		switch (sourceName){
		
		case depositRadioName:
			System.out.println(sourceName);
			//user clicked deposit radio button
			if (isSavings) {
				CardLayout cardLayout = (CardLayout)(cards.getLayout());
				cardLayout.show(cards,saveDepositText);
			} else {
				//make checking deposit panel show up
				
				CardLayout cardLayout = (CardLayout)(cards.getLayout());
				cardLayout.show(cards,checkDepositText);
			}
			break;
			
		case withdrawRadioName:
			System.out.println(sourceName);
			if (isSavings){
				CardLayout cardLayout = (CardLayout)(cards.getLayout());
				cardLayout.show(cards,saveWithdrawText);
			} else {
				CardLayout cardLayout = (CardLayout)(cards.getLayout());
				cardLayout.show(cards,checkWithdrawText);
			}
			break;
		case viewRadioName:
		case accountButtonName:
			if (isSavings) {
				CardLayout cardLayout = (CardLayout)(cards.getLayout());
				cardLayout.show(cards,saveViewText);
			} else {
				CardLayout cardLayout = (CardLayout)(cards.getLayout());
				cardLayout.show(cards,checkViewText);
			}
			viewBalanceRadio.setSelected(true);
			break;
		case submitTransactName:
			//Code should go here for what to do when you hit the submit transaction button

            Transaction tr = new Transaction();
            Input in = new Input();

            tr.performTransaction(in.getAccountType(), in.getTransactionType(),
                    in.getAmount(), in.getUserID(), in.getDate());

            //Date value
			
			String dateInput = dateTextField.getText();
			try {
			    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			LocalDate dateValue = LocalDate.parse(dateInput,formatter);
			System.out.printf("%s%n", dateValue);
			}
			catch (DateTimeParseException exc) {
			    System.out.printf("%s is not parsable!%n", dateInput);
			    throw exc;      // Rethrow the exception.
			}
			break;
		}
	}
	
	
	public static void main(String[] args) {
		BankingGui program = new BankingGui();
		
	}
	public void itemStateChanged(ItemEvent evt) {
//	    CardLayout cardLayout = (CardLayout)(cards.getLayout());
//	    cardLayout.show(cards, (String)evt.getItem());
//	    System.out.println("got card");
	}


	
}
