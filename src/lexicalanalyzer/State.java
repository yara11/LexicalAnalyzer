package lexicalanalyzer;

import java.util.ArrayList;

public class State {
    private int id;
    private boolean isAccepting;
    private ArrayList<State> closures=new ArrayList<State>();
    
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

    public ArrayList<State> getClosures() {
        return closures;
    }

    public void setClosures() {
        ArrayList<Transition> stateTransitions= Nfa.transitions.get(id);
        char c;
        for(int i=0;i<stateTransitions.size();i++){
            c=stateTransitions.get(i).getSymbol();
            if(c=='~')
                closures.add(stateTransitions.get(i).getState());
                
        }
        
    }
    State getNextState(char symbol){
       int row=this.getId();
       int column=symbol-'!';
      int nextId= NfaTable.nfaTable[row][column];
//      if(nextId==0)
//          return null;
//      else{
System.out.println();
     System.out.println(symbol);
     State nextState=Nfa.states.get(nextId);
     System.out.println(row);
      System.out.println();
     return nextState;}
      
  //  }
    
    
}
