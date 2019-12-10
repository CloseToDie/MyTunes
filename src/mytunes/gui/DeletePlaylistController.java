/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import mytunes.be.Playlist;

/**
 *
 * @author kacper
 */
public class DeletePlaylistController {

    private AppModel appModel;
    private Playlist playlist;
    @FXML
    private Button Yes;
    @FXML
    private Button No;

    /**
     * Set the appModel object
     *
     * @param app
     */
    public void setAppModel(AppModel app) {

        appModel = app;

    }

    /**
     * Set the selected playlist
     *
     * @param playlist
     */
    public void setPlaﬁylist(Playlist playlist) {
        this.playlist = playlist;
    }

    /**
     * Delete a chosen playlist
     *
     * @param event
     */
    @FXML
    private void Yes(ActionEvent event) {
        appModel.deletePlaylist(playlist);
        Stage stage = (Stage) No.getScene().getWindow();
        stage.close();
    }

    /**
     * Close the window without doing anything else
     *
     * @param event
     */
    @FXML
    private void No(ActionEvent event) {
        Stage stage = (Stage) No.getScene().getWindow();
        stage.close();
    }

}
