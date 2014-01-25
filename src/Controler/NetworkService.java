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
		try {
			bookingNetService.sendSellOrder();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendBuyOrder(){
		try {
			bookingNetService.sendBuyOrder();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void refreshValue(int tick,float price) {
		action.add(tick,price);
	}
}