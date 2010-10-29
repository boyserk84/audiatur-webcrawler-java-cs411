import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Test;

/**
 * Testing main program (output to console) (just for reference)
 * @author nate
 *
 */
public class mainTest extends TestCase{

	@Test
	public void testMainNoinput()
	{
		String[] str = new String[1];
		str[0] = "";
		main.main(str);
	}
	
	@Test
	/**
	 * Should get "Error: At least one argument required!"
	 */
	public void testMainNoinput1()
	{
		String[] str = null;
		assertNull(str);
		main.main(str);
	}
	
	@Test
	public void testMainWrongInput()
	{
		String[] str = new String[2];
		str[0] = "Digital Cameras";
		str[1] = "a";
		main.main(str);
	}
	
	@Test
	public void testMainWrongInput2()
	{
		String[] str = new String[2];
		str[0] = "Digital Cameras";
		str[1] = "-1";
		main.main(str);
	}
	
	@Test
	public void testMainWrongInput3()
	{
		String[] str = new String[2];
		str[0] = "1";
		str[1] = "2";
		main.main(str);
	}
	
	@Test
	public void testMainWeirdInput4()
	{
		String[] str = new String[2];
		str[0] = "abazldjtewtehk";
		str[1] = "2";
		main.main(str);
	}

	@Test
	public void testMain1() {
		String[] str = new String[1];
		str[0] = "DigitalCameras";
		main.main(str);
	}
	
	@Test
	public void testMain2() {
		String[] str = new String[2];
		str[0] = "Digital";
		str[1] = "Cameras";
		main.main(str);
	}
	
	@Test
	public void testMain3() {
		String[] str = new String[2];
		str[0] = "Baby";
		str[1] = "Strollers";
		main.main(str);
	}
	
	@Test
	public void testMain4() {
		String[] str = new String[1];
		str[0] = "Computer";
		main.main(str);
	}
	
	@Test
	public void testMain5(){
		String[] str = new String[3];
		str[0] = "Digital";
		str[1] = "Cameras";
		str[2] = "1";
		main.main(str);
	}
	
	@Test
	public void testMain6()
	{
		String[] str = new String[2];
		str[0] = "Computer";
		str[1] = "2";
		main.main(str);
	}
	
	@Test
	public void testMain7()
	{
		String[] str = new String[3];
		str[0] = "baby";
		str[1] = "strollers";
		str[2] = "3";
		main.main(str);
	}
	

	

}
