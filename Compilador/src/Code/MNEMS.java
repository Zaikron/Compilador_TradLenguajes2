
package Code;

import compilerTools.ErrorLSSL;
import compilerTools.Production;
import compilerTools.Token;
import java.util.ArrayList;

public class MNEMS {
    String mnem = "";
    String val1 = "";
    String val2 = "";
    
    String lexical1;
    String lexical2;
    
    ArrayList<Identifier> identifiers;
    
    public MNEMS(String mnem, String val1, String val2, ArrayList<Identifier> identifiers){
        this.mnem = mnem;
        this.val1 = val1;
        this.val2 = val2;
        this.identifiers = identifiers;
    }
    
    public void checkMNEMS(Registers regs, ArrayList<Token> t, int i, ArrayList<ErrorLSSL> errors, Production p, String struct){
        lexical1 = t.get(i+4).getLexicalComp();
        lexical2 = t.get(i+6).getLexicalComp();
        
        System.out.println("lex1: " + lexical1);
        System.out.println("lex2: " + lexical2);
        
        if(mnem.equals("mov")){
            MOV(regs, errors, p, t.get(i), struct);
        }else if(mnem.equals("add")){
            ADD(regs, errors, p, t.get(i), struct);
        }
    }
    
    public void MOV(Registers r, ArrayList<ErrorLSSL> errors, Production p, Token t, String struct){
        System.out.println("v1: " + val1 + " v2: " + val2);
        if(isReg(lexical1) && isReg(lexical2)){
            r.regs.put(val2, r.regs.get(val1));
        }else if(isNum(lexical1) && isReg(lexical2)){
            r.regs.put(val2, val1);
        }else if(isIdent(lexical1) && isIdent(lexical2)){
            //Se obtienen valores de identificadores
        }else if(isIdent(lexical1) && isReg(lexical2)){
            //Se obtiene valor de identificador, revisar si existe
        }else if(isReg(lexical1) && isIdent(lexical2)){
            //Asignar el nuevo valor al identificador
            if(existIdentifier(struct, val2)){
                getVar(struct, val2).saved = r.regs.get(val1);
            }else{
                errors.add(new ErrorLSSL(150, " --- Error Semantico({}): La instruccion tiene acciones no validas  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
            }
        }else if(isNum(lexical1) && isIdent(lexical2)){
            //Asignar el nuevo valor al identificador
        }else{
            //Error
            errors.add(new ErrorLSSL(160, " --- Error Semantico({}): La instruccion tiene acciones no validas  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
        }
    }
    
    public void ADD(Registers r, ArrayList<ErrorLSSL> errors, Production p, Token t, String struct){
        int newValue = 0;
        if(isReg(lexical1) && isReg(lexical2)){
            newValue = Integer.parseInt(r.regs.get(val2)) + Integer.parseInt(r.regs.get(val1));
            r.regs.put(val2, String.valueOf(newValue));
        }else if(isNum(lexical1) && isReg(lexical2)){
            newValue = Integer.parseInt(r.regs.get(val2)) + Integer.parseInt(val1);
            r.regs.put(val2, String.valueOf(newValue));
        }else{
            //Error o caso no programado
            errors.add(new ErrorLSSL(160, " --- Error Semantico({}): La instruccion tiene acciones no validas  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
        }
    }
    
    
    
    public boolean isReg(String value){
        if(value.equals("REG_16") || value.equals("REG_8")){
            return true;
        }
        return false;
    }
    
    public boolean isNum(String value){
        if(value.equals("NUMERO")){
            return true;
        }
        return false;
    }
    
    public boolean isIdent(String value){
        if(value.equals("IDENTIFICADOR")){
            return true;
        }
        return false;
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
    
    //Busqueda de la estructura (main o alguna funcion) para comprobar si existe, es ese caso devuelve el indice
    public int getIndexStruct(String struct){
        for(int i = 0; i < identifiers.size(); i++){
            if(identifiers.get(i).structName.equals(struct)){
                return i;
            }
        }
        return -1;
    }
    
}
