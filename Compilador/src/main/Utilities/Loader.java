
package main.Utilities;

import java.util.ArrayList;

public class Loader {
    
    public ArrayList<Token> tokens = new ArrayList<Token>();
    int attb = 500;
    
    public Loader(){
        load();
    }
    
    public void load(){
        tokens.add(new Token("if", attb++));
        tokens.add(new Token("else", attb++));
        tokens.add(new Token("while", attb++));
        tokens.add(new Token("for", attb++));
        tokens.add(new Token("switch", attb++));
        tokens.add(new Token("case", attb++));
        tokens.add(new Token("default", attb++));
        tokens.add(new Token("integer", attb++));
        tokens.add(new Token("float", attb++));
        tokens.add(new Token("string", attb++));
        tokens.add(new Token("char", attb++));
        tokens.add(new Token("boolean", attb++));
        tokens.add(new Token("=", attb++));
        tokens.add(new Token("==", attb++));
        tokens.add(new Token("=!", attb++));
        tokens.add(new Token(">", attb++));
        tokens.add(new Token("<", attb++));
        tokens.add(new Token(">=", attb++));
        tokens.add(new Token("<=", attb++));
        tokens.add(new Token("&&", attb++));
        tokens.add(new Token("||", attb++));
        tokens.add(new Token(";", attb++));
        tokens.add(new Token("{", attb++));
        tokens.add(new Token("}", attb++));
        tokens.add(new Token("(", attb++));
        tokens.add(new Token(")", attb++));
        tokens.add(new Token("+", attb++));
        tokens.add(new Token("-", attb++));
        tokens.add(new Token("*", attb++));
        tokens.add(new Token("/", attb++));
        tokens.add(new Token("++", attb++));
        tokens.add(new Token("--", attb++));
    }
    
}
