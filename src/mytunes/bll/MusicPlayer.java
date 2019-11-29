/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.bll;

import java.nio.file.Paths;
import javafx.collections.MapChangeListener;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;

/**
 *
 * @author kacpe
 */
public class MusicPlayer
{
    static Media m; 
    static MediaPlayer player;
    private String title;
   
    
    
    public MusicPlayer(String filename)
    {
        m = new Media(Paths.get(filename).toUri().toString());
        
        m.getMetadata().addListener((MapChangeListener<String, Object>) change -> {
            
             title = (String)m.getMetadata().get("title");
             
             System.out.println(title);
   });
        
        
        player = new MediaPlayer(m);
    }

    public MusicPlayer() {
        
        
        
    }
    
    public static void playSound(){

        player.play();
        
        
    }
    
    public static void PauseSound()
    {
        player.pause();
    }
    
    public static boolean isPlaying(){
    
    boolean playing = player.getStatus().equals(Status.PLAYING);
    return playing;
    
    
    
    }

    
    public static boolean isDone(){
    
        if(!player.getCurrentTime().lessThan(player.getStopTime())){
        
            System.out.println("fffffffffffffffffffffffffffffffffffff");
        return true; 
        
        
        }
        System.out.println(player.getCurrentTime());
        System.out.println(player.getStopTime());
        
   return false; 
    
    }
    
    public static MediaPlayer getMP(){
    
    return player; 
    
    }

    public String getTitle() {
        return title;
    }
    
    
    

}
