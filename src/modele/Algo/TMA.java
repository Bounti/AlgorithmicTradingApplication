package modele.Algo;

import org.apache.commons.collections4.queue.CircularFifoQueue;

public class TMA {
	private CircularFifoQueue<Float> fast;
	private CircularFifoQueue<Float> slow;
	public static float slowAverage;
	public static float fastAverage;
	
	public TMA() {
		this.fast = new CircularFifoQueue<>(5);
		this.slow =  new CircularFifoQueue<>(20);
		slowAverage=0;
		fastAverage=0;

	}
	
	public void calculSlow(float SMASlow){
		if(slow.size()<=20){
			for(float i :slow){
				slowAverage+=i;
			}
			slowAverage=slowAverage/slow.size();
		}
		else{
			slowAverage= slowAverage - (slow.peek()/20)+(SMASlow/20);
			
		}
		slow.add(SMASlow);
	}
	
	public void calculFast(float SMAFast){
		if(slow.size()<=5){
			for(float i :slow){
				fastAverage+=i;
			}
			fastAverage=fastAverage/slow.size();
		}
		else{
			fastAverage= fastAverage - (fast.peek()/5)+(SMAFast/5);			
		}
		fast.add(SMAFast);
	}
}
