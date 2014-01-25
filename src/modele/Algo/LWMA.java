package modele.Algo;

public class LWMA {
	public static Action ring;
	public static float slowAverage;
	public static float fastAverage;
	public LWMA(Action r){
		ring=r;
		slowAverage=0;
		fastAverage=0;
	}
	
	public void  calculSlow(){
		RingBuffer slowTab= ring.getSlow();
		int indice = slowTab.getIndice();
		if(ring.slowSize()<=20){
			for(int i=0; i<ring.slowSize();i++){
				slowAverage+=slowTab.get(i)*(i+1);
			}
			slowAverage=slowAverage/ring.slowSize();
		}
		else{
			for(int i=1;i<21;i++){
				slowAverage+=slowTab.get(indice)*(i+1);
				indice=(indice+1)%20;
			}
			slowAverage=slowAverage/20;
		}
	}
	
	public void  calculFast(){
		RingBuffer slowTab= ring.getSlow();
		int indice = slowTab.getIndice();
		if(ring.slowSize()<=5){
			for(int i=0; i<ring.slowSize();i++){
				fastAverage+=slowTab.get(i)*(i+1);
			}
			fastAverage=fastAverage/ring.slowSize();
		}
		else{
			for(int i=1;i<6;i++){
				fastAverage+=slowTab.get(indice)*(i+1);
				indice=(indice+1)%5;
			}
			fastAverage=fastAverage/5;
		}
	}
}
