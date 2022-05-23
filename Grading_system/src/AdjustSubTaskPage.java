import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * front-end for subtask page
 */
public class AdjustSubTaskPage extends JPanel{
    private String courseName;
    private String taskName;


    private JLabel subTaskLabel;
    private DefaultTableModel subTaskTableModel;
    private JTable subTaskTable;
    private JScrollPane scrollPane;

    private JTextField subTaskNameN;
    private JTextField subTaskWeightN;

    private JLabel s;
    private JTextField sm;
    private JTextField sd;
    private JTextField sy;

    private JLabel e;
    private JTextField em;
    private JTextField ed;


    private JTextField ey;

    private JButton addButton;
    private JTextField group;
    private JTextField maxBonus;

    private JButton updateButton;
    private JTextField subTaskWeightU;


    private JButton saveButton;
    private JButton backButton;
    private Object[][] data;

    /**
     * container for adjustment for subtask page
     * @param courseName
     * @param taskName
     */
    public AdjustSubTaskPage(String courseName, String taskName){
        DataBase.linkToDatabase();
        setBounds(0, 0, 1554, 1280);

        this.courseName = courseName;
        this.taskName = taskName;

        subTaskLabel = new JLabel("SubTask Table For selected Task Below: ",SwingConstants.CENTER);
        subTaskLabel.setFont(new Font("Times New Roman",Font.PLAIN,25));
        String[] columns = new String[]{"Name","Subtask","Weight","start","due", "group", "maxBonus"};

        ResultSet resultSet = DataBase.selectSubTaskPage(courseName, taskName);

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

        subTaskTableModel = new DefaultTableModel(data,columns);
        subTaskTable = new JTable(subTaskTableModel);
        scrollPane = new JScrollPane(subTaskTable);

        subTaskNameN = new JTextField("Subtask name");
        subTaskWeightN = new JTextField("Subtask weight");

        s = new JLabel("Start date:");
        sm = new JTextField("MM");
        sd = new JTextField("DD");
        sy = new JTextField("YYYY");

        e = new JLabel("Due date:");
        em = new JTextField("MM");
        ed = new JTextField("DD");
        ey = new JTextField("YYYY");

        addButton = new JButton("Add New Subtask");
        group = new JTextField("Group: 0/1");
        maxBonus = new JTextField("Max Bonus");

        updateButton = new JButton("Update weight");
        subTaskWeightU = new JTextField("");


        saveButton = new JButton("Save all changes");
        backButton = new JButton("Back to Home");



//        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
        JPanel controlPanel = new JPanel(new GridLayout(1,2,10,10));

//        controlPanel.add(courseNameN);
        controlPanel.add(subTaskNameN);
        controlPanel.add(subTaskWeightN);
//        controlPanel.add(addButton);

        JPanel startDPanel = new JPanel(new GridLayout(1,3,10,10));
        startDPanel.add(s);
        startDPanel.add(sm);
        startDPanel.add(sd);
        startDPanel.add(sy);

        JPanel endDPanel = new JPanel(new GridLayout(1,3,10,10));
        endDPanel.add(e);
        endDPanel.add(em);
        endDPanel.add(ed);
        endDPanel.add(ey);

        JPanel controlSubmitPanel = new JPanel(new GridLayout(1,3,10,10));
        controlSubmitPanel.add(group);
        controlSubmitPanel.add(maxBonus);
        controlSubmitPanel.add(addButton);


        JPanel updatePanel = new JPanel(new GridLayout(1,2,10,10));
        updatePanel.add(subTaskWeightU);
        updatePanel.add(updateButton);


        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;


        this.add(scrollPane,gbc);
        this.add(controlPanel,gbc);

        this.add(startDPanel, gbc);
        this.add(endDPanel, gbc);
        this.add(controlSubmitPanel, gbc);
        this.add(updatePanel,gbc);

        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LAST_LINE_END;

        this.add(saveButton, gbc);
        this.add(backButton,gbc);
        setVisible(true);
    }

    public JButton getAddButton() {
        return addButton;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getTaskName() {
        return taskName;
    }

    public JLabel getSubTaskLabel() {
        return subTaskLabel;
    }

    public DefaultTableModel getSubTaskTableModel() {
        return subTaskTableModel;
    }

    public JTable getSubTaskTable() {
        return subTaskTable;
    }

    public JTextField getSubTaskNameN() {
        return subTaskNameN;
    }

    public JTextField getSubTaskWeightN() {
        return subTaskWeightN;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JTextField getSubTaskWeightU() {
        return subTaskWeightU;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JLabel getS() {
        return s;
    }

    public JTextField getSm() {
        return sm;
    }

    public JTextField getSd() {
        return sd;
    }

    public JTextField getSy() {
        return sy;
    }

    public JLabel getE() {
        return e;
    }

    public JTextField getEm() {
        return em;
    }

    public JTextField getEd() {
        return ed;
    }

    public JTextField getEy() {
        return ey;
    }

    public JTextField getGroup() {
        return group;
    }

    public JTextField getMaxBonus() {
        return maxBonus;
    }

}
