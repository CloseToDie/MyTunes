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
public class MusicPlayer {

    private static Media m;
    private static MediaPlayer player;

    /**
     * MusicPlayer constructor
     *
     * @param filename
     */
    public MusicPlayer(String filename) {
        m = new Media(Paths.get(filename).toUri().toString());
        player = new MediaPlayer(m);
    }

    /**
     * Start playing a song
     */
    public static void playSound() {
        player.play();
    }

    /**
     * Pause the song
     */
    public static void PauseSound() {
        player.pause();
    }

    /**
     * Check if the song is done playing
     *
     * @return boolean
     */
    public static boolean isDone() {
        if (!player.getCurrentTime().lessThan(player.getStopTime())) {
            return true;
        }
        return false;
    }

    /**
     * Get the MediaPlayer
     *
     * @return MediaPlayer
     */
    public MediaPlayer getMP() {
        return player;
    }


}
