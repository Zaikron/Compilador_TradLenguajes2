
package Code;

import java.util.ArrayList;

public class Identifier {
    
    public String structName = "GLOBAL";
    public String type;
    public ArrayList<Variable> words;
    public ArrayList<Variable> params;
    
    public Identifier(){
        words = new ArrayList<>();
        params = new ArrayList<>();
    }
    
    public void showIdentifiers(){
        System.out.println("* " + structName);
        System.out.print("* PARAMENTROS: ");
        for(int p = 0; p < params.size(); p++){
            System.out.print(params.get(p).type + " " + params.get(p).name + ", ");
        }
        System.out.println("");
        for(int i = 0; i < words.size(); i++){
            System.out.println("    - " + words.get(i).type + " " + words.get(i).name + " " +words.get(i).saved);
        }
        System.out.println("-----------------------------");
    }
    
    //Agregar una variable y funcion nuevas
    public void addNew(String struct, Variable idf){
        structName = struct;
        words.add(idf);
    }
    //Agregar solo una variable nueva en una funcion existente
    public void addExist(Variable idf){
        words.add(idf);
    }
    //Agregar un parametro
    public void addParam(Variable idf){
        params.add(idf);
    }
}
