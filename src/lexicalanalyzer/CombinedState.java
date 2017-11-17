package lexicalanalyzer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CombinedState {

    int id;

    Set<State> combinedStates;
    ArrayList<String> pattern= new ArrayList<String>();
    boolean isAccepting;
    Set<Character> combinedInputs;

    public CombinedState() {
    }

    public Set<Character> getCombinedInputs() {
        return combinedInputs;
    }

    public CombinedState(int id) {
        this.id = id;

        this.combinedStates = new HashSet<State>();
        this.combinedInputs = new HashSet<Character>();

    }

    public CombinedState(int id, Set combinedStates) {
        this.id = id;
        this.combinedStates = combinedStates;
        this.combinedInputs = new HashSet<Character>();

    }

    void setCombinedInputs() {


 for(State i : combinedStates){
          
          this.combinedInputs.addAll(i.getInputs());

        }

    }

    public int getId() {
        return id;
    }
   //add the closures of each state in the combined state to the combined state to complete the combined state.
    void setCombinedStates() {
       
        Set<State> copy = new HashSet<State>();
        for(State j :combinedStates ){
           copy.add(j);
       
        }
       for(State i : copy){
          
            this.combinedStates.addAll(i.getClosures());

        }
       
      

    }

    public Set<State> getCombinedStates() {
        return combinedStates;
    }
    

}
