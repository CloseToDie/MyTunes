/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.dal;

import java.util.List;
import mytunes.be.Playlist;
import mytunes.be.Song;

/**
 *the interface connecting the playlistBLL to the playlistDAl
 * @author anton
 */
public interface PlaylistFacade {
    
/**
     * Get a list of all playlists from the database
     *
     * @return list of playlist or null
     */
    public List<Playlist> getAllPlaylists();

    /**
     * Create a new playlist in the database
     *
     * @param playlist
     * @return playlist or null
     */
    public Playlist createPlaylist(Playlist playlist);

    /**
     * Update a playlist in the database
     *
     * @param playlist
     * @return boolean
     */
    public boolean updatePlaylist(Playlist playlist);

    public boolean deletePlaylist(Playlist playlist);

    /**
     * Get all the songs in a playlist from the database
     *
     * @param playlist
     * @return list of songs
     */
    public List<Song> getAllSongsInPlaylist(Playlist playlist);

    /**
     * Deletes all the songs in a playlist from the database.
     *
     * @param playlist
     * @return boolean
     */
    public boolean clearPlaylist(Playlist playlist);

    /**
     * Add a song to a playlist in database
     *
     * @param playlist
     * @param song
     * @param position
     * @return boolean
     */
    public boolean addToPlaylist(Playlist playlist, Song song, int position);

    /**
     * Change the order of the songs in a playlist in the database
     *
     * @param playlist
     * @param song
     * @param position
     * @param direction
     * @return bolean indicating if the operation was successfull
     */
    public boolean orderPlaylist(Playlist playlist, Song song, int position, boolean direction);

    /**
     * Deletes a single song from a playlist. It will also adjusts the positions
     * after the deletion so they align with the new positions.
     *
     * @param playlist
     * @param song
     * @param position
     * @return boolean indicating if the deletion was successfull
     */
    public boolean clearSongFromPlaylist(Playlist playlist, Song song, int position);

}
