/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexicalanalyzer;

import java.util.Arrays;

/**
 *
 * @author User
 */
public class LexicalAnalyzer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        RegularExpression regular = new RegularExpression();
       String postfix= regular.regex_to_postfix("a.b*");
      System.out.println(postfix);
      NfaCreation n = new NfaCreation();
     // String []splitted  =n.split_input(postfix);
      ///System.out.println(Arrays.toString(splitted));
      Nfa nfa1=n.buildfNfa(postfix);
      CombinedNfa.nfas.push(nfa1);
       System.out.println(nfa1.getStart().getId());
      Nfa.printGraph();
      System.out.println();
      Nfa nfa2 =n.buildfNfa("ab|");
       CombinedNfa.nfas.push(nfa2);
      System.out.println(nfa2.getStart().getId());
      Nfa.printGraph();
      NfaTable.constructNfaTable();
      NfaTable.printNfaTable();
      
     CombinedNfa.CombineNfa();
     System.out.println(CombinedNfa.finalStart.getId());
     CombinedNfa.finalStart.setClosures();
     
      for(int i=0;i<CombinedNfa.finalStart.getClosures().size();i++){
          System.out.println(CombinedNfa.finalStart.getClosures().get(i).getId());
      }
      Nfa.printGraph();
    System.out.println(Nfa.states.get(2).getNextState('b').getId());
      //check that states arraylist has all states and that state id is equal to index 
//      System.out.println(Nfa.states.size());
//      for(int i=0;i<Nfa.states.size();i++){
//          System.out.println(Nfa.states.get(i).getId());
//      }
    }
    
}
