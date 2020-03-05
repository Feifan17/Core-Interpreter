/**
 * Class for node <in>.
 */
class In {

    /**
	 * Private members: Child nodes.
	 */
    private Id_list idList;

    /**
	 * Constructor of this class.
	 */
    public In() {
        this.idList = null;
    }

    /**
	 * Parse <in> node.
	 *
	 * @param tokenizer     tokenizer object that contains the legal tokens.
	 */
    public void parseIn(Tokenizer tokenizer) {

        tokenizer.matchKeyword("read");
        this.idList = new Id_list();
        this.idList.parseIdList(tokenizer);

        //get the first id.
        Id_list list = this.idList;
        Id id = list.getId();
        
        //error catching: use of undeclared variables.
        if(!id.isDeclared()) {
            System.out.println("Parsing error: id " + id.getIdName() + " has not been declared.");
            System.out.println("Can't read value for an undeclared variable.");
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
                System.out.println("Can't read value for an undeclared variable.");
                System.out.println("Program terminated.");
                System.exit(2);
            }
        }
        tokenizer.matchKeyword(";");

    }

    /**
	 * Print <in> node.
     * 
     * @param level     define the number of "tab" spaces to print. 
	 */
    public void printIn(int level) {

        //create indentation.
        Interpreter.indent(level);
        System.out.print("read ");
        this.idList.printIdList();
        System.out.println(";");

    }

    /**
	 * Execute <in> node.
	 */
    public void execIn() {

        //get the first id.
        Id_list list = this.idList;
        Id id = list.getId();
        
        //read the value for the first id.
        id.setIdVal(Interpreter.getInput());
        
        //after read the value, mark the id as initialized.
        if(!id.isInitialized()) {
            id.setInitialized();
        }

        //keep read values for ids when possible.
        while(list.getAltNo() == 1) {
            list = list.getIdList();
            id = list.getId();

            //read the value for the first id.
            id.setIdVal(Interpreter.getInput());
        
            //after read the value, mark the id as initialized.
            if(!id.isInitialized()) {
                id.setInitialized();
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