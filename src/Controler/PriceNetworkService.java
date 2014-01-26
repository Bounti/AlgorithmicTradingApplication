package Controler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import modele.Algo.Action;
import modele.Algo.Stock;

public class PriceNetworkService extends Thread{
	
	private static int tick = 0;
	private Socket socket;
	private int port = 3000;
	private String address = "localhost";
	private boolean listening = true;
	private Stock lastStock = new Stock(0,0);

	private DataOutputStream out;
	private DataInputStream in;

	private byte[] buffer;
	
	private NetworkService netService;

	public PriceNetworkService(NetworkService netService) throws UnknownHostException, IOException{
		this.netService = netService;

		socket = new Socket(this.address,this.port);

		out = new DataOutputStream(socket.getOutputStream());
		in = new DataInputStream(socket.getInputStream());

		buffer = new byte[4096];
	}
	
	public void initTransaction() throws IOException {
		out.writeBytes("H\n");
	}

	public void run(){
		try {
			System.out.println("Lancement connection");
			initTransaction();
			String sValue = "";
			byte[] b = new byte[1];
			while(listening)
			{
				in.read(b);
				if( b[0] == '|')
				{
					//System.out.println("[INFO]"+sValue);
					netService.refreshValue(tick,Float.valueOf(sValue));
					lastStock = new Stock(tick,Float.valueOf(sValue));
					tick++;
					sValue = "";
				}
				else if ( b[0] == 'C')
				{
					Action.close();
				}
				else
				{
					sValue += new String(b,"ISO-8859-1");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Stock getLastStock() {
		return lastStock;
	}
}
