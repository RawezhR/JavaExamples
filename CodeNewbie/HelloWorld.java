
//HelloWorld.java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class HelloWorld {

	public static void main(String[] args) {

		if (args.length == 0) {
			System.out.println("Hello CodeNewbies!");
			System.exit(0);
		}

		String srccode = null;
		String srcfile = null;
		String cmd = null;
		for (String language : args) {
			language = language.toLowerCase();
			switch (language) {
			case "python":
				srccode = "print \"Hello Python!\"";
				srcfile = "hello.py";
				cmd = language;
				break;
			case "ruby":
				srccode = "puts \"Hello Ruby!\"";
				srcfile = "hello.rb";
				cmd = language;
				break;
			case "perl":
				srccode = "print \"Hello Perl!\n\";";
				srcfile = "hello.pl";
				cmd = language;
				break;
			case "java":
				srccode = "public class hello { public static void main(String[] args) {System.out.println(\"Hello Java!\");}}";
				srcfile = "hello.java";
				cmd = "javac";
				//compile 
				writeProgram(srcfile,srccode);
				executeProgram(cmd,srcfile);
				cmd = language;
				srcfile = "hello";
				break;
			default:
				System.out.println("Hello CodeNewbies!");
				break;
			}
			writeProgram(srcfile,srccode);
			executeProgram(cmd,srcfile);
		}

	}

	public static void executeProgram(String cmd, String fname) {
		try {
			ProcessBuilder   ps=new ProcessBuilder(cmd,fname);
			ps.redirectErrorStream(true);
			Process pr = ps.start();  
			BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}
			pr.waitFor();
			in.close();
		}
		catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	public static void writeProgram(String fname, String srcstr) {
		if (srcstr.trim().length() == 0)
			return;
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(fname)))) {
			bw.write(srcstr);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

