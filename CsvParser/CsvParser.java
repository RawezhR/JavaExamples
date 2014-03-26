import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * Author: Edwin Torres
 *  email: CoachEd@gmail.com
 * 
 * This class parses an incoming CSV (comma-separated values) file
 * and stores the values in Java objects. The file may or may not
 * have a header row (column names). A command line argument indicates
 * the presence of a header row.
 */

public class CsvParser {

    private boolean bHeaderRow = false;

    private String sFileFullPath = "";
    private ArrayList<ArrayList<String>> alData;
    private ArrayList<String> alColNames;

    /*
     * Custom constructor.
     */
    public CsvParser(String f, String b) {

        /** check for null arguments */
        if (f == null || b == null) {
            System.err.println("error - argument is null");
            System.exit(0);
        }

        /** set properties */
        bHeaderRow = new Boolean(b);
        sFileFullPath = f;

        /** verify that the file exists */
        File checkFile = new File(sFileFullPath);
        if (!checkFile.exists()) {
            System.err.println("error - file does not exist: " + sFileFullPath);
            System.exit(0);
        }

        /** initialize our data structures */
        alData = new ArrayList<ArrayList<String>>();
        alColNames = new ArrayList<String>();        

    }

    /*
     * This method parses the incoming file and stores the data in two ArrayLists.
     * The first ArrayList stores the column names (if present).
     * The second ArrayList is an ArrayList of ArrayList<String>. It stores the record
     * data.
     */
    public void parse() {

        BufferedReader br = null;
        boolean bSeenHeader = false;
        try {
            String line;
            br = new BufferedReader(new FileReader(sFileFullPath));

            /** read entire lines, one at a time */
            while ((line = br.readLine()) != null) {

                if (bHeaderRow && !bSeenHeader) {
                    /** process the header row */
                    alColNames = new ArrayList<String>(Arrays.asList(line.split(",")));
                    bSeenHeader = true;
                } else {
                    /** process normal rows */
                    String arr[] = line.split(",");
                    alData.add(new ArrayList<String>(Arrays.asList(arr)));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }        
    }

    /*
     * Print the data nicely.
     */
    public void printData() {

        if (bHeaderRow) {
            System.out.println("Column names:");
            for (int i=0; i < alColNames.size()-1; i++) {
                System.out.print(alColNames.get(i) + ",");
            }
            System.out.println(alColNames.get(alColNames.size()-1));
            System.out.println();
        }

        System.out.println("Data:");
        for (int i=0; i < alData.size(); i++) {
            ArrayList<String> al = alData.get(i);
            for (int j=0; j < al.size()-1; j++) {
                System.out.print(al.get(j) + ",");
            }
            System.out.println(al.get(al.size()-1));
        }
    }

    /**
     * Main driver program.
     * 
     * @param args  the incoming arguments
     */
    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("usage: java CsvParser [full path filename] [true|false header row]");
            return;
        }

        CsvParser parser = new CsvParser(args[0], args[1]);
        parser.parse();
        parser.printData();

    }

    public boolean isHeaderRowPresent() {
        return bHeaderRow;
    }

    public void setHeaderRowPresent(boolean bHeaderRow) {
        this.bHeaderRow = bHeaderRow;
    }

    public String getsFileFullPath() {
        return sFileFullPath;
    }

    public void setsFileFullPath(String sFileFullPath) {
        this.sFileFullPath = sFileFullPath;
    }

}
