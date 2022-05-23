import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * a class to use java do creat, retrieve, update, delete operations to database
 */
public class DataBase {
    static Connection connection = null;
    public static void linkToDatabase(){
        String url = "jdbc:mysql://localhost:3306/grading_system";
        String username = "root";
        String password = "dengchao";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // authority is boolean, because this column's type is tinyint(1) in database
    public static void addCredential(String email, String password, boolean authority){
        String query = "insert into grading_system.credential values(default, ?, ?, ?)";
        int count = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.setInt(3, authority ? 1 : 0);
            count = preparedStatement.executeUpdate();
            System.out.println("Table credential: "+ count + " rows have been changed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * delete Credential by id
     * @param id
     */
    public static void deleteCredentialById(int id){
        String query = "delete from grading_system.credential where id = ?";
        int count = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            count = preparedStatement.executeUpdate();
            System.out.println("Table credential: " + count + " rows have been changed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * add staff
     * @param name
     * @param email
     * @param credentialId
     */
    public static void addStaff(String name, String email, int credentialId){
        String query = "insert into grading_system.staff values(default, ?, ?, ?)";
        int count = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setInt(3, credentialId);
            count = preparedStatement.executeUpdate();
            System.out.println("Table staff: " + count + " rows have been changed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * delete staff by id
     * @param id
     */
    public static void deleteStaffById(int id){
        String query = "delete from grading_system.staff where id = ?";
        int count = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            count = preparedStatement.executeUpdate();
            System.out.println("Table staff: " + count + " rows have been changed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * add course
     * @param name
     * @param credit
     * @param section
     * @param year
     * @param semester
     * @param professorId
     */
    public static void addCourse(String name, int credit, String section, String year, String semester, int professorId){
        String query = "insert into grading_system.course values(default, ?, ?, ?, ?, ?, ?)";
        int count = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, credit);
            preparedStatement.setString(3, section);
            preparedStatement.setString(4, year);
            preparedStatement.setString(5, semester);
            preparedStatement.setInt(6, professorId);
            count = preparedStatement.executeUpdate();
            System.out.println("Table course: " + count + " rows have been changed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * delete course by name
     * @param name
     */
    public static void deleteCourseByName(String name){
        String query = "delete from grading_system.course where name = ?";
        int count = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            count = preparedStatement.executeUpdate();
            System.out.println("Table course: " + count + " rows have been changed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * delete course by id
     * @param id
     */
    public static void deleteCourseById(int id){
        String query = "delete from grading_system.course where id = ?";
        int count = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            count = preparedStatement.executeUpdate();
            System.out.println("Table course: " + count + " rows have been changed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * add task
     * @param name
     * @param courseId
     * @param weight
     */
    public static void addTask(String name, int courseId, double weight){
        String query = "insert into grading_system.task values(default, ?, ?, ?)";
        int count = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, courseId);
            preparedStatement.setDouble(3, weight);
            count = preparedStatement.executeUpdate();
            System.out.println("Table task: " + count + " rows have been changed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * delete task by id
     * @param id
     */
    public static void deleteTaskById(int id){
        String query = "delete from grading_system.task where id = ?";
        int count = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            count = preparedStatement.executeUpdate();
            System.out.println("Table task: " + count + " rows have been changed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * add subtask
     * @param taskId
     * @param weight
     * @param name
     * @param totalPoints
     * @param releasedDate
     * @param dueDate
     * @param groupProject
     * @param maxBonusPoint
     */
    public static void addSubTask(int taskId, double weight, String name, float totalPoints, String releasedDate, String dueDate,
                                  boolean groupProject, float maxBonusPoint){
        String query = "insert into grading_system.subtask values(default, ?, ?, ?, ?, ?, ?, ?, ?)";
        int count = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, taskId);
            preparedStatement.setDouble(2, weight);
            preparedStatement.setString(3, name);
            preparedStatement.setFloat(4, totalPoints);
            preparedStatement.setString(5, releasedDate);
            preparedStatement.setString(5, dueDate);
            preparedStatement.setInt(6, groupProject ? 1 : 0);
            preparedStatement.setFloat(7, maxBonusPoint);
            count = preparedStatement.executeUpdate();
            System.out.println("Table subtask: " + count + " rows have been changed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * delete subtask by id
     * @param id
     */
    public static void deleteSubTaskById(int id){
        String query = "delete from grading_system.subtask where id = ?";
        int count = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            count = preparedStatement.executeUpdate();
            System.out.println("Table subtask: " + count + " rows have been changed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * add student
     * @param name
     * @param email
     * @param buId
     * @param isWithDrawn
     */
    public static void addStudent(String name, String email, String buId, boolean isWithDrawn){
        String query = "insert into grading_system.student values(default, ?, ?, ?, ?)";
        int count = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, buId);
            preparedStatement.setInt(4, isWithDrawn ? 1 : 0);
            count = preparedStatement.executeUpdate();
            System.out.println("Table student: " + count + " rows have been changed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * delete student by id
     * @param id
     */
    public static void deleteStudentById(int id){
        String query = "delete from grading_system.student where id = ?";
        int count = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            count = preparedStatement.executeUpdate();
            System.out.println("Table student: " + count + " rows have been changed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * select student by by id
     * @param buId
     * @return
     */
    public static String selectStudentByBuId(String buId){
        String query = "select name,email,buId,isWithdrawn from grading_system.student where buId = ?";
        String result = "No results be found";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, buId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            // Using split to split the result.
            result = resultSet.getString(1) + " " + resultSet.getString(2) + " " +
                    resultSet.getString(3) + " " + resultSet.getString(4);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * select student by email
     * @param email
     * @return
     */
    public static String selectStudentByEmail(String email){
        String query = "select name,email,buId,isWithdrawn from grading_system.student where email = ?";
        String result = "No results be found";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            // Using split to split the result.
            result = resultSet.getString(1) + " " + resultSet.getString(2) + " " +
                    resultSet.getString(3) + " " + resultSet.getString(4);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * select student by name
     * @param name
     * @return
     */
    public static String selectStudentByName(String name){
        String query = "select name,email,buId,isWithdrawn from grading_system.student where name = ?";
        String result = "No results be found";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            // Using split to split the result.
            result = resultSet.getString(1) + " " + resultSet.getString(2) + " " +
                    resultSet.getString(3) + " " + resultSet.getString(4);
        }catch (SQLException e) {
            System.out.println("Empty Result Set!");
            return "";
        }
        return result;
    }

    /**
     * add grade
     * @param subTaskId
     * @param studentId
     * @param score
     */
    public static void addGrade(int subTaskId, int studentId, float score){
        String query = "insert into grading_system.grade values(default, ?, ?, ?)";
        int count = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, subTaskId);
            preparedStatement.setInt(2, studentId);
            preparedStatement.setFloat(3, score);
            count = preparedStatement.executeUpdate();
            System.out.println("Table grade: " + count + " rows have been changed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * delete group by id
     * @param id
     */
    public static void deleteGradeById(int id){
        String query = "delete from grading_system.grade where id = ?";
        int count = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            count = preparedStatement.executeUpdate();
            System.out.println("Table grade: " + count + " rows have been changed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * change grade
     * @param subTaskId
     * @param studentId
     * @param newScore
     */
    public static void changeGrade(int subTaskId, int studentId, int newScore){
        String query = "update grading_system.grade set score = ? where subTaskId = ? and studentId = ?";
        int count = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, newScore);
            preparedStatement.setInt(2, subTaskId);
            preparedStatement.setInt(3, studentId);
            count = preparedStatement.executeUpdate();
            System.out.println("Table grade: " + count + " rows have been changed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * search for credential
     * @param id
     * @param password
     * @return
     */
    public static String searchForCredential(String id, String password){
        String query = "select * from grading_system.Credential where email = ? and password = ?";
        String result = "No results be found";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            // Using split to split the result.
            result = resultSet.getString(1) + " " + resultSet.getString(2) + " " +
                    resultSet.getString(3) + " " + resultSet.getString(4);
        }catch (SQLException e) {
            System.out.println("Empty Result Set!");
            return "";
        }
        return result;
    }

    /**
     * seaarch for course
     * @param credentialId
     * @return
     */
    public static List<String> searchForCourse(String credentialId){
        String query = "select Course.name from Credential,Staff,Course where Credential.id = ? and Credential.id = Staff.credentialId and Staff.id = Course.professorId";
        List<String> courseList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, credentialId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String courseName = resultSet.getString(1);
                courseList.add(courseName);
            }
        }catch (SQLException e) {
            System.out.println("Empty Result Set!");
            return null;
        }
        return courseList;
    }

    /**
     * search professor by credential
     * @param id
     * @return
     */
    public static String searchProfIdByCredential(String id){
        String query = "select Staff.id from Credential,Staff where Credential.id = ? and Credential.id = Staff.credentialId";
        String result = "No results be found";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            // Using split to split the result.
            result = resultSet.getString(1);
        }catch (SQLException e) {
            System.out.println("Empty Result Set!");
            return "";
        }
        return result;
    }
    public static String searchForCourseId(String courseName){
        String query = "select Course.id from Course where Course.name = ?";
        String courseId = "";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, courseName);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            courseId = resultSet.getString(1);

        }catch (SQLException e) {
            System.out.println("Empty Result Set!");
            return null;
        }
        return courseId;
    }
    public static List<String> searchStudentByCourseId(String courseId){
        String query = "select Enrollment.studentId from Enrollment where Enrollment.courseId = ?";
        List<String> studentList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, courseId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String studentId = resultSet.getString(1);
                studentList.add(studentId);
            }
        }catch (SQLException e) {
            System.out.println("Empty Result Set!");
            return null;
        }
        return studentList;
    }
    public static List<String> searchTaskByCourseId(String courseId){
        String query = "select * from Task where Task.courseId = ?";
        List<String> taskList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, courseId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String task = resultSet.getString(1)+" "+resultSet.getString(2)+" "
                        +resultSet.getString(3)+" "+resultSet.getString(4);
                taskList.add(task);
            }
        }catch (SQLException e) {
            System.out.println("Empty Result Set!");
            return null;
        }
        return taskList;
    }
    public static String searchStudentById(String id){
        String query = "select name,email,buId,isWithdrawn from grading_system.student where id = ?";
        String result = "No results be found";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            result = resultSet.getString(1) + " " + resultSet.getString(2) + " " +
                    resultSet.getString(3) + " " + resultSet.getString(4);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static List<String> searchSubTaskByTaskAndStu(String stuId,String taskId){
        String query = "select SubTask.name,Grade.score,SubTask.weight from Grade,SubTask where SubTask.taskId = ? and Grade.studentId = ? and Grade.subTaskId = SubTask.id";
        List<String> subTaskList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, taskId);
            preparedStatement.setString(2,stuId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String subTask = resultSet.getString(1)+" "+resultSet.getString(2)+" "+resultSet.getString(3);
                subTaskList.add(subTask);
            }
        }catch (SQLException e) {
            System.out.println("Empty Result Set!");
            return null;
        }
        return subTaskList;
    }
    public static List<String> searchStudentByName(String stuName){
        String query = "select Student.id from Student where Student.name = ?";
        List<String> studentList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, stuName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String studentId = resultSet.getString(1);
                studentList.add(studentId);
            }
        }catch (SQLException e) {
            System.out.println("Empty Result Set!");
            return null;
        }
        return studentList;
    }
    public static List<String> searchStudentByBuId(String buId){
        String query = "select Student.id from Student where Student.buId = ?";
        List<String> studentList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, buId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String studentId = resultSet.getString(1);
                studentList.add(studentId);
            }
        }catch (SQLException e) {
            System.out.println("Empty Result Set!");
            return null;
        }
        return studentList;
    }
    public static List<String> searchStudentByEmail(String email){
        String query = "select Student.id from Student where Student.email = ?";
        List<String> studentList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String studentId = resultSet.getString(1);
                studentList.add(studentId);
            }
        }catch (SQLException e) {
            System.out.println("Empty Result Set!");
            return null;
        }
        return studentList;
    }

    public static ResultSet selectTaskPage(String courseName){
        System.out.println(courseName);
        String query = "SELECT course.name,task.name,task.weight FROM task,course WHERE (course.name = ?) AND course.id = task.courseId";
        ResultSet resultSet = null;
        try {
//            PreparedStatement preparedStatement = (PreparedStatement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            PreparedStatement preparedStatement = connection.prepareStatement(query,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            preparedStatement.setString(1, courseName);
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    public static void updateTaskPage(String courseName, String taskName, String weight){
        String query = "UPDATE task SET weight = ? WHERE courseId = (SELECT id FROM course WHERE name = ?) AND name = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1, Double.valueOf(weight));
            preparedStatement.setString(2, courseName);
            preparedStatement.setString(3, taskName);
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void addTaskFromTaskPage(String taskName, String courseName, double weight){
        String query = "insert into grading_system.task values(default, ?, (SELECT id FROM course WHERE name = ?), ?)";
        int count = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, taskName);
            preparedStatement.setString(2, courseName);
            preparedStatement.setDouble(3, weight);
            count = preparedStatement.executeUpdate();
            System.out.println("Table task: " + count + " rows have been changed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * add data to Enrollment table
     * @return
     */
    public static void addEnrollment(int studentId, int courseId){
        String query = "insert into grading_system.Enrollment values(default, ?, ?)";
        int count = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, courseId);
            count = preparedStatement.executeUpdate();
            System.out.println("Table Enrollment: " + count + " rows have been changed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * search password corresponding to the id
     * @param id
     * @return
             */
    public static String searchForPassword(String id){
        String query = "select password from grading_system.Credential where email = ?";
        String result = "No results be found";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            // Using split to split the result.
            result = resultSet.getString(1);
            System.out.println("password result = " + result);
        }catch (SQLException e) {
            System.out.println("Empty Result Set!");
            return "";
        }
        return result;
    }
    public static ResultSet selectSubTaskPage(String courseName, String taskName){
        System.out.println(courseName);
        String query = "SELECT task.name, subtask.name, subtask.weight, releasedDate, dueDate, groupProject, maxBonusPoints FROM task, subtask, course WHERE task.courseId = course.id " +
                "AND task.id = subtask.taskId AND course.name = ? AND task.name = ?";
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            preparedStatement.setString(1, courseName);
            preparedStatement.setString(2, taskName);
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    public static void updateSubTaskPage(String taskName, String subTaskName, String weight, String course){
        String query = "UPDATE subtask SET weight = ? WHERE taskId = (SELECT id FROM task WHERE name = ? " +
                "AND courseId = (SELECT id FROM course WHERE name = ?)) AND name = ?";
        System.out.println(taskName + " "+ subTaskName + " "+ weight);
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1, Double.valueOf(weight));
            preparedStatement.setString(2, taskName);
            preparedStatement.setString(3, course);
            preparedStatement.setString(4, subTaskName);
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void addSubTaskFromTaskPage(String taskName, double weight, String subTaskName, String rD, String dD, Integer group, Float maxB, String course){
        String query = "insert into grading_system.subtask values(default, (SELECT id FROM task WHERE name = ? " +
                "AND courseId = (SELECT id FROM course WHERE name = ?)), ?, ? , 100, ?, ?, ?, ?)";
        int count = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, taskName);
            preparedStatement.setString(2, course);
            preparedStatement.setDouble(3, weight);
            preparedStatement.setString(4, subTaskName);
            preparedStatement.setString(5, rD);
            preparedStatement.setString(6, dD);
            preparedStatement.setInt(7, group);
            preparedStatement.setFloat(8, maxB);

            count = preparedStatement.executeUpdate();
            System.out.println("Table task: " + count + " rows have been changed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static List<String> searchSubTaskIdByTask(String taskId){
        String query = "select SubTask.id from SubTask where SubTask.taskId = ?";
        List<String> subTaskList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, taskId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String subTask = resultSet.getString(1);
                subTaskList.add(subTask);
            }
        }catch (SQLException e) {
            System.out.println("Empty Result Set!");
            return null;
        }
        return subTaskList;
    }
    public static void updateGrade(Float score, String studentName, String subTaskName, String courseName){
        String query = "UPDATE grade SET score = ? WHERE studentId = (SELECT id FROM student WHERE name = ?) " +
                "AND subTaskId = (SELECT subtask.id FROM subtask, task, course WHERE subtask.name = ? " +
                "AND task.courseId = (SELECT id FROM course WHERE name = ?) AND subtask.taskId = task.id " +
                "AND task.courseId = course.id) ";
        int count = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setFloat(1, score);
            preparedStatement.setString(2, studentName);
            preparedStatement.setString(3, subTaskName);
            preparedStatement.setString(4, courseName);
            count = preparedStatement.executeUpdate();
            System.out.println("Table task: " + count + " rows have been changed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static boolean ifCanUpdate(String subtaskName, String courseName){
        String query = "SELECT * FROM subtask AS s, course AS c, task AS t WHERE s.name = ? " +
                "AND t.courseId = (SELECT id FROM course WHERE course.name = ?) AND t.id = s.taskId " +
                "AND t.courseId = c.id";
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, subtaskName);
            preparedStatement.setString(2, courseName);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.isBeforeFirst()){
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
