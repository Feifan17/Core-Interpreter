# Core Interpreter

## Core Grammar

	<prog>		::=     program <decl seq> begin <stmt seq> end 
	<decl seq>	::= 	<decl> | <decl> <decl seq>          
	<stmt seq>	::= 	<stmt> | <stmt> <stmt seq>
	<decl>		::=	int  <id list>;
	<id list>	::=	<id> | <id>, <id list>
	<stmt>		::=	<assign>|<if>|<loop>|<in>|<out>
	<assign>	::=	<id> = <exp>;
	<if>		::=	if <cond> then <stmt seq> end;|if <cond> then <stmt seq> else <stmt seq> end;
	<loop>		::=	while <cond> loop <stmt seq> end;
	<in>		::=	read <id list>;
	<out>		::=	write <id list>;
	<cond>		::=	<comp>|!<cond>| [<cond> && <cond>] | [<cond> || <cond>]
	<comp>		::= 	(<op> <comp op> <op>)	
	<exp>		::= 	<fac>|<fac>+<exp>|<fac>-<exp>
	<fac>		::= 	<op> | <op> * <fac>		
	<op>		::= 	<int> | <id> | (<exp>)	
	<comp op> 	::= 	!= | == | < | > | <= | >= 
	<id>		::= 	<let> | <let><id> | <let><int>
	<let>		::=	A | B | C | ... | X | Y | Z
	<int>		::=	<digit> | <digit><int>
	<digit>		::=	0 | 1 | 2 | 3 | ... | 9


## Interpreter Design
The overall design of the interpreter is based on the object-oriented approach, where each of the following classes is corresponding to a non-terminal node in the Core language grammar.

	Prog class      -> <prog>
	Decl_seq class  -> <decl seq>
	Stmt_seq class  -> <stmt seq>
	Decl class      -> <decl>
	Id_list class   -> <id list>
	Stmt class      -> <stmt>
	Assign class    -> <assign>
	If class        -> <if>
	Loop class      -> <loop>
	In class        -> <in>
	Out class       -> <out>
	Cond class      -> <cond>
	Comp class      -> <comp>
	Exp class       -> <exp>
	Fac class       -> <fac>
	Op class        -> <op>
	Comp_op class   -> <comp op>
	
Each of these classes has a `parse()` method associated with it. The `parse()` method takes a tokenizer object
as its function input and uses the recursive-descent approach to construct the parse tree.

Two types of errors are caught during the parsing time:

- context-free errors (i.e., violations of the Core BNF grammar).
-  context-sensitive errors:
	   * a variable that is used in the statement sequence portion without having been declared.
	   * a doubly-declared variable.

Each of these classes also has a `print()` method associated with it. A helper method indent() in the interpreter
class is used by the print() method to create indentation.

Except for the classes Decl_seq, Decl and Id_list, all the classes mentioned above have an `execute()` method.
Classes Decl_seq, `Decl` and `Id_list` don't have the `execute()` method because it won't be needed.

The run-time errors are caught during the execution time:

- using the value of an uninitialized variable.
- trying to read a value from an empty input stream.


## Compilation & Running the Project
1. Navigate to the project folder, compile with the terminal command: `make`

2. In the same folder, execute the command: 
	
	`java Main tests/test1/coreProgram tests/test1/inputData`
	
	It takes two command line arguments:
	- `coreProgram` is the file that contains your Core program.
	- `inputData` is the file that contains your input data.

3. run `make clean` and `make` to rebuild the project.

## User Mannual
use the Interpreter class with the following steps:

1. create a new interpreter object in your main function using: `Interpreter interpreter = new Interpreter(Tokenizer tokenizer)`;
2. call the `getParseTree()` method on the interpreter to get a parse tree.
3. call the `prettyPrint()` method on the interpreter to output a pretty-printed format of the original program.
4. call the `readData(ArrayList<Integer> data)` method on the interpreter to read in data for the program.
5. call the `execute()` method on the interpreter to execute the program.

## Test Cases
"tests" folder contains 10 test cases: Each within its own subfolder.

- The first 3 test cases are provided by the instructor.
Test case 4 is an error test case where X has been declared twice.
- Test case 5 is an error test case where X5 is not declared but used in statement sequences.
- Test case 6 is an error test case where input stream doesn't contain a value for x4.
- Test case 7 is a correct test case where while condition is a bit complex.
- Test case 8 is a correct test case where while condition is very complex.
- Test case 9 is an error test case where XY has not been initialized but program tries to print out its value.
- Test case 10 is an error test case where XY has not been initialized but an expression tries to use it on the right side.

All the test cases produced the expected results.



## Author
Feifan Lin



