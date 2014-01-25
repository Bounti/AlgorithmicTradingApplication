package modele.Algo;

import java.io.IOException;
import java.util.Hashtable;
import java.util.LinkedList;

import org.apache.commons.collections4.queue.CircularFifoQueue;

import View.Line;
import View.MainFrame;
import Controler.NetworkService;

public class Action extends Thread {
	private CircularFifoQueue<Float> fast ;
	private CircularFifoQueue<Float> slow;
	private int tick;
	private float currentPrice;
	private float oldestPrice;
	private NetworkService networkService;
	private MainFrame frame;
	private Hashtable <Integer, float[]> data;
	private LinkedList<Stock> queue;
	
	public Action() {
		this.fast =new CircularFifoQueue<Float>(5);
		this.slow =new CircularFifoQueue<Float>(20);
		currentPrice=0;
		frame = new MainFrame();
		data = new Hashtable<>();
		this.queue =  new LinkedList<Stock>();
		try{
			networkService = NetworkService.getInstance(this);
			networkService.initTransaction();
		} catch (IOException e1) {
			System.out.println("[Error]:"+e1.getMessage());
		}
		
	}
	
	public void add(int tick, float price){
		synchronized(this.queue){
			this.queue.add(new Stock(tick,price));
		}
	}
	
	public void update(Stock s){
		this.tick=s.getTick();
		currentPrice=s.getPrice();
		this.oldestPrice=this.fast.element();
		this.fast.add(currentPrice);
		this.slow.add(currentPrice);
	}
	
	public float getCurrent() {
		return currentPrice;
	}

	public float getOldest() {
		return oldestPrice;
	}

	public int fastSize(){		
		return fast.size();
	}
	
	public int slowSize(){
		return slow.size();
	}
	
	public CircularFifoQueue<Float> getFast() {
		return fast;
	}

	public CircularFifoQueue<Float> getSlow() {
		return slow;
	}

	public int getTick(){
		return tick;
	}
	
	public void run(){
		Stock s;
		float[] average_old =new float[8];
		float[] average_current =null;
		float sma_ecart_old, sma_ecart_current, tma_ecart_current, tma_ecart_old,lwma_ecart_old, lwma_ecart_current;
		SMA sma = new SMA(this);
		TMA tma = new TMA(this);
		LWMA lwma = new LWMA(this);
		
		while(true){
			synchronized(this.queue){
				s= this.queue.pollFirst();
			}
			this.update(s);
			sma.calcul();
			tma.calcul(sma.getFastAverage(), sma.getSlowAverage());
			lwma.calcul();
			average_current[0]=sma.getSlowAverage();
			average_current[1]=sma.getFastAverage();
			average_current[2]=tma.getSlowAverage();
			average_current[3]=tma.getFastAverage();
			average_current[2]=lwma.getSlowAverage();
			average_current[3]=lwma.getFastAverage();
			if(average_old==null){
				average_old=new float[8];
				
			}
			else{
				sma_ecart_old=average_old[1]-average_old[0];
				sma_ecart_current=average_current[1]-average_current[0];
				tma_ecart_old=average_old[3]-average_old[2];
				tma_ecart_current=average_current[3]-average_current[2];
				lwma_ecart_old=average_old[5]-average_old[4];
				lwma_ecart_current=average_current[5]-average_current[4];
				
				frame.add(Line.SMA20,average_current[0],s.getTick());
				frame.add(Line.SMA5,average_current[1],s.getTick());
				frame.add(Line.TMA20,average_current[2],s.getTick());
				frame.add(Line.TMA5,average_current[3],s.getTick());
				frame.add(Line.LWMA20,average_current[4],s.getTick());
				frame.add(Line.LWMA5,average_current[5],s.getTick());
				
				if((sma_ecart_old < 0 && sma_ecart_current > 0 ) ){
					networkService.sendBuyOrder();
				}
				else if((tma_ecart_old < 0 && tma_ecart_current > 0 )){
					networkService.sendBuyOrder();
				}
				else if((lwma_ecart_old < 0 && lwma_ecart_current > 0 )){
					networkService.sendBuyOrder();
				}
				else if((sma_ecart_old > 0 && sma_ecart_current < 0 )){
					networkService.sendSellOrder();
				}
				else if(tma_ecart_old > 0 && tma_ecart_current < 0 ){
					networkService.sendSellOrder();
				}
				else if(lwma_ecart_old > 0 && lwma_ecart_current < 0 ){
					networkService.sendSellOrder();
				}
			}
		}
		
	}
}
