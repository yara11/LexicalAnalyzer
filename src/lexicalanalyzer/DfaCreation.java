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
   static int combinedId=0;
   static ArrayList<CombinedState> cList = new ArrayList<CombinedState>();
   
        
  static void createDfa(State finalStart){
      
       System.out.println(finalStart.getClosures().size());
       CombinedState dfaStart  = new CombinedState(combinedId++,finalStart.getClosures()); 
       Dfa.AddDfa();
       cList.add(dfaStart);
       cList.get(0).setInputs();
       
       for(int i=0;i<cList.size();i++){
           
         //  System.out.println(cList.get(i).inputs.size());
             
           
           CombinedState c1 = cList.get(i);
           for(char input: c1.inputs){
               CombinedState c2  = new CombinedState(combinedId++); 
               /////if mesh mogdaaaa
             //  cList.add(c2);//  Dfa.AddDfa();
              // Dfa.connectCombinedStates(c1 , c2, input);
                    
                   for(State st : c1.combinedStates){
                       State nextState=st.getNextState(input);
            
                       if(nextState!=null){
                           c2.combinedStates.add(st);}
                   }       
                      
                       
                       
                       
                  
                c2.setClosures();
                c2.setInputs();
                
                
                
                
                
                
                if(isExist(c2) == null){ 
                    cList.add(c2);
                    Dfa.AddDfa();
                    Dfa.connectCombinedStates(c1 , c2, input);
                   
            
                }
                
                else{
                    
                    Dfa.connectCombinedStates(c1, isExist(c2), input); 
                   
                    
                    
                }
               
                
               
               
               
               
           }//end of inputs 
           
           
       }//end of clist  
       
   }//end of dfa   
       
       
 static CombinedState isExist(CombinedState c) {
      for(int i=0;i<cList.size();i++){
         Set set1 =cList.get(i).combinedStates;
         Set set2 =c.combinedStates;
         
         if(set1.size()!= set2.size()){
             return null;
         }
         
         if(set1.containsAll(set2)){
              return cList.get(i);
         }
        
      }
      
      return null;
     
   }  
   
       
       
       
       
       
       
       
       
    
    
}//end of class
