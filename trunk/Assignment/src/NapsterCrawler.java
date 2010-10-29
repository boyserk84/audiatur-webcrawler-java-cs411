import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Get artist bio
 * @author nkemav2
 *
 */
public class NapsterCrawler extends Crawler{
	
	private final String BASE_URL = "http://music.napster.com/105798-music/bio/";	//base URL
	
	private String TARGET_URL;	// Target URL to get info
	
	private Artist artist;		// Artist reference

	public NapsterCrawler(Artist artist) throws IOException
	{
		this.TARGET_URL = BASE_URL.replaceAll("105798", this.reformatString(artist.getName()));
		//System.out.println(artist.getName());
		//System.out.println(TARGET_URL);
		this.artist = artist;
		fetchDataFromURL(this.TARGET_URL,0);
		this.artist.setName(this.reformatString(artist.getName()));
	}
	
	/**
	 * return a proper format for string with all spaces and underscore replaced with dash
	 * @param input
	 * @return
	 */
	public String reformatString(String input)
	{
		return (this.replaceSpace(input).replaceAll("_", "-")).replace("-(band)", "");
	}
	


	@Override
	/**
	 * Get data from URL
	 */
	public void fetchDataFromURL(String new_url, int page) throws IOException 
	{
		// TODO Auto-generated method stub
		URL website = new URL(TARGET_URL);
	    URLConnection web_connect = website.openConnection();
	    BufferedReader in = new BufferedReader(
	                            new InputStreamReader(
	                            web_connect.getInputStream()));
	    
	    while ((inputLine = in.readLine()) != null)
	    {	
	    	if (inputLine.indexOf("<div class=\"noResultsText\"")>=0)	// if not found
	    	{
	    		artist.setDes(null);
	    		break;
	    	}
	    	if (inputLine.indexOf("<div class=\"content\"")>= 0 ) // if found content
			{
	    		inputLine = in.readLine();
	    		if (inputLine.indexOf("$bio.content") <= 0)	// if there is a description
	    		{
	    			artist.setDes(inputLine);
	    			//System.out.println(artist.getName() +"<--:-->" + inputLine);
	    		} else {
	    			artist.setDes(null);
	    		}
			}
	    }
	    in.close();
	}

	@Override
	protected void fetchEachEntry(BufferedReader in) throws IOException {
		
	}
	
	@Override
	protected String getURLFromPage(int page) throws IOException {
		
		return null;
	}
	
	

}
