/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import mytunes.be.Playlist;

/**
 * FXML Controller class
 *
 * @author lumby
 */
public class PlaylistController implements Initializable {

    @FXML
    private TextField Name;
    @FXML
    private Button Cancel;
    @FXML
    private Button Save;

    private AppModel appModel;

    private Playlist playlist;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    /**
     * Close the window without aplaying changes
     *
     * @param event
     */
    @FXML
    private void Cancel(ActionEvent event) {
        Stage stage = (Stage) Cancel.getScene().getWindow();
        stage.close();
    }

    /**
     * Sets the appModel for the class
     *
     * @param app
     */
    public void setAppModel(AppModel app) {
        appModel = app;
    }

    /**
     * Adds a new playlist or applies the changes to an already existing
     * playlist
     *
     * @param event
     */
    @FXML
    private void Save(ActionEvent event) {
        String playlistName = Name.getText();
        if (playlistName == "") {
            JOptionPane.showMessageDialog(null, "Song title can not be blank!");
            playlistName = "EDIT ME";
        } else {
            playlistName = Name.getText();
        }

        Playlist Playlist = new Playlist(0, playlistName, 0, 0, "timetext");
        appModel.createPlaylist(Playlist);
        Stage stage = (Stage) Cancel.getScene().getWindow();
        stage.close();
    }

    /**
     * Sets playlist name in the text field.
     *
     * @param playlist
     */
    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
        Name.setText(playlist.getName());
    }

}
