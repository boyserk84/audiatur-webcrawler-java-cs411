import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;



public class MergeXMLTest extends TestCase{
	
	@Test
	public void testMergeXML() throws FileNotFoundException
	{
		List<String> xml_files = new ArrayList<String>();
		xml_files.add("Roadrunner_data.xml");
		xml_files.add("contemporary_blues.xml");
		
		MergeXML test = new MergeXML(xml_files);
		test.printOnlyArtists();
	}
}
