package app1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;


public class FileService {
	
	String fileName;
	BufferedReader in;
	PrintWriter out;
	
	//public static volatile FileService instance;
	
	public FileService(String fname){
		this.fileName = fname;
	
		try {
			out = new PrintWriter(new FileWriter(fileName, true));
			in = new BufferedReader(new FileReader(fileName));
		} catch (Exception e) { e.printStackTrace();}
	}
	
		public /*synchronized*/ void write(String msg){
			
			synchronized(this) {
			
			Date date = new Date(System.currentTimeMillis());
			out.println("Date: " + date);
			out.println("Message: " + msg);
			out.flush();
			
			}
		}
		
		public /*synchronized*/ String read() throws IOException{
			
			synchronized(this) {
			
			String iterator, last="no message to read";
			while((iterator = in.readLine()) != null){
				last= new Date(System.currentTimeMillis()) + " - "
						+ iterator;
			}
			return last;
			}
		}


}
