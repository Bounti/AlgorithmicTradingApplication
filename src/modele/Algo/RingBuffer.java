package modele.Algo;


public class RingBuffer{
	private float[] tab ;

	private int indice;
	
	public RingBuffer(int i) {
		this.tab = new float[i];
	}
	
	public float add(int i){
		float old;
		if(indice==0)
			old=i;
		else
			old=this.tab[indice];
		this.tab[indice] =i;
		indice = (indice+1)%tab.length;
		return old;
	}
	
	
	public float get(int i){
		return tab[i];
	}
	
	public int size(){
		return indice;
	}
	
	public int getIndice(){
		return indice;
	}

	public float[] getTab() {
		return tab;
	}

}
