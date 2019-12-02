/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.bll.SongManager;

/**
 *
 * @author andreasvillumsen
 */
public class AppModel {
    
     public static void main(String[] args) throws Exception {
        AppModel app = new AppModel();
        
        ArrayList<Song> songs = new ArrayList<>();
        songs.addAll(app.getAllSongs());
        System.out.println(songs.get(0).getTitle());
        
    }
    
    private ObservableList<Song> allSongs;
    private ObservableList<Playlist> allPlaylist;
    private SongManager songManager; 
    private AppController controler;

    public AppModel() throws Exception {
        
        songManager = new SongManager();
        allSongs = FXCollections.observableArrayList();
        allSongs.addAll(songManager.getAllSongs());
    }
     public ObservableList<Song> getAllSongs()
    {
        return allSongs;
    }
     
     
      /**
     * calls the search function of the songmanager.
     *
     * @param query
     * 
     */
     public void search(String query){
     //TO-DO implement the search
             if (query.isEmpty())
        {
            allSongs.clear();
           allSongs.addAll(songManager.getAllSongs());
        } else
        {
            allSongs.clear();
            allSongs.addAll(songManager.search(query));
        }
     
     
     }
     
     public Label getIsPlaying() {
        return controler.getIsPlaying();
    }
    
    
    
    
}
