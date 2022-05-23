import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 *  logic for adding a new course page
 */
public class NewStudentPageOp {
    NewStudentPage newStudentPage;
    JPanel mainPanel;
    String courseName;
    public NewStudentPageOp(JPanel prePanel,String courseName){
        newStudentPage = new NewStudentPage();
        this.mainPanel = prePanel;
        this.courseName = courseName;
        mainPanel.removeAll();
        mainPanel.revalidate();
        mainPanel.repaint();
        mainPanel.add(newStudentPage,BorderLayout.CENTER);
        this.initAllOperation();
    }

    /**
     * init all operations
     */
    public void initAllOperation(){
        newStudentPage.getAddButton().addActionListener(l-> addNewStudent());
        newStudentPage.getQuitButton().addActionListener(l-> quit());
        newStudentPage.getHomeButton().addActionListener(l->backToHome());
    }

    /**
     * quit this page
     */
    public void quit(){
        AllStudentInfoOpera allStudentInfoOpera = new AllStudentInfoOpera(mainPanel,courseName);
    }

    /**
     * back to home
     */
    public void backToHome(){
        List<String> course = DataBase.searchForCourse(LoginOperation.credentialId);
        CoursePageOperation coursePageOperation = new CoursePageOperation(mainPanel,course);
    }

    /**
     * add a new student
     */
    public void addNewStudent(){
        String name = newStudentPage.getNameText().getText();
        String email = newStudentPage.getEmailText().getText();
        String buId = newStudentPage.getBUIDText().getText();
        if(name.length()==0||email.length()==0||buId.length()==0){
            JOptionPane.showMessageDialog(newStudentPage,"Please Do Not Leave Empty Slots!","Warning",JOptionPane.WARNING_MESSAGE);
        }else{
            DataBase.addStudent(name,email,buId,false);

            String courseId = DataBase.searchForCourseId(courseName);
            List<String> studentIdList = DataBase.searchStudentByBuId(buId);
            String stuId = studentIdList.get(0);
            DataBase.addEnrollment(Integer.valueOf(stuId),Integer.valueOf(courseId));

            List<String> taskList = DataBase.searchTaskByCourseId(courseId);
            for(String task:taskList){
                String[] taskArr = task.split(" ");
                String taskId=  taskArr[0];
                List<String> subTaskIdList = DataBase.searchSubTaskIdByTask(taskId);
                for(String subTaskId:subTaskIdList){
                    DataBase.addGrade(Integer.valueOf(subTaskId),Integer.valueOf(stuId),0);
                }
            }
            JOptionPane.showMessageDialog(newStudentPage,"Add New Student Successfully!","Message",JOptionPane.INFORMATION_MESSAGE);
            AllStudentInfoOpera allStudentInfoOpera = new AllStudentInfoOpera(mainPanel,courseName);
        }
    }
}
