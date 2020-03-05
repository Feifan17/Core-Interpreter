/**
 * Class for node <if>.
 */
class If {

    /**
	 * Private members: Child nodes and the alternative number.
	 */
    private int altNo;              //parsing decision:
	private Cond cond;              //if condition;
	private Stmt_seq ifStmtSeq;     //altNo = 0: <if> ::= if <cond> then <stmt seq> end;
    private Stmt_seq elseStmtSeq;   //altNo = 1: <if> ::= if <cond> then <stmt seq> else <stmt seq> end;
    
    /**
	 * Constructor of this class.
	 */
    public If() {

        this.altNo = 0;
        this.cond = null;
        this.ifStmtSeq = null;
        this.elseStmtSeq = null;

    }

    /**
	 * Parse <if> node.
	 *
	 * @param tokenizer     tokenizer object that contains the legal tokens.
	 */
    public void parseIf(Tokenizer tokenizer) {

        tokenizer.matchKeyword("if");
        this.cond = new Cond();
        this.cond.parseCond(tokenizer);
        tokenizer.matchKeyword("then");
        this.ifStmtSeq = new Stmt_seq();
        this.ifStmtSeq.parseStmtSeq(tokenizer);
        
        //parse an else statement if an "else" is encountered.
        String currentToken = tokenizer.getToken();
        if(currentToken.equals("else")) {
            this.altNo = 1;
            tokenizer.skipToken();
            this.elseStmtSeq = new Stmt_seq();
            this.elseStmtSeq.parseStmtSeq(tokenizer);
        }

        tokenizer.matchKeyword("end");
        tokenizer.matchKeyword(";");

    }

    /**
	 * Print <if> node.
     * 
     * @param level     define the number of "tab" spaces to print. 
	 */
    public void printIf(int level) {

        //create indentation.
        Interpreter.indent(level);
        System.out.print("if ");
        this.cond.printCond();
        //create indentation.
        System.out.print("\n");
        Interpreter.indent(level + 1);
        System.out.println("then");
        this.ifStmtSeq.printStmtSeq(level + 2);
        if(this.altNo == 1) {
            //create indentation.
            Interpreter.indent(level + 1);
            System.out.println("else");
            this.elseStmtSeq.printStmtSeq(level + 2);
        }
        //create indentation.
        Interpreter.indent(level);
        System.out.println("end;");
    }

    /**
	 * Execute <if> node.
	 */
    public void execIf() {

        if(this.cond.execCond()) {
            this.ifStmtSeq.execStmtSeq();
        }
        else if(this.altNo == 1) {
            this.elseStmtSeq.execStmtSeq();
        }
        
    }

    /**
	 * Accessing child node(s) and the alternative number enabled.
	 */
    public int getAltNo() { 
        return this.altNo; 
    }

    public Cond getCond() { 
        return this.cond; 
    }
    
    public Stmt_seq getIfStmtSeq() { 
        return this.ifStmtSeq; 
    }
    
    public Stmt_seq getElseStmtSeq() { 
        return this.elseStmtSeq; 
    }
    
}