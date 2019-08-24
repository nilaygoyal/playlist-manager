package com.hs.mixtape.datamodel;

/**
 * 
 * Change is the datamodel for each song
 * 
 * <pre>
 *	{
 *		"id" : #song_id,
 *		"artist" : 'artist name',
 *		"title" : 'title of the song'
 *	}
 * </pre>
 * 
 */
public class Song {
	private String id;
	private String artist;
	private String title;
	
	public Song() {
		super();
	}
	public Song(String id, String artist, String title) {
		super();
		this.id = id;
		this.artist = artist;
		this.title = title;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
