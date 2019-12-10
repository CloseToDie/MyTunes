/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 /*Here the package that this class is in is defined*/
package mytunes.gui;

/*All the imports are defined here,the class needs to know witch other classes, packages or libraries it has acces to,
this also defines how the class fits into the programs design structure.*/
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
/**
 * NewSongController is the class that controls the fxml page where the user can
 * create and add new songs to the app
 */
public class NewSongController implements Initializable {

    /**
     * The instance variables er defined and some given an initial value
     */
    private int duration = 0;
    private String filename = "";
    private String directory = "";
    private AppModel appModel;

    /**
     * Here is where the program autogeneretes the definitions of all the
     * elements of the fxml
     */
    @FXML
    private TextField Title;
    @FXML
    private TextField Artist;
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

    /**
     * Initializes the controller class. Creates a list of categories and sets
     * it to the choiceBox.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<String> categories = new ArrayList<>();

        list = FXCollections.observableArrayList(categories);

        list.add("rock");
        list.add("punk");
        list.add("jazz");
        list.add("pop");
        list.add("rap");

        for (String string : list) {
            System.out.println(string);
        }

        CategoryChoiceBox.setItems(list);
    }

    /**
     * we need to make sure that the controller uses the same appmodel as the
     * rest of the program otherwise it would be wotking with diferent datasets.
     * We therefore have a method that we call when the fxml stage is set where
     * the correct appmodel is passed to the controller.
     */
    public void setAppModel(AppModel app) {
        appModel = app;
    }

    /**
     * Close the window without applying any changes
     *
     * @param event
     */
    @FXML
    private void Cancel(ActionEvent event) {
        Stage stage = (Stage) Cancel.getScene().getWindow();
        stage.close();
    }

    /**
     * Sends the changes to the database and closes the window
     *
     * @param event
     */
    @FXML
    private void Save(ActionEvent event) {
        String title = Title.getText();
        String artist = Artist.getText();
        String genre = CategoryChoiceBox.getSelectionModel().getSelectedItem();
        String songPath = FileTextField.getText();

        Song songToAdd = new Song(1, title, "", artist, genre, duration, songPath, "");
        appModel.createSong(songToAdd);
        System.out.print(directory + "dræb mig");
        Stage stage = (Stage) Cancel.getScene().getWindow();
        stage.close();

    }

    /**
     * Opens a window to find the audio file for the song
     *
     * @param event
     */
    @FXML
    private void songChoiceButton(ActionEvent event) {
        FileDialog fd = new java.awt.FileDialog((java.awt.Frame) null);
        fd.setDirectory("C:\\");
        fd.setFile("*.wav;*.mp3");
        fd.setVisible(true);
        filename = fd.getFile();
        directory = fd.getDirectory();
        if (filename == null) {
            JOptionPane.showMessageDialog(null, "Add song canceled");
        } else {
            FileTextField.setText("music/" + filename);
            System.out.print(directory + "dræb mig");
        }

        try {
            AudioFile audioFile = AudioFileIO.read(new File(directory + filename));
            duration = audioFile.getAudioHeader().getTrackLength();
            /*
            int seconds = duration % 60;
            int minutes = (int) Math.floor(duration / 60);
             */
            Time_textField.setText(duration + "");

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
