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
		int i = 1,s=0;
		
		if(ring.slowSize()<20){
			for(float f : ring.getSlow()){
				slowAverage+=f*(i++);
				s+=i;
			}
			slowAverage=slowAverage/s;
		}
		else{
			for(float f : ring.getSlow()){
				slowAverage+=f*(i++);
				s+=i;
			}
			slowAverage=slowAverage/s;
		}
	}
	
	private void  calculFast(){
		int i = 1,s=0;
		if(ring.fastSize()<5){
			for(float f : ring.getFast()){
				fastAverage+=f*(i++);
				s+=i;
			}
			fastAverage=fastAverage/s;
		}
		else{
			for(float f : ring.getFast()){
				fastAverage+=f*(i++);
				s+=i;
			}
			fastAverage=fastAverage/s;
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
