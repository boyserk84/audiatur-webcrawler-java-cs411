import java.io.IOException;

import junit.framework.TestCase;

import org.junit.Test;



public class wikibandsCrawlerTest extends TestCase{

	@Test
	/**
	 * Testing fetching death metal bands
	 */
	public void testDeath_Metal() throws IOException
	{
		wikibandsCrawler test = new wikibandsCrawler("death metal");
	}
	
	@Test
	/**
	 * Testing fetching industrial metal bands
	 */
	public void testInd_Metal() throws IOException
	{
		wikibandsCrawler test = new wikibandsCrawler("industrial metal");
	}
	
	@Test
	public void testArtist() throws IOException
	{
		wikibandsCrawler test = new wikibandsCrawler("");
		//test.fetchDataFromSubURL("http://music.napster.com/slipknot-music/bio/", new Artist("slipknot"));
		//test.fetchDataFromSubURL("http://music.napster.com/babab-music/bio/", new Artist("babab"));
	}
}
