import javax.swing.JFrame;
import java.awt.*;

public class myFrame extends JFrame {
    myFrame() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exits app when closing
        this.setSize(315,500);
        this.setLocationRelativeTo(null); // window opens in center of screen
        this.setLayout(new BorderLayout()); // default frame layout will be BorderLayout
        this.setResizable(false);

    }
}
