/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.be;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author andreasvillumsen
 */
public class Playlist {
    private int id;
    private final SimpleStringProperty name;
    

    public Playlist(int id, String name) {
        this.id = id;
        this.name = new SimpleStringProperty(name);
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

    @Override
    public String toString() {
        return "Playlist{" + "id=" + id + ", name=" + name.get() + '}';
    }
    
    
}
