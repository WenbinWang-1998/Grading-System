import javax.swing.*;
import java.awt.*;
import java.util.jar.JarEntry;

/**
 *  front-end for adding a new course page
 */
public class NewCoursePage extends JPanel {

    private JButton createButton;
    private JButton backButton;
    private JTextField nameText;
    private JTextField creditText;
    private JTextField sectionText;
    private JTextField yearText;
    private JTextField semesterText;
    //private JComboBox templateList;

    /**
     * container for new course page
     */
    public NewCoursePage() {

        JLabel label = new JLabel("Please Type in Course Info Below:", SwingConstants.CENTER);
        label.setFont(new Font("", Font.PLAIN, 30));
        createButton = new JButton("Create Course");
        backButton = new JButton("Back to previous Page");

        JLabel nameLabel = new JLabel("Course Name: ");
        JLabel creditLabel = new JLabel("Credit: ");
        JLabel sectionLabel = new JLabel("Section: ");
        JLabel yearLabel = new JLabel("Year: ");
        JLabel semesterLabel = new JLabel("Semester: ");
        nameText =new JTextField();
        creditText = new JTextField();
        sectionText = new JTextField();
        yearText =new JTextField();
        semesterText =new JTextField();

        Panel infoPanel = new Panel(new GridLayout(5, 2, 10,10));
        Panel buttonPanel = new Panel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        //infoPanel.add(templateLabel);
        infoPanel.add(nameLabel);
        infoPanel.add(nameText);

        infoPanel.add(creditLabel);
        infoPanel.add(creditText);

        infoPanel.add(sectionLabel);
        infoPanel.add(sectionText);

        infoPanel.add(yearLabel);
        infoPanel.add(yearText);
        infoPanel.add(semesterLabel);
        infoPanel.add(semesterText);

        buttonPanel.add(createButton);
        buttonPanel.add(backButton);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        this.add(label, gbc);
        this.add(infoPanel, gbc);
        this.add(buttonPanel, gbc);
    }

    public JButton getCreateButton() {
        return createButton;
    }

    public JTextField getNameText() {
        return nameText;
    }
    public JTextField getCreditText() {
        return creditText;
    }
    public JTextField getSectionText() {
        return sectionText;
    }
    public JTextField getYearText() {
        return yearText;
    }
    public JTextField getSemesterText() {
        return semesterText;
    }

    public JButton getBackButton() {
        return backButton;
    }
}