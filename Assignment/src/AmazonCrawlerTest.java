import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;


public class AmazonCrawlerTest extends TestCase{

	@Test
	public void testAmazonCrawler() throws IOException 
	{
		List<String> URL_list = new ArrayList<String>();
		URL_list.add("http://www.amazon.com/Christian-Rap-Music/b/ref=bw_ab_38_4?ie=UTF8&node=63939&pf_rd_p=236927301&pf_rd_s=browse&pf_rd_t=101&pf_rd_i=38&pf_rd_m=ATVPDKIKX0DER&pf_rd_r=1K8E8DYREHNMDQCZMEDW");
		URL_list.add("http://www.amazon.com/Gangsta-Hardcore-Rap-Hip-Hop-Music/b/ref=bw_ab_38_7?ie=UTF8&node=67195&pf_rd_p=236927301&pf_rd_s=browse&pf_rd_t=101&pf_rd_i=38&pf_rd_m=ATVPDKIKX0DER&pf_rd_r=1K8E8DYREHNMDQCZMEDW");
		//URL_list.add("http://www.amazon.com/Pop-Music/b/ref=bw_ab_37_0?ie=UTF8&node=67172&pf_rd_p=236813301&pf_rd_s=browse&pf_rd_t=101&pf_rd_i=37&pf_rd_m=ATVPDKIKX0DER&pf_rd_r=1V1FRX7HM8GZ584VWW86#/ref=sr_pg_4?rh=n%3A5174%2Cn%3A!301668%2Cn%3A37%2Cn%3A67172&page=4&ie=UTF8&qid=1290144566");
		List<String> genre = new ArrayList<String>();
		genre.add("Christian Hiphop");
		genre.add("Gangsta Hardcore");
		
		AmazonCrawler test = null;
		
		for (int i = 0; i < URL_list.size(); ++i) 
		{
			//System.out.println(URL_list.get(i));
			test = new AmazonCrawler(genre.get(i),URL_list.get(i));

			test.loadXML("Hiphop.xml");
			test.printOnlyArtists();
			test.runCrawlerwithURLoption();
			test.saveasXML();

			//test.loadXML("contemporary_blues.xml");
		}
		test.printOut();
	}
	
/*	@Test
	public void testAmazonCrawler02() throws IOException
	{
		AmazonCrawler test = new AmazonCrawler("General Pop");
		test.loadXML("General Pop.xml");
		test.printOnlyArtists();
	}*/

}
