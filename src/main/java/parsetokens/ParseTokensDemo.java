package parsetokens;
// Objective of this coding exercise is to read a config file, parse it
// into tokens, and interpret the tokens
// copied /etc/samba/smb.conf to src/main/resources/workingcopy_smb.conf

import java.io.*;

public class ParseTokensDemo {
    public static void main(String... args) {

        // first define the file paths
        String inputPath = "src/main/resources/workingcopy_smb.conf";
        String outputPath = "src/main/resources/outputcopy_smb.conf";

        // as a first demo, copy the file using my parsetokens.CopyFileUsingStreams class
        // file should be copied by the following
        CopyFileUsingStreams cp = new CopyFileUsingStreams( inputPath, outputPath);
        cp.copyFiles();

        // as a second demo, break the input file into work and number tokens
        // instantiate a FileReader input stream and a StreamTokenizer
        try (FileReader fileReader = new FileReader(inputPath)) {

            // StreamTokenizer cannot be used inside a try-with-resources because
            // it does not implement the AutoCloseable interface
            StreamTokenizer streamTokenizer = new StreamTokenizer(fileReader);

            // specify the parameters for the Stream being read
            streamTokenizer.commentChar('#');   // character argument starts a single-line comment
            streamTokenizer.commentChar(';');   // character argument starts a single-line comment
            streamTokenizer.eolIsSignificant(false); // whether ends of line are treated as tokens
            streamTokenizer.slashSlashComments(true); // whether tokenizer recognizes C++-style comments
            streamTokenizer.slashStarComments(true); // whether tokenizer recognizes C-style comments
            streamTokenizer.ordinaryChar('/'); // indicates that a single '/' is not to be treated as a comment

            while (streamTokenizer.nextToken() != StreamTokenizer.TT_EOF) {
                switch (streamTokenizer.ttype) {
                    case StreamTokenizer.TT_NUMBER -> {
                        System.out.print(streamTokenizer.lineno() + " TT_NUMBER => ");
                        System.out.println(streamTokenizer.nval);
                    }
                    case StreamTokenizer.TT_WORD -> {
                        System.out.print(streamTokenizer.lineno() + " TT_WORD => ");
                        System.out.println(streamTokenizer.sval);
                    }
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print(java.time.LocalDateTime.now());
    }
}
