

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class FileOperations {

	FileInputStream fInStream;
	DataInputStream in;
	BufferedReader br;
	
	public FileOperations(String filename) throws FileNotFoundException{
		fInStream = new FileInputStream(filename);
		in = new DataInputStream(fInStream);
		br = new BufferedReader(new InputStreamReader(in));
	}
	
	public String readLineFromFile() {
		try {
			String strline;
			while ((strline = br.readLine()) != null) {
					return strline;
			}
			in.close();
		} catch (Exception e) {
			System.out.println("Error :" + e.getMessage());
		}
		return "";

	}
	
	public void writeToFile(String filename,String[] linesToWrite) throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter writer = new PrintWriter(filename, "UTF-8");
		for (String str : linesToWrite) {
			if ( str == null ) break;
			writer.println(str);			
		}
		writer.close();
	}
	
	

}
