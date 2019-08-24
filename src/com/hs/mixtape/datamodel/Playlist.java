package com.hs.mixtape.datamodel;

import java.util.List;

/**
 * 
 * Change is the datamodel for each playlist
 * 
 * <pre>
 *	{
 *		"id" : #playlist_id,
 *		"user_id" : #user_id,
 *		"song_ids" : [
 *			#song_id,
 *			...
 *		]
 *	}
 * </pre>
 * 
 */
public class Playlist {
	private String id;
	private String user_id;
	private List<String> song_ids;
	
	public Playlist() {
		super();
	}
	public Playlist(String id, String user_id, List<String> song_ids) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.song_ids = song_ids;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public List<String> getSong_ids() {
		return song_ids;
	}
	public void setSong_ids(List<String> song_ids) {
		this.song_ids = song_ids;
	}
	public void addSong(String song_id) {
		List<String> songsList = getSong_ids();
		if(songsList.contains(song_id)) {
			throw new RuntimeException("Add-Song-Failed: Song already present in the playlist.");
		}
		songsList.add(song_id);
	}
	
	public void removeSong(String song_id) {
		List<String> songsList = getSong_ids();
		if(!songsList.contains(song_id)) {
			throw new RuntimeException("Remove-Song-Failed: Song not present in the playlist.");
		}
		songsList.remove(song_id);
	}
}
