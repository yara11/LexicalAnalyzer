package lexicalanalyzer;

import java.util.ArrayList;


public class Nfa {
    public static ArrayList<ArrayList<Transition>> transitions = new ArrayList();
    public static int last_id;
    private State start = null, end = null;
    
    public Nfa(State start, State end) {
        this.start = start;
        this.end = end;
    }
    
    //  adding next state to the a certain state.
     public static void AddNfa()
             
     {
        ArrayList<Transition> t = new ArrayList();
        transitions.add(t);
     }
     
     public static Nfa AddTransition(char sym) {
        State start = new State(last_id++, false);
        State end = new State(last_id++, true);
        transitions.get(start.getId()).add(new Transition(end, sym));
        Nfa ret = new Nfa(start, end);
        return ret;
     }
     
     public static void connectStates(State a, State b, char sym) {
         transitions.get(a.getId()).add(new Transition(b, sym));
     }
     
     public State getStart() {
         return start;
     }
     
     public State getEnd() {
         return end;
     }
     
     public static void printGraph() {
         for(int i = 0; i < transitions.size(); i++) {
             for(Transition t: transitions.get(i)) {
                 System.out.println("(" + i + " -> " + t.getState().getId() + ", " + t.getSymbol() + ")");
             }
         }
     }
}
