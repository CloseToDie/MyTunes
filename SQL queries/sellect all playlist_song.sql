/****** Script for SelectTopNRows command from SSMS  ******/


  DELETE FROM song_playlist WHERE song_playlist.playlistid = 5 AND song_playlist.songid = 43 AND song_playlist.position = 1
  UPDATE song_playlist SET Position = Position - 1 WHERE Position > 1
  
  
  
  SELECT TOP (1000) [songid]
      ,[playlistid]
      ,[position]
  FROM [hitlersOfreMyTunes].[dbo].[song_playlist]
  WHERE [playlistid] = 5
  ORDER BY [position]