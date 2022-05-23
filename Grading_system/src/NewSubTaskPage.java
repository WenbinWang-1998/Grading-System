import javax.swing.*;
import java.awt.*;

/**
 *  front-end for adding a new sub task page
 */
public class NewSubTaskPage {
    private JLabel nameLabel;
    private JLabel taskIdLabel;
    private JLabel weightLabel;
    private JLabel releaseLabel;
    private JLabel dueLabel;
    private JLabel bonusLabel;

    private JTextField nameText;
    private JTextField taskIdText;
    private JTextField weightText;
    private JTextField releaseText;
    private JTextField dueText;
    private JTextField bonusText;

    private JButton addButton;
    private JButton quitButton;

    /**
     *  container for new sub task page
     */
    public NewSubTaskPage(JFrame jf){
        JDialog dialog = new JDialog(jf,"New Sub Task",true);
        dialog.setSize(500,600);
        dialog.setLocationRelativeTo(jf);
        nameLabel = new JLabel("SubTask Name: ");
        taskIdLabel = new JLabel("Task ID: ");
        weightLabel = new JLabel("SubTask Weight: ");
        releaseLabel = new JLabel("Released Date(mm/dd/yy): ");
        dueLabel = new JLabel("Due Date(mm/dd/yy): ");
        bonusLabel = new JLabel("Max Bonus: ");

        nameText = new JTextField("");
        taskIdText = new JTextField("");
        weightText = new JTextField("");
        releaseText = new JTextField("");
        dueText = new JTextField("");
        bonusText = new JTextField("");
        JPanel infoPanel = new JPanel(new GridLayout(6,2,10,10));
        infoPanel.add(nameLabel);
        infoPanel.add(nameText);
        infoPanel.add(taskIdLabel);
        infoPanel.add(taskIdText);
        infoPanel.add(weightLabel);
        infoPanel.add(weightText);
        infoPanel.add(releaseLabel);
        infoPanel.add(releaseText);
        infoPanel.add(dueLabel);
        infoPanel.add(dueText);
        infoPanel.add(bonusLabel);
        infoPanel.add(bonusText);

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

