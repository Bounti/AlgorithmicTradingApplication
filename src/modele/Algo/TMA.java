package modele.Algo;

import org.apache.commons.collections4.queue.CircularFifoQueue;

public class TMA {
	private CircularFifoQueue<Integer> fast;
	private CircularFifoQueue<Integer> slow;
	public static int slowAverage;
	public static int fastAverage;
	
	public TMA() {
		this.fast = new CircularFifoQueue<>(5);
		this.slow =  new CircularFifoQueue<>(20);
		slowAverage=0;
		fastAverage=0;

	}
	
	public void calculSlow(int SMASlow){
		if(slow.size()<=20){
			for(int i :slow){
				slowAverage+=i;
			}
			slowAverage=slowAverage/slow.size();
		}
		else{
			slowAverage= slowAverage - (slow.peek()/20)+(SMASlow/20);
			
		}
		slow.add(SMASlow);
	}
	
	public void calculFast(int SMAFast){
		if(slow.size()<=5){
			for(int i :slow){
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
