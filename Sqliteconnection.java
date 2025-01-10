package OneTimeJob;

import javax.swing.*;
import java.sql.*;



public class Sqliteconnection {
    Connection con = null;
    public static Connection dbConnector(){
        try{
           Class.forName("org.sqlite.JDBC");
           Connection con = DriverManager.getConnection("jdbc:sqlite:/Users/kiarabrcic/Documents/ComputerScience_IA/src/OneTimeJob/OneTimeJob.sqlite");
           JOptionPane.showMessageDialog(null, "connection successful");
           return con;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            return null;

        }

    }
}
