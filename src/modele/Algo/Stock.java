package modele.Algo;


public class Stock {
	private int tick;
	private float price;
	
	public Stock(int tick, float price){
		this.price=price;
		this.tick=tick;
	}

	public int getTick() {
		return tick;
	}

	public float getPrice() {
		return price;
	}
}
