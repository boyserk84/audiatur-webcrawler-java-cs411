import java.io.IOException;
import java.lang.String;

/**
 * This is where a main program takes place.
 * @author nate
 *
 */
public class main {
	/**
	 * Representing command line option for return only a number of matches
	 */
	static final int RESULT_ONLY = -1; 
	static int page;
	
	/**
	 * Main program
	 * @param args command line string
	 */
	public static void main(String[] args) {
		String tag = "";
		NexTagCrawler robot = null;
		page = RESULT_ONLY;
		
		if (checkEmptyInput(args))
		{
			System.out.println("Error: At least one argument required!");
			return;
		}
		
		if (!checkErrorInput(args))
		{
			tag = extractCommand(args);
		} else return;

		System.out.println("\nKeyword: " + tag + "\n");

		robot = processWork(tag,page);	// process work
		
		outputToConsole(tag, robot);	// print results

	}

	/**
	 * Printing output to the console
	 * @param tag	Keyword
	 * @param robot	Crawler
	 */
	private static void outputToConsole(String tag, NexTagCrawler robot) 
	{
		robot.printResult();
		if (page > RESULT_ONLY)
			System.out.println("\nThere are " + robot.getPage_query() + " matches for "+ tag +" from the page " + page + ".");
		System.out.println("There are " + robot.getTotal_query() + " matches for "+ tag +".");
	}

	
	/**
	 * Checking for errors in the command input
	 * @param args an array of string command line
	 * @return True if error is detected. Otherwise, False is returned.
	 */
	private static boolean checkErrorInput(String[] args) 
	{
		try {
			// with specifying page
			page = Integer.parseInt(args[args.length-1]);
			if (page < 1)
			{
				System.out.println("Error: Please indicate page number greater than 0!");
				return true;
			}
		} catch (Exception e) {
			// without specifying page
			if (args[args.length-1].length()==1)
			{
				System.out.println("Error: Please indicate page number!");
				return true;
			}
		}
		return false;
	}

	/**
	 * checking if a command line is empty
	 * @param args an array of string command inputs
	 * @return True if there is an empty input. Otherwise, false is returned.
	 */
	private static boolean checkEmptyInput(String[] args) 
	{
		return args ==null || args.length == 0 || args[0].equals("");
	}
	
	/**
	 * Giving a crawler keyword and page to process
	 * @param tag Search keyword
	 * @param page page number
	 * @return Crawler object
	 */
	private static NexTagCrawler processWork(String tag, int page)
	{
		try {
			return new NexTagCrawler(tag,page);
		} catch (IOException e) {
			System.out.println("Error: Crawler is unable to process works.");
			return null;
		}
	}
	

	/**
	 * Extract keyword from the command line
	 * @param com_str an array of string from the command line
	 * @return a string of keyword
	 */
	private static String extractCommand(String[] com_str) {
		String new_str = "";
		int max_length = com_str.length-1;
		
		// Find length of a string without numeric value
		try {
			Integer.parseInt(com_str[com_str.length-1]);
		} catch (Exception e){
			max_length = com_str.length;
		}
		// Combine array of keywords into a string
		for (int i = 0; i < max_length; i++) 
		{
			new_str = new_str.concat(com_str[i]);
			if (i != max_length - 1)
				new_str = new_str.concat(" ");
		}
		return new_str;
	}
	

}
