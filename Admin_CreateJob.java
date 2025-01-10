package OneTimeJob;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class Admin_CreateJob {
    public JPanel Admin_CreateJob;
    private JButton exitButton;
    private JTextArea Ta_JobDescript;
    private JComboBox HoursRequires_comboBox;
    private JTextField tF_PayAmount;
    private JComboBox Gender_comboBox;
    private JComboBox Age_comboBox;
    private JButton SUBMIT_Button;
    private JTextField tf_JobID;
    private JComboBox comboBox_JobStatus;
    static JFrame ACreate = new JFrame("Admin_CreateJob");
    static Connection connection = null;

    public Admin_CreateJob() {
        connection = Sqliteconnection.dbConnector();
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ACreate.dispose();
                JFrame frame = new JFrame("Administrator_Portal");
                frame.setContentPane(new Administrator_Portal().Administrator_Portal);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });

        SUBMIT_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //if statement validates that all fields have been filled
                    if ((tf_JobID.getText().equals("")) || (Ta_JobDescript.getText().equals("")) || (HoursRequires_comboBox.getSelectedItem().equals(""))
                            || (Gender_comboBox.getSelectedItem().equals("")) || (Age_comboBox.getSelectedItem().equals("")) || (tF_PayAmount.getText().equals("")) ||
                            (comboBox_JobStatus.getSelectedItem().equals(""))) {
                        JOptionPane.showMessageDialog(null, "Fields are empty");}
                    else {
                        //once presence is checked all data is inputted into the database through the Prepared Statement
                        PreparedStatement pst1 = connection.prepareStatement("INSERT INTO JOB (JobID, Job_Description, Hours_Required, Gender_Required, " +
                                "Age_Required, Pay_Amount, Job_Status) VALUES (?,?,?,?,?,?,?)");
                        pst1.setString(1, tf_JobID.getText());
                        pst1.setString(2, Ta_JobDescript.getText());
                        pst1.setString(3, HoursRequires_comboBox.getSelectedItem().toString());
                        pst1.setString(4, Gender_comboBox.getSelectedItem().toString());
                        pst1.setString(5, Age_comboBox.getSelectedItem().toString());
                        pst1.setString(6, tF_PayAmount.getText());
                        pst1.setString(7, comboBox_JobStatus.getSelectedItem().toString());
                        pst1.execute();
                        JOptionPane.showMessageDialog(null, "Record was saved");
                        pst1.close();
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }





    public static void main(String[] args) {
        ACreate.setContentPane(new Admin_CreateJob().Admin_CreateJob);
        ACreate.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ACreate.pack();
        ACreate.setVisible(true);
    }


}
