/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.bll;

import java.nio.file.Paths;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

/**
 *
 * @author kacpe
 */

public class MusicPlayer
{
    static Media m; 
    static MediaPlayer player;
    static Boolean isdone;
    
    public MusicPlayer(String filename)
    {
        m = new Media(Paths.get(filename).toUri().toString());
        player = new MediaPlayer(m);
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
    
    boolean done = false; 
    
   player.setOnEndOfMedia(new Runnable() {
        @Override
        public void run() {
            isdone = true;
        }
    });  
    
    return isdone;
    
    
    
    }
    
    
}
