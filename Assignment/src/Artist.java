
import java.util.*;
import java.io.Serializable;
import java.sql.Date;


public class Artist implements Serializable{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static long id_gen = 1;	// generate artist id
		/**
		 * Artist name
		 */
		private String name;
		/**
		 * Description
		 */
		private String des;
		/**
		 * Year founded
		 */
		private int year_found;
		
		/**
		 * artist Id
		 */
		private String artist_id;
		
		/**
		 * List of artist's albums
		 */
		private List<Album> arr_albums;
		
		/**
		 * List of artist's genre
		 */
		private List<Genre> arr_genre;
		
		/**
		 * Constructor
		 * @param n
		 */
		public Artist(String n)
		{
			this(n, null, null);
		}
		public Artist()
		{
			
		}
		
		@SuppressWarnings("deprecation")
		public Artist(String n, String des, String date)
		{
			this.year_found = fetchYearFromString(date);
			this.name = n;
			this.des = des;
			this.artist_id = Long.toString(this.id_gen++);
			this.arr_albums = new ArrayList<Album>();
			this.arr_genre = new ArrayList<Genre>();
		}
		
		/**
		 * Add genre to the artist
		 * @param n
		 */
		public void addGenre(String n)
		{
			this.arr_genre.add(new Genre(n,"",null));
		}
		
		/**
		 * Add Album to the artist
		 * @param n album name
		 * @param date release date
		 */
		public void addAlbum(String n, String date)
		{
			this.arr_albums.add(new Album(n,artist_id,date));
		}
		/**
		 * 
		 * @param date
		 * @return
		 */
		private int fetchYearFromString(String date)
		{
			try {
				return Integer.parseInt(date);
			} catch (Exception e){
				return 0;
			}
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDes() {
			return des;
		}

		public void setDes(String des) {
			this.des = des;
		}

		public int getYear_found() {
			return year_found;
		}

		public void setYear_found(int year_found) {
			this.year_found = year_found;
		}

		public String getArtist_id() {
			return artist_id;
		}

		public void setArtist_id(String artist_id) {
			this.artist_id = artist_id;
		}

		public List<Album> getArr_albums() {
			return arr_albums;
		}

		public void setArr_albums(List<Album> arr_albums) {
			this.arr_albums = arr_albums;
		}
		public List<Genre> getArr_genre() {
			return arr_genre;
		}
		public void setArr_genre(List<Genre> arr_genre) {
			this.arr_genre = arr_genre;
		}
		
		
		
}