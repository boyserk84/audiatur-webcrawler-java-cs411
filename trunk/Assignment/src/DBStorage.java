import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Statement;
import java.util.List;


public class DBStorage {

	/**
	 * DB exec statement
	 */
	private Statement stmt;
	/**
	 * Connection to DB
	 */
	private Connection conn;
	
	private List<Artist> arr_list;
	
	private final String TB_ARTIST = "artists";
	private final String TB_ALBUMS = "albums";
	private final String TB_SONGS = "songs";
	private final String TB_AR_GENRE = "artists_playing_genres";
	private final String TB_GENRES = "genres";
	
	
	public DBStorage()
	{
		conn = this.dbConnect("jdbc:mysql://localhost/audiatur", "root", "");
	}
	/**
	 * Constructor
	 */
	public DBStorage(List<Artist> arr) 
	{
		this.arr_list = arr;
		conn = this.dbConnect("jdbc:mysql://localhost/audiatur", "root", "");
		for (int j = 0; j < arr_list.size(); j++) 
		{
			
		}
	}
	
	private void addRowToTable(String table, Artist data)
	{
		String command = null;
		try {
			stmt = conn.createStatement();
			command = ("INSERT INTO " + table + "(id,name,description,founded_in)"
			+ "VALUES('" + data.getArtist_id() + "','" 
			+ data.getName()
			+ "','"
			+ data.getDes().replace(";", "").replace("'", "&rsquo;").replace("\"","&quot;" )
			+ "','"
			+ data.getYear_found()
			+ "')");
			System.out.println(command);
			stmt.executeUpdate(command);

		} catch (Exception e)
		{
			System.out.println(e);
			System.out.println(command);
			System.out.println("Fail to Insert to " + table + " with " + data.getName());
		}
	}
	
	public void addRowtoArtistsTable(Artist data)
	{
		addRowToTable(TB_ARTIST,data);
	}
	
	public void addRowtoAlbumsTable(Album data)
	{
		addRowToTableAlbum(data);
	}
	
	public void addRowtoSongsTable(Song data)
	{
		addRowToTableSong(data);
	}
	
	public void addRowtoArtistGenreTable(Artist data)
	{
		addRowToTableArtistGenre(data);
	}
	
	public void addGenreToTable(Genre data)
	{

		String command = null;
		if (data.isParent())
		{
			try {
				stmt = conn.createStatement();
				command = ("INSERT INTO " + TB_GENRES + "(name,sub_genre_name,description)"
						+ "VALUES('" 
						+ data.getName()
						+ "','"
						+ "NULL"
						+ "','"
						+ data.getDes()
						+ "')" );
				System.out.println(command);
				stmt.executeUpdate(command);

			} catch (Exception e)
			{
				System.out.println(e);
				System.out.println(command);
				System.out.println("Fail to Insert to " + TB_GENRES + " with " + data.getName());
			}
		} else {
/*			for (int i = 0; i < data.getSub_g().size(); i++) {
				try {
					stmt = conn.createStatement();
					command = ("INSERT INTO " + TB_GENRES + "(name,sub_genre_name,description)"
							+ "VALUES('" 
							+ data.getName()
							+ "','"
							+ data.getSub_g().get(i).getName()
							+ "','"
							+ data.getDes()
							+ "')" );
					System.out.println(command);
					stmt.executeUpdate(command);

				} catch (Exception e)
				{
					System.out.println(e);
					System.out.println(command);
					System.out.println("Fail to Insert to " + TB_GENRES + " with " + data.getName());
				}
			}*/
		}
	}
	
	private void addRowToTableArtistGenre(Artist data)
	{
		String command = null;
		for (int i = 0; i < data.getArr_genre().size(); i++) {
			try {
				stmt = conn.createStatement();
				command = ("INSERT INTO " + TB_AR_GENRE + "(artist_id,genre_name)"
						+ "VALUES('" 
						+ data.getArtist_id()
						+ "','"
						+ data.getArr_genre().get(i).getName()
						+ "')" );
				System.out.println(command);
				stmt.executeUpdate(command);

			} catch (Exception e)
			{
				System.out.println(e);
				System.out.println(command);
				System.out.println("Fail to Insert to " + TB_ALBUMS + " with " + data.getArtist_id());
			}
		}
	}
	
	private void addRowToTableSong(Song data)
	{
		String command = null;
		try {
			stmt = conn.createStatement();
			command = ("INSERT INTO " + TB_SONGS + "(artist_id,album_name,song_name,duration)"
			+ "VALUES('" 
			+ data.getArtist_id()
			+ "','"
			+ data.getAlbum_name().replace(";", "").replace("\'", "&rsquo;").replace("\"","&quot;" ).replace("[", "").replace("]","")
			+ "','"
			+ data.getName()
			+ "','"
			+ data.getDuration()
			+ "')" );
			System.out.println(command);
			stmt.executeUpdate(command);

		} catch (Exception e)
		{
			System.out.println(e);
			System.out.println(command);
			System.out.println("Fail to Insert to " + TB_SONGS + " with " + data.getArtist_id());
		}
	}
	
	
	
	private void addRowToTableAlbum(Album data)
	{
		String command = null;
		try {
			stmt = conn.createStatement();
			command = ("INSERT INTO " + TB_ALBUMS + "(artist_id,album_name,release_date)"
			+ "VALUES('" 
			+ data.getArtist_id()
			+ "','"
			+ data.getAlbum_name().replace(";", "").replace("'", "&rsquo;").replace("\"","&quot;" )
			+ "','"
			+ sanitizeDate(data.getRelease_date().replace(".", "-"))
			+ "')" );
			System.out.println(command);
			stmt.executeUpdate(command);

		} catch (Exception e)
		{
			System.out.println(e);
			System.out.println(command);
			System.out.println("Fail to Insert to " + TB_ALBUMS + " with " + data.getArtist_id());
		}
	}
	
	private String sanitizeDate(String str)
	{
		String[] temp = str.split("-");
		return temp[2] + "-" + temp[0] + "-"+ temp[1];
	}
	
	/**
	 * Connect to the database
	 * @param db_connect_string database addr
	 * @param db_userid user name
	 * @param db_password pasword
	 * @return connection
	 */
	public Connection dbConnect(String db_connect_string,
	          String db_userid, String db_password)
	        {
	                try
	                {
	                        Class.forName("org.gjt.mm.mysql.Driver").newInstance();
	                        Connection conn = DriverManager.getConnection(
	                          db_connect_string, db_userid, db_password);
	      
	                        System.out.println("connected");
	                        return conn;
	                        
	                }
	                catch (Exception e)
	                {
	                        e.printStackTrace();
	                        return null;
	                }
	        }

}
