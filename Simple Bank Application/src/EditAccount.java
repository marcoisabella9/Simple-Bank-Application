import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditAccount implements ActionListener {

    myFrame editAccountFrame = new myFrame();

    JPanel mainPanel = new JPanel();
    JPanel titlePanel = new JPanel();
    JPanel footerPanel = new JPanel();

    JLabel titleLabel = new JLabel("View/Edit Account Page");
    JLabel searchLabel = new JLabel("Acc Name:");
    JLabel accountsListLabel = new JLabel();
    JLabel editMenuSearchLabel = new JLabel("Deposit/Withdraw:");
    JLabel editMenuConfirmLabel = new JLabel();

    JTextField accountSearchTextField = new JTextField();
    JTextField editMenuTextField = new JTextField();

    JButton accountSearchButton = new JButton("Search");
    JButton launchPageButton = new JButton("Return to Menu");
    JButton editMenuDepositButton = new JButton("Deposit");
    JButton editMenuWithdrawButton = new JButton("Withdraw");
    String selectedAcc = "";

    EditAccount() {

        launchPageButton.setFocusable(false);
        launchPageButton.addActionListener(this);

        titleLabel.setBounds(0, 0, 200, 50);
        titleLabel.setFont(new Font(null, Font.PLAIN, 25));

        // -------------- VIEW/SEARCH ------------------

        accountSearchButton.setBounds(200,30,75,30);
        accountSearchButton.setFocusable(false);
        accountSearchButton.addActionListener(this);

        accountSearchTextField.setPreferredSize(new Dimension(100,30));
        accountSearchTextField.setBounds(90, 30, 105, 30);

        searchLabel.setFont(new Font(null, Font.PLAIN, 15));
        searchLabel.setBounds(10, 30, 75, 30);

        accountsListLabel.setVisible(true);
        accountsListLabel.setFont(new Font(null, Font.PLAIN, 15));
        accountsListLabel.setBounds(10, 30, 275, 100);

        // --------------- EDIT MENU ------------------

        editMenuSearchLabel.setVisible(false);
        editMenuSearchLabel.setBounds(10, 120, 125, 30);
        editMenuSearchLabel.setFont(new Font(null, Font.PLAIN, 15));

        editMenuConfirmLabel.setVisible(false);
        editMenuConfirmLabel.setBounds(75, 200, 175, 30);
        editMenuConfirmLabel.setFont(new Font(null, Font.BOLD, 17));

        editMenuTextField.setVisible(false);
        editMenuTextField.setPreferredSize(new Dimension(100, 30));
        editMenuTextField.setBounds(135, 120, 140, 30);

        editMenuDepositButton.setVisible(false);
        editMenuDepositButton.setBounds(50, 170, 90,30);
        editMenuDepositButton.setFocusable(false);
        editMenuDepositButton.addActionListener(this);

        editMenuWithdrawButton.setVisible(false);
        editMenuWithdrawButton.setBounds(175,170,90,30);
        editMenuWithdrawButton.setFocusable(false);
        editMenuWithdrawButton.addActionListener(this);

        // --------------------------------------------

        mainPanel.setBackground(new Color(255, 209, 181));
        mainPanel.setLayout(null); // layout of main panel is null, so we can place elements ourselves
        mainPanel.add(accountsListLabel);
        mainPanel.add(accountSearchTextField);
        mainPanel.add(accountSearchButton);
        mainPanel.add(searchLabel);
        mainPanel.add(editMenuSearchLabel);
        mainPanel.add(editMenuTextField);
        mainPanel.add(editMenuDepositButton);
        mainPanel.add(editMenuWithdrawButton);
        mainPanel.add(editMenuConfirmLabel);

        // title and footer panels utilize BorderLayout
        titlePanel.setBackground(new Color(255, 209, 181));
        titlePanel.add(titleLabel);

        footerPanel.setBackground(new Color(255, 209, 181));
        footerPanel.add(launchPageButton);

        titlePanel.setPreferredSize(new Dimension(100, 50));
        mainPanel.setPreferredSize(new Dimension(100,100));
        footerPanel.setPreferredSize(new Dimension(100, 50));

        editAccountFrame.add(mainPanel, BorderLayout.CENTER);
        editAccountFrame.add(titlePanel, BorderLayout.NORTH);
        editAccountFrame.add(footerPanel, BorderLayout.SOUTH);

        ImageIcon bankIcon = new ImageIcon("resources/images/bank of marmerica icon.png");
        editAccountFrame.setIconImage(bankIcon.getImage());

        editAccountFrame.setTitle("BoM - View/Edit Acc");
        editAccountFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == launchPageButton) {
            SoundPlayer.playSound("resources/audio/click.wav");
            editAccountFrame.dispose();
            new LaunchPage();
        }

        // When searching for an account, we check the list of all BankAccount objects, set the appearing text to it's toString, and allow the edit elements to appear if appropriate
        if (e.getSource() == accountSearchButton) {
            String accountName = accountSearchTextField.getText();
            BankAccount foundAccount = null;

            for (BankAccount account : BankAccount.allAccounts) {
                if (account.getAccName().equals(accountName)) {
                    foundAccount = account;
                    break;
                }
            }

            if (foundAccount != null) {
                SoundPlayer.playSound("resources/audio/click.wav");
                selectedAcc = accountName;
                accountsListLabel.setText(BankAccount.accountToString(accountName));

                editMenuSearchLabel.setVisible(true);
                editMenuTextField.setVisible(true);
                editMenuDepositButton.setVisible(true);
                editMenuWithdrawButton.setVisible(true);
            } else {
                SoundPlayer.playSound("resources/audio/windowsxpError.wav");
                accountsListLabel.setText("Account Not Found");
            }

            accountsListLabel.setVisible(true);
            accountSearchTextField.setText("");
            editMenuTextField.setText("");
            editMenuConfirmLabel.setText("");
        }

        if (e.getSource() == editMenuDepositButton) {
            String editAmountText = editMenuTextField.getText();

            // combination of try-catch and if-else statements handle all possible inputs
            try {
                double editAmount = (double) Math.round(Double.parseDouble(editAmountText) * 100) / 100; // converting the inputted String to a double and rounding it to the hundredths place

                BankAccount foundAccount = findAccountByName(selectedAcc);
                if (foundAccount != null) {
                    SoundPlayer.playSound("resources/audio/click.wav");
                    foundAccount.deposit(editAmount);
                    accountsListLabel.setText(BankAccount.accountToString(selectedAcc));
                    editMenuConfirmLabel.setText("$" + editAmount + " Deposited");
                }
            } catch (NumberFormatException e2) {
                SoundPlayer.playSound("resources/audio/windowsxpError.wav");
                editMenuConfirmLabel.setText("Enter Valid Amount");
            }

            editMenuConfirmLabel.setVisible(true);
        }

        if (e.getSource() == editMenuWithdrawButton) {
            String editAmountText = editMenuTextField.getText();

            try {
                double editAmount = (double) Math.round(Double.parseDouble(editAmountText) * 100) / 100;

                BankAccount foundAccount = findAccountByName(selectedAcc);
                if (foundAccount != null && foundAccount.getBalance() >= editAmount) {
                    SoundPlayer.playSound("resources/audio/click.wav");
                    foundAccount.withdraw(editAmount);
                    accountsListLabel.setText(BankAccount.accountToString(selectedAcc));
                    editMenuConfirmLabel.setText("$" + editAmount + " Withdrawn");
                }
                else if (foundAccount.getBalance() < editAmount) { // withdraw cannot exceed balance
                    SoundPlayer.playSound("resources/audio/windowsxpError.wav");
                    editMenuConfirmLabel.setText("Not Enough Funds");
                }
            } catch (NumberFormatException e2) {
                SoundPlayer.playSound("resources/audio/windowsxpError.wav");
                editMenuConfirmLabel.setText("Enter Valid Amount");
            }

            editMenuConfirmLabel.setVisible(true);
        }
    }

    // helper method used for editing certain a certain account's information
    private BankAccount findAccountByName(String accountName) {
        for (BankAccount account : BankAccount.allAccounts) {
            if (account.getAccName().equals(accountName)) {
                return account;
            }
        }
        return null;
    }
}
