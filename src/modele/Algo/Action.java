package modele.Algo;

public class Action {
	private RingBuffer fast ;
	private RingBuffer slow;
	private float current;
	private float oldest;
	
	public Action() {
		this.fast =new RingBuffer(5);
		this.slow =new RingBuffer(20);
		current=0;
	}
	
	public void add(int i){
		current=i;
		this.oldest=this.fast.add(i);
		this.slow.add(i);
	}
	
	
	public float getCurrent() {
		return current;
	}

	public float getOldest() {
		return oldest;
	}

	public int fastSize(){		
		return fast.size();
	}
	
	public int slowSize(){
		return slow.size();
	}
	
	public RingBuffer getFast() {
		return fast;
	}

	public RingBuffer getSlow() {
		return slow;
	}


}
