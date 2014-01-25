package modele.Algo;

public class SMA {
	public static Action ring;
	public static float slowAverage;
	public static float fastAverage;
	public SMA(Action r){
		ring=r;
		slowAverage=0;
		fastAverage=0;
	}
	
	private static float calculSlow(){
		if(ring.slowSize()<=20){
			RingBuffer tab = ring.getSlow();
			for(int i=0; i<ring.slowSize();i++){
				slowAverage+=tab.get(i);
			}
			slowAverage=slowAverage/ring.slowSize();
		}
		else{
			slowAverage= slowAverage - (ring.getOldest()/20)+(ring.getCurrent()/20);
		}
		return slowAverage;
	}
	
	private static float calculFast(){
		if(ring.fastSize()<=5){
			RingBuffer tab =ring.getFast();
			for(int i=0; i<ring.fastSize();i++){
				fastAverage+=tab.get(i);
			}
			fastAverage=fastAverage/ring.fastSize();
		}
		else{
			fastAverage= fastAverage - (ring.getOldest()/5)+(ring.getCurrent()/5);
		}
		return fastAverage;
	}
}
