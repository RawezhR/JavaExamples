import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class MyEncrypt {

    /**
     * This program accepts an input file (any type) and password string.
     * It outputs the encrypted data to a file named encrypt.bin
     * in the current directory.
     * 
     * Data Encryption Standard (DES) is used, options:
     * DES/ECB/PKCS5Padding.
     * 
     * @param  args  arguments to the main method
     * 
     * Usage: java MyEncrypt input.txt mypassword
     */
    public static void main(String[] args) {

        if (args.length != 2) {
            System.err.println("usage: java MyEncrypt <input file> <password>");
            return;
        }

        /** make sure the input file exists */
        String fname = args[0]; //full path name
        File f = new File(fname);
        if(!f.exists()){
            System.err.println("error: input file does not exist");
            return;
        }

        /** make sure password is at least eight characters */
        String password = args[1];
        if(password == null || password.length() < 8){
            System.err.println("error: password must be at least eight characters");
            return;
        }        

        /** the output file; saved to the current directory */
        String sOutputFile = "encrypt.bin";

        try{

            /** create the DES key using the supplied password */
            byte[] desKeyData = password.getBytes(); //uses first 8 bytes as the key material
            DESKeySpec desKeySpec = new DESKeySpec(desKeyData);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");      
            SecretKey myDesKey = keyFactory.generateSecret(desKeySpec);  

            /** create and initialize the cipher */
            Cipher desCipher; 
            desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding"); //hard-coded security options
            desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);

            /** read in the input file */
            byte[] text = readFile(fname);

            /** encrypt the data */
            byte[] textEncrypted = desCipher.doFinal(text);

            /** output to a binary file */
            outputBinaryFile(sOutputFile, textEncrypted);

        } catch (Exception e) {
            /** just catch all exceptions and display a generic error */
            System.err.println("error: unable to encrypt the file");
        }

    }        

    public static byte[] readFile(String fullPathName) {
        FileInputStream fileInputStream;
        File file = new File(fullPathName);
        byte[] bytes = new byte[(int) file.length()];

        try {
            //convert file into array of bytes
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytes);
            fileInputStream.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return bytes;
    }

    public static void outputBinaryFile(String fname, byte[] arr) {
        OutputStream out = null;
        try {
            out = new FileOutputStream(fname);
            out.write(arr);
            out.close();
            System.out.println("file written: " + fname);            
        } catch (IOException e) {
            e.printStackTrace();          
        }
    }


}
