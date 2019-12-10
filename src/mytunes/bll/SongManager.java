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
 *contains the business logic for the songs. it also connects the BLL and the DAL.
 * @author antoni
 */

public class SongManager {
    private SongFacade songDBDAO;
    
    /**
     * SongManager constructor
     */
    public SongManager() throws Exception {
        songDBDAO = new SongDBDAO();
    }
    
    /**
     * Get a list of all songs
     * @return list of songs
     */
    public List<Song> getAllSongs() {
       return songDBDAO.getAllSongs();
    }

    /**
     * Search for a song
     * @param query
     * @return song results
     */
    public List<Song> search(String query) {
        List<Song> searchBase = songDBDAO.getAllSongs();
        List<Song> result = new ArrayList<>();

        for (Song song : searchBase) {
            if (song.getTitle().toLowerCase().contains(query.toLowerCase())) {
                result.add(song);
            } else if(song.getArtist().toLowerCase().contains(query.toLowerCase())) {
                result.add(song);
            } else if(song.getCategory().toLowerCase().contains(query.toLowerCase())) {
                result.add(song);
            }
        }
        return result;
    }

    /**
     * Create a new song
     * @param song
     */
    public void createSong(Song songToAdd)
    {
        songDBDAO.createSong(songToAdd);
    }
    
    /**
     * Delete a song
     * @param song
     */
    public void deleteSong(Song songToDelete)
    {
        songDBDAO.deleteSong(songToDelete);
    }
    
    /**
     * Update a song
     * @param song
     */
    public void updateSong(Song SongToUpdate)
    {
        songDBDAO.updateSong(SongToUpdate);
    }
}