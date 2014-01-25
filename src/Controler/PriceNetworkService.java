package Controler;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class PriceNetworkService extends Thread{

	private 
	
	private Socket socket;
	private int port;
	private String address;
	
	public PriceNetworkService() throws UnknownHostException, IOException{
		socket = new Socket(this.address,this.port);
	}
	
	public void initTransaction() throws IOException {
		String payload = "H";		
		socket.getOutputStream().write(payload.getBytes());
	}
	
	public void getPrice(){
		
	}
}
