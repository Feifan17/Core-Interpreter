/**
 * Class for node <comp op>.
 */

 class Comp_op {

    /**
	 * Private members: Child nodes.
	 */
    private String comp_op;

    /**
	 * Constructor of this class.
	 */
    public Comp_op() {
         this.comp_op = " ";
    }

     /**
	 * Parse <comp> node.
	 *
	 * @param tokenizer     tokenizer object that contains the legal tokens.
	 */
    public void parseCompOp(Tokenizer tokenizer) {

        String currentToken = tokenizer.getToken();
        if(currentToken.equals("!=") || currentToken.equals("==") || currentToken.equals("<")
        ||currentToken.equals(">")||currentToken.equals("<=")||currentToken.equals(">=")) {
            this.comp_op = currentToken;
            tokenizer.skipToken();
        }
        else {
            System.out.println("Error: token no." + tokenizer.getTracker());
            System.out.println("Current token is " + currentToken);
            System.out.println("Expected a <comp op>");
            System.out.println("Program terminated.");
            System.exit(2);
        }

    }

    /**
	 * Print <comp op> node.
	 */
    public void printCompOp() {
        System.out.print(" " + this.comp_op + " ");
    }

    /**
	 * Accessing child node(s) enabled.
	 */
    public String getCompOp() {
         return this.comp_op;
    }

}