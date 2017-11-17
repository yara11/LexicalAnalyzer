/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexicalanalyzer;

import java.util.ArrayList;
import java.util.Set;

/**
 *
 * @author yomnabarakat
 */
public class DfaCreation {

    static int combinedId = 0;
    static ArrayList<CombinedState> combinedStateList = new ArrayList<CombinedState>();

    static void createDfa(State finalStart) {

        CombinedState dfaStart = new CombinedState(combinedId++, finalStart.getClosures());
        Dfa.AddDfa();
        combinedStateList.add(dfaStart);
        combinedStateList.get(0).setCombinedInputs();
        for (int i = 0; i < combinedStateList.size(); i++) {

            CombinedState c1 = combinedStateList.get(i);

           
//            for (char c : c1.combinedInputs) {
//
//                System.out.println("myid" + c1.id + "inputs" + c);
//
//            }

            for (char input : c1.combinedInputs) {
               
                CombinedState c2 = new CombinedState(combinedId++);

                for (State st : c1.combinedStates) {
                    State nextState = st.getNextState(input);

                    if (nextState != null) {
                        c2.combinedStates.add(nextState);
                    }

                }

                c2.setCombinedStates();

                c2.setCombinedInputs();

                if (isExist(c2) == null) {
                    combinedStateList.add(c2);
                    Dfa.AddDfa();
                    Dfa.connectCombinedStates(c1, c2, input);

                } else {

                    Dfa.connectCombinedStates(c1, isExist(c2), input);

                }

            }//end of inputs 

        }//end of clist  
        //Dead state inserted.
        
        createDeadState();
        setAcceptingStates();
        
    }//end of dfa   

    static void createDeadState() {
       

        CombinedState deadState = new CombinedState(--combinedId);
        combinedStateList.add(deadState);
    }

    //set accepting states in the dfa
    static void setAcceptingStates() {
        for (int i = 0; i < combinedStateList.size(); i++) {
            CombinedState state = combinedStateList.get(i);
            for (State st : state.combinedStates) {
                if (st.isIsAccepting()) {
                    state.isAccepting = true;
                    state.pattern.add(st.getPattern());
                }
            }
        }
    }

    static CombinedState isExist(CombinedState c) {
        for (int i = 0; i < combinedStateList.size(); i++) {
            Set set1 = combinedStateList.get(i).combinedStates;
            Set set2 = c.combinedStates;

            if (set1.equals(set2)) {
                return combinedStateList.get(i);
            }

        }

        return null;

    }

}//end of class
