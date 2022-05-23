import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * front-end for adjustment task page
 */
public class AdjustTaskPage extends JPanel{
    private JLabel taskLabel;
    private DefaultTableModel taskTableModel;
    private JTable taskTable;
    private JScrollPane scrollPane;

    private JTextField taskName;
    private JButton adjustSubTaskButton;


    private JButton addButton;
    private JTextField courseNameN;
    private JTextField hwNameN;
    private JTextField hwWeightN;


    private JButton updateButton;
    private JTextField hwWeightU;


    private JButton saveButton;
//    private JButton cancelButton;
    private JButton backButton;
    private Object[][] data;

    private String courseName;

    /**
     * container for adjust task page
     * @param courseName
     */
    public AdjustTaskPage(String courseName){
        this.courseName = courseName;
        DataBase.linkToDatabase();
        setBounds(0, 0, 1554, 1280);

        taskLabel = new JLabel("Table For Main Tasks Below: ",SwingConstants.CENTER);
        taskLabel.setFont(new Font("Times New Roman",Font.PLAIN,25));
        String[] columns = new String[]{"CourseName","HwName","HwWeight"};

        ResultSet resultSet = DataBase.selectTaskPage(courseName);

        int rowSize = 0;
        int columnSize = 0;

        try {
            resultSet.last();
            rowSize = resultSet.getRow();
            resultSet.beforeFirst();
            columnSize = resultSet.getMetaData().getColumnCount();
            data = new String[rowSize][columnSize];
            int i = 0;
            while(resultSet.next() && i < rowSize)
            {
                for(int j=0;j<columnSize;j++){
                    data[i][j] = resultSet.getString(j+1);
                }
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        taskTableModel = new DefaultTableModel(data,columns);
        taskTable = new JTable(taskTableModel);
        scrollPane = new JScrollPane(taskTable);

        addButton = new JButton("Add New Task");

        courseNameN = new JTextField("Course");
        hwNameN = new JTextField("Task name");
        hwWeightN = new JTextField("Task weight");

        updateButton = new JButton("Update weight");
        hwWeightU = new JTextField("");


        saveButton = new JButton("Save all changes");
        backButton = new JButton("Back");
        adjustSubTaskButton = new JButton("Adjust SubTask Weight");
        taskName = new JTextField("");


        JPanel adjustPanel = new JPanel(new GridLayout(1,2,10,10));
        adjustPanel.add(taskName);
        adjustPanel.add(adjustSubTaskButton);

//        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
        JPanel controlPanel = new JPanel(new GridLayout(1,3,10,50));
//        controlPanel.add(courseNameN);
        controlPanel.add(hwNameN);
        controlPanel.add(hwWeightN);
        controlPanel.add(addButton);

        JPanel updatePanel = new JPanel(new GridLayout(1,2,10,50));
        updatePanel.add(hwWeightU);
        updatePanel.add(updateButton);


        // set layout
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // add panels
        this.add(taskLabel,gbc);
        this.add(adjustPanel,gbc);
        this.add(scrollPane,gbc);
        this.add(controlPanel,gbc);

        this.add(updatePanel,gbc);

        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LAST_LINE_END;

        this.add(saveButton, gbc);
        this.add(backButton,gbc);
        setVisible(true);
    }

    public JButton getAdjustSubTaskButton() {
        return adjustSubTaskButton;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public DefaultTableModel getTaskTableModel() {
        return taskTableModel;
    }

    public JTextField getTaskName() {
        return taskName;
    }

    public JTextField getCourseNameN() {
        return courseNameN;
    }

    public JTextField getHwNameN() {
        return hwNameN;
    }

    public JTextField getHwWeightN() {
        return hwWeightN;
    }

    public JTextField getHwWeightU() {
        return hwWeightU;
    }

    public JTable getTaskTable() {
        return taskTable;
    }

    public JButton getSaveButton() {
        return saveButton;
    }
}
