import static org.junit.Assert.*;

import java.io.IOException;

import junit.framework.TestCase;

import org.junit.Test;


public class AmazonCrawlerTest extends TestCase{

	@Test
	public void testAmazonCrawler() throws IOException 
	{
		AmazonCrawler test = new AmazonCrawler("General Pop");
		test.runCrawler();
		test.saveasXML();
		//test.loadXML("contemporary_blues.xml");
		test.printOut();
	}

}
