import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.sql.SQLException;

/**
 * logic for adjustment task page
 */
public class AdjustTaskPageOperation {
    AdjustTaskPage adjustTaskPage;
    JPanel mainPanel;
    int originalRow;
    String courseName;
    String taskName;


    /**
     * operation of adjust task page
     * @param previousPanel
     * @param course
     * @throws SQLException
     */
    public AdjustTaskPageOperation(JPanel previousPanel, String course) throws SQLException {

        adjustTaskPage = new AdjustTaskPage(course);
        taskName = adjustTaskPage.getTaskName().getText();

        this.mainPanel = previousPanel;
        mainPanel.removeAll(); // remove previous component and layout
        mainPanel.revalidate();  // follow by removeAll
        mainPanel.repaint();     // follow by revalidate
        mainPanel.add(adjustTaskPage, BorderLayout.CENTER);
        originalRow = adjustTaskPage.getTaskTableModel().getRowCount();
        courseName = course;
        this.initAllOperation();
    }


    /**
     * init operation
     */
    public void initAllOperation(){
        adjustTaskPage.getAddButton().addActionListener(l -> addTask());
        adjustTaskPage.getUpdateButton().addActionListener(l -> updateTask());
        adjustTaskPage.getSaveButton().addActionListener(l -> saveAll());
        adjustTaskPage.getAdjustSubTaskButton().addActionListener(l -> adjustSub());
        adjustTaskPage.getBackButton().addActionListener(l->backToPreTable());
    }

    /**
     * add sub task
     */
    public void adjustSub(){
        try {
            AdjustSubTaskPageOperation ad = new AdjustSubTaskPageOperation(mainPanel, courseName, adjustTaskPage.getTaskName().getText());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * add task
     */
    public void addTask(){
        DefaultTableModel table = adjustTaskPage.getTaskTableModel();
        JTextField t1 = adjustTaskPage.getCourseNameN();
        JTextField t2 = adjustTaskPage.getHwNameN();
        JTextField t3 = adjustTaskPage.getHwWeightN();
        table.addRow(new Object[]{courseName, t2.getText(), t3.getText()});
        t1.setText("");
        t2.setText("");
        t3.setText("");
    }

    /**
     * update task
     */
    public void updateTask(){
        DefaultTableModel model = adjustTaskPage.getTaskTableModel();
        JTable table = adjustTaskPage.getTaskTable();
        JTextField t = adjustTaskPage.getHwWeightU();
        int i = table.getSelectedRow();
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                t.setText((String)model.getValueAt(i, 2));
            }
        });
        model.setValueAt(t.getText(), i, 2);
    }

    /**
     * save all
     */
    public void saveAll(){
        DefaultTableModel model = adjustTaskPage.getTaskTableModel();
        JTable table = adjustTaskPage.getTaskTable();
        for(int i = 0; i < originalRow; i++){
            String s1 = (String) model.getValueAt(i, 0);
            String s2 = (String) model.getValueAt(i, 1);
            String s3 = (String) model.getValueAt(i, 2);
            DataBase.updateTaskPage(s1, s2, s3);
        }

        for (int i = originalRow; i < model.getRowCount(); i++){
            String s1 = (String) model.getValueAt(i, 0);
            String s2 = (String) model.getValueAt(i, 1);
            Double s3 = Double.valueOf((String) model.getValueAt(i, 2));
            DataBase.addTaskFromTaskPage(s2, s1, s3);
        }
    }

    /**
     * go back operation
     */
    public void backToPreTable(){
        AllStudentInfoOpera allStudentInfoOpera = new AllStudentInfoOpera(mainPanel,courseName);
    }
}
