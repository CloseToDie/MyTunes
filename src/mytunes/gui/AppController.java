/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.stage.Stage;
import mytunes.bll.MusicPlayer;

/**
 *
 * @author andreasvillumsen
 */
public class AppController implements Initializable
{

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

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
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
        MusicPlayer.playSound("music/3amWestEnd.mp3");
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
       
            Parent root = FXMLLoader.load(getClass().getResource("gui/NewSong.fxml"));
            Stage stage = new Stage();
            stage.setTitle("add/edit song");
            stage.setScene(new Scene(root));
            stage.show();
          
    }

}
