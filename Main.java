import java.io.*;
import java.util.*;

/**
 * Main class for the Core interpreter project.
 */
public class Main {
    public static void main(String args[]) {
        
        try {
            
            //read the core program line by line and store them into an ArrayList.
            ArrayList<String> lines = new ArrayList<>();
            File coreProgram = new File(args[0]);
            BufferedReader br = new BufferedReader(new FileReader(coreProgram));
            String line;
            while((line = br.readLine()) != null) {
                lines.add(line);
            }

            //read integer values from data file and store them into an ArrayList.
            ArrayList<Integer> data = new ArrayList<>();
            File dataFile = new File(args[1]);
            Scanner scanner = new Scanner(dataFile);
            while(scanner.hasNextInt()) {
                data.add(scanner.nextInt());
            }
            
            //create a tokenizer object from the Tokenizer class
            Tokenizer tokenizer = new Tokenizer();
            ArrayList<String> words = tokenizer.getWords(lines);
            tokenizer.tokenize(words);
            //create an interpreter object from the Interpreter class
            Interpreter interpreter = new Interpreter(tokenizer);
            //generate parse tree.
            interpreter.getParseTree();
            System.out.println("\nParse tree is constructed successfully.");
            //print the original program in a pretty format.
            interpreter.prettyPrint();
            //read input data.
            interpreter.readData(data);
            //execute the core program.
            interpreter.execute();
            
            //close scanner and bufferedReader.
            br.close();
            scanner.close();

        } catch(FileNotFoundException e) {
            System.out.println("File not found! Please try again.");
        } catch(IOException e) {
            System.out.println("Error occurred when reading the file! Please try again.");
        }
    }

}