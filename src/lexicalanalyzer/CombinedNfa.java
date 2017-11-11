/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexicalanalyzer;

import java.util.Stack;

/**
 *
 * @author yomnabarakat
 */
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
