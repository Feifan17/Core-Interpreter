/**
 * Class for node <prog>.
 */
class Prog {

    /**
	 * Private members: Child nodes.
	 */
    private Decl_seq declSeq;
    private Stmt_seq stmtSeq;

    /**
	 * Constructor of this class.
	 */
    public Prog() {
        this.declSeq = null;
        this.stmtSeq = null;
    }

    /**
	 * Parse <prog> node.
	 *
	 * @param tokenizer     tokenizer object that contains the legal tokens.
	 */
    public void parseProg(Tokenizer tokenizer) {
        
        tokenizer.matchKeyword("program");
        this.declSeq = new Decl_seq();
        this.declSeq.parseDeclSeq(tokenizer);
        tokenizer.matchKeyword("begin");
        this.stmtSeq = new Stmt_seq();
        this.stmtSeq.parseStmtSeq(tokenizer);
        tokenizer.matchKeyword("end");
        tokenizer.matchKeyword("EOF");
        
    }

    /**
	 * Print <prog> node.
	 */
    public void printProg() {
        
        System.out.println("program");
        this.declSeq.printDeclSeq(1);
        Interpreter.indent(1);
        System.out.println("begin");
        this.stmtSeq.printStmtSeq(2);
        Interpreter.indent(1);
        System.out.println("end");

    }

    /**
	 * Execute <prog> node.
	 */
    public void execProg() {
        this.stmtSeq.execStmtSeq();
    }

    
    /**
	 * Accessing child node(s) enabled.
	 */
    public Decl_seq getDeclSeq() {
        return this.declSeq;
    }

    public Stmt_seq getStmtSeq() {
        return this.stmtSeq;
    }

}