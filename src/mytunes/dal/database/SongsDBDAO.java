/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.dal.database;

/**
 *
 * @author andreasvillumsen
 */
public class SongsDBDAO {
    private DatabaseConnector dbCon;
    
    public SongsDBDAO() throws Exception {
        dbCon = new DatabaseConnector();
    }
    
    
}
