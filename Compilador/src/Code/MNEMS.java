
package Code;

import compilerTools.Token;
import java.util.ArrayList;

public class MNEMS {
    String mnem = "";
    String val1 = "";
    String val2 = "";
    
    String lexical1;
    String lexical2;
    
    public MNEMS(String mnem, String val1, String val2){
        this.mnem = mnem;
        this.val1 = val1;
        this.val2 = val2;
    }
    
    public void checkMNEMS(Registers regs, ArrayList<Token> t, int i){
        lexical1 = t.get(i+4).getLexicalComp();
        lexical2 = t.get(i+6).getLexicalComp();
        
        System.out.println("lex1: " + lexical1);
        System.out.println("lex2: " + lexical2);
        
        if(mnem.equals("mov")){
            MOV(regs);
        }else if(mnem.equals("add")){
            
        }
    }
    
    public void MOV(Registers r){
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
        }else if(isNum(lexical1) && isIdent(lexical2)){
            //Asignar el nuevo valor al identificador
        }else{
            //Error
            System.out.println("Error en ASM");
        }
    }
    
    public void ADD(){
        
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
    
}
