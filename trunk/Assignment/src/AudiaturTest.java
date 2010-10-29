import java.io.IOException;

import junit.framework.TestCase;



public class AudiaturTest extends TestCase{

	
	public void testAudiaturIndustrialMetal() throws IOException
	{
		Audiatur test = new Audiatur("industrial metal");
		test.printBands();
	}
}
