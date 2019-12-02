/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.bll.MusicPlayer;


/**
 *
 * @author andreasvillumsen
 */
public class AppController implements Initializable
{
    private boolean tock = false;
    private boolean tick = false;
    private SongModel songModel;
    private AppModel appmodel;
    private MusicPlayer d;
    private Label label;
    @FXML
    private TableView<Playlist> playlist;
    @FXML
    private TableView<Song> Songs;
    @FXML
    private ListView<Song> SongsInPlaylist;
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
    private TableColumn<Song, String> songTitelCol;
    @FXML
    private TableColumn<Song, String> songArtistCol;
    @FXML
    private TableColumn<Song, String> songCategoryCol;
    @FXML
    private TableColumn<Song, Integer> songTimeCol;
    @FXML
    private TableColumn<Playlist, String> playlistNameCol;
    @FXML
    private TableColumn<Playlist, Integer> playlistSongsCol;
    @FXML
    private TableColumn<Playlist, Integer> playlistTimeCol;


    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        /*try
        {
            songModel = new SongModel();
            Songs.setItems(songModel.getAllSongs());
        } catch (Exception ex)
        {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
         d = new MusicPlayer("music/test.mp3");
        
        volume.setValue(d.getMP().getVolume()* 100);
        volume.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                
                d.getMP().setVolume(volume.getValue() /100);
            }
        });
        
                    songTitelCol.setCellValueFactory(
                new PropertyValueFactory<Song,String>("title")
            );
            songArtistCol.setCellValueFactory(
                new PropertyValueFactory<Song,String>("artist")
            );
            songCategoryCol.setCellValueFactory(
                new PropertyValueFactory<Song,String>("category")
            );

            songTimeCol.setCellValueFactory(
                new PropertyValueFactory<Song,Integer>("time")
            );
            
            
            try
        {
            
           songModel = new SongModel();
           appmodel = new AppModel();
           
           Songs.setItems(appmodel.getAllSongs());
          // Songs.getColumns().addAll(songTitelCol,songArtistCol,songCategoryCol, songTimeCol);
            
        } catch (Exception ex)
        {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
             
            /*
            Songs.setItems(songModel.getAllSongs());
            Songs.getColumns().addAll(songTitelCol,songArtistCol,songCategoryCol, songTimeCol);
            */
            
          
        
      
    }

    @FXML
    private void Search(KeyEvent event)
    {
        try
        {
            String query = Search.getText().trim();
            appmodel.search(query);
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
        
    }

    @FXML
    private void ToPlaylist(ActionEvent event)
    {
    }

    @FXML
    private void NewPlaylist(ActionEvent event) throws IOException
    {
        openMenu("Playlist.fxml", "new/edit Playlist");
    }

    @FXML
    private void EditPlaylist(ActionEvent event) throws IOException
    {
        openMenu("Playlist.fxml", "new/edit Playlist");
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
    private void EditSong(ActionEvent event) throws IOException
    {
        openMenu("NewSong.fxml", "add/edit Song");
    }

    @FXML
    private void DeleteSong(ActionEvent event)
    {
        Songs.refresh();
        System.out.println("mytunes.gui.AppController.DeleteSong()");
    }

    @FXML
    private void Exit(ActionEvent event)
    {
           // get a handle to the stage
    Stage stage = (Stage) Exit.getScene().getWindow();
    // do what you have to do
    stage.close();
    }

    @FXML
    private void previous(ActionEvent event)
    {
         //d = new MusicPlayer("music/Mudkip.mp3");
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
     * @param event 
     */
    @FXML
    private void newSong(ActionEvent event) throws IOException
    {
            FXMLLoader fxmlLoader = new FXMLLoader();
            
            Parent root = (Parent) fxmlLoader.load(getClass().getResource("NewSong.fxml").openStream());
            NewSongController cont = (NewSongController) fxmlLoader.getController();
            cont.setappmodel(appmodel);
            Stage stage = new Stage();
            stage.setTitle("New Song");
            stage.setScene(new Scene(root));
            stage.setAlwaysOnTop(true);
            stage.show();
    }

    public Label getIsPlaying() {
        return isPlaying;
    }

    public void openMenu(String fxmlFile, String title) throws IOException
    {
        
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.setAlwaysOnTop(true);
            stage.show();
    } 
}
