package lexicalanalyzer;

public class Transition {
    private State nextState;
    private char symbol;
    public Transition(State nextState, char symbol) {
        this.nextState = nextState;
        this.symbol = symbol;
    }
}
