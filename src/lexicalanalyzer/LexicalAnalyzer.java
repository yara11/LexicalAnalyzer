package lexicalanalyzer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class LexicalAnalyzer {

    public static void main(String[] args) {

        // TODO code application logic here
      RegularExpression regular = new RegularExpression();
      String postfix= regular.regex_to_postfix("a.b");
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
      
      
     CombinedNfa.CombineNfa();
     System.out.println(CombinedNfa.finalStart.getId());
     // setting inputs and closures for each state in the nfa.
     for(int i=0;i< Nfa.states.size();i++)
     {
         Nfa.states.get(i).setClosures();
          Nfa.states.get(i).setInputs();
     }
     for(int i=0;i<Nfa.states.size();i++)
     {
          System.out.println("state closures "+i+" "+ Nfa.states.get(i).getClosures().size());
          System.out.println("state inputs "+i+" "+ Nfa.states.get(i).getInputs().size());
     }
     
     NfaTable.constructNfaTable();
      NfaTable.printNfaTable();
    //  System.out.println("dfaaaaaaaaaaaaa");
     DfaCreation.createDfa(CombinedNfa.finalStart); 
     Dfa.printDfaGraph();
     
     
       
     

//
//     State st1 = new State(9,true);
//     State st2 = new State(10,true);
//     State st3 = new State(11,true);
//     State st4 = new State(12,true);
//     State st5 = new State(13,true);
//      
//     Set<State> set1= new HashSet<State>();
//     Set<State> set2= new HashSet<State>();
//     Iterator<State> iterator3 = set2.iterator();
//      set1.add(st1);
//      set1.add(st2);
//      
//      set2.add(st1);
//      set2.add(st2);
//     // System.out.println("equaaaalls");
//      
//      if(set1.equals(set2)){
//          System.out.println("equaaaalls");
//      }
//      
//     
////        while (iterator3.hasNext()) {
////      System.out.println("dinaaaaaa");
////      set1.add(iterator3.next()); 
////     
////        }
////     
////    for(State i: set2){
////     
////     set1.add(i); 
////     
////     
////        }
   
  
     
    }

}
