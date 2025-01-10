package OneTimeJob;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Admin_ViewJobs {

    static Connection connection = null;
    public JPanel Admin_ViewJobs;
    private JButton exitButton;
    private JTable table1;
    private JButton confirmAJobButton;
    private JTextField tf_JobID;
    private JButton viewJobsButton;
    private JLabel L_JobID;
    private JButton confirmButton;
    private JComboBox comboBox1;
    static JFrame AView = new JFrame("Administrator_Portal");


    public Admin_ViewJobs() {
        tf_JobID.setVisible(false);
        L_JobID.setVisible(false);
        comboBox1.setVisible(false);
        confirmButton.setVisible(false);

        connection = Sqliteconnection.dbConnector();
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AView.dispose();
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
                    String query = "select * from Applications";
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
        confirmAJobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tf_JobID.setVisible(true);
                L_JobID.setVisible(true);
                comboBox1.setVisible(true);
                confirmButton.setVisible(true);
                confirmAJobButton.setEnabled(false);

            }
        });
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String query_check = "select * from JOB where JobID= '" + tf_JobID.getText() + "'";
                    PreparedStatement ps7 = connection.prepareStatement(query_check);
                    ResultSet r1 = ps7.executeQuery();
                    // An if statement that checks that the job ID inputted exists in the system
                    int loop = 0;
                    int result = 0;
                    while (r1.next()) {
                        loop += 1;}
                    if (loop == 1) {
                        result = 1;
                    } else {
                        result = 0;
                    }
                    r1.close();
                    ps7.close();

                    if (result == 0) {
                        JOptionPane.showMessageDialog(null, "The job ID doesn't exist");
                    }
                    if (result == 1) {
                        //when admin confirms a job prepared statement executes query that updates the Job status from pending to confirmed
                        connection = Sqliteconnection.dbConnector();
                        String query = "update JOB set Job_Status = '" + comboBox1.getSelectedItem().toString() +
                                "'where JobID ='"+tf_JobID.getText()+"'";
                        // status of the job is changed from pending to confirmed in database Applications
                        String query2 = "update Applications set Job_Status = '" + comboBox1.getSelectedItem().toString() +
                                "' where JobID='" + tf_JobID.getText()+"'";

                        PreparedStatement pst5 = connection.prepareStatement(query);
                        PreparedStatement pst6 = connection.prepareStatement(query2);

                        pst5.execute();
                        pst6.execute();
                        JOptionPane.showMessageDialog(null, "Fields where saved");
                        pst5.close();
                        pst6.close();


                    }
                } catch (Exception e7) {
                    e7.printStackTrace();
                }

            }
        })
        ;
    }

    public static void main(String[] args) {
        AView.setContentPane(new Admin_ViewJobs().Admin_ViewJobs);
        AView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AView.pack();
        AView.setVisible(true);
    }

}

