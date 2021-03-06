
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
        
        if(mnem.equals("mov")){
            MOV(regs, errors, p, t.get(i), struct);
        }else if(mnem.equals("add")){
            ADD(regs, errors, p, t.get(i), struct);
        }else if(mnem.equals("sub")){
            SUB(regs, errors, p, t.get(i), struct);
        }else if(mnem.equals("mul")){
            MUL(regs, errors, p, t.get(i), struct);
        }else if(mnem.equals("div")){
            DIV(regs, errors, p, t.get(i), struct);
        }else if(mnem.equals("pow")){
            POW(regs, errors, p, t.get(i), struct);
        }else if(mnem.equals("sqrt")){
            SQRT(regs, errors, p, t.get(i), struct);
        }else if(mnem.equals("cos")){
            COS(regs, errors, p, t.get(i), struct);
        }else if(mnem.equals("sen")){
            SEN(regs, errors, p, t.get(i), struct);
        }else if(mnem.equals("tan")){
            TAN(regs, errors, p, t.get(i), struct);
        }
    }
    
    
    public void MOV(Registers r, ArrayList<ErrorLSSL> errors, Production p, Token t, String struct){
        if(isReg(lexical1) && isReg(lexical2)){
            //revisar que sean del mismo tamaño
            if(lexical1.equals(lexical2))
                r.regs.put(val2, r.regs.get(val1));
            else
                errors.add(new ErrorLSSL(151, " --- Error Semantico({}): El tamaño de los registros debe ser igual  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
        }else if(isNum(lexical1) && isReg(lexical2)){
            //si es un registro de 8 bits no puede almacenar mas de 256
            if(lexical2.equals("REG_8") && Integer.parseInt(val1)>255)
                errors.add(new ErrorLSSL(152, " --- Error Semantico({}): El valor supera la capacidad del registro  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
            else    
                r.regs.put(val2, val1);
        }else if(isIdent(lexical1) && isIdent(lexical2)){
            //comprobar que existen los identificadores
            if(existIdentifier(struct, val1) && existIdentifier(struct, val2)){
                getVar(struct, val2).saved = getVar(struct, val1).saved;
            }else{
                errors.add(new ErrorLSSL(153, " --- Error Semantico({}): Las variables no existen  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
            }
        }else if(isIdent(lexical1) && isReg(lexical2)){
            //Se obtiene valor de identificador, revisar si existe
            if(existIdentifier(struct, val1)){
                //si el registro es de 8 bits, revisar que el valor sea menor a 256
                if(lexical2.equals("REG_8") && Integer.parseInt(getVar(struct, val1).saved)>255){
                    errors.add(new ErrorLSSL(154, " --- Error Semantico({}): El valor supera la capacidad del registro  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                }else{  
                    r.regs.put(val2, getVar(struct, val1).saved);
                }
            }else{
                errors.add(new ErrorLSSL(153, " --- Error Semantico({}): La variable no existe  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
            }
        }else if(isReg(lexical1) && isIdent(lexical2)){
            //Asignar el nuevo valor al identificador
            if(existIdentifier(struct, val2)){
                getVar(struct, val2).saved = r.regs.get(val1);
            }else{
                errors.add(new ErrorLSSL(150, " --- Error Semantico({}): La variable no existe  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
            }
        }else if(isNum(lexical1) && isIdent(lexical2)){
            //Revisar que el identificador exista
            if(existIdentifier(struct, val2)){
                getVar(struct, val2).saved = val1;
            }else{
                errors.add(new ErrorLSSL(155, " --- Error Semantico({}): La variable no existe  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
            }
        }else{
            //Error
            errors.add(new ErrorLSSL(160, " --- Error Semantico({}): La instruccion tiene acciones no validas  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
        }
    }
    
    public void ADD(Registers r, ArrayList<ErrorLSSL> errors, Production p, Token t, String struct){
        int newValue = 0;
        //revisar que sean del mismo tamaño
        if(isReg(lexical1) && isReg(lexical2)){
            if(lexical1.equals(lexical2))
            {
                newValue = Integer.parseInt(r.regs.get(val2)) + Integer.parseInt(r.regs.get(val1));
                r.regs.put(val2, String.valueOf(newValue));
            }
            else
            {
                errors.add(new ErrorLSSL(170, " --- Error Semantico({}): Los registros deben ser del mismo tamaño  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
            }
            
        }else if(isNum(lexical1) && isReg(lexical2)){
            if(lexical2.equals("REG_8") && Integer.parseInt(val1)>255){
                    errors.add(new ErrorLSSL(171, " --- Error Semantico({}): El valor supera la capacidad del registro  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
            }else{  
                    newValue = Integer.parseInt(r.regs.get(val2)) + Integer.parseInt(val1);
                    r.regs.put(val2, String.valueOf(newValue));
            }
        }else if(isIdent(lexical1) && isReg(lexical2)){
            //Se obtiene valor de identificador, revisar si existe
            if(existIdentifier(struct, val1)){
                //si el registro es de 8 bits, revisar que el valor sea menor a 256
                if(lexical2.equals("REG_8") && Integer.parseInt(getVar(struct, val1).saved)>255){
                    errors.add(new ErrorLSSL(172, " --- Error Semantico({}): El valor supera la capacidad del registro  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
                }else{  
                    newValue = Integer.parseInt(r.regs.get(val2)) + Integer.parseInt(getVar(struct, val1).saved);
                    r.regs.put(val2, String.valueOf(newValue));
                }
            }else{
                errors.add(new ErrorLSSL(173, " --- Error Semantico({}): La variable no existe  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
            }    
        }else if(isNum(lexical1) && isIdent(lexical2)){
            //Se obtiene valor de identificador, revisar si existe
            if(existIdentifier(struct, val2)){
                  
                    newValue = Integer.parseInt(val1) + Integer.parseInt(getVar(struct, val2).saved);
                    getVar(struct, val2).saved = Integer.toString(newValue);
                
            }else{
                errors.add(new ErrorLSSL(177, " --- Error Semantico({}): La variable no existe  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
            }
        }else if(isIdent(lexical1) && isIdent(lexical2)){
            //revisar si existen
            if(existIdentifier(struct, val1) && existIdentifier(struct, val2)){
                  
                    newValue = Integer.parseInt(getVar(struct, val1).saved) + Integer.parseInt(getVar(struct, val2).saved);
                    getVar(struct, val2).saved = Integer.toString(newValue);
                
            }else{
                errors.add(new ErrorLSSL(177, " --- Error Semantico({}): Alguna de las variables no existe  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
            }
        }else{
            //Error o caso no programado
            errors.add(new ErrorLSSL(176, " --- Error Semantico({}): La instruccion tiene acciones no validas  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
        }
    }
    
    public void SUB(Registers r, ArrayList<ErrorLSSL> errors, Production p, Token t, String struct){
        int newValue = 0;
        //revisar que sean del mismo tamaño
        if(isReg(lexical1) && isReg(lexical2)){
            if(lexical1.equals(lexical2))
            {
                newValue = Integer.parseInt(r.regs.get(val2)) - Integer.parseInt(r.regs.get(val1));
                r.regs.put(val2, String.valueOf(newValue));
            }
            else
            {
                errors.add(new ErrorLSSL(180, " --- Error Semantico({}): Los registros deben ser del mismo tamaño  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
            }
            
        }else if(isNum(lexical1) && isReg(lexical2)){
            newValue = Integer.parseInt(r.regs.get(val2)) - Integer.parseInt(val1);
            r.regs.put(val2, String.valueOf(newValue));
            
        }else if(isIdent(lexical1) && isReg(lexical2)){
            //Se obtiene valor de identificador, revisar si existe
            if(existIdentifier(struct, val1)){
                newValue = Integer.parseInt(r.regs.get(val2)) - Integer.parseInt(getVar(struct, val1).saved);
                r.regs.put(val2, String.valueOf(newValue));
                
            }else{
                errors.add(new ErrorLSSL(182, " --- Error Semantico({}): La variable no existe  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
            }    
        }else if(isNum(lexical1) && isIdent(lexical2)){
            //Se obtiene valor de identificador, revisar si existe
            if(existIdentifier(struct, val2)){
                  
                    newValue = Integer.parseInt(getVar(struct, val2).saved) - Integer.parseInt(val1) ;
                    getVar(struct, val2).saved = Integer.toString(newValue);
                
            }else{
                errors.add(new ErrorLSSL(183, " --- Error Semantico({}): La variable no existe  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
            }
        }else if(isIdent(lexical1) && isIdent(lexical2)){
            //revisar si existen
            if(existIdentifier(struct, val1) && existIdentifier(struct, val2)){
                  
                    newValue = Integer.parseInt(getVar(struct, val1).saved) - Integer.parseInt(getVar(struct, val2).saved);
                    getVar(struct, val2).saved = Integer.toString(newValue);
                
            }else{
                errors.add(new ErrorLSSL(184, " --- Error Semantico({}): Alguna de las variables no existe  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
            }
        }else{
            //Error o caso no programado
            errors.add(new ErrorLSSL(185, " --- Error Semantico({}): La instruccion tiene acciones no validas  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
        }
    }
    
    public void MUL(Registers r, ArrayList<ErrorLSSL> errors, Production p, Token t, String struct){
        int newValue = 0;
        if(isReg(lexical1)){
            if(lexical1.equals("REG_8"))
            {
                newValue = Integer.parseInt(r.regs.get("al")) * Integer.parseInt(r.regs.get(val1));
                r.regs.put("ax", String.valueOf(newValue));
            }
            else if(lexical1.equals("REG_16"))
            {
                newValue = Integer.parseInt(r.regs.get("ax")) * Integer.parseInt(r.regs.get(val1));
                r.regs.put("dx", String.valueOf(newValue));
            }
        }else if(isNum(lexical1)){
            if(Integer.parseInt(val1) > 0)
            {
               if(Integer.parseInt(val1) < 256)
                {
                    newValue = Integer.parseInt(r.regs.get("al")) * Integer.parseInt(val1);
                    r.regs.put("ax", String.valueOf(newValue));
                }
                else
                {
                    newValue = Integer.parseInt(r.regs.get("ax")) * Integer.parseInt(val1);
                    r.regs.put("dx", String.valueOf(newValue));
                } 
            }
            else{
                errors.add(new ErrorLSSL(190, " --- Error Semantico({}): No se aceptan números negativos  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));        
            }
        }
        else if(isIdent(lexical1)){
            if(existIdentifier(struct, val1)){
                int valorVariable = Integer.parseInt(getVar(struct, val1).saved);
                if(valorVariable > 0)
                {
                   if(valorVariable < 256)
                    {
                        newValue = Integer.parseInt(r.regs.get("al")) * valorVariable;
                        r.regs.put("ax", String.valueOf(newValue));
                    }
                    else
                    {
                        newValue = Integer.parseInt(r.regs.get("ax")) * valorVariable;
                        r.regs.put("dx", String.valueOf(newValue));
                    } 
                }
                else{
                    errors.add(new ErrorLSSL(191, " --- Error Semantico({}): No se aceptan números negativos  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));        
                }
            }
            else{
                    errors.add(new ErrorLSSL(192, " --- Error Semantico({}): La variable no existe  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));        
            }
        }else{
        errors.add(new ErrorLSSL(193, " --- Error Semantico({}): La instruccion tiene acciones no validas  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
        }
    }
    
    public void DIV(Registers r, ArrayList<ErrorLSSL> errors, Production p, Token t, String struct){
        //simular que se usa dx:ax copiando lo mismo a los dos registros
        int newValue = 0;
        int resto = 0;
        if(isReg(lexical1)){
            if(lexical1.equals("REG_8"))
            {
                newValue = Integer.parseInt(r.regs.get("ax")) / Integer.parseInt(r.regs.get(val1));
                resto = Integer.parseInt(r.regs.get("ax")) % Integer.parseInt(r.regs.get(val1));
                r.regs.put("al", String.valueOf(newValue));
                r.regs.put("ah", String.valueOf(resto));
                
                r.regs.put("ax", "0");
            }
            else if(lexical1.equals("REG_16"))
            {
                newValue = Integer.parseInt(r.regs.get("ax")) / Integer.parseInt(r.regs.get(val1));
                resto = Integer.parseInt(r.regs.get("ax")) % Integer.parseInt(r.regs.get(val1));
                r.regs.put("al", String.valueOf(newValue));
                r.regs.put("ah", String.valueOf(resto));
                
                r.regs.put("ax", "0");
            }
        }else if(isNum(lexical1)){
            if(Integer.parseInt(val1) > 0)
            {
               if(Integer.parseInt(val1) < 256)
                {
                    newValue = Integer.parseInt(r.regs.get("ax")) / Integer.parseInt(val1);
                    resto = Integer.parseInt(r.regs.get("ax")) % Integer.parseInt(val1);
                    r.regs.put("al", String.valueOf(newValue));
                    r.regs.put("ah", String.valueOf(resto));
                    
                    r.regs.put("ax", "0");
                }
                else
                {
                    newValue = Integer.parseInt(r.regs.get("ax")) / Integer.parseInt(val1);
                    resto = Integer.parseInt(r.regs.get("ax")) % Integer.parseInt(val1);
                    r.regs.put("al", String.valueOf(newValue));
                    r.regs.put("ah", String.valueOf(resto));
                    
                    r.regs.put("ax", "0");
                } 
            }
            else{
                errors.add(new ErrorLSSL(190, " --- Error Semantico({}): No se aceptan números negativos  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));        
            }
        }
        else if(isIdent(lexical1)){
            if(existIdentifier(struct, val1)){
                int valorVariable = Integer.parseInt(getVar(struct, val1).saved);
                if(valorVariable > 0)
                {
                   if(valorVariable < 256)
                    {
                        newValue = Integer.parseInt(r.regs.get("ax")) / valorVariable;
                        resto = Integer.parseInt(r.regs.get("ax")) % valorVariable;
                        r.regs.put("al", String.valueOf(newValue));
                        r.regs.put("ah", String.valueOf(resto));
                        
                        r.regs.put("ax", "0");
                    }
                    else
                    {
                        newValue = Integer.parseInt(r.regs.get("ax")) / valorVariable;
                        resto = Integer.parseInt(r.regs.get("ax")) % valorVariable;
                        r.regs.put("al", String.valueOf(newValue));
                        r.regs.put("ah", String.valueOf(resto));
                        
                        r.regs.put("ax", "0");
                    } 
                }
                else{
                    errors.add(new ErrorLSSL(191, " --- Error Semantico({}): No se aceptan números negativos  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));        
                }
            }
            else{
                    errors.add(new ErrorLSSL(192, " --- Error Semantico({}): La variable no existe  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));        
            }
        }else{
        errors.add(new ErrorLSSL(193, " --- Error Semantico({}): La instruccion tiene acciones no validas  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
        }
    }
    
    public void POW(Registers r, ArrayList<ErrorLSSL> errors, Production p, Token t, String struct){
        int pot = 0;
        if(isNum(lexical1) && isNum(lexical2)){
            pot = (int) Math.pow(Integer.valueOf(val1), Integer.valueOf(val2));
            r.regs.put("dx", String.valueOf(pot));
        }else if(isReg(lexical1) && isNum(lexical2)){
            pot = (int) Math.pow(Integer.valueOf(r.regs.get(val1)), Integer.valueOf(val2));
            r.regs.put("dx", String.valueOf(pot));
        }else{
            errors.add(new ErrorLSSL(220, " --- Error Semantico({}): La instruccion tiene acciones no validas  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
        }
    }
    
    public void SQRT(Registers r, ArrayList<ErrorLSSL> errors, Production p, Token t, String struct){
        int sqrt = 0;
        if(isNum(lexical1)){
            sqrt = (int) Math.sqrt(Integer.valueOf(val1));
            r.regs.put("dx", String.valueOf(sqrt));
        }else if(isReg(lexical1)){
            sqrt = (int) Math.sqrt(Integer.valueOf(r.regs.get(val1)));
            r.regs.put("dx", String.valueOf(sqrt));
        }else if(isIdent(lexical1)){
            if(existIdentifier(struct, val1)){
                int valorVariable = Integer.parseInt(getVar(struct, val1).saved);
                if(valorVariable > 0)
                {
                    sqrt = (int) Math.sqrt(valorVariable);
                    r.regs.put("dx", String.valueOf(sqrt)); 
                }
                else{
                    errors.add(new ErrorLSSL(191, " --- Error Semantico({}): No se aceptan números negativos  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));        
                }
            }
            else{
                    errors.add(new ErrorLSSL(192, " --- Error Semantico({}): La variable no existe  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));        
            }
        }else{
            errors.add(new ErrorLSSL(230, " --- Error Semantico({}): La instruccion tiene acciones no validas  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
        }
    }
    
    public void COS(Registers r, ArrayList<ErrorLSSL> errors, Production p, Token t, String struct){
        float cos = 0;
        double value = 0;
        if(isNum(lexical1) && isReg(lexical2)){
            value = Double.parseDouble(val1);
            cos =  (float)Math.cos(value);
            r.regs.put(val2, String.valueOf(cos));
        }else if(isReg(lexical1) && isReg(lexical2)){
            value = Double.parseDouble(r.regs.get(val1));
            cos =  (float)Math.cos(value);
            r.regs.put(val2, String.valueOf(cos));
        }else if(isReg(lexical1) && isIdent(lexical2)){
            value = Double.parseDouble(r.regs.get(val1));
            cos =  (float)Math.cos(value);
            //Se obtiene valor de identificador, revisar si existe
            if(existIdentifier(struct, val2)){
                getVar(struct, val2).saved = String.valueOf(cos);
            }else{
                errors.add(new ErrorLSSL(183, " --- Error Semantico({}): La variable no existe  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
            }
        }else{
            errors.add(new ErrorLSSL(221, " --- Error Semantico({}): La instruccion tiene acciones no validas  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
        }
    }
    
    public void SEN(Registers r, ArrayList<ErrorLSSL> errors, Production p, Token t, String struct){
        float sen = 0;
        double value = 0;
        if(isNum(lexical1) && isReg(lexical2)){
            value = Double.parseDouble(val1);
            sen =  (float)Math.sin(value);
            r.regs.put(val2, String.valueOf(sen));
        }else if(isReg(lexical1) && isReg(lexical2)){
            value = Double.parseDouble(r.regs.get(val1));
            sen =  (float)Math.sin(value);
            r.regs.put(val2, String.valueOf(sen));
        }else if(isReg(lexical1) && isIdent(lexical2)){
            value = Double.parseDouble(r.regs.get(val1));
            sen =  (float)Math.sin(value);
            //Se obtiene valor de identificador, revisar si existe
            if(existIdentifier(struct, val2)){
                getVar(struct, val2).saved = String.valueOf(sen);
            }else{
                errors.add(new ErrorLSSL(184, " --- Error Semantico({}): La variable no existe  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
            }
        }else{
            errors.add(new ErrorLSSL(222, " --- Error Semantico({}): La instruccion tiene acciones no validas  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
        }
    }
    
    public void TAN(Registers r, ArrayList<ErrorLSSL> errors, Production p, Token t, String struct){
        float tan = 0;
        double value = 0;
        if(isNum(lexical1) && isReg(lexical2)){
            value = Double.parseDouble(val1);
            tan =  (float)Math.tan(value);
            r.regs.put(val2, String.valueOf(tan));
        }else if(isReg(lexical1) && isReg(lexical2)){
            value = Double.parseDouble(r.regs.get(val1));
            tan =  (float)Math.tan(value);
            r.regs.put(val2, String.valueOf(tan));
        }else if(isReg(lexical1) && isIdent(lexical2)){
            value = Double.parseDouble(r.regs.get(val1));
            tan =  (float)Math.tan(value);
            //Se obtiene valor de identificador, revisar si existe
            if(existIdentifier(struct, val2)){
                getVar(struct, val2).saved = String.valueOf(tan);
            }else{
                errors.add(new ErrorLSSL(183, " --- Error Semantico({}): La variable no existe  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
            }
        }else{
            errors.add(new ErrorLSSL(221, " --- Error Semantico({}): La instruccion tiene acciones no validas  [Linea: "+t.getLine()+", Caracter: "+t.getColumn()+"]", p, true));
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
