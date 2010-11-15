import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


public class AmazonCrawler extends Crawler implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Artist> arr_artist;
	private List<String> arr_URL;
	private Hashtable<String,String> artist_hash;
	
	private String BASE_URL = "http://www.amazon.com";
	private String blue_URL = "http://www.amazon.com/Blues-Music/b/ref=amb_link_7154062_12?ie=UTF8&node=31&pf_rd_m=ATVPDKIKX0DER&pf_rd_s=browse&pf_rd_r=0BAKWT3Y49M7JT3W7R8X&pf_rd_t=101&pf_rd_p=1279348062&pf_rd_i=5174";
	private String contempBlue_URL ="http://www.amazon.com/Contemporary-Blues/b/ref=bw_ab_31_6?ie=UTF8&node=5236&pf_rd_p=168707901&pf_rd_s=browse&pf_rd_t=101&pf_rd_i=31&pf_rd_m=ATVPDKIKX0DER&pf_rd_r=1Z3E6KYDPXCP5D71PA62";
	private String hiphop_URL ="http://www.amazon.com/Rap-Hip-Hop-Music/b/ref=bw_ab_38_0?ie=UTF8&node=67196&pf_rd_p=236927301&pf_rd_s=browse&pf_rd_t=101&pf_rd_i=38&pf_rd_m=ATVPDKIKX0DER&pf_rd_r=15W7573SBKTQYXDCMRVG";
	private String jamaicanska_URL ="http://www.amazon.com/World-International-Jamaican-Ska-Reggae-Music/b/ref=amb_link_44269922_4?ie=UTF8&node=63890&sr=1-2-tc&pf_rd_m=ATVPDKIKX0DER&pf_rd_s=browse&pf_rd_r=1H0HB0PREX4FPH37JMB1&pf_rd_t=101&pf_rd_p=90891022&pf_rd_i=63885";
	private String powerpop_URL ="http://www.amazon.com/Power-Pop-Rock-Music/b/ref=bw_ab_40_11?ie=UTF8&node=599862&pf_rd_p=236929701&pf_rd_s=browse&pf_rd_t=101&pf_rd_i=40&pf_rd_m=ATVPDKIKX0DER&pf_rd_r=07K1904GNKJRYD9J89C4";
	private String dancepop_URL ="http://www.amazon.com/Dance-Pop-Music/b/ref=bw_ab_37_9?ie=UTF8&node=63704&pf_rd_p=236813301&pf_rd_s=browse&pf_rd_t=101&pf_rd_i=37&pf_rd_m=ATVPDKIKX0DER&pf_rd_r=047FF4YRDBA0BJDJY0Y0";
	private String generalPop_URL ="http://www.amazon.com/Pop-Music/b/ref=bw_ab_37_0?ie=UTF8&node=67172&pf_rd_p=236813301&pf_rd_s=browse&pf_rd_t=101&pf_rd_i=37&pf_rd_m=ATVPDKIKX0DER&pf_rd_r=047FF4YRDBA0BJDJY0Y0";
	
	private String xml_filename = "default.xml";
	/**
	 * Constructor
	 */
	public AmazonCrawler(String keyword)
	{	
		Artist.startIDGenwith(124);
		this.keyword = keyword;
		this.xml_filename = keyword.trim().concat(".xml");
		arr_artist = new ArrayList<Artist>();
		arr_URL = new ArrayList<String>();
		artist_hash = new Hashtable<String,String>();
	}
	
	public void runCrawler() throws IOException
	{
		this.fetchDataFromURL(generalPop_URL, 0);
		this.fetchAllArtists();
	}
	
	
	@Override
	protected String getURLFromPage(int page) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	void fetchDataFromURL(String new_url, int page) throws IOException {
		// TODO Auto-generated method stub
		URL website = new URL(new_url);
	    URLConnection web_connect = website.openConnection();
	    BufferedReader in = new BufferedReader(
	                            new InputStreamReader(
	                            web_connect.getInputStream()));
	    while ((inputLine = in.readLine()) != null)
	    {	
	    	if (inputLine.indexOf("<span class=\"ptBrand\">by") >= 0)
	    	{
	    		String line = inputLine.substring(inputLine.indexOf("by"));
	    		
	    		if (line.indexOf("<a href") >=0)	// if there is a link
	    		{
	    			String url = line.substring(line.indexOf("href")+6,line.indexOf("\">"));
	    			String artist = line.substring((line.indexOf(">")+1),(line.indexOf("</a>")));
	    			
	    			// If the artist has not been added yet
	    			if (!this.artist_hash.contains(artist))
	    			{
	    				this.artist_hash.put(artist, artist);
	    				// Fetch artist information such as genre, artist name and url
	    				this.arr_artist.add(new Artist(artist));
	    				
	    				if (keyword.equals("Blues"))
		    			{
			    			this.arr_artist.get(this.arr_artist.size()-1).addGenre(this.keyword);
			    			this.arr_artist.get(this.arr_artist.size()-1).addGenre("Contemporary Blues");
		    			}
		    			if (keyword.equals("Hiphop"))
		    			{
		    				this.arr_artist.get(this.arr_artist.size()-1).addGenre(this.keyword);
			    			this.arr_artist.get(this.arr_artist.size()-1).addGenre("General HipHop");
		    			}
		    			if (keyword.equals("Jamaican Ska"))
		    			{
		    				this.arr_artist.get(this.arr_artist.size()-1).addGenre(this.keyword);
			    			this.arr_artist.get(this.arr_artist.size()-1).addGenre("Reggae");
		    			}
		    			if (keyword.equals("Power Pop"))
		    			{
		    				this.arr_artist.get(this.arr_artist.size()-1).addGenre(this.keyword);
			    			this.arr_artist.get(this.arr_artist.size()-1).addGenre("Pop");
		    			}
		    			if (keyword.equals("Dance Pop"))
		    			{
		    				this.arr_artist.get(this.arr_artist.size()-1).addGenre(this.keyword);
			    			this.arr_artist.get(this.arr_artist.size()-1).addGenre("Pop");
		    			}
		    			if (keyword.equals("General Pop"))
		    			{
		    				this.arr_artist.get(this.arr_artist.size()-1).addGenre(this.keyword);
			    			this.arr_artist.get(this.arr_artist.size()-1).addGenre("Pop");
		    			}
		    			this.arr_URL.add(this.BASE_URL+url);
	    			}
	    		}
	    	}
	    }
	    in.close();
	}
	
	protected void fetchAllArtists() throws IOException
	{
		for (int i = 0; i < this.arr_URL.size(); i++) 
		{
			fetchEachArtist(this.arr_URL.get(i), this.arr_artist.get(i));
		}
	}
	
	protected void fetchEachArtist(String url, Artist artist) throws IOException
	{
		System.out.println(url);
		URL website = new URL(url);
	    URLConnection web_connect = website.openConnection();
	    BufferedReader in = new BufferedReader(
	                            new InputStreamReader(
	                            web_connect.getInputStream()));
	    String album_name;
	    String theurl;
	    List<String> url_name = new ArrayList<String>();
	    List<String> url_album = new ArrayList<String>();
	    while ((inputLine = in.readLine()) != null)
	    {	
	    	// Fetch albums
	    	if (inputLine.indexOf("faceoutTitle") >= 0)
	    	{
	    		inputLine = in.readLine();
	    		
	    		// get URL for album
	    		theurl = inputLine.substring(inputLine.indexOf("href")+6,inputLine.indexOf(("title"))-2);
	    		//System.out.println(theurl);
	    		
	    		// get album name
	    		album_name = inputLine.substring(inputLine.indexOf("title=\"")+7, inputLine.indexOf("\" id"));
	    		//System.out.println(album_name);
	    		
	    		url_album.add(this.BASE_URL + theurl);
	    		url_name.add(album_name);
	    		// Add album
	    		artist.addAlbum(album_name,"");
	    		//System.out.println(artist.getArr_albums().get(artist.getArr_albums().size()-1).getAlbum_name());
	    	}
	    	
	    	// Fetch biography
	    	if (inputLine.indexOf("<div id=\"artistCentralBio_officialFullBioContent\" class")>=0)
	    	{
	    		
	    		String bio = inputLine.substring(inputLine.indexOf("Body\">")+6);
	    		artist.setDes(bio);
	    		//System.out.println(bio);
	    	}
	    	//System.out.println(inputLine);
	    }
	    in.close();
	    
	    // Fetch songs from each album
	    for (int i = 0; i < url_name.size(); i++) 
	    {
	    	this.fetchSongsFromAlbum(url_album.get(i), artist.getArr_albums().get(i));
		}
	    
	}

	protected void fetchSongsFromAlbum(String url, Album album) throws IOException
	{
		//System.out.println(url);
		URL website = new URL(url);
	    URLConnection web_connect = website.openConnection();
	    BufferedReader in = new BufferedReader(
	                            new InputStreamReader(
	                            web_connect.getInputStream()));
	    String song;
	    String run_time;
	    //System.out.println(album.getAlbum_name());
	    while ((inputLine = in.readLine()) != null)
	    {
	    	// fetch each songs
	    	if (inputLine.indexOf("<td class=\"playCol\">")>=0)
	    	{
	    		try {
		    		song=inputLine.substring(inputLine.indexOf("title"));
		    		song = song.substring(song.indexOf("href"));
		    		run_time = song.substring(song.indexOf("runtimeCol")+12 ,song.indexOf("</td><td class=\"priceCol\""));
		    		song = song.substring(song.indexOf("href"),song.indexOf("</a></td>"));
		    		song = song.substring(song.indexOf("\">")+2);
		    		//System.out.println(song +":"+ run_time);
		    		
	    		} catch (Exception e)
	    		{
	    			
		    			// differ format
		    			in.readLine();
		    			in.readLine();
		    			inputLine = in.readLine();
		    			song = inputLine.substring(inputLine.lastIndexOf("\">")+2,inputLine.indexOf("</a>"));
		    			while (inputLine.indexOf("runtimeCol") <=0)
		    			{
		    				inputLine = in.readLine();
		    			}
		    			run_time = inputLine.substring(inputLine.indexOf(">")+1, inputLine.indexOf("</td>"));
		    			//System.out.println(run_time);
	    			
	    		}
	    		album.addSongToAlbum(song, run_time);
	    		
	    	}
	    	
	    	//Fetch release album date
	    	if (inputLine.indexOf("Release Date:</b>")>=0)
	    	{
	    		String date = inputLine.substring(inputLine.indexOf("Release Date:</b>")+"Release Date:</b> ".length(),inputLine.indexOf("</li>"));
	    		album.setRelease_date(date);
	    		//System.out.println(date);
	    	}
	    }
	    in.close();

	}
	public void saveasXML() throws FileNotFoundException
	{
		this.saveAsXML(this.arr_artist,this.xml_filename);
	}
	
	public void loadXML(String filename) throws FileNotFoundException
	{
		BufferedInputStream stream = new BufferedInputStream(
                new FileInputStream(filename));

		XMLDecoder d = new XMLDecoder (stream);
		this.arr_artist = (List<Artist>) d.readObject();
		d.close();
		
		// Prepare hash mapping
		for (int i = 0; i < this.arr_artist.size(); i++) 
		{
			this.artist_hash.put(this.arr_artist.get(i).getName(),this.arr_artist.get(i).getName());
		}
		
	}
	
	public void printOut()
	{
		for (int i = 0; i < this.arr_artist.size(); i++) {
			
			System.out.println(this.arr_artist.get(i).getName());
			System.out.println(this.arr_artist.get(i).getDes());
			for (int j = 0; j < this.arr_artist.get(i).getArr_albums().size(); j++) 
			{
				System.out.println("Album Name: " + this.arr_artist.get(i).getArr_albums().get(j).getAlbum_name());
				System.out.println("Year:" + this.arr_artist.get(i).getArr_albums().get(j).getRelease_date());
			}
		}
		
	}
	
	@Override
	protected void fetchEachEntry(BufferedReader in) throws IOException {
		// TODO Auto-generated method stub
		
	}

}