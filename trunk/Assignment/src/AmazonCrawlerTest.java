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
		URL_list.add("http://www.amazon.com/Pop-Music/b/ref=bw_ab_37_0?ie=UTF8&node=67172&pf_rd_p=236813301&pf_rd_s=browse&pf_rd_t=101&pf_rd_i=37&pf_rd_m=ATVPDKIKX0DER&pf_rd_r=1V1FRX7HM8GZ584VWW86#/ref=sr_pg_1?rh=n%3A5174%2Cn%3A!301668%2Cn%3A37%2Cn%3A67172&ie=UTF8&qid=1290145124");
		URL_list.add("http://www.amazon.com/Pop-Music/b/ref=bw_ab_37_0?ie=UTF8&node=67172&pf_rd_p=236813301&pf_rd_s=browse&pf_rd_t=101&pf_rd_i=37&pf_rd_m=ATVPDKIKX0DER&pf_rd_r=1V1FRX7HM8GZ584VWW86#/ref=sr_pg_2?rh=n%3A5174%2Cn%3A!301668%2Cn%3A37%2Cn%3A67172&page=2&ie=UTF8&qid=1290144579");
		URL_list.add("http://www.amazon.com/Pop-Music/b/ref=bw_ab_37_0?ie=UTF8&node=67172&pf_rd_p=236813301&pf_rd_s=browse&pf_rd_t=101&pf_rd_i=37&pf_rd_m=ATVPDKIKX0DER&pf_rd_r=1V1FRX7HM8GZ584VWW86#/ref=sr_pg_3?rh=n%3A5174%2Cn%3A!301668%2Cn%3A37%2Cn%3A67172&page=3&ie=UTF8&qid=1290144550");
		URL_list.add("http://www.amazon.com/Pop-Music/b/ref=bw_ab_37_0?ie=UTF8&node=67172&pf_rd_p=236813301&pf_rd_s=browse&pf_rd_t=101&pf_rd_i=37&pf_rd_m=ATVPDKIKX0DER&pf_rd_r=1V1FRX7HM8GZ584VWW86#/ref=sr_pg_4?rh=n%3A5174%2Cn%3A!301668%2Cn%3A37%2Cn%3A67172&page=4&ie=UTF8&qid=1290144566");
		AmazonCrawler test = null;
		
		for (int i = 0; i < URL_list.size(); ++i) 
		{
			//System.out.println(URL_list.get(i));
			test = new AmazonCrawler("General Pop",URL_list.get(i));

			test.loadXML("General Pop.xml");
			test.printOnlyArtists();
			test.runCrawlerwithURLoption();
			test.saveasXML();

			//test.loadXML("contemporary_blues.xml");
		}
		test.printOut();
	}

}
