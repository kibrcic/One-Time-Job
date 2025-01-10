package OneTimeJob;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Admin_Inquires {

    static JFrame AInquires = new JFrame("Admin_Inquires");
    public JPanel Admin_Inquires;
    private JButton exitButton;
    private JTable table1;
    private JButton viewEmployeeFeedbackButton;
    static Connection connection = null;

    public Admin_Inquires() {
        connection = Sqliteconnection.dbConnector();
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AInquires.dispose();
                JFrame frame = new JFrame("Administrator_Portal");
                frame.setContentPane(new Administrator_Portal().Administrator_Portal);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
        viewEmployeeFeedbackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String query = "select Message from Comments";
                    PreparedStatement pst = connection.prepareStatement(query);
                    ResultSet r = pst.executeQuery();
                    table1.setModel(DbUtils.resultSetToTableModel(r));
                    pst.close();
                    r.close();
                }
                catch(Exception e3){e3.printStackTrace();}
                }
            });
        }


    public static void main(String[] args) {
        AInquires.setContentPane(new Admin_Inquires().Admin_Inquires);
        AInquires.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AInquires.pack();
        AInquires.setVisible(true);
    }
}
