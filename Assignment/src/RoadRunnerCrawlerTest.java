import java.io.IOException;

import junit.framework.TestCase;



public class RoadRunnerCrawlerTest extends TestCase{
	
	public void testRoadRunnerCrawler() throws IOException
	{
		RoadRunnerCrawler test = new RoadRunnerCrawler();
		//test.fetchEachArtist("airbourne", null);
		test.printOut();
		test.saveAsXML();
	}

}
