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
	
	private void calculSlow(){
		
		if(ring.slowSize()< 20){
			
			for(float f : ring.getSlow()){
				slowAverage+=f;
			}
			slowAverage=slowAverage/ring.slowSize();
		}
		else{
			float old=slowAverage;
			slowAverage= old - (ring.getOldest()/20)+(ring.getCurrent()/20);
		}
	}
	
	public float getSlowAverage() {
		return slowAverage;
	}

	public float getFastAverage() {
		return fastAverage;
	}

	private void calculFast(){
		if(ring.fastSize()<5){

			for(float i : ring.getFast()){
				fastAverage+=i;
			}
			fastAverage=fastAverage/ring.fastSize();
		}
		else{
			float old=slowAverage;
			fastAverage= old - (ring.getOldest()/5)+(ring.getCurrent()/5);
		}
	}
	
	public void calcul(){
		this.calculFast();
		this.calculSlow();
	}
}
