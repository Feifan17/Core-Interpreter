/**
 * Class for node <comp>.
 */
class Comp {

    /**
	 * Private members: Child nodes.
	 */
    private Op op1;
    private Op op2;
    private Comp_op comp_op;

    /**
	 * Constructor of this class.
	 */
    public Comp() {
        this.op1 = null;
        this.op2 = null;
        this.comp_op = null;
    }

    /**
	 * Parse <comp> node.
	 *
	 * @param tokenizer     tokenizer object that contains the legal tokens.
	 */
    public void parseComp(Tokenizer tokenizer) {

        tokenizer.matchKeyword("(");
        this.op1 = new Op();
        this.op1.parseOp(tokenizer);
        this.comp_op = new Comp_op();
        this.comp_op.parseCompOp(tokenizer);
        this.op2 = new Op();
        this.op2.parseOp(tokenizer);
        tokenizer.matchKeyword(")");

    }

    /**
	 * Print <comp> node.
	 */
    public void printComp() {
        
        System.out.print("(");
        this.op1.printOp();
        this.comp_op.printCompOp();
        this.op2.printOp();
        System.out.print(")");

    }

    /**
	 * Execute <comp> node: return the result of the condition evaluation.
	 */
    public boolean execComp() {

        boolean res = true;
        int v1 = this.op1.execOp();
        int v2 = this.op2.execOp();
        String operator = this.comp_op.getCompOp();

        switch(operator) {
            case "!=":
                    res = (v1 != v2);
                    break;
            case "==":
                    res = (v1 == v2);
                    break; 
            case "<":
                    res = (v1 < v2);
                    break;  
            case ">":
                    res = (v1 > v2);
                    break;  
            case "<=":
                    res = (v1 <= v2);
                    break;  
            case ">=":
                    res = (v1 >= v2); 
            default:
                    break;     
        }
        return res;

    }

    /**
	 * Accessing child node(s) enabled.
	 */
    public Op getOp1() { 
        return this.op1; 
    }

	public Op getOp2() { 
        return this.op2; 
    }
    
	public Comp_op getCompOp() { 
        return this.comp_op; 
    }

}