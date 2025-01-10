package OneTimeJob;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Employee_Portal {
    public JPanel Employee_Portal;
    private JButton btn_exit;
    private JButton btn_Inquires;
    private JButton btn_ApplyJob;

    static JFrame EPortal = new JFrame("Employee_Portal");
    public Employee_Portal(){

// each button redirects the user to the corresponding interface through the ActionListener
        btn_Inquires.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EPortal.dispose();
                JFrame frame = new JFrame("Emp_Inquires");
                frame.setContentPane(new Emp_Inquires().Emp_Inquires);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
        btn_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EPortal.dispose();
                JFrame frame = new JFrame("Login_Emp");
                frame.setContentPane(new Login_Emp().Login_Emp);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

            }
        });
        btn_ApplyJob.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EPortal.dispose();
                JFrame frame = new JFrame("Emp_Apply_Job");
                frame.setContentPane(new Emp_Apply_Job().Emp_Apply_Job);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });


    }

    public static void main(String[] args) {
        EPortal.setContentPane(new Employee_Portal().Employee_Portal);
        EPortal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        EPortal.pack();
        EPortal.setVisible(true);
    }

}
