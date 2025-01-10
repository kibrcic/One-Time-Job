package OneTimeJob;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Emp_Register {
    public JPanel Emp_Register;
    private JTextField TF_Fullname;
    private JComboBox comboBox_Gender;
    private JComboBox comboBox_Age;
    private JTextField TfUser_emp;
    private JPasswordField Tfpassword_emp;
    private JButton SUBMITButton;
    private JButton btn_BacktoLogin;
    private JCheckBox showPasswordCheckBox;
    static JFrame ERegister = new JFrame("Emp_Register");
    static Connection connection = null;
    static String gender;


    public Emp_Register() {
        connection = Sqliteconnection.dbConnector();
        btn_BacktoLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ERegister.dispose();
                JFrame frame = new JFrame("Login_Emp");
                frame.setContentPane(new Login_Emp().Login_Emp);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
        SUBMITButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    //if statement validates that all fields have been filled
                    if ((TF_Fullname.getText().equals("")) || (comboBox_Gender.getSelectedItem().equals("")) ||
                            (comboBox_Age.getSelectedItem().equals("")) || (TfUser_emp.getText().equals("")) ||
                            (Tfpassword_emp.getText().equals(""))) {
                        JOptionPane.showMessageDialog(null, "Fields are empty");}

                    else {
                        // PreparedStatement inserts data entered by the user into the database Employees through a query
                        gender = comboBox_Gender.getSelectedItem().toString();
                        PreparedStatement pst = connection.prepareStatement("INSERT INTO Employees (Name, Gender, Age, " + "Username, Password) VALUES (?, ?, ?, ?, ?)");
                        pst.setString(1, TF_Fullname.getText());
                        pst.setString(2, comboBox_Gender.getSelectedItem().toString());
                        pst.setString(3, comboBox_Age.getSelectedItem().toString());
                        pst.setString(4, TfUser_emp.getText());
                        pst.setString(5,Tfpassword_emp.getText());
                        pst.execute();
                        JOptionPane.showMessageDialog(null, "Record was saved");
                        pst.close();
                }}
                catch(Exception e3 ){
                    e3.printStackTrace();
            }


            }
        });
        //ActionListener that enables the user to view the password written before making an entry
        showPasswordCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(showPasswordCheckBox.isSelected()){
                    Tfpassword_emp.setEchoChar((char)0);
                }
                else{
                    Tfpassword_emp.setEchoChar((char)0);
                }
            }
        });
    }
    public static void main(String[] args) {
        ERegister.setContentPane(new Emp_Register().Emp_Register);
        ERegister.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ERegister.pack();
        ERegister.setVisible(true);
    }
}
