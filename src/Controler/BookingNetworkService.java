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
	private int port = 3001;
	private String address = "localhost";
	private boolean listening = true;
	
	private DataOutputStream out;
	private DataInputStream in;

	public void sendSellOrder() throws IOException {
		System.out.println("=========SSS=============");
		/*socket = new Socket(this.address,this.port);

		out = new DataOutputStream(socket.getOutputStream());
		in = new DataInputStream(socket.getInputStream());

		out.writeBytes("S\n");
		String str = in.readLine();

		System.out.println(str);
		socket.close();*/
	}

	public void sendBuyOrder() throws IOException {
		System.out.println("=====BBB=================");

		/*socket = new Socket(this.address,this.port);

		out = new DataOutputStream(socket.getOutputStream());
		in = new DataInputStream(socket.getInputStream());

		out.writeBytes("B\n");
		String str = in.readLine();

		System.out.println(str);

		socket.close();*/
	}
}
