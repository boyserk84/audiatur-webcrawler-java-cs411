import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;



public class MergeXMLTest extends TestCase{
	
	@Test
	public void testMergeFix() throws FileNotFoundException
	{
		MergeXML test = new MergeXML(true);
		test.printAllArtist_Year();
	}
	
	
	
/*	@Test
	public void testMergeXML() throws FileNotFoundException
	{
		List<String> xml_files = new ArrayList<String>();
		xml_files.add("Roadrunner_data.xml");
		xml_files.add("contemporary_blues.xml");
		xml_files.add("Hiphop.xml");
		xml_files.add("General Pop.xml");
		xml_files.add("Metal.xml");
		xml_files.add("Classic_Rock.xml");
		xml_files.add("Funk.xml");
		xml_files.add("NeoSoul.xml");
		xml_files.add("Power Pop.xml");
		xml_files.add("Soft_Rock.xml");
		xml_files.add("Rap_Rock.xml");
		xml_files.add("Jamaican Ska.xml");
		xml_files.add("Dance Pop.xml");
		
		MergeXML test = new MergeXML(xml_files);
		test.printOnlyArtists();
		
		test.saveasXMLFile("audiatur_list.xml");
	}*/
}
