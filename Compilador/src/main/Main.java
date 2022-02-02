
package main;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;   

import main.Utilities.Analysis;
import main.Utilities.Loader;


public class Main {

    public static void main(String[] args) {
        
        Loader loader = new Loader();
        Analysis analysis = new Analysis(loader.tokens);
        Scanner scanner = new Scanner(System.in);
        String token = "";
        
        try{
            File file=new File("programa.txt");   
            scanner = new Scanner(file);  
            int contadorLineas=0;
            while (scanner.hasNextLine()){
                System.out.println("Linea "+contadorLineas); 
                analysis.action(scanner.nextLine());
                System.out.println("\n\n"); 
                contadorLineas++;
            }
              
        }catch(Exception e){  
            e.printStackTrace();  
        }  
        
    }

}
