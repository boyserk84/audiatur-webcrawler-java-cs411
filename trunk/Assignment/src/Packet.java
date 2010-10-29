

public class Packet {

	private String title;
	private String desc;
	private String vendor;
	
	/**
	 * Constructor
	 * @param title
	 * @param des
	 * @param vendor
	 */
	public void process(String title, String des, String vendor)
	{
		this.title = title;
		this.desc = des;
		this.vendor = vendor;
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
