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

    public PlayListManager() throws Exception 
    {
       pD = new PlaylistDBDAO();
    }
 
    public List<Playlist> getAllPlaylists()
    {
       return pD.getAllPlaylists();
    }

    public void createPlaylist(Playlist playlistToAdd)
    {
        pD.createPlaylist(playlistToAdd);
    }

    public void deletePlaylist(Playlist playlistToDelete)
    {
        pD.deletePlaylist(playlistToDelete);
    }

    public void updatePlaylist(Playlist playlistToUpdate)
    {
        pD.updatePlaylist(playlistToUpdate);
    }
    
    public List<Song> getAllSongsInPlaylist(Playlist playlist)
    {
        return pD.getAllSongsInPlaylist(playlist);
    }
    
    public void addToPlaylist(Playlist playlist, Song song, int position)
    {
        pD.addToPlaylist(playlist, song, position);
    }
    
    public void clearPlaylist(Playlist playlist)
    {
        pD.clearPlaylist(playlist);
    }
 
}