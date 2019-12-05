/****** Script for SelectTopNRows command from SSMS  ******/
SELECT TOP (1000) [songid]
      ,[playlistid]
      ,[position]
  FROM [hitlersOfreMyTunes].[dbo].[song_playlist]
