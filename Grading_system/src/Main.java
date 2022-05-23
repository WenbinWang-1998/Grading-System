
import java.awt.*;
import javax.swing.*;

public class Main {

    /**
     * program start here
     * @param args
     */
    public static void main(String[] args) {
        DataBase.linkToDatabase();
        MainPage mainpage = new MainPage();
        JPanel mainPanel = mainpage.getParentPanel();
        LoginOperation loginOperation = new LoginOperation(mainPanel);

    }
}
