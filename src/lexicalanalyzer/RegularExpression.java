package lexicalanalyzer;

import java.util.Stack;

public class RegularExpression {
    
   
    String insert_concat(String regexp){
    String ret="";
    char c,c2;
    for( int i=0; i<regexp.length(); i++){
        c=regexp.charAt(i);
        if(i+1<regexp.length()){
            c2=regexp.charAt(i+1);
            ret+=c;
            if(c!='('&&c2!=')'&&c!='+'&&c2!='+'&&c2!='*' &&c!='|' &&c2!='|'){
                ret+='.';
            }
        }
    }
    ret+=regexp.charAt(regexp.length()-1);
          
    return ret;
}

    //detecting priorities of operators
    int priority(char c) {
        switch (c) {
            case '*':
                return 3;
            case '+':
                return 3;
            case '.':
                return 2;
            case '|':
                return 1;
            default:
                return 0;

        }
    }

    //converting form infix to postfix
    String regex_to_postfix(String regex) {
        String postfix = "";
        Stack operands = new Stack();
        char character;
        for (int i = 0; i < regex.length(); i++) {
            if (Character.isDigit(regex.charAt(i))) {
                postfix += regex.charAt(i);

            } else if (Character.isAlphabetic(regex.charAt(i))) {
                postfix += regex.charAt(i);

            } else if (regex.charAt(i) == '(') {
                operands.push(regex.charAt(i));

            } else if (regex.charAt(i) == ')') {
                while ((Character) operands.peek() != '(') {
                    postfix += (Character) operands.pop();

                }
                operands.pop();

            } else if (regex.charAt(i) == ' ') {
                postfix += regex.charAt(i);

            } else {
                while (!operands.isEmpty()) {
                    character = (Character) operands.peek();
                    if (priority(character) >= priority(regex.charAt(i))) {
                        postfix += (Character) operands.peek();
                        operands.pop();
                    } else {
                        break;
                    }
                }
                operands.push(regex.charAt(i));
            }
        }
        while (!operands.empty()) {
            postfix += " ";
            postfix += (Character) operands.peek();
            operands.pop();
        }

        return postfix;

    }

}
