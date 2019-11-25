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
import mytunes.be.Song;
import mytunes.dal.SongFacade;

/**
 *
 * @author andreasvillumsen
 */
public class SongDBDAO {
    private final DatabaseConnector dbCon;
    
    public SongDBDAO() throws Exception {
        dbCon = new DatabaseConnector();
    }
    
    public boolean createTable() throws SQLServerException, SQLException {
        try(Connection con = dbCon.getConnection()) {
            PreparedStatement ps = con.prepareStatement("CREATE TABLE IF NOT "
                    + "EXISTS songs (id int NOT NULL, title varchar(255) NOT "
                    + "NULL, album varchar(255) NOT NULL, artist varchar(255) "
                    + "NOT NULL, category varchar(255) NOT NULL, time varchar(255) "
                    + "NOT NULL, path varchar(255) NOT NULL, PRIMARY KEY (id))");
            ResultSet rs = ps.executeQuery();
            return true;
        } catch(SQLServerException ex) {
            ex.printStackTrace();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public List<Song> getAllSongs() {
        ArrayList<Song> songs = new ArrayList<>();
        
        try(Connection con = dbCon.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM songs");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String album = rs.getString("album");
                String artist = rs.getString("artist");
                String category = rs.getString("category");
                String time = rs.getString("time");
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
    
    public Song createSong(Song song) {
        try(Connection con = dbCon.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO songs (title, album, artist, category, time, path) VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, song.getTitle());
            ps.setString(2, song.getAlbum());
            ps.setString(3, song.getArtist());
            ps.setString(4, song.getCategory());
            ps.setString(5, song.getTime());
            ps.setString(6, song.getPath());
            
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            
            if(rs.next()) {
                song.setId((int) rs.getLong(1));
            } else {
                return null;
            }
            
            return song;
            
        } catch(SQLServerException ex) {
            ex.printStackTrace();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return null;
    }
    
    public boolean updateSong(Song song) {
        try(Connection con = dbCon.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE songs SET title = ?, album = ?, artist = ?, category = ?, time = ?, path = ? WHERE id = ?");
            ps.setString(1, song.getTitle());
            ps.setString(2, song.getAlbum());
            ps.setString(3, song.getArtist());
            ps.setString(4, song.getCategory());
            ps.setString(5, song.getTime());
            ps.setInt(6, song.getId());
            ResultSet rs = ps.executeQuery();
            return ps.executeUpdate() > 0;
            
        } catch(SQLServerException ex) {
            ex.printStackTrace();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return false;
    }
    
    public boolean deleteInmate(Song song) {
        
        try(Connection con = dbCon.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM songs WHERE id = ?");
            ps.setInt(1, song.getId());
            ResultSet rs = ps.executeQuery();
            return ps.executeUpdate() > 0;
            
        } catch(SQLServerException ex) {
            ex.printStackTrace();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return false;
    }
    
    
}
