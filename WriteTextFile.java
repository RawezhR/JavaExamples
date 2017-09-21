import java.io.BufferedWriter; 
import java.io.File; 
import java.io.FileWriter; 
import java.io.IOException;

/**
 *  description:
 *  This program shows how to write output to 
 *  a text file. If the file exists, it will
 *  overwrite it.
 *
 */
public class WriteTextFile { 
	public static void main(String[] args) { 

		String fullPathOutFile = "dat/out.txt";
		BufferedWriter bw = null;
		FileWriter fw = null;
		File outputFile = new File(fullPathOutFile); 

		try { 
			fw = new FileWriter(outputFile.getAbsoluteFile()); 
			bw = new BufferedWriter(fw); 
			bw.write("A"); 
			bw.newLine(); 
			bw.write("B"); 
			bw.newLine(); 
			bw.write("C"); 
			bw.close(); 
			fw.close(); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		} finally {
			try {
				if (bw != null)
					bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (fw != null)
					fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}            
		}
	} 
}