package Controler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class PriceNetworkService extends Thread{
	
	private static int tick = 0;
	private Socket socket;
	private int port = 3000;
	private String address = "localhost";
	private boolean listening = true;
	
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
					tick++;
					sValue = "";
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
}
