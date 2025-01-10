package OneTimeJob;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Admin_EditJob {
    public JPanel Admin_EditJob;
    private JButton exitButton;
    private JTable table1;
    private JButton editButton;
    private JButton viewJobsButton;
    private JTextField tf_JobID;
    private JButton confirmButton;
    private JButton cancelButton;
    private JTextArea tA_Description;
    private JComboBox cB_Hours;
    private JComboBox cB_Gender;
    private JComboBox cB_Age;
    private JLabel L_JobID;
    private JLabel L_JobDescription;
    private JLabel L_Hours;
    private JLabel L_Gender;
    private JLabel L_Age;
    private JButton saveButton;
    static JFrame AEdit = new JFrame("Admin_EditJob");
    static Connection connection = null;


    public Admin_EditJob() {
        tf_JobID.setVisible(false);
        cancelButton.setVisible(false);
        confirmButton.setVisible(false);
        tA_Description.setVisible(false);
        cB_Hours.setVisible(false);
        cB_Gender.setVisible(false);
        cB_Age.setVisible(false);
        L_JobID.setVisible(false);
        L_JobDescription.setVisible(false);
        L_Hours.setVisible(false);
        L_Gender.setVisible(false);
        L_Age.setVisible(false);
        saveButton.setVisible(false);


        connection = Sqliteconnection.dbConnector();
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AEdit.dispose();
                JFrame frame = new JFrame("Administrator_Portal");
                frame.setContentPane(new Administrator_Portal().Administrator_Portal);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
        viewJobsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String query = "select JobID,Job_Description,Hours_Required,Gender_Required,Age_Required,Pay_Amount,Job_Status from JOB";
                    PreparedStatement pst = connection.prepareStatement(query);
                    ResultSet r = pst.executeQuery();
                    table1.setModel(DbUtils.resultSetToTableModel(r));
                    pst.close();
                    r.close();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }

        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tf_JobID.setVisible(true);
                cancelButton.setVisible(true);
                confirmButton.setVisible(true);
                tA_Description.setVisible(true);
                cB_Hours.setVisible(true);
                cB_Gender.setVisible(true);
                cB_Age.setVisible(true);
                L_JobID.setVisible(true);
                L_JobDescription.setVisible(true);
                L_Hours.setVisible(true);
                L_Gender.setVisible(true);
                L_Age.setVisible(true);
                saveButton.setVisible(true);
                editButton.setEnabled(false);
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tf_JobID.setVisible(false);
                cancelButton.setVisible(false);
                confirmButton.setVisible(false);
                tA_Description.setVisible(false);
                cB_Hours.setVisible(false);
                cB_Gender.setVisible(false);
                cB_Age.setVisible(false);
                L_JobID.setVisible(false);
                L_JobDescription.setVisible(false);
                L_Hours.setVisible(false);
                L_Gender.setVisible(false);
                L_Age.setVisible(false);
                saveButton.setVisible(false);
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String query_check = "select * from JOB where JobID= '"+ tf_JobID.getText()+"'";
                    PreparedStatement ps4 = connection.prepareStatement(query_check);
                    ResultSet r1 = ps4.executeQuery();
                    // An if statement that checks that the job ID inputted exists in the system
                    int loop = 0;
                    int result = 0;
                    while (r1.next()){
                        loop += 1;}
                    if(loop == 1){
                        result = 1;}
                    else {
                        result = 0;}
                    r1.close();
                    ps4.close();
                    //if statement validates that all fields have been filled
                    if ((tf_JobID.getText().equals("")) || (tA_Description.getText().equals(""))) {
                        JOptionPane.showMessageDialog(null, "Fields are empty");
                    }
                    if ( result == 0) {
                        JOptionPane.showMessageDialog(null, "The job ID doesn't exist");
                    }
                    if ((result == 1) & (!tf_JobID.getText().equals("") ) & (!tA_Description.getText().equals("")) ) {
                        connection = Sqliteconnection.dbConnector();
                    // prepared statement executes query that updates information on database
                        String query = "update JOB set Job_Description = '"+ tA_Description.getText() + "' , Hours_Required = " +
                                "'"+ cB_Hours.getSelectedItem().toString() +  "', Gender_Required = '" + cB_Gender.getSelectedItem().toString()
                                + "', Age_Required='" + cB_Age.getSelectedItem().toString() + "'where JobID = '"+ tf_JobID.getText() + "'";

                        PreparedStatement pst5 = connection.prepareStatement(query);
                        pst5.execute();
                        JOptionPane.showMessageDialog(null,"Fields where saved");



                    }

                }
                catch(Exception e7){
                    e7.printStackTrace();
                }

            }
        });
    }



    public static void main(String[] args) {
        AEdit.setContentPane(new Admin_EditJob().Admin_EditJob);
        AEdit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AEdit.pack();
        AEdit.setVisible(true);
    }

}
