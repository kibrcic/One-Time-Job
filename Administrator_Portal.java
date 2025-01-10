package OneTimeJob;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Administrator_Portal {
    public JPanel Administrator_Portal;
    private JButton exitButton;
    private JButton editExistingJobProfilesButton;
    private JButton viewProspectiveEmployeeJobButton;
    private JButton deleteCompletedOrNonButton;
    private JButton viewProspectEmployeeJobButton;
    private JButton createNewJobProfileButton;
    static JFrame Admin_Portal = new JFrame("Administrator_Portal");

    public Administrator_Portal(){

        createNewJobProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Admin_Portal.dispose();
                JFrame frame = new JFrame("Admin_CreateJob");
                frame.setContentPane(new Admin_CreateJob().Admin_CreateJob);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Admin_Portal.dispose();
                JFrame frame = new JFrame("Login_Admin");
                frame.setContentPane(new Login_Admin().Login_Admin);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
        viewProspectEmployeeJobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Admin_Portal.dispose();
                JFrame frame = new JFrame("Admin_ViewJobs");
                frame.setContentPane(new Admin_ViewJobs().Admin_ViewJobs);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
        deleteCompletedOrNonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Admin_Portal.dispose();
                JFrame frame = new JFrame("Admin_DeleteJob");
                frame.setContentPane(new Admin_DeleteJob().Admin_DeleteJob);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
        editExistingJobProfilesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Admin_Portal.dispose();
                JFrame frame = new JFrame("Admin_EditJob");
                frame.setContentPane(new Admin_EditJob().Admin_EditJob);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
        viewProspectiveEmployeeJobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Admin_Portal.dispose();
                JFrame frame = new JFrame("Admin_Inquires");
                frame.setContentPane(new Admin_Inquires().Admin_Inquires);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
    public static void main(String[] args) {
        Admin_Portal.setContentPane(new Administrator_Portal().Administrator_Portal);
        Admin_Portal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Admin_Portal.pack();
        Admin_Portal.setVisible(true);
    }





}

