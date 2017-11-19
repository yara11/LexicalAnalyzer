package lexicalanalyzer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class LexicalAnalyzer {

    public static void main(String[] args) {

      
      readRE.printReadFile(); 
       NfaInput.nfaInput();
      RegularExpression regular = new RegularExpression();
       NfaCreation n = new NfaCreation();
       System.out.println("priorityyyyyyy");
       for (String key : readRE.priority.keySet()) {
            System.out.println("key: " + key + "  and value: " + readRE.priority.get(key));

       }
      for(String Key:readRE.RE.keySet()){
          String expression = readRE.RE.get(Key);
          System.out.println("exp of : "+ Key +" is  "+ expression );
          String exWConcat = regular.insert_concat(expression);
          System.out.println("concat of : "+ Key +" is  "+ exWConcat );
          String postfix= regular.regex_to_postfix(exWConcat);
          System.out.println("postfix  of : "+ Key +" is  "+ postfix);
         
          Nfa nfa1=n.buildfNfa(postfix,Key);
          System.out.println("NFA  of : "+ Key +" is done");
          CombinedNfa.nfas.push(nfa1);
          System.out.println("push of : "+ Key +" is done");
      }
      
       
        
     System.out.println("\nThis is the NFA:\n");
     CombinedNfa.CombineNfa();
     Nfa.printGraph();
     System.out.println("combinedNFAStart: "+CombinedNfa.finalStart.getId());
     
     // setting inputs and closures for each state in the nfa.
     for(int i=0;i< Nfa.states.size();i++)
     {
         Nfa.states.get(i).setClosures();
         Nfa.states.get(i).setInputs();
     }

     
     NfaTable.constructNfaTable();
     //NfaTable.printNfaTable();
   
     DfaCreation.createDfa(CombinedNfa.finalStart); 
     System.out.println("\nThis is the DFA:\n");
     Dfa.printDfaGraph();
     DFaTable.constructDfaTable();
     //DFaTable.printDfaTable();
    Validation.validate("if");
     
      System.out.println("This is the resut pattern "+Validation.getPriority());
       
      


   
  
     
   }

}
