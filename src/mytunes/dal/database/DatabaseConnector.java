/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.dal.database;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andreasvillumsen
 */
public class DatabaseConnector {
    private SQLServerDataSource dataSource;
    
    /**
     * DatabaseConnector constructor
     */
    public DatabaseConnector()  {
        Properties props = new Properties();
        try {
            props.load(new FileReader("DBSettings.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        dataSource = new SQLServerDataSource();
        dataSource.setDatabaseName(props.getProperty("database"));
        dataSource.setUser(props.getProperty("user"));
        dataSource.setPassword(props.getProperty("password"));
        dataSource.setServerName(props.getProperty("server"));
    }
    
    /**
     * Get the connection to the database
     * @return Database connection
     */
    public Connection getConnection() throws SQLServerException {
        return dataSource.getConnection();
    }
}
