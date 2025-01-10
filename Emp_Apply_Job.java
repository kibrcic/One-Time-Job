package OneTimeJob;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//The class Apply for a Job inherits the values present in the variables of gender and age from the employee login class
public class Emp_Apply_Job extends Login_Emp {
    public JPanel Emp_Apply_Job;
    private JButton exitButton;
    private JTable table1;
    private JButton viewJobsButton;
    private JTextField JobID;
    private JButton apply_Button;
    private JButton confirmButton;
    private JLabel job;
    static JFrame EApply = new JFrame("Emp_Apply_Job");
    static Connection connection = null;



    public Emp_Apply_Job() {
        JobID.setVisible(false);
        confirmButton.setVisible(false);
        job.setVisible(false);


        connection = Sqliteconnection.dbConnector();
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EApply.dispose();
                JFrame frame = new JFrame("Employee_Portal");
                frame.setContentPane(new Employee_Portal().Employee_Portal);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });

        viewJobsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //Through the use of the try-catch block and a query the user can view the existing jobs that fit to his/her gender and age.
                    //The variables Gender and Age inherited from the employee login class are used to filter the jobs.
                    // This filtering is performed by the use of a query that retrieves the filtered data.
                    String query= "select JobID,Job_Description,Hours_Required,Gender_Required,Age_Required,Pay_Amount," +
                            "Job_Status from JOB where Gender_Required='"
                            + Gender+"' and Age_Required='" + Age+"'";
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
        apply_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JobID.setVisible(true);
                confirmButton.setVisible(true);
                job.setVisible(true);
                apply_Button.setEnabled(false);
            }
        });
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String query_check = "select * from JOB where JobID= '"+ JobID.getText()+"'";
                    PreparedStatement ps4 = connection.prepareStatement(query_check);
                    ResultSet r1 = ps4.executeQuery();
                    // An if statement that checks that the job ID inputted exists in the system.
                    //If the Job ID exists, the application is saved and the method returns a value of 1, otherwise, it returns a value of 0.
                    // If it does exist, the username of the user, and the job ID are inserted into the database table “Applications”.
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
                    if ( result == 1){
                        String query = "INSERT INTO Applications (JobID, Username) VALUES (?,?)";
                        PreparedStatement pst8 = connection.prepareStatement(query);
                        pst8.setString(1,JobID.getText());
                        pst8.setString(2, Username);
                        pst8.execute();
                        JOptionPane.showMessageDialog(null, "Your applications has been successfully saved");
                        pst8.close();
                        JobID.setVisible(false);
                        confirmButton.setVisible(false);
                        job.setVisible(false);
                        apply_Button.setEnabled(true);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "The Job ID doesn't exist");
                    }


                }
                catch(Exception e8){
                    e8.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        EApply.setContentPane(new Emp_Apply_Job().Emp_Apply_Job);
        EApply.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        EApply.pack();
        EApply.setVisible(true);
    }
}
