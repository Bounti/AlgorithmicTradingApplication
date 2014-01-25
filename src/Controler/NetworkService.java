package Controler;

import modele.Algo.Action;
import java.io.IOException;
import java.net.UnknownHostException;

public class NetworkService{

	private static NetworkService instance = null;
	
	private PriceNetworkService priceNetService;
	private BookingNetworkService bookingNetService;
	private Action action;
	
	public static NetworkService getInstance(Action action) throws UnknownHostException, IOException{
		if(instance == null)
			instance = new NetworkService(action);
		return instance;
	}
	
	private NetworkService(Action action) throws UnknownHostException, IOException{
		this.action = action;
		priceNetService = new PriceNetworkService(this);
		bookingNetService = new BookingNetworkService();
	}
	
	public void initTransaction() throws IOException{
		priceNetService.start();
	}
	
	public void sendSellOrder(){
		
	}

	public void sendBuyOrder(){
		
	}

	public void refreshValue(int tick,String sValue) {
		action.add(Float.valueOf(sValue));
	}
}