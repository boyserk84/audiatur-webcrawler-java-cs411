import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;


/**
 * MergeXML
 * 	- Merge all XML files into one XML file and sanitize ordering aka artist ID.
 * @author nkemav2
 *
 */
public class MergeXML extends AmazonCrawler{

	private List<String> files;	// List of XML files
	
	/**
	 * Constructor
	 * @param f array/list of xml files
	 * @throws FileNotFoundException 
	 */
	public MergeXML(List<String> f) throws FileNotFoundException
	{
		files = f;
		this.loadXML(files.get(0));
		
	}
	
	/**
	 * Loading subsequent XML files Only
	 * @param filename
	 * @throws FileNotFoundException
	 */
	private void loadXMLwithMergeOption(String filename) throws FileNotFoundException
	{
		BufferedInputStream stream = new BufferedInputStream(
                new FileInputStream(filename));
		XMLDecoder d = new XMLDecoder (stream);
		try {
			this.arr_artist = (List<Artist>) d.readObject();
		} catch (Exception e)
		{
			System.out.println("Fail to read");
		}
		d.close();
	}
	
	
	public void runMerge()
	{
		for (int i =0; i < files.size(); ++i)
		{
			files.get(i);
		}
	}
}
