package Controler;

import java.net.Socket;

public class NetworkService {

	private PriceNetworkService priceNetService;
	private BookingNetworkService bookingNetService;
	
	public NetworkService(){
		priceNetService = new PriceNetworkService();
		bookingNetService = new BookingNetworkService();
	}
	
}
