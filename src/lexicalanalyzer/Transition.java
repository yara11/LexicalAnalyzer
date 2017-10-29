package lexicalanalyzer;

public class Transition {
    private State nextState;
    private char symbol;
    public Transition(State nextState, char symbol) {
        this.nextState = nextState;
        this.symbol = symbol;
    }
    public Transition(State nextState)
    {
        this.nextState = nextState;
        this.symbol=' ';
    }
    public State getState() {
        return nextState;
    }
    public char getSymbol() {
        return symbol;
    }
}
