package lexicalanalyzer;

import java.util.ArrayList;

public class Partition {

    int id;
    ArrayList<CombinedState> pCombinedState;
    boolean isFinal;
    String pStr;

    public Partition(int id) {
        this.id = id;
        pCombinedState = new ArrayList<CombinedState>();

    }

}
