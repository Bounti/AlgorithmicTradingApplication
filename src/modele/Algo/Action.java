package modele.Algo;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.LinkedList;

import modele.Adapter.Adapter;

import org.apache.commons.collections4.queue.CircularFifoQueue;

import View.Line;
import View.MainFrame;
import Controler.NetworkService;

public class Action extends Thread {
	
	private static boolean listen = true;
	private Adapter adapter;
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
		if(this.fast.isEmpty()){
			this.oldestPrice=currentPrice;
		}
		else
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
	
	public static void close(){
		listen = false;
	}

	public void run(){
		Stock s;
		float[] average_old =null;
		float[] average_current =new float[6];
		float sma_ecart_old, sma_ecart_current, tma_ecart_current, tma_ecart_old,lwma_ecart_old, lwma_ecart_current;
		SMA sma = new SMA(this);
		TMA tma = new TMA(this);
		LWMA lwma = new LWMA(this);

		
		while(listen){
			synchronized(this.queue){
				if(!this.queue.isEmpty()){
					s= this.queue.pollFirst();
					this.update(s);
					sma.calcul();
					tma.calcul(sma.getFastAverage(), sma.getSlowAverage());
					lwma.calcul();
					average_current[0]=sma.getSlowAverage();
					average_current[1]=sma.getFastAverage();
					average_current[2]=tma.getSlowAverage();
					average_current[3]=tma.getFastAverage();
					average_current[4]=lwma.getSlowAverage();
					average_current[5]=lwma.getFastAverage();
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
						System.out.println();
						System.out.println("Price "+s.getPrice()+" 5 : "+average_current[1]+" 20 : "+average_current[0]);
						System.out.println("5 : "+average_current[3]+" 20 : "+average_current[2]);
						System.out.println("5 : "+average_current[5]+" 20 : "+average_current[4]);
						System.out.println();
						frame.add(Line.SMA20,average_current[0],s.getTick());
						frame.add(Line.SMA5,average_current[1],s.getTick());
						frame.add(Line.TMA20,average_current[2],s.getTick());
						frame.add(Line.TMA5,average_current[3],s.getTick());
						frame.add(Line.LWMA20,average_current[4],s.getTick());
						frame.add(Line.LWMA5,average_current[5],s.getTick());

						if((sma_ecart_old < 0 && sma_ecart_current > 0 ) ){
							setAsynchTaskToAdapter(s.getPrice(),"buy",s.getTick(),"SMA");
							
							networkService.sendBuyOrder();
							frame.addTableValue(s.getPrice(), this.networkService.getLastStock().getPrice());
							System.out.println();
							System.out.println("buy sma old : "+sma_ecart_old+" current : "+sma_ecart_current);
							System.out.println();
						}
						else if((tma_ecart_old < 0 && tma_ecart_current > 0 )){
							//System.out.println();
							networkService.sendBuyOrder();
							setAsynchTaskToAdapter(s.getPrice(),"buy",s.getTick(),"TMA");

							//	System.out.println("buy tma old : "+tma_ecart_old+" current : "+tma_ecart_current);
							//System.out.println();
						}
						else if((lwma_ecart_old < 0 && lwma_ecart_current > 0 )){
							networkService.sendBuyOrder();
							setAsynchTaskToAdapter(s.getPrice(),"buy",s.getTick(),"LWMA");

							//System.out.println();
							//System.out.println("buy lwma old : "+lwma_ecart_old+" current : "+lwma_ecart_current);
							//System.out.println();
						}
						else if((sma_ecart_old > 0 && sma_ecart_current < 0 )){
							setAsynchTaskToAdapter(s.getPrice(),"sell",s.getTick(),"SMA");

							networkService.sendSellOrder();
							System.out.println();
							System.out.println("sell sma old : "+sma_ecart_old+" current : "+sma_ecart_current);
							System.out.println();
						}
						else if(tma_ecart_old > 0 && tma_ecart_current < 0 ){
							setAsynchTaskToAdapter(s.getPrice(),"sell",s.getTick(),"TMA");
							networkService.sendSellOrder();
						}
						else if(lwma_ecart_old > 0 && lwma_ecart_current < 0 ){
							setAsynchTaskToAdapter(s.getPrice(),"sell",s.getTick(),"LWMA");
							networkService.sendSellOrder();
						}
						
						average_old[0]=average_current[0];
						average_old[1]=average_current[1];
						average_old[2]=average_current[2];
						average_old[3]=average_current[3];
						average_old[4]=average_current[4];
						average_old[5]=average_current[5];
					}
				}

			}
		}
	}
	
	public void setAsynchTaskToAdapter(float price, String type, int time,String strategy){
		adapter = new Adapter(Paths.get("./output.json"));

		adapter.setPrice(price);
		adapter.setStrategy(strategy);
		adapter.setTime(time);
		adapter.setType(type);
		
		adapter.start();
	}
}
