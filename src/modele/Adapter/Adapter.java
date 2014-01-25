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
import java.util.Set;

import com.google.gson.stream.JsonWriter;

public class Adapter {
	private JsonWriter jw;
	private StringWriter sw;
	private BufferedWriter buf;
	private Path file;
	public Adapter (Path filePath){
		
		
		try {
			if(!Files.exists(filePath)){
				file=Files.createFile(filePath);
			}
			else
				file=filePath;
			buf=Files.newBufferedWriter(file, Charset.defaultCharset(), StandardOpenOption.WRITE ,StandardOpenOption.TRUNCATE_EXISTING);
			jw= new JsonWriter( buf);
			jw.beginObject();
			jw.name("team").value("coqAuRicard");
			jw.name("transaction").beginArray();
			
		} catch (IOException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
		
	}
	
	public void addTransaction(int time,String type, int price, String strategy){
		try {
			jw.beginObject();
			jw.name("time").value(time);
			jw.name("type").value(type);
			jw.name("price").value(price);
			jw.name("strategy").value(strategy);
			jw.endObject();

		} catch (IOException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
	}
	
	public void endFile(){
		try {
			jw.endArray();
			jw.endObject();
			jw.close();
		} catch (IOException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
		
	}
}
