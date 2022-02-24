
package Code;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Main {
    
    public static void main(String[] args) throws Exception {
        Main main = new Main();
        String ruta1 = main.getFilePath("Lexer.flex");
        
        String ruta2 = main.getFilePath("LexerCup.flex");
        String[] rutaS = {"-parser", "Sintax", main.getFilePath("Sintax.cup")};
        generar(ruta1, ruta2, rutaS);
        
        PrincipalFrame pf = new PrincipalFrame();
        pf.setVisible(true);
        pf.setLocationRelativeTo(null);
    }
    
    public static void generar(String ruta1, String ruta2, String[] rutaS) throws IOException, Exception{
        File archivo;
        Main main = new Main();
        archivo = new File(ruta1);
        JFlex.Main.generate(archivo);
        archivo = new File(ruta2);
        JFlex.Main.generate(archivo);
        java_cup.Main.main(rutaS);
        
        Path rutaSym = Paths.get("src/Code/sym.java");
        Path rutaMoveSym = Paths.get("sym.java");
        Path rutaMoveSintax = Paths.get("Sintax.java");
        
        System.out.println(rutaSym.toAbsolutePath());
        System.out.println(rutaMoveSym.toAbsolutePath());
        System.out.println(rutaMoveSintax.toAbsolutePath());
        if (Files.exists(rutaSym.toAbsolutePath())) {
            Files.delete(rutaSym);
        }
        Files.move(
                Paths.get(rutaMoveSym.toAbsolutePath().toString()),
                Paths.get(rutaSym.toAbsolutePath().toString()) 
        );
        
        Path rutaSin = Paths.get("src/Code/Sintax.java"); 
        if (Files.exists(rutaSin.toAbsolutePath())) {
            Files.delete(rutaSin);
        }
        Files.move(
                Paths.get(rutaMoveSintax.toAbsolutePath().toString()), 
                Paths.get(rutaSin.toAbsolutePath().toString())
        );
    }
    
    public String getFilePath(String path){
        URL url = getClass().getResource(path);
        return url.getPath();
    }
}
