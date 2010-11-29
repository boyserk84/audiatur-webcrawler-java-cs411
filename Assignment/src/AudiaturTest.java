import java.io.IOException;

import org.junit.Test;

import junit.framework.TestCase;



public class AudiaturTest extends TestCase{

	
	public void testAudiaturIndustrialMetal() throws IOException
	{
		//Audiatur test = new Audiatur("industrial metal");
		//test.printBands();
	}
	
	@Test
	public void testInsertArtistTable() throws IOException
	{
		AmazonCrawler test = new AmazonCrawler();
		test.loadXML("audiatur_fix1.xml");
		//test.printOnlyArtists();
		DBStorage db_Test = new DBStorage();
		for (int i = 100; i < 123; i++) 
		{
			//db_Test.addRowtoArtistsTable(test.getArr_artist().get(i));
			//db_Test.addRowtoArtistGenreTable(test.getArr_artist().get(i));
			//db_Test.addRowstoAlbumsTable(test.getArr_artist().get(i));
			db_Test.addRowstoSongsTable(test.getArr_artist().get(i));
			
			/*
			for (int j = 0; j < test.getArtistIndexOf(i).getArr_albums().size(); j++) {
				System.out.println(test.getArtistIndexOf(i).getArr_albums().get(j).getAlbum_name() + ":"+ 
				test.getArtistIndexOf(i).getArr_albums().get(j).getRelease_date());
			}*/
			
		}
		//test.getArtistIndexOf(31).setDes("No Info Available");
/*		db_Test.addRowtoArtistsTable(test.getArtistIndexOf(40));
		db_Test.addRowtoArtistsTable(test.getArtistIndexOf(56));
		db_Test.addRowtoArtistsTable(test.getArtistIndexOf(84));
		db_Test.addRowtoArtistsTable(test.getArtistIndexOf(92));
		db_Test.addRowtoArtistsTable(test.getArtistIndexOf(94));
		db_Test.addRowtoArtistsTable(test.getArtistIndexOf(109));*/
		
	}
}
