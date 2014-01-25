package Controler;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import View.MainFrame;

public class NetworkService{

	private static NetworkService instance = null;
	
	private boolean listening = true;
	
	private PriceNetworkService priceNetService;
	private BookingNetworkService bookingNetService;
	
	private MainFrame mf = new MainFrame();
	
	public static NetworkService getInstance() throws UnknownHostException, IOException{
		if(instance == null)
			instance = new NetworkService();
		return instance;
	}
	
	private NetworkService() throws UnknownHostException, IOException{
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
		mf.addTableValue(sValue,tick);
	}
}