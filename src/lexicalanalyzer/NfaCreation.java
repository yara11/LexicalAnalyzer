/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexicalanalyzer;

import java.util.Stack;

/**
 *
 * @author yomnabarakat
 */
public class NfaCreation {

    int ID = 0;
    Stack <Nfa> nfaStack = new Stack<Nfa>();
   
    //Split the postfix at each space and insert them into an array of inputs.

//    public static String[] split_input(String string) {
//        String[] inputArray = string.split(" ");
//        if (inputArray.length ==1 ) {
//            throw new IllegalArgumentException();
//        }
//        return inputArray;
//
//    }
    
    Nfa buildfNfa(String postfix) {
        //String[] inputArray = split_input(postfix);
        for (int i = 0; i < postfix.length(); i++) {
            char input = postfix.charAt(i);
            if(isInput(input)) {
                nfaStack.push(Nfa.AddTransition(input));
            }
            else if(input == '.') {
                Nfa b = nfaStack.pop();
                Nfa a = nfaStack.pop();
                nfaStack.push(Concat(a, b));
            }
            else if(input == '*') {
                Nfa top = nfaStack.pop();
                nfaStack.push(Kleene(top));
            }
            else if(input == '+') {
                Nfa top = nfaStack.pop();
                nfaStack.push(Plus(top));
            }
            else if(input == '|') {
                Nfa b = nfaStack.pop();
                Nfa a = nfaStack.pop();
                nfaStack.push(Union(a, b));
            }
        }
        //a part is added here to get complete nfa
        Nfa completeNfa = nfaStack.pop();
        return completeNfa;
    }

    // ~ is epsilon
     public static Nfa Concat(Nfa a, Nfa b) {
         Nfa.connectStates(a.getEnd(), b.getStart(), '~');
         a.getEnd().setIsAccepting(false);
         Nfa ret = new Nfa(a.getStart(), b.getEnd());
         return ret;
     }
     
     public static Nfa Kleene(Nfa a) {
         State new_start = new State(Nfa.last_id++, false);
         //
         Nfa.states.add(new_start);
         //
         State new_end = new State(Nfa.last_id++, true);
         //
         Nfa.states.add(new_end);
         //
         a.getEnd().setIsAccepting(false);
         Nfa.connectStates(new_start, a.getStart(), '~');
         Nfa.connectStates(a.getEnd(), new_end, '~');
         Nfa.connectStates(new_start, new_end, '~');
         Nfa.connectStates(new_end, new_start, '~');
         Nfa ret = new Nfa(new_start, new_end);
         return ret;
     }
     public static Nfa Plus(Nfa a) {
         State new_start = new State(Nfa.last_id++, false);
         //
         Nfa.states.add(new_start);
         //
         State new_end = new State(Nfa.last_id++, true);
         //
         Nfa.states.add(new_end);
         //
         a.getEnd().setIsAccepting(false);
         Nfa.connectStates(new_start, a.getStart(), '~');
         Nfa.connectStates(a.getEnd(), new_end, '~');
         Nfa.connectStates(new_start, new_end, '~');
         Nfa ret = new Nfa(new_start, new_end);
         return ret;
     }
     public static Nfa Union(Nfa a, Nfa b) {
         State new_start = new State(Nfa.last_id++, false);
         //
         Nfa.states.add(new_start);
         //
         State new_end = new State(Nfa.last_id++, true);
         //
         Nfa.states.add(new_end);
         //
         a.getEnd().setIsAccepting(false);
         b.getEnd().setIsAccepting(false);
         Nfa.connectStates(new_start, a.getStart(), '~');
         Nfa.connectStates(new_start, b.getStart(), '~');
         Nfa.connectStates(a.getEnd(), new_end, '~');
         Nfa.connectStates(b.getEnd(), new_end, '~');
         Nfa ret = new Nfa(new_start, new_end);
         return ret;
     }
    boolean isInput(char c) {
        if (Character.isAlphabetic(c)) {
            return true;
        }

        if (Character.isDigit(c)) {
            return true;
        }
        return false;

    }
   

}
