/*
 * for web exercise of chapter one 
 */ 

import java.net.MalformedURLException;
import java.net.URL;

public class Wget {
	public static void main(String[] args) {
		if (args.length == 0 || args[0] == "-h" || args[0] == "--help") {
			StdOut.println("usage: Wget urls...");
			System.exit(-1);
		}
		
		for(String arg : args) {
			In in = new In(arg);
			String filepath = "";
			try {
				URL url = new URL(arg);
				filepath = url.getFile();
			} catch (MalformedURLException mue) {
				mue.printStackTrace();
				in.close();
				System.exit(-1);
			}

			String content = in.readAll();
			
			int indx = filepath.lastIndexOf("/");
			String filename = filepath.substring(indx + 1);
			Out out = new Out(filename);
			out.print(content);
			in.close();
			out.close();
		}
	}
}