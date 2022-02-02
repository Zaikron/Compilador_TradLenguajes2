
package main;
import java.util.ArrayList;
import java.util.Scanner;
import main.Utilities.Analysis;
import main.Utilities.Loader;

public class Main {

    public static void main(String[] args) {
        
        Loader loader = new Loader();
        Analysis analysis = new Analysis(loader.tokens);
        Scanner scanner = new Scanner(System.in);
        String token = "";
        
        System.out.print("Introduce el token: ");
        token = scanner.nextLine();

        analysis.action(token);
    }

}
