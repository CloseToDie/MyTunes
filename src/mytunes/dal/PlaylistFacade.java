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
 *
 * @author anton
 */
public interface PlaylistFacade {
    
    public List<Playlist> getAllPlaylists();
    
    public Playlist createPlaylist(Playlist playlist);
    
    public boolean updatePlaylist(Playlist playlist);
    
    public boolean deletePlaylist(Playlist playlist);
    
    public List<Song> getAllSongsInPlaylist(Playlist playlist);
    
    public boolean clearPlaylist(Playlist playlist);
    
    public boolean addToPlaylist(Playlist playlist, Song song, int position);
    
}
