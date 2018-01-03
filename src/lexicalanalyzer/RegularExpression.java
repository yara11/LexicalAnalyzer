package lexicalanalyzer;

import java.util.Stack;

public class RegularExpression {

    String insert_concat(String regexp) {
        if(regexp.length()==1)
            return regexp;
        String ret = "";
        char c, c2;
        for (int i = 0; i < regexp.length(); i++) {
            c = regexp.charAt(i);
            if (i + 1 < regexp.length()) {
                c2 = regexp.charAt(i + 1);
                ret += c;
                if (c != '(' && c2 != ')' && c != '+' && c2 != '+' && c2 != '*' && c != '|' && c2 != '|') {
                    ret += '.';
                }
            }
        }
        ret += regexp.charAt(regexp.length() - 1);

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
        int flag=0;
        Stack operands = new Stack();
        char character;
        if(regex.length()==1)
            return regex;
        for (int i = 0; i < regex.length(); i++) {
            char c = regex.charAt(i);
            if (Character.isDigit(regex.charAt(i))) {
                postfix += regex.charAt(i);

            } else if (Character.isAlphabetic(regex.charAt(i))) {
                postfix += regex.charAt(i);

            } else if ((c == '<' || c == '>' || c == '=' || c == '!' || c == '{' || c == '}' || c == ';' || c == ',' || c == '/' || c == '-')) {
                postfix += regex.charAt(i);

            }  else if (regex.charAt(i) == '(') {
                operands.push(regex.charAt(i));

            } else if (regex.charAt(i) == ')') {
                while ((Character) operands.peek() != '(') {
                    postfix += (Character) operands.pop();

                }
                operands.pop();

            } else if (regex.charAt(i) == ' ') {
                postfix += regex.charAt(i);

            } else {
                 if (c == '*') {
                if (i == 0) {
                    postfix += regex.charAt(i);
                    flag=1;
                } else if (regex.charAt(i - 1) == '|') {
                    postfix += regex.charAt(i);
                    flag=1;
                }
            } else if (c == '+') {
                if (i == 0) {
                    postfix += regex.charAt(i);
                    flag=1;
                } else if (regex.charAt(i - 1) == '|') {
                    postfix += regex.charAt(i);
                    flag=1;
                }
            }
                while (!operands.isEmpty()&&flag==0) {
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
            flag=0;
        }
        while (!operands.empty()) {
           
            postfix += (Character) operands.peek();
            operands.pop();
        }

        return postfix;

    }

}
