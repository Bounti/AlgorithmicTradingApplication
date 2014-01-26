package modele.Adapter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.text.DecimalFormat;
import java.util.Set;

import com.google.gson.stream.JsonWriter;

public class Adapter extends Thread{

	private int time;
	private String type;
	private float price;
	private String strategy;

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getStrategy() {
		return strategy;
	}

	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}

	private JsonWriter jw;
	private StringWriter sw;
	private BufferedWriter buf;
	private Path file;

	public Adapter (Path filePath) {
		file = filePath;
	}

	public void run()
	{
		addTransaction();
	}

	public void addTransaction(){
		boolean isset =false;
		try {
			if(!Files.exists(file))
			{
				isset =true;
				file = Files.createFile(file);
				buf=Files.newBufferedWriter(file, Charset.defaultCharset() ,StandardOpenOption.APPEND);
				jw= new JsonWriter( buf);
				jw.beginObject();
				jw.name("team").value("coqAuRicard");
				jw.name("transaction").beginArray();
			}
			else
			{
				buf=Files.newBufferedWriter(file, Charset.defaultCharset() ,StandardOpenOption.APPEND);
				jw= new JsonWriter( buf);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			jw.beginObject();
			jw.name("time").value(time);
			jw.name("type").value(type);
			jw.name("price").value(price);
			jw.name("strategy").value(strategy);
			jw.endObject();

			if( isset )
			{
				jw.endArray();
				jw.endObject();
			}
			jw.close();
		} catch (IOException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}

	}
}
