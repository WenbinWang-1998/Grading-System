import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.util.List;

/**
 * logic for add a new course
 */
public class NewCourseOperation {
    NewCoursePage newCoursePage;
    List<String> course;
    JPanel mainPanel;

    public NewCourseOperation(JPanel prePanel,List<String> course){
        newCoursePage = new NewCoursePage();
        this.mainPanel = prePanel;
        this.course = course;
        mainPanel.removeAll();
        mainPanel.revalidate();
        mainPanel.repaint();
        mainPanel.add(newCoursePage,BorderLayout.CENTER);
        this.initAllOperation();
    }
    public void initAllOperation(){
        newCoursePage.getCreateButton().addActionListener(l->create());
        newCoursePage.getBackButton().addActionListener(l->back());
    }

    /**
     * create a new page
     */
    public void create(){
        String name = newCoursePage.getNameText().getText();
        String credit = newCoursePage.getCreditText().getText();
        String section = newCoursePage.getSectionText().getText();
        String year = newCoursePage.getYearText().getText();
        String semester = newCoursePage.getSemesterText().getText();
        if(name.equals("")||credit.equals("")||section.equals("")||year.equals("")||semester.equals("")){
            JOptionPane.showMessageDialog(newCoursePage,"Please Do Not Leave Empty Slots!","Warning",JOptionPane.WARNING_MESSAGE);
        }else if(!isNumeric(credit) || !isNumeric(year)){
            JOptionPane.showMessageDialog(newCoursePage,"Please Enter The Correct Format For Credit And Year!","Warning",JOptionPane.WARNING_MESSAGE);
        }else{
            String profId = DataBase.searchProfIdByCredential(LoginOperation.credentialId);
            course.add(name);
            DataBase.addCourse(name,Integer.valueOf(credit),section,year,semester,Integer.valueOf(profId));
            JOptionPane.showMessageDialog(newCoursePage,"Add New Course Successfully!","Message",JOptionPane.INFORMATION_MESSAGE);
            CoursePageOperation coursePageOperation = new CoursePageOperation(mainPanel,course);
        }
    }

    /**
     * go back
     */
    public void back(){
        CoursePageOperation coursePageOperation = new CoursePageOperation(mainPanel,course);
    }

    /**
     * determine whether input is a numeric
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        for(int i=0;i<str.length();i++){
            if(!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
