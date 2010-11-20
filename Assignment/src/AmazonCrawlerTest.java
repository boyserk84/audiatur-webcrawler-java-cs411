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
		//URL_list.add("http://www.amazon.com/gp/search/ref=sr_nr_n_0?rh=n%3A5174%2Cn%3A!301668%2Cn%3A67207%2Cn%3A67208&bbn=67207&ie=UTF8&qid=1290224039&rnid=67207");
		URL_list.add("http://www.amazon.com/gp/search/ref=sr_nr_n_6?rh=n%3A5174%2Cn%3A!301668%2Cn%3A67207%2Cn%3A468416&bbn=67207&ie=UTF8&qid=1290224106&rnid=67207");
		//URL_list.add("http://www.amazon.com/Classic-Rock-Glam/b/ref=bw_ab_67204_7?ie=UTF8&node=467954&pf_rd_p=236932801&pf_rd_s=browse&pf_rd_t=101&pf_rd_i=67204&pf_rd_m=ATVPDKIKX0DER&pf_rd_r=005HHW14PYTGNZ3J8X4N");
		//URL_list.add("http://www.amazon.com/Classic-Rock-Psychedelic-Rock/b/ref=bw_ab_67204_8?ie=UTF8&node=67220&pf_rd_p=236932801&pf_rd_s=browse&pf_rd_t=101&pf_rd_i=67204&pf_rd_m=ATVPDKIKX0DER&pf_rd_r=005HHW14PYTGNZ3J8X4N");
		//URL_list.add("http://www.amazon.com/Classic-Rock-Southern-Rock/b/ref=bw_ab_67204_9?ie=UTF8&node=67222&pf_rd_p=236932801&pf_rd_s=browse&pf_rd_t=101&pf_rd_i=67204&pf_rd_m=ATVPDKIKX0DER&pf_rd_r=005HHW14PYTGNZ3J8X4N");
		//URL_list.add("http://www.amazon.com/Soft-Rock-Pop-Music/b/ref=bw_ab_37_16?ie=UTF8&node=67177&pf_rd_p=236813301&pf_rd_s=browse&pf_rd_t=101&pf_rd_i=37&pf_rd_m=ATVPDKIKX0DER&pf_rd_r=07NDVM9280W01NYF6CCZ");
		//URL_list.add("http://www.amazon.com/Rap-Rock-Music/b/ref=bw_ab_40_13?ie=UTF8&node=408262&pf_rd_p=236929701&pf_rd_s=browse&pf_rd_t=101&pf_rd_i=40&pf_rd_m=ATVPDKIKX0DER&pf_rd_r=1D7ZCYK7FA9B1R4XQYWG#/ref=sr_pg_3?rh=n%3A5174%2Cn%3A!301668%2Cn%3A40%2Cn%3A408262&page=3&sort=salesrank&ie=UTF8&qid=1290223443");
		List<String> genre = new ArrayList<String>();
		//genre.add("Alternative Metal");
		genre.add("Trash");
		//genre.add("Glam");
		//genre.add("Psychedelic Rock");
		//genre.add("Soft Rock");
		//genre.add("Rap Rock");
		AmazonCrawler test = null;
		
		for (int i = 0; i < URL_list.size(); ++i) 
		{
			//System.out.println(URL_list.get(i));
			test = new AmazonCrawler(genre.get(i),URL_list.get(i));
			
			test.loadXML("Metal.xml");
			test.printOnlyArtists();
			test.runCrawlerwithURLoption();
			//test.saveasXMLFile("Metal.xml");
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
