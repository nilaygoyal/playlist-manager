# playlist-manager

## build
    git clone https://github.com/nilaygoyal/playlist-manager.git
    cd playlist-manager
    mvn package
### dependencies
- Java 8
- Maven
  - maven-compiler-plugin
  - maven-assembly-plugin
- com.google.code.gson : managed by Maven
## usage
### command
    java -jar target/playlisteditor-0.0.1-SNAPSHOT-jar-with-dependencies.jar mixtape.json changelist.json output-file.json
### change list (json)
#### definition
    {
        "operation" : "CREATE_PLAYLIST|DELETE_PLAYLIST|ADD_SONGS_TO_PLAYLIST|REMOVE_SONGS_FROM_PLAYLIST",
        "user_id" : #user_id,
        "playlist_id" : #playlist_id,
        "song_ids" : [
          #song_id,
          ...
        ]
    }
    
#### #operation
- Create a new playlist on the mixtape
> CREATE_PLAYLIST
  - requires
    - user_id
    - song_ids 
- Delete a playlist from the mixtape
> DELETE_PLAYLIST 
  - requires
    - playlist_id 
- Add song(s) to an existing playlist
> ADD_SONGS_TO_PLAYLIST
  - requires
    - playlist_id
    - song_ids
- Remove song(s) from an existing playlist
> REMOVE_SONGS_FROM_PLAYLIST
  - requires
    - playlist_id
    - song_ids

#### sample
    {
      "changes" : [
        {
          "operation" : "CREATE_PLAYLIST",
          "user_id" : "1",
          "song_ids" : [
            "1",
            "2",
            "3"
          ]
        },
        {
          "operation" : "DELETE_PLAYLIST",
          "playlist_id" : "3"
        },
        {
          "operation" : "ADD_SONGS_TO_PLAYLIST",
          "playlist_id" : "4",
          "song_ids" : [
            "4",
            "5"
          ]
        },
        {
          "operation" : "REMOVE_SONGS_FROM_PLAYLIST",
          "playlist_id" : "4",
          "song_ids" : [
            "1",
            "2",
            "3"
          ]
        }
      ]
    }

## errors
Possible errors/exceptions, self explanatory

    - Inavild arguments: program arguments 'mixtape.json changelist.json output-file.json'
    - FileNotFoundException/IOException: check the files
    - Duplicate-User: Duplicate user found in mixtape
    - Duplicate-Song: Duplicate song found in mixtape
    - Duplicate-Playlist: Duplicate playlist found in mixtape
    - Invalid-Song: Invalid song found in mixtape
    - Invalid-User: Invalid user found in mixtape
    - Invalid-Create-Playlist-Change:Invalid-User: playlist create operation failed, invalid user id used.
    - Invalid-Create-Playlist-Change:No-Songs: playlist create operation failed, no songs selected for playlist.
    - Invalid-Delete-Playlist-Change:Invalid-Playlist: playlist delete operation failed, invalid playlist id used.
    - Invalid-Add-Song-Change:Invalid-Playlist: add song operation failed, invalid playlist id used.
    - Invalid-Add-Song-Change:No-Songs: add song operation failed, no songs selected to add to playlist.
    - Invalid-Remove-Song-Change:Invalid-Playlist: remove song operation failed, invalid playlist id used.
    - Invalid-Remove-Song-Change:No-Songs: remove song operation failed, no songs selected to be removed from playlist.
    - Change-Operation-Failed: invalid user id
    - Change-Operation-Failed: invalid playlist id
    - Change-Operation-Failed: invalid song id
    
