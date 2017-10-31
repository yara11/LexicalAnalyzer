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
public class NfaTable {
    static int no_of_rows=Nfa.transitions.size();
   static  int nfaTable[][]=new int[no_of_rows][100];
   

//    NfaTable(int length) {
//        this.nfaTable = new int[length][100];
//        this.no_of_rows = length;
//    }

   static void constructNfaTable() {
        for (int i = 0; i < Nfa.transitions.size(); i++) {
            for (int j = 0; j < Nfa.transitions.get(i).size(); j++) { //Horrible shakl.
                // fix the many functions.
                ArrayList<Transition> transitions = Nfa.transitions.get(i);
                int id = transitions.get(j).getState().getId();
                int symbol = (transitions.get(j).getSymbol());
                int col = symbol - '!';
                int row = i;
                nfaTable[row][col] = id;

            }
        }
    }
   

  static  public void printNfaTable() {
        
        for(int i=0;i<100;i++){
            int number= i+'!'; 
            System.out.print(Character.toChars(number));
            System.out.print(" ");
        }
        System.out.println();
        for (int i = 0; i < no_of_rows; i++) {
            for (int j = 0; j < 100; j++) {
                System.out.print(nfaTable[i][j] + " ");
            }
            System.out.println();
        }
    }

}
