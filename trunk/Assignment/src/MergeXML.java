import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


/**
 * ISSUES with duplicate and reorder of artist ID after found duplicate
 */

/**
 * MergeXML
 * 	- Merge all XML files into one XML file and sanitize ordering aka artist ID.
 * @author nkemav2
 *
 */
public class MergeXML extends AmazonCrawler{

	private List<String> files;	// List of XML files
	private List<Artist> temp_artists;
	
	
	/**
	 * Constructor
	 * @param f array/list of xml files
	 * @throws FileNotFoundException 
	 */
	public MergeXML(List<String> f) throws FileNotFoundException
	{
		files = f;
		this.loadXML(files.get(0)); // only for first xml file only
		
		for (int i = 1; i < f.size(); i++) 
		{
			mergeData(i);
		}
		this.saveasXMLFile("audiatur.xml");
		
	}

	/**
	 * Merge data
	 * @param xml_index index of file in the list
	 * @throws FileNotFoundException 
	 */
	private void mergeData(int xml_index) throws FileNotFoundException {
		
		this.temp_artists = loadXMLwithMergeOption(files.get(xml_index));
		// for each artist from the XML file
		for (int i=0; i < this.temp_artists.size(); ++i)
		{
			// Print out
			System.out.println(this.temp_artists.get(i).getName() + ":");
			
			// check for duplicate
			if (this.artist_hash.containsKey(temp_artists.get(i).getName()))
			{
				// get artist index from the hash
				String artist_index = (Long.toString(this.artist_hash.get(temp_artists.get(i).getName())));
				
				if (this.artist_hash.get(temp_artists.get(i).getName()) < 126) // if it's in the roadrunner list
				{
					// modify artist ID to artist_index (existing ID)
					temp_artists.get(i).resetIdTo(artist_index);
				
					// Add albums, songs, genre
					this.arr_artist.get(Integer.parseInt(artist_index)).addListofAlbum(temp_artists.get(i).getArr_albums());
				} else {
					// add only genres
					this.arr_artist.get(Integer.parseInt(artist_index)).addListOfGenre(temp_artists.get(i).getArr_genre());
				}
				System.out.println("Duplicate Found on "+ this.temp_artists.get(i).getName() +":ID = "+ artist_index);
		
			} else {
				// if not duplicate add into a hash and change artist ID

				// modify artist ID to the latest counter number
				temp_artists.get(i).resetIdTo(Integer.toString(this.arr_artist.size()+((i==0)?i+1:1)));
				
				// put key and value into a hash table
				this.artist_hash.put(temp_artists.get(i).getName(), Long.parseLong(temp_artists.get(i).getArtist_id()));
				
				// add this artist to the artist list
				this.arr_artist.add(temp_artists.get(i));
			}		
		}//for
	}
	
	/**
	 * Loading subsequent XML files Only
	 * @param filename
	 * @throws FileNotFoundException
	 */
	private List<Artist> loadXMLwithMergeOption(String filename) throws FileNotFoundException
	{
		List<Artist> temp_arr = new ArrayList<Artist>();
		BufferedInputStream stream = new BufferedInputStream(
                new FileInputStream(filename));
		XMLDecoder d = new XMLDecoder (stream);
		try {
			temp_arr = (List<Artist>) d.readObject();
		} catch (Exception e)
		{
			System.out.println("Fail to read");
		}
		d.close();
		
		return temp_arr;
	}
	
}
