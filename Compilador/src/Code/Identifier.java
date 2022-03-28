
package Code;

import java.util.ArrayList;

public class Identifier {
    
    public String structName = "GLOBAL";
    public ArrayList<Variable> words;
    
    public Identifier(){
        words = new ArrayList<>();
    }
    
    public void showIdentifiers(){
        System.out.println("* " + structName);
        for(int i = 0; i < words.size(); i++){
            System.out.println("    - " + words.get(i).type + " " + words.get(i).name);
        }
        System.out.println("-----------------------------");
    }
    
    public void addNew(String struct, Variable idf){
        structName = struct;
        words.add(idf);
    }
    
    public void addExist(Variable idf){
        words.add(idf);
    }
}
