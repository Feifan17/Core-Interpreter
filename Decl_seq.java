/**
 * Class for node <decl seq>.
 */
class Decl_seq {

    /**
	 * Private members: Child nodes and the alternative number.
	 */
    private int altNo;          //parsing decision:
    private Decl decl;          //altNo = 0: <decl seq>	::= <decl>
    private Decl_seq declSeq;   //altNo = 1: <decl seq>	::= <decl> <decl seq>

    /**
	 * Constructor of this class.
	 */
    public Decl_seq() {
        this.altNo = 0;
        this.decl = null;
        this.declSeq = null;
    }

    /**
	 * Parse <decl seq> node.
	 *
	 * @param tokenizer     tokenizer object that contains the legal tokens.
	 */
    public void parseDeclSeq(Tokenizer tokenizer) {

        this.decl = new Decl();
        this.decl.parseDecl(tokenizer);
        //no "begin" keyword means that there is another <decl seq>, parse it.
        if(!tokenizer.getToken().equals("begin")) {
            this.altNo = 1;
            this.declSeq = new Decl_seq();
            this.declSeq.parseDeclSeq(tokenizer);
        }

    }

    /**
	 * Print <decl seq> node.
     * 
     * @param level     define the number of "tab" spaces to print. 
	 */
    public void printDeclSeq(int level) {
        
        this.decl.printDecl(level);
        //print another <decl seq> if there is a one.
        if(this.altNo == 1) {
            this.declSeq.printDeclSeq(level);
        }

    }

    /**
	 * Accessing child node(s) and the alternative number enabled.
	 */
    public int getAltNo() {
        return this.altNo;
    }

    public Decl getDecl() {
        return this.decl;
    }

    public Decl_seq getDeclSeq() {
        return this.declSeq;
    }
    
}