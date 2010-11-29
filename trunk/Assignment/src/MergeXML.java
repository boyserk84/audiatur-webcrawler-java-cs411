import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * ISSUES:
 */

/**
 * MergeXML
 * 	- Merge all XML files into one XML file and sanitize ordering aka artist ID.
 * @author nkemav2
 *
 */
public class MergeXML extends AmazonCrawler implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> files;	// List of XML files
	//private List<Artist> temp_artists;
	
	
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
	}
	
	/**
	 * MergeXML for repairing format in the XML ONLY
	 * @param fix
	 * @throws FileNotFoundException
	 */
	public MergeXML(Boolean fix) throws FileNotFoundException
	{
		this.loadXML("audiatur_list.xml");
		
		// Fix Null Description
		for (int i = 0; i < this.arr_artist.size(); i++) {
			// fix null description
			if (this.arr_artist.get(i).getDes()==null)
			{
				this.arr_artist.get(i).setDes("No Info available");
			}
		}
		
		// Fix Year Found
		for (int i = 126; i < this.arr_artist.size(); i++) {
			//System.out.println(i + " : " + this.arr_artist.get(i).getName());
			findYearFound(this.arr_artist.get(i));
		}
		this.saveAsXML(this.arr_artist, "audiatur_fix1.xml");
	}
	
	/**
	 * Print artist and year founded
	 */
	public void printAllArtist_Year()
	{
		for (int i = 0; i < this.arr_artist.size(); i++) 
		{
			System.out.println(this.arr_artist.get(i).getArtist_id() + ":" + this.arr_artist.get(i).getName() + ":" + this.arr_artist.get(i).getYear_found());
		}
	}
	
	/**
	 * Find year_found of the artist based on their album
	 * @param data
	 */
	private void findYearFound(Artist data)
	{
		String [] temp_str = new String[3];
		//String current_year = new String();
		int year = 0, min_year=0;
		String year_found = new String();
		
		
		for (int i = 0; i < data.getArr_albums().size(); i++) 
		{
			year_found = data.getArr_albums().get(i).getRelease_date();
			//System.out.println("Year Found: " + data.getArr_albums().get(i).getRelease_date());
			try {
				temp_str = data.getArr_albums().get(i).getRelease_date().split(" ");
				year = Integer.parseInt(temp_str[2]);
				if (i==0)
				{
					min_year = year;
				}
				
				//Fix Album release date Format into this formats
				data.getArr_albums().get(i).setRelease_date(temp_str[2] + "-" + temp_str[0] + "-" + temp_str[1]);
				//System.out.println(data.getArr_albums().get(i).getRelease_date());
				//System.out.println("New Format: " + year);
			} catch (Exception e)
			{
				// if empty (album release date is empty or none)
				if (year_found.equals("") || year_found.isEmpty())
				{
					// Fix album release date
					data.getArr_albums().get(i).setRelease_date("3000");
				}
				
				
				year = Integer.parseInt(data.getArr_albums().get(i).getRelease_date());
				if (i==0)
				{
					min_year = year;
				}
				//System.out.println("Prev Format: " + data.getArr_albums().get(i).getRelease_date());
			}
			
			// Comparison
			if (min_year > year){
				min_year = year;
			}
			
		}
		
		//System.out.println(min_year);
		data.setYear_found(min_year);
		
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
