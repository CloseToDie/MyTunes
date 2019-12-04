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
        
       
        
        for (Playlist allPlaylist : dao.getAllPlaylists()) {
            
            System.out.println(allPlaylist);
            
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
    
    public boolean deletePlaylist(Playlist playlist) {
        
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
        
        return false;
    }
}
