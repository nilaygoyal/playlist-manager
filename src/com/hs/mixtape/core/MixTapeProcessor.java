package com.hs.mixtape.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hs.mixtape.datamodel.MixTape;
import com.hs.mixtape.datamodel.Playlist;
import com.hs.mixtape.datamodel.Song;
import com.hs.mixtape.datamodel.User;

/**
 * 
 * MixTape processor with indexing. NOT concurrent for scaling.
 * Comes with built-in auto-incrementing playlist_id for create ops.
 *
 */
public class MixTapeProcessor {
	
	private MixTape mixTape;
	private Map<String, User> usersMap;
	
	private Map<String, Playlist> playlistsMap;
	private Map<String, Song> songsMap;
	
	private long maxPlaylistID = 0;
	
	public MixTapeProcessor(MixTape mixTape) {
		super();
		this.mixTape = mixTape;
	}
	
	public Map<String, User> getUsersMap() {
		if(usersMap == null) {
			buildIndex();
		}
		return usersMap;
	}
	public Map<String, Playlist> getPlaylistsMap() {
		if(playlistsMap == null) {
			buildIndex();
		}
		return playlistsMap;
	}
	public Map<String, Song> getSongsMap() {
		if(songsMap == null) {
			buildIndex();
		}
		return songsMap;
	}
	
	public void buildIndex() {
		usersMap = new HashMap<String, User>();
		songsMap = new HashMap<String, Song>();
		playlistsMap = new HashMap<String, Playlist>();
		for (User user : mixTape.getUsers()) {
			indexUser(user.getId(), user);
		}
		for(Song song : mixTape.getSongs()) {
			indexSong(song.getId(), song);
		}
		for(Playlist playlist : mixTape.getPlaylists()) {
			indexPlaylist(playlist.getId(), playlist);
		}
	}
	
	public User findUser(String userId) {
		return getUsersMap().get(userId);
	}
	
	public User indexUser(String userId, User user) {
		if(getUsersMap().containsKey(userId)) {
			throw new RuntimeException("Duplicate-User: Duplicate user found in mixtape");
		}
		
		getUsersMap().put(userId, user);
		return user;
	}
	
	public Song findSong(String songId) {
		return getSongsMap().get(songId);
	}
	
	public Song indexSong(String songId, Song song) {
		if(getSongsMap().containsKey(songId)) {
			throw new RuntimeException("Duplicate-Song: Duplicate song found in mixtape");
		}
		
		getSongsMap().put(songId, song);
		return song;
	}
	
	public Playlist findPlaylist(String playlistId) {
		return getPlaylistsMap().get(playlistId);
	}
	
	public Playlist indexPlaylist(String playlistId, Playlist playlist) {
		if(getPlaylistsMap().containsKey(playlistId)) {
			throw new RuntimeException("Duplicate-Playlist: Duplicate playlist found in mixtape");
		}
		
		List<Song> songs = new ArrayList<Song>();
		for(String songId : playlist.getSong_ids()) {
			Song song = getSongsMap().get(songId);
			if(song == null) {
				throw new RuntimeException("Invalid-Song: Invalid song found in mixtape");
			}
			songs.add(song);
		}
		User user = getUsersMap().get(playlist.getUser_id());
		if(user == null) {
			throw new RuntimeException("Invalid-User: Invalid user found in mixtape");
		}
		long id_long = Long.parseLong(playlistId);
		maxPlaylistID = Math.max(maxPlaylistID, id_long);
		getPlaylistsMap().put(playlistId, playlist);
		return playlist;
	}
	
	/**
	 * Create operation, with built-in auto-incrementing playlist id
	 * @param user_id
	 * @param song_ids
	 * @return
	 */
	public Playlist createPlaylist(String user_id, List<String> song_ids) {
		String playlistId = generatePlaylistId();
		Playlist playlist = new Playlist(playlistId, user_id, song_ids);
		mixTape.getPlaylists().add(playlist);
		getPlaylistsMap().put(playlistId, playlist);
		return playlist;
	}
	
	public void deletePlaylist(String playlistId) {
		Playlist playlist = getPlaylistsMap().remove(playlistId);
		mixTape.getPlaylists().remove(playlist);
	}

	public String generatePlaylistId() {
		return String.valueOf(++maxPlaylistID);
	}
}
