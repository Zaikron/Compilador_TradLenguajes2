
package Code;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    
    public static void main(String[] args) throws Exception {

        Path path1 = Paths.get("src/Code/Lexer.flex");
        File fileFlex;
        fileFlex = new File(path1.toAbsolutePath().toString());
        JFlex.Main.generate(fileFlex);

        
        PrincipalFrame pf = new PrincipalFrame();
        pf.setVisible(true);
        pf.setLocationRelativeTo(null);
    }
    
}
