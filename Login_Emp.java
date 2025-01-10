package OneTimeJob;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Login_Emp {
    static Connection connection = null;
    private JButton btn_SUBMIT;
    private JTextField tf_Username;
    private JButton btn_Register;
    public JPanel Login_Emp;
    private JPasswordField passwordField2;
    private JCheckBox showPasswordCheckBox;
    private JButton exitButton;
    private JComboBox CBox_Age;
    private JComboBox CBox_Gender;
    static JFrame Elogin = new JFrame("Login_Emp");
    static Login_Emp obj2 = new Login_Emp();
    static int value;
    static int f_value;
    static String Username;
    static String Gender;
    static String Age;

    public Login_Emp(){
        connection = Sqliteconnection.dbConnector();
        //When clicking button ActionListener responds by jumping to the Emp_register interface
        btn_Register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Elogin.dispose();
                JFrame frame = new JFrame("Emp_Register");
                frame.setContentPane(new Emp_Register().Emp_Register);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
        btn_SUBMIT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    //if statement validates that all fields have been filled

                    if ((passwordField2.getText().equals("")) || (tf_Username.getText().equals("")) || (CBox_Age.getSelectedItem().equals(""))
                            ||(CBox_Gender.getSelectedItem().equals("")) ) {
                        JOptionPane.showMessageDialog(null, "Fields are empty");
                    }
                    //Checks the presence of the account in the database
                    // Compares each field in the database table “Employees” with the corresponding information inputted by the user.
                    else{
                        value = obj2.Emp_Login_Correct("select * from Employees where Username = '" + tf_Username.getText()+
                                "' AND Password = '" + passwordField2.getText() + "' AND Gender = '" + CBox_Gender.getSelectedItem() +
                                "' AND Age = '" + CBox_Age.getSelectedItem() + "'");
                        if (value == 0) {
                            JOptionPane.showMessageDialog(null, "Username, password, gender and age don't match");
                        }
                        else if (value == 1) {
                            Username = tf_Username.getText();
                            Age = CBox_Age.getSelectedItem().toString();
                            Gender = CBox_Gender.getSelectedItem().toString();
                            JOptionPane.showMessageDialog(null, "Valid Fields");
                            Elogin.dispose();
                            JFrame frame = new JFrame("Employee_Portal");
                            frame.setContentPane(new Employee_Portal().Employee_Portal);
                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            frame.pack();
                            frame.setVisible(true);
                        }
                    }
                }

                catch(Exception e2){
                    e2.printStackTrace();}
            }
        });
        showPasswordCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(showPasswordCheckBox.isSelected()){
                    passwordField2.setEchoChar((char)0);
                }
                else{
                    passwordField2.setEchoChar('*');
                }
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Elogin.dispose();
                JFrame frame = new JFrame("Welcome_Page");
                frame.setContentPane(new Welcome_Page().WelcomePage);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
    //If the account exists, login is successful and the method returns a value of 1, otherwise, it returns a value of 0.
    //The ResultSet stores the results of the query and the prepared statement executes it.
    public Integer Emp_Login_Correct (String query_Employee){
        connection = Sqliteconnection.dbConnector();
        try {
            PreparedStatement pst = connection.prepareStatement(query_Employee);
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
        catch(Exception e6){
            e6.printStackTrace();
        }
        return f_value;
    }

    public static void main(String[] args) {
        Elogin.setContentPane(new Login_Emp().Login_Emp);
        Elogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Elogin.pack();
        Elogin.setVisible(true);
    }

}
