import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class RoadRunnerCrawler extends Crawler implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * List of Artists
	 */
	private List<Artist> arr_artist;
	
	/**
	 * List of Artists' URLs
	 */
	private List<String> arr_url;
	
	private final String BASE_URL = "http://www.roadrunnerrecords.com/artists/";	//base URL
	
	
	private DBStorage DB;
	/**
	 * Constructor
	 * @throws IOException 
	 */
	public RoadRunnerCrawler() throws IOException
	{
		arr_artist = new ArrayList<Artist>();
		arr_url = new ArrayList<String>();
		//DB = new DBStorage();			// only used when connect to the db
		//fetchDataFromURL(BASE_URL,0);
	}
	
	/**
	 * Run crawler
	 * @throws IOException
	 */
	public void runCrawler() throws IOException
	{
		fetchDataFromURL(BASE_URL,0);
	}
	
	@Override
	public void fetchDataFromURL(String new_url, int page) throws IOException {

		Boolean fetch_data = false;
		URL website = new URL(new_url);
	    URLConnection web_connect = website.openConnection();
	    BufferedReader in = new BufferedReader(
	                            new InputStreamReader(
	                            web_connect.getInputStream()));
	    while ((inputLine = in.readLine()) != null)
	    {	
	    	if (inputLine.indexOf("<div class=\"artist_home_item\">") >= 0)
	    	{
	    		inputLine = in.readLine();
	    		
	    		// get artist url
	    		this.arr_url.add(inputLine.substring(inputLine.indexOf("href=\"")+6,inputLine.indexOf("\"><img")));
	    		// get artist name
	    		this.arr_artist.add(new Artist(inputLine.substring
	    				(inputLine.lastIndexOf("alt=\"")+5,inputLine.indexOf("\" /></a>"))
	    				));
	    		fetch_data = true;
	    		
	    	} else
	    	
	    	if (inputLine.indexOf("<div class=\"artist_home_sideitem\">") >=0)
	    	{
	    		inputLine = in.readLine();
	    		//System.out.println
	    		String band_url = 
	    		(inputLine.substring
	    				(inputLine.indexOf("<a href=\"")+"<a href=\"".length(),inputLine.indexOf("\">"))
	    				);
	    		// get aritst url
	    		this.arr_url.add(band_url);
	    		// get artist name
	    		this.arr_artist.add(new Artist(inputLine.substring(inputLine.indexOf("alt=\"")+5,inputLine.indexOf("\" /> <a"))));
	    		//this.arr_artist.add(new Artist((band_url.replaceAll("/artists/", "").replace("/", ""))));
	    		
	    		fetch_data = true;
	    	}
	    	
	    	if (fetch_data==true)
	    	{
	    		this.arr_artist.get(arr_artist.size()-1).addGenre("metal");
	    		//Save type of genre to DB
	    		//this.DB.addGenreToTable(this.arr_artist.get(this.arr_artist.size()-1).getArr_genre().get(0));
	    		
	    		// Save artist's genre to DB
	    		//this.DB.addRowtoArtistGenreTable(this.arr_artist.get(this.arr_artist.size()-1));
	    		
	    		// getting the latest band added in the list and fetch info
	    		
	    		this.fetchEachArtist(this.arr_url.get(this.arr_url.size()-1),this.arr_artist.get(this.arr_artist.size()-1));
	    	}
	    	
	    	fetch_data = false;
	    }
	    in.close();
	}
	
	/**
	 * save artists info to the database
	 */
	public void saveToDB()
	{
		for (int i = 0; i < this.arr_artist.size(); i++) 
		{
			// Store artist info into a DB
    		this.DB.addRowtoArtistsTable(this.arr_artist.get(i));
		}
	}
	
	public void loadXML(String filename) throws Exception
	{
		BufferedInputStream stream = new BufferedInputStream(
                new FileInputStream(filename));

		XMLDecoder d = new XMLDecoder (stream);
		this.arr_artist = (List<Artist>) d.readObject();
		d.close();
	}
	
	
	public void printOut()
	{
		for (int i = 0; i < this.arr_artist.size();++i)
		{
			System.out.println(arr_artist.get(i).getName());
			System.out.println(arr_artist.get(i).getDes());
			System.out.println(arr_artist.get(i).getArr_albums().size());
			System.out.println(arr_url.get(i));
		}
	}
	
	/**
	 * Print all artists
	 */
	public void printListArtist()
	{
		System.out.println("Total Artists: " + arr_artist.size());
		for (int i = 0; i < arr_artist.size(); i++) {
			System.out.println(arr_artist.get(i).getArtist_id() +":"+ arr_artist.get(i).getName() + ":" + arr_artist.get(i).getArr_genre().get(0).getName() + ":" + arr_artist.get(i).getYear_found());
		}
	}
	
	/**
	 * Print all albums
	 */
	public void printListAlbums()
	{
		int count = 0;
		for (int i = 0; i < arr_artist.size(); i++) 
		{
			for (int k = 0; k < arr_artist.get(i).getArr_albums().size(); k++) {
				System.out.println(arr_artist.get(i).getArr_albums().get(k).getAlbum_name());
				++count;
			}
			
		}
		System.out.println("Total of " + count + " albums.");
	}
	
	/**
	 * Print all songs
	 */
	public void printListSongs()
	{
		int count = 0;
		for (int i = 0; i < arr_artist.size(); i++) 
		{
			for (int k = 0; k < arr_artist.get(i).getArr_albums().size(); k++) {
				for (int j = 0; j < arr_artist.get(i).getArr_albums().get(k).getList_of_songs().size(); j++) 
				{
					System.out.println(arr_artist.get(i).getArr_albums().get(k).getList_of_songs().get(j).getName());
					++count;
				}
				
			}
			
		}
		System.out.println("Total of " + count + " songs.");
		
	}

	@Override
	protected void fetchEachEntry(BufferedReader in) throws IOException {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Fetch each artist info from its own page
	 * @param url
	 * @throws IOException
	 */
	public void fetchEachArtist(String url, Artist entry) throws IOException
	{
		url = this.BASE_URL.concat(url.replace("/artists/", ""));
		URL website = new URL(url);
	    URLConnection web_connect = website.openConnection();
	    BufferedReader in = new BufferedReader(
	                            new InputStreamReader(
	                            web_connect.getInputStream()));
	    String album_name = null;
	    String release_date = null;
	    String album_url = null;
	    List<String> album_web = new ArrayList<String>();
	    while ((inputLine = in.readLine()) != null)
	    {
	    	//System.out.println(inputLine);
	    	
	    	// Fetching description/biogrpahy
	    	if (inputLine.indexOf("Official Biography")>=0)
	    	{
	    		inputLine = in.readLine();
	    		// Sanitize unwanted description/format
	    		if (inputLine.matches("<!--[if gte mso 9]><xml> Normal   0 </xml><![endif]--><!--  -->"))
	    		{
	    			inputLine = in.readLine();
	    		}
	    		entry.setDes(inputLine.replace("<!--[if gte mso 9]><xml> Normal   0 </xml><![endif]--><!--  --><br>", ""));
	    		//System.out.println(entry.getName() +" "+ entry.getDes() );
	    	}//if
	    	
	    	// fetch the latest album info 
	    	if (inputLine.matches("<div id=\"latest_album_module\" class=\"module gray\">"))
	    	{
	    		readUntil(in, "title=\"");
	    		try {
	    			album_url = inputLine.substring(inputLine.indexOf(("href=\""))+6,inputLine.indexOf("\" style"));

	    			//System.out.println(entry.getName() +"-->" + inputLine);
	    			album_name = (inputLine.substring(inputLine.indexOf("title=\"")+7,inputLine.indexOf("\" class=\"home_mini\"")));	
	    			readUntil (in,"Release Date:");
	    			release_date =  (inputLine.substring(inputLine.indexOf("</strong>")+10,inputLine.indexOf("<br")));
	    		} catch (Exception e){
	    			
	    		}
	    		
	    		//System.out.println(album_url + "dd");
	    		//entry.addAlbum(album_name,release_date);
	    	}
	    	
	    	
	    	// If latest album is found, but no discography
	    	if (inputLine.indexOf("<!-- eof: latest album -->") >=0){
	    		inputLine = in.readLine();
	    		inputLine = in.readLine();
	    		inputLine = in.readLine();
	    		inputLine = in.readLine();
	    		if (inputLine.indexOf("<!-- sof: discography -->")<0)
	    		{
	    			if (album_name!=null)
	    			{
	    				entry.addAlbum(album_name,release_date);
	    			}
	    			//System.out.println(album_url);
	    			album_web.add(album_url);	// Temporary save a list of the album URL
	    			//System.out.println(entry.getName() + "->no "+ entry.getArr_albums().size());
	    			
	    			// Fetch Songs
		    		this.fetchSongsFromAlbum(album_web.get(album_web.size()-1), entry.getArr_albums().get(entry.getArr_albums().size()-1));
		    		inputLine = in.readLine();
	    		}
	    	}
	    	
	    	// Fetching album information
	    	if (inputLine.indexOf("discography_list")>=0)
	    	{
	    		inputLine = in.readLine();
	    		// Fetch album url
	    		try {
	    			
	    			album_url = inputLine.substring(inputLine.indexOf(("href=\""))+6,inputLine.indexOf("\" style"));
	    			System.out.println(album_url);
	    			
	    			album_web.add(album_url); // Temporary save a list of album URLs
	    			
	    			inputLine = in.readLine();
	    			album_name = (inputLine.substring(inputLine.lastIndexOf("\">")+2,inputLine.indexOf("</a>")));
	    			//album_name = (inputLine.substring(inputLine.indexOf("boldfont\">")));
	    			//System.out.println(album_name);
	    			inputLine = in.readLine();

	    			release_date = (inputLine.substring(inputLine.indexOf("</strong>")+10));
		    		inputLine = in.readLine();
		    		entry.addAlbum(album_name, release_date);
		    		
		    		System.out.println(entry.getName() + "-> "+ entry.getArr_albums().size());
	    			
		    		// Save Album info to DB
		    		//this.DB.addRowtoAlbumsTable(entry.getArr_albums().get(entry.getArr_albums().size()-1));
		    		// Fetch Songs
		    		this.fetchSongsFromAlbum(album_web.get(album_web.size()-1), entry.getArr_albums().get(entry.getArr_albums().size()-1));
		    		
	    		} catch (Exception e)
	    		{
	    			/*while (inputLine.indexOf("<span class=\"boldfont\">")<0) inputLine = in.readLine();
	    			if (inputLine.indexOf("<span class=\"boldfont\">") >=0){
	    				album_url = inputLine.substring(inputLine.indexOf(("href=\""))+6,inputLine.lastIndexOf("/a"));
	    				System.out.println(album_url);
	    				System.out.println(album_url.substring(0,inputLine.indexOf("\">")));
	    				album_name = (album_url.substring(inputLine.lastIndexOf("\">"),inputLine.lastIndexOf("<")));
	    				System.out.println("Catch name :" + album_name);
	    				album_url = album_url.substring(inputLine.indexOf(("href=\""))+6,inputLine.indexOf("<"));
	    				System.out.println("catch this " + album_url);
	    			} */
	    		}
	    		
	    		
	    	}//if
	    	
	    	
	    }//while
	    in.close();
	    
	    
	    
	    // Set Year found
	    try {
	    	String year = entry.getArr_albums().get(entry.getArr_albums().size()-1).getRelease_date();
	    	entry.setYear_found(Integer.parseInt(year.substring(year.lastIndexOf(".")+1)));
	    } catch (Exception e){
	    	//Unknown year found
	    	entry.setYear_found(3000);
	    }
	    //this.DB.addRowtoArtistsTable(this.arr_artist.get(this.arr_artist.size()-1));
	}
	
	/**
	 * get songs from the album
	 * @param url
	 * @param entry
	 * @throws IOException
	 */
	public void fetchSongsFromAlbum(String url, Album entry) throws IOException
	{
		url = this.BASE_URL.replace("/artists/", url);
		URL website = new URL(url);
	    URLConnection web_connect = website.openConnection();
	    BufferedReader in = new BufferedReader(
	                            new InputStreamReader(
	                            web_connect.getInputStream()));

	    String[] arr_song = null;
	    //System.out.println(url);
	    while ((inputLine = in.readLine()) != null)
	    {
	    	if (inputLine.indexOf("<div id=\"tracklisting\"") >= 0)
	    	{
	    		inputLine = in.readLine();
	    		// Sanitze input
	    		inputLine = inputLine.replace("<br />", "<br/>");
	    		inputLine = inputLine.replace("&nbsp;", "");
	    		
	    		try {
	    			inputLine = inputLine.substring(inputLine.indexOf("1")); // set starting index
	    		} catch (Exception e)
	    		{
	    			
	    		}
	    		//System.out.println(inputLine);
	    		arr_song = inputLine.split("<br/>");	// split when see <br/>
	    		
	    	}
	    	
	    }
	    in.close();
		
	    // Fetch all songs to the album
	    for (int i = 0; i < arr_song.length; i++) 
	    {
	    	if (!(arr_song[i].indexOf("<strong")>=0))
	    	{
	    		if (arr_song[i].isEmpty() || arr_song[i]==null)
	    		{
	    			entry.addSongToAlbum("Unavailable",0);
	    		} else {
	    			entry.addSongToAlbum(arr_song[i], 0);
	    		}
	    		// Save Song to DB
	    		//this.DB.addRowtoSongsTable(entry.getList_of_songs().get(entry.getList_of_songs().size()-1));
	    		
	    		//System.out.println(arr_song[i]);
	    	} else break;
		}
	    
	}

	/**
	 * Save As XML file
	 * @throws FileNotFoundException
	 */
	public void saveAsXML() throws FileNotFoundException
	{
		saveAsXML(this.arr_artist);
	}
	
	@Override
	protected String getURLFromPage(int page) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Artist> getArr_artist() {
		return arr_artist;
	}

	public void setArr_artist(List<Artist> arr_artist) {
		this.arr_artist = arr_artist;
	}
}
