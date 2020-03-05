/**
 * Class for node <cond>.
 */
class Cond {
    
    /**
	 * Private members: Child nodes and the alternative number.
	 */
    private int altNo;  //parsing decision:
    private Comp comp;  //altNo = 1: <cond>	::= <comp>
    private Cond neg;   //altNo = 2: <cond>	::=	<assign>
    private Cond c1;    //altNo = 3 or 4: <cond> ::= [<cond> && <cond>] | [<cond> or <cond>]
    private Cond c2;    //altNo = 3 or 4: <cond> ::= [<cond> && <cond>] | [<cond> or <cond>]

    /**
	 * Constructor of this class.
	 */
    public Cond() {
        this.altNo = 0;
        this.comp = null;
        this.neg = null;
        this.c1 = null;
        this.c2 = null;
    }

    /**
	 * Parse <cond> node.
	 *
	 * @param tokenizer     tokenizer object that contains the legal tokens.
	 */
    public void parseCond(Tokenizer tokenizer) {

        String currentToken = tokenizer.getToken();

        if(currentToken.equals("(")) {
            this.altNo = 1;
            this.comp = new Comp();
            this.comp.parseComp(tokenizer);
        }
        else if(currentToken.equals("!")) {
            tokenizer.skipToken();
            this.altNo = 2;
            this.neg = new Cond();
            this.neg.parseCond(tokenizer);
        }
        else if(currentToken.equals("[")) {
            tokenizer.skipToken();
            this.c1 = new Cond();
            this.c1.parseCond(tokenizer);
            String token = tokenizer.getToken();
            if(token.equals("&&")) {
                tokenizer.skipToken();
                this.altNo = 3;
                this.c2 = new Cond();
                this.c2.parseCond(tokenizer);
            }
            else if(token.equals("||")) {
                tokenizer.skipToken();
                this.altNo = 4;
                this.c2 = new Cond();
                this.c2.parseCond(tokenizer);
            }
            else {
                System.out.println("Parsing error: token no." + tokenizer.getTracker());
                System.out.println("Current token is " + token);
                System.out.println("Expected && or ||");
                System.out.println("Program terminated.");
                System.exit(2);
            }
            tokenizer.matchKeyword("]");
        }
        else {
            System.out.println("Parsing error: token no." + tokenizer.getTracker());
            System.out.println("Current token is " + currentToken);
            System.out.println("Expected a <cond>");
            System.out.println("Program terminated.");
            System.exit(2);
        }
    }

    /**
	 * Print <cond> node.
	 */
    public void printCond() {

        switch(this.altNo) {
            case 1:
                    this.comp.printComp();
                    break;
            case 2:
                    System.out.print("!");
                    this.neg.printCond();
                    break; 
            case 3:
                    System.out.print("[");
                    this.c1.printCond();
                    System.out.print(" && ");
                    this.c2.printCond();
                    System.out.print("]");
                    break;  
            case 4:
                    System.out.print("[");
                    this.c1.printCond();
                    System.out.print(" || ");
                    this.c2.printCond();
                    System.out.print("]");
                    break;   
            default:
                    break;         
        }

    }

    /**
	 * Execute <cond> node.
	 */
    public boolean execCond() {

        boolean res = true;

        switch(this.altNo) {
            case 1:
                    res = this.comp.execComp();
                    break;
            case 2:
                    res = !(neg.execCond());
                    break; 
            case 3:
                    res = (this.c1.execCond()) && (this.c2.execCond());
                    break;  
            case 4:
                    res = (this.c1.execCond()) || (this.c2.execCond());
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

	public Comp getComp() { 
        return this.comp; 
    }

	public Cond getNeg() { 
        return this.neg; 
    }

	public Cond getC1() { 
        return this.c1; 
    }

	public Cond getC2() { 
        return this.c2; 
    }

}