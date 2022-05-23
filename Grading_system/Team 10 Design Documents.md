# Team 10 Design Documents

### Team member: Chao Deng, Wenbin Wang, Shun Yao



## 1. Overall Design



### 1.1 Introduction

In our design, we write the class for each page, like CoursePage, AllStudentInfo, etc. Also, we create the controller for each page to make sure the operation for each page is individual and safe, that is, all pages end with Operation are our controller. Therefore, the controller class would be initialized first so that the page the controller points to and all operation in this page could be initialized. We do not need to do any changes in page class. What we need to do is adding the ActionListener to monitor each button and mouse-click. Then if the operation is performed, we could choose to update the database or pass the parameter to initialize the next page controller. Such object structure could make sure the project we made is robust and extensive. 

We use different Layout Models in default library like BoarderLayout, GridLayout or FlowLayout etc. And then we insert those components into the container that we have already set up the Layout and adjust the format within the containers. It is more convenient for us to set up the locations of all components because we just need to decide the location of their containers but the layout inside has been decided by default models.

We use a main panel to load the page we need in the project. It would be initialized and add new content from login page. Then it would be used to jump back and forth between different pages. When we want to enter a new page, we could clear the main panel and add new page into it so that the page could be refreshed within a unique frame. It is user-friendly and explicit. 



### 1.2 Example Code

```java
public class LoginOperation {
    LoginPage loginPage;
    JPanel mainPanel;
    static String credentialId;
    static String authority;
    public LoginOperation(JPanel prePanel){
        loginPage = new LoginPage();
        this.mainPanel = prePanel;
        mainPanel.removeAll(); // remove previous component and layout
        mainPanel.revalidate();  // follow by removeAll
        mainPanel.repaint();     // follow by revalidate
        mainPanel.add(loginPage,BorderLayout.CENTER);
        this.initAllOperation();
    }
    public void initAllOperation(){
        loginPage.getLoginInButton().addActionListener(l->login());
        loginPage.getCancelButton().addActionListener(l-> quit());
    }
    public void login(){}
    public boolean isPassword(){}
    public void quit(){}
}
```

In this example, I delete all the content code and reserve the structure code. As you can see, we use a JPanel called mainPanel to show the content of this page. In the constructor, we remove all the previous content of this JPanel. So that we don't need to new a page every time and this structure avoids opening many pages when you use our application.  Also, we extract all the control parts like pushing a button to this controller class, so, this design is more neat and readable. 

```java
public class Main {
    public static void main(String[] args) {
        DataBase.linkToDatabase();
        MainPage mainpage = new MainPage();
        JPanel mainPanel = mainpage.getParentPanel();
        LoginOperation loginOperation = new LoginOperation(mainPanel);
    }
}
```

In our main class, when you want to show a page, the only thing you need to do is to initialize an operation class like LoginOperation and add the main page's panel to it. You can change the LoginOperation to any other Operation like NewCourseOpertion so that it can show the new course page. 



### 1.3 Bonus

1. We don't need to new a page every time and this structure avoids open many pages when you use this application.  
2. We extract all the control part like push a button to this controller class, when you want to add some more function, you can add it in the controller class, so it's extendible.
3. We use decorator pattern in this design. 





## 2. Other details 



### 2.1 Connecting to database

In this project, we use mysql database and jdbc to deal with data. We wrote our database's all method as static because the database is unique and doesn't need to be initialized. 

```java
DataBase.linkToDatabase();       
String id = DataBase.searchForCourseId(courseName);
List<String> studentList = DataBase.searchStudentByCourseId(id);
List<String> taskList = DataBase.searchTaskByCourseId(id);
```

As you can see, after linking to the database, we can use DataBase directly, just like the Math class. This really convenient and neat.



### 2.2 Permission difference

We could use ‘Authority’ attribute on the table named Credential in our database to distinguish the TA and Professor. There are some limitations for TAs’ right so it is benefit for professor to better manage the student information.

### 2.3 Import students by CSV files

we use a  good design  to automatically retrieve the path based on the CSV file that is opened, so no matter where the CSV file is, we don't need to modify the code.

```java
JFileChooser fd = new JFileChooser();
        fd.showOpenDialog(null);
        File f = fd.getSelectedFile();
        if(f != null){
            String path = f.getPath();
            System.out.println("file path = " + path);
        }
```



### 2.4 Bonus

1. We chose to use static methods and this is a good design.  
2. The permission between TA and professor are different, so that it's better to manage student information.
3. System can import student information by CSV files.



 