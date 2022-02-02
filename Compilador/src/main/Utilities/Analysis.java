
package main.Utilities;

import java.util.ArrayList;


public class Analysis {
    ArrayList<Token> tokens = new ArrayList<Token>();
    
    public Analysis(ArrayList<Token> tokens){
        this.tokens = tokens;
    }
    
    public void action(String palabra){
        palabra=palabra.toLowerCase();

        String[] splitedTokens=palabra.split(" ");


        for(int i=0; i<splitedTokens.length; i++){
            System.out.print(splitedTokens[i]);

            if(splitedTokens[i].equals("if")){
                System.out.println(" Es una palabra reservada");
            }else if(splitedTokens[i].equals("else")){
                System.out.println(" Es una palabra reservada");
            }else if(splitedTokens[i].equals("while")){
                System.out.println(" Es una palabra reservada");
            }else if(splitedTokens[i].equals("for")){
                System.out.println(" Es una palabra reservada");
            }else if(splitedTokens[i].equals("switch")){
                System.out.println(" Es una palabra reservada");
            }else if(splitedTokens[i].equals("case")){
                System.out.println(" Es una palabra reservada");
            }else if(splitedTokens[i].equals("default")){
                System.out.println(" Es una palabra reservada");
            }else if(splitedTokens[i].equals("integer")){
                System.out.println(" Es una palabra reservada");
            }else if(splitedTokens[i].equals("float")){
                System.out.println(" Es una palabra reservada");
            }else if(splitedTokens[i].equals("string")){
                System.out.println(" Es una palabra reservada");
            }else if(splitedTokens[i].equals("char")){
                System.out.println(" Es una palabra reservada");
            }else if(splitedTokens[i].equals("boolean")){
                System.out.println(" Es una palabra reservada");
            }else if(splitedTokens[i].equals("=")){
                System.out.println(" Es un operador de asignacion");
            }else if(splitedTokens[i].equals("==")){
                System.out.println(" Es un operador de comparacion");
            }else if(splitedTokens[i].equals("=!")){
                System.out.println(" Es un operador de comparacion");
            }else if(splitedTokens[i].equals(">")){
                System.out.println(" Es un operador de comparacion");
            }else if(splitedTokens[i].equals("<")){
                System.out.println(" Es un operador de comparacion");
            }else if(splitedTokens[i].equals(">=")){
                System.out.println(" Es un operador de comparacion");
            }else if(splitedTokens[i].equals("<=")){
                System.out.println(" Es un operador de comparacion");
            }else if(splitedTokens[i].equals("&&")){
                System.out.println(" Es un operador de comparacion");
            }else if(splitedTokens[i].equals("||")){
                System.out.println(" Es un operador de comparacion");
            }else if(splitedTokens[i].equals(";")){
                System.out.println(" Es un separador de sentencias");
            }else if(splitedTokens[i].equals("{")){
                System.out.println(" Es un separador de sentencias");
            }else if(splitedTokens[i].equals("}")){
                System.out.println(" Es un separador de sentencias");
            }else if(splitedTokens[i].equals("(")){
                System.out.println(" Es un separador de sentencias");
            }else if(splitedTokens[i].equals(")")){
                System.out.println(" Es un separador de sentencias");
            }else if(splitedTokens[i].equals("+")){
                System.out.println(" Es un operador matematico");
            }else if(splitedTokens[i].equals("-")){
                System.out.println(" Es un operador matematico");
            }else if(splitedTokens[i].equals("*")){
                System.out.println(" Es un operador matematico");
            }else if(splitedTokens[i].equals("/")){
                System.out.println(" Es un operador matematico");
            }else if(splitedTokens[i].equals("++")){
                System.out.println(" Es un operador de incremento");
            }else if(splitedTokens[i].equals("--")){
                System.out.println(" Es un operador de drecremento");
            }else{
                if(identifierCheck(splitedTokens[i])){
                    System.out.println(" Es un identificador de "+splitedTokens[i].length()+" caracteres");
                }else{
                    System.out.println("Palabra no permitida, uso de simbolos incorrecto");
                }

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
