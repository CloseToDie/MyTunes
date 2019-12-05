SELECT song_playlist.playlistid, song_playlist.position, song_playlist.songid,song.id,song.title,song.artist,song.category,song.time,song.path 
FROM song_playlist
INNER JOIN song ON song_playlist.songid = song.id
WHERE song_playlist.playlistid = 1
ORDER BY song_playlist.position ASC