/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui;

import java.io.File;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import mytunes.bll.MusicPlayer;

/**
 *
 * @author andreasvillumsen
 */
public class AppController implements Initializable
{

    private boolean tock = false;
    private boolean tick = false;
    private MusicPlayer d;
    private Label label;
    @FXML
    private TableView<?> playlist;
    @FXML
    private TableView<?> Songs;
    @FXML
    private ListView<?> SongsInPlaylist;
    @FXML
    private TextField Search;
    @FXML
    private Button ToPlaylist;
    @FXML
    private Button NewPlaylist;
    @FXML
    private Button EditPlaylist;
    @FXML
    private Button deletePlaylist;
    @FXML
    private Button Up;
    @FXML
    private Button Down;
    @FXML
    private Button RemoveSongFromPlaylist;
    @FXML
    private Button newSong;
    @FXML
    private Button EditSong;
    @FXML
    private Button DeleteSong;
    @FXML
    private Button Exit;
    @FXML
    private Button previous;
    @FXML
    private Button Play;
    @FXML
    private Button Skip;
    @FXML
    private Slider volume;
    @FXML
    private Label isPlaying;
    @FXML
    private MediaView mediaView;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
         d = new MusicPlayer("music/test.mp3");
        
        volume.setValue(d.getMP().getVolume()* 100);
        volume.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                
                d.getMP().setVolume(volume.getValue() /100);
            }
        });
        
      
    }

    @FXML
    private void Search(KeyEvent event)
    {
    }

    @FXML
    private void ToPlaylist(ActionEvent event)
    {
    }

    @FXML
    private void NewPlaylist(ActionEvent event)
    {
    }

    @FXML
    private void EditPlaylist(ActionEvent event)
    {
    }

    @FXML
    private void deletePlaylist(ActionEvent event)
    {
    }

    @FXML
    private void goUp(ActionEvent event)
    {
    }

    @FXML
    private void GoDown(ActionEvent event)
    {
    }

    @FXML
    private void RemoveSongFromPlaylist(ActionEvent event)
    {
    }

    @FXML
    private void EditSong(ActionEvent event)
    {
    }

    @FXML
    private void DeleteSong(ActionEvent event)
    {
    }

    @FXML
    private void Exit(ActionEvent event)
    {
    }

    @FXML
    private void previous(ActionEvent event)
    {
    }

    @FXML
    private void Play(ActionEvent event)
    {
      if(d != null)  {
        if(d.isDone()){
        
            System.out.println("done");
            tock = false;
        
        }
        else{
        System.out.println("not done");
        } 
            
      }
        
        if (tock == false)
        {
            
             d = new MusicPlayer("music/test.mp3");
          
            tock = true;
            
        } 
       
    
        if (tick == false)
        {
            tick = true;
            d.playSound();
            
        }
        else if(tick == true)
        {
            tick = false;
            d.PauseSound();
        }
       
        
    }

    @FXML
    private void Skip(ActionEvent event)
    {
    }

    @FXML
    private void changeVolume(MouseEvent event)
    {
    }

    /**
     * Opens the menu to add a new song,
     * when the button "new" under the song list is pressed.
     * (shit dont work yet)
     * @param event 
     */
    @FXML
    private void newSong(ActionEvent event) throws Exception
    {       
       /*
            Parent root = FXMLLoader.load(getClass().getResource("gui/NewSong.fxml"));
            Stage stage = new Stage();
            stage.setTitle("add/edit song");
            stage.setScene(new Scene(root));
            stage.show();
            */
          FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NewSong.fxml"));
          Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));  
            stage.show();

    }

    public Label getIsPlaying() {
        return isPlaying;
    }
    
    

}
