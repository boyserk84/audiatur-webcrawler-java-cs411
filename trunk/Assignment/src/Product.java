
/**
 * Product class object
 * This class will hold all information that has been fetched from the crawler.
 * @author nate kemavaha
 *
 */
public class Product {

	private String title;	// Title
	private String desc;	// Description
	private String vendor;	// Vendor/seller
	
	/**
	 * Default Constructor
	 */
	public Product()
	{
		this.title = null;
		this.desc = null;
		this.vendor = null;
	}
	
	/**
	 * Constructor
	 * @param title 	Title/Product name
	 * @param des		Description
	 * @param vendor	Vendor/seller
	 */
	public Product(String title, String des, String vendor)
	{
		this.title = title;
		this.desc = des;
		this.vendor = vendor;
	}
	
	/**
	 * Print a product's information to the screen
	 */
	public void printProduct()
	{
		System.out.println("Title: " + this.title );
		System.out.println("Description: " + this.desc);
		System.out.println("Vendor: " + this.vendor);
	}
	
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param set the product title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the description
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * @param set description
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * @return the vendor information
	 */
	public String getVendor() {
		return vendor;
	}
	/**
	 * @param set the vendor information
	 */
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
}

