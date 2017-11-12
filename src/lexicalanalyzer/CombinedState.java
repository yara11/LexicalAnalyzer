package lexicalanalyzer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CombinedState {

    int id;

    Set<State> combinedStates  ;

   
    boolean isAccepting;
    Set<Character> inputs;
   // Set<State> closures;

    public CombinedState(int id) {
        this.id = id;

        this.combinedStates=new HashSet<State>();
        this.inputs= new HashSet<Character>();
        //this.closures= new HashSet<State>();
        

    }

    public CombinedState(int id, Set combinedStates) {
        this.id = id;
        this.combinedStates = combinedStates;
        this.inputs= new HashSet<Character>();
        
        
    }
            
    void setInputs() {
       
       
        Iterator<State> iterator = this.combinedStates.iterator();
        while (iterator.hasNext()) {
            
          iterator.next().setInputs();
             // System.out.println(iterator.next().getInputs().size());
            inputs.addAll(iterator.next().getInputs());
        }


    }

    public int getId() {
        return id;
    }
    
    
    void setClosures(){
        Iterator<State> iterator = this.combinedStates.iterator();
        while (iterator.hasNext()) {
           this.combinedStates.addAll(iterator.next().getClosures());
          
        }
        
    }
    
    
   
    
    
       
    

    void getInputs() {
        for (int i = 0; i < this.combinedStates.size(); i++) {
            inputs.addAll(combinedStates.get(i).getInputs());
        }
    }

}