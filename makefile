JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
		$(JC) $(JFLAGS) $*.java

CLASSES = \
        Assign.java \
        Comp_op.java \
        Comp.java \
        Cond.java \
		Decl_seq.java \
		Decl.java \
		Exp.java \
		Fac.java \
		Id_list.java \
		Id.java \
		If.java \
		In.java \
		Loop.java \
		Op.java \
		Out.java \
		Prog.java \
		Stmt_seq.java \
		Stmt.java \
		Tokenizer.java \
		Interpreter.java \
		Main.java 

default: classes

classes: $(CLASSES:.java=.class)

clean:
		$(RM) *.class
