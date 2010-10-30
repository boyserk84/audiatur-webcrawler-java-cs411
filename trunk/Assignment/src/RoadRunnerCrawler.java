import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
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
	
	/**
	 * Constructor
	 * @throws IOException 
	 */
	public RoadRunnerCrawler() throws IOException
	{
		arr_artist = new ArrayList<Artist>();
		arr_url = new ArrayList<String>();
		fetchDataFromURL(BASE_URL,0);
	}
	
	@Override
	public void fetchDataFromURL(String new_url, int page) throws IOException {

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
	    		
	    		// get artist name
	    		this.arr_url.add(inputLine.substring(inputLine.indexOf("href=\"")+6,inputLine.indexOf("\"><img")));
	    		// get artist url
	    		this.arr_artist.add(new Artist(inputLine.substring
	    				(inputLine.lastIndexOf("alt=\"")+5,inputLine.indexOf("\" /></a>"))
	    				));
	    		// getting the latest band added in the list and fetch info
	    		fetchEachArtist(this.arr_url.get(this.arr_url.size()-1),this.arr_artist.get(this.arr_artist.size()-1));
	    	}
	    }
	    in.close();
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
	    while ((inputLine = in.readLine()) != null)
	    {
	    	//System.out.println(inputLine);
	    	
	    	// Fetching description/biogrpahy
	    	if (inputLine.indexOf("Official Biography")>=0)
	    	{
	    		inputLine = in.readLine();
	    		entry.setDes(inputLine);
	    	}//if
	    	
	    	// Fetching album information
	    	if (inputLine.indexOf("discography_list")>=0)
	    	{
	    		inputLine = in.readLine();
	    		inputLine = in.readLine();
	    		//entry.addAlbum(inputLine.substring(inputLine.lastIndexOf("\">")+2,inputLine.indexOf("</a>")), date)
	    		//System.out.println(inputLine.substring(inputLine.lastIndexOf("\">")+2,inputLine.indexOf("</a>")));
	    		album_name = (inputLine.substring(inputLine.lastIndexOf("\">")+2,inputLine.indexOf("</a>")));
	    		inputLine = in.readLine();
	    		entry.addAlbum(album_name, (inputLine.substring(inputLine.indexOf("</strong>")+10)));
	    		//System.out.println(inputLine.substring(inputLine.indexOf("</strong>")+10));
	    	} else
	    	
	    	// Alternatively, fetch album info 
	    	if (inputLine.indexOf("<li class=\"latest_album\">")>=0)
	    	{
	    		readUntil(in, "title=\"");
	    		album_name = (inputLine.substring(inputLine.indexOf("title=\"")+7,inputLine.indexOf("\" class=\"home_mini\"")));	
	    		readUntil (in,"Release Date:");
	    		
	    		entry.addAlbum(album_name, (inputLine.substring(inputLine.indexOf("</strong>")+10,inputLine.indexOf("<br"))));
	    	}
	    }//while
	    in.close();
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
