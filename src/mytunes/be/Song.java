/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.be;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author andreasvillumsen
 */
public class Song {
    private int id;
    private final SimpleStringProperty title;
    private final SimpleStringProperty album;
    private final SimpleStringProperty artist;
    private final SimpleStringProperty category;
    private final SimpleIntegerProperty time;
    private final SimpleStringProperty timetext;
    private int seconds;
    private int minutes; 
    private String path;

    public Song(int id, String title, String album, String artist, String category, int time, String path, String timetext) {
        
        this.id = id;
        this.timetext = new SimpleStringProperty(timetext);
        this.title = new SimpleStringProperty(title);
        this.album = new SimpleStringProperty(album);
        this.artist = new SimpleStringProperty(artist);
        this.category = new SimpleStringProperty(category);
        this.time = new SimpleIntegerProperty(time);
        this.path = path;
        
        minutes = (int) Math.floor(time / 60);
        seconds = time % 60;
        setTimetext(minutes +" : "+ seconds);
        
        
    }

    /**
     * Get the id of the song
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Set the id of the song
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * 
     * @return timetext
     */
    public String getTimetext(){
        return  timetext.get();
    }
    /**
     * 
     * @param timetext 
     */
    public void setTimetext(String timetext) {
        this.timetext.set(timetext);
    }

    /**
     * Get the title of the song
     * @return title
     */
    public String getTitle() {
        return title.get();
    }

    /**
     * Set the title of the song
     * @param title 
     */
    public void setTitle(String title) {
        this.title.set(title);
    }

    /**
     * Get the album of the song
     * @return album
     */
    public String getAlbum() {
        return album.get();
    }

    /**
     * Set the album of the song
     * @param album 
     */
    public void setAlbum(String album) {
        this.album.set(album);
    }

    /**
     * Get the artist of the song
     * @return artist
     */
    public String getArtist() {
        return artist.get();
    }

    /**
     * Set the artist of the song
     * @param artist 
     */
    public void setArtist(String artist) {
        this.artist.set(artist);
    }

    /**
     * Get the category of the song
     * @return category
     */
    public String getCategory() {
        return category.get();
    }

    /**
     * Set the category of the song
     * @param category 
     */
    public void setCategory(String category) {
        this.category.set(category);
    }

    /**
     * Get the time length of the song
     * @return time
     */
    public int getTime() {
        return time.get();
    }

    /**
     * Set the time length of the song
     * @param time 
     */
    public void setTime(int time) {
        this.time.set(time);
    }
    
    /**
     * Get the path of the song
     * @return path
     */
    public String getPath() {
        return path;
    }

    /**
     * Set the path of the song
     * @param path 
     */
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return getTitle()  + "  "+  getArtist() +"  "+  getCategory() + "  "+ getTimetext();
    }
    
    
    
}
