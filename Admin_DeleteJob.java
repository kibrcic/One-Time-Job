package OneTimeJob;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Admin_DeleteJob {
    public JPanel Admin_DeleteJob;
    private JButton exitButton;
    private JTable table1;
    private JButton deleteButton;
    private JButton viewJobsButton;
    private JTextField tf_JobID;
    private JButton confirmButton;
    private JLabel Job_ID;
    static JFrame Adelete = new JFrame("Admin_DeleteJob");
    static Connection connection = null;

    public Admin_DeleteJob() {

        tf_JobID.setVisible(false);
        Job_ID.setVisible(false);
        confirmButton.setVisible(false);
        connection = Sqliteconnection.dbConnector();

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Adelete.dispose();
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
                try{
                    String query = "select JobID,Job_Description,Hours_Required,Gender_Required," +
                            "Age_Required,Pay_Amount,Job_Status from JOB";
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

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tf_JobID.setVisible(true);
                Job_ID.setVisible(true);
                confirmButton.setVisible(true);
                deleteButton.setEnabled(false);
            }
        });


        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String query_check = "select * from JOB where JobID= '"+ tf_JobID.getText()+"'";
                    PreparedStatement ps4 = connection.prepareStatement(query_check);
                    ResultSet r1 = ps4.executeQuery();

                   // An if statement that checks that the job ID inputted exists in the system
                    int loop = 0;
                    int result = 0;
                    while (r1.next()){
                        loop += 1;
                    }
                    if(loop == 1){
                        result = 1;
                    }
                    else {
                        result = 0;
                    }
                    r1.close();
                    ps4.close();

                    if ( result == 1){
                    //Prepared statement executes query for deleting jobs
                        String query_delete = "delete from JOB where JobID = '"+ tf_JobID.getText()+"'";
                        PreparedStatement pst3 = connection.prepareStatement(query_delete);
                        pst3.execute();
                        JOptionPane.showMessageDialog(null, "The JOB has been eliminated from the system");
                        pst3.close();

                        tf_JobID.setVisible(false);
                        Job_ID.setVisible(false);
                        confirmButton.setVisible(false);
                        deleteButton.setEnabled(true);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "The Job ID doesn't exist");
                    }


                }
                catch(Exception e3){}
            }
        });
    }

    public static void main(String[] args) {
        Adelete.setContentPane(new Admin_DeleteJob().Admin_DeleteJob);
        Adelete.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Adelete.pack();
        Adelete.setVisible(true);
    }

}
