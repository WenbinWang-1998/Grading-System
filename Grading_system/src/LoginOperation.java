import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 *  logic for login
 */
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
    public void login(){
        String id = loginPage.getUserIdText().getText();
        String pwd = String.valueOf(loginPage.getPwdText().getPassword());

        String credential = DataBase.searchForCredential(id,pwd);
        if(!credential.equals("")){
            JOptionPane.showMessageDialog(mainPanel,"Login in Successfully!","Login Confirmation",JOptionPane.INFORMATION_MESSAGE);
            String[] credAttr = credential.split(" ");
            //System.out.println(credAttr.length);
            credentialId = credAttr[0];
            authority = credAttr[3];
            List<String> course = DataBase.searchForCourse(credentialId);
            CoursePageOperation coursePageOp = new CoursePageOperation(mainPanel,course);
        }else if(!isPassword()){
            // determine whether password is correct
            JOptionPane.showMessageDialog(mainPanel,"id or password wrong","Login Error",JOptionPane.WARNING_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(mainPanel,"Can not Find the Credential!","Login Error",JOptionPane.WARNING_MESSAGE);
        }

    }

    /**
     * determine the correctness of password
     * @return
     */
    public boolean isPassword(){
        String id = loginPage.getUserIdText().getText();
        String pwd = String.valueOf(loginPage.getPwdText().getPassword());
        String return_password = DataBase.searchForPassword(id);
        if (pwd == return_password){
            return true;
        }else {
            return false;
        }
    }
    public void quit(){
        System.exit(0);
    }
}
