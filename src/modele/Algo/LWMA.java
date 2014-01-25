package modele.Algo;

public class LWMA {
	private Action ring;
	private float slowAverage;
	private float fastAverage;
	public LWMA(Action r){
		ring=r;
		slowAverage=0;
		fastAverage=0;
	}
	
	private void  calculSlow(){
		int i = 0;
		if(ring.slowSize()<=20){
			for(float f : ring.getSlow()){
				slowAverage+=f*(i++);
			}
			slowAverage=slowAverage/ring.slowSize();
		}
		else{
			for(float f : ring.getSlow()){
				slowAverage+=f*(i++);
			}
			slowAverage=slowAverage/20;
		}
	}
	
	private void  calculFast(){
		int i = 0;
		if(ring.slowSize()<=5){
			for(float f : ring.getFast()){
				fastAverage+=f*(i++);
			}
			fastAverage=fastAverage/ring.slowSize();
		}
		else{
			for(float f : ring.getFast()){
				fastAverage+=f*(i+1);
			}
			fastAverage=fastAverage/5;
		}
	}
	
	
	public void calcul(){
		this.calculFast();
		this.calculSlow();
	}
	

	public float getSlowAverage() {
		return slowAverage;
	}

	public float getFastAverage() {
		return fastAverage;
	}
}
