
package main.Utilities;

import java.util.ArrayList;


public class Analysis {
    ArrayList<Token> tokens = new ArrayList<Token>();
    
    public Analysis(ArrayList<Token> tokens){
        this.tokens = tokens;
    }
    
    
    public void action(String palabra){
        if(palabra.equals("if")){
            System.out.println("Palabra Reservada");
        }else if(palabra.equals("else")){
            System.out.println("Palabra Reservada");
        }else if(palabra.equals("while")){
            System.out.println("Palabra Reservada");
        }else if(palabra.equals("for")){
            System.out.println("Palabra Reservada");
        }else if(palabra.equals("switch")){
            System.out.println("Palabra Reservada");
        }else if(palabra.equals("case")){
            System.out.println("Palabra Reservada");
        }else if(palabra.equals("default")){
            System.out.println("Palabra Reservada");
        }else if(palabra.equals("integer")){
            System.out.println("Palabra Reservada");
        }else if(palabra.equals("float")){
            System.out.println("Palabra Reservada");
        }else if(palabra.equals("string")){
            System.out.println("Palabra Reservada");
        }else if(palabra.equals("char")){
            System.out.println("Palabra Reservada");
        }else if(palabra.equals("boolean")){
            System.out.println("Palabra Reservada");
        }else if(palabra.equals("=")){
            System.out.println("Asignacion");
        }else if(palabra.equals("==")){
            System.out.println("Operador");
        }else if(palabra.equals("=!")){
            System.out.println("Operador");
        }else if(palabra.equals(">")){
            System.out.println("Operador");
        }else if(palabra.equals("<")){
            System.out.println("Operador");
        }else if(palabra.equals(">=")){
            System.out.println("Operador");
        }else if(palabra.equals("<=")){
            System.out.println("Operador");
        }else if(palabra.equals("&&")){
            System.out.println("Operador");
        }else if(palabra.equals("||")){
            System.out.println("Operador");
        }else if(palabra.equals(";")){
            System.out.println("Separador de Sentencias");
        }else if(palabra.equals("{")){
            System.out.println("Separador de Sentencias");
        }else if(palabra.equals("}")){
            System.out.println("Separador de Sentencias");
        }else if(palabra.equals("(")){
            System.out.println("Separador de Sentencias");
        }else if(palabra.equals(")")){
            System.out.println("Separador de Sentencias");
        }else if(palabra.equals("+")){
            System.out.println("Operador");
        }else if(palabra.equals("-")){
            System.out.println("Operador");
        }else if(palabra.equals("*")){
            System.out.println("Operador");
        }else if(palabra.equals("/")){
            System.out.println("Operador");
        }else if(palabra.equals("++")){
            System.out.println("Operador");
        }else if(palabra.equals("--")){
            System.out.println("Operador");
        }else{
            System.out.println("Identificador");
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
    
    
}
