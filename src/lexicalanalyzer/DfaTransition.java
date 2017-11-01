/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexicalanalyzer;

/**
 *
 * @author yomnabarakat
 */
public class DfaTransition {
     private CombinedState nextState;
    private char symbol;
    public DfaTransition(CombinedState nextState, char symbol) {
        this.nextState = nextState;
        this.symbol = symbol;
    }
    public DfaTransition(CombinedState nextState)
    {
        this.nextState = nextState;
        this.symbol=' ';
    }
    public CombinedState getState() {
        return nextState;
    }
    public char getSymbol() {
        return symbol;
    }
}
