import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadTextFile {

    /**
     * Author: Edwin Torres
     *  email: CoachEd@gmail.com
     *  
     *  Description:
     * This simple program reads a text file line by line
     * and prints out each line as it reads it.
     */
    public static void main(String[] args) {

        String fileFullPath = "C:\\Temp\\data.txt";

        BufferedReader br = null;
        try {
            String line;
            br = new BufferedReader(new FileReader(fileFullPath));

            /** keep reading lines while we still have some */
            while ((line = br.readLine()) != null) {
                System.out.println(line);
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

    } //end main()

} //end ReadTextFile class
