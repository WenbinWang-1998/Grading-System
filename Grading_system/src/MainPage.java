import java.awt.*;
import javax.swing.*;

/**
 * front-end for the main page
 */
public class MainPage extends JFrame{
    JPanel headerPanel;
    JPanel parentPanel;

    /**
     * container of main page
     */
    public MainPage() {
        headerPanel = new JPanel();
        parentPanel = new JPanel(new GridLayout());
        Dimension headerDimension = new Dimension(1000, 30);
        JLabel label = new JLabel("Welcome to Grading System ");
        headerPanel.setPreferredSize(headerDimension);
        headerPanel.setBackground(Color.lightGray);
        headerPanel.add(label);
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(parentPanel, BorderLayout.CENTER);
        this.setBounds(250, 150, 1440, 1280);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * get head of the main page
     * @return
     */
    public JPanel getHeaderPanel() {
        return headerPanel;
    }

    /**
     * get parent panel
     * @return
     */
    public JPanel getParentPanel() {
        return parentPanel;
    }
}
