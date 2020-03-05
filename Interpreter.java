import java.io.*;
import java.util.*;

/**
 * Interpreter class for the Core interpreter project.
 */
public class Interpreter {

    /**
	 * Private members.
	 */

    //starting node of the parse tree.
    private Prog prog;
    //array that holds all the input data.
    private static ArrayList<Integer> data = new ArrayList<>();
    //index that tracks the next data to read.
    private static int dataTracker = 0;

    /**
	 * Constructor of this class.
	 *
	 * @param tokenizer     tokenizer object that contains the legal tokens.
	 */
    public Interpreter(Tokenizer tokenizer) {
        this.prog = new Prog();
        this.prog.parseProg(tokenizer);
    }

    /**
	 * Read input data.
	 *
	 * @param dataArray     data array obtained from the input data file. 
	 */
    public void readData(ArrayList<Integer> dataArray) {
        for(int i = 0; i < dataArray.size(); i++) {
            data.add(dataArray.get(i));
        }
    }

    /**
	 * Generate the parse tree.
	 */
    public Prog getParseTree() {
        return this.prog;
    }

    /**
	 * Pretty print the original core program.
	 */
    public void prettyPrint() {
        System.out.println("\n***************** Pretty-printed Result *****************\n");
        this.prog.printProg();
    }

    /**
	 * Execute the core program.
	 */
    public void execute() {
        System.out.println("\n******************** Program Output *********************\n");
        this.prog.execProg();
    }

    /**
	 * Helper method to create indentation for prettyPrint method.
     * 
     * @param level     define the number of "tab" spaces to print. 
	 */
    public static void indent(int level) {

        for(int i = 0; i < level; i++) {
            System.out.print("  ");
        }

    }

    /**
	 * Static method that fetches the next data.
	 */
    public static int getInput() {
        
        if(dataTracker >= data.size()) {
            
            System.out.println("Run-tiem error: No data available.");
            System.out.println("Program terminated.");
            System.exit(2);
            
        }
        return data.get(dataTracker++);

    }

}