
/**
 * logic for student information page
 */
import com.mysql.cj.xdevapi.Table;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.TableView;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * logic of dealing with students information
 */
public class AllStudentInfoOpera {
    AllStudentInfo allStudentInfo;
    JPanel mainPanel;
    String courseName;


    /**
     * register operations
     * @param prePanel
     * @param courseName
     */
    public AllStudentInfoOpera(JPanel prePanel,String courseName){
        this.courseName = courseName;
        allStudentInfo = new AllStudentInfo(courseName);
        mainPanel = prePanel;
        mainPanel.removeAll();
        mainPanel.revalidate();
        mainPanel.repaint();
        mainPanel.add(allStudentInfo, BorderLayout.CENTER);
        initTable(courseName);
        initAllOperation();
    }

    /**
     * init operations
     */
    public void initAllOperation(){
        allStudentInfo.getAddStudent().addActionListener(l-> addStudent());
        allStudentInfo.getHomePage().addActionListener(l->backToHome());
        allStudentInfo.getSearchByBUId().addActionListener(l->searchByBuId());
        allStudentInfo.getSearchByName().addActionListener(l->searchByName());
        allStudentInfo.getSearchByEmail().addActionListener(l->searchByEmail());
        allStudentInfo.getBackButton().addActionListener(l->backToPreTable());
        allStudentInfo.getSortByAscending().addActionListener(l->sortByAscending());
        allStudentInfo.getSortByDescending().addActionListener(l->sortByDescending());
        allStudentInfo.getAdjustWeight().addActionListener(l -> adjustTaskWeight());
        allStudentInfo.getChangeGrade().addActionListener(l->changeGrade());
        allStudentInfo.getImportStudent().addActionListener(l-> {
            try {
                importStudentByFile();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    public void changeGrade(){
//        DefaultTableModel model = allStudentInfo.getStudentTableModel();
        JTable table = allStudentInfo.getStudentTable();
//        System.out.println(table.getRowCount() + " "+ table.getColumnCount());
        for(int i = 0; i < table.getRowCount(); i++){
            for(int j = 3; j < table.getColumnCount() - 1; j++){
                String columnName = table.getColumnName(j);
                System.out.println(columnName + "..." + courseName);
                System.out.println(DataBase.ifCanUpdate(columnName, courseName));
                if(DataBase.ifCanUpdate(columnName, courseName)){
                    DataBase.updateGrade(Float.valueOf((String) table.getValueAt(i, j)), (String) table.getValueAt(i, 0), columnName, courseName);
                }
            }
        }
    }

    public void adjustTaskWeight(){
        try {
            if(LoginOperation.authority.equals("1")){
                AdjustTaskPageOperation adjust = new AdjustTaskPageOperation(mainPanel, courseName);
            }else{
                JOptionPane.showMessageDialog(allStudentInfo,"Sorry, you do not have this right!","Operation Error",JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initTable(String courseName){
        String id = DataBase.searchForCourseId(courseName);
        List<String> studentList = DataBase.searchStudentByCourseId(id);
        List<String> taskList = DataBase.searchTaskByCourseId(id);

        List<String> columns = new ArrayList<>();
        List<List<Object>> data = new ArrayList<>();
        columns.add("Name");
        columns.add("Email");
        columns.add("BU ID");
        int turn = 1;
        for(String stuId:studentList){
            List<Object> curStudent = new ArrayList<>();
            String student = DataBase.searchStudentById(stuId);
            System.out.println(student);
            String[] stuArr = student.split(" ");
            curStudent.add(stuArr[0]);
            curStudent.add(stuArr[1]);
            curStudent.add(stuArr[2]);
            float allGrade = 0;
            for(String task:taskList){
                String[] taskArr = task.split(" ");
                String taskId = taskArr[0];
                List<String> subTaskList = DataBase.searchSubTaskByTaskAndStu(stuId,taskId);
                if(turn==1 && subTaskList.size()!=1){
                    String taskName = taskArr[1];
                    columns.add(taskName);
                }
                List<String> subTaskGrade = new ArrayList<>();
                float taskGrade = 0;
                for(String subTask:subTaskList){
                    System.out.println(subTask);
                    String[] subTaskArr = subTask.split(" ");
                    if(turn==1){
                        columns.add(subTaskArr[0]);
                    }
                    taskGrade += Float.valueOf(subTaskArr[1])* Float.valueOf(subTaskArr[2])*0.01;
                    subTaskGrade.add(subTaskArr[1]);
                }
                curStudent.add(String.valueOf(taskGrade));
                if(subTaskList.size()!=1) curStudent.addAll(subTaskGrade);
                allGrade += taskGrade * Float.valueOf(taskArr[3])*0.01;
            }
            if(turn==1){
                columns.add("Overall Grade");
            }
            turn++;
            curStudent.add(String.valueOf(allGrade));
            data.add(curStudent);
        }
        if(data.size()!=0){
            Object[][] ob = new Object[data.size()][data.get(0).size()];
            for(int i=0;i<ob.length;i++){
                ob[i] = data.get(i).toArray();
            }
            String[] col = columns.toArray(new String[columns.size()]);
            allStudentInfo.getStudentTable().setModel(new DefaultTableModel(ob,col));
        }else{
            allStudentInfo.getStudentTable().setModel(new DefaultTableModel());
        }
    }
    public void initTable(String courseName,List<String> studentCollection){
        String id = DataBase.searchForCourseId(courseName);
        List<String> studentList = studentCollection;
        List<String> taskList = DataBase.searchTaskByCourseId(id);

        List<String> columns = new ArrayList<>();
        List<List<Object>> data = new ArrayList<>();
        columns.add("Name");
        columns.add("Email");
        columns.add("BU ID");
        int turn = 1;
        for(String stuId:studentList){
            List<Object> curStudent = new ArrayList<>();
            String student = DataBase.searchStudentById(stuId);
            System.out.println(student);
            String[] stuArr = student.split(" ");
            curStudent.add(stuArr[0]);
            curStudent.add(stuArr[1]);
            curStudent.add(stuArr[2]);
            double allGrade = 0;
            for(String task:taskList){
                System.out.println(task);
                String[] taskArr = task.split(" ");
                String taskId = taskArr[0];
                if(turn==1){
                    String taskName = taskArr[1];
                    columns.add(taskName);
                }
                List<String> subTaskList = DataBase.searchSubTaskByTaskAndStu(stuId,taskId);
                List<String> subTaskGrade = new ArrayList<>();
                double taskGrade = 0;
                for(String subTask:subTaskList){
                    String[] subTaskArr = subTask.split(" ");
                    if(turn==1){
                        columns.add(subTaskArr[0]);
                    }
                    taskGrade += Float.valueOf(subTaskArr[1])* Float.valueOf(subTaskArr[2])*0.01;
                    subTaskGrade.add(subTaskArr[1]);
                }
                curStudent.add(String.valueOf(taskGrade));
                curStudent.addAll(subTaskGrade);
                allGrade += taskGrade*Float.valueOf(taskArr[3])*0.01;
            }
            if(turn==1){
                columns.add("Overall Grade");
            }
            turn++;
            curStudent.add(String.valueOf(allGrade));
            data.add(curStudent);
        }
        if(data.size()!=0){
            Object[][] ob = new Object[data.size()][data.get(0).size()];
            for(int i=0;i<ob.length;i++){
                ob[i] = data.get(i).toArray();
            }
            String[] col = columns.toArray(new String[columns.size()]);
            allStudentInfo.getStudentTable().setModel(new DefaultTableModel(ob,col));
        }else{
            allStudentInfo.getStudentTable().setModel(new DefaultTableModel());
        }
    }
    public void backToHome(){
        List<String> course = DataBase.searchForCourse(LoginOperation.credentialId);
        CoursePageOperation coursePageOperation = new CoursePageOperation(mainPanel,course);
    }
    public void searchByName(){
        String stuName = allStudentInfo.getNameText().getText();
        List<String> student = DataBase.searchStudentByName(stuName);
        initTable(courseName,student);
        allStudentInfo.getNameText().setText("");
    }
    public void searchByBuId(){
        String buId = allStudentInfo.getIdText().getText();
        List<String> student = DataBase.searchStudentByBuId(buId);
        initTable(courseName,student);
        allStudentInfo.getIdText().setText("");
    }
    public void searchByEmail(){
        String email = allStudentInfo.getEmailText().getText();
        List<String> student = DataBase.searchStudentByEmail(email);
        initTable(courseName,student);
        allStudentInfo.getEmailText().setText("");
    }
    public void backToPreTable(){
        AllStudentInfoOpera allStudentInfoOpera = new AllStudentInfoOpera(mainPanel,courseName);
    }
    public void sortByAscending(){
        JTable table = allStudentInfo.getStudentTable();
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);

        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(table.getColumnCount()-1, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
    }
    public void sortByDescending(){
        JTable table = allStudentInfo.getStudentTable();
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);

        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(table.getColumnCount()-1, SortOrder.DESCENDING));
        sorter.setSortKeys(sortKeys);
    }
    public  void importStudentByFile() throws FileNotFoundException {
        JFileChooser fd = new JFileChooser();
        fd.showOpenDialog(null);
        File f = fd.getSelectedFile();
        if(f != null){
            String path = f.getPath();
            System.out.println("file path = " + path);
        }
        List<List<String>> records = new ArrayList<>();
        Scanner scanner = new Scanner(f);
        while (scanner.hasNextLine()) {
            records.add(getRecordFromLine(scanner.nextLine()));
        }
        // print items in records
        System.out.println("--------items in CSV file------");
        for (List<String> e : records){
            System.out.println(e.toString());
        }
        // clear data and insert data to three new lists
        List<List<String>> studentList = new ArrayList<>();
        List<List<String>> enrollmentList = new ArrayList<>();
        List<List<String>> gradeList = new ArrayList<>();

        for (int i = 0; i < records.size(); i++){
            if (records.get(i).get(0).equals("student")){
                studentList.add(records.get((i + 1)));
                studentList.add(records.get((i + 2)));
                studentList.add(records.get((i + 3)));
                studentList.add(records.get((i + 4)));
            }

            if (records.get(i).get(0).equals("Enrollment")) {
                enrollmentList.add(records.get(i + 1));
                enrollmentList.add(records.get(i + 2));
                enrollmentList.add(records.get(i + 3));
                enrollmentList.add(records.get(i + 4));
            }
            if(i >= 1) {
                if (records.get(i - 1).get(0).equals("grade")) {
                    while (i != records.size()) {
                        gradeList.add(records.get(i));
                        i++;
                    }
                    break;
                }
            }
        }
        // insert data into database
        for (int i = 0; i < studentList.size(); i++){
            // add new student
            DataBase.addStudent(studentList.get(i).get(0)
                    ,studentList.get(i).get(1)
                    ,studentList.get(i).get(2)
                    ,"1".equals(studentList.get(i).get(3)));

            // add new grade
            String str = DataBase.searchStudentByBuId(studentList.get(i).get(2)).get(0);
            int studentId = Integer.parseInt(str);
            DataBase.addGrade(Integer.parseInt(gradeList.get(i).get(0))
                    ,studentId
                    ,Integer.parseInt(gradeList.get(i).get(2)));
            DataBase.addGrade(Integer.parseInt(gradeList.get(i+1).get(0))
                    ,studentId
                    ,Integer.parseInt(gradeList.get(i+1).get(2)));
            DataBase.addGrade(Integer.parseInt(gradeList.get(i+2).get(0))
                    ,studentId
                    ,Integer.parseInt(gradeList.get(i+2).get(2)));
            DataBase.addGrade(Integer.parseInt(gradeList.get(i+3).get(0))
                    ,studentId
                    ,Integer.parseInt(gradeList.get(i+3).get(2)));
            // add new enrollment
            DataBase.addEnrollment(studentId, Integer.parseInt(enrollmentList.get(i).get(1)));

        }

    }

    private List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }
    public void addStudent(){
        if(LoginOperation.authority.equals("1")){
            NewStudentPageOp newStudentPage = new NewStudentPageOp(mainPanel,courseName);
        }else{
            JOptionPane.showMessageDialog(allStudentInfo,"Sorry, you do not have this right!","Operation Error",JOptionPane.WARNING_MESSAGE);
        }
    }
}
