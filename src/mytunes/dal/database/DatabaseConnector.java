/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.dal.database;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.FileReader;
import java.sql.Connection;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author andreasvillumsen
 */
public class DatabaseConnector {
    private SQLServerDataSource dataSource;
    
    public DatabaseConnector() throws IOException {
        Properties props = new Properties();
        props.load(new FileReader("DBSettings.dat"));
        dataSource = new SQLServerDataSource();
        dataSource.setDatabaseName(props.getProperty("database"));
        dataSource.setUser(props.getProperty("user"));
        dataSource.setPassword(props.getProperty("password"));
        dataSource.setServerName(props.getProperty("server"));
    }
    
    public Connection getConnection() throws SQLServerException {
        return dataSource.getConnection();
    }
}
