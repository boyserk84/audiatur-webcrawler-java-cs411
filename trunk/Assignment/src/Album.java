import java.util.ArrayList;
import java.util.List;


public class Album {
	
	
	private String artist_id;
	private String album_name;
	private String release_date;
	
	private List<Song> list_of_songs;
	
	
	/**
	 * Constructor
	 * @param name song's name
	 * @param artist_id artist id
	 * @param release_date release date for this album
	 */
	public Album(String name, String artist_id, String release_date)
	{
		this.artist_id = artist_id;
		this.album_name = name;
		this.release_date = release_date;
		list_of_songs = new ArrayList<Song>();
	}

	/**
	 * add song to this album
	 * @param s_n song name
	 * @param duration duration
	 */
	public void addSongToAlbum(String s_n, float duration)
	{
		list_of_songs.add(new Song(s_n,duration,album_name,artist_id));
	}

	public String getArtist_id() {
		return artist_id;
	}


	public void setArtist_id(String artist_id) {
		this.artist_id = artist_id;
	}


	public String getAlbum_name() {
		return album_name;
	}


	public void setAlbum_name(String album_name) {
		this.album_name = album_name;
	}


	public String getRelease_date() {
		return release_date;
	}


	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}

}
