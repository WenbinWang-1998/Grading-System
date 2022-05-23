import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * logic for course page
 */
public class CoursePageOperation {
    CoursePage coursePage;
    JPanel mainPanel;

    public CoursePageOperation(JPanel prePanel, List<String> course){
        coursePage = new CoursePage(course);
        mainPanel = prePanel;

        mainPanel.removeAll();
        mainPanel.revalidate();
        mainPanel.repaint();
        mainPanel.add(coursePage,BorderLayout.CENTER);
        this.initAllOperation(course);
    }

    /**
     * init operations
     * @param course
     */
    public void initAllOperation(List<String> course){
        coursePage.getCreateButton().addActionListener(l -> createNewCourse(course));
        coursePage.getDeleteButton().addActionListener(l -> deleteCurCourse());
        coursePage.getLogoutButton().addActionListener(l -> logout());

        coursePage.getCourseList().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JList courseList = (JList)e.getSource();
                int index = courseList.getSelectedIndex();
                if(index>=0){
                    coursePage.getDeleteButton().setVisible(true);
                    if(e.getClickCount()==2){
                        Object obj = courseList.getModel().getElementAt(index);
                        AllStudentInfoOpera allStudent = new AllStudentInfoOpera(mainPanel,obj.toString());
                    }
                }
            }
        });
    }

    /**
     * logout
     */
    public void logout(){
        LoginOperation loginOperation = new LoginOperation(mainPanel);
    }

    /**
     * create a new course
     * @param course
     */
    public void createNewCourse(List<String> course){
        if(LoginOperation.authority.equals("1")){
            NewCourseOperation newCourseOp = new NewCourseOperation(mainPanel, course);
        }else{
            JOptionPane.showMessageDialog(coursePage,"Sorry, you do not have this right!","Operation Error",JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * delete current course
     */
    public void deleteCurCourse(){
        if(LoginOperation.authority.equals("1")) {
            int op = JOptionPane.showConfirmDialog(coursePage, "Do you want delete this course?", "Delete Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (op == 0) {
                DefaultListModel listModel = coursePage.getDlm();
                JList courseList = coursePage.getCourseList();
                int index = coursePage.getCourseList().getSelectedIndex();
                Object courseName = courseList.getModel().getElementAt(index);
                listModel.removeElementAt(index);
                DataBase.deleteCourseByName(courseName.toString());
                coursePage.getDeleteButton().setVisible(false);
            }
        }else{
            JOptionPane.showMessageDialog(coursePage,"Sorry, you do not have this right!","Operation Error",JOptionPane.WARNING_MESSAGE);
        }
    }
}
