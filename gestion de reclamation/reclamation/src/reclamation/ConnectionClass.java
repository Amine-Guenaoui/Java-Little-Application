/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reclamation;

import static java.lang.Class.forName;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



/**
 *
 * @author amine
 */

public class ConnectionClass {
public Connection connection;
    public Connection getConnection() throws SQLException
    {
        String dbName ="reclamation";
        String UserName ="root";
        String password ="";
        
        try {
           Class.forName("com.mysql.jdbc.Driver");
            connection =DriverManager.getConnection("jdbc:mysql://localhost/"+dbName,UserName,password);
            
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
}
    
}
