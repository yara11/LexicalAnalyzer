package lexicalanalyzer;

import java.util.ArrayList;

public class NfaTable {

    static int no_of_rows = Nfa.transitions.size();
    static int nfaTable[][] = new int[no_of_rows][100];

//    NfaTable(int length) {
//        this.nfaTable = new int[length][100];
//        this.no_of_rows = length;
//    }
    static void constructNfaTable() {

        for (int i = 0; i < no_of_rows; i++) {
            for (int j = 0; j < 100; j++) {
                nfaTable[i][j] = -1;

            }
        }

        for (int i = 0; i < no_of_rows; i++) {
            for (int j = 0; j < Nfa.transitions.get(i).size(); j++) { //Horrible shakl.
                
                ArrayList<Transition> transitions = Nfa.transitions.get(i);
                int id = transitions.get(j).getState().getId();
                int symbol = (transitions.get(j).getSymbol());
                int col = symbol - '!';
                int row = i;
                nfaTable[row][col] = id;

            }
        }
    }

    static public void printNfaTable() {

        for (int i = 0; i < 100; i++) {
            int number = i + '!';
            System.out.print(String.format("%3s %3s ", (char) number, " "));
        }
        System.out.println();
        for (int i = 0; i < no_of_rows; i++) {
            for (int j = 0; j < 100; j++) {
                System.out.print(String.format("%3s %3s ", nfaTable[i][j], " "));
            }
            System.out.println();
        }
    }

}
