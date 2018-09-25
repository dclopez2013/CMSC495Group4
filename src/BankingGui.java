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

public class BankingGui extends JFrame implements ItemListener, ActionListener{

	JPanel mainPane = new JPanel();
	JPanel comboBoxPane = new JPanel();
	JPanel cards = new JPanel(new CardLayout());
	final static String checkViewText = "Check View";
	final static String saveViewText = "Save View";
	JPanel checkViewPanel = new JPanel();//card 1
	JPanel saveViewPanel = new JPanel();//card 2
	
	//combobox pane
	String[] accountType = {"Checking","Savings"};
	JComboBox selectAccount = new JComboBox(accountType);
	String selectedAccount = "Checking";
	JButton changeAccountButton = new JButton("Change Account");
	
	//Checking View Panel declarations
	JLabel bankingText = new JLabel("Mobile Banking");
	
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
	// Savings View Panel declarations
	JTextArea saveTestText = new JTextArea(50,50);
	
	public BankingGui() {
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
       
       // main pane
        add(mainPane);
        mainPane.setLayout(new GridBagLayout());
        mainPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        
                
        //Mobile Banking Label
        optionsPanelCon.fill = GridBagConstraints.HORIZONTAL;
        optionsPanelCon.weightx = 0.5;
        optionsPanelCon.weighty = 0.5;
        optionsPanelCon.ipady = 50;
        optionsPanelCon.gridx = 1;
        optionsPanelCon.gridy = 0;
        mainPane.add(bankingText,optionsPanelCon);
        
        
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
        
        //Account Label
        optionsPanelCon.fill = GridBagConstraints.HORIZONTAL;
        optionsPanelCon.weightx = 0.5;
        optionsPanelCon.gridx = 2;
        optionsPanelCon.gridy = 2;
        mainPane.add(accountBalLabel,optionsPanelCon);
        
        //Account Dollar amount
        optionsPanelCon.fill = GridBagConstraints.HORIZONTAL;
        optionsPanelCon.weightx = 0.5;
        optionsPanelCon.weighty = 0.5;
        optionsPanelCon.gridx = 3;
        optionsPanelCon.gridy = 2;
        mainPane.add(accountBalAmount,optionsPanelCon);
        
        //Activity Label
        optionsPanelCon.fill = GridBagConstraints.HORIZONTAL;
        optionsPanelCon.weightx = 1;
        optionsPanelCon.gridx = 1;
        optionsPanelCon.gridy = 3;
        mainPane.add(activityLabel,optionsPanelCon);
        
        //Activity Panel 
        //TODO: this needs to change to checking account panel and new check panel constraints
        optionsPanelCon.fill = GridBagConstraints.HORIZONTAL;
        optionsPanelCon.ipady = 150;
        optionsPanelCon.weightx = 1;
        optionsPanelCon.gridx = 0;
        optionsPanelCon.gridy = 4;
        optionsPanelCon.gridwidth = 4;
        // Add each card to card pane
        checkViewPanel.add(checkActivityPanel);
        checkActivityPanel.setPreferredSize(new Dimension(550,200));
        saveViewPanel.add(saveActivityPanel);
        cards.add(checkViewPanel);
        cards.add(saveViewPanel);
        CardLayout cardLayout = (CardLayout)(cards.getLayout());
        cardLayout.addLayoutComponent(checkViewPanel,checkViewText);
        cardLayout.addLayoutComponent(saveViewPanel,saveViewText);
        mainPane.add(cards, optionsPanelCon);

          ButtonGroup group = new ButtonGroup();
        group.add(viewBalanceRadio);
        group.add(makeDepositRadio);
  
        // Adding elements to savingsViewPanel
        saveViewPanel.add(saveTestText);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);  

        //Select Account Listener
        changeAccountButton.addActionListener(this); 
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String selectedAccount = (String)selectAccount.getSelectedItem();
		System.out.println(selectedAccount);
		if(selectedAccount.equals("Savings")){
			CardLayout cardLayout = (CardLayout)(cards.getLayout());
			cardLayout.show(cards,saveViewText);
		}
		if(selectedAccount.equals("Checking")){
			CardLayout cardLayout = (CardLayout)(cards.getLayout());
			cardLayout.show(cards,checkViewText);
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
