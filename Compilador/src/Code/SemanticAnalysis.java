
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
        addIdentifier("MAIN", createVar("c", "char")); //Variable para pruebas
        
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
                    addIdentifier(struct, createVar(tokens.get(i+1).getLexeme(), t.getLexeme()));
                }
                //declaracion con asignacion
                else if(tokens.get(i+2).getLexeme().equals("=") && tokens.get(i+4).getLexeme().equals(";"))
                {
                    if(tokens.get(i+3).getLexicalComp().equals("NUMERO"))
                    {
                        addIdentifier(struct, createVar(tokens.get(i+1).getLexeme(), t.getLexeme()));
                    }
                    
                    /*** Comprobacion de un nombre de variable ***/
                    else if(tokens.get(i+3).getLexicalComp().equals("IDENTIFICADOR")){
                        //Comprobar existencia de la variable, si es falso entonces se puede agregar, no estara repetida
                        if(existIdentifier(struct, tokens.get(i+1).getLexeme()) == false){
                            //Comprobar existencia de la variable asignada, pues primero tuvo que ser declarada para poder asignarla
                            if(existIdentifier(struct, tokens.get(i+3).getLexeme()) == true){
                                //Comprobar si el tipo de dato de la variable asignada es correcto
                                if(isCorrectType(struct, tokens.get(i+3).getLexeme(), t.getLexeme()) == true){
                                    addIdentifier(struct, createVar(tokens.get(i+1).getLexeme(), t.getLexeme()));
                                }else{
                                    errors.add(new ErrorLSSL(5, " --- Error Semantico({}): La variable no es compatible con el tipo de dato  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                                }
                            }else{
                                errors.add(new ErrorLSSL(6, " --- Error Semantico({}): La variable asignada no existe  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                            }
                            
                        }else{
                            errors.add(new ErrorLSSL(7, " --- Error Semantico({}): La variable esta repetida  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                        }
                    }
                    /*** ***/
                    
                    else{
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
                        addIdentifier(struct, createVar(tokens.get(i+1).getLexeme(), t.getLexeme()));
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
    
    //Busqueda de la estructura (main o alguna funcion) para comprobar si existe, es ese caso devuelve el indice
    public int getIndexStruct(String struct){
        for(int i = 0; i < identifiers.size(); i++){
            if(identifiers.get(i).structName.equals(struct)){
                return i;
            }
        }
        return -1;
    }
    
    //Obtener una variable
    public Variable getVar(String struct, String name){
        int index = getIndexStruct(struct);
        System.out.println("i: " + index);
        ArrayList<Variable> idfs = identifiers.get(index).words;
        for(int i = 0; i < idfs.size(); i++){
            System.out.println("N: " + idfs.get(i).name + " " + name);
            if(idfs.get(i).name.equals(name)){
                return idfs.get(i);
            }
        }
        return null;
    }
    
    //identifica que variable pertenece a que funcion, o si es global
    public void addIdentifier(String struct, Variable identifier){
        int index = getIndexStruct(struct);
        if(index != -1){
            identifiers.get(index).addExist(identifier);
        }else{
            Identifier idfs = new Identifier();
            idfs.addNew(struct, identifier);
            identifiers.add(idfs);
        } 
    }
    
    public Variable createVar(String nom, String type){
        Variable v = new Variable(nom, type);
        return v;
    }
    
    //Comprobar si ya existe un nombre de variable
    public boolean existIdentifier(String struct, String name){
        int index = getIndexStruct(struct);
        ArrayList<Variable> idfs = identifiers.get(index).words;
        
        for(int i = 0; i < idfs.size(); i++){
            if(name.equals(idfs.get(i).name)){
                System.out.println("true");
                return true;
            }
        }
        return false;
    }
    
    //Comprobar que una asignacion este correcta en cuanto al tipo de dato, Ejemplo: int a = b;
    public boolean isCorrectType(String struct, String name, String type){
        Variable v = getVar(struct, name);
        
        if(v != null){
            System.out.println("V: " + v.type);
            System.out.println("v: " + type);
            if(v.type.equals(type)){
                return true;
            }
        }
        return false;
    }
    
    //Comprobar que en una asignacion los tipos de datos sean correctos, Ejemplo: a = b;
    public boolean isCorrectAsign(String struct, String name){
        Variable v1 = getVar(struct, name);
        Variable v2 = getVar(struct, name);
        
        if(v1 != null && v2 != null){
            if(v1.type.equals(v2.type)){
                return true;
            }
        }
        return false;
    }
    
}
