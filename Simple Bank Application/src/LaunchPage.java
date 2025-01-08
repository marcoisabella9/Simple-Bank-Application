import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

// implements ActionListener to allow for button actions
public class LaunchPage implements ActionListener {

    myFrame launchFrame = new myFrame();

    JLabel titleLabel = new JLabel("Bank of Marmerica");
    JLabel footerLabel = new JLabel("Created By Marco Isabella");

    JPanel mainPanel = new JPanel();
    JPanel titlePanel = new JPanel();
    JPanel footerPanel = new JPanel();

    JButton createAccountButton = new JButton("Create Account");
    JButton editAccountButton = new JButton("View/Edit Account");

    LaunchPage() {

        createAccountButton.setBounds(75, 90, 150, 50);
        createAccountButton.setFocusable(false);
        createAccountButton.addActionListener(this);

        editAccountButton.setBounds(75, 180, 150, 50);
        editAccountButton.setFocusable(false);
        editAccountButton.addActionListener(this);

        titleLabel.setFont(new Font(null, Font.BOLD, 20));
        titleLabel.setVerticalAlignment(JLabel.CENTER);

        footerLabel.setFont(new Font(null, Font.PLAIN, 10));
        footerLabel.setVerticalAlignment(JLabel.CENTER);

        titlePanel.setBackground(new Color(255, 209, 181));
        titlePanel.add(titleLabel);

        mainPanel.setBackground(new Color(255, 209, 181));
        mainPanel.setLayout(null); // layout of main panel is null, so we can place elements ourselves
        mainPanel.add(createAccountButton);
        mainPanel.add(editAccountButton);

        footerPanel.setBackground(new Color(255, 209, 181));
        footerPanel.add(footerLabel);

        titlePanel.setPreferredSize(new Dimension(100,50));
        mainPanel.setPreferredSize(new Dimension(100,100));
        footerPanel.setPreferredSize(new Dimension(100,50));

        launchFrame.add(titlePanel, BorderLayout.NORTH);
        launchFrame.add(mainPanel, BorderLayout.CENTER);
        launchFrame.add(footerPanel, BorderLayout.SOUTH);

        // Creating and using bankIcon
        ImageIcon bankIcon = new ImageIcon("resources/images/bank of marmerica icon.png");
        launchFrame.setIconImage(bankIcon.getImage());

        File file = new File("resources/images/bank of marmerica icon.png");
        System.out.println(file.exists() ? "Image Found" : "Image not found");

        launchFrame.setTitle("Bank of Marmerica");
        launchFrame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createAccountButton) {
            SoundPlayer.playSound("resources/audio/click.wav");
            launchFrame.dispose();
            new CreateAccount();
        }
        if (e.getSource() == editAccountButton) {
            SoundPlayer.playSound("resources/audio/click.wav");
            launchFrame.dispose();
            new EditAccount();
        }
    }
}
