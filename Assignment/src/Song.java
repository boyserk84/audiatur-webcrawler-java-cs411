
public class Song {



	private String name;
	private float duration;
	
	private String album_name;
	private String artist_id;
	
	/**
	 * Constructor
	 * @param name
	 * @param duration
	 * @param title
	 * @param a_name
	 * @param id
	 */
	public Song(String name, float duration, String a_name, String id)
	{
		this.name = name;
		this.duration = duration;
		this.album_name = a_name;
		this.artist_id = id;
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
