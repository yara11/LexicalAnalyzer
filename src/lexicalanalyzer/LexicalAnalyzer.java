package lexicalanalyzer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class LexicalAnalyzer {

    public static void main(String[] args) {
        RegularExpression regular = new RegularExpression();
        String postfix = regular.regex_to_postfix("a.b*");
        System.out.println(postfix);
        NfaCreation n = new NfaCreation();
        // String []splitted  =n.split_input(postfix);
        ///System.out.println(Arrays.toString(splitted));
        Nfa nfa1 = n.buildfNfa(postfix);
        CombinedNfa.nfas.push(nfa1);
        System.out.println(nfa1.getStart().getId());
        Nfa.printGraph();
        System.out.println();
        Nfa nfa2 = n.buildfNfa("ab|");
        CombinedNfa.nfas.push(nfa2);
        System.out.println(nfa2.getStart().getId());
        Nfa.printGraph();
        NfaTable.constructNfaTable();
        NfaTable.printNfaTable();

        CombinedNfa.CombineNfa();
        System.out.println(CombinedNfa.finalStart.getId());
        CombinedNfa.finalStart.setClosures();

//      for(int i=0;i<CombinedNfa.finalStart.getClosures().size();i++){
//          System.out.println(CombinedNfa.finalStart.getClosures().get(i).getId());
//      }
        Nfa.printGraph();
        System.out.println(Nfa.states.get(2).getNextState('b').getId());
        //iterator to get states from set
        State state1 = new State(100, true);
        State state2 = new State(200, true);
        Set<State> set1 = new HashSet<State>();
        set1.add(state1);
        set1.add(state2);
        set1.add(state1);

        Iterator<State> iterator = set1.iterator();
        while (iterator.hasNext()) {
            System.out.println("id:" + iterator.next().getId() + " ");
        }
        //check that states arraylist has all states and that state id is equal to index 
//      System.out.println(Nfa.states.size());
//      for(int i=0;i<Nfa.states.size();i++){
//          System.out.println(Nfa.states.get(i).getId());
//      }
    }

}
