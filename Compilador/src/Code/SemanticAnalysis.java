
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
            //se separa el scope de las variables, global o de una funcion
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
    //la comprobacion que tenga un i+numero mas bajo siempre va hasta arriba, y conforme mas grande el numero, mas abajo
    public void structAnalysis(Production p, ArrayList<ErrorLSSL> errors, String struct){
        ArrayList<Token> tokens = p.getTokens();
        for(int i = 0; i < tokens.size(); i++){
            Token t = tokens.get(i);  //Token actual
            //System.out.println("Simbolo: " + t.getLexeme());
            //System.out.println("NombreToken: " + t.getLexicalComp());

            //Variables int
            //declaracion simple   
            if(t.getLexeme().equals("int"))
            {
                //declaracion simple
                if(tokens.get(i+1).getLexicalComp().equals("IDENTIFICADOR") && tokens.get(i+2).getLexicalComp().equals("PUNTO_COMA")){
                    addIdentifier(struct, tokens.get(i+1).getLexeme());
                }
                //declaracion con asignacion
                else if(tokens.get(i+2).getLexeme().equals("=") && tokens.get(i+4).getLexeme().equals(";"))
                {
                    if(tokens.get(i+3).getLexicalComp().equals("NUMERO") || tokens.get(i+3).getLexicalComp().equals("IDENTIFICADOR"))
                    {
                        addIdentifier(struct, tokens.get(i+1).getLexeme());
                    }
                    else
                    {
                        errors.add(new ErrorLSSL(1, " --- Error Semantico({}): Valor no compatible con el tipo de dato  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                    }
                }
                //declaracion y asignacion con operacion aritmetica
                else if(tokens.get(i+2).getLexeme().equals("=") && tokens.get(i+4).getLexicalComp().equals("OP_ARIT") && tokens.get(i+6).getLexeme().equals(";"))
                {
                    if(!tokens.get(i+3).getLexicalComp().equals("NUMERO"))
                    {
                        if(!tokens.get(i+3).getLexicalComp().equals("IDENTIFICADOR"))
                        {
                          errors.add(new ErrorLSSL(2, " --- Error Semantico({}): El primer operando no es un tipo de dato valido  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));  
                        }
                    }
                    else if(!tokens.get(i+5).getLexicalComp().equals("NUMERO"))
                    {
                        if(!tokens.get(i+5).getLexicalComp().equals("IDENTIFICADOR"))
                        {
                          errors.add(new ErrorLSSL(3, " --- Error Semantico({}): El segundo operando no es un tipo de dato valido  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));  
                        }
                    }
                    else
                    {
                        addIdentifier(struct, tokens.get(i+1).getLexeme());
                    }
                }
            }
            /*
            //Variables char
            //declaracion con asignacion
            if(t.getLexeme().equals("char") && tokens.get(i+2).getLexicalComp().equals("ASIGNACION")){
                //correcto
                if(tokens.get(i+4).getLexicalComp().equals("IDENTIFICADOR") && tokens.get(i+4).getLexeme().length() == 1){
                    addIdentifier(struct, tokens.get(i+1).getLexeme());
                //tipo de dato incorrecto
                }else{
                    errors.add(new ErrorLSSL(3, " --- Error Semantico({}): Valor no compatible con el tipo de dato  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                }
            //declaracion con concatenacion
            }else if(t.getLexeme().equals("char") && tokens.get(i+6).getLexeme().equals("+")){
                //correcta
                if(tokens.get(i+4).getLexicalComp().equals("IDENTIFICADOR") && tokens.get(i+8).getLexicalComp().equals("IDENTIFICADOR") && tokens.get(i+4).getLexeme().length() == 1 && tokens.get(i+8).getLexeme().length() == 1){
                    addIdentifier(struct, tokens.get(i+1).getLexeme());
                //uno de los operadores es un tipo de dato erroneo
                }else{
                    errors.add(new ErrorLSSL(3, " --- Error Semantico({}): Valor no compatible con el tipo de dato  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                }
            //declaracion simple
            }else if(t.getLexeme().equals("char") && tokens.get(i+1).getLexicalComp().equals("IDENTIFICADOR")){
                addIdentifier(struct, tokens.get(i+1).getLexeme());
            }
            
            //variables long
            //declaracion y asignacion
            if(t.getLexeme().equals("long") && tokens.get(i+2).getLexicalComp().equals("ASIGNACION")){
                //correcta
                if(tokens.get(i+3).getLexicalComp().equals("NUMERO")){
                    addIdentifier(struct, tokens.get(i+1).getLexeme()); 
                //tipo de dato incorrecto
                }else{
                    errors.add(new ErrorLSSL(1, " --- Error Semantico({}): Valor no compatible con el tipo de dato  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                }
            //asignacion de una operacion aritmetica
            }else if(t.getLexeme().equals("long") && tokens.get(i+4).getLexicalComp().equals("OP_ARIT")){
                //correcta
                if(tokens.get(i+3).getLexicalComp().equals("NUMERO") && tokens.get(i+5).getLexicalComp().equals("NUMERO")){
                    addIdentifier(struct, tokens.get(i+1).getLexeme()); 
                //tipo de dato erroneo
                }else{
                    errors.add(new ErrorLSSL(11, " --- Error Semantico({}): Operador no compatible con el tipo de dato  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                }
            }
            //declaracion
            else if(t.getLexeme().equals("long") && tokens.get(i+1).getLexicalComp().equals("IDENTIFICADOR")){
                addIdentifier(struct, tokens.get(i+1).getLexeme());
            }
            
            //variable float
            //declaracion y asignacion
            if(t.getLexeme().equals("float") && tokens.get(i+2).getLexicalComp().equals("ASIGNACION")){
                //correcta
                if(tokens.get(i+3).getLexicalComp().equals("NUMERO")){
                    addIdentifier(struct, tokens.get(i+1).getLexeme()); 
                //tipo de dato incorrecto
                }else{
                    errors.add(new ErrorLSSL(1, " --- Error Semantico({}): Valor no compatible con el tipo de dato  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                }
            //asignacion de una operacion aritmetica
            }else if(t.getLexeme().equals("float") && tokens.get(i+4).getLexicalComp().equals("OP_ARIT")){
                //correcta
                if(tokens.get(i+3).getLexicalComp().equals("NUMERO") && tokens.get(i+5).getLexicalComp().equals("NUMERO")){
                    addIdentifier(struct, tokens.get(i+1).getLexeme()); 
                //tipo de dato erroneo
                }else{
                    errors.add(new ErrorLSSL(11, " --- Error Semantico({}): Operador no compatible con el tipo de dato  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                }
            }
            //declaracion
            else if(t.getLexeme().equals("float") && tokens.get(i+1).getLexicalComp().equals("IDENTIFICADOR")){
                addIdentifier(struct, tokens.get(i+1).getLexeme());
            }
            
            //variable double
            //declaracion y asignacion
            if(t.getLexeme().equals("double") && tokens.get(i+2).getLexicalComp().equals("ASIGNACION")){
                //correcta
                if(tokens.get(i+3).getLexicalComp().equals("NUMERO")){
                    addIdentifier(struct, tokens.get(i+1).getLexeme()); 
                //tipo de dato incorrecto
                }else{
                    errors.add(new ErrorLSSL(1, " --- Error Semantico({}): Valor no compatible con el tipo de dato  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                }
            //asignacion de una operacion aritmetica
            }else if(t.getLexeme().equals("double") && tokens.get(i+4).getLexicalComp().equals("OP_ARIT")){
                //correcta
                if(tokens.get(i+3).getLexicalComp().equals("NUMERO") && tokens.get(i+5).getLexicalComp().equals("NUMERO")){
                    addIdentifier(struct, tokens.get(i+1).getLexeme()); 
                //tipo de dato erroneo
                }else{
                    errors.add(new ErrorLSSL(11, " --- Error Semantico({}): Operador no compatible con el tipo de dato  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                }
            }
            //declaracion
            else if(t.getLexeme().equals("double") && tokens.get(i+1).getLexicalComp().equals("IDENTIFICADOR")){
                addIdentifier(struct, tokens.get(i+1).getLexeme());
            }
            
            
            //Variables int
            //declaracion y asignacion
            if(t.getLexeme().equals("int") && tokens.get(i+2).getLexicalComp().equals("ASIGNACION")){
                //correcta
                if(tokens.get(i+3).getLexicalComp().equals("NUMERO")){
                    addIdentifier(struct, tokens.get(i+1).getLexeme()); 
                //tipo de dato incorrecto
                }else{
                    errors.add(new ErrorLSSL(1, " --- Error Semantico({}): Valor no compatible con el tipo de dato  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                }
            //asignacion de una operacion aritmetica
            }else if(t.getLexeme().equals("int") && tokens.get(i+4).getLexicalComp().equals("OP_ARIT")){
                //correcta
                if(tokens.get(i+3).getLexicalComp().equals("NUMERO") && tokens.get(i+5).getLexicalComp().equals("NUMERO")){
                    addIdentifier(struct, tokens.get(i+1).getLexeme()); 
                //tipo de dato erroneo
                }else{
                    errors.add(new ErrorLSSL(11, " --- Error Semantico({}): Operador no compatible con el tipo de dato  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                }
            }
            //declaracion
            else if(t.getLexeme().equals("int") && tokens.get(i+1).getLexicalComp().equals("IDENTIFICADOR")){
                addIdentifier(struct, tokens.get(i+1).getLexeme());
            }
            
            //Variables string
            //declaracion con asignacion
            if(t.getLexeme().equals("string") && tokens.get(i+2).getLexicalComp().equals("ASIGNACION")){
                //correcto
                if(tokens.get(i+4).getLexicalComp().equals("IDENTIFICADOR")){
                    addIdentifier(struct, tokens.get(i+1).getLexeme()); 
                }else{
                    errors.add(new ErrorLSSL(2, " --- Error Semantico({}): Valor no compatible con el tipo de dato  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                }
            //declaracion con concatenacion
            }else if(t.getLexeme().equals("string") && tokens.get(i+6).getLexeme().equals("+")){
                //correcta
                if(tokens.get(i+4).getLexicalComp().equals("IDENTIFICADOR") && tokens.get(i+8).getLexicalComp().equals("IDENTIFICADOR")){
                    addIdentifier(struct, tokens.get(i+1).getLexeme());
                //uno de los operadores es un tipo de dato erroneo
                }else{
                    errors.add(new ErrorLSSL(3, " --- Error Semantico({}): Valor no compatible con el tipo de dato  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                }
            //declaracion simple
            }else if(t.getLexeme().equals("string") && tokens.get(i+1).getLexicalComp().equals("IDENTIFICADOR")){
                addIdentifier(struct, tokens.get(i+1).getLexeme());
            }
            */
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
    
    //identifica que variable pertenece a que funcion, o si es global
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
