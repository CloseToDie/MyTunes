/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.dal;

import java.util.List;
import mytunes.be.Song;

/**
 *
 * @author andreasvillumsen
 */
public interface SongFacade {
    
    /**
     * List over all songs in database
     * @return list of songs
     */
    public List<Song> getAllSongs();
    
     /**
     * Create a new song in the database
     * @param song
     * @return boolean
     */
    public Song createSong(Song song);
    
    /**
     * Updates the database
     * @param song
     * @return boolean
     */
    public boolean updateSong(Song song);
    
    /**
     * Deletes a song from the database
     * @param song
     * @return boolean
     */
    public boolean deleteSong(Song song);
}
