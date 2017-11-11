/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexicalanalyzer;

import java.util.ArrayList;
import static lexicalanalyzer.Nfa.transitions;

/**
 *
 * @author yomnabarakat
 */
public class Dfa {
    public static ArrayList<ArrayList<DfaTransition>> transitions = new ArrayList();
    
      public static void AddDfa()
             
     {
        ArrayList<DfaTransition> t = new ArrayList();
        transitions.add(t);
     }
    
     public static void connectCombinedStates(CombinedState a, CombinedState b, char sym) {
         transitions.get(a.getId()).add(new DfaTransition(b, sym));
     }
     
     public static void printDfaGraph() {
         for(int i = 0; i < transitions.size(); i++) {
             for(DfaTransition t: transitions.get(i)) {
                 System.out.println("(" + i + " -> " + t.getState().getId() + ", " + t.getSymbol() + ")");
             }
         }
     }
   
        
        
       
        
        
    
}







