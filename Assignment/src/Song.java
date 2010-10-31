import java.io.Serializable;


public class Song implements Serializable{



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private float duration;
	
	private String album_name;
	private String artist_id;
	
	/**
	 * Constructor
	 * @param name name of the song
	 * @param duration duration
	 * @param a_name album name
	 * @param id artist id
	 */
	public Song(String name, float duration, String a_name, String id)
	{
		this.name = name;
		this.duration = duration;
		this.album_name = a_name;
		this.artist_id = id;
	}
	
	public Song()
	{
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}

	public String getAlbum_name() {
		return album_name;
	}

	public void setAlbum_name(String album_name) {
		this.album_name = album_name;
	}

	public String getArtist_id() {
		return artist_id;
	}

	public void setArtist_id(String artist_id) {
		this.artist_id = artist_id;
	}
}
