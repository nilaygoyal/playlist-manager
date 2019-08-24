package com.hs.mixtape.datamodel;

import java.util.List;

/**
 * 
 * Change is the datamodel for each change
 * 
 * <pre>
 *	{
 *		"operation" : "CREATE_PLAYLIST|DELETE_PLAYLIST|ADD_SONGS_TO_PLAYLIST|REMOVE_SONGS_FROM_PLAYLIST",
 *		"user_id" : #user_id,
 *		"playlist_id" : #playlist_id,
 *		"song_ids" : [
 *			#song_id,
 *			...
 *		]
 *	}
 * </pre>
 * 
 * CREATE_PLAYLIST - requires user_id, song_ids
 * DELETE_PLAYLIST - requires playlist_id
 * ADD_SONGS_TO_PLAYLIST - requires playlist_id, song_ids
 * REMOVE_SONGS_FROM_PLAYLIST - requires playlist_id, song_ids
 * 
 */
public class Change {

	private Operation operation;
	private String user_id;
	private String playlist_id;
	private List<String> song_ids;
	
	public Change() {
		super();
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getPlaylist_id() {
		return playlist_id;
	}

	public void setPlaylist_id(String playlist_id) {
		this.playlist_id = playlist_id;
	}

	public List<String> getSong_ids() {
		return song_ids;
	}

	public void setSong_ids(List<String> song_ids) {
		this.song_ids = song_ids;
	}
}