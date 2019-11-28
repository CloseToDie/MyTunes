/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.bll;

import java.nio.file.Paths;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author kacpe
 */

public class MusicPlayer
{
    static Media m; 
    static MediaPlayer player;
    public static void playSound(String filename){
           m = new Media(Paths.get(filename).toUri().toString());
         player = new MediaPlayer(m);
        player.play();
    }
    
}
