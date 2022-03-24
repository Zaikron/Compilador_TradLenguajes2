
package Code;

import compilerTools.ErrorLSSL;
import compilerTools.Production;
import compilerTools.Token;
import java.util.ArrayList;

public class SemanticAnalysis {
    
    ArrayList<Identifier> identifiers;
    
    public SemanticAnalysis(){
        identifiers = new ArrayList<>();
    }
    
    public void analysis(ArrayList<Production> productions, ArrayList<ErrorLSSL> errors){
        String struct = "GLOBAL";
        for(int i = 0; i < productions.size(); i++){
            Production p = productions.get(i);      
            
            if(p.lexicalCompRank(0).equals("MAIN")){
                struct = "MAIN";
                structAnalysis(p, errors, struct);
            }else if(p.lexicalCompRank(0).equals("GATO") && p.lexicalCompRank(2).equals("IDENTIFICADOR")){
                //struct = "FUNCTION";
                struct = p.lexemeRank(2);
                structAnalysis(p, errors, struct);
            }
            
        }
        
        showStructs();
    }
    
    public void structAnalysis(Production p, ArrayList<ErrorLSSL> errors, String struct){
        ArrayList<Token> tokens = p.getTokens();
        for(int i = 0; i < tokens.size(); i++){
            Token t = tokens.get(i);  //Token actual
            //System.out.println("Simbolo: " + t.getLexeme());
            //System.out.println("NombreToken: " + t.getLexicalComp());

            //Variables int
            if(t.getLexeme().equals("int") && tokens.get(i+1).getLexicalComp().equals("ASIGNACION")){
                if(tokens.get(i+3).getLexicalComp().equals("NUMERO")){
                    addIdentifier(struct, tokens.get(i+1).getLexeme()); //Se agrega el identificador. En i es int y en i+1 es el nombre de la variable
                }else{
                    errors.add(new ErrorLSSL(1, " --- Error Semantico({}): Valor no compatible con el tipo de dato  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                }
            }else if(t.getLexeme().equals("int") && tokens.get(i+1).getLexicalComp().equals("IDENTIFICADOR")){
                addIdentifier(struct, tokens.get(i+1).getLexeme());
            }
            
            //Variables string
            if(t.getLexeme().equals("string") && tokens.get(i+2).getLexicalComp().equals("ASIGNACION")){
                if(tokens.get(i+4).getLexicalComp().equals("IDENTIFICADOR")){
                    addIdentifier(struct, tokens.get(i+1).getLexeme()); //Se agrega el identificador. En i es int y en i+1 es el nombre de la variable
                }else{
                    errors.add(new ErrorLSSL(2, " --- Error Semantico({}): Valor no compatible con el tipo de dato  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                }
            }else if(t.getLexeme().equals("string") && tokens.get(i+1).getLexicalComp().equals("IDENTIFICADOR")){
                addIdentifier(struct, tokens.get(i+1).getLexeme());
            }
            
            //Variables char
            if(t.getLexeme().equals("char") && tokens.get(i+2).getLexicalComp().equals("ASIGNACION")){
                if(tokens.get(i+4).getLexicalComp().equals("IDENTIFICADOR")){
                    addIdentifier(struct, tokens.get(i+1).getLexeme()); //Se agrega el identificador. En i es int y en i+1 es el nombre de la variable
                }else{
                    errors.add(new ErrorLSSL(3, " --- Error Semantico({}): Valor no compatible con el tipo de dato  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                }
            }else if(t.getLexeme().equals("char") && tokens.get(i+1).getLexicalComp().equals("IDENTIFICADOR")){
                addIdentifier(struct, tokens.get(i+1).getLexeme());
            }
            
            
            
            
        }
    }
    
    public void showStructs(){
        for(int i = 0; i < identifiers.size(); i++){
            identifiers.get(i).showIdentifiers();
        }
    }
    
    public int getIndexStruct(String struct){
        for(int i = 0; i < identifiers.size(); i++){
            if(identifiers.get(i).structName.equals(struct)){
                return i;
            }
        }
        return -1;
    }
    
    public void addIdentifier(String struct, String identifier){
        int index = getIndexStruct(struct);
        if(index != -1){
            identifiers.get(index).addExist(identifier);
        }else{
            Identifier idfs = new Identifier();
            idfs.addNew(struct, identifier);
            identifiers.add(idfs);
        } 
    }
    
}
