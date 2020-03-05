/**
 * Class for node <id list>.
 */
class Id_list {

    /**
	 * Private members: Child nodes and the alternative number.
	 */
    private int altNo;          //parsing decision:
    private Id id;              //altNo = 0: <id list> ::= <id>
    private Id_list idList;     //altNo = 1: <id list> ::= <id>, <id list>

    /**
	 * Constructor of this class.
	 */
    public Id_list() {
        this.altNo = 0;
        this.id = null;
        this.idList= null;
    }

    /**
	 * Parse <id list> node.
	 *
	 * @param tokenizer     tokenizer object that contains the legal tokens.
	 */
    public void parseIdList(Tokenizer tokenizer) {
        
        this.id = Id.parseId(tokenizer);
        //parse another <id list> if a comma is encountered.
        if(tokenizer.getToken().equals(",")) {
            this.altNo = 1;
            tokenizer.skipToken();
            this.idList = new Id_list();
            this.idList.parseIdList(tokenizer);
        }
        
    }

    /**
	 * Print <id list> node.
	 */
    public void printIdList() {

        System.out.print(this.id.getIdName());
        //print another <id list> if there is a one.
        if(this.altNo == 1) {
            System.out.print(", ");
            this.idList.printIdList();
        }

    }

    /**
	 * Accessing child node(s) and the alternative number enabled.
	 */
    public int getAltNo() {
        return this.altNo;
    }

    public Id getId() {
        return this.id;
    }

    public Id_list getIdList() {
        return this.idList;
    }

}