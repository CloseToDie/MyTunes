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
        
       
        
        for (Song song : dao.getAllSongsInPlaylist(new Playlist(1, "acustic", 0, 0))) {
            
            System.out.println(song);
            
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
                playlists.add(new Playlist(id, name,1,1));
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
                songs.add(new Song(id, title, album, artist, category, time, path));
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
    
    public boolean clearPlaylist(Playlist playlist) {
        try(Connection con = dbCon.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM song_playlist WHERE playlistid = ?");
            ps.setInt(1, playlist.getId());
            ps.executeUpdate();
            
            PreparedStatement pStatement = con.prepareStatement("SELECT * song_playlist WHERE playlistid = ?");
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
