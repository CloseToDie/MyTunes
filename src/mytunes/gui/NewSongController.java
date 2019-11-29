/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lumby
 */
public class NewSongController implements Initializable
{

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
    @FXML
    private Label CategoryLabel;
    @FXML
    private Label TimeLabel;
    @FXML
    private Label fileLabel;
    @FXML
    private TextField Time_textField;
    @FXML
    private TextField FileTextField;
    @FXML
    private ChoiceBox<String> CategoryChoiceBox;

    private ObservableList<String> list;
    private ArrayList<String> categories;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        ArrayList<String> categories = new ArrayList<>();
        
        list = FXCollections.observableArrayList(categories);
        
        list.add("rock");
         list.add("punk");
          list.add("jazz");
        
         for (String string : list) {
            
             System.out.println(string);
        }
        
        CategoryChoiceBox.setItems(list);
        
    }    

    @FXML
    private void MoreCategories(ActionEvent event)
    {
    }

    @FXML
    private void Cancel(ActionEvent event)
    {
           // get a handle to the stage
    Stage stage = (Stage) Cancel.getScene().getWindow();
    // do what you have to do
    stage.close();
    }

    @FXML
    private void Save(ActionEvent event)
    {
    }
    
}
