import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;



/**
 * Crawler: Abstract class
 * This class is a model class for a feed crawler. 
 * All common methods and members are included for utilize in the derived class.
 * @author nkemav2
 *
 */
abstract public class Crawler implements Serializable{
	protected static final long serialVersionUID = 1L;
	protected final int NOT_FOUND = -1;
	
	protected ArrayList<Product> result;		// List of products from fetching
	protected String inputLine;					// Buffered line
	protected int total_query = -1;				// Total query 
	/**
	 * Keyword that this crawler will search for
	 */
	protected String keyword;					// Keyword that this crawler will search for
	protected int page_query = 0;				// Total query per page

	/**
	 * Default constructor
	 */
	public Crawler() 
	{
		super();
		result = new ArrayList<Product>();
	}

	/**
	 * get URL address corresponding to the page number
	 * @param page
	 * @return URL address corresponding to the page number
	 * @throws IOException
	 */
	abstract protected String getURLFromPage(int page) throws IOException;

	/**
	 * Fetch data from the URL
	 * @param new_url Web address
	 * @param page page number
	 * @throws IOException
	 */
	abstract void fetchDataFromURL(String new_url, int page) throws IOException;

	/**
	 * Fetching product entry
	 * @param in BufferedReader object
	 * @throws IOException
	 */
	abstract protected void fetchEachEntry(BufferedReader in) throws IOException;

	/**
	 * remove all HTML tags from the given string
	 * @param str a string
	 * @return a string without html tags
	 */
	protected String removeHTMLTags(String str) {
		String new_str = str.replaceAll("<.*?>","");
		new_str = new_str.replace("&#34;","-");
		new_str = new_str.replace("&quot;", "\"");
		new_str = new_str.replace("&amp;", "&");
		new_str = new_str.replace("&#39;", "'");
		return new_str;
	}

	/**
	 * Sanitize numeric string and convert it to integer
	 * @param str 
	 * @return return numeric value otherwise -1 is returned
	 */
	protected int sanitizeNumberStr(String str) {
		try {
			return Integer.parseInt(str.replace(",", ""));
		} catch (Exception e){
			return -1;
		}
	}

	/**
	 * Keep reading buffer until meet a target string
	 * @param in bufferedreader 
	 * @param until_string target string
	 * @throws IOException
	 */
	protected void readUntil(BufferedReader in, String until_string) throws IOException {
		while (inputLine.indexOf(until_string) <= 0)
		{
			inputLine = in.readLine();	
			if (inputLine ==null) return;
		}
	}
	
	/**
	 * Save all fetching entries as XML file
	 * @throws FileNotFoundException
	 */
	protected void saveAsXML(List<Artist> list) throws FileNotFoundException
	{
		XMLEncoder e = new XMLEncoder(
		                new BufferedOutputStream(
		                    new FileOutputStream("Roadrunner_test.xml")));

		e.writeObject(list);

		e.close();
	}
	
	/**
	 * Print result from the crawler
	 */
	public void printResult() 
	{
		for (int i = 0; i < result.size(); i++) 
		{
			result.get(i).printProduct();
		}
	}

	/**
	 * Replace space with dash
	 * @param input string
	 * @return a new string with all spaces replaced with dash.
	 */
	public String replaceSpace(String input) {
		return (input.replace(" ", "-"));
	}

	/**
	 * @return Total query result
	 */
	public int getTotal_query() {
		return (total_query < 0)?0:total_query;
	}

	/**
	 * @return total of query in a particular page
	 */
	public int getPage_query() {
		return page_query;
	}


}