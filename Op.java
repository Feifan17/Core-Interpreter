/**
 * Class for node <op>.
 */
class Op {

    /**
	 * Private members: Child nodes and the alternative number.
	 */
    private int altNo;      //parsing decision:
    private int val;        //altNo = 0: <op> ::= <int>	
    private Id id;          //altNo = 1: <op> ::= <id>
    private Exp exp;        //altNo = 2: <op> ::= (<exp>)

    /**
	 * Constructor of this class.
	 */
    public Op() {
        this.altNo = 0;
        this.val = -1;
        this.id =null;
        this.exp = null;
    }

    /**
	 * Parse <op> node.
	 *
	 * @param tokenizer     tokenizer object that contains the legal tokens.
	 */
    public void parseOp(Tokenizer tokenizer) {

        //case: <op> is an integer.
        if(tokenizer.getTokenNum() == 31) {
            this.val = tokenizer.intVal();
            tokenizer.skipToken();
        }
        //case: <op> is an id.
        else if(tokenizer.getTokenNum() == 32) {
            this.altNo = 1;
            this.id = Id.parseId(tokenizer);

            //error catching: use of undeclared variables.
            if(!this.id.isDeclared()) {
                System.out.println("Parsing error: id " + this.id.getIdName() + " has not been declared.");
                System.out.println("Can't use it as an operand.");
                System.out.println("Program terminated.");
                System.exit(2);
            }
        }
        //case: <op> is an expression.
        else if(tokenizer.getToken().equals("(")) {
            this.altNo = 2;
            tokenizer.skipToken();
            this.exp = new Exp();
            this.exp.parseExp(tokenizer);
            tokenizer.matchKeyword(")");
        }
        else {
            System.out.println("Parsing error: token no." + tokenizer.getTracker());
            System.out.println("Current token is " + tokenizer.getToken());
            System.out.println("Expected an <op>");
            System.out.println("Program terminated.");
            System.exit(2);
        }

    }

    /**
	 * Print <op> node.
	 */
    public void printOp() {

        switch(this.altNo) {
            case 0:
                    System.out.print(this.val);
                    break;
            case 1:
                    System.out.print(this.id.getIdName());
                    break; 
            case 2:
                    System.out.print("(");
                    this.exp.printExp();
                    System.out.print(")");
                    break;  
            default:
                    break;         
        }

    }

    /**
	 * Execute <op> node: return the value of the operand.
	 */
    public int execOp() {

        int res = 0;

        switch(this.altNo) {
            case 0:
                    res = this.val;
                    break;
            case 1:
                    //check if the id is initialized.
                    if(this.id.isInitialized()) {
                        res = this.id.getVal();
                    }
                    //error catching: using the value of an uninitialized variable.
                    else {
                        System.out.println("Run-time error: id " + this.id.getIdName() + " has not been initialized.");
                        System.out.println("Can't use it as an operand.");
                        System.out.println("Program terminated.");
                        System.exit(2);
                    }
                    break; 
            case 2:
                    res = this.exp.execExp();
                    break;  
            default:
                    break;         
        }
        return res;

    }

    /**
	 * Accessing child node(s) and the alternative number enabled.
	 */
    public int getAltNo() {
        return this.altNo;
    }

    public int getVal() {
        return this.val;
    }

    public Id getId() {
        return this.id;
    }

    public Exp getExp() {
        return this.exp;
    }

}