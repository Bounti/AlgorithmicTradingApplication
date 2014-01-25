package modele.Algo;


public class RingBuffer{
	private int[] tab ;

	private int indice;
	
	public RingBuffer(int i) {
		this.tab = new int[i];
	}
	
	public int add(int i){
		int old;
		if(indice==0)
			old=i;
		else
			old=this.tab[indice];
		this.tab[indice] =i;
		indice = (indice+1)%tab.length;
		return old;
	}
	
	
	public int get(int i){
		return tab[i];
	}
	
	public int size(){
		return indice;
	}
	
	public int getIndice(){
		return indice;
	}

	public int[] getTab() {
		return tab;
	}

}
