/**
 * Class for node <exp>.
 */
class Exp {

    /**
	 * Private members: Child nodes and the alternative number.
	 */
    private int altNo;  //parsing decision:
    private Fac fac;    //altNo = 0: <exp> ::= <fac>
    private Exp exp;    //altNo = 1 or 2: <exp> ::= <fac> + <exp> | <fac> - <exp>

    /**
	 * Constructor of this class.
	 */
    public Exp() {
        this.altNo = 0;
        this.fac = null;
        this.exp = null;
    }

    /**
	 * Parse <exp> node.
	 *
	 * @param tokenizer     tokenizer object that contains the legal tokens.
	 */
    public void parseExp(Tokenizer tokenizer) {

        this.fac = new Fac();
        this.fac.parseFac(tokenizer);
        String currentToken = tokenizer.getToken();

        //case: <exp> ::= <fac> + <exp>
        if(currentToken.equals("+")) {
            tokenizer.skipToken();
            this.altNo = 1;
            this.exp = new Exp();
            this.exp.parseExp(tokenizer);
        }
        //case: <exp> ::= <fac> - <exp>
        else if(currentToken.equals("-")) {
            tokenizer.skipToken();
            this.altNo = 2;
            this.exp = new Exp();
            this.exp.parseExp(tokenizer);
        }

    }

    /**
	 * Print <exp> node.
	 */
    public void printExp() {

        switch(this.altNo) {
            case 0:
                    this.fac.printFac();
                    break;
            case 1:
                    this.fac.printFac();
                    System.out.print(" + ");
                    this.exp.printExp();
                    break; 
            case 2:
                    this.fac.printFac();
                    System.out.print(" - ");
                    this.exp.printExp();
                    break;  
            default:
                    break;         
        }

    }

    /**
	 * Execute <exp> node: return the result of the expression evaluation.
	 */
    public int execExp() {

        int res = 0;
        switch(this.altNo) {
            case 0:
                    res = this.fac.execFac();
                    break;
            case 1:
                    res = this.fac.execFac() + this.exp.execExp();
                    break; 
            case 2:
                    res = this.fac.execFac() - this.exp.execExp();
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
    
    public Fac getFac() {
        return this.fac;
    }

    public Exp getExp() {
        return this.exp;
    }

}