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
        
        for (Song song : songs) {
            
            System.out.println(song.getPath());
        }
        
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
        
        //check if there are any playlists
        if(pm.getAllPlaylists().size() != 0){
        songsInPlaylist.addAll(pm.getAllSongsInPlaylist(pm.getAllPlaylists().get(0)));
        }
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
     
     /**
      * returns all playlists in the database
      * @return 
      */
     public ObservableList<Playlist> getAllPlaylist() {
        allPlaylist.clear();
        allPlaylist.addAll(pm.getAllPlaylists());
        return allPlaylist;
    }
     
     /**
      * returns all the songs in a given playlist
      * @param playlist
      * @return 
      */
     public ObservableList<Song> getSongsInPlaylist(Playlist playlist)
             {
                 songsInPlaylist.clear();
                 songsInPlaylist.addAll(pm.getAllSongsInPlaylist(playlist));
                 return songsInPlaylist;
             }
 
     
     
      /**
     * calls the search function of the songmanager.
     * @param query
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
     
     /**
      * returning the isPlaying label
      * @return 
      */
     public Label getIsPlaying() {
        return controler.getIsPlaying();
    }

    
    

     /**
      * creates a new musicplayer
      * @param path 
      */
    void createMusicPlayer(String path)
    {
        musicPlayer = new MusicPlayer(path);
    }

    /**
     * returns the musicplayer
     * @return 
     */
    public MusicPlayer getmusicPlayer()
    {
        return musicPlayer;
    }

    /**
     * adds a new song to the database
     * @param songToAdd 
     */
    void createSong(Song songToAdd)
    {
        songManager.createSong(songToAdd);
        songClearAdd();
    }
    
    /**
     * deletes a chosen song from the database
     * @param songToDelete 
     */
    public void deleteSong(Song songToDelete)
    {
        songManager.deleteSong(songToDelete);
        songClearAdd();
    }

    /**
     * updates a songs data
     * @param SongToUpdate 
     */
    public void updateSong(Song SongToUpdate)
    {
        songManager.updateSong(SongToUpdate);
        songClearAdd();
    }
    
    
    /**
     * creates a new playlist in the database
     * @param playlistToAdd 
     */
    void createPlaylist(Playlist playlistToAdd)
    {
        pm.createPlaylist(playlistToAdd);
        songInPlaylistClearAdd(playlistToAdd);
        playlistClearAdd();
    }
    
    /**
     * deletes a playlist from the database
     * @param playlistToDelete 
     */
    public void deletePlaylist(Playlist playlistToDelete)
    {
        pm.deletePlaylist(playlistToDelete);
        songInPlaylistClearAdd(playlistToDelete);
        playlistClearAdd();
    }

    /**
     * updates a playlists data
     * @param PlaylistToUpdate 
     */
    public void updatePlaylist(Playlist PlaylistToUpdate)
    {
        pm.updatePlaylist(PlaylistToUpdate);
        songInPlaylistClearAdd(PlaylistToUpdate);
        playlistClearAdd();
        
    }
    
    /**
     * adds a song to a given playlist
     * @param playlist
     * @param song
     * @param position 
     */
    public void addToPlaylist(Playlist playlist, Song song, int position)
    {
        pm.addToPlaylist(playlist, song, position);
        songInPlaylistClearAdd(playlist);
        allPlaylist.clear();
        allPlaylist.addAll(pm.getAllPlaylists());
    }
    
    /**
     * updates the list of playlists
     * @param playlist 
     */
    public void clearPlaylist(Playlist playlist)
    {
        songInPlaylistClearAdd(playlist);
        pm.clearPlaylist(playlist);
    }
    
    /**
     * Gets playlist data
     */
    public void playlistClearAdd()
    {
        allPlaylist.clear();
        allPlaylist.addAll(pm.getAllPlaylists());
    }
    
    /**
     * Gets song data
     */
    public void songClearAdd()
    {
        allSongs.clear();
        allSongs.addAll(songManager.getAllSongs());
    }
    
    /**
     * gets the data from the songs in a playlist
     * @param playlist 
     */
    public void songInPlaylistClearAdd(Playlist playlist)
    {
        songsInPlaylist.clear();
        songsInPlaylist.addAll(pm.getAllSongsInPlaylist(playlist));
    }
    
    /**
     * moves a songs postion in a playlist
     * @param playlist
     * @param song
     * @param position
     * @param direction 
     */
    public void orderPlaylist(Playlist playlist, Song song, int position, boolean direction)
    {
        pm.orderPlaylist(playlist, song, position, direction);
        songInPlaylistClearAdd(playlist);
    }
    
    /**
     * clears all songs form a playlist
     * @param playlist
     * @param song
     * @param position
     * @return 
     */
    public boolean clearSongFromPlaylist(Playlist playlist ,Song song ,int position)
    {
        boolean result = pm.clearSongFromPlaylist(playlist,song,position);
         songsInPlaylist.clear();
        songsInPlaylist.addAll(pm.getAllSongsInPlaylist(playlist));
        allPlaylist.clear();
        allPlaylist.addAll(pm.getAllPlaylists());
        return result;
    }
    
    
}
