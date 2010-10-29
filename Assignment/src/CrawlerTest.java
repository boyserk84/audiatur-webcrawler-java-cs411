import static org.junit.Assert.*;

import java.io.IOException;

import junit.framework.TestCase;

import org.junit.Test;


public class CrawlerTest extends TestCase{

	NexTagCrawler robot;
	
	public void Setup() throws IOException
	{
		robot = new NexTagCrawler("digital cameras",2);
	}
	
	@Test
	public void testCrawler() throws IOException 
	{
		this.Setup();
	}
	

}
