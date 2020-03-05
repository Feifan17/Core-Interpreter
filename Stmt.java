/**
 * Class for node <stmt>.
 */
class Stmt {

    /**
	 * Private members: Child nodes and the alternative number.
	 */
    private int altNo;  //parsing decision:
	private Assign s1;  //altNo = 1: <stmt> ::=	<assign>
	private If s2;      //altNo = 2: <stmt> ::= <if>
	private Loop s3;    //altNo = 3: <stmt> ::= <loop>
	private In s4;      //altNo = 4: <stmt> ::= <in>
    private Out s5;     //altNo = 5: <stmt> ::= <out>
    
    /**
	 * Constructor of this class.
	 */
    public Stmt() {
        this.altNo = 0;
        this.s1 = null;
        this.s2 = null;
        this.s3 = null;
        this.s4 = null;
        this.s5 = null;
    }

    /**
	 * Parse <stmt> node.
	 *
	 * @param tokenizer     tokenizer object that contains the legal tokens.
	 */
    public void parseStmt(Tokenizer tokenizer) {

        String currentToken = tokenizer.getToken();

        //case: if next token is an id, then it should be an <assign> node.
        if(tokenizer.getTokenNum() == 32) {
            this.altNo = 1;
            this.s1 = new Assign();
            this.s1.parseAssign(tokenizer);
        }
        //case: if next token is "if", then it should be an <if> node.
        else if(currentToken.equals("if")) {
            this.altNo = 2;
            this.s2 = new If();
            this.s2.parseIf(tokenizer);
        }
        //case: if next token is "while", then it should be a <loop> node.
        else if(currentToken.equals("while")) {
            this.altNo = 3;
            this.s3 = new Loop();
            this.s3.parseLoop(tokenizer);
        }
        //case: if next token is "read", then it should be an <in> node.
        else if(currentToken.equals("read")) {
            this.altNo = 4;
            this.s4 = new In();
            this.s4.parseIn(tokenizer);
        }
        //case: if next token is "write", then it should be an <out> node.
        else if(currentToken.equals("write")) {
            this.altNo = 5;
            this.s5 = new Out();
            this.s5.parseOut(tokenizer);
        }
        else {
            System.out.println("Error: token no." + tokenizer.getTracker());
            System.out.println("Current token is " + currentToken);
            System.out.println("Expected a <stmt>");
            System.out.println("Program terminated.");
            System.exit(2);
        }

    }

    /**
	 * Print <stmt> node.
     * 
     * @param level     define the number of "tab" spaces to print. 
	 */
    public void printStmt(int level) {

        switch(this.altNo) {
            case 1:
                    this.s1.printAssign(level);
                    break;
            case 2:
                    this.s2.printIf(level);
                    break; 
            case 3:
                    this.s3.printLoop(level);
                    break;  
            case 4:
                    this.s4.printIn(level);
                    break;  
            case 5:
                    this.s5.printOut(level);
                    break;  
            default:
                    break;         
        }
        
    }

    /**
	 * Execute <stmt> node.
	 */
    public void execStmt() {

        switch(this.altNo) {
            case 1:
                    this.s1.execAssign();
                    break;
            case 2:
                    this.s2.execIf();
                    break; 
            case 3:
                    this.s3.execLoop();
                    break;  
            case 4:
                    this.s4.execIn();
                    break;  
            case 5:
                    this.s5.execOut();
                    break;  
            default:
                    break;         
        }

    }

    /**
	 * Accessing child node(s) and the alternative number enabled.
	 */
    public int getAltNo() { 
        return this.altNo; 
    }

	public Assign getAssign() { 
        return this.s1; 
    }

	public If getIf() { 
        return this.s2; 
    }

	public Loop getLoop() { 
        return this.s3; 
    }

	public In getIn() { 
        return this.s4; 
    }

	public Out getOut() { 
        return this.s5; 
    }

}