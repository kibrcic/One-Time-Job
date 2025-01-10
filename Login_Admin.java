package OneTimeJob;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login_Admin {
    static Connection connection = null;
    public JPanel Login_Admin;
    private JTextField tf_Username;
    private JButton SUBMITButton;
    private JPasswordField passwordField1;
    private JCheckBox showPasswordCheckBox;
    private JLabel ImageLogo;
    private JButton exitButton;
    static JFrame Alogin = new JFrame("Login_Admin");
    static Login_Admin obj1 = new Login_Admin();
    static int value;
    static int f_value;


   // constructor
    public Login_Admin(){
        connection = Sqliteconnection.dbConnector();


        SUBMITButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //if statement validates that all fields are filled
                    if ((passwordField1.getText().equals("")) || (tf_Username.getText().equals(""))) {
                        JOptionPane.showMessageDialog(null, "Fields are empty");
                    }
                    else{
                      //  password and username have to match those predefined in the “Administrator” table to access the administrator portal.
                        value = obj1.Admin_Login_Correct("select * from Administrator where AdminUsername = '" + tf_Username.getText() +
                                "' AND Password = '" + passwordField1.getText() + "'");
                        if (value == 0) {
                            JOptionPane.showMessageDialog(null, "Username and password don't match");
                        }
                        else if (value == 1) {
                            JOptionPane.showMessageDialog(null, "Valid Fields");
                            Alogin.dispose();
                            JFrame frame = new JFrame("Administrator_Portal");
                            frame.setContentPane(new Administrator_Portal().Administrator_Portal);
                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            frame.pack();
                            frame.setVisible(true);
                        }
                    }
                }
                catch(Exception e1){
                    e1.printStackTrace();}


                }
            });
        //Action Listener that allows user to view the password before submitting an entry
        showPasswordCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(showPasswordCheckBox.isSelected()){
                    passwordField1.setEchoChar((char)0);
                }
                else{
                    passwordField1.setEchoChar('*');
                }
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Alogin.dispose();
                JFrame frame = new JFrame("Welcome_Page");
                frame.setContentPane(new Welcome_Page().WelcomePage);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

            }
        });
    }
    public Integer Admin_Login_Correct (String query_Admin){
        connection = Sqliteconnection.dbConnector();
        try {
            //If the password and username match, login is successful and the method returns a value of 1, otherwise, it returns a value of 0.
            //The ResultSet stores the results of the query and the prepared statement executes it
            PreparedStatement pst = connection.prepareStatement(query_Admin);
            ResultSet r = pst.executeQuery();
            int count = 0;
            value =0;
            while (r.next()) {
                count += 1;
            }
            if (count == 1) {
                f_value = 1;
                return f_value;

            }
            else if (count == 0) {
                f_value = 0;
                return f_value;

            }
            r.close();
            pst.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return f_value;
    }

    public static void main(String[] args) {
        Alogin.setContentPane(new Login_Admin().Login_Admin);
        Alogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Alogin.pack();
        Alogin.setVisible(true);
}

    private void createUIComponents() {

    }
}

