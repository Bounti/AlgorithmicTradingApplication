import java.io.IOException;
import java.net.UnknownHostException;

import Controler.NetworkService;


public class Application {

	public static void main(String[] argv ){
		try {
			NetworkService networkService = NetworkService.getInstance();
			networkService.start();
		} catch (IOException e1) {
			System.out.println("[Error]:"+e1.getMessage());
		}
		
	}
}
