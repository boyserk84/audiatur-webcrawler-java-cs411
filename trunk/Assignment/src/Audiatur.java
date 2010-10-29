import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Main processor
 * @author nkemav2
 *
 */
public class Audiatur {
	wikibandsCrawler wiki_bot;
	NapsterCrawler napster_bot;
	
	List<Artist> artist_list;
	
	public Audiatur(String genre) throws IOException
	{
		artist_list = new ArrayList<Artist>();
		wiki_bot = new wikibandsCrawler(genre); // find a list of bands/artists
		artist_list = wiki_bot.getData();
		
		System.out.println(artist_list.size());
		
		// Fetch information for each element in the list of artist
		for (int i=0; i < artist_list.size(); ++i)
		{
			System.out.println(i);
			napster_bot = new NapsterCrawler(artist_list.get(i)); // Fetch info from Napster
		}
	}

	
	public void printBands()
	{
		for (int i=0; i < artist_list.size();++i)
		{
			System.out.println(artist_list.get(i).getName());
			System.out.println(artist_list.get(i).getDes());
		}//for
	}
}
