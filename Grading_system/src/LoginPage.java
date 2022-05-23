import javax.swing.*;
import java.awt.*;

/**
 * front-end for login
 */
public class LoginPage extends JPanel {
    JLabel userIDLabel;
    JTextField userIdText;

    JLabel pwdLabel;
    JPasswordField pwdText;

    JButton loginInButton;
    JButton cancelButton;

    /**
     * container for whole page
     */
    public LoginPage() {

        Dimension dim = new Dimension(250, 20);
        userIDLabel = new JLabel("User ID:",SwingConstants.CENTER);
        userIdText =new JTextField("cpk@bu.edu");
        userIdText.setPreferredSize(dim);

        pwdLabel = new JLabel("Password:",SwingConstants.CENTER);
        pwdText =new JPasswordField();
        pwdText.setPreferredSize(dim);

        loginInButton = new JButton("Login In");
        cancelButton = new JButton("Quit");


        Panel infoPanel = new Panel();
        Panel buttonPanel = new Panel();


        infoPanel.setLayout(new GridLayout(2, 2, 20,50));
        infoPanel.add(userIDLabel);
        infoPanel.add(userIdText);
        infoPanel.add(pwdLabel);
        infoPanel.add(pwdText);

        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(loginInButton);
        buttonPanel.add(cancelButton);




        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;   // dynamically extension

        this.add(infoPanel, gbc);
        this.add(buttonPanel, gbc);
    }

    /**
     * get login button
     * @return
     */
    public JButton getLoginInButton() {
        return loginInButton;
    }

    /**
     * get cancelling button
     * @return
     */
    public JButton getCancelButton() {
        return cancelButton;
    }

    /**
     * get user id text field
     * @return
     */
    public JTextField getUserIdText() {
        return userIdText;
    }

    /**
     * get password
     * @return
     */
    public JPasswordField getPwdText() {
        return pwdText;
    }
}

