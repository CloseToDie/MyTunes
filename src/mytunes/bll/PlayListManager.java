/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.bll;

import java.util.List;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.dal.PlaylistFacade;
import mytunes.dal.database.PlaylistDBDAO;

/**
 *
 * @author anton
 */
public class PlayListManager {
    private PlaylistFacade pD;

    /**
     * PlaylistManager constructor
     */
    public PlayListManager() throws Exception {
       pD = new PlaylistDBDAO();
    }
 
    /**
     * List of playlists
     * @return list of playlists
     */
    public List<Playlist> getAllPlaylists() {
        List<Playlist> result = pD.getAllPlaylists();
        
        for (Playlist playlist : result) {w
            playlist.setTime(playlistTotalTime(getAllSongsInPlaylist(playlist)));
            playlist.setSongs(pD.getAllSongsInPlaylist(playlist).size());
        }
        
        return result;
    }

    /**
     * Create a playlist
     * @param playlist
     */
    public void createPlaylist(Playlist playlistToAdd) {
        pD.createPlaylist(playlistToAdd);
    }

    /**
     * Delete a playlist
     * @param playlist
     */
    public void deletePlaylist(Playlist playlistToDelete) {
        pD.deletePlaylist(playlistToDelete);
    }

    /**
     * Update a playlist
     * @param playlist
     */
    public void updatePlaylist(Playlist playlistToUpdate) {
        pD.updatePlaylist(playlistToUpdate);
    }
    
    /**
     * Get all songs in a playlist
     * @return list of songs in playlist
     */
    public List<Song> getAllSongsInPlaylist(Playlist playlist) {
        return pD.getAllSongsInPlaylist(playlist);
    }
    
    /**
     * Add a song to a playlist
     * @param playlist
     * @param song
     * @param position
     */
    public void addToPlaylist(Playlist playlist, Song song, int position) {
        pD.addToPlaylist(playlist, song, position);
    }
    
    /**
     * Clear songs in playlist
     * @param playlist
     */
    public void clearPlaylist(Playlist playlist) {
        pD.clearPlaylist(playlist);
    }
    
    /**
     * Change the order of the songs in a playlist
     * @param playlist
     * @param song
     * @param position
     * @param direction
     */
    public void orderPlaylist(Playlist playlist, Song song, int position, boolean direction) {
        pD.orderPlaylist(playlist, song, position, direction);
    }
    
    /**
     * Clear a song a playlist
     * @param playlist
     * @param song
     * @param position
     * @return boolean
     */
    public boolean clearSongFromPlaylist(Playlist playlist, Song song, int position) {
        return pD.clearSongFromPlaylist(playlist, song, position);
    }
    
    /**
     * The total amount of playtime in the playlist
     * @param songs
     * @return totalTime
     */
    public int playlistTotalTime(List<Song> songs) {
        int totalTime = 0;
        totalTime = songs.stream().map((song) -> song.getTime()).reduce(totalTime, Integer::sum);
        return totalTime;
    }
 
}