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
import mytunes.bll.playListManager;

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
    
    private final ObservableList<Song> allSongs;
    private ObservableList<Playlist> allPlaylist;
    private SongManager songManager; 
    private playListManager pm;
    private AppController controler;

    public AppModel() throws Exception {
        
        songManager = new SongManager();
        allSongs = FXCollections.observableArrayList();
        allSongs.addAll(songManager.getAllSongs());
        
        
        pm = new playListManager();
        allPlaylist = FXCollections.observableArrayList();
        allPlaylist.addAll(pm.getAllPlaylists());
    }
    /**
     * returns all songs in the database
     * @return 
     */
     public ObservableList<Song> getAllSongs()
    {
        allSongs.clear();
         allSongs.addAll(songManager.getAllSongs());
        return allSongs;
    }
     
     ObservableList<Playlist> getAllPlaylist() {
        allPlaylist.clear();
        allPlaylist.addAll(pm.getAllPlaylists());
        return allPlaylist;
    }
     
     
     public void fetchData() {
         
    allSongs.clear();
    allSongs.addAll(songManager.getAllSongs());
    
    
    }
     
     
      /**
     * calls the search function of the songmanager.
     *
     * @param query
     * 
     */
     public void search(String query){     
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

    void createSong(Song songToAdd)
    {
        songManager.createSong(songToAdd);
        allSongs.clear();
        allSongs.addAll(songManager.getAllSongs());
    }
    
    
    
    
}
