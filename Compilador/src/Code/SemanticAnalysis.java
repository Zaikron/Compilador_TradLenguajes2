
package Code;

import compilerTools.ErrorLSSL;
import compilerTools.Production;
import compilerTools.Token;
import java.util.ArrayList;
import javax.swing.JTextPane;

public class SemanticAnalysis {
    
    ArrayList<Identifier> identifiers;
    boolean headerIndicator = false;
    Registers regs;
    MNEMS mnems;
    
    String printText = "";
    int indexPrint = 0;
    
    public SemanticAnalysis(){
        identifiers = new ArrayList<>();
        regs = new Registers();
    }
    
    public void analysis(ArrayList<Production> productions, ArrayList<ErrorLSSL> errors, JTextPane textSintax){
        String struct = "GLOBAL";
        //addIdentifier("MAIN", createVar("c", "char","NULL")); //Variable para pruebas
        
        for(int i = 0; i < productions.size(); i++){
            Production p = productions.get(i);  
            //se separa el scope de las variables, global o de una funcion
            if(p.lexicalCompRank(0).equals("IMPORT")){
                if(p.lexemeRank(1).equals("BASIC")){
                    headerIndicator = true;
                }else{
                    errors.add(new ErrorLSSL(80, " --- Error Semantico({}): La libreria importada no existe  [Linea: "+p.getLine()+", Caracter: "+p.getColumn()+"]", p, true));
                }
            }
            
            if(p.lexicalCompRank(0).equals("MAIN")){
                if(headerIndicator == true){
                    struct = "MAIN";
                    addIdentifier(struct, createVar("NULL", "NULL","NULL"));
                    identifiers.get(getIndexStruct(struct)).type = "void";
                    structAnalysis(p, errors, struct);
                }else{
                    errors.add(new ErrorLSSL(81, " --- Error Semantico({}): No se ha importado la libreria BASIC  [Linea: "+p.getLine()+", Caracter: "+p.getColumn()+"]", p, true));
                }
                
            }else if(p.lexicalCompRank(0).equals("GATO") && p.lexicalCompRank(2).equals("IDENTIFICADOR")){
                if(headerIndicator == true){
                    struct = p.lexemeRank(2);
                    if(existFunction(struct) == false){
                        addIdentifier(struct, createVar("NULL", "NULL","NULL"));
                        identifiers.get(getIndexStruct(struct)).type = p.lexemeRank(1);
                        structAnalysis(p, errors, struct);
                    }else{
                        errors.add(new ErrorLSSL(81, " --- Error Semantico({}): El nombre de la funcion "+struct+" ya existe  [Linea: "+p.getLine()+", Caracter: "+p.getColumn()+"]", p, true));
                    }
                }else{
                   errors.add(new ErrorLSSL(81, " --- Error Semantico({}): No se ha importado la libreria BASIC  [Linea: "+p.getLine()+", Caracter: "+p.getColumn()+"]", p, true)); 
                }
            }
        }
        showStructs();
        regs.showRegisters();
        textSintax.setText(textSintax.getText() + "\n" + printText);
    }
    //la comprobacion que tenga un i+numero mas bajo siempre va hasta arriba, y conforme mas grande el numero, mas abajo
    public void structAnalysis(Production p, ArrayList<ErrorLSSL> errors, String struct){
        ArrayList<Token> tokens = p.getTokens();
        for(int i = 0; i < tokens.size(); i++){
            Token t = tokens.get(i);  //Token actual
            
            
            
            //Ensamblador
            if(t.getLexicalComp().equals("ASM")){
                mnems = new MNEMS(tokens.get(i+3).getLexeme(), tokens.get(i+4).getLexeme(), tokens.get(i+6).getLexeme(), identifiers);
                mnems.checkMNEMS(regs, tokens, i, errors, p, struct);
            }
            
            //Print
            if(t.getLexicalComp().equals("PRINT")){
                String print = getPrintString(tokens, i, struct, errors, p);
                System.out.println("-----> " + print);
                printText += (print + "\n");
                i = indexPrint;
            }
            
            
            //Comprobacion de que se retorne el tipo correcto en una funcion
            if(t.getLexicalComp().equals("RETURN")){
                if(tokens.get(i+1).getLexicalComp().equals("NUMERO")){
                    if(!identifiers.get(getIndexStruct(struct)).type.equals("int")){
                        errors.add(new ErrorLSSL(40, " --- Error Semantico({}): La funcion "+struct+" no esta retornando un valor correcto  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                    }
                }else if(tokens.get(i+1).getLexicalComp().equals("COMILLAS")){
                    if(!identifiers.get(getIndexStruct(struct)).type.equals("string")){
                        errors.add(new ErrorLSSL(41, " --- Error Semantico({}): La funcion "+struct+" no esta retornando un valor correcto  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                    }
                }else if(tokens.get(i+1).getLexicalComp().equals("C_SIMPLE")){
                    if(!identifiers.get(getIndexStruct(struct)).type.equals("char")){
                        errors.add(new ErrorLSSL(42, " --- Error Semantico({}): La funcion "+struct+" no esta retornando un valor correcto  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                    }
                }else if(tokens.get(i+1).getLexicalComp().equals("IDENTIFICADOR")){
                    if(existIdentifier(struct, tokens.get(i+1).getLexeme()) == true){
                        if(!getVar(struct, tokens.get(i+1).getLexeme()).type.equals(identifiers.get(getIndexStruct(struct)).type)){
                                errors.add(new ErrorLSSL(43, " --- Error Semantico({}): La funcion "+struct+" no esta retornando un valor correcto  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                            }
                        /*if(!getVar(struct, tokens.get(i+1).getLexeme()).saved.equals("NULL")){
                            
                        }else{
                            errors.add(new ErrorLSSL(43, " --- Error Semantico({}): La variable retornada en la funcion "+struct+" no esta definida  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                        }*/
                    }else{
                        errors.add(new ErrorLSSL(41, " --- Error Semantico({}): La variable retornada en la funcion "+struct+" no existe  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                    }
                }
            }
            
            //Para revisar que las variables sean correctas en una llamada de la funcion
            if(t.getLexicalComp().equals("IDENTIFICADOR") && tokens.get(i+1).getLexicalComp().equals("PARENTESIS_A")){
                if(!tokens.get(i+2).getLexicalComp().equals("SIMB_SENT")){
                    if(existFunction(t.getLexeme()) == true){
                        if(checkFunctionCall(tokens, i, struct, t.getLexeme(), errors, p) == false){
                            errors.add(new ErrorLSSL(31, " --- Error Semantico({}): Error en los valores de los parametros  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                        }
                    }else{
                        errors.add(new ErrorLSSL(32, " --- Error Semantico({}): La funcion no ha sido declarada  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                    }
                }
            }
            
            //Para guardar las variables en los parametros
            if(t.getLexicalComp().equals("SIMB_SENT")){
                if(tokens.get(i+1).getLexicalComp().equals("INT") | tokens.get(i+1).getLexicalComp().equals("FLOAT") 
                        | tokens.get(i+1).getLexicalComp().equals("CHAR") | tokens.get(i+1).getLexicalComp().equals("CADENA")){
                    
                    Variable v = createVar(tokens.get(i+2).getLexeme(), tokens.get(i+1).getLexeme(),"NULL");
                    addIdentifier(struct, v);
                    identifiers.get(getIndexStruct(struct)).addParam(v);
                }
            }
            
            //Para guardar todas las variables con declaracion simple, Ejemplo: int a; , float b , etc
            if(t.getLexicalComp().equals("INT") | t.getLexicalComp().equals("FLOAT") | t.getLexicalComp().equals("CHAR") | t.getLexicalComp().equals("CADENA")){
                if(tokens.get(i+1).getLexicalComp().equals("IDENTIFICADOR") && tokens.get(i+2).getLexicalComp().equals("PUNTO_COMA")){
                    //Comprobar existencia de la variable, si es falso entonces se puede agregar, no estara repetida
                    if(existIdentifier(struct, tokens.get(i+1).getLexeme()) == false){
                        addIdentifier(struct, createVar(tokens.get(i+1).getLexeme(), t.getLexeme(),"NULL"));
                    }
                    else{
                        errors.add(new ErrorLSSL(7, " --- Error Semantico({}): La variable esta repetida  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                    }
                }
            }
            
            int indexStruct = 0;
            int indexVar = 0;
            //Asignaciones, Ejemplo: a = b;
            if(t.getLexicalComp().equals("IDENTIFICADOR") && tokens.get(i+1).getLexicalComp().equals("ASIGNACION") && 
            (!tokens.get(i-1).getLexicalComp().equals("INT") && !tokens.get(i-1).getLexicalComp().equals("FLOAT") && 
            !tokens.get(i-1).getLexicalComp().equals("CADENA") && !tokens.get(i-1).getLexicalComp().equals("CHAR"))){
                if(tokens.get(i+2).getLexicalComp().equals("IDENTIFICADOR")){
                    //comprueba que las variables existan
                    if(existIdentifier(struct, t.getLexeme()) && existIdentifier(struct, tokens.get(i+2).getLexeme())){
                        if(isCorrectAsign(struct, t.getLexeme(), tokens.get(i+2).getLexeme()) == false){
                            errors.add(new ErrorLSSL(10, " --- Error Semantico({}): El tipo de dato de las variables no coincide  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                        }else{
                            if(getVar(struct, tokens.get(i+2).getLexeme()).saved.equals("NULL")){
                                errors.add(new ErrorLSSL(12, " --- Error Semantico({}): La variable "+tokens.get(i+2).getLexeme()+" es nula  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                            }else{
                                indexStruct = getIndexStruct(struct);
                                indexVar = getIndexVar(indexStruct, t.getLexeme());
                                identifiers.get(indexStruct).words.get(indexVar).saved = getVar(struct, tokens.get(i+2).getLexeme()).saved;
                            }
                        }
                    }else{
                        errors.add(new ErrorLSSL(11, " --- Error Semantico({}): La variable no a sido declarada  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                    }
                }else if(tokens.get(i+2).getLexicalComp().equals("NUMERO")){
                    if(existIdentifier(struct, t.getLexeme())){
                        if(getVar(struct, t.getLexeme()).type.equals("int") || getVar(struct, t.getLexeme()).type.equals("float")){
                            indexStruct = getIndexStruct(struct);
                            indexVar = getIndexVar(indexStruct, t.getLexeme());
                            identifiers.get(indexStruct).words.get(indexVar).saved = tokens.get(i+2).getLexeme();
                        }else{
                            errors.add(new ErrorLSSL(11, " --- Error Semantico({}): El valor no coincide con el tipo de dato  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                        }
                    }else{
                        errors.add(new ErrorLSSL(11, " --- Error Semantico({}): La variable no a sido declarada  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                    }
                }else if(tokens.get(i+2).getLexicalComp().equals("COMILLAS")){
                    if(existIdentifier(struct, t.getLexeme())){
                        if(getVar(struct, t.getLexeme()).type.equals("string")){
                            indexStruct = getIndexStruct(struct);
                            indexVar = getIndexVar(indexStruct, t.getLexeme());
                            identifiers.get(indexStruct).words.get(indexVar).saved = "\""+tokens.get(i+3).getLexeme()+"\"";
                        }else{
                            errors.add(new ErrorLSSL(11, " --- Error Semantico({}): El valor no coincide con el tipo de dato  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                        }
                    }else{
                        errors.add(new ErrorLSSL(11, " --- Error Semantico({}): La variable no a sido declarada  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                    }
                }else if(tokens.get(i+2).getLexicalComp().equals("C_SIMPLE")){
                    if(existIdentifier(struct, t.getLexeme())){
                        if(getVar(struct, t.getLexeme()).type.equals("char")){
                            indexStruct = getIndexStruct(struct);
                            indexVar = getIndexVar(indexStruct, t.getLexeme());
                            identifiers.get(indexStruct).words.get(indexVar).saved = "\""+tokens.get(i+3).getLexeme()+"\"";
                        }else{
                            errors.add(new ErrorLSSL(11, " --- Error Semantico({}): El valor no coincide con el tipo de dato  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                        }
                    }else{
                        errors.add(new ErrorLSSL(11, " --- Error Semantico({}): La variable no a sido declarada  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                    }
                }
            }

            //Variables int y float
            //declaracion simple   
            if(t.getLexeme().equals("int") || t.getLexeme().equals("float"))
            {
                //declaracion con asignacion
                if(tokens.get(i+2).getLexeme().equals("=") && tokens.get(i+4).getLexeme().equals(";"))
                {
                    //se asigna un numero
                    if(tokens.get(i+3).getLexicalComp().equals("NUMERO"))
                    {
                        //Comprobar existencia de la variable, si es falso entonces se puede agregar, no estara repetida
                        if(existIdentifier(struct, tokens.get(i+1).getLexeme()) == false){
                            addIdentifier(struct, createVar(tokens.get(i+1).getLexeme(), t.getLexeme(),tokens.get(i+3).getLexeme()));
                        }
                        else{
                            errors.add(new ErrorLSSL(7, " --- Error Semantico({}): La variable esta repetida  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                        }
                    }
                    
                    //se asigna una variable
                    /*** Comprobacion de un nombre de variable ***/
                    else if(tokens.get(i+3).getLexicalComp().equals("IDENTIFICADOR") && tokens.get(i+2).getLexicalComp().equals("ASIGNACION")){
                        //Comprobar existencia de la variable, si es falso entonces se puede agregar, no estara repetida
                        if(existIdentifier(struct, tokens.get(i+1).getLexeme()) == false){
                            //Comprobar existencia de la variable asignada, pues primero tuvo que ser declarada para poder asignarla
                            if(existIdentifier(struct, tokens.get(i+3).getLexeme()) == true){
                                //Comprobar si el tipo de dato de la variable asignada es correcto
                                if(isCorrectType(struct, tokens.get(i+3).getLexeme(), t.getLexeme()) == true){
                                    if(getVar(struct, tokens.get(i+3).getLexeme()).saved.equals("NULL")){
                                        errors.add(new ErrorLSSL(12, " --- Error Semantico({}): La variable "+tokens.get(i+3).getLexeme()+" es nula  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                                    }else{
                                        addIdentifier(struct, createVar(tokens.get(i+1).getLexeme(), t.getLexeme(),tokens.get(i+3).getLexeme()));
                                    }
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
                                        
                    /*** Error general de tipo ***/
                    else{
                        errors.add(new ErrorLSSL(1, " --- Error Semantico({}): Valor no compatible con el tipo de dato  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                    }
                }
                
                //se asigna una operacion aritmetica
                //ambos operandos numero
                else if(tokens.get(i+3).getLexicalComp().equals("NUMERO") && tokens.get(i+4).getLexicalComp().equals("OP_ARIT") && tokens.get(i+5).getLexicalComp().equals("NUMERO")){
                    //Comprobar existencia de la variable, si es falso entonces se puede agregar, no estara repetida
                    if(existIdentifier(struct, tokens.get(i+1).getLexeme()) == false){
                        addIdentifier(struct, createVar(tokens.get(i+1).getLexeme(), t.getLexeme(),tokens.get(i+3).getLexeme()+"+"+tokens.get(i+5).getLexeme()));                            
                    }else{
                        errors.add(new ErrorLSSL(7, " --- Error Semantico({}): La variable esta repetida  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                    }
                }
                //primer operando numero, segundo variable
                else if(tokens.get(i+3).getLexicalComp().equals("NUMERO") && tokens.get(i+4).getLexicalComp().equals("OP_ARIT")){
                    //Comprobar existencia de la variable, si es falso entonces se puede agregar, no estara repetida
                    if(existIdentifier(struct, tokens.get(i+1).getLexeme()) == false){
                        //Comprobar existencia de la variable asignada, pues primero tuvo que ser declarada para poder asignarla
                        if(existIdentifier(struct, tokens.get(i+5).getLexeme()) == true){
                            //Comprobar si el tipo de dato de la variable asignada es correcto
                            if(isCorrectType(struct, tokens.get(i+5).getLexeme(), t.getLexeme()) == true){
                                addIdentifier(struct, createVar(tokens.get(i+1).getLexeme(), t.getLexeme(),tokens.get(i+3).getLexeme()+"+"+tokens.get(i+5).getLexeme()));
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

                //primer operando variable, segundo numero o variable
                else if(tokens.get(i+3).getLexicalComp().equals("IDENTIFICADOR") && tokens.get(i+4).getLexicalComp().equals("OP_ARIT")){
                    //Comprobar existencia de la variable, si es falso entonces se puede agregar, no estara repetida
                    if(existIdentifier(struct, tokens.get(i+1).getLexeme()) == false){
                        //Comprobar existencia de la variable asignada, pues primero tuvo que ser declarada para poder asignarla
                        if(existIdentifier(struct, tokens.get(i+3).getLexeme()) == true){
                            //Comprobar si el tipo de dato de la variable asignada es correcto
                            if(isCorrectType(struct, tokens.get(i+3).getLexeme(), t.getLexeme()) == true){
                                //si el segundo operando tambien es una variable
                                if(tokens.get(i+5).getLexicalComp().equals("IDENTIFICADOR"))
                                {

                                        //Comprobar existencia de la variable asignada, pues primero tuvo que ser declarada para poder asignarla
                                        if(existIdentifier(struct, tokens.get(i+5).getLexeme()) == true){
                                            //Comprobar si el tipo de dato de la variable asignada es correcto
                                            if(isCorrectType(struct, tokens.get(i+5).getLexeme(), t.getLexeme()) == true){
                                                addIdentifier(struct, createVar(tokens.get(i+1).getLexeme(), t.getLexeme(),tokens.get(i+3).getLexeme()+"+"+tokens.get(i+5).getLexeme()));
                                            }else{
                                                errors.add(new ErrorLSSL(5, " --- Error Semantico({}): La variable no es compatible con el tipo de dato  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                                            }
                                        }else{
                                            errors.add(new ErrorLSSL(6, " --- Error Semantico({}): La variable asignada no existe  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                                        }
                                }
                                //si el seugndo operando es un numero
                                else if(tokens.get(i+5).getLexicalComp().equals("NUMERO"))
                                {
                                   addIdentifier(struct, createVar(tokens.get(i+1).getLexeme(), t.getLexeme(),tokens.get(i+3).getLexeme()+"+"+tokens.get(i+5).getLexeme())); 
                                }
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
            }
            
            //variables char
            if(t.getLexeme().equals("char")){
                //declaracion con asignacion
                
                //asignacion tipo char a = 'a';
                if(tokens.get(i+2).getLexeme().equals("=") && tokens.get(i+3).getLexeme().equals("'"))
                {
                    //Comprobar existencia de la variable, si es falso entonces se puede agregar, no estara repetida
                    if(existIdentifier(struct, tokens.get(i+1).getLexeme()) == false){
                        if(tokens.get(i+2).getLexeme().equals("=") && tokens.get(i+6).getLexeme().equals(";")){
                            //comprobar que solo almacene 1 caracter
                            if(tokens.get(i+4).getLexeme().length() == 1)
                            {
                                addIdentifier(struct, createVar(tokens.get(i+1).getLexeme(), t.getLexeme(),"'"+tokens.get(i+4).getLexeme()+"'")); 
                            }
                            else
                            {
                                errors.add(new ErrorLSSL(20, " --- Error Semantico({}): Las variables char solo pueden almacenar 1 caracter  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                            }
                        }
                    }
                    else
                    {
                        errors.add(new ErrorLSSL(21, " --- Error Semantico({}): La variable ya existe  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                    }
                }
                
                //asignacion tipo char a = b ;
                //se asigna una variable
                /*** Comprobacion de un nombre de variable ***/
                else if(tokens.get(i+3).getLexicalComp().equals("IDENTIFICADOR") && tokens.get(i+2).getLexicalComp().equals("ASIGNACION")){
                    //Comprobar existencia de la variable, si es falso entonces se puede agregar, no estara repetida
                    System.out.println("--------");
                    if(existIdentifier(struct, tokens.get(i+1).getLexeme()) == false){
                        //Comprobar existencia de la variable asignada, pues primero tuvo que ser declarada para poder asignarla
                        if(existIdentifier(struct, tokens.get(i+3).getLexeme()) == true){
                            //Comprobar si el tipo de dato de la variable asignada es correcto
                            if(isCorrectType(struct, tokens.get(i+3).getLexeme(), t.getLexeme()) == true){
                                if(getVar(struct, tokens.get(i+3).getLexeme()).saved.equals("NULL")){
                                    errors.add(new ErrorLSSL(12, " --- Error Semantico({}): La variable "+tokens.get(i+3).getLexeme()+" es nula  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                                }else{
                                    addIdentifier(struct, createVar(tokens.get(i+1).getLexeme(), t.getLexeme(),tokens.get(i+3).getLexeme()));
                                }
                            }else{
                                errors.add(new ErrorLSSL(22, " --- Error Semantico({}): La variable no es compatible con el tipo de dato  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                            }
                        }else{
                            errors.add(new ErrorLSSL(23, " --- Error Semantico({}): La variable asignada no existe  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                        }

                    }else{
                        errors.add(new ErrorLSSL(24, " --- Error Semantico({}): La variable "+tokens.get(i+1).getLexeme()+" esta repetida  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                    }
                }
            }
            
             //Variables string
            //declaracion simple   
            if(t.getLexeme().equals("string"))
            {
                try{
                    //se asigna una variable
                    if(tokens.get(i+2).getLexeme().equals("=") && tokens.get(i+3).getLexicalComp().equals("IDENTIFICADOR") && tokens.get(i+4).getLexicalComp().equals("PUNTO_COMA")){

                        /*** Comprobacion de un nombre de variable ***/
                        if(tokens.get(i+3).getLexicalComp().equals("IDENTIFICADOR") && tokens.get(i+2).getLexicalComp().equals("ASIGNACION")){
                            //Comprobar existencia de la variable, si es falso entonces se puede agregar, no estara repetida
                            if(existIdentifier(struct, tokens.get(i+1).getLexeme()) == false){
                                //Comprobar existencia de la variable asignada, pues primero tuvo que ser declarada para poder asignarla
                                if(existIdentifier(struct, tokens.get(i+3).getLexeme()) == true){
                                    //Comprobar si el tipo de dato de la variable asignada es correcto
                                    if(isCorrectType(struct, tokens.get(i+3).getLexeme(), t.getLexeme()) == true){
                                        if(getVar(struct, tokens.get(i+3).getLexeme()).saved.equals("NULL")){
                                            errors.add(new ErrorLSSL(12, " --- Error Semantico({}): La variable "+tokens.get(i+3).getLexeme()+" es nula  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                                        }else{
                                            addIdentifier(struct, createVar(tokens.get(i+1).getLexeme(), t.getLexeme(),tokens.get(i+3).getLexeme()));
                                        }
                                    }else{
                                        errors.add(new ErrorLSSL(32, " --- Error Semantico({}): La variable no es compatible con el tipo de dato  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                                    }
                                }else{
                                    errors.add(new ErrorLSSL(33, " --- Error Semantico({}): La variable asignada no existe  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                                }

                            }else{
                                errors.add(new ErrorLSSL(34, " --- Error Semantico({}): La variable esta repetida  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                            }
                        }

                        /*** Error general de tipo ***/
                        else{
                            errors.add(new ErrorLSSL(35, " --- Error Semantico({}): Valor no compatible con el tipo de dato  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                        }
                    }

                    //se asigna una concatenacion
                    //primer operando variable, segundo cadena o variable
                    else if(tokens.get(i+2).getLexeme().equals("=") && tokens.get(i+3).getLexicalComp().equals("IDENTIFICADOR") && tokens.get(i+4).getLexeme().equals("+")){
                        //Comprobar existencia de la variable, si es falso entonces se puede agregar, no estara repetida
                        if(existIdentifier(struct, tokens.get(i+1).getLexeme()) == false){
                            //Comprobar existencia de la variable asignada, pues primero tuvo que ser declarada para poder asignarla
                            if(existIdentifier(struct, tokens.get(i+3).getLexeme()) == true){
                                //Comprobar si el tipo de dato de la variable asignada es correcto
                                if(isCorrectType(struct, tokens.get(i+3).getLexeme(), t.getLexeme()) == true){
                                    //si el segundo operando tambien es una variable
                                    if(tokens.get(i+5).getLexicalComp().equals("IDENTIFICADOR"))
                                    {

                                            //Comprobar existencia de la variable asignada, pues primero tuvo que ser declarada para poder asignarla
                                            if(existIdentifier(struct, tokens.get(i+5).getLexeme()) == true){
                                                //Comprobar si el tipo de dato de la variable asignada es correcto
                                                if(isCorrectType(struct, tokens.get(i+5).getLexeme(), t.getLexeme()) == true){
                                                    addIdentifier(struct, createVar(tokens.get(i+1).getLexeme(), t.getLexeme(),tokens.get(i+3).getLexeme()+"+"+tokens.get(i+5).getLexeme()));
                                                }else{
                                                    errors.add(new ErrorLSSL(40, " --- Error Semantico({}): La variable no es compatible con el tipo de dato  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                                                }
                                            }else{
                                                errors.add(new ErrorLSSL(41, " --- Error Semantico({}): La variable asignada no existe  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                                            }
                                    }
                                    //si el seugndo operando es una cadena
                                    else if(tokens.get(i+5).getLexicalComp().equals("COMILLAS"))
                                    {
                                       addIdentifier(struct, createVar(tokens.get(i+1).getLexeme(), t.getLexeme(),tokens.get(i+3).getLexeme()+"+\""+tokens.get(i+6).getLexeme()+"\"")); 
                                    }
                                }else{
                                    errors.add(new ErrorLSSL(42, " --- Error Semantico({}): La variable no es compatible con el tipo de dato  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                                }
                            }else{
                                errors.add(new ErrorLSSL(43, " --- Error Semantico({}): La variable asignada no existe  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                            }

                        }else{
                            errors.add(new ErrorLSSL(44, " --- Error Semantico({}): La variable esta repetida  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                        }
                    }
                    //declaracion con asignacion, cadena
                    else if(tokens.get(i+2).getLexeme().equals("=") && tokens.get(i+3).getLexicalComp().equals("COMILLAS") && tokens.get(i+6).getLexicalComp().equals("PUNTO_COMA"))
                    {

                            //Comprobar existencia de la variable, si es falso entonces se puede agregar, no estara repetida
                            if(existIdentifier(struct, tokens.get(i+1).getLexeme()) == false){
                                addIdentifier(struct, createVar(tokens.get(i+1).getLexeme(),t.getLexeme(),"\""+tokens.get(i+4).getLexeme()+"\""));
                            }
                            else{
                                errors.add(new ErrorLSSL(31, " --- Error Semantico({}): La variable esta repetida  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                            }

                    //se asigna una variable
                    }
                    //usa un operador que no es +
                    else if((tokens.get(i+6).getLexicalComp().equals("OP_ARIT") && !tokens.get(i+6).getLexeme().equals("+")) || (tokens.get(i+4).getLexicalComp().equals("OP_ARIT") && !tokens.get(i+4).getLexeme().equals("+")))
                    {
                       errors.add(new ErrorLSSL(45, " --- Error Sintactico({}): Para concatenar utilizar el simbolo +  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true)); 
                    }
                    //primer operando cadena, segundo variable
                    else if(tokens.get(i+2).getLexeme().equals("=") && tokens.get(i+3).getLexicalComp().equals("COMILLAS") && tokens.get(i+6).getLexeme().equals("+") && tokens.get(i+7).getLexicalComp().equals("IDENTIFICADOR")){
                        //Comprobar existencia de la variable, si es falso entonces se puede agregar, no estara repetida
                        if(existIdentifier(struct, tokens.get(i+1).getLexeme()) == false){
                            //Comprobar existencia de la variable asignada, pues primero tuvo que ser declarada para poder asignarla
                            if(existIdentifier(struct, tokens.get(i+7).getLexeme()) == true){
                                //Comprobar si el tipo de dato de la variable asignada es correcto
                                if(isCorrectType(struct, tokens.get(i+7).getLexeme(), t.getLexeme()) == true){
                                    addIdentifier(struct, createVar(tokens.get(i+1).getLexeme(), t.getLexeme(),"\""+tokens.get(i+4).getLexeme()+"\""+tokens.get(i+7).getLexeme()));
                                }else{
                                    errors.add(new ErrorLSSL(37, " --- Error Semantico({}): La variable no es compatible con el tipo de dato  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                                }
                            }else{
                                errors.add(new ErrorLSSL(38, " --- Error Semantico({}): La variable asignada no existe  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                            }

                        }else{
                            errors.add(new ErrorLSSL(39, " --- Error Semantico({}): La variable esta repetida  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                        }
                    }
                    //ambos operandos cadenas
                    else if(tokens.get(i+3).getLexicalComp().equals("COMILLAS") && tokens.get(i+6).getLexeme().equals("+") && tokens.get(i+7).getLexicalComp().equals("COMILLAS")){
                        //Comprobar existencia de la variable, si es falso entonces se puede agregar, no estara repetida
                        if(existIdentifier(struct, tokens.get(i+1).getLexeme()) == false){
                            addIdentifier(struct, createVar(tokens.get(i+1).getLexeme(), t.getLexeme(),"\""+tokens.get(i+4).getLexeme()+"\"+\""+tokens.get(i+8).getLexeme()+"\""));                            
                        }else{
                            errors.add(new ErrorLSSL(36, " --- Error Semantico({}): La variable esta repetida  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                        }
                    }
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
            }
        }
    }
    
    public void showStructs(){
        for(int i = 0; i < identifiers.size(); i++){
            System.out.print(identifiers.get(i).type + " ");
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
    
    public int getIndexVar(int indexStruct, String name){
        ArrayList<Variable> vars = identifiers.get(indexStruct).words;
        for(int i = 0; i < vars.size(); i++){
            if(vars.get(i).name.equals(name)){
                return i;
            }
        }
        return -1;
    }
    
    //Obtener una variable
    public Variable getVar(String struct, String name){
        int index = getIndexStruct(struct);
        ArrayList<Variable> idfs = identifiers.get(index).words;
        for(int i = 0; i < idfs.size(); i++){
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
    
    public Variable createVar(String nom, String type, String save){
        Variable v = new Variable(nom, type,save);
        return v;
    }
    
    //Comprobar si ya existe un nombre de variable
    public boolean existIdentifier(String struct, String name){
        int index = getIndexStruct(struct);
        ArrayList<Variable> idfs = identifiers.get(index).words;
        for(int i = 0; i < idfs.size(); i++){
            if(name.equals(idfs.get(i).name)){
                return true;
            }
        }
        return false;
    }
    
    //Comprobar la existencia de una funcion
    public boolean existFunction(String name){
        for(int i = 0; i < identifiers.size(); i++){
            if(identifiers.get(i).structName.equals(name)){
                return true;
            }
        }
        return false;
    }
    
    //Comprobar que una asignacion este correcta en cuanto al tipo de dato, Ejemplo: int a = b;
    public boolean isCorrectType(String struct, String name, String type){
        Variable v = getVar(struct, name);
        
        if(v != null){
            if(v.type.equals(type)){
                return true;
            }
        }
        return false;
    }
    
    //Comprobar que en una asignacion los tipos de datos sean correctos, Ejemplo: a = b;
    public boolean isCorrectAsign(String struct, String name1, String name2){
        Variable v1 = getVar(struct, name1);
        Variable v2 = getVar(struct, name2);
        
        if(v1 != null && v2 != null){
            if(v1.type.equals(v2.type)){
                return true;
            }
        }
        return false;
    }
    
    //Comprobar que una llamada de la funcion tenga los parametros correctos
    public boolean checkFunctionCall(ArrayList<Token> tokens, int index, String struct, String structFunct, ArrayList<ErrorLSSL> errors, Production p){
        int i = index+1;
        int parmPosition = 0; 
        ArrayList<Variable> params = identifiers.get(getIndexStruct(structFunct)).params;
        
        while(!tokens.get(i).getLexicalComp().equals("PARENTESIS_C")){
            Token t = tokens.get(i);
            if(parmPosition < params.size()){
                if(tokens.get(i).getLexicalComp().equals("IDENTIFICADOR")){
                    if(existIdentifier(struct, tokens.get(i).getLexeme()) == false){
                        errors.add(new ErrorLSSL(33, " --- Error Semantico({}): La variable "+tokens.get(i).getLexeme()+" no existe  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                        return false;
                    }else if(!params.get(parmPosition).type.equals(getVar(struct, tokens.get(i).getLexeme()).type)){
                        errors.add(new ErrorLSSL(33, " --- Error Semantico({}): El tipo de dato de la variable "+tokens.get(i).getLexeme()+" no coincide [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                        return false;
                    }else if(getVar(struct, tokens.get(i).getLexeme()).saved.equals("NULL")){
                        errors.add(new ErrorLSSL(33, " --- Error Semantico({}): La variable "+tokens.get(i).getLexeme()+" es nula  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                        return false;
                    }else{
                        parmPosition++;
                    }
                }
                else if(tokens.get(i).getLexicalComp().equals("NUMERO")){
                    if(!params.get(parmPosition).type.equals("int") && !params.get(parmPosition).type.equals("float")){
                        return false;
                    }else{
                        parmPosition++;
                    }
                }else if(tokens.get(i).getLexicalComp().equals("COMILLAS") &&
                        (tokens.get(i+1).getLexicalComp().equals("IDENTIFICADOR") || tokens.get(i+1).getLexicalComp().equals("NUMERO")) &&
                        tokens.get(i+2).getLexicalComp().equals("COMILLAS")){
                    i+=2;
                    if(!params.get(parmPosition).type.equals("string")){
                        return false;
                    }else{
                        parmPosition++;
                    }
                }else if(tokens.get(i).getLexicalComp().equals("C_SIMPLE") &&
                        (tokens.get(i+1).getLexicalComp().equals("IDENTIFICADOR") || tokens.get(i+1).getLexicalComp().equals("NUMERO")) &&
                        tokens.get(i+2).getLexicalComp().equals("C_SIMPLE")){
                    i+=2;
                    if(!params.get(parmPosition).type.equals("char")){
                        return false;
                    }else{
                        parmPosition++;
                    }
                }
            }else{
                return false;
            }
            
            i++;
        }
        if(parmPosition < params.size()){
            return false;
        }
        return true;
    }
    
    
    public String getPrintString (ArrayList<Token> tokens, int index, String struct, ArrayList<ErrorLSSL> errors, Production p){
        int i = index + 2;
        String print = "";
        Variable aux;
        
        while(!(tokens.get(i).getLexicalComp().equals("PARENTESIS_C"))){
            Token t = tokens.get(i);
            if(existIdentifier(struct, tokens.get(i).getLexeme())){
                aux = getVar(struct, tokens.get(i).getLexeme());
                if(!(aux.saved.equals("NULL"))){
                    print += aux.saved + " ";
                }else{
                    errors.add(new ErrorLSSL(300, " --- Error Semantico({}): La variable es nula [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                }
            }else{
                if(!(tokens.get(i).getLexicalComp().equals("COMILLAS")) && !(tokens.get(i).getLexicalComp().equals("OP_ARIT"))){
                    print += tokens.get(i).getLexeme() + " ";
                }
            }
            i++;
        }
        indexPrint = i;
        return print;
    }
    
  
}
