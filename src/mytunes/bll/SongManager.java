/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.bll;

import java.io.IOException;
import java.util.ArrayList;
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
    
    public static void main(String[] args) throws Exception {
        SongManager songm = new SongManager();
        
        ArrayList<Song> songs = new ArrayList<>();
        songs.addAll(songm.getAllSongs());
        System.out.println(songs.get(0).getTitle());
        
    }
    
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


 public List<Song> search(String query) 
    {
        List<Song> searchBase = songDBDAO.getAllSongs();
        List<Song> result = new ArrayList<>();

        for (Song movie : searchBase)
        {
            if (movie.getTitle().toLowerCase().contains(query.toLowerCase()))
            {
                result.add(movie);
            }
        }
        return result;
    }
}