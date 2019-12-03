/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui;

import java.awt.Frame;
import java.awt.FileDialog;
import java.io.File;
import java.net.URL;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.swing.JOptionPane;
import mytunes.be.Song;
import mytunes.dal.database.SongDBDAO;
import org.jaudiotagger.audio.AudioFile; 
import org.jaudiotagger.audio.AudioFileIO;

/**
 * FXML Controller class
 *
 * @author lumby
 */
public class EditSongController implements Initializable
{
    private int duration = 0;
    private String filename = "";
    private String directory = "";
    private AppModel appmodel;

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
    private ChoiceBox<String> CategoryChoiceBox;

    private ObservableList<String> list;
    private ArrayList<String> categories;
    @FXML
    private Button songChoiceButton;
    @FXML
    private TextField FileTextField;
    private Song song;
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
            list.add("pop");
        
         for (String string : list) {
            
             System.out.println(string);
        }
        
        CategoryChoiceBox.setItems(list);
        
       
    } 
    
    public void setappmodel(AppModel app){
    
    appmodel = app;
    
    
    }

    /**
     * let's the user add another categorie to a song
     * @param event 
     */
    @FXML
    private void MoreCategories(ActionEvent event)
    {
    }

    /**
     * closes the window without applying any changes
     * @param event 
     */
    @FXML
    private void Cancel(ActionEvent event)
    {
           // get a handle to the stage
    Stage stage = (Stage) Cancel.getScene().getWindow();
    // do what you have to do
    stage.close();
    }

    /**
     * sends the changes to the database and closes the window
     * @param event 
     */
    @FXML
    private void Save(ActionEvent event)
    {
        //SongDBDAO addSong = new SongDBDAO();
        
        String title = Title.getText();
        String artist = Artist.getText();
        String genre = CategoryChoiceBox.getSelectionModel().getSelectedItem();
        String songPath = FileTextField.getText();
        //duration = song.getTime();
        if(title == "")
        {
            JOptionPane.showMessageDialog(null, "Song title can not be blank!");
            title = "EDIT ME"; 
        }
        else
        {
            title = Title.getText();; 
        }
          
        if(artist == "")
        {
            JOptionPane.showMessageDialog(null, "Song artist can not be blank!");
            artist = "EDIT ME"; 
        }
        else
        {
            artist = Artist.getText(); 
        }
        
        if(genre == "")
        {
            JOptionPane.showMessageDialog(null, "Song genre can not be blank!");
            genre = "EDIT ME"; 
        }
        else
        {
           genre = CategoryChoiceBox.getSelectionModel().getSelectedItem(); 
        }
        
        if(songPath == "")
        {
            JOptionPane.showMessageDialog(null, "Song path can not be blank!");
            songPath = "EDIT ME"; 
        }
        else
        {
           songPath = FileTextField.getText(); 
        }
        
        if(duration == 0)
        {
            duration = song.getTime();
        }
        
        
        
        
        Song songToUpdate = new Song(song.getId(), title,"",artist, genre, duration, songPath);
        appmodel.updateSong(songToUpdate);
        Stage stage = (Stage) Cancel.getScene().getWindow();
        stage.close();
            
        
              
          
    }

    /**
     * opens a window to find the audio file for the song
     * @param event 
     */
    @FXML    
    private void songChoiceButton(ActionEvent event)
    {
        FileDialog fd = new java.awt.FileDialog((java.awt.Frame) null);
    fd.setDirectory("C:\\");
    fd.setFile("*.wav;*.mp3");
    fd.setVisible(true);
    filename = fd.getFile();
    directory = fd.getDirectory();
    if (filename == null){
    JOptionPane.showMessageDialog(null, "Add song canceled");}
    else{
        FileTextField.setText("music/" + filename);
    }
    
    

    try {
      AudioFile audioFile = AudioFileIO.read(new File(directory + filename));
      duration = audioFile.getAudioHeader().getTrackLength();
      /*int seconds = duration % 60;
      int minutes = (int) Math.floor(duration / 60);*/
      Time_textField.setText(duration+"");

    } 
        catch (Exception e) {
            e.printStackTrace();

    }
        
    }
    
    public void setSong(Song song)
    {
        this.song = song;
        
        Title.setText(song.getTitle());
        Artist.setText(song.getArtist());
        CategoryChoiceBox.setValue(song.getCategory());
        Time_textField.setText(song.getTime() + "");
        FileTextField.setText(song.getPath());
    }
}
