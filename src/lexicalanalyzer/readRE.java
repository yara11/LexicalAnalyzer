package lexicalanalyzer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class readRE {

    public static HashMap<String, String> RE = new HashMap<>();
    public static HashMap<String, String> RD = new HashMap<>();
    public static String all = "a|b|c|d|e|f|g|h|i|j|k|l|m|n|o|p|q|r|s|t|u|v|u|x|y|z|A|B|C|D|E|F|G|H|I|J|K|L|M|N|O|B|K|R|S|T|Y|V|W|X|Y|Z|0|1|2|3|4|5|6|7|8|9";

    public static void read() {
        String fileName = "input.txt";
        String line = null;

        try {

            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String[] split = new String[50];

            while ((line = bufferedReader.readLine()) != null) {
                
                 //Keywords
                if (line.charAt(0) == '{') {

                   
                        line=line.replace("{","");
                        line=line.replace("}","");
                    

                    String[] arrKeywords;

                    arrKeywords = line.split(" ");

                    for (int g = 0; g < arrKeywords.length; g++) {
                        RE.put(arrKeywords[g], arrKeywords[g]);

                    }

                }//end of Keywords
                
                //punc
               else if (line.charAt(0) == '[') {

                   
                        line=line.replace("[","");
                        line=line.replace("]","");
                    

                    String[] arrKeywords;

                    arrKeywords = line.split(" ");

                    for (int g = 0; g < arrKeywords.length; g++) {
                        if(arrKeywords[g].charAt(0)=='\\')
                          
                       // RE.put(arrKeywords[g], arrKeywords[g]);
 RE.put( arrKeywords[g].replace("\\",""),  arrKeywords[g].replace("\\",""));
                        
                        else {
                            
                            RE.put( arrKeywords[g],  arrKeywords[g]);
                        }
                        
                    }

                }//end of punc
                
          else {         
                //line without space
                String linews = line.trim().replaceAll(" ", "");

                // regular definitions
                int equal = -1;
                if (linews.contains("=")) {

                    for (int i = 0; i < linews.length(); i++) {
                        if (linews.charAt(i) == '=') {
                            equal = i;
                        }
                    }

                    if (linews.charAt(equal - 1) != '\\' && linews.charAt(equal - 1) != ':') {
                        split = linews.split("=");

                        if (!split[1].equals(" ")) {
                            RD.put(split[0], split[1]);
                        }

                    }

                }//end of definitions

                // regular expression
                int colon = -1;
                if (linews.contains(":")) {

                    for (int i = 0; i < linews.length(); i++) {
                        if (linews.charAt(i) == ':') {
                            colon = i;
                        }
                    }

                    if (linews.charAt(colon - 1) != '\\') {
                        split = linews.split(":");
                        RE.put(split[0], split[1]);

                    }

                }//end of experssions
                
               

                
                
                
                   
                
                }   
               

            }//end of while 

            bufferedReader.close();

        }//end of try
        catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        for (String key : RD.keySet()) {
            String def = RD.get(key);

            if (def.contains("-")) {
                int dash = -1;

                for (int i = 0; i < def.length(); i++) {
                    if (def.charAt(i) == '-') {
                        dash = i;

                        if (def.charAt(dash - 1) != '\\') {
                            int indexb = -1;
                            int indexe = -1;

                            for (int j = 0; j < all.length(); j++) {

                                if (all.charAt(j) == def.charAt(dash - 1)) {
                                    indexb = j;
                                }

                                if (all.charAt(j) == def.charAt(dash + 1)) {
                                    indexe = j;
                                }

                            }
                            String req = all.substring(indexb, indexe + 1);
                            String toreplace = def.charAt(dash - 1) + "-" + def.charAt(dash + 1);
                            def = def.replace(toreplace, req);
                            RD.put(key, def);

                        }

                    }

                }

            }

        }
        for (String key1 : RD.keySet()) {
            for (String key2 : RD.keySet()) {
                if (RD.get(key2).contains(key1)) {
                    RD.put(key2, RD.get(key2).replace(key1, "(" + RD.get(key1) + ")"));
                }

            }
        }

        for (String key3 : RD.keySet()) {
            for (String key4 : RE.keySet()) {
                if (RE.get(key4).contains(key3)) {
                    RE.put(key4, RE.get(key4).replace(key3, "(" + RD.get(key3) + ")"));
                }

            }
        }

    }//end of read

    public static void printReadFile() {

        read();

//        System.out.println("definitions");
//        for (String key : RD.keySet()) {
//            System.out.println("key: " + key + " and value: " + RD.get(key));
//        }
//        System.out.println("experssions");
//        for (String key : RE.keySet()) {
//            System.out.println("key: " + key + "  and value: " + RE.get(key));
//
//        }

    }

}
