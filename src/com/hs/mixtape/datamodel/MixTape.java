package com.hs.mixtape.datamodel;

import java.util.List;

/**
 * 
 * Change is the datamodel for the MixTape
 * 
 * <pre>
 *	{
 *		"users" : [
 *			#user,
 *			...
 *		],
 *		"playlists" : [
 *			#playlist,
 *			...
 *		],
 *		"songs" : [
 *			#song,
 *			...
 *		]
 *	}
 * </pre>
 * 
 */
public class MixTape {
	private List<User> users;
	private List<Playlist> playlists;
	private List<Song> songs;
	
	public MixTape() {
		super();
	}
	public MixTape(List<User> users, List<Song> songs, List<Playlist> playlists) {
		super();
		this.users = users;
		this.playlists = playlists;
		this.songs = songs;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<Playlist> getPlaylists() {
		return playlists;
	}
	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}
	public List<Song> getSongs() {
		return songs;
	}
	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}
	
	
}
