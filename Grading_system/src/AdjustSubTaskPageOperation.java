import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;

/**
 * logic for subtask page
 */
public class AdjustSubTaskPageOperation {
    AdjustSubTaskPage adjustSubTaskPage;
    JPanel mainPanel;
    int originalRow;
    String courseName;
    String taskName;


    /**
     * adjust sub task
     * @param previousPanel
     * @param course
     * @param task
     * @throws SQLException
     */
    public AdjustSubTaskPageOperation(JPanel previousPanel, String course, String task) throws SQLException {
        adjustSubTaskPage = new AdjustSubTaskPage(course, task);
        this.mainPanel = previousPanel;
        mainPanel.removeAll(); // remove previous component and layout
        mainPanel.revalidate();  // follow by removeAll
        mainPanel.repaint();     // follow by revalidate
        mainPanel.add(adjustSubTaskPage, BorderLayout.CENTER);
        originalRow = adjustSubTaskPage.getSubTaskTableModel().getRowCount();
        courseName = course;
        taskName = task;
        this.initAllOperation();
    }


    /**
     * init all operations
     */
    public void initAllOperation(){
        adjustSubTaskPage.getAddButton().addActionListener(l -> addTask());
        adjustSubTaskPage.getUpdateButton().addActionListener(l -> updateTask());
        adjustSubTaskPage.getSaveButton().addActionListener(l -> saveAll());
        adjustSubTaskPage.getBackButton().addActionListener(l -> back());
    }

    /**
     * back operation
     */
    public void back(){
        try {
            AdjustTaskPageOperation ad = new AdjustTaskPageOperation(mainPanel, courseName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * add task operation
     */
    public void addTask(){
        DefaultTableModel table = adjustSubTaskPage.getSubTaskTableModel();
        System.out.println(table.getRowCount() + " "+ table.getColumnCount());

        JTextField t1 = adjustSubTaskPage.getSubTaskNameN();
        JTextField t2 = adjustSubTaskPage.getSubTaskWeightN();
        JTextField t3 = adjustSubTaskPage.getSm();
        JTextField t4 = adjustSubTaskPage.getSd();
        JTextField t5 = adjustSubTaskPage.getSy();
        JTextField t6 = adjustSubTaskPage.getEm();
        JTextField t7 = adjustSubTaskPage.getEd();
        JTextField t8 = adjustSubTaskPage.getEy();
        JTextField t9 = adjustSubTaskPage.getGroup();
        JTextField t10 = adjustSubTaskPage.getMaxBonus();

        String start = t3.getText()+"/"+t4.getText()+"/"+t5.getText();
        String end = t6.getText()+"/"+t7.getText()+"/"+t8.getText();

        table.addRow(new Object[]{taskName, t1.getText(), t2.getText(), start, end, t9.getText(), t10.getText()});
        t1.setText("");
        t2.setText("");
        t3.setText("");
        t4.setText("");
        t5.setText("");
        t6.setText("");
        t7.setText("");
        t8.setText("");
        t9.setText("");
        t10.setText("");
    }

    /**
     * update task operation
     */
    public void updateTask(){
        DefaultTableModel model = adjustSubTaskPage.getSubTaskTableModel();
        JTable table = adjustSubTaskPage.getSubTaskTable();
        JTextField t = adjustSubTaskPage.getSubTaskWeightU();
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
     * save all operation
     */
    public void saveAll(){
        DefaultTableModel model = adjustSubTaskPage.getSubTaskTableModel();
        JTable table = adjustSubTaskPage.getSubTaskTable();
        for(int i = 0; i < originalRow; i++){
            String s1 = (String) model.getValueAt(i, 0);
            String s2 = (String) model.getValueAt(i, 1);
            String s3 = (String) model.getValueAt(i, 2);
            DataBase.updateSubTaskPage(s1, s2, s3, courseName);
        }

        for (int i = originalRow; i < model.getRowCount(); i++){
            String s1 = (String) model.getValueAt(i, 0);
            String s2 = (String) model.getValueAt(i, 1);
            Double s3 = Double.valueOf((String) model.getValueAt(i, 2));
            String s4 = (String) model.getValueAt(i, 3);
            String s5 = (String) model.getValueAt(i, 4);
            Integer s6 = Integer.valueOf((String) model.getValueAt(i, 5));
            Float s7 = Float.valueOf((String) model.getValueAt(i, 6));
            DataBase.addSubTaskFromTaskPage(s1, s3, s2, s4, s5, s6, s7, courseName);
        }
    }
}
