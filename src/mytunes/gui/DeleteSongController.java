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
import mytunes.be.Song;

/**
 *
 * @author kacpe
 */
public class DeleteSongController {

    private AppModel appmodel;
    private Song song;
    @FXML
    private Button Yes;
    @FXML
    private Button No;

    /**
     * Sets the appModel
     *
     * @param app
     */
    public void setappmodel(AppModel app) {
        appmodel = app;
    }

    /**
     * Set the selected song
     *
     * @param song
     */
    public void setSong(Song song) {
        this.song = song;
    }

    /**
     * Delete the selected song
     *
     * @param event
     */
    @FXML
    private void Yes(ActionEvent event) {
        appmodel.deleteSong(song);
        Stage stage = (Stage) No.getScene().getWindow();
        stage.close();
    }

    /**
     * Closes the window without doing anything else
     *
     * @param event
     */
    @FXML
    private void No(ActionEvent event) {
        Stage stage = (Stage) No.getScene().getWindow();
        stage.close();
    }

}
