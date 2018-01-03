
package lexicalanalyzer;
import java.util.HashMap;



public class NfaInput {
    
    
   public static void nfaInput(){
     for (String key : readRE.RE.keySet()) {
       readRE.RE.put(key,readRE.RE.get(key).replace("\\",""));
       
     }
     
//          System.out.println("exp without slash ");
//        for (String key : readRE.RE.keySet()) {
//            System.out.println("key: " + key + " and value: " + readRE.RE.get(key));
//        }
     
     
}
    
}
