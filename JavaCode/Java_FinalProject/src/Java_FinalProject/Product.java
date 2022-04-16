package Java_FinalProject;

public class Product implements ProductInterface {
	private String name;
	private int cost;
	public Product(String name, String cost) {
		this.name = name;
		this.cost = Integer.parseInt(cost);
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public int getCost() {
		return cost;
	}
}//end class
