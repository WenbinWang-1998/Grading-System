import javax.swing.*;
import java.awt.*;

/**
 *  front-end for adding a new student page
 */
public class NewStudentPage extends JPanel{
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JLabel BUIDLabel;
    private JTextField nameText;
    private JTextField emailText;
    private JTextField BUIDText;

    private JButton addButton;
    private JButton quitButton;
    private JButton homeButton;

    /**
     * container for new students page
     */
    public NewStudentPage() {
        JLabel firstLabel = new JLabel("Add a Student", SwingConstants.CENTER);
        firstLabel.setFont(new Font("Times New Roman",Font.PLAIN,25));
        addButton = new JButton("Add New Student");
        quitButton = new JButton("Quit");
        homeButton = new JButton("Back to Home");

        nameLabel = new JLabel("Student Name: ");
        emailLabel = new JLabel("Student Email: ");
        BUIDLabel = new JLabel("BU ID: ");
        nameText =new JTextField("");
        emailText =new JTextField("");
        BUIDText =new JTextField("");

        Panel infoPanel = new Panel();
        Panel buttonPanel = new Panel();

        infoPanel.setLayout(new GridLayout(3, 2, 10,10));
        infoPanel.add(nameLabel);
        infoPanel.add(nameText);
        infoPanel.add(emailLabel);
        infoPanel.add(emailText);
        infoPanel.add(BUIDLabel);
        infoPanel.add(BUIDText);

        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(addButton);
        buttonPanel.add(quitButton);
        buttonPanel.add(homeButton);



        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;


        this.add(infoPanel, gbc);
        this.add(buttonPanel, gbc);

    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getQuitButton() {
        return quitButton;
    }

    public JButton getHomeButton() {
        return homeButton;
    }

    public JTextField getEmailText() {
        return emailText;
    }

    public JTextField getNameText() {
        return nameText;
    }

    public JTextField getBUIDText() {
        return BUIDText;
    }
}