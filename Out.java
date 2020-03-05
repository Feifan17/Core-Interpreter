/**
 * Class for node <out>.
 */
class Out {

    /**
	 * Private members: Child nodes.
	 */
    private Id_list idList;

    /**
	 * Constructor of this class.
	 */
    public Out() {
        this.idList = null;
    }

    /**
	 * Parse <out> node.
	 *
	 * @param tokenizer     tokenizer object that contains the legal tokens.
	 */
    public void parseOut(Tokenizer tokenizer) {

        tokenizer.matchKeyword("write");
        this.idList = new Id_list();
        this.idList.parseIdList(tokenizer);

        //get the first id.
        Id_list list = this.idList;
        Id id = list.getId();
        
        //error catching: use of undeclared variables.
        if(!id.isDeclared()) {
            System.out.println("Parsing error: id " + id.getIdName() + " has not been declared.");
            System.out.println("Can't write value for an undeclared variable.");
            System.out.println("Program terminated.");
            System.exit(2);
        }

        //keep checking if there is an use of undeclared variables.
        while(list.getAltNo() == 1) {
            list = list.getIdList();
            id = list.getId();

            //error catching: use of undeclared variables.
            if(!id.isDeclared()) {
                System.out.println("Parsing error: id " + id.getIdName() + " has not been declared.");
                System.out.println("Can't write value for an undeclared variable.");
                System.out.println("Program terminated.");
                System.exit(2);
            }
        }
        tokenizer.matchKeyword(";");

    }

    /**
	 * Print <out> node.
     * 
     * @param level     define the number of "tab" spaces to print. 
	 */
    public void printOut(int level) {

        //create indentation.
        Interpreter.indent(level);
        System.out.print("write ");
        this.idList.printIdList();
        System.out.println(";");

    }

    /**
	 * Execute <out> node.
	 */
    public void execOut() {
   
        Id_list list = this.idList;
        Id id = list.getId();
        if(id.isInitialized()) {
            System.out.println(id.getIdName() + " = " + id.getVal());
        }
        //error catching: using the value of an uninitialized variable.
        else {
            System.out.println("Run-time error: id " + id.getIdName() + " has not been initialized.");
            System.out.println("Can't print out its value.");
            System.out.println("Program terminated.");
            System.exit(2);
        }
        //keep print out values for ids when possible.
        while(list.getAltNo() == 1) {
            list = list.getIdList();
            id = list.getId();
            if(id.isInitialized()) {
                System.out.println(id.getIdName() + " = " + id.getVal());
            }
            //error catching: using the value of an uninitialized variable.
            else {
                System.out.println("Run-time error: id " + id.getIdName() + " has not been initialized.");
                System.out.println("Can't print out its value.");
                System.out.println("Program terminated.");
                System.exit(2);
            }
        }
        
    }

    /**
	 * Accessing child node(s) enabled.
	 */
    public Id_list getIdList() { 
        return this.idList; 
    }

}