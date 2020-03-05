import java.io.*;
import java.util.*;
/**
 * Class for node <id>.
 */
class Id {

    /**
	 * Private members:
	 */
    private String name;
    private int val;
    private boolean declared;
    private boolean initialized;

    //static array that holds all existing ids.
    private static ArrayList<Id> eId = new ArrayList<>();
    //static variable that keeps tracking the number of existing ids.
    private static int idCount = 0;

    /**
	 * Private constructor of this class.
	 */
    private Id(String name) {
        
        this.name = name;
        this.declared = false;
        this.initialized = false;

    }

    /**
	 * Static method that returns the number of existing ids.
	 */
    public static int getIdCount() {
        return idCount;
    }

    /**
	 * Static method that parses id:
     * If the id already exists, return it
     * otherwise create a new id object.
	 */
    public static Id parseId(Tokenizer tokenizer) {

        String currentToken = tokenizer.getToken();
        tokenizer.skipToken();
        
        //loop through the array to check if the id exists.
        for(int i = 0; i < eId.size(); i++) {
            if(currentToken.equals(eId.get(i).name)) {
                //return the id if it already exists.
                return eId.get(i);
            }
        }
        //If not existed, construct a new id.
        Id id = new Id(currentToken);
        eId.add(id);
        idCount++;
        return id;

    }

    /**
	 * Return the value of the id.
	 */
    public int getVal() {
        return this.val;
    }

    /**
	 * Set the value of the id.
	 */
    public void setIdVal(int val) {
        this.val = val;
    }

    /**
	 * Return if the id is initialized.
	 */
    public boolean isInitialized() {
        return this.initialized;
    }

    /**
	 * Return if the id is declared.
	 */
    public boolean isDeclared() {
        return this.declared;
    }

    /**
	 * Set the initialized property to true.
	 */
    public void setInitialized() {
        this.initialized = true;
    }

    /**
	 * Set the declared property to true.
	 */
    public void setDeclared() {
        this.declared = true;
    }

    /**
	 * Return the name of the id.
	 */
    public String getIdName() {
        return this.name;
    }

    public static Id findId(String id) {

        Id result = null;
        for(int i = 0; i < eId.size(); i++) {
            if(id.equals(eId.get(i).name)) {
                result = eId.get(i);
            }
        }
        return result;

    }

}