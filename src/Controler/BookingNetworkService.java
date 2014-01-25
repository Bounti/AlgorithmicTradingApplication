package Controler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class BookingNetworkService extends Thread{
	
	private Socket socket;
	private int port = 3000;
	private String address = "localhost";
	private boolean listening = true;
	
	private DataOutputStream out;
	private DataInputStream in;

	private byte[] buffer;
	
	public BookingNetworkService() throws UnknownHostException, IOException{
		socket = new Socket(this.address,this.port);

		out = new DataOutputStream(socket.getOutputStream());
		in = new DataInputStream(socket.getInputStream());

		buffer = new byte[4096];
	}

	public void sendSellOrder() throws IOException {
		out.writeBytes("S\n");
	}

	public void sendBuyOrder() throws IOException {
		out.writeBytes("B\n");
	}
}
