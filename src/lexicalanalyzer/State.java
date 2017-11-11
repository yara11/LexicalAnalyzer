package lexicalanalyzer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class State {

    private int id;
    private boolean isAccepting;
    private Set <State> closures = new HashSet<State>();
    private ArrayList<Character> inputs = new ArrayList<Character>();

    State(int id, boolean isAccepting) {
        this.id = id;
        this.isAccepting = false;
        Nfa.AddNfa();
    }

    public int getId() {
        return id;
    }

    public void setIsAccepting(boolean state) {
        isAccepting = state;
    }

    public Set <State> getClosures() {
        return closures;
    }

   public void setClosures() {
        
        boolean[] vis = new boolean[Nfa.transitions.size()]; // TODO:
        Queue<Integer> q = new LinkedList<>();
        q.add(this.id);
        vis[this.id] = true;
        while(!q.isEmpty()) {
            ArrayList<Transition> stateTransitions = Nfa.transitions.get(q.remove());
            for(Transition t: stateTransitions) {
                if(!vis[t.getState().getId()] && t.getSymbol() == '~') {
                    q.add(t.getState().getId());
                    this.closures.add(t.getState());
                    vis[t.getState().getId()] = true;
                }
            }
        }
        this.closures.add(Nfa.states.get(this.id)); // add myself
    }
    void setInputs() {
        int transitions = Nfa.transitions.get(id).size();
        for (int i = 0; i < transitions; i++) {
            char input = Nfa.transitions.get(id).get(i).getSymbol();

            if (input != '~') {
                inputs.add(input);
            }
        }

    }

    ArrayList<Character> getInputs() {
        return this.inputs;
    }

    State getNextState(char symbol) {
        int row = this.getId();
        int column = symbol - '!';
        int nextId = NfaTable.nfaTable[row][column];
      if(nextId==-1)
          return null;
      else{
       
        State nextState = Nfa.states.get(nextId);
       
        return nextState;
    }

     }
}
