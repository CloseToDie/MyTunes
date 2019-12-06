/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.bll.MusicPlayer;
import mytunes.bll.SongManager;
import mytunes.bll.PlayListManager;

/**
 *
 * @author andreasvillumsen
 */
public class AppModel {
    private  MusicPlayer musicPlayer;
    
     public static void main(String[] args) throws Exception {
        AppModel app = new AppModel();
        
        ArrayList<Song> songs = new ArrayList<>();
        songs.addAll(app.getAllSongs());
        System.out.println(songs.get(0).getTitle());
        
    }
    
    private final ObservableList<Song> allSongs;
    private ObservableList<Playlist> allPlaylist;
    private ObservableList<Song> songsInPlaylist;
    private SongManager songManager; 
    private PlayListManager pm;
    private AppController controler;

    public AppModel() throws Exception {
        
        songManager = new SongManager();
        allSongs = FXCollections.observableArrayList();
        allSongs.addAll(songManager.getAllSongs());
        
        
        pm = new PlayListManager();
        allPlaylist = FXCollections.observableArrayList();
        allPlaylist.addAll(pm.getAllPlaylists());
        
        songsInPlaylist = FXCollections.observableArrayList();
        songsInPlaylist.addAll(pm.getAllSongsInPlaylist(pm.getAllPlaylists().get(0)));
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
     
     public ObservableList<Playlist> getAllPlaylist() {
        allPlaylist.clear();
        allPlaylist.addAll(pm.getAllPlaylists());
        return allPlaylist;
    }
     
     public ObservableList<Song> getSongsInPlaylist(Playlist playlist)
             {
                 songsInPlaylist.clear();
                 songsInPlaylist.addAll(pm.getAllSongsInPlaylist(playlist));
                 return songsInPlaylist;
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

    
    

    void createMusicPlayer(String path)
    {
        musicPlayer = new MusicPlayer(path);
    }

    public MusicPlayer getmusicPlayer()
    {
        return musicPlayer;
    }

    void createSong(Song songToAdd)
    {
        songManager.createSong(songToAdd);
        songClearAdd();
    }
    
    
    public void deleteSong(Song songToDelete)
    {
        songManager.deleteSong(songToDelete);
        songClearAdd();
    }

    
    public void updateSong(Song SongToUpdate)
    {
        songManager.updateSong(SongToUpdate);
        songClearAdd();
    }
    
    
    
    void createPlaylist(Playlist playlistToAdd)
    {
        pm.createPlaylist(playlistToAdd);
        playlistClearAdd();
    }
    
    
    public void deletePlaylist(Playlist playlistToDelete)
    {
        pm.deletePlaylist(playlistToDelete);
        playlistClearAdd();
    }

    
    public void updatePlaylist(Playlist PlaylistToUpdate)
    {
        pm.updatePlaylist(PlaylistToUpdate);
        playlistClearAdd();
    }
    
    public void addToPlaylist(Playlist playlist, Song song, int position)
    {
        pm.addToPlaylist(playlist, song, position);
        songInPlaylistClearAdd(playlist);
    }
    
    public void clearPlaylist(Playlist playlist)
    {
        pm.clearPlaylist(playlist);
    }
    
    
    public void playlistClearAdd()
    {
        allPlaylist.clear();
        allPlaylist.addAll(pm.getAllPlaylists());
    }
    
    public void songClearAdd()
    {
        allSongs.clear();
        allSongs.addAll(songManager.getAllSongs());
    }
    
    public void songInPlaylistClearAdd(Playlist playlist)
    {
        songsInPlaylist.clear();
        songsInPlaylist.addAll(pm.getAllSongsInPlaylist(playlist));
    }
    
    public void orderPlaylist(Playlist playlist, Song song, int position, boolean direction)
    {
        pm.orderPlaylist(playlist, song, position, direction);
        songInPlaylistClearAdd(playlist);
    }
    
}
