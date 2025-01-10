package OneTimeJob;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Welcome_Page {
    private JButton btn_EmployeeWindow;
    private JButton btn_AdminWindow;
    public JPanel WelcomePage;



    static JFrame WelcomeFrame = new JFrame("Welcome_Page");
//When a button is clicked the action listener responds by jumping to the corresponding interface.
// This initializes the objects in the class.
    public Welcome_Page(){
        btn_EmployeeWindow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               WelcomeFrame.dispose();
               JFrame frame = new JFrame("Login_Emp");
               frame.setContentPane(new Login_Emp().Login_Emp);
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame.pack();
               frame.setVisible(true);

            }
        });

        btn_AdminWindow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WelcomeFrame.dispose();
                JFrame frame = new JFrame("Login_Admin");
                frame.setContentPane(new Login_Admin().Login_Admin);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

            }
        });
    }

    public static void main(String[] args) {
        WelcomeFrame.setContentPane(new Welcome_Page().WelcomePage);
        WelcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WelcomeFrame.pack();
        WelcomeFrame.setVisible(true);
    }

    private void createUIComponents() {


    }
}
