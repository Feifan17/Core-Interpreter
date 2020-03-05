/**
 * Class for node <assign>.
 */
class Assign {

    /**
	 * Private members: Child nodes.
	 */
    private Id id;
    private Exp exp;

    /**
	 * Constructor of this class.
	 */
    public Assign() {
        this.id = null;
        this.exp = null;
    }

    /**
	 * Parse <assign> node.
	 *
	 * @param tokenizer     tokenizer object that contains the legal tokens.
	 */
    public void parseAssign(Tokenizer tokenizer) {

        this.id = Id.parseId(tokenizer);

        //error catching: use of undeclared variables.
        if(!this.id.isDeclared()) {
            System.out.println("Parsing error: id " + id.getIdName() + " has not been declared.");
            System.out.println("Program terminated.");
            System.exit(2);
        }

        tokenizer.matchKeyword("=");
        this.exp = new Exp();
        exp.parseExp(tokenizer);
        tokenizer.matchKeyword(";");

    }

    /**
	 * Print <assign> node.
     * 
     * @param level     define the number of "tab" spaces to print. 
	 */
    public void printAssign(int level) {

        //create indentation.
        Interpreter.indent(level);
        System.out.print(this.id.getIdName() + " = ");
        this.exp.printExp();
        System.out.println(";");
        
    }

    /**
	 * Execute <assign> node.
	 */
    public void execAssign() {

        this.id.setIdVal(this.exp.execExp());

        //after assigning the value, mark the id as initialized.
            if(!this.id.isInitialized()) {
                this.id.setInitialized();
            }
        
    }

    /**
	 * Accessing child node(s) enabled.
	 */
    public Exp getExp() { 
        return this.exp; 
    }

	public Id getId() { 
        return this.id; 
    }
}
