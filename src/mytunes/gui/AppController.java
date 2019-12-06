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
import mytunes.bll.SongManager;


/**
 *
 * @author andreasvillumsen
 */
public class AppController implements Initializable
{
    private Song currentlyPlayingSong = null;
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
    private int SelectedIndex;


    /**
     * 
     * @param url
     * @param rb 
     */
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
            
           /* private TableColumn<Playlist, String> playlistNameCol;
    @FXML
    private TableColumn<Playlist, Integer> playlistSongsCol;
    @FXML
    private TableColumn<Playlist, Integer> playlistTimeCol;*/
            
            playlistNameCol.setCellValueFactory(
                new PropertyValueFactory<Playlist,String>("name")
            );
            
            playlistSongsCol.setCellValueFactory(
                new PropertyValueFactory<Playlist,Integer>("songs")
            );
            playlistTimeCol.setCellValueFactory(
                new PropertyValueFactory<Playlist,Integer>("time")
            );
            
            
            
            
            try
        {
            //TO-DO get rid of the songmodel
           //songModel = new SongModel();
           appmodel = new AppModel();
           File file = new File(appmodel.getAllSongs().get(0).getPath());
           int i = 0;
           while( !appmodel.getAllSongs().isEmpty() && !file.exists() && i <= appmodel.getAllSongs().size() -1)
           {
                
                file = new File(appmodel.getAllSongs().get(i).getPath()); 
                i = i+1;
           }
           if (file.exists())
           {
               
               
               if(appmodel.getmusicPlayer() != null) {
          
          
          //danger zone vi calder dispose på mediaplayer for at være sikker at der ikke kommer flære end en ad gangnen
                    appmodel.getmusicPlayer().getMP().dispose();
            }
               
               
                appmodel.createMusicPlayer(file.getPath()); 
                currentlyPlayingSong = appmodel.getAllSongs().get(i);
        
        volume.setValue( appmodel.getmusicPlayer().getMP().getVolume()* 100);
        volume.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                
                appmodel.getmusicPlayer().getMP().setVolume(volume.getValue() /100);
            }
        });
           }
           
        
     
           
           
           Songs.setItems(appmodel.getAllSongs());
           playlist.setItems(appmodel.getAllPlaylist());
           SongsInPlaylist.setItems(appmodel.getSongsInPlaylist(appmodel.getAllPlaylist().get(0)));
           //Songs.getColumns().addAll(songTitelCol,songArtistCol,songCategoryCol, songTimeCol);
            
        } catch (Exception ex)
        {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
             
            /*
            Songs.setItems(songModel.getAllSongs());
            Songs.getColumns().addAll(songTitelCol,songArtistCol,songCategoryCol, songTimeCol);
            */
            
          appmodel.getmusicPlayer().getMP().setOnEndOfMedia(new Runnable() {
       public void run() {
           
            System.out.println("end of media");
         
       }
   });
        
      
    }

    /**
     * let's the user search from the different songs in the database
     * @param event 
     */
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

    /**
     * adds a song to a playlist
     * @param event 
     */
    @FXML
    private void ToPlaylist(ActionEvent event)
    {
       Playlist currentlySelectedPlaylist = playlist.getSelectionModel().getSelectedItem();
       Song currentlyselectedsong = Songs.getSelectionModel().getSelectedItem();
       int position = appmodel.getSongsInPlaylist(currentlySelectedPlaylist).size() +1;
       appmodel.addToPlaylist(currentlySelectedPlaylist, currentlyselectedsong, position);
       
    }

    /**
     * Opens the add/edit playlist window
     * @param event
     * @throws IOException 
     */
    @FXML
    private void NewPlaylist(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
            
            Parent root = (Parent) fxmlLoader.load(getClass().getResource("Playlist.fxml").openStream());
            PlaylistController cont = (PlaylistController) fxmlLoader.getController();
            cont.setAppModel(appmodel);
            Stage stage = new Stage();
            stage.setTitle("New/Edit Playlist");
            stage.setScene(new Scene(root));
            stage.setAlwaysOnTop(true);
            stage.show(); 
    }

    /**
     * Opens the add/edit playlist window
     * @param event
     * @throws IOException 
     */
    @FXML
    private void EditPlaylist(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
            
            Parent root = (Parent) fxmlLoader.load(getClass().getResource("EditPlaylist.fxml").openStream());
            EditPlaylistController cont = (EditPlaylistController) fxmlLoader.getController();
            cont.setAppModel(appmodel);
            cont.setPlaylist(playlist.getSelectionModel().getSelectedItem());
            Stage stage = new Stage();
            stage.setTitle("New/Edit Playlist");
            stage.setScene(new Scene(root));
            stage.setAlwaysOnTop(true);
            stage.show();
    }

    /**
     * Deletes a playllist from the database
     * @param event 
     */
    @FXML
    private void deletePlaylist(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
            
            Parent root2 = (Parent) fxmlLoader.load(getClass().getResource("DeletePlaylist.fxml").openStream());
            DeletePlaylistController cont2 = (DeletePlaylistController) fxmlLoader.getController();
            cont2.setAppModel(appmodel);
            cont2.setPlaylist(playlist.getSelectionModel().getSelectedItem());
            Stage stage = new Stage();
            stage.setTitle("Delete Playlist");
            stage.setScene(new Scene(root2));
            stage.setAlwaysOnTop(true);
            stage.show();
    }

    /**
     * Moves a song up in a playlist up
     * @param event 
     */
    
    @FXML
    private void goUp(ActionEvent event)
    {
        Playlist selectedPlaylist = playlist.getSelectionModel().getSelectedItem();
        Song songToMove = SongsInPlaylist.getSelectionModel().getSelectedItem();
        SelectedIndex = SongsInPlaylist.getSelectionModel().getSelectedIndex() + 1;
        
        if(SelectedIndex == 1)
        {
            System.out.println("can't move this song up");
        }else
        {        
        appmodel.orderPlaylist(selectedPlaylist, songToMove, SelectedIndex, false);
        SongsInPlaylist.refresh();
        }
    }

    /**
     * Moves a song down in a playlist down
     * @param event 
     */
    
    @FXML
    private void GoDown(ActionEvent event)
    {
        Playlist selectedPlaylist = playlist.getSelectionModel().getSelectedItem();
        Song songToMove = SongsInPlaylist.getSelectionModel().getSelectedItem();
        SelectedIndex = SongsInPlaylist.getSelectionModel().getSelectedIndex() + 1;
        
       /*if(SelectedIndex > selectedPlaylist.getSongslist().size())
        {
            System.out.println("Can't move this song down");
        }else */
        //{
        appmodel.orderPlaylist(selectedPlaylist, songToMove, SelectedIndex, true);
        SongsInPlaylist.refresh();
        //}
    }

    /**
     * Removes a song from a chosen playlist
     * @param event 
     */
    @FXML
    private void RemoveSongFromPlaylist(ActionEvent event)
    {
    }

    /**
     * Opens the menu to add/edit songs 
     * @param event
     * @throws IOException 
     */
    @FXML
    private void EditSong(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
            
            Parent root = (Parent) fxmlLoader.load(getClass().getResource("EditSong.fxml").openStream());
            EditSongController cont = (EditSongController) fxmlLoader.getController();
            cont.setAppModel(appmodel);
            cont.setSong(Songs.getSelectionModel().getSelectedItem());
            Stage stage = new Stage();
            stage.setTitle("New/edit Song");
            stage.setScene(new Scene(root));
            stage.setAlwaysOnTop(true);
            stage.show();
    }

    /**
     * Deletes a song from the list/database
     * @param event 
     */
    @FXML
    private void DeleteSong(ActionEvent event) throws IOException
    {
        
        FXMLLoader fxmlLoader = new FXMLLoader();
            
            Parent root2 = (Parent) fxmlLoader.load(getClass().getResource("DeleteSong.fxml").openStream());
            DeleteSongController cont2 = (DeleteSongController) fxmlLoader.getController();
            cont2.setappmodel(appmodel);
            cont2.setSong(Songs.getSelectionModel().getSelectedItem());
            Stage stage = new Stage();
            stage.setTitle("Delete song");
            stage.setScene(new Scene(root2));
            stage.setAlwaysOnTop(true);
            stage.show();
        
       /* Song selectedItem = Songs.getSelectionModel().getSelectedItem();        
        Songs.getItems().remove(selectedItem);
        appmodel.deleteSong(selectedItem);*/
    }

    /**
     * Closes the app
     * @param event 
     */
    @FXML
    private void Exit(ActionEvent event)
    {
           // get a handle to the stage
    Stage stage = (Stage) Exit.getScene().getWindow();
    // do what you have to do
    stage.close();
    }

    /**
     * ´Plays the previous song from the song list or playlist
     * @param event 
     */
    @FXML
    private void previous(ActionEvent event)
    {
         //d = new MusicPlayer("music/Mudkip.mp3");
        previous();
    }
    private void previous()
    {
        System.out.println();
      System.out.println();
      System.out.println();
      System.out.println();
      SelectedIndex = Songs.getSelectionModel().getSelectedIndex();
      
      System.out.println(Songs.getItems().size() +"    storlse");
      
      System.out.println(SelectedIndex +"       SelectedIndex");
      System.out.println(SelectedIndex +1 +"       SelectedIndex ++");
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println();
      
      if( SelectedIndex -1 <= -1)
      {
      
            Songs.getSelectionModel().selectLast();
            System.out.println("neeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
            Play();
            
      
      }
      else{
           Songs.getSelectionModel().select(SelectedIndex -1);
           Play();
           
      }
     
      
      
        
        System.out.println(Songs.getSelectionModel().getSelectedIndex());
        
    
    
    
    
    
    }

    /**
     * it plays the selcted song/playlist 
     * @param event 
     */
    @FXML
    private void Play(ActionEvent event)
    {

      if(appmodel.getmusicPlayer() != null)  {
        
       if(!Songs.getSelectionModel().getSelectedItem().equals(null))  { 
          if(Songs.getSelectionModel().getSelectedItem().getTitle() != currentlyPlayingSong.getTitle())
          {
              appmodel.getmusicPlayer().PauseSound();
              System.out.println("changing song");
              tock = false;
          }
          else
          {
             System.out.println("still playing current song");
          }
       }
          
        if(appmodel.getmusicPlayer().isDone()){
        
            System.out.println("done");
            tock = false;
        
        }    
        else{
        System.out.println("not done");
        } 
            
      }
        
        if (tock == false)
        {
            
             /*d = new MusicPlayer("music/test.mp3");*/
            
            if(appmodel.getmusicPlayer() != null) {
          
          
          //danger zone vi calder dispose på mediaplayer for at være sikker at der ikke kommer flære end en ad gangnen
          appmodel.getmusicPlayer().getMP().dispose();
            }
            
            appmodel.createMusicPlayer(Songs.getSelectionModel().getSelectedItem().getPath());
          currentlyPlayingSong = Songs.getSelectionModel().getSelectedItem();
            tock = true;
            isPlaying.setText(currentlyPlayingSong.getTitle() + " is playing");
            appmodel.getmusicPlayer().playSound();
            
              appmodel.getmusicPlayer().getMP().setOnEndOfMedia(new Runnable() {
                  
       public void run() {
           
            System.out.println("end of media");
            skip();
            
         
       }
   });
        } 
       
    
        if (tick == false && Songs.getSelectionModel().getSelectedItem().getTitle() == currentlyPlayingSong.getTitle())
        {
            tick = true;
            appmodel.getmusicPlayer().playSound();
            isPlaying.setText(currentlyPlayingSong.getTitle() + " is playing");
            
        }
        else if(tick == true && Songs.getSelectionModel().getSelectedItem().getTitle() == currentlyPlayingSong.getTitle())
        {
            tick = false;
            isPlaying.setText("(None)... is playing");
            appmodel.getmusicPlayer().PauseSound();
        }
       
        

    }
    
     private void Play(){
      if(appmodel.getmusicPlayer() != null) {
          
          
          //danger zone vi calder dispose på mediaplayer for at være sikker at der ikke kommer flære end en ad gangnen
          appmodel.getmusicPlayer().getMP().dispose();
          
          
          
      appmodel.createMusicPlayer(Songs.getSelectionModel().getSelectedItem().getPath());
      
          currentlyPlayingSong = Songs.getSelectionModel().getSelectedItem();
            isPlaying.setText(currentlyPlayingSong.getTitle() + " is playing");
            appmodel.getmusicPlayer().playSound();
            
        appmodel.getmusicPlayer().getMP().setOnEndOfMedia(new Runnable() {
            
       public void run() {
           
            System.out.println("end of media");
            skip();
         
       }
   });
     
     
      }
     }
    

    @FXML
    private void Skip(ActionEvent event)
    {
        skip();
    }
    public void skip(){
        
   //  System.out.println();
    //  System.out.println();
     // System.out.println();
     // System.out.println();
      SelectedIndex = Songs.getSelectionModel().getSelectedIndex();
      
    //  System.out.println(Songs.getItems().size() +"    storlse");
      
    //  System.out.println(SelectedIndex +"       SelectedIndex");
    //  System.out.println(SelectedIndex +1 +"       SelectedIndex ++");
   //   System.out.println();
    //  System.out.println();
     // System.out.println();
      //System.out.println();
      
      if( SelectedIndex +1 == Songs.getItems().size())
      {
      
            Songs.getSelectionModel().selectFirst();
            System.out.println("neeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
            Play();
            
      
      }
      else{
           Songs.getSelectionModel().select(SelectedIndex +1);
           Play();
           
      }
     
      
      
        
        System.out.println(Songs.getSelectionModel().getSelectedIndex());
        
    
    
    
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
            cont.setAppModel(appmodel);
            Stage stage = new Stage();
            stage.setTitle("New/edit Song");
            stage.setScene(new Scene(root));
            stage.setAlwaysOnTop(true);
            stage.show();         
    }

    public Label getIsPlaying() {
   
        return isPlaying;
    }    

    @FXML
    private void updatePlaylistview(MouseEvent event)
    {
        if (playlist.getSelectionModel().getSelectedItem() != null)
        { 
          SongsInPlaylist.setItems(appmodel.getSongsInPlaylist(playlist.getSelectionModel().getSelectedItem()));
        }
        
    }
 
    
}
