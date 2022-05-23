import javax.swing.*;
import java.awt.*;
import java.util.List;


/**
 * front-end for course page
 */
public class CoursePage extends JPanel{

    private JButton createButton;
    private JButton deleteButton;
    private JButton logoutButton;
    private JList courseList;
    DefaultListModel<String> dlm;
    public CoursePage(List<String> course) {
        JLabel welcomeLabel = new JLabel("The Active Courses shown below:", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Times New Roman",Font.PLAIN,25));

        dlm = new DefaultListModel<>();
        setCourseList(course);
        courseList = new JList(dlm);
        courseList.setFont(new Font("Times New Roman",Font.BOLD,30));
        courseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane jScrollPane = new JScrollPane(courseList);
        jScrollPane.setPreferredSize(new Dimension(1000, 500));

        createButton = new JButton("Create Course");
        deleteButton = new JButton("Delete Course");
        deleteButton.setVisible(false);
        logoutButton = new JButton("Log Out");

        Panel courseListPanel = new Panel();
        Panel buttonPanel = new Panel();

        courseListPanel.add(jScrollPane);

        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(createButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(logoutButton);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        this.add(welcomeLabel, gbc);
        this.add(jScrollPane, gbc);
        this.add(buttonPanel, gbc);
    }

    public JButton getCreateButton() {
        return createButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getLogoutButton() {
        return logoutButton;
    }

    public JList getCourseList() {
        return courseList;
    }

    public void setCourseList(List<String> course) {
        for(String str:course){
            dlm.addElement(str);
        }
    }

    public DefaultListModel<String> getDlm() {
        return dlm;
    }
}
