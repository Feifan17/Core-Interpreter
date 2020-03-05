/**
 * Class for node <loop>.
 */
class Loop {

    /**
	 * Private members: Child nodes.
	 */
    private Cond cond;              
    private Stmt_seq stmtSeq;
    
    /**
	 * Constructor of this class.
	 */
    public Loop() {

        this.cond = null;
        this.stmtSeq = null;

    }
    
    /**
	 * Parse <loop> node.
	 *
	 * @param tokenizer     tokenizer object that contains the legal tokens.
	 */
    public void parseLoop(Tokenizer tokenizer) {

        tokenizer.matchKeyword("while");
        this.cond = new Cond();
        this.cond.parseCond(tokenizer);
        tokenizer.matchKeyword("loop");
        this.stmtSeq = new Stmt_seq();
        this.stmtSeq.parseStmtSeq(tokenizer);

        tokenizer.matchKeyword("end");
        tokenizer.matchKeyword(";");

    }

    /**
	 * Print <loop> node.
     * 
     * @param level     define the number of "tab" spaces to print. 
	 */
    public void printLoop(int level) {
        
        //create indentation.
        Interpreter.indent(level);
        System.out.print("while ");
        this.cond.printCond();
        //create indentation.
        System.out.print("\n");
        Interpreter.indent(level + 1);
        System.out.println("loop");
        this.stmtSeq.printStmtSeq(level + 2);
        //create indentation.
        Interpreter.indent(level + 1);
        System.out.println("end;");

    }

    /**
	 * Execute <loop> node.
	 */
    public void execLoop() {

        while(this.cond.execCond()) {
            this.stmtSeq.execStmtSeq();
        }
        
    }

    /**
	 * Accessing child node(s) enabled.
	 */
    public Stmt_seq getStmtSeq() { 
        return this.stmtSeq; 
    }
    
	public Cond getCond() { 
        return this.cond; 
    }

}