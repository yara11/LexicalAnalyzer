/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexicalanalyzer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import static lexicalanalyzer.Validation.stack_blocks;

/**
 *
 * @author yomnabarakat
 */
public class Tokens {
    static ArrayList<String> acceptedTokens=new ArrayList<String>();
    public static void read() throws FileNotFoundException, IOException {
        String fileName = "tokens.txt";
        String line = null;
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String[] split = new String[50];
            while ((line = bufferedReader.readLine()) != null) {
                 split = line.split("\\s+");
                 for(String input: split){
                     System.out.println(input);
                     getTokens(input);
                 }
                
                }
     
     }
    public static void getTokens(String input){
       Validation.validate(input);
       int i=0;
       while(!Validation.queue_blocks.isEmpty()){
            System.out.println("This is the pattern:"+Validation.getPriority());
            acceptedTokens.add(Validation.getPriority());
            Validation.queue_blocks.poll();
       }
    
     Validation.stack_blocks.clear();
     Validation.queue_blocks.clear();
    }
      static void printOutput(){
          System.out.println("Output is:");
          for(String token:acceptedTokens){
              System.out.println(token);
          }
      }
}
