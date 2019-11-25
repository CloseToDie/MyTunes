/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author andreasvillumsen
 */
public class SongMSSQLDAO implements SongDalFacade {
    
    public static void main(String[] args) {
    
    }
    
    private SQLServerDataSource ds = new SQLServerDataSource();

    public SongMSSQLDAO() {
        ds.setUser("CSe19A_2");
        ds.setPassword("CSe19A_2");
        ds.setPortNumber(1433);
        ds.setDatabaseName("hitlersOfreMyTunes");
        ds.setServerName("10.176.111.31");
    }
}
