/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.dal.database;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.dal.PlaylistFacade;

/**
 *
 * @author andreasvillumsen
 */
public class PlaylistDBDAO implements PlaylistFacade{
    
    public static void main(String[] args) throws Exception {
        
        PlaylistDBDAO dao;
        dao = new PlaylistDBDAO();
        
       // dao.createPlaylist(new Playlist(0, "acustic depression"));
        
       //dao.clearPlaylist(new Playlist(1, "stuf", 0, 0));
       //dao.addToPlaylist( new Playlist(1, "name", 0, 0), new Song(42, "title", "album", "artist", "category", 0, "path"), 2);
      // dao.deletePlaylist(new Playlist(1, "name", 0, 0));
      //dao.orderPlaylist(new Playlist(2, "acustic", 0, 0), new Song(43, "title", "album", "artist", "category", 0, "path"), 2, true);
        
   // System.out.println(  dao.clearSongFromPlaylist(new Playlist(5, "name", 0, 0), new Song(55, "title", "album", "artist", "category", 0, "path"), 1));
        for (Song song : dao.getAllSongsInPlaylist(new Playlist(5, "acustic", 0, 0,"antoni"))) {
            
            System.out.println( "" + song.getId() + "   "+ song);
            System.out.println("");
        }
    }
    
    private final DatabaseConnector dbCon;
    
    public PlaylistDBDAO() throws Exception {
        dbCon = new DatabaseConnector();
    }
    
    public List<Playlist> getAllPlaylists() {
        ArrayList<Playlist> playlists = new ArrayList<>();
        
        try(Connection con = dbCon.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM playlist");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                playlists.add(new Playlist(id, name,1,1,"antoni"));
            }
            return playlists;
            
        } catch(SQLServerException ex) {
            ex.printStackTrace();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return null;
    }
    
    public List<Song> getAllSongsInPlaylist(Playlist playlist) {
        ArrayList<Song> songs = new ArrayList<>();
        
        try(Connection con = dbCon.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT "
                    + "song_playlist.playlistid, song_playlist.position, "
                    + "song_playlist.songid,song.id,song.title,song.artist,"
                    + "song.category,song.time,song.path,song.album " 
                    + "FROM song_playlist " 
                    + "INNER JOIN song ON song_playlist.songid = song.id " 
                    + "WHERE song_playlist.playlistid = ? " 
                    + "ORDER BY song_playlist.position ASC");
            ps.setInt(1, playlist.getId());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String album = rs.getString("album");
                String artist = rs.getString("artist");
                String category = rs.getString("category");
                int time = rs.getInt("time");
                String path = rs.getString("path");
                songs.add(new Song(id, title, album, artist, category, time, path, "dafwfawf"));
            }
            return songs;
            
        } catch(SQLServerException ex) {
            ex.printStackTrace();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return null;
    }
    
    public Playlist createPlaylist(Playlist playlist) {
        try(Connection con = dbCon.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO playlist "
                    + "(name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, playlist.getName());
            
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            
            if(rs.next()) {
                playlist.setId((int) rs.getLong(1));
            } else {
                return null;
            }
            
            return playlist;
            
        } catch(SQLServerException ex) {
            ex.printStackTrace();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return null;
    }
    
    public boolean addToPlaylist(Playlist playlist, Song song, int position) {
        try(Connection con = dbCon.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO song_playlist "
                    + "(songid, playlistid, position) VALUES (?,?,?)");
            ps.setInt(1, song.getId());
            ps.setInt(2, playlist.getId());
            ps.setInt(3, position);
            
            return ps.executeUpdate() > 0;
            
        } catch(SQLServerException ex) {
            ex.printStackTrace();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return false;
    }
    
    public boolean updatePlaylist(Playlist playlist) {
        try(Connection con = dbCon.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE playlist SET name = ? WHERE id = ?");
            ps.setString(1, playlist.getName());
            ps.setInt(2, playlist.getId());
            int updatedRows = ps.executeUpdate();
            return updatedRows > 0;
            
        } catch(SQLServerException ex) {
            ex.printStackTrace();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return false;
    }
    /**
     * it changes the order in the plalist songlist. 
     * @param playlist
     * @param song
     * @param position
     * @param direction
     * @return bolean indicating if the operation was successfull
     */
    public boolean orderPlaylist(Playlist playlist, Song song, int position, boolean direction) {
        try(Connection con = dbCon.getConnection()) {
            int id = 0;
            
            PreparedStatement ps = con.prepareStatement("SELECT * FROM song_playlist WHERE playlistid = ? AND position = ?");
            ps.setInt(1, playlist.getId());
            if(direction) {
                ps.setInt(2, position + 1);
            } else {
                ps.setInt(2, position - 1);
            }
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                id = rs.getInt("id");
            }
            
            PreparedStatement ps1 = con.prepareStatement("UPDATE song_playlist SET position = ? WHERE id = ?");
            ps1.setInt(1, position);
            ps1.executeUpdate();
            
            PreparedStatement ps3 = con.prepareStatement("UPDATE song_playlist SET position = ? WHERE playlistid = ? AND songid = ? AND position = ? AND id != ?");
            
            ps3.setInt(2, playlist.getId());
            ps3.setInt(3, song.getId());
            ps3.setInt(4, position);
            if(direction) {
                ps3.setInt(1, position + 1);
            } else {
                ps3.setInt(1, position - 1);
            }
            ps3.setInt(5, id);
            
            
            
            return ps3.executeUpdate() > 0;
            
        } catch(SQLServerException ex) {
            ex.printStackTrace();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return false;
    }
    /**
     * deletes all the songs in a playlist.
     * @param playlist
     * @return 
     */
    public boolean clearPlaylist(Playlist playlist) {
        try(Connection con = dbCon.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM song_playlist WHERE playlistid = ?");
            ps.setInt(1, playlist.getId());
            ps.executeUpdate();
            
            PreparedStatement pStatement = con.prepareStatement("SELECT * FROM song_playlist WHERE playlistid = ?");
            pStatement.setInt(1, playlist.getId());
            ResultSet rs = pStatement.executeQuery();
            
            while(rs.next())
            {
                return false;
            }
            
            return true;
            
        } catch(SQLServerException ex) {
            ex.printStackTrace();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return false;
    }
    /**
     * deletes a single somg from a playlist. it also adjusts the positions after the deletion so they align with the new positions.
     * @param playlist
     * @param song
     * @param position
     * @return bolean indicating if the deletion was successfull
     */
    public boolean clearSongFromPlaylist(Playlist playlist ,Song song ,int position) {
        try(Connection con = dbCon.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM song_playlist WHERE playlistid = ? "
                                                      + "AND songid = ? "
                                                      + "AND song_playlist.position = ?");
            ps.setInt(1, playlist.getId());
            ps.setInt(2, song.getId());
            ps.setInt(3, position);
           
          
            int updatedRows = ps.executeUpdate();
           
            if(updatedRows > 0) {
            
            PreparedStatement ps2 = con.prepareStatement("UPDATE song_playlist SET Position = Position - 1 WHERE Position > ?" );
            
             ps2.setInt(1, position);
             
             int updatedRows2 = ps2.executeUpdate();
             
             return updatedRows2 > 0;
             
            
            }
            
        } catch(SQLServerException ex) {
            ex.printStackTrace();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return false;
    }
    /**
     * deletes a playlist from the list of playlists
     * @param playlist
     * @return bolean indicating if the operation was successfull
     */
    public boolean deletePlaylist(Playlist playlist) {
        if(clearPlaylist(playlist)) {
            try(Connection con = dbCon.getConnection()) {
                PreparedStatement ps = con.prepareStatement("DELETE FROM playlist WHERE id = ?");
                ps.setInt(1, playlist.getId());
                int updatedRows = ps.executeUpdate();
                return updatedRows > 0;

            } catch(SQLServerException ex) {
                ex.printStackTrace();
            } catch(SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        return false;
    }
}
