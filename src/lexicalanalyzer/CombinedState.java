/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexicalanalyzer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author yomnabarakat
 */
public class CombinedState {
    int id;
    ArrayList<State> combinedStates  ;
    boolean isAccepting;
    Set<Character> inputs;

    public CombinedState(int id) {
        this.id = id;
        this.combinedStates=new ArrayList<State>();
        this.inputs= new HashSet<Character>();
    }

    public CombinedState(int id, ArrayList combinedStates) {
        this.id = id;
        this.combinedStates = combinedStates;
        this.inputs= new HashSet<Character>();
    }
            
  void getInputs(){
      for(int i=0; i<this.combinedStates.size();i++)
      {
          inputs.addAll(combinedStates.get(i).getInputs());
      }
  }
    
}
