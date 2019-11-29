/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.bll;

import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.be.Song;
import mytunes.dal.SongFacade;
import mytunes.dal.database.SongDBDAO;

/**
 *
 * @author anton
 */

public class SongManager {
    
    private SongFacade songDBDAO;
    
    public SongManager() throws Exception
    {
        songDBDAO = new SongDBDAO();

    }
    
     public List<Song> getAllSongs()
     {
        return songDBDAO.getAllSongs();
     }
        
    


public void fetchData() {
    
    
    
    
    }
}