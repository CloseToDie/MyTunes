/****** Script for SelectTopNRows command from SSMS  ******/
SELECT TOP (1000) [id]
      ,[title]
      ,[artist]
      ,[category]
      ,[time]
      ,[album]
      ,[path]
  FROM [hitlersOfreMyTunes].[dbo].[song]