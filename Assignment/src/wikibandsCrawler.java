import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


/**
 * Feed crawler to fetch the artists/musicians/bands information based on genre
 * from the wiki-pedia.
 * 	- Need to create band object
 * 	- Need to spawn threads to fetch each band info
 * @author nate
 *
 */
public class wikibandsCrawler extends Crawler{

	private static final long serialVersionUID = 1L;
	private final String BASE_URL_WIKI = "http://en.wikipedia.org/wiki/";
	
	private List<Artist> arr_artist;
	
	
	public wikibandsCrawler(String genre) throws IOException
	{
		this.keyword = genre.toLowerCase();
		if (genre.isEmpty()) return;
		arr_artist = new ArrayList<Artist>();
		
		if (keyword.equals("death metal"))
		{
			this.fetchDataFromURL(BASE_URL_WIKI.concat("List_of_death_metal_bands"),0);
		} else if (keyword.equals("industrial metal"))
		{
			this.fetchDataFromURL(BASE_URL_WIKI.concat("List_of_industrial_metal_bands"), 0);
		}	
		else
		{
			System.out.println("WTF");
		}
	}
	
	public List<Artist> getData()
	{
		return arr_artist;
	}
	
	@Override
	protected String getURLFromPage(int page) throws IOException 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void fetchDataFromURL(String new_url, int page) throws IOException 
	{
		//System.out.println(new_url);
		URL website = new URL(new_url);
	    URLConnection web_connect = website.openConnection();
	    BufferedReader in = new BufferedReader(
	                            new InputStreamReader(
	                            web_connect.getInputStream()));
	    
	    while ((inputLine = in.readLine()) != null)
	    {	
	    	//System.out.println(inputLine);
	    	if (inputLine.indexOf("flagicon")>= 0 )
			{

	    		if (checkLinkExistence() )
	    		{
	    			// fetch all artist names
	    			arr_artist.add(new Artist(inputLine.substring
						(inputLine.lastIndexOf("png")+77,inputLine.lastIndexOf("title")-2)));
	    			System.out.println(inputLine.substring(inputLine.lastIndexOf("png")+77,inputLine.lastIndexOf("title")-2));
	    			
	    		}
			}//if
			
	    }//while
	    
	    in.close();
	}
	
	public void fetchDataFromSubURL(String sub_url, Artist entry) throws IOException
	{
		URL website = new URL(sub_url);
	    URLConnection web_connect = website.openConnection();
	    BufferedReader in = new BufferedReader(
	                            new InputStreamReader(
	                            web_connect.getInputStream()));
	    
	    while ((inputLine = in.readLine()) != null)
	    {	
	    	if (inputLine.indexOf("<div class=\"content\"")>= 0 )
			{
	    		inputLine = in.readLine();
	    		if (inputLine.indexOf("$bio.content") <= 0)
	    		{
	    			entry.setDes(inputLine);
	    			System.out.println(inputLine);
	    		} else {
	    			System.out.println("NOT FOUND");
	    		}
	    		//entry.setYear_found();
	    		
			}//if
			
	    }//while
	    
	    in.close();
	}

	/**
	 * checking if link does exist
	 * @return
	 */
	private boolean checkLinkExistence() 
	{
		return inputLine.indexOf("page does not exist") <0;
	}

	@Override
	protected void fetchEachEntry(BufferedReader in) throws IOException 
	{
		// TODO Auto-generated method stub
		if (inputLine.indexOf("flagicon")>= 0)
		{
			System.out.println(inputLine.substring(inputLine.indexOf("span>")));
		}
	}
	
	/**
	 * 
	 * @param URL
	 * @throws IOException
	 */
	protected void fetchEachBandInfo(String URL) throws IOException
	{
		//TODO implement this
	}

	
}
