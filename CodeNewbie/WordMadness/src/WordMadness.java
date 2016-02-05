

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;

public class WordMadness {

	public static String story = "John drove the fast car and drank the sweet lemonade.";
	
	public static void main(String[] args) {
		try {

			POSTag(story);
			
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void POSTag(String story) throws IOException {
		if (story == null) story = "";
		
		POSModel model = new POSModelLoader().load(new File("dat/en-pos-maxent.bin"));
		POSTaggerME tagger = new POSTaggerME(model);

		@SuppressWarnings("deprecation")
		ObjectStream<String> lineStream = new PlainTextByLineStream(new StringReader(story));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println();
		String whitespaceTokenizerLine[] = WhitespaceTokenizer.INSTANCE.tokenize(lineStream.read());
		String[] tags = tagger.tag(whitespaceTokenizerLine);
		String newstr = "";
		String newwrd = null;
		if (tags.length == whitespaceTokenizerLine.length) {
			for (int i=0; i < tags.length; i++) {
				switch(tags[i]) {
				case "VBP":
					System.out.println("Enter a verb (present tense):");
					newwrd = br.readLine();
					break;
				case "VBD":
					System.out.println("Enter a verb (past tense):");
					newwrd = br.readLine();						
					break;
				case "JJ":
					System.out.println("Enter an adjective:");
					newwrd = br.readLine();							
					break;
				case "NNS":
					System.out.println("Enter a noun (plural):");
					newwrd = br.readLine();						
					break;	
				case "NN":
					System.out.println("Enter a noun (singular):");
					newwrd = br.readLine();										
					break;
				case "NNP":
					System.out.println("Enter a noun (proper):");
					newwrd = br.readLine();							
					break;
				case "RB":
					System.out.println("Enter an adverb:");
					newwrd = br.readLine();								
					break;							
				default:
					newwrd = "";
					break;
				}

				if (newwrd.length() > 0) {
					newstr += "*" + newwrd + "* ";
				} else {
					newstr += whitespaceTokenizerLine[i] + " ";
				}
			}				
		} else {
			System.err.println("error - arrays differ in length");
			System.exit(99);
		}

		lineStream.close();
		br.close();
		
		System.out.println();
		
		System.out.println("Original story:");
		System.out.println(story + "\n");		
		
		System.out.println("New story:");
		System.out.println(newstr + ".");
	}	

}
