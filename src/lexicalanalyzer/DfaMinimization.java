package lexicalanalyzer;

import java.util.ArrayList;

public class DfaMinimization {

    
    public static ArrayList<Partition> minimize() {

        ArrayList<Partition> firstLevel = new ArrayList<Partition>();
        Partition accepted = new Partition(0);
        Partition nonaccepted = new Partition(1);

        for (CombinedState CS : DfaCreation.combinedStateList) {
            if (CS.isAccepting == true) {
                accepted.pCombinedState.add(CS);
            } else if (CS.isAccepting == false) {
                nonaccepted.pCombinedState.add(CS);
            }

        }

        firstLevel.add(accepted);
        firstLevel.add(nonaccepted);

        ArrayList<Partition> lastLevel = minimizeLevel(firstLevel);
        return lastLevel;

    }//end of function minimize

    static ArrayList<Partition> minimizeLevel(ArrayList<Partition> level) {
        ArrayList<Partition> newLevel = new ArrayList<Partition>();
        int id = 0;
        int counter = 0;

        //getting the strings loop
        for (Partition Par : level) {
            getStrings(Par, level);

        }

        //partitioning loop
        for (Partition Par : level) {

            ArrayList<Partition> draft = new ArrayList<Partition>();
            Partition newPar1 = new Partition(id++);

            newPar1.pCombinedState.add(Par.pCombinedState.get(0));
            newPar1.pStr = Par.pCombinedState.get(0).parStr;
            draft.add(newPar1);

            for (int p = 1; p < Par.pCombinedState.size(); p++) {
                CombinedState CS = Par.pCombinedState.get(p);

                //finding matching par 
                int flag = 0;
                for (int s = 0; s < draft.size(); s++) {

                    Partition parComp = draft.get(s);

                    //insert to existing partition
                    if (parComp.pStr.equals(CS.parStr)) {
                        parComp.pCombinedState.add(CS);
                        flag = 1;

                    }

                }//end of draft loop 

                //insert to new partition
                if (flag == 0) {
                    Partition newPar = new Partition(id++);
                  
                    newPar.pCombinedState.add(CS);
                    newPar.pStr = CS.parStr;
                    draft.add(newPar);
                }

                //end of insert
            }//end of partition loop

            if (draft.size() == 1) {
                counter++;
                draft.get(0).isFinal = true;

            }

            for (Partition p : draft) {
                newLevel.add(p);
            }

        }//end of level loop

        if (counter != level.size()) {
            return minimizeLevel(newLevel);
        } else {
            return newLevel;
        }

    }//end of minimizeLevel function  

    static int getPartitionID(int nextStateID, ArrayList<Partition> level) {

        for (Partition par : level) {
            for (CombinedState comp : par.pCombinedState) {
                if (comp.id == nextStateID) {
                    return par.id;
                }

            }
        }

        return -1;

    }//end of getpartitionid function

    static void getStrings(Partition Par, ArrayList<Partition> level) {

        for (CombinedState CS : Par.pCombinedState) {
            CS.parStr = "";
            int cSize = DFaTable.dfaTable[0].length;

            for (int j = 0; j < cSize; j++) {

                int nextStateID = DFaTable.dfaTable[CS.id][j];
                int parNo = getPartitionID(nextStateID, level);

                CS.parStr = CS.parStr.concat(Integer.toString(parNo));

            }//end of columns loop

        }//end of get parStr loop 

    }//end of getStrings function    

}//end of class

