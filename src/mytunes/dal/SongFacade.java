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
    public List<Song> getAllSongs();
}
