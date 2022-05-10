
package Code;


public class Registers {
    
    float ax = 0;
    float bx = 0;
    float cx = 0;
    float dx = 0;
    
    float ah = 0;
    float al = 0;
    float bh = 0;
    float bl = 0;
    float ch = 0;
    float cl = 0;
    float dh = 0;
    float dl = 0;
    
    public Registers(){
        
    }
    
    public void showRegisters(){
        System.out.println("*** XX ***");
        System.out.println(" - ax: " + ax);
        System.out.println(" - bx: " + bx);
        System.out.println(" - cx: " + cx);
        System.out.println(" - dx: " + dx);
        
        System.out.println("*** XH XL ***");
        System.out.println(" - ah: " + ah);
        System.out.println(" - al: " + al);
        System.out.println(" - bh: " + bh);
        System.out.println(" - bl: " + bl);
        System.out.println(" - ch: " + ch);
        System.out.println(" - cl: " + cl);
        System.out.println(" - dh: " + dh);
        System.out.println(" - dl: " + dl);
    }
}
