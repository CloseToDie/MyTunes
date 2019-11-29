/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import mytunes.be.Song;
import mytunes.bll.SongManager;

/**
 *
 * @author andreasvillumsen
 */
public class AppModel {
    
    private ObservableList<Song> allSongs;
    private SongManager songManager; 
    private AppController controler;

    public AppModel() {
        
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
     
     
     
     }
     
     public Label getIsPlaying() {
        return controler.getIsPlaying();
    }
    
    
    
    
}
