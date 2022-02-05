
package Utilities;

import java.util.ArrayList;


public class Analysis {
    
    public ArrayList<Token> tokens = new ArrayList<Token>();
    
    public Analysis(ArrayList<Token> tokens){
        this.tokens = tokens;
    }
    
    
    public String action(String palabra){
        if(palabra.equals("if")){
            System.out.println("Es una palabra reservada");
            return "Es una palabra reservada";
        }else if(palabra.equals("else")){
            System.out.println("Es una palabra reservada");
            return "Es una palabra reservada";
        }else if(palabra.equals("while")){
            System.out.println("Es una palabra reservada");
            return "Es una palabra reservada";
        }else if(palabra.equals("for")){
            System.out.println("Es una palabra reservada");
            return "Es una palabra reservada";
        }else if(palabra.equals("switch")){
            System.out.println("Es una palabra reservada");
            return "Es una palabra reservada";
        }else if(palabra.equals("case")){
            System.out.println("Es una palabra reservada");
            return "Es una palabra reservada";
        }else if(palabra.equals("default")){
            System.out.println("Es una palabra reservada");
            return "Es una palabra reservada";
        }else if(palabra.equals("integer")){
            System.out.println("Es una palabra reservada");
            return "Es una palabra reservada";
        }else if(palabra.equals("float")){
            System.out.println("Es una palabra reservada");
            return "Es una palabra reservada";
        }else if(palabra.equals("string")){
            System.out.println("Es una palabra reservada");
            return "Es una palabra reservada";
        }else if(palabra.equals("char")){
            System.out.println("Es una palabra reservada");
            return "Es una palabra reservada";
        }else if(palabra.equals("boolean")){
            System.out.println("Es una palabra reservada");
            return "Es una palabra peservada";
        }else if(palabra.equals("=")){
            System.out.println("Es un operador de asignacion");
            return "Es un operador de asignacion";
        }else if(palabra.equals("==")){
            System.out.println("Es un operador de comparacion");
            return "Es un operador de comparacion";
        }else if(palabra.equals("=!")){
            System.out.println("Es un operador de comparacion");
            return "Es un operador de comparacion";
        }else if(palabra.equals(">")){
            System.out.println("Es un operador de comparacion");
            return "Es un operador de comparacion";
        }else if(palabra.equals("<")){
            System.out.println("Es un operador de comparacion");
            return "Es un operador de comparacion";
        }else if(palabra.equals(">=")){
            System.out.println("Es un operador de comparacion");
            return "Es un operador de comparacion";
        }else if(palabra.equals("<=")){
            System.out.println("Es un operador de comparacion");
            return "Es un operador de comparacion";
        }else if(palabra.equals("&&")){
            System.out.println("Es un operador de comparacion");
            return "Es un operador de comparacion";
        }else if(palabra.equals("||")){
            System.out.println("Es un operador de comparacion");
            return "Es un operador de comparacion";
        }else if(palabra.equals(";")){
            System.out.println("Es un separador de sentencias");
            return "Es un separador de sentencias";
        }else if(palabra.equals("{")){
            System.out.println("Es un separador de sentencias");
            return "Es un separador de sentencias";
        }else if(palabra.equals("}")){
            System.out.println("Es un separador de sentencias");
            return "Es un separador de sentencias";
        }else if(palabra.equals("(")){
            System.out.println("Es un separador de sentencias");
            return "Es un separador de sentencias";
        }else if(palabra.equals(")")){
            System.out.println("Es un separador de sentencias");
            return "Es un separador de sentencias";
        }else if(palabra.equals("+")){
            System.out.println("Es un operador matematico");
            return "Es un operador matematico";
        }else if(palabra.equals("-")){
            System.out.println("Es un operador matematico");
            return "Es un operador matematico";
        }else if(palabra.equals("*")){
            System.out.println("Es un operador matematico");
            return "Es un operador matematico";
        }else if(palabra.equals("/")){
            System.out.println("Es un operador matematico");
            return "Es un operador matematico";
        }else if(palabra.equals("++")){
            System.out.println("Es un operador de incremento");
            return "Es un operador de incremento";
        }else if(palabra.equals("--")){
            System.out.println("Es un operador de drecremento");
            return "Es un operador de drecremento";
        }else{
            if(identifierCheck(palabra)){
                if(palabra.length() == 0){
                    System.out.println("No se a introducido nada");
                    return "No se a introducido nada";
                }else{
                    System.out.println("Es un identificador");
                    return "Es un identificador";
                }
            }else{
                System.out.println("Palabra no permitida, uso de simbolos incorrecto");
                return "Palabra no permitida, uso de simbolos incorrecto";
            }
            
        }
    }
    
    
    public Token find(String token){
        for(int i = 0; i < tokens.size(); i++){
            if(token.equals(tokens.get(i).name)){
                return tokens.get(i);
            }
        }
        return null;
    }
    
    //Funcion para comprobar que no exista ningun simbolo en un identificador
    public boolean identifierCheck(String identifier){
        for(int i = 0; i < identifier.length(); i++){
            if(identifier.charAt(i) == '+' || identifier.charAt(i) == '-' ||
                    identifier.charAt(i) == '*' || identifier.charAt(i) == '/' ||
                    identifier.charAt(i) == '(' || identifier.charAt(i) == ')' ||
                    identifier.charAt(i) == '{' || identifier.charAt(i) == '}' ||
                    identifier.charAt(i) == ';' || identifier.charAt(i) == '>' ||
                    identifier.charAt(i) == '<' || identifier.charAt(i) == '!'){
                return false;
            }
        }
        return true;
    }
    
    
}
