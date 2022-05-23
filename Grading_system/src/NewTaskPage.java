import javax.swing.*;
import java.awt.*;

/**
 * front-end for task page
 */
public class NewTaskPage {
    private JLabel nameLabel;
    private JLabel courseIdLabel;
    private JLabel weightLabel;
    private JTextField nameText;
    private JTextField courseIdText;
    private JTextField weightText;

    private JButton addButton;
    private JButton quitButton;

    /**
     * container for new task page
     * @param jf
     * @param com
     */
    public NewTaskPage(JFrame jf,Component com){
        JDialog dialog = new JDialog(jf,"New Task",true);
        dialog.setSize(500,300);
        dialog.setLocationRelativeTo(com);
        nameLabel = new JLabel("Task Name: ");
        courseIdLabel = new JLabel("Course ID: ");
        weightLabel = new JLabel("Task Weight: ");

        nameText = new JTextField("");
        courseIdText = new JTextField("");
        weightText = new JTextField("");

        JPanel infoPanel = new JPanel(new GridLayout(3,2,10,10));
        infoPanel.add(nameLabel);
        infoPanel.add(nameText);
        infoPanel.add(courseIdLabel);
        infoPanel.add(courseIdText);
        infoPanel.add(weightLabel);
        infoPanel.add(weightText);

        addButton = new JButton("Add Task");
        quitButton = new JButton("Quit");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
        buttonPanel.add(addButton);
        buttonPanel.add(quitButton);


        dialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        dialog.add(infoPanel,gbc);
        dialog.add(buttonPanel,gbc);
        dialog.setVisible(true);
    }
}
