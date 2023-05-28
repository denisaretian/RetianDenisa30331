package l6a2;

import java.io.FileWriter;
import java.io.PrintWriter;

public class FileService {
	String fileName;
	PrintWriter out;

	public FileService(String fname) {
		this.fileName = fname;

		try {
			out = new PrintWriter(new FileWriter(fileName, true));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void write(String message) {
		out.println(message);
		out.flush();
	}
}
