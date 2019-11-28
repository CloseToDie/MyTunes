/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.be;

/**
 *
 * @author andreasvillumsen
 */
public class Song {
    private int id;
    private String title;
    private String album;
    private String artist;
    private String category;
    private int time;
    private String path;

    public Song(int id, String title, String album, String artist, String category, int time, String path) {
        this.id = id;
        this.title = title;
        this.album = album;
        this.artist = artist;
        this.category = category;
        this.time = time;
        this.path = path;
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
     * Get the title of the song
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the title of the song
     * @param title 
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get the album of the song
     * @return album
     */
    public String getAlbum() {
        return album;
    }

    /**
     * Set the album of the song
     * @param album 
     */
    public void setAlbum(String album) {
        this.album = album;
    }

    /**
     * Get the artist of the song
     * @return artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Set the artist of the song
     * @param artist 
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * Get the category of the song
     * @return category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Set the category of the song
     * @param category 
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Get the time length of the song
     * @return time
     */
    public int getTime() {
        return time;
    }

    /**
     * Set the time length of the song
     * @param time 
     */
    public void setTime(int time) {
        this.time = time;
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
        return "Song{" + "id=" + id + ", title=" + title + ", album=" + album + ", artist=" + artist + ", category=" + category + ", time=" + time + ", path=" + path + '}';
    }
    
    
    
}
