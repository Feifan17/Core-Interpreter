/**
 * Class for node <decl>.
 */
class Decl {

    /**
	 * Private members: Child nodes.
	 */
    private Id_list idList;

    /**
	 * Constructor of this class.
	 */
    public Decl() {
        this.idList = null;
    }

    /**
	 * Parse <decl> node.
	 *
	 * @param tokenizer     tokenizer object that contains the legal tokens.
	 */
    public void parseDecl(Tokenizer tokenizer) {
        
        tokenizer.matchKeyword("int");
        this.idList = new Id_list();
        this.idList.parseIdList(tokenizer);

        //get the first id.
        Id_list list = this.idList;
        Id id = list.getId();

        if(!id.isDeclared()) {
            id.setDeclared();
        }
        //error catching: a doubly-declared variable.
        else {
            System.out.println("Parsing error: id " + id.getIdName() + " has been declared already.");
            System.out.println("Program terminated.");
            System.exit(2);
        }
        //check each id to ensure that no double-declaration happens.
        while(list.getAltNo() == 1) {
            list = list.getIdList();
            id = list.getId();

            if(!id.isDeclared()) {
                id.setDeclared();
            }
            //error catching: a doubly-declared variable.
            else {
                System.out.println("Parsing error: id " + id.getIdName() + " has been declared already.");
                System.out.println("Program terminated.");
                System.exit(2);
            }
        }
        tokenizer.matchKeyword(";");
        
    }

    /**
	 * Print <decl> node.
     * 
     * @param level     define the number of "tab" spaces to print. 
	 */
    public void printDecl(int level) {
        
        //create indentation.
        Interpreter.indent(level);
        System.out.print("int ");
        this.idList.printIdList();
        System.out.println(";");

    }

    /**
	 * Accessing child node(s) enabled.
	 */
    public Id_list getIdList() { 
        return this.idList; 
    }

}