import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAccount implements ActionListener {

    myFrame createAccountFrame = new myFrame();

    JPanel mainPanel = new JPanel();
    JPanel titlePanel = new JPanel();
    JPanel footerPanel = new JPanel();

    JLabel label = new JLabel("Create Account Page");
    JLabel accountNameLabel = new JLabel("Account Name: ");
    JLabel startingBalanceLabel = new JLabel("Starting Balance: ");
    JLabel confirmMsgLabel = new JLabel("Account Created Successfully");

    JTextField accountNameTextField = new JTextField();
    JTextField startingBalanceTextField = new JTextField();

    JButton submitButton = new JButton("Submit");
    JButton launchPageButton = new JButton("Return to Menu");

    CreateAccount() {

        launchPageButton.setBounds(100, 160, 100, 20);
        launchPageButton.setFocusable(false);
        launchPageButton.addActionListener(this);

        submitButton.setFocusable(false);
        submitButton.addActionListener(this);

        label.setBounds(0,0, 200, 50);
        label.setFont(new Font(null, Font.PLAIN, 25));

        accountNameLabel.setBounds(0,0,200,50);
        accountNameLabel.setFont(new Font(null, Font.PLAIN, 15));

        startingBalanceLabel.setBounds(0,0,200,50);
        startingBalanceLabel.setFont(new Font(null, Font.PLAIN, 15));

        confirmMsgLabel.setBounds(0,10,200,50);
        confirmMsgLabel.setFont(new Font(null, Font.PLAIN, 17));
        confirmMsgLabel.setVisible(false);

        accountNameTextField.setPreferredSize(new Dimension(150, 30));
        startingBalanceTextField.setPreferredSize(new Dimension(140,30));

        // verify entered value is double
        startingBalanceTextField.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                String text = ((JTextField) input).getText();
                if (text.isEmpty()) {
                    return true;
                }

                try {
                    Double.parseDouble(text);
                    return true;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(createAccountFrame, "Please enter a valid number", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    startingBalanceTextField.setText("");
                    return false;
                }
            }
        });

        titlePanel.setBackground(new Color(255, 209, 181));
        titlePanel.add(label);

        mainPanel.setBackground(new Color(255, 209, 181));
        mainPanel.add(accountNameLabel);
        mainPanel.add(accountNameTextField);
        mainPanel.add(startingBalanceLabel);
        mainPanel.add(startingBalanceTextField);
        mainPanel.add(submitButton);
        mainPanel.add(confirmMsgLabel);

        footerPanel.setBackground(new Color(255, 209, 181));
        footerPanel.add(launchPageButton);

        titlePanel.setPreferredSize(new Dimension(100, 50));
        mainPanel.setPreferredSize(new Dimension(100,100));
        footerPanel.setPreferredSize(new Dimension(100, 50));

        createAccountFrame.add(titlePanel, BorderLayout.NORTH);
        createAccountFrame.add(mainPanel, BorderLayout.CENTER);
        createAccountFrame.add(footerPanel, BorderLayout.SOUTH);

        //ImageIcon bankIcon = new ImageIcon("resources/images/bank of marmerica icon.png");
        ImageIcon bankIcon = new ImageIcon(getClass().getClassLoader().getResource("images/bank of marmerica icon.png"));
        createAccountFrame.setIconImage(bankIcon.getImage());

        createAccountFrame.setTitle("BoM - Create Account");
        createAccountFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == launchPageButton) {
            SoundPlayer.playSound("audio/click.wav");
            createAccountFrame.dispose();
            new LaunchPage();
        }
        if (e.getSource() == submitButton) {
            String accountName = accountNameTextField.getText();
            String startingBalanceText = startingBalanceTextField.getText();

            if (accountName != null && !accountName.equals("") && !BankAccount.accountExists(accountName)) {
                try {
                    double startingBalance = Double.parseDouble(startingBalanceText);
                    BankAccount myBankAccount = new BankAccount(accountName, startingBalance);

                    SoundPlayer.playSound("audio/click.wav");
                    confirmMsgLabel.setText("Account Created Successfully");

                } catch (NumberFormatException e2) {
                    SoundPlayer.playSound("audio/windowsxpError.wav");
                    confirmMsgLabel.setText("Error - Enter Valid Starting Balance");
                }
            } else {
                if (BankAccount.accountExists(accountName)) {
                    SoundPlayer.playSound("audio/windowsxpError.wav");
                    confirmMsgLabel.setText("Account with that name already exists");
                } else {
                    SoundPlayer.playSound("audio/windowsxpError.wav");
                    confirmMsgLabel.setText("Error - Account Name is required");
                }
            }

            confirmMsgLabel.setVisible(true);
            accountNameTextField.setText("");
            startingBalanceTextField.setText("");
            
        }
    }

}