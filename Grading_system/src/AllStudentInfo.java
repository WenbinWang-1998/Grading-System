import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
/**
 * front-end for student information page
 */
public class AllStudentInfo extends JPanel{
    private JLabel courseLabel;
    private JLabel stuTableLabel;
    private DefaultTableModel studentTableModel;
    private JTable studentTable;
    private JScrollPane scrollPane;
    private JButton searchByBUId;
    private JTextField idText;

    private JButton searchByName;
    private JTextField nameText;

    private JButton searchByEmail;
    private JTextField emailText;

    private JButton sortByAscending;
    private JButton sortByDescending;
    private JButton backButton;

    private JButton adjustWeight;
    private JButton addStudent;
    private JButton importStudent;

    private JButton changeGrade;


    private JButton homePage;

    public AllStudentInfo(String courseName){
        courseLabel = new JLabel("Welcome to the course "+courseName,SwingConstants.CENTER);
        courseLabel.setFont(new Font("Times New Roman",Font.BOLD,60));
        stuTableLabel = new JLabel("ALL STUDENT INFO SHOWN BELOW: ",SwingConstants.CENTER);
        stuTableLabel.setFont(new Font("Times New Roman",Font.PLAIN,25));
        studentTableModel = new DefaultTableModel();
        studentTable = new JTable(studentTableModel);
        scrollPane = new JScrollPane(studentTable);
        studentTable.setFillsViewportHeight(true);
        studentTable.setBackground(Color.LIGHT_GRAY);
        homePage = new JButton("Back To Home");
        homePage.setPreferredSize(new Dimension(200,30));
        searchByBUId = new JButton("Search By BU Id:");
        idText = new JTextField("");
        searchByName = new JButton("Search By Student Name:");
        nameText = new JTextField("");
        searchByEmail = new JButton("Search By Email:");
        emailText = new JTextField("");

        sortByAscending = new JButton("Sort in Ascending Order");
        sortByDescending = new JButton("Sort in Descending Order");
        backButton = new JButton("Back to Table For All Student");

        // add panels
        JPanel searchPanel = new JPanel(new GridLayout(1,6,10,10));
        searchPanel.add(idText);
        searchPanel.add(searchByBUId);
        searchPanel.add(nameText);
        searchPanel.add(searchByName);
        searchPanel.add(emailText);
        searchPanel.add(searchByEmail);

        JPanel buttonPanel = new JPanel(new GridLayout(1,3,10,10));
        buttonPanel.add(sortByAscending);
        buttonPanel.add(sortByDescending);
        buttonPanel.add(backButton);

        addStudent = new JButton("Add New Student");
        importStudent = new JButton("Import Student From file");
        adjustWeight = new JButton("Adjust Weight For Tasks");
        changeGrade = new JButton("Update grade");
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
        controlPanel.add(addStudent);
        controlPanel.add(importStudent);
        controlPanel.add(adjustWeight);
        controlPanel.add(changeGrade);

        // set layout
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        this.add(courseLabel,gbc);
        this.add(stuTableLabel,gbc);
        this.add(searchPanel,gbc);
        this.add(buttonPanel,gbc);
        this.add(scrollPane,gbc);
        this.add(controlPanel,gbc);


        gbc.anchor = GridBagConstraints.LAST_LINE_END;
        gbc.fill = GridBagConstraints.NONE;
        this.add(homePage,gbc);

    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public JTable getStudentTable() {
        return studentTable;
    }

    public void setStudentTableModel(DefaultTableModel studentTableModel) {
        this.studentTableModel = studentTableModel;
    }

    public DefaultTableModel getStudentTableModel() {
        return studentTableModel;
    }

    public JButton getSearchByBUId() {
        return searchByBUId;
    }

    public JButton getSearchByEmail() {
        return searchByEmail;
    }

    public JButton getSearchByName() {
        return searchByName;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getHomePage() {
        return homePage;
    }

    public JButton getSortByAscending() {
        return sortByAscending;
    }

    public JButton getSortByDescending() {
        return sortByDescending;
    }

    public JButton getAddStudent() {
        return addStudent;
    }

    public JButton getAdjustWeight() {
        return adjustWeight;
    }

    public JTextField getNameText() {
        return nameText;
    }

    public JTextField getIdText() {
        return idText;
    }

    public JTextField getEmailText() {
        return emailText;
    }

    public JButton getImportStudent() {
        return importStudent;
    }

    public JButton getChangeGrade() {
        return changeGrade;
    }
}
