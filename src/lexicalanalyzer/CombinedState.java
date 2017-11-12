package lexicalanalyzer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CombinedState {

    int id;

    Set<State> combinedStates;

    boolean isAccepting;
    Set<Character> combinedInputs;

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

        Iterator<State> iterator = this.combinedStates.iterator();
        while (iterator.hasNext()) {

            iterator.next().setInputs();
            if(iterator.next().getInputs().size()!=0)
            combinedInputs.addAll(iterator.next().getInputs());
        }

    }

    public int getId() {
        return id;
    }
   //add the closures of each state in the combined state to the combined state to complete the combined state.
    void setCombinedStates() {
        Iterator<State> iterator = this.combinedStates.iterator();
        while (iterator.hasNext()) {
            this.combinedStates.addAll(iterator.next().getClosures());

        }

    }

    public Set<State> getCombinedStates() {
        return combinedStates;
    }
    

}
