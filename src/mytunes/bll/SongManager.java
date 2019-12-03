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
     private SongFacade songDBDAO;
     
    public static void main(String[] args) throws Exception {
        SongManager songm = new SongManager();
        
        ArrayList<Song> songs = new ArrayList<>();
        songs.addAll(songm.getAllSongs());
        System.out.println(songs.get(0).getTitle());
        
    }
    
   
    
    public SongManager() throws Exception
    {
        songDBDAO = new SongDBDAO();

    }
    
     public List<Song> getAllSongs()
     {
        return songDBDAO.getAllSongs();
     }
        
    


 public List<Song> search(String query) 
    {
        List<Song> searchBase = songDBDAO.getAllSongs();
        List<Song> result = new ArrayList<>();

        for (Song song : searchBase)
        {
            if (song.getTitle().toLowerCase().contains(query.toLowerCase()))
            {
                result.add(song);
            }
            else if(song.getArtist().toLowerCase().contains(query.toLowerCase()))
                    {
                        result.add(song);
                    }
            else if(song.getCategory().toLowerCase().contains(query.toLowerCase()))
            {
                result.add(song);
            }
        }
        return result;
    }

    public void createSong(Song songToAdd)
    {
        songDBDAO.createSong(songToAdd);
    }
    
    
    public void deleteSong(Song songToDelete)
    {
        songDBDAO.deleteSong(songToDelete);
    }
}