
package main;
import java.util.ArrayList;
import main.Utilities.Analysis;
import main.Utilities.Loader;

public class Main {

    public static void main(String[] args) {
        
        Loader loader = new Loader();
        Analysis analysis = new Analysis(loader.tokens);
        
        analysis.action("switch");
        
    }

}
