package modele.Algo;


public class SMA {
	private Action ring;
	private float slowAverage;
	private float fastAverage;
	
	public SMA(Action r){
		ring=r;
		slowAverage=0;
		fastAverage=0;
	}
	
	private float calculSlow(){
		if(ring.slowSize()<= 20){
			
			for(float f : ring.getSlow()){
				slowAverage+=f;
			}
			slowAverage=slowAverage/ring.slowSize();
		}
		else{
			slowAverage= slowAverage - (ring.getOldest()/20)+(ring.getCurrent()/20);
		}
		return slowAverage;
	}
	
	public float getSlowAverage() {
		return slowAverage;
	}

	public float getFastAverage() {
		return fastAverage;
	}

	private float calculFast(){
		if(ring.fastSize()<=5){

			for(float i : ring.getFast()){
				fastAverage+=i;
			}
			fastAverage=fastAverage/ring.fastSize();
		}
		else{
			fastAverage= fastAverage - (ring.getOldest()/5)+(ring.getCurrent()/5);
		}
		return fastAverage;
	}
	
	public void calcul(){
		this.calculFast();
		this.calculSlow();
	}
}
