package Controler;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetworkService extends Thread{

	private static NetworkService instance = null;
	
	private boolean listening = true;
	
	private PriceNetworkService priceNetService;
	private BookingNetworkService bookingNetService;
	
	public static NetworkService getInstance() throws UnknownHostException, IOException{
		if(instance == null)
			instance = new NetworkService();
		return instance;
	}
	
	private NetworkService() throws UnknownHostException, IOException{
		priceNetService = new PriceNetworkService();
		bookingNetService = new BookingNetworkService();
	}
	
	private void initTransaction() throws IOException{
		priceNetService.initTransaction();
	}
	
	public void sendSellOrder(){
		
	}

	public void sendBuyOrder(){
		
	}

	public void run() {
		try {
			initTransaction();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		while(listening)
		{
			System.out.println("");
		}
	}
}