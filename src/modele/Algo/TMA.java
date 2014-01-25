package modele.Algo;

import org.apache.commons.collections4.queue.CircularFifoQueue;

public class TMA {
	private CircularFifoQueue<Float> fast;
	private CircularFifoQueue<Float> slow;
	private float slowAverage;
	private float fastAverage;
	private Action ring;
	
	public TMA(Action r) {
		this.fast = new CircularFifoQueue<>(5);
		this.slow =  new CircularFifoQueue<>(20);
		slowAverage=0;
		fastAverage=0;
		ring=r;
	}
	
	private void calculSlow(float SMASlow){
		slow.add(SMASlow);
		if(slow.size()<=20){
			for(float i :slow){
				slowAverage+=i;
			}
			slowAverage=slowAverage/ring.getTick();
		}
		else{
			slowAverage= slowAverage - (slow.peek()/20)+(SMASlow/20);
			
		}
		slow.add(SMASlow);
	}
	
	private void calculFast(float SMAFast){
		fast.add(SMAFast);
		if(slow.size()<=5){
			for(float i :slow){
				fastAverage+=i;
			}
			fastAverage=fastAverage/ring.getTick();
		}
		else{
			fastAverage= fastAverage - (fast.peek()/5)+(SMAFast/5);			
		}
		fast.add(SMAFast);
	}
	
	public float getSlowAverage() {
		return slowAverage;
	}

	public float getFastAverage() {
		return fastAverage;
	}

	public void calcul(float SMAFast,float SMASlow){
		this.calculFast(SMAFast);
		this.calculSlow(SMASlow);
	}
}
