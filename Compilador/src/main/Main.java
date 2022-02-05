
package main;
import Forms.Principal;
import java.util.Scanner;
import Utilities.Analysis;
import Utilities.Loader;

public class Main {

    public static void main(String[] args) {
        
        Principal principal = new Principal();
        principal.setVisible(true);
        principal.setLocationRelativeTo(null);
        
        /*Loader loader = new Loader();
        Analysis analysis = new Analysis(loader.tokens);
        Scanner scanner = new Scanner(System.in);
        String token = "";
        
        System.out.print("Introduce el token: ");
        token = scanner.next();
        
        analysis.action(token);*/
        
    }

}
