import java.io.IOException;

import junit.framework.TestCase;



public class RoadRunnerCrawlerTest extends TestCase{
	
	public void testRoadRunnerCrawler() throws IOException
	{
		RoadRunnerCrawler test = new RoadRunnerCrawler();
		//test.runCrawler();
		//test.fetchEachArtist("TheWombats", new Artist("TheWombats"));
		//test.fetchEachArtist("slipknot", new Artist("slipknot"));
		//test.fetchSongsFromAlbum("/newreleases/release.aspx?releaseID=398", null);
		//test.fetchSongsFromAlbum("/newreleases/release.aspx?releaseID=332", null);
		//test.printOut();
		//test.saveAsXML();
		//DBStorage db_store = new DBStorage(test.getArr_artist());
		//test.saveToDB();
	}
	
	public void testLoadXML() throws Exception
	{
		RoadRunnerCrawler test = new RoadRunnerCrawler();
		test.loadXML("Roadrunner_data.xml");
		test.printListArtist();
		//test.printListAlbums();
		//test.printListSongs();
	}
	


}
