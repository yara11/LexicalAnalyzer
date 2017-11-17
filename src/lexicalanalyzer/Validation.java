/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexicalanalyzer;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author yomnabarakat
 */
public class Validation {

    static Stack<AcceptingBlocks> stack_blocks = new Stack<AcceptingBlocks>();
    static Queue<CombinedState> queue_blocks = new LinkedList();
    //validation function
    static void validate(String lexeme) {
        int end = 0;
        String new_lexeme = lexeme;

        while (end == 0) {
            CombinedState start = DfaCreation.combinedStateList.get(0);
            CombinedState oldState = start;
            int old_id = oldState.getId();
            CombinedState newState = new CombinedState();
            int new_id;
            for (int i = 0; i < new_lexeme.length(); i++) {
                new_id = DFaTable.dfaTable[old_id][new_lexeme.charAt(i) - '!'];
                newState = DfaCreation.combinedStateList.get(new_id);
                add_if_Accepting(newState, i);
                old_id = new_id;
            }
            if (newState.isAccepting != true && !stack_blocks.empty()) {
                AcceptingBlocks backTrack= stack_blocks.pop();
                queue_blocks.add(backTrack.state);
                new_lexeme= new_lexeme.substring(backTrack.i+1);               
                if(new_lexeme.length()==0){
                    end=1;
                }
                
            }
            else if(newState.isAccepting == true)
            {
                AcceptingBlocks backTrack= stack_blocks.pop();
                queue_blocks.add(backTrack.state);
                end=1;
            }
            else{
                System.out.println("Wrong");
                end=1;
            }
        }
    }

    static void add_if_Accepting(CombinedState c, int i) {
        if (c.isAccepting == true) {
            AcceptingBlocks blocks = new AcceptingBlocks(i, c);
            stack_blocks.add(blocks);
        }

    }

}
