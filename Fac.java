/**
 * Class for node <fac>.
 */
class Fac {

    /**
	 * Private members: Child nodes and the alternative number.
	 */
    private int altNo;  //parsing decision:
    private Op op;      //altNo = 0: <fac> ::= <op>
    private Fac fac;    //altNo = 1: <fac> ::= <op> * <fac>

    /**
	 * Constructor of this class.
	 */
    public Fac() {
        this.altNo = 0;
        this.op = null;
        this.fac = null;
    }

    /**
	 * Parse <fac> node.
	 *
	 * @param tokenizer     tokenizer object that contains the legal tokens.
	 */
    public void parseFac(Tokenizer tokenizer) {

        this.op = new Op();
        this.op.parseOp(tokenizer);

        String currentToken = tokenizer.getToken();
        if(currentToken.equals("*")) {
            tokenizer.skipToken();
            this.altNo = 1;
            this.fac = new Fac();
            this.fac.parseFac(tokenizer);
        }

    }

    /**
	 * Print <fac> node.
	 */
    public void printFac() {

        switch(this.altNo) {
            case 0:
                    this.op.printOp();
                    break;
            case 1:
                    this.op.printOp();
                    System.out.print(" * ");
                    this.fac.printFac();
                    break; 
            default:
                    break;         
        }
    }

    /**
	 * Execute <fac> node: return the result of the expression evaluation.
	 */
    public int execFac() {

        int res = 0;
        switch(this.altNo) {
            case 0:
                    res = this.op.execOp();
                    break;
            case 1:
                    res = this.op.execOp() * this.fac.execFac();
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

    public Op getOp() {
        return this.op;
    }

    public Fac getFac() {
        return this.fac;
    }
     
}