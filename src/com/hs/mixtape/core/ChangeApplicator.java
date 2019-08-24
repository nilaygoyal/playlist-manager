package com.hs.mixtape.core;

import java.util.ArrayList;
import java.util.List;

import com.hs.mixtape.datamodel.Change;
import com.hs.mixtape.datamodel.Playlist;
import com.hs.mixtape.datamodel.Song;
import com.hs.mixtape.datamodel.User;

/**
 * 
 * Apply each change on mixtape, built stateless can be scaled and operate muliple mixtapes in parallel
 *
 */
public class ChangeApplicator {

	private static final void validateCreatePlaylistOp(Change change) {
		if(change.getUser_id() == null || change.getUser_id().trim().length() == 0) {
			throw new RuntimeException("Invalid-Create-Playlist-Change:Invalid-User: playlist create operation failed, invalid user id used.");
		}
		
		if(change.getSong_ids() == null || change.getSong_ids().size() == 0) {
			throw new RuntimeException("Invalid-Create-Playlist-Change:No-Songs: playlist create operation failed, no songs selected for playlist.");
		}
	}
	
	private static final void validateDeletePlaylistOp(Change change) {
		if(change.getPlaylist_id() == null || change.getPlaylist_id().trim().length() == 0) {
			throw new RuntimeException("Invalid-Delete-Playlist-Change:Invalid-Playlist: playlist delete operation failed, invalid playlist id used.");
		}
	}
	
	private static final void validateAddSongToPlaylistOp(Change change) {
		if(change.getPlaylist_id() == null || change.getPlaylist_id().trim().length() == 0) {
			throw new RuntimeException("Invalid-Add-Song-Change:Invalid-Playlist: add song operation failed, invalid playlist id used.");
		}
		
		if(change.getSong_ids() == null || change.getSong_ids().size() == 0) {
			throw new RuntimeException("Invalid-Add-Song-Change:No-Songs: add song operation failed, no songs selected to add to playlist.");
		}
	}
	
	private static final void validateRemoveSongFromPlaylistOp(Change change) {
		if(change.getPlaylist_id() == null || change.getPlaylist_id().trim().length() == 0) {
			throw new RuntimeException("Invalid-Remove-Song-Change:Invalid-Playlist: remove song operation failed, invalid playlist id used.");
		}
		
		if(change.getSong_ids() == null || change.getSong_ids().size() == 0) {
			throw new RuntimeException("Invalid-Remove-Song-Change:No-Songs: remove song operation failed, no songs selected to be removed from playlist.");
		}
		
	}
	
	public static final void applyOperation(MixTapeProcessor mixTapeProcessor, Change change) {
		User user = null;
		if(change.getUser_id() != null) {
			user = mixTapeProcessor.findUser(change.getUser_id());
			if(user == null) {
				throw new RuntimeException("Change-Operation-Failed: invalid user id");
			}
		}
		Playlist playlist = null;
		if(change.getPlaylist_id() != null) {
			playlist = mixTapeProcessor.findPlaylist(change.getPlaylist_id());
			if(playlist == null) {
				throw new RuntimeException("Change-Operation-Failed: invalid playlist id");
			}
		}
		List<Song> songs = new ArrayList<Song>();
		if(change.getSong_ids() != null) {
			for(String songId : change.getSong_ids()) {
				Song song = mixTapeProcessor.findSong(songId);
				if(song == null) {
					throw new RuntimeException("Change-Operation-Failed: invalid song id");
				}
				songs.add(song);
			}
		}
		switch (change.getOperation()) {
		case REMOVE_SONGS_FROM_PLAYLIST:
			validateRemoveSongFromPlaylistOp(change);
			for(String song : change.getSong_ids()) {
				playlist.removeSong(song);
			}
			break;
		case ADD_SONGS_TO_PLAYLIST:
			validateAddSongToPlaylistOp(change);
			for(String song : change.getSong_ids()) {
				playlist.addSong(song);
			}
			break;
		case CREATE_PLAYLIST:
			validateCreatePlaylistOp(change);
			mixTapeProcessor.createPlaylist(change.getUser_id(), change.getSong_ids());
			break;
		case DELETE_PLAYLIST:
			validateDeletePlaylistOp(change);
			mixTapeProcessor.deletePlaylist(change.getPlaylist_id());
			break;
		default:
			break;
		}
	}
}
