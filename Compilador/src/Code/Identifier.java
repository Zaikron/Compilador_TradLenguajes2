
package Code;

import java.util.ArrayList;

public class Identifier {
    
    String structName = "GLOBAL";
    ArrayList<String> words;
    
    public Identifier(){
        words = new ArrayList<>();
    }
    
    public void showIdentifiers(){
        System.out.println("* " + structName);
        for(int i = 0; i < words.size(); i++){
            System.out.println("    - " + words.get(i));
        }
        System.out.println("-----------------------------");
    }
    
    public void addNew(String struct, String idf){
        structName = struct;
        words.add(idf);
    }
    
    public void addExist(String idf){
        words.add(idf);
    }
}
