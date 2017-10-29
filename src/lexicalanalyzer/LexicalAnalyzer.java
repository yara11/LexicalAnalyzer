/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexicalanalyzer;

import java.util.Arrays;

/**
 *
 * @author User
 */
public class LexicalAnalyzer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        RegularExpression regular = new RegularExpression();
       String postfix= regular.regex_to_postfix("a|b");
      System.out.println(postfix);
      NfaCreation n = new NfaCreation();
     // String []splitted  =n.split_input(postfix);
      ///System.out.println(Arrays.toString(splitted));
      n.buildfNfa(postfix);
      Nfa.printGraph();
    }
    
}
