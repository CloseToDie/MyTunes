/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.be;

import java.util.ArrayList;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author andreasvillumsen
 */
public class Playlist {
    private int id;
    private final SimpleStringProperty name;
    private final SimpleIntegerProperty songs;
    private final SimpleIntegerProperty time;
    private final SimpleStringProperty timetext;
    private ArrayList<Song> songslist;
    private int minutes;
    private int seconds;
    
    

    public Playlist(int id, String name, int songs, int time , String timetext) {
        this.id = id;
        this.timetext = new SimpleStringProperty(timetext);
        this.name = new SimpleStringProperty(name);
        this.songs = new SimpleIntegerProperty(songs);
        this.time = new SimpleIntegerProperty(time);
        
        songslist = new ArrayList<>();
        
        
        minutes = (int) Math.floor(time / 60);
        seconds = time % 60;
        setTimetext(minutes +" : "+ seconds);
    }

    /**
     * Get the id of the playlist
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Set the id of the playlist
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }
    
    public String getTimetext(){
        return  timetext.get();
    }
    
    public void setTimetext(String timetext) {
        this.timetext.set(timetext);
    }
    

    /**
     * Get the name of the playlist
     * @return name
     */
    public String getName() {
        return name.get();
    }

    /**
     * Set the name of the playlist
     * @param name 
     */
    public void setName(String name) {
        this.name.set(name);
    }
    
     public int getSongs() {
        return songs.get();
    }

    /**
     * Set the title of the song 
     * @param songs
     */
    public void setSongs(int songs) {
        this.songs.set(songs);
    }
    
    public int getTime() {
        return time.get();
    }

    /**
     * Set the title of the song
     * @param time 
     */
    public void setTime(int time) {
        this.time.set(time);
        
        minutes = (int) Math.floor(time / 60);
        seconds = time % 60;
       setTimetext(minutes +" : "+ seconds);
        
    }

    public ArrayList<Song> getSongslist() {
        return songslist;
    }

    public void setSongslist(ArrayList<Song> songslist) {
        this.songslist = songslist;
    }
    

    @Override
    public String toString() {
        return "Playlist{" + "id=" + id + ", name=" + name.get() + '}';
    }
    
    
}
