package lexicalanalyzer;

public class State {
    private int id;
    private boolean isAccepting;
    
    State(int id, boolean isAccepting)
    {
        this.id = id;
        this.isAccepting=false;
        Nfa.AddNfa();
    }
    public int getId() {
        return id;
    }
    public void setIsAccepting(boolean state) {
        isAccepting = state;
    }
}
