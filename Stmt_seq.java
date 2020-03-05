/**
 * Class for node <stmt seq>.
 */
class Stmt_seq {
    
    /**
	 * Private members: Child nodes and the alternative number.
	 */
    private int altNo;          //parsing decision:
    private Stmt stmt;          //altNo = 0: <stmt seq>	::= <stmt>
    private Stmt_seq stmtSeq;   //altNo = 1: <stmt seq>	::= <stmt> <stmt seq>

    /**
	 * Constructor of this class.
	 */
    public Stmt_seq() {
        this.altNo = 0;
        this.stmt = null;
        this.stmtSeq = null;
    }

    /**
	 * Parse <stmt seq> node.
	 *
	 * @param tokenizer     tokenizer object that contains the legal tokens.
	 */
    public void parseStmtSeq(Tokenizer tokenizer) {

        this.stmt = new Stmt();
        this.stmt.parseStmt(tokenizer);
        //parse another <stmt seq> if not the end of a <prog>, <if> or <loop>;
        if(!tokenizer.getToken().equals("end") && !tokenizer.getToken().equals("else")) {
            this.altNo = 1;
            this.stmtSeq = new Stmt_seq();
            this.stmtSeq.parseStmtSeq(tokenizer);
        }

    }

    /**
	 * Print <stmt seq> node.
     * 
     * @param level     define the number of "tab" spaces to print. 
	 */
    public void printStmtSeq(int level) {

        this.stmt.printStmt(level);
        //print another <stmt seq> if there is a one.
        if(this.altNo == 1) {
            this.stmtSeq.printStmtSeq(level);
        }
        
    }

    /**
	 * Execute <stmt seq> node.
	 */
    public void execStmtSeq() {

        this.stmt.execStmt();
        //execute another <stmt seq> if there is a one.
        if(this.altNo == 1) {
            this.stmtSeq.execStmtSeq();
        }

    }

    /**
	 * Accessing child node(s) and the alternative number enabled.
	 */
    public int getAltNo() {
        return this.altNo;
    }

    public Stmt getStmt() {
        return this.stmt;
    }

    public Stmt_seq getStmtSeq() {
        return this.stmtSeq;
    }

}