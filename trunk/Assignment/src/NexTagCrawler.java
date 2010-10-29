import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.String;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * NexTagCrawler class: derived from Crawler
 * This class will spawn a crawler to fetch all information based on a given tag from the website www.nextag.com.
 * @author nate kemavaha
 *
 */
public class NexTagCrawler extends Crawler{
	
	private static final long serialVersionUID = 1L;
	
	private final String BASE_URL_NEXTAG = "http://www.nextag.com/";		// Base URL for nextag website
	
	// NEXTAG specific value
	private final String ENTRY_TAG = "opPNLink";
	private final String DESCRIPTION_TAG = "sr-info-description";
	private final String GRANDTOTAL_TAG = "sr-h-o-r";
	private final String VENDOR_TAG = "<a rel=\"nofollow\" class=\"featuredSeller\"";
	
	/**
	 * Constructor
	 * @param tag keyword
	 * @param page page
	 * @throws IOExceptionbreak;
	 */
	public NexTagCrawler(String tag, int page) throws IOException
	{
		keyword = tag;
		
		if (tag.isEmpty() || page < -1) return;
		
		if (page > 1)
		{
			this.fetchDataFromURL(getURLFromPage(page), page);
		} else {
			this.fetchDataFromURL(BASE_URL_NEXTAG + replaceSpace(tag) + "/stores-html", page);
		} 
	}
	
	/**
	 * get URL address corresponding to the page number
	 * @param page page number
	 * @return URL address corresponding to the page number. 
	 * Otherwise, NULL is returned and error message is displayed.
	 * @throws IOException
	 */
	protected String getURLFromPage(int page) throws IOException {
		String new_url = "";
	
		try {
			URL website = new URL(BASE_URL_NEXTAG + replaceSpace(keyword) + "/stores-html");
			URLConnection web_connect = website.openConnection();
	        BufferedReader in = new BufferedReader(
	                                new InputStreamReader(
	                                web_connect.getInputStream()));
	       
	        while ((inputLine = in.readLine()) != null)
	        {
	        	
	            if (inputLine.indexOf("currentpage") > NOT_FOUND)
	            {
	            	new_url = (inputLine.substring(inputLine.indexOf("href=\"/")+"href=\"/".length(),inputLine.indexOf("\">" + 2)));
	            	new_url = new_url.replace("z1z", "z" + (page-1) + "z");
	            } 	
	        }//while
	        in.close();
		} catch (MalformedURLException e) {
			System.out.println("Unable to  retrieve the URL from page!");
			return null;
		}
		return BASE_URL_NEXTAG + new_url;
	
	}

	/**
	 * Fetch data from the URL
	 * @param new_url Web address
	 * @param page page number
	 * @throws IOException
	 */
	protected void fetchDataFromURL(String new_url, int page)
			throws IOException {
				URL website = new URL(new_url);
			    URLConnection web_connect = website.openConnection();
			    BufferedReader in = new BufferedReader(
			                            new InputStreamReader(
			                            web_connect.getInputStream()));
			    
			    while ((inputLine = in.readLine()) != null)
			    {	
					// get the length of all entries
					if (inputLine.indexOf(GRANDTOTAL_TAG) > NOT_FOUND)
					{
						total_query = sanitizeNumberStr(inputLine.substring(inputLine.indexOf("of ")+"of ".length(), inputLine.indexOf(" matches")));
						if (page < 0) break; 	// if no page indicated
					} else {
						fetchEachEntry(in);		// Fetch data
					}
			    }//while
			    
			    in.close();
			}

	/**
	 * Fetching a product entry
	 * @param in BufferedReader object
	 * @throws IOException
	 */
	protected void fetchEachEntry(BufferedReader in) throws IOException {
		// Fetching each entry
		if (foundEntry())
		{
			Product entry = new Product();
			++page_query;
			
			entry.setTitle(fetchTitle(in)); 		// title
			entry.setDesc(fetchDescription(in));	// description
			entry.setVendor(fetchVendor(in));		// vendor
		    
			result.add(entry);
		}//if
	}

	/**
	 * Checking if a product entry is found during crawling
	 * @return True if a product entry is found. Otherwise, False is returned.
	 */
	private boolean foundEntry() 
	{
		return inputLine.indexOf(ENTRY_TAG) > NOT_FOUND;
	}
	
	/**
	 * Fetching title from buffered string
	 * @param in buffer string
	 * @return Title string without HTML tags
	 */
	private String fetchTitle(BufferedReader in)
	{
		try {
			return removeHTMLTags(inputLine.substring((inputLine.indexOf(">")+ 1),(inputLine.indexOf("</a>"))));
		} catch (Exception e){
			return "unknown";
		}
	}
	
	/**
	 * Fetching description from buffered string
	 * @param in buffer string
	 * @return Description string without HTML tags
	 * @throws IOException
	 */
	private String fetchDescription(BufferedReader in) throws IOException
	{
		while (inputLine.indexOf(DESCRIPTION_TAG) <= NOT_FOUND)
		{
			inputLine = in.readLine();
			if (inputLine == null) return "Unknown";
		}
		inputLine = in.readLine();
		return removeHTMLTags(inputLine);
	}
	
	/**
	 * Fetching vendor info from buffered string
	 * @param in buffer string
	 * @return Vendor info
	 * @throws IOException
	 */
	private String fetchVendor(BufferedReader in) throws IOException
	{
		while(inputLine.indexOf(VENDOR_TAG) <= NOT_FOUND)
	    {
	    	inputLine = in.readLine();
	    	if (inputLine == null) return "Unknown";
	    }
		return inputLine.substring(inputLine.indexOf("_blank\" >")+"_blank\" >".length(),inputLine.indexOf("</a>") );

	}
}
