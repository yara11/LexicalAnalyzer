package lexicalanalyzer;

import java.util.Stack;
//////////////////weird behaviour //////////////////////

public class CombinedNfa {

    static Stack<Nfa> nfas = new Stack<Nfa>();
    static State finalStart;
    //pushing all Nfas to stack and forming a whole new State as a start.

    static void CombineNfa() {
        finalStart = new State(Nfa.last_id++, false);
        //
        Nfa.states.add(finalStart);
        //
        while (!nfas.isEmpty()) {
            Nfa nfa = nfas.pop();
            Nfa.connectStates(finalStart, nfa.getStart(), '~');
        }
   //  finalStart.setClosures();
    }

}
