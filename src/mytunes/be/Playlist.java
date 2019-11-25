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
public class Playlist {
    private int id;
    private String name;

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
        return name;
    }

    /**
     * Set the name of the playlist
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }
}
