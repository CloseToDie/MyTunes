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
public class DeletePlaylistController
{
    private AppModel appModel;
    private Playlist playlist;

    @FXML
    private Button Yes;
    @FXML
    private Button No;

    

    public void setAppModel(AppModel app){
    
    appModel = app;
    
    
    }
    
    public void setPlaylist(Playlist playlist)
    {
        this.playlist = playlist;
        
     
    }

    @FXML
    private void Yes(ActionEvent event)
    {
        appModel.deletePlaylist(playlist);
        Stage stage = (Stage) No.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void No(ActionEvent event)
    {
        Stage stage = (Stage) No.getScene().getWindow();
        stage.close();
    }
    
}
