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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author lumby
 */
public class NewSongController implements Initializable
{

    @FXML
    private Label Category;
    @FXML
    private Label Time;
    @FXML
    private Label File;
    @FXML
    private TextField Title;
    @FXML
    private TextField Artist;
    @FXML
    private Button MoreCategories;
    @FXML
    private Button Cancel;
    @FXML
    private Button Save;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void MoreCategories(ActionEvent event)
    {
    }

    @FXML
    private void Cancel(ActionEvent event)
    {
    }

    @FXML
    private void Save(ActionEvent event)
    {
    }
    
}
