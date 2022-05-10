
package Code;

import java.util.Hashtable;


public class Registers {
    
    Hashtable<String, String> regs;
    
    public Registers(){
        regs = new Hashtable<String, String>();
        
        regs.put("ax", "0");
        regs.put("bx", "0");
        regs.put("cx", "0");
        regs.put("dx", "0");
        
        regs.put("ah", "0");
        regs.put("al", "0");
        regs.put("bh", "0");
        regs.put("bl", "0");
        regs.put("ch", "0");
        regs.put("cl", "0");
        regs.put("dh", "0");
        regs.put("dl", "0");
    }
    
    public void showRegisters(){
        System.out.println("*** XX ***");
        System.out.println(" - ax: " + regs.get("ax"));
        System.out.println(" - bx: " + regs.get("bx"));
        System.out.println(" - cx: " + regs.get("cx"));
        System.out.println(" - dx: " + regs.get("dx"));
        
        System.out.println("*** XH XL ***");
        System.out.println(" - ah: " + regs.get("ah"));
        System.out.println(" - al: " + regs.get("al"));
        System.out.println(" - bh: " + regs.get("bh"));
        System.out.println(" - bl: " + regs.get("bl"));
        System.out.println(" - ch: " + regs.get("ch"));
        System.out.println(" - cl: " + regs.get("cl"));
        System.out.println(" - dh: " + regs.get("dh"));
        System.out.println(" - dl: " + regs.get("dl"));
    }
}
