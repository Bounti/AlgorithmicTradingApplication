package View;

public enum Line {
	PRICE(0,"PRICE"),
	EMA5(1,"EMA5"),
	EMA20(2,"EMA20"),
	SMA5(3,"SMA5"),
	SMA20(4,"SMA20"), 
	TMA5(5,"TMA5"),
	TMA20(6,"TMA20"),
	LWMA5(7,"LWMA5"),
	LWMA20(8,"LWMA20");

	private int index;
	private String name;
	
	private Line(int index, String name){
		this.index = index;
		this.name = name;
	}

	public int getIndex() {
		return index;
	}
	
	public String getName() {
		return name;
	}
}
