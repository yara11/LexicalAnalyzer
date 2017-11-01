package lexicalanalyzer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CombinedState {

    int id;
    ArrayList<State> combinedStates;
    boolean isAccepting;
    Set<Character> inputs;

    public CombinedState(int id) {
        this.id = id;
        this.combinedStates = new ArrayList<State>();
        this.inputs = new HashSet<Character>();
    }

    public CombinedState(int id, ArrayList combinedStates) {
        this.id = id;
        this.combinedStates = combinedStates;
        this.inputs = new HashSet<Character>();
    }

    void getInputs() {
        for (int i = 0; i < this.combinedStates.size(); i++) {
            inputs.addAll(combinedStates.get(i).getInputs());
        }
    }

}
