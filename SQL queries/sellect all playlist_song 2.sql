/****** Script for SelectTopNRows command from SSMS  ******/
SELECT TOP (1000) [songid]
      ,[playlistid]
      ,[position]
  FROM [hitlersOfreMyTunes].[dbo].[song_playlist]
  WHERE [playlistid] = 5
  ORDER BY [position]