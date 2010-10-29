import java.util.ArrayList;
import java.util.List;


public class Genre {
	
	/**
	 * Name
	 */
	private String name;
	/**
	 * Description
	 */
	private String des;
	/**
	 * Sub genre
	 */
	private List<Genre> sub_g;
	/**
	 * Main genre
	 */
	private Genre parent_g;
	
	public Genre(String n, String d, Genre parent)
	{
		this.name = n;
		this.des = d;
		this.parent_g = parent;
		this.sub_g = new ArrayList<Genre>();
	}
	
	
	
	/**
	 * Checking if this is a main genre
	 * @return
	 */
	public Boolean isParent()
	{
		if (parent_g==null) return true;
		else return false;
	}
	
	/**
	 * return main genre
	 * @return
	 */
	public String main_genre()
	{
		if (!isParent())
		{
			return this.parent_g.name;
		} else {
			return "None";
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public List<Genre> getSub_g() {
		return sub_g;
	}

	public void addSub_g(Genre sub_g) {
		this.sub_g.add(sub_g);
	}

	public Genre getParent_g() {
		return parent_g;
	}

	public void setParent_g(Genre parent_g) {
		this.parent_g = parent_g;
	}

}
