/**
@filename: BankingGui.java
@date: 9/24/18
@author skingroberson
@purpose: GUI and main for Banking App
**/

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class BankingGui extends JFrame implements ActionListener{

	JPanel mainPanel = new JPanel();
	JLabel bankingText = new JLabel("Mobile Banking");
	String[] accountType = {"Checking","Savings"};
	JComboBox selectAccount = new JComboBox(accountType);
	Container optionsPane = new Container();
	ButtonGroup optionsGroup = new ButtonGroup();
	JRadioButton viewBalanceRadio = new JRadioButton("View Balance");
	JRadioButton makeDepositRadio = new JRadioButton("Make Deposit");
	JLabel accountBalLabel = new JLabel("Account Balance: ");
	JLabel accountBalAmount = new JLabel("$0.00");
	JLabel activityLabel = new JLabel("Activity");
	String[] checkColumnNames = {"Date","Transaction","Amount"};
	String[][] checkData = {{"08/01/2019", "Checking Account Deposit", "$1,000"},
			{"08/30/2019", "Interest Earned","$1.00"}};
	JTable checkTableData = new JTable(checkData, checkColumnNames);
	JScrollPane checkActivityPanel = new JScrollPane(checkTableData);
	
	
	String[] saveColumnNames = {"Date","Transaction","Amount"};
	String[][] saveData = {{"08/01/2019", "Savings Account Deposit", "$1,000"},
			{"08/30/2019", "Interest Earned","$1.00"}};
	JTable saveTableData = new JTable(saveData, saveColumnNames);
	JScrollPane saveActivityPanel = new JScrollPane(saveTableData);
	
	GridBagConstraints optionsPanelCon = new GridBagConstraints();
	
	
	public BankingGui() {
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
       //adding main panel
        add(mainPanel);
        

        //adding UI elements to main panel
        
        add(optionsPane);
        optionsPane.setLayout(new GridBagLayout());
        optionsPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        
        //Mobile Banking Label
        optionsPanelCon.fill = GridBagConstraints.HORIZONTAL;
        optionsPanelCon.weightx = 0.5;
        optionsPanelCon.weighty = 0.5;
        optionsPanelCon.ipady = 50;
        optionsPanelCon.gridx = 1;
        optionsPanelCon.gridy = 0;
        optionsPane.add(bankingText,optionsPanelCon);
        
        
        //Select Account Type
        optionsPanelCon.fill = GridBagConstraints.HORIZONTAL;
        optionsPanelCon.weightx = 0.5;
        optionsPanelCon.weighty = 0.5;
//        optionsPanelCon.ipady = 50;
        optionsPanelCon.gridx = 1;
        optionsPanelCon.gridy = 1;
        optionsPane.add(selectAccount, optionsPanelCon);
        
        //View Balance Radio
        optionsPanelCon.fill = GridBagConstraints.HORIZONTAL;
        optionsPanelCon.weightx = 0.5;
        optionsPanelCon.weighty = 0.5;
        optionsPanelCon.gridx = 0;
        optionsPanelCon.gridy = 2;
        optionsPane.add(viewBalanceRadio,optionsPanelCon);  
        
        //Make deposit Radio
        optionsPanelCon.fill = GridBagConstraints.HORIZONTAL;
        optionsPanelCon.weightx = 0.5;
        optionsPanelCon.gridx = 1;
        optionsPanelCon.gridy = 2;
        optionsPane.add(makeDepositRadio,optionsPanelCon);
        viewBalanceRadio.setSelected(true);
        
        //Account Label
        optionsPanelCon.fill = GridBagConstraints.HORIZONTAL;
        optionsPanelCon.weightx = 0.5;
        optionsPanelCon.gridx = 2;
        optionsPanelCon.gridy = 2;
        optionsPane.add(accountBalLabel,optionsPanelCon);
        
        //Account Dollar amount
        optionsPanelCon.fill = GridBagConstraints.HORIZONTAL;
        optionsPanelCon.weightx = 0.5;
        optionsPanelCon.weighty = 0.5;
        optionsPanelCon.gridx = 3;
        optionsPanelCon.gridy = 2;
        optionsPane.add(accountBalAmount,optionsPanelCon);
        
        //Activity Label
        optionsPanelCon.fill = GridBagConstraints.HORIZONTAL;
        optionsPanelCon.weightx = 1;
        optionsPanelCon.gridx = 1;
        optionsPanelCon.gridy = 3;
        optionsPane.add(activityLabel,optionsPanelCon);
        
        //Activity Panel
        optionsPanelCon.fill = GridBagConstraints.HORIZONTAL;
        optionsPanelCon.ipady = 150;
        optionsPanelCon.weightx = 1;
        optionsPanelCon.gridx = 1;
        optionsPanelCon.gridy = 4;
        //if checking account and view balance show checking table data
        //if(selectAccount.
	        optionsPane.add(checkActivityPanel,optionsPanelCon);
	        checkActivityPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	        checkActivityPanel.setPreferredSize(new Dimension(450,100));
	        
	    //if savings account and view balance show savings table data    
	        optionsPane.add(saveActivityPanel,optionsPanelCon);
	        saveActivityPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	        saveActivityPanel.setPreferredSize(new Dimension(450,100));
        ButtonGroup group = new ButtonGroup();
        group.add(viewBalanceRadio);
        group.add(makeDepositRadio);
        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);  

        //Select Account Listener
        selectAccount.addActionListener(this); 
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String selectedAccount = (String)selectAccount.getSelectedItem();
		System.out.println(selectedAccount);
		
	}
	public static void main(String[] args) {
		BankingGui program = new BankingGui();
		
	}
	
}
