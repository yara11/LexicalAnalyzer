package lexicalanalyzer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class LexicalAnalyzer {

    public static void main(String[] args) {

        RegularExpression regular = new RegularExpression();
        //String concat = "input";
        //System.out.println("This is the concatenated string "+concat.substring(5).length());
        String expression = "a+";
        String postfix = regular.regex_to_postfix(expression);
        System.out.println(postfix);
        NfaCreation n = new NfaCreation();
        Nfa nfa1 = n.buildfNfa("*", expression);
        CombinedNfa.nfas.push(nfa1);
        System.out.println(nfa1.getStart().getId());
        Nfa.printGraph();
        System.out.println();
        String expression2 = "b+";
        Nfa nfa2 = n.buildfNfa("b+", expression2);
        CombinedNfa.nfas.push(nfa2);
        System.out.println(nfa2.getStart().getId());

        //print the Nfa 
        System.out.println("THis is our NFA");
        Nfa.printGraph();

        CombinedNfa.CombineNfa();
        System.out.println(CombinedNfa.finalStart.getId());
        // setting inputs and closures for each state in the nfa.
        for (int i = 0; i < Nfa.states.size(); i++) {
            Nfa.states.get(i).setClosures();
            Nfa.states.get(i).setInputs();
        }

        NfaTable.constructNfaTable();
        //NfaTable.printNfaTable();
        System.out.println();
        System.out.println("this is our Dfa");

        DfaCreation.createDfa(CombinedNfa.finalStart);
        Dfa.printDfaGraph();
        DFaTable.constructDfaTable();
        //print the dfa
        //DFaTable.printDfaTable();
        Validation.validate("a");
    System.out.println("This is the resut pattern "+Validation.queue_blocks.peek().pattern.get(0));
        //readRE.printReadFile();
        //NfaInput.nfaInput();

    }

}
