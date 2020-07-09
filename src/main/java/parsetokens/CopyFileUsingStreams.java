package parsetokens;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class CopyFileUsingStreams {
   private String inputPath;
   private String outputPath;

   public void copyFiles (){
       // use FileReader class since it reads text from character files, rather
       // than using FileInputStream, which reads raw bytes
       // note the use of try-with-resource rather than simple try catch
       // try-with-resources allows releasing of resources automatically, rather than having
       // to put something like stream.close() in a finally{} block
       // note also that there is no need for code to read the file char by char into the FileReader stream
       // when the FileReader object is instantiated, the stream is available
       try (
               FileReader fileReader = new FileReader(inputPath);
               FileWriter fileWriter =  new FileWriter(outputPath)){
           // Since Java 9, InputStream provides a method called transferTo with the following signature:
           // public long transferTo(OutputStream out) throws IOException
           // InputStream abstract class is the superclass of all classes representing an input stream of bytes.
           fileReader.transferTo(fileWriter);

           // now the contents of the stream are in the output stream, and thus in the output file
           // no need to write to the output file character by character


           System.out.println("Input File path is " + inputPath);
           System.out.println("FileReader object is " + fileReader.toString());
           System.out.println("FileWriter object is " + fileWriter.toString());
           System.out.println("Output File path is " + outputPath);

       } catch (IOException e) {
           e.printStackTrace();
       }

    }

    public CopyFileUsingStreams(String inputPath, String outputPath){
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }
}
