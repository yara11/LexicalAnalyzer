/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexicalanalyzer;

import java.util.ArrayList;

/**
 *
 * @author yomnabarakat
 */
public class DFaTable {

    static int no_of_rows = Dfa.transitions.size();
    static int dfaTable[][] = new int[no_of_rows][100];

    static void constructDfaTable() {
        int dead=0;
        for (int i = 0; i < no_of_rows; i++) {
            for (int j = 0; j < 100; j++) {
                dfaTable[i][j] = -1;

            }
        }

        for (int i = 0; i < no_of_rows; i++) {
          
            for (int j = 0; j < Dfa.transitions.get(i).size(); j++) {

                ArrayList<DfaTransition> transitions = Dfa.transitions.get(i);
                int id = transitions.get(j).getState().getId();
                int symbol = (transitions.get(j).getSymbol());
                int col = symbol - '!';
                int row = i;
                dfaTable[row][col] = id;
               
            }
            
        }
       
        
         //add any no transition to the dead state
        ArrayList<CombinedState> list = DfaCreation.combinedStateList;
        int deadStateId = list.get(list.size() - 1).getId();
        for (int i = 0; i < no_of_rows; i++) {
            for (int j = 0; j < 100; j++) {
                if (dfaTable[i][j] == -1) {
                    dfaTable[i][j] = deadStateId;
                }

            }

        }
    }

    static public void printDfaTable() {

        for (int i = 0; i < 100; i++) {
            int number = i + '!';
            System.out.print(String.format("%3s %3s ", (char) number, " "));
        }
        System.out.println();
        for (int i = 0; i < no_of_rows; i++) {
            System.out.print(i +"    ");
            for (int j = 0; j < 100; j++) {
                System.out.print(String.format("%3s %3s ", dfaTable[i][j], " "));
            }
            System.out.println();
        }
    }

}
