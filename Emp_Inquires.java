package OneTimeJob;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Emp_Inquires {
    public JPanel Emp_Inquires;
    private JTextField tf_Inquires;
    private JButton SUBMITButton;
    private JButton exitButton;
    static JFrame EInquires = new JFrame("Emp_Inquires");
    static Connection connection = null;

    public Emp_Inquires() {
        connection = Sqliteconnection.dbConnector();
        //When button is pressed action listener responds by jumping to the corresponding interface
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EInquires.dispose();
                JFrame frame = new JFrame("Employee_Portal");
                frame.setContentPane(new Employee_Portal().Employee_Portal);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
        SUBMITButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Employee comments/feedback are inserted into the table INQUIRES by the use of a query along with a Prepared Statement that executes it.
                try{
                    PreparedStatement pst3 = connection.prepareStatement("INSERT INTO Comments (Message) VALUES (?) ");
                    pst3.setString(1, tf_Inquires.getText());
                    pst3.execute();
                    JOptionPane.showMessageDialog(null, "Record was saved");
                    pst3.close();
                }
                catch (Exception e4){
                    e4.printStackTrace();
                }
            }
        });
    }





    public static void main(String[] args) {
        EInquires.setContentPane(new Emp_Inquires().Emp_Inquires);
        EInquires.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        EInquires.pack();
        EInquires.setVisible(true);
    }
}
