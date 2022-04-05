
package Code;

import compilerTools.Grammar;
import compilerTools.Production;
import java.util.ArrayList;

public class SintacticAnalysis {
    
    String analysisText = "";
    public SintacticAnalysis(){
        
    }
    
    public void analysis(Grammar g, ArrayList<Production> productions){
        
        //Se eliminan los errores
        g.delete(new String[]{"ERROR", "ERROR_1", "ERROR_2"}, 1);
        g.finalLineColumn();
        //g.initialLineColumn();
        
        //Declaracion de funcion
        g.group("DEC_FUNCION", "GATO VOID IDENTIFICADOR");
                    //Incorrectos: void
        g.group("DEC_FUNCION", "VOID IDENTIFICADOR", true,
                2, "Error Sintactico({}): Falta el simbolo(gato) en la funcion (funcion)  [Linea: #, Caracter: %]");
        g.group("DEC_FUNCION", "GATO VOID", true,
                2, "Error Sintactico({}): Falta un identificador (funcion)  [Linea: #, Caracter: %]"); 
        g.group("DEC_FUNCION", "GATO IDENTIFICADOR", true,
                2, "Error Sintactico({}): Falta palabra reservada (funcion)  [Linea: #, Caracter: %]"); 
        
        g.group("DEC_FUNCION_RET", "GATO (INT | FLOAT | CHAR | CADENA) IDENTIFICADOR", true);


        
        //Parametros
        g.group("PARAMETROS", "SIMB_SENT (INT | FLOAT | CHAR | CADENA) IDENTIFICADOR", true);
        
        
        //LLamada de la funcion
        g.group("LLAMADA_FUNCT", "IDENTIFICADOR PARENTESIS_A ((NUMERO | IDENTIFICADOR | (COMILLAS (NUMERO | IDENTIFICADOR) COMILLAS) | "
                + "(C_SIMPLE (NUMERO | IDENTIFICADOR) C_SIMPLE) | (COMILLAS COMILLAS) | (C_SIMPLE C_SIMPLE))"
                + " (COMA (NUMERO | IDENTIFICADOR | (COMILLAS (NUMERO | IDENTIFICADOR) COMILLAS) | "
                + "(C_SIMPLE (NUMERO | IDENTIFICADOR) C_SIMPLE) | (COMILLAS COMILLAS) | (C_SIMPLE C_SIMPLE)))+)* PARENTESIS_C PUNTO_COMA");
            //Errores
        g.group("LLAMADA_FUNCT", "IDENTIFICADOR PARENTESIS_A ((NUMERO | IDENTIFICADOR | (COMILLAS (NUMERO | IDENTIFICADOR) COMILLAS) | "
                + "(C_SIMPLE (NUMERO | IDENTIFICADOR) C_SIMPLE) | (COMILLAS COMILLAS) | (C_SIMPLE C_SIMPLE))"
                + " (COMA (NUMERO | IDENTIFICADOR | (COMILLAS (NUMERO | IDENTIFICADOR) COMILLAS) | "
                + "(C_SIMPLE (NUMERO | IDENTIFICADOR) C_SIMPLE) | (COMILLAS COMILLAS) | (C_SIMPLE C_SIMPLE)))+)* PARENTESIS_C", true,
                3, "Error Sintactico({}): Falto punto y coma (llamada funcion) [Linea: #, Caracter: %]");
        
        g.group("LLAMADA_FUNCT", "IDENTIFICADOR PARENTESIS_A ((NUMERO | IDENTIFICADOR | (COMILLAS (NUMERO | IDENTIFICADOR) COMILLAS) | "
                + "(C_SIMPLE (NUMERO | IDENTIFICADOR) C_SIMPLE) | (COMILLAS COMILLAS) | (C_SIMPLE C_SIMPLE))"
                + " ((COMA)? (NUMERO | IDENTIFICADOR | (COMILLAS (NUMERO | IDENTIFICADOR) COMILLAS) | "
                + "(C_SIMPLE (NUMERO | IDENTIFICADOR) C_SIMPLE) | (COMILLAS COMILLAS) | (C_SIMPLE C_SIMPLE)))+)* PARENTESIS_C PUNTO_COMA", true,
                4, "Error Sintactico({}): Falto coma(s) (llamada funcion) [Linea: #, Caracter: %]");
        

        /*Declaraciones: Tipos de datos */
                //Correctos
        /*g.group("DECLARACION", "TIPO_DATO IDENTIFICADOR ASIGNACION (IDENTIFICADOR | NUMERO | (C_SIMPLE IDENTIFICADOR C_SIMPLE)) "
                + "(OP_ARIT (IDENTIFICADOR | NUMERO | (C_SIMPLE IDENTIFICADOR C_SIMPLE)))* PUNTO_COMA", true, productions);
        g.group("DECLARACION", "TIPO_DATO IDENTIFICADOR PUNTO_COMA", true, productions);
        g.group("DECLARACION", "TIPO_DATO IDENTIFICADOR ASIGNACION (IDENTIFICADOR | NUMERO | (C_SIMPLE IDENTIFICADOR C_SIMPLE)) PUNTO_COMA", true, productions);
        g.group("DECLARACION", "TIPO_DATO IDENTIFICADOR PUNTO_COMA", true, productions);
                //Incorrectos
        g.group("DECLARACION", "TIPO_DATO IDENTIFICADOR ASIGNACION (IDENTIFICADOR | NUMERO | (C_SIMPLE IDENTIFICADOR C_SIMPLE)) "
                + "(IDENTIFICADOR | NUMERO)* PUNTO_COMA", true,
                3, "Error Sintactico({}): Falto un operador (variable)  [Linea: #, Caracter: %]");
        g.group("DECLARACION", "TIPO_DATO IDENTIFICADOR ASIGNACION (IDENTIFICADOR | NUMERO | (C_SIMPLE IDENTIFICADOR C_SIMPLE)) "
                + "OP_ARIT PUNTO_COMA", true,
                4, "Error Sintactico({}): Falto un identificador (variable)  [Linea: #, Caracter: %]");
        g.group("DECLARACION", "TIPO_DATO IDENTIFICADOR ASIGNACION (IDENTIFICADOR | NUMERO | (C_SIMPLE IDENTIFICADOR C_SIMPLE)) "
                + "(OP_ARIT (IDENTIFICADOR | NUMERO | (C_SIMPLE IDENTIFICADOR C_SIMPLE)))*", true,
                5, "Error Sintactico({}): Falto punto y coma (variable)  [Linea: #, Caracter: %]");        
        
        g.group("DECLARACION", "TIPO_DATO PUNTO_COMA", true,
                6, "Error Sintactico({}): Falto crear un nombre para la variable (variable)  [Linea: #, Caracter: %]");
        g.group("DECLARACION", "TIPO_DATO ASIGNACION (IDENTIFICADOR | NUMERO | (C_SIMPLE IDENTIFICADOR C_SIMPLE)) PUNTO_COMA", true,
                7, "Error Sintactico({}): Falto crear un nombre para la variable (variable)  [Linea: #, Caracter: %]");
        g.group("DECLARACION", "TIPO_DATO IDENTIFICADOR ASIGNACION (IDENTIFICADOR | NUMERO | (C_SIMPLE IDENTIFICADOR C_SIMPLE))", true,
                8, "Error Sintactico({}): Falto ($ o ;) (variable)  [Linea: #, Caracter: %]");
        g.group("DECLARACION", "TIPO_DATO IDENTIFICADOR", true,
                9, "Error Sintactico({}): Falto ($ o ;)  (variable)  [Linea: #, Caracter: %]");
        
                    //Sin declaracion
        g.group("DECLARACION", "TIPO_DATO", true,
                10, "Error Sintactico({}): No esta en una declaracion correcta (variable) [Linea: #, Caracter: %]");*/
        
        /*Declaraciones: Enteros */
                //Correctos
        g.group("DECLARACION", "INT IDENTIFICADOR ASIGNACION (IDENTIFICADOR | NUMERO) "
                + "OP_ARIT (IDENTIFICADOR | NUMERO) PUNTO_COMA", true, productions);//7
        g.group("DECLARACION", "INT IDENTIFICADOR ASIGNACION (IDENTIFICADOR | NUMERO) PUNTO_COMA", true, productions);//5
        g.group("DECLARACION", "INT IDENTIFICADOR PUNTO_COMA", true, productions);//3
        
                //Incorrectos
        g.group("DECLARACION", "INT IDENTIFICADOR ASIGNACION (C_SIMPLE|COMILLAS) (IDENTIFICADOR | NUMERO) (C_SIMPLE|COMILLAS)"
                + " OP_ARIT (((C_SIMPLE|COMILLAS) (IDENTIFICADOR | NUMERO) (C_SIMPLE|COMILLAS))|((IDENTIFICADOR | NUMERO))) PUNTO_COMA", true, 100, "Error Semantico({}): Operando de tipo de dato erroneo(int)  [Linea: #, Caracter: %]");//11
        g.group("DECLARACION", "INT IDENTIFICADOR ASIGNACION (IDENTIFICADOR | NUMERO)"
                + " OP_ARIT (C_SIMPLE|COMILLAS) (IDENTIFICADOR | NUMERO) (C_SIMPLE|COMILLAS) PUNTO_COMA", true, 100, "Error Semantico({}): Operando de tipo de dato erroneo(int)  [Linea: #, Caracter: %]");//9
        g.group("DECLARACION", "INT IDENTIFICADOR ASIGNACION (C_SIMPLE|COMILLAS) (IDENTIFICADOR | NUMERO) (C_SIMPLE|COMILLAS) PUNTO_COMA"
                ,true, 100, "Error Semantico({}): Tipo de dato erroneo(int)  [Linea: #, Caracter: %]");//7
        g.group("DECLARACION", "INT IDENTIFICADOR ASIGNACION (IDENTIFICADOR | NUMERO) (IDENTIFICADOR | NUMERO)* PUNTO_COMA", 
                true,10, "Error Sintactico({}): Falto un operador (variable)  [Linea: #, Caracter: %]");//6
        g.group("DECLARACION", "INT IDENTIFICADOR ASIGNACION (IDENTIFICADOR | NUMERO) OP_ARIT PUNTO_COMA", 
                true,11, "Error Sintactico({}): Falto un identificador (variable)  [Linea: #, Caracter: %]");//6
        g.group("DECLARACION", "INT IDENTIFICADOR ASIGNACION (IDENTIFICADOR | NUMERO) (OP_ARIT (IDENTIFICADOR | NUMERO))*", 
                true,12, "Error Sintactico({}): Falto punto y coma (variable)  [Linea: #, Caracter: %]");//6  
        g.group("DECLARACION", "INT ASIGNACION NUMERO PUNTO_COMA", 
                true,9, "Error Sintactico({}): Falto crear un nombre para la variable (variable)  [Linea: #, Caracter: %]");//4
        g.group("DECLARACION", "INT IDENTIFICADOR ASIGNACION NUMERO", 
                true,10, "Error Sintactico({}): Falto un simbolo ($ o ;)  [Linea: #, Caracter: %]");//4
        g.group("DECLARACION", "INT IDENTIFICADOR PARENTESIS_A", 
                true,7, "Error Sintactico({}): Error en la declaracion de la funcion (funcion, main)  [Linea: #, Caracter: %]");//3
        g.group("DECLARACION", "INT PUNTO_COMA", 
                true,8, "Error Sintactico({}): Falto crear un nombre para la variable (variable)  [Linea: #, Caracter: %]");//2
        g.group("DECLARACION", "INT IDENTIFICADOR", 
                true,11, "Error Sintactico({}): Falto un simbolo ($ o ;) (variable)  [Linea: #, Caracter: %]");//2
        //Sin declaracion
        g.group("DECLARACION", "INT", 
                true,12, "Error Sintactico({}): No esta en una declaracion correcta (variable) [Linea: #, Caracter: %]");//1
        
        /*Declaraciones: Flotantes */
                //Correctos
        g.group("DECLARACION", "FLOAT IDENTIFICADOR ASIGNACION (IDENTIFICADOR | NUMERO) "
                + "OP_ARIT (IDENTIFICADOR | NUMERO) PUNTO_COMA", true, productions);//7
        g.group("DECLARACION", "FLOAT IDENTIFICADOR ASIGNACION (IDENTIFICADOR | NUMERO) PUNTO_COMA", true, productions);//5
        g.group("DECLARACION", "FLOAT IDENTIFICADOR PUNTO_COMA", true, productions);//3
        
                //Incorrectos
        g.group("DECLARACION", "FLOAT IDENTIFICADOR ASIGNACION (C_SIMPLE|COMILLAS) (IDENTIFICADOR | NUMERO) (C_SIMPLE|COMILLAS)"
                + " OP_ARIT (((C_SIMPLE|COMILLAS) (IDENTIFICADOR | NUMERO) (C_SIMPLE|COMILLAS))|((IDENTIFICADOR | NUMERO))) PUNTO_COMA", true, 100, "Error Semantico({}): Operando de tipo de dato erroneo(float)  [Linea: #, Caracter: %]");//11
        g.group("DECLARACION", "FLOAT IDENTIFICADOR ASIGNACION (IDENTIFICADOR | NUMERO)"
                + " OP_ARIT (C_SIMPLE|COMILLAS) (IDENTIFICADOR | NUMERO) (C_SIMPLE|COMILLAS) PUNTO_COMA", true, 100, "Error Semantico({}): Operando de tipo de dato erroneo(float)  [Linea: #, Caracter: %]");//9
        g.group("DECLARACION", "FLOAT IDENTIFICADOR ASIGNACION (C_SIMPLE|COMILLAS) (IDENTIFICADOR | NUMERO) (C_SIMPLE|COMILLAS) PUNTO_COMA"
                ,true, 100, "Error Semantico({}): Tipo de dato erroneo(float)  [Linea: #, Caracter: %]");//7
        g.group("DECLARACION", "FLOAT IDENTIFICADOR ASIGNACION (IDENTIFICADOR | NUMERO) (IDENTIFICADOR | NUMERO)* PUNTO_COMA", 
                true,10, "Error Sintactico({}): Falto un operador (variable)  [Linea: #, Caracter: %]");//6
        g.group("DECLARACION", "FLOAT IDENTIFICADOR ASIGNACION (IDENTIFICADOR | NUMERO) OP_ARIT PUNTO_COMA", 
                true,11, "Error Sintactico({}): Falto un identificador (variable)  [Linea: #, Caracter: %]");//6
        g.group("DECLARACION", "FLOAT IDENTIFICADOR ASIGNACION (IDENTIFICADOR | NUMERO) (OP_ARIT (IDENTIFICADOR | NUMERO))*", 
                true,12, "Error Sintactico({}): Falto punto y coma (variable)  [Linea: #, Caracter: %]");//6  
        g.group("DECLARACION", "FLOAT ASIGNACION NUMERO PUNTO_COMA", 
                true,9, "Error Sintactico({}): Falto crear un nombre para la variable (variable)  [Linea: #, Caracter: %]");//4
        g.group("DECLARACION", "FLOAT IDENTIFICADOR ASIGNACION NUMERO", 
                true,10, "Error Sintactico({}): Falto un simbolo ($ o ;)  [Linea: #, Caracter: %]");//4
        g.group("DECLARACION", "FLOAT IDENTIFICADOR PARENTESIS_A", 
                true,7, "Error Sintactico({}): Error en la declaracion de la funcion (funcion, main)  [Linea: #, Caracter: %]");//3
        g.group("DECLARACION", "FLOAT PUNTO_COMA", 
                true,8, "Error Sintactico({}): Falto crear un nombre para la variable (variable)  [Linea: #, Caracter: %]");//2
        g.group("DECLARACION", "FLOAT IDENTIFICADOR", 
                true,11, "Error Sintactico({}): Falto un simbolo ($ o ;) (variable)  [Linea: #, Caracter: %]");//2
        //Sin declaracion
        g.group("DECLARACION", "FLOAT", 
                true,12, "Error Sintactico({}): No esta en una declaracion correcta (variable) [Linea: #, Caracter: %]");//1
        
        
        /*Declaraciones:  Caracteres */
                //Correctos
        g.group("DECLARACION", "CHAR IDENTIFICADOR ASIGNACION C_SIMPLE (IDENTIFICADOR|NUMERO) C_SIMPLE PUNTO_COMA", true, productions);//6
        g.group("DECLARACION", "CHAR IDENTIFICADOR ASIGNACION IDENTIFICADOR PUNTO_COMA", true, productions);//5
        g.group("DECLARACION", "CHAR IDENTIFICADOR ASIGNACION C_SIMPLE C_SIMPLE PUNTO_COMA", true, productions);//5
        g.group("DECLARACION", "CHAR IDENTIFICADOR PUNTO_COMA", true, productions);//3
                //Incorrectos
        g.group("DECLARACION", "CHAR IDENTIFICADOR ASIGNACION (IDENTIFICADOR | (C_SIMPLE IDENTIFICADOR C_SIMPLE)) "
                + "(OP_ARIT (IDENTIFICADOR | (C_SIMPLE IDENTIFICADOR C_SIMPLE)))* PUNTO_COMA", true,
                13, "Error Semantico({}): El tipo char solo puede almacenar un caracter (variable)  [Linea: #, Caracter: %]");//11
        g.group("DECLARACION", "CHAR ASIGNACION C_SIMPLE (IDENTIFICADOR | NUMERO) C_SIMPLE PUNTO_COMA", true,
                14, "Error Sintactico({}): Falto crear un nombre para la variable (variable)  [Linea: #, Caracter: %]");//6
        g.group("DECLARACION", "CHAR IDENTIFICADOR ASIGNACION C_SIMPLE (IDENTIFICADOR | NUMERO) C_SIMPLE", true,
                15, "Error Sintactico({}): Falto punto y coma (variable)  [Linea: #, Caracter: %]");//6
        g.group("DECLARACION", "CHAR IDENTIFICADOR ASIGNACION C_SIMPLE (IDENTIFICADOR | NUMERO) PUNTO_COMA", true,
                17, "Error Sintactico({}): Faltaro una comilla simple de cierre (variable)  [Linea: #, Caracter: %]");//6
        g.group("DECLARACION", "CHAR IDENTIFICADOR ASIGNACION (IDENTIFICADOR | NUMERO) C_SIMPLE PUNTO_COMA", true,
                18, "Error Sintactico({}): Falto una comilla simple de apertura (variable) [Linea: #, Caracter: %]");//6
        g.group("DECLARACION", "CHAR IDENTIFICADOR ASIGNACION COMILLAS (IDENTIFICADOR|NUMERO) COMILLAS PUNTO_COMA", true, 31, 
                "Error Semantico({}): Tipo de dato invalido (char)  [Linea: #, Caracter: %]");//6
        g.group("DECLARACION", "CHAR IDENTIFICADOR ASIGNACION NUMERO PUNTO_COMA", true,
                13, "Error Semantico({}): Tipo de dato invalido (char)  [Linea: #, Caracter: %]");//5 
        g.group("DECLARACION", "CHAR IDENTIFICADOR PARENTESIS_A", true,
                13, "Error Sintactico({}): Error en la declaracion de la funcion (funcion, main)  [Linea: #, Caracter: %]");//3
        g.group("DECLARACION", "CHAR PUNTO_COMA", true,
                13, "Error Sintactico({}): Falto crear un nombre para la variable (variable)  [Linea: #, Caracter: %]");//2
                //Sin declaracion
        g.group("DECLARACION", "CHAR", true,
                19, "Error Sintactico({}): No esta en una declaracion correcta (caracter) [Linea: #, Caracter: %]");
        
        
        /*Declaraciones:  Cadenas */
                //Correctos
        g.group("DECLARACION", "CADENA IDENTIFICADOR ASIGNACION (IDENTIFICADOR | (COMILLAS IDENTIFICADOR COMILLAS)) "
                + "OP_ARIT (IDENTIFICADOR | (COMILLAS IDENTIFICADOR COMILLAS)) PUNTO_COMA", true,productions);//11
        g.group("DECLARACION", "CADENA IDENTIFICADOR ASIGNACION COMILLAS (IDENTIFICADOR|NUMERO) COMILLAS PUNTO_COMA", true, productions);//6
        g.group("DECLARACION", "CADENA IDENTIFICADOR ASIGNACION IDENTIFICADOR PUNTO_COMA", true, productions);//5
        g.group("DECLARACION", "CADENA IDENTIFICADOR ASIGNACION COMILLAS COMILLAS PUNTO_COMA", true, productions);//5
        g.group("DECLARACION", "CADENA IDENTIFICADOR PUNTO_COMA", true, productions);//3
                //Incorrectos
        g.group("DECLARACION", "CADENA IDENTIFICADOR ASIGNACION (IDENTIFICADOR | (COMILLAS IDENTIFICADOR COMILLAS)) "
                + "OP_ARIT (C_SIMPLE IDENTIFICADOR C_SIMPLE) PUNTO_COMA", true,
                13, "Error Semantico({}): Operandos incorrectos (variable)  [Linea: #, Caracter: %]");//11
        g.group("DECLARACION", "CADENA IDENTIFICADOR ASIGNACION C_SIMPLE IDENTIFICADOR C_SIMPLE "
                + "OP_ARIT (IDENTIFICADOR | (COMILLAS IDENTIFICADOR COMILLAS)) PUNTO_COMA", true,
                13, "Error Semantico({}): Operandos incorrectos (variable)  [Linea: #, Caracter: %]");//11
        g.group("DECLARACION", "CADENA IDENTIFICADOR ASIGNACION C_SIMPLE IDENTIFICADOR C_SIMPLE "
                + "OP_ARIT C_SIMPLE IDENTIFICADOR C_SIMPLE PUNTO_COMA", true,
                13, "Error Semantico({}): Operandos incorrectos (variable)  [Linea: #, Caracter: %]");//11
        g.group("DECLARACION", "CADENA ASIGNACION COMILLAS (IDENTIFICADOR | NUMERO) COMILLAS PUNTO_COMA", true,
                14, "Error Sintactico({}): Falto crear un nombre para la variable (variable)  [Linea: #, Caracter: %]");//6
        g.group("DECLARACION", "CADENA IDENTIFICADOR ASIGNACION COMILLAS (IDENTIFICADOR | NUMERO) COMILLAS", true,
                15, "Error Sintactico({}): Falto punto y coma (variable)  [Linea: #, Caracter: %]");//6
        g.group("DECLARACION", "CADENA IDENTIFICADOR ASIGNACION COMILLAS (IDENTIFICADOR | NUMERO) PUNTO_COMA", true,
                17, "Error Sintactico({}): Faltaron comillas de cierre (variable)  [Linea: #, Caracter: %]");//6
        g.group("DECLARACION", "CADENA IDENTIFICADOR ASIGNACION (IDENTIFICADOR | NUMERO) COMILLAS PUNTO_COMA", true,
                    18, "Error Sintactico({}): Faltaron comillas de apertura (variable) [Linea: #, Caracter: %]");//6
        g.group("DECLARACION", "CADENA IDENTIFICADOR ASIGNACION C_SIMPLE (IDENTIFICADOR|NUMERO) C_SIMPLE PUNTO_COMA", true, 31, 
                "Error Sintactico({}): Simbolo invalido, usar comillas dobles (string)  [Linea: #, Caracter: %]");//6
        g.group("DECLARACION", "CADENA IDENTIFICADOR ASIGNACION NUMERO PUNTO_COMA", true,
                13, "Error Semantico({}): Tipo de dato invalido (string)  [Linea: #, Caracter: %]");//5 
        g.group("DECLARACION", "CADENA IDENTIFICADOR ASIGNACION NUMERO PUNTO_COMA", true,
                16, "Error Sintactico({}): Faltaron comillas dobles (variable)  [Linea: #, Caracter: %]");//5
        g.group("DECLARACION", "CADENA IDENTIFICADOR PARENTESIS_A", true,
                13, "Error Sintactico({}): Error en la declaracion de la funcion (funcion, main)  [Linea: #, Caracter: %]");//3
        g.group("DECLARACION", "CADENA PUNTO_COMA", true,
                13, "Error Sintactico({}): Falto crear un nombre para la variable (variable)  [Linea: #, Caracter: %]");//2
                //Sin declaracion
        g.group("DECLARACION", "STRING", true,
                19, "Error Sintactico({}): No esta en una declaracion correcta (caracter) [Linea: #, Caracter: %]");
                
        /* Sentencia boleana|*/
                //Correctos;
        g.group("BOLEANA", "SIMB_SENT (NEG)? IDENTIFICADOR OP_RELACIONAL (OP_BOOL | NUMERO | IDENTIFICADOR "
                + "| COMILLAS COMILLAS | (COMILLAS (IDENTIFICADOR | NUMERO) COMILLAS)) "
                + "OP_LOGICO SIMB_SENT (NEG)? IDENTIFICADOR OP_RELACIONAL (OP_BOOL | NUMERO | IDENTIFICADOR "
                + "| COMILLAS COMILLAS | (COMILLAS (IDENTIFICADOR | NUMERO) COMILLAS))+", false);
        
        g.group("BOLEANA", "SIMB_SENT (NEG)? IDENTIFICADOR OP_RELACIONAL (OP_BOOL | NUMERO | IDENTIFICADOR "
                + "| COMILLAS COMILLAS | (COMILLAS (IDENTIFICADOR | NUMERO) COMILLAS))", true); 

        g.group("BOLEANA", "SIMB_SENT  OP_BOOL", true);
                //Incorrectos
        g.group("BOLEANA", "IDENTIFICADOR OP_RELACIONAL (OP_BOOL | NUMERO | IDENTIFICADOR "
                + "| COMILLAS COMILLAS | COMILLAS (IDENTIFICADOR | NUMERO) COMILLAS)", true,
                30, "Error Sintactico({}): Falta el simbolo($) de variables en sentencias (bool) [Linea: #, Caracter: %]");
        g.group("BOLEANA", "SIMB_SENT IDENTIFICADOR OP_RELACIONAL", true,
                30, "Error Sintactico({}): Falto el valor a relacionar (bool) [Linea: #, Caracter: %]");
        
        g.group("BOLEANA", "(NEG)? IDENTIFICADOR OP_RELACIONAL (OP_BOOL | NUMERO | IDENTIFICADOR "
                + "| COMILLAS COMILLAS | (COMILLAS (IDENTIFICADOR | NUMERO) COMILLAS)) "
                + "OP_LOGICO SIMB_SENT (NEG)? IDENTIFICADOR OP_RELACIONAL (OP_BOOL | NUMERO | IDENTIFICADOR "
                + "| COMILLAS COMILLAS | (COMILLAS (IDENTIFICADOR | NUMERO) COMILLAS))+", true,
                32, "Error Sintactico({}): Falta el simbolo($) de variables en sentencias (bool) [Linea: #, Caracter: %]");
        g.group("BOLEANA", "SIMB_SENT (NEG)? IDENTIFICADOR OP_RELACIONAL (OP_BOOL | NUMERO | IDENTIFICADOR "
                + "| COMILLAS COMILLAS | (COMILLAS (IDENTIFICADOR | NUMERO) COMILLAS)) "
                + "OP_LOGICO (NEG)? IDENTIFICADOR OP_RELACIONAL (OP_BOOL | NUMERO | IDENTIFICADOR "
                + "| COMILLAS COMILLAS | (COMILLAS (IDENTIFICADOR | NUMERO) COMILLAS))+", true,
                32, "Error Sintactico({}): Falta el simbolo($) de variables en sentencias (bool) [Linea: #, Caracter: %]");
        g.group("BOLEANA", "SIMB_SENT (NEG)? IDENTIFICADOR OP_RELACIONAL "
                + "OP_LOGICO SIMB_SENT (NEG)? IDENTIFICADOR OP_RELACIONAL (OP_BOOL | NUMERO | IDENTIFICADOR "
                + "| COMILLAS COMILLAS | (COMILLAS (IDENTIFICADOR | NUMERO) COMILLAS))+", true,
                31, "Error Sintactico({}): Falto la sentencia (bool) [Linea: #, Caracter: %]");
        g.group("BOLEANA", "SIMB_SENT (NEG)? IDENTIFICADOR OP_RELACIONAL (OP_BOOL | NUMERO | IDENTIFICADOR "
                + "| COMILLAS COMILLAS | (COMILLAS (IDENTIFICADOR | NUMERO) COMILLAS)) "
                + "OP_LOGICO SIMB_SENT (NEG)? IDENTIFICADOR OP_RELACIONAL", true,
                31, "Error Sintactico({}): Falto la sentencia (bool) [Linea: #, Caracter: %]");
        
        
        g.group("BOLEANA", "OP_BOOL", true,
                33, "Error Sintactico({}): Falta el simbolo($) de variables en sentencias (bool) [Linea: #, Caracter: %]");
        
        //Sin declaracion: bool
        g.group("BOLEANA", "OP_LOGICO", true,
                34, "Error Sintactico({}): No esta en una declaracion correcta (operador logico) [Linea: #, Caracter: %]");
        
        
        
        //Declaracion for
                //Correctos
        g.group("DECL_FOR", "SIMB_SENT IDENTIFICADOR OP_ATRIBUCION NUMERO", true);
        g.group("DECL_FOR", "SIMB_SENT IDENTIFICADOR OP_INCREMENTO", true);
        g.group("DECL_FOR", "SIMB_SENT OP_INCREMENTO IDENTIFICADOR", true);
        
        g.group("DECLARACION", "IDENTIFICADOR OP_INCREMENTO PUNTO_COMA", true);
        g.group("DECLARACION", "IDENTIFICADOR OP_ATRIBUCION NUMERO PUNTO_COMA", true); 
        g.group("DECLARACION", "OP_INCREMENTO IDENTIFICADOR PUNTO_COMA", true);
        
                //Incorrectos
        g.group("DECL_FOR", "IDENTIFICADOR OP_ATRIBUCION NUMERO", true,
                41, "Error Sintactico({}): Falta el simbolo ($ o ;) en la declaracion (variable) [Linea: #, Caracter: %]");
        g.group("DECL_FOR", "IDENTIFICADOR OP_INCREMENTO", true,
                42, "Error Sintactico({}): Falta el simbolo ($ o ;)  en la declaracion  (varable) [Linea: #, Caracter: %]");
        g.group("DECL_FOR", "OP_INCREMENTO IDENTIFICADOR", true,
                43, "Error Sintactico({}): Falta el simbolo ($ o ;) en la declaracion  (variable) [Linea: #, Caracter: %]");
        
        g.group("DECL_FOR", "SIMB_SENT IDENTIFICADOR OP_ATRIBUCION", true,
                44, "Error Sintactico({}): Falta el valor de atribucion (variable) [Linea: #, Caracter: %]");
        g.group("DECL_FOR", "SIMB_SENT IDENTIFICADOR NUMERO", true,
                45, "Error Sintactico({}): Falta el operador (variable) [Linea: #, Caracter: %]");
        g.group("DECL_FOR", "SIMB_SENT OP_ATRIBUCIONNUMERO", true,
                46, "Error Sintactico({}): Falta un identificador (variable) [Linea: #, Caracter: %]");
        g.group("DECL_FOR", "SIMB_SENT IDENTIFICADOR", true,
                47, "Error Sintactico({}): Falta el operador (variable) [Linea: #, Caracter: %]");
        g.group("DECL_FOR", "SIMB_SENT OP_ATRIBUCION NUMERO", true,
                48, "Error Sintactico({}): Falta un identificador (variable) [Linea: #, Caracter: %]");
        g.group("DECL_FOR", "SIMB_SENT OP_INCREMENTO", true,
                49, "Error Sintactico({}): Falta el identificador (variable) [Linea: #, Caracter: %]");
        
        /* return */
                //Correctos
        g.group("RETORNO", "RETURN (NUMERO | IDENTIFICADOR | OP_BOOL | (COMILLAS IDENTIFICADOR COMILLAS) | "
                + "(COMILLAS COMILLAS) | (C_SIMPLE IDENTIFICADOR C_SIMPLE) | (C_SIMPLE C_SIMPLE)) PUNTO_COMA", true);
        g.group("RETORNO", "RETURN PUNTO_COMA", true); 
                //Incorrectos
        g.group("RETORNO", "RETURN (NUMERO | IDENTIFICADOR | OP_BOOL)", true,
                81, "Error Sintactico({}): Falto punto y coma (return) [Linea: #, Caracter: %]");
                                        //Sin declaracion:return
        g.group("RETORNO", "RETURN", true,
                82, "Error Sintactico({}): No esta en una declaracion correcta (return) [Linea: #, Caracter: %]");
        
                //Producciones y Errores generales de declaraciones
                //Correctos
        g.group("DECLARACION", "IDENTIFICADOR ASIGNACION (IDENTIFICADOR | NUMERO | (COMILLAS IDENTIFICADOR COMILLAS) | (C_SIMPLE IDENTIFICADOR C_SIMPLE)) PUNTO_COMA", true, productions);
        g.group("DECLARACION", "IDENTIFICADOR ASIGNACION (NUMERO | IDENTIFICADOR | (COMILLAS IDENTIFICADOR COMILLAS) | (C_SIMPLE IDENTIFICADOR C_SIMPLE)) "
                + "(OP_ARIT (NUMERO | IDENTIFICADOR | (COMILLAS IDENTIFICADOR COMILLAS) | (C_SIMPLE IDENTIFICADOR C_SIMPLE)))* PUNTO_COMA", true, productions);
        //->
                //Incorrectos
        g.group("DECLARACION", "IDENTIFICADOR ASIGNACION (NUMERO | IDENTIFICADOR | (COMILLAS IDENTIFICADOR COMILLAS) | (C_SIMPLE IDENTIFICADOR C_SIMPLE)) "
                + "(NUMERO | IDENTIFICADOR | (COMILLAS IDENTIFICADOR COMILLAS) | (C_SIMPLE IDENTIFICADOR C_SIMPLE))* PUNTO_COMA", true,
                50, "Error Sintactico({}): Falto un operador (variable)  [Linea: #, Caracter: %]");
        g.group("DECLARACION", "IDENTIFICADOR ASIGNACION (NUMERO | IDENTIFICADOR | (COMILLAS IDENTIFICADOR COMILLAS) | (C_SIMPLE IDENTIFICADOR C_SIMPLE)) "
                + "OP_ARIT PUNTO_COMA", true,
                50, "Error Sintactico({}): Falto un identificador (variable)  [Linea: #, Caracter: %]");
        g.group("DECLARACION", "IDENTIFICADOR ASIGNACION (NUMERO | IDENTIFICADOR | (COMILLAS IDENTIFICADOR COMILLAS) | (C_SIMPLE IDENTIFICADOR C_SIMPLE)) "
                + "(OP_ARIT (NUMERO | IDENTIFICADOR | (COMILLAS IDENTIFICADOR COMILLAS) | (C_SIMPLE IDENTIFICADOR C_SIMPLE)))*", true,
                50, "Error Sintactico({}): Falto punto y coma (variable)  [Linea: #, Caracter: %]");
        
                    //Crear una variable con un tipo de dato que no existe
        g.group("DECLARACION", "IDENTIFICADOR IDENTIFICADOR PUNTO_COMA", true,
                61, "Error Sintactico({}): El tipo de dato no existe (variable)  [Linea: #, Caracter: %]");
        g.group("DECLARACION", "IDENTIFICADOR IDENTIFICADOR", true,
                62, "Error Sintactico({}): El tipo de dato no existe y falta punto y coma (variable)  [Linea: #, Caracter: %]");
                    //Errores en asignacion
        g.group("DECLARACION", "IDENTIFICADOR ASIGNACION (IDENTIFICADOR | NUMERO | (COMILLAS IDENTIFICADOR COMILLAS) | (C_SIMPLE IDENTIFICADOR C_SIMPLE))", true,
                63, "Error Sintactico({}): Falto punto y coma (variable) [Linea: #, Caracter: %]");
        g.group("DECLARACION", "ASIGNACION (IDENTIFICADOR | NUMERO | (COMILLAS IDENTIFICADOR COMILLAS) | (C_SIMPLE IDENTIFICADOR C_SIMPLE)) PUNTO_COMA", true,
                64, "Error Sintactico({}): No se coloco una variable (variable) [Linea: #, Caracter: %]");
        g.group("DECLARACION", "IDENTIFICADOR ASIGNACION PUNTO_COMA", true,
                65, "Error Sintactico({}): No se asigno ningun valor (variable) [Linea: #, Caracter: %]");
        g.group("DECLARACION", "IDENTIFICADOR (IDENTIFICADOR | NUMERO | (COMILLAS IDENTIFICADOR COMILLAS) | (C_SIMPLE IDENTIFICADOR C_SIMPLE)) PUNTO_COMA", true,
                66, "Error Sintactico({}): Falto el operador (variable) [Linea: #, Caracter: %]");
        g.group("DECLARACION", "OP_INCREMENTO PUNTO_COMA", true,
                67, "Error Sintactico({}): No se coloco una variable (variable) [Linea: #, Caracter: %]");
        
        g.group("DECLARACION", "IDENTIFICADOR OP_ATRIBUCION PUNTO_COMA", true,
                68, "Error Sintactico({}): Falto el valor (variable) [Linea: #, Caracter: %]");         
        g.group("DECLARACION", "IDENTIFICADOR PUNTO_COMA", true,
                69, "Error Sintactico({}): Variable no asignada (variable) [Linea: #, Caracter: %]");   
        g.group("DECLARACION", "OP_ATRIBUCION PUNTO_COMA", true,
                70, "Error Sintactico({}): Falto identificador (variable) [Linea: #, Caracter: %]");  
        
        
        /* Sentencias */
         //sentencia(g);


        // Reservada IFELSE
                     //Correctos
        g.loopForFunExecUntilChangeNotDetected(() -> {
            
            g.group("SENT_IFELSE", "IF PARENTESIS_A BOLEANA PARENTESIS_C LLAVE_A (SENTENCIA)* LLAVE_C "
                        + "ELSE LLAVE_A (SENTENCIA)* LLAVE_C", true);
            g.group("SENT_IF", "IF PARENTESIS_A BOLEANA PARENTESIS_C LLAVE_A (SENTENCIA)* LLAVE_C", true);
            g.group("SENT_FOR", "SENTENCIA BOLEANA PUNTO_COMA DECL_FOR", true);
            g.group("FOR_C", "FOR PARENTESIS_A SENT_FOR PARENTESIS_C LLAVE_A (SENTENCIA)* LLAVE_C", true);
            g.group("WHILE_C", "WHILE PARENTESIS_A BOLEANA PARENTESIS_C LLAVE_A (SENTENCIA)* LLAVE_C", true);
            
            g.group("SENTENCIA", "(SENTENCIA | DECLARACION | SENT_IF | SENT_IFELSE | FOR_C | WHILE_C)*", true);
                        
            g.group("SENT_IFELSE", "IF PARENTESIS_A BOLEANA PARENTESIS_C LLAVE_A (SENTENCIA)* LLAVE_C "
                        + "ELSE LLAVE_A (SENTENCIA)* LLAVE_C", true);
        });
                    //Incorrectos
        g.group("SENT_IFELSE", "IF PARENTESIS_A BOLEANA PARENTESIS_C LLAVE_A (SENTENCIA)* LLAVE_C ELSE (SENTENCIA)* LLAVE_C", true,
                90, "Error Sintactico({}): Falto la llave de apertura (if[else]) [Linea: #, Caracter: %]");
        g.group("SENT_IFELSE", "IF PARENTESIS_A BOLEANA PARENTESIS_C LLAVE_A (SENTENCIA)* LLAVE_C ELSE LLAVE_A (SENTENCIA)*", true,
                91, "Error Sintactico({}): Falto la llave de cierre (if[else])  [Linea: #, Caracter: %]");

        g.group("SENT_IFELSE", "IF PARENTESIS_A PARENTESIS_C LLAVE_A (SENTENCIA)* LLAVE_C ELSE LLAVE_A (SENTENCIA)* LLAVE_C", true,
                92, "Error Sintactico({}): Falto expresion a evaluar ([if]else) [Linea: #, Caracter: %]");
        g.group("SENT_IFELSE", "IF PARENTESIS_A BOLEANA PARENTESIS_C (SENTENCIA)* LLAVE_C ELSE LLAVE_A (SENTENCIA)* LLAVE_C", true,
                93, "Error Sintactico({}): Falto la llave de apertura ([if]else) [Linea: #, Caracter: %]");
        g.group("SENT_IFELSE", "IF PARENTESIS_A BOLEANA PARENTESIS_C LLAVE_A (SENTENCIA)* ELSE LLAVE_A (SENTENCIA)* LLAVE_C", true,
                94, "Error Sintactico({}): Falto la llave de cierre ([if]else) [Linea: #, Caracter: %]");
        g.group("SENT_IFELSE", "IF BOLEANA PARENTESIS_C LLAVE_A (SENTENCIA)* LLAVE_C ELSE LLAVE_A (SENTENCIA)* LLAVE_C", true,
                95, "Error Sintactico({}): Falto parentesis de apertura ([if]else) [Linea: #, Caracter: %]");
        g.group("SENT_IFELSE", "IF PARENTESIS_A BOLEANA LLAVE_A (SENTENCIA)* LLAVE_C ELSE LLAVE_A (SENTENCIA)* LLAVE_C", true,
                96, "Error Sintactico({}): Falto parentesis de cierre ([if]else) [Linea: #, Caracter: %]");
                
        // Reservada IF
                    //Correctos
        g.loopForFunExecUntilChangeNotDetected(() -> {
            
            g.group("SENT_IF", "IF PARENTESIS_A BOLEANA PARENTESIS_C LLAVE_A (SENTENCIA)* LLAVE_C", true);
            
            g.group("SENT_FOR", "SENTENCIA BOLEANA PUNTO_COMA DECL_FOR", true);
            g.group("FOR_C", "FOR PARENTESIS_A SENT_FOR PARENTESIS_C LLAVE_A (SENTENCIA)* LLAVE_C", true);
            g.group("WHILE_C", "WHILE PARENTESIS_A BOLEANA PARENTESIS_C LLAVE_A (SENTENCIA)* LLAVE_C", true);
            
            g.group("SENTENCIA", "(SENTENCIA | DECLARACION | SENT_IF | SENT_IFELSE | FOR_C | WHILE_C)*", true);
            
            g.group("SENT_IF", "IF PARENTESIS_A BOLEANA PARENTESIS_C LLAVE_A (SENTENCIA)* LLAVE_C", true);
        });
                    //Incorrectos
        g.group("SENT_IF", "IF PARENTESIS_A PARENTESIS_C LLAVE_A (SENTENCIA)* LLAVE_C", true,
                101, "Error Sintactico({}): Falto una expresion a evaluar (if) [Linea: #, Caracter: %]");
        g.group("SENT_IF", "IF BOLEANA PARENTESIS_C LLAVE_A (SENTENCIA)* LLAVE_C", true,
                102, "Error Sintactico({}): Falto el parentesis de apertura (if) [Linea: #, Caracter: %]");
        g.group("SENT_IF", "IF PARENTESIS_A BOLEANA LLAVE_A (SENTENCIA)* LLAVE_C", true,
                103, "Error Sintactico({}): Falto el parentesis de cierre (if) [Linea: #, Caracter: %]");
        g.group("SENT_IF", "IF PARENTESIS_A BOLEANA PARENTESIS_C (SENTENCIA)* LLAVE_C", true,
                44, "Error Sintactico({}): Falto la llave de apertura (if) [Linea: #, Caracter: %]");
        g.group("SENT_IF", "IF PARENTESIS_A BOLEANA PARENTESIS_C LLAVE_A (SENTENCIA)*", true,
                104, "Error Sintactico({}): Falto la llave de cierre (if) [Linea: #, Caracter: %]");

        
                                        //Sin declaracion: ifelse
        g.group("SENT_IF", "IF", true,
                105, "Error Sintactico({}): No esta en una declaracion correcta (if) [Linea: #, Caracter: %]");
        g.group("SENT_IF", "ELSE", true,
                106, "Error Sintactico({}): No esta en una declaracion correcta (else) [Linea: #, Caracter: %]");
  
        
        //Sentencia for
                //Correctos
        g.group("SENT_FOR", "SENTENCIA BOLEANA PUNTO_COMA DECL_FOR", true);
                //Incorrectos
        g.group("SENT_FOR", "BOLEANA PUNTO_COMA DECL_FOR", true,
                111, "Error Sintactico({}): Falto la sentencia (Error;Bien;Bien) (for()) [Linea: #, Caracter: %]");
        g.group("SENT_FOR", "SENTENCIA PUNTO_COMA DECL_FOR", true,
                112, "Error Sintactico({}): Falto la sentencia boleana (Bien;Error;Bien) (for()) [Linea: #, Caracter: %]");
        g.group("SENT_FOR", "SENTENCIA BOLEANA DECL_FOR", true,
                113, "Error Sintactico({}): Falto punto y coma (Bien;Error;Error) (for()) [Linea: #, Caracter: %]");
        g.group("SENT_FOR", "SENTENCIA BOLEANA PUNTO_COMA", true,
                114, "Error Sintactico({}): Falto la operacion (Bien;Bien;Error) (for()) (Bien;Bien;Error) (for()) [Linea: #, Caracter: %]");
        g.group("SENT_FOR", "SENTENCIA BOLEANA PUNTO_COMA", true,
                115, "Error Sintactico({}): Falto la operacion (Bien;Bien;Error) (for()) [Linea: #, Caracter: %]");
        
        //For completo
                //Correctos
        g.loopForFunExecUntilChangeNotDetected(() -> {
            g.group("WHILE_C", "WHILE PARENTESIS_A BOLEANA PARENTESIS_C LLAVE_A (SENTENCIA)* LLAVE_C", true);
            
            g.group("SENTENCIA", "(SENTENCIA | DECLARACION | SENT_IF | SENT_IFELSE | FOR_C | WHILE_C)*", true);
            
            g.group("FOR_C", "FOR PARENTESIS_A SENT_FOR PARENTESIS_C LLAVE_A (SENTENCIA)* LLAVE_C", true);
        });
                //Incorrectos
        g.group("FOR_C", "FOR SENT_FOR PARENTESIS_C LLAVE_A (SENTENCIA)* LLAVE_C", true,
                116, "Error Sintactico({}): Falto el parentesis de apertura (for) [Linea: #, Caracter: %]");
        g.group("FOR_C", "FOR PARENTESIS_A SENT_FOR LLAVE_A (SENTENCIA)* LLAVE_C", true,
                117, "Error Sintactico({}): Falto el parentesis de cierre (for) [Linea: #, Caracter: %]");
        g.group("FOR_C", "FOR PARENTESIS_A SENT_FOR PARENTESIS_C (SENTENCIA)* LLAVE_C", true,
                118, "Error Sintactico({}): Falto la lave de apertura (for) [Linea: #, Caracter: %]");
        g.group("FOR_C", "FOR PARENTESIS_A SENT_FOR PARENTESIS_C LLAVE_A (SENTENCIA)*", true,
                119, "Error Sintactico({}): Falto la lave de cierre (for) [Linea: #, Caracter: %]");
        g.group("FOR_C", "FOR PARENTESIS_A PARENTESIS_C LLAVE_A (SENTENCIA)* LLAVE_C", true,
                120, "Error Sintactico({}): Falto la sentencia en for (for) [Linea: #, Caracter: %]");
        //Sin declaracion: for
        g.group("FOR_C", "FOR", true,
                121, "Error Sintactico({}): No esta en una declaracion correcta (for) [Linea: #, Caracter: %]");

        //While
                //Correctos
        g.loopForFunExecUntilChangeNotDetected(() -> {
            g.group("SENTENCIA", "(SENTENCIA | DECLARACION | SENT_IF | SENT_IFELSE | FOR_C | WHILE_C)*", true);
            
            g.group("WHILE_C", "WHILE PARENTESIS_A BOLEANA PARENTESIS_C LLAVE_A (SENTENCIA)* LLAVE_C", true);
        });
        
                //Incorrectos
        g.group("WHILE_C", "WHILE BOLEANA PARENTESIS_C LLAVE_A (SENTENCIA)* LLAVE_C", true,
                131, "Error Sintactico({}): Falto el parentesis de apertura (while) [Linea: #, Caracter: %]");
        g.group("WHILE_C", "WHILE PARENTESIS_A BOLEANA LLAVE_A (SENTENCIA)* LLAVE_C", true,
                132, "Error Sintactico({}): Falto el parentesis de cierre (while) [Linea: #, Caracter: %]");
        g.group("WHILE_C", "WHILE PARENTESIS_A BOLEANA PARENTESIS_C (SENTENCIA)* LLAVE_C", true,
                133, "Error Sintactico({}): Falto la llave de apertura (while) [Linea: #, Caracter: %]");
        g.group("WHILE_C", "WHILE PARENTESIS_A BOLEANA PARENTESIS_C LLAVE_A (SENTENCIA)*", true,
                134, "Error Sintactico({}): Falto la llave de cierre (while) [Linea: #, Caracter: %]");
        g.group("WHILE_C", "WHILE PARENTESIS_A PARENTESIS_C LLAVE_A (SENTENCIA)* LLAVE_C", true,
                135, "Error Sintactico({}): Falto la espresion a evaluar (while) [Linea: #, Caracter: %]");  
        //Sin declaracion: while
        g.group("WHILE_C", "WHILE", true,
                136, "Error Sintactico({}): No esta en una declaracion correcta (while) [Linea: #, Caracter: %]");
        
        
        // Funciones
                    //Correctos
        g.group("FUNCION", "DEC_FUNCION_RET PARENTESIS_A (PARAMETROS (COMA PARAMETROS)+)* PARENTESIS_C "
                + "LLAVE_A (SENTENCIA)* RETORNO LLAVE_C", false, productions);
        g.group("FUNCION", "DEC_FUNCION_RET PARENTESIS_A (PARAMETROS)* PARENTESIS_C "
                + "LLAVE_A (SENTENCIA)* RETORNO LLAVE_C", true, productions);
        
        g.group("FUNCION", "DEC_FUNCION PARENTESIS_A (PARAMETROS (COMA PARAMETROS)+)* PARENTESIS_C "
                + "LLAVE_A (SENTENCIA)* LLAVE_C", false, productions);
        g.group("FUNCION", "DEC_FUNCION PARENTESIS_A (PARAMETROS)* PARENTESIS_C "
                + "LLAVE_A (SENTENCIA)* LLAVE_C", true, productions);
                    //Incorrectos
                            //Con retorno
        g.group("FUNCION", "DEC_FUNCION_RET PARENTESIS_A (PARAMETROS (COMA PARAMETROS)+)* PARENTESIS_C "
                + "LLAVE_A (SENTENCIA)* LLAVE_C", true,
                138, "Error Sintactico({}): Falto retornar un valor (funcion) [Linea: #, Caracter: %]");
        g.group("FUNCION", "DEC_FUNCION_RET (PARAMETROS (COMA PARAMETROS)+)* PARENTESIS_C "
                + "LLAVE_A (SENTENCIA)* RETORNO LLAVE_C", true,
                139, "Error Sintactico({}): Falto el parentesis de apertura (funcion) [Linea: #, Caracter: %]");
        g.group("FUNCION", "DEC_FUNCION_RET PARENTESIS_A (PARAMETROS (COMA PARAMETROS)+)* "
                + "LLAVE_A (SENTENCIA)* RETORNO LLAVE_C", true,
                140, "Error Sintactico({}): Falto el parentesis de cierre (funcion) [Linea: #, Caracter: %]");
        g.group("FUNCION", "DEC_FUNCION_RET PARENTESIS_A (PARAMETROS (COMA PARAMETROS)+)* PARENTESIS_C "
                + "(SENTENCIA)* RETORNO LLAVE_C", true,
                141, "Error Sintactico({}): Falto la llave de apertura (funcion) [Linea: #, Caracter: %]");
        g.group("FUNCION", "DEC_FUNCION_RET PARENTESIS_A (PARAMETROS (COMA PARAMETROS)+)* PARENTESIS_C "
                + "LLAVE_A (SENTENCIA)* RETORNO", true,
                142, "Error Sintactico({}): Falto la llave de cierre (funcion) [Linea: #, Caracter: %]");
        g.group("FUNCION", "DEC_FUNCION_RET PARENTESIS_A (PARAMETROS (PARAMETROS)+)* PARENTESIS_C "
                + "LLAVE_A (SENTENCIA)* RETORNO LLAVE_C", true,
                143, "Error Sintactico({}): Falto una coma en los parametros (funcion) [Linea: #, Caracter: %]");
        g.group("FUNCION", "DEC_FUNCION_RET PARENTESIS_A (PARAMETROS (COMA PARAMETROS)+)* PARENTESIS_C "
                + "LLAVE_A (SENTENCIA)*", true,
                138, "Error Sintactico({}): Falto la llave de cierre(funcion) [Linea: #, Caracter: %]");
        
        g.group("FUNCION", "DEC_FUNCION_RET PARENTESIS_A (PARAMETROS)* PARENTESIS_C "
                + "LLAVE_A (SENTENCIA)* LLAVE_C", true,
                144, "Error Sintactico({}): Falto retornar un valor (funcion) [Linea: #, Caracter: %]");
        g.group("FUNCION", "DEC_FUNCION_RET (PARAMETROS)* PARENTESIS_C "
                + "LLAVE_A (SENTENCIA)* RETORNO LLAVE_C", true,
                145, "Error Sintactico({}): Falto el parentesis de apertura (funcion) [Linea: #, Caracter: %]");
        g.group("FUNCION", "DEC_FUNCION_RET PARENTESIS_A (PARAMETROS)* "
                + "LLAVE_A (SENTENCIA)* RETORNO LLAVE_C", true,
                146, "Error Sintactico({}): Falto el parentesis de cierre (funcion) [Linea: #, Caracter: %]");
        g.group("FUNCION", "DEC_FUNCION_RET PARENTESIS_A (PARAMETROS)* PARENTESIS_C "
                + "(SENTENCIA)* RETORNO LLAVE_C", true,
                147, "Error Sintactico({}): Falto la llave de apertura (funcion) [Linea: #, Caracter: %]");
        g.group("FUNCION", "DEC_FUNCION_RET PARENTESIS_A (PARAMETROS)* PARENTESIS_C "
                + "LLAVE_A (SENTENCIA)* RETORNO", true,
                148, "Error Sintactico({}): Falto la llave de cierre (funcion) [Linea: #, Caracter: %]");
        g.group("FUNCION", "DEC_FUNCION_RET PARENTESIS_A (PARAMETROS)* PARENTESIS_C "
                + "LLAVE_A (SENTENCIA)*", true,
                144, "Error Sintactico({}): Falto la llave de cierre (funcion) [Linea: #, Caracter: %]");
          
                            //funciones void
        g.group("FUNCION", "DEC_FUNCION (PARAMETROS (COMA PARAMETROS)+)* PARENTESIS_C "
                + "LLAVE_A (SENTENCIA)* LLAVE_C", true,
                149, "Error Sintactico({}): Falto el parentesis de apertura (funcion) [Linea: #, Caracter: %]");
        g.group("FUNCION", "DEC_FUNCION PARENTESIS_A (PARAMETROS (COMA PARAMETROS)+)* "
                + "LLAVE_A (SENTENCIA)* LLAVE_C", true,
                150, "Error Sintactico({}): Falto el parentesis de cierre (funcion) [Linea: #, Caracter: %]");
        g.group("FUNCION", "DEC_FUNCION PARENTESIS_A (PARAMETROS (COMA PARAMETROS)+)* PARENTESIS_C "
                + "(SENTENCIA)* LLAVE_C", true,
                151, "Error Sintactico({}): Falto la llave de apertura (funcion) [Linea: #, Caracter: %]");
        g.group("FUNCION", "DEC_FUNCION PARENTESIS_A (PARAMETROS (COMA PARAMETROS)+)* PARENTESIS_C "
                + "LLAVE_A (SENTENCIA)*", true,
                152, "Error Sintactico({}): Falto la llave de cierre (funcion) [Linea: #, Caracter: %]");
        g.group("FUNCION", "DEC_FUNCION PARENTESIS_A (PARAMETROS (PARAMETROS)+)* PARENTESIS_C "
                + "LLAVE_A (SENTENCIA)* LLAVE_C", true,
                153, "Error Sintactico({}): Falto una coma en los parametros (funcion) [Linea: #, Caracter: %]");
        
        g.group("FUNCION", "DEC_FUNCION (PARAMETROS)* PARENTESIS_C "
                + "LLAVE_A (SENTENCIA)* LLAVE_C", true,
                154, "Error Sintactico({}): Falto el parentesis de apertura (funcion) [Linea: #, Caracter: %]");
        g.group("FUNCION", "DEC_FUNCION PARENTESIS_A (PARAMETROS)* "
                + "LLAVE_A (SENTENCIA)* LLAVE_C", true,
                155, "Error Sintactico({}): Falto el parentesis de cierre (funcion) [Linea: #, Caracter: %]");
        g.group("FUNCION", "DEC_FUNCION PARENTESIS_A (PARAMETROS)* PARENTESIS_C "
                + "(SENTENCIA)* LLAVE_C", true,
                156, "Error Sintactico({}): Falto la llave de apertura (funcion) [Linea: #, Caracter: %]");
        g.group("FUNCION", "DEC_FUNCION PARENTESIS_A (PARAMETROS)* PARENTESIS_C "
                + "LLAVE_A (SENTENCIA)*", true,
                157, "Error Sintactico({}): Falto la llave de cierre (funcion) [Linea: #, Caracter: %]");
        
        
        
         /* Sentencias */
         sentencia(g);

         /* MAIN */
                //Correctos
         g.group("PRINCIPAL", "MAIN PARENTESIS_A PARENTESIS_C LLAVE_A (SENTENCIA)* LLAVE_C", true, productions);
                //Incorrectos
         g.group("PRINCIPAL", "MAIN PARENTESIS_C LLAVE_A (SENTENCIA)* LLAVE_C", true,
                141, "Error Sintactico({}): Falto el parentesis de apertura (main) [Linea: #, Caracter: %]");
         g.group("PRINCIPAL", "MAIN PARENTESIS_A LLAVE_A (SENTENCIA)* LLAVE_C", true,
                142, "Error Sintactico({}): Falto el parentesis de cierre (main) [Linea: #, Caracter: %]");
         g.group("PRINCIPAL", "MAIN PARENTESIS_A PARENTESIS_C (SENTENCIA)* LLAVE_C", true,
                143, "Error Sintactico({}): Falto la llave de apertura (main) [Linea: #, Caracter: %]");
         g.group("PRINCIPAL", "MAIN PARENTESIS_A PARENTESIS_C LLAVE_A (SENTENCIA)*", true,
                144, "Error Sintactico({}): Falto la llave de cierre (main) [Linea: #, Caracter: %]");
                    //Parentesis y  llaves
         g.group("PRINCIPAL", "MAIN PARENTESIS_C LLAVE_A (SENTENCIA)* LLAVE_C", true,
                145, "Error Sintactico({}): Falto el parentesis de apertura (main) [Linea: #, Caracter: %]");
         g.group("PRINCIPAL", "MAIN PARENTESIS_A LLAVE_A (SENTENCIA)* LLAVE_C", true,
                146, "Error Sintactico({}): Falto el parentesis de cierre (main) [Linea: #, Caracter: %]");
         g.group("PRINCIPAL", "MAIN PARENTESIS_A PARENTESIS_C (SENTENCIA)* LLAVE_C", true,
                147, "Error Sintactico({}): Falto la llave de apertura (main) [Linea: #, Caracter: %]");
         g.group("PRINCIPAL", "MAIN PARENTESIS_A PARENTESIS_C LLAVE_A (SENTENCIA)*", true,
                148, "Error Sintactico({}): Falto la llave de cierre (main) [Linea: #, Caracter: %]");
         
                    //Error al escribir funcion principal
         g.group("PRINCIPAL", "(MAIN | IDENTIFICADOR) PARENTESIS_A PARENTESIS_C LLAVE_A (SENTENCIA)* LLAVE_C", true,
                149, "Error Sintactico({}): Error en la declaracion de la funcion principal (main) [Linea: #, Caracter: %]");
                                         //Sin declaracion: main
        g.group("PRINCIPAL", "MAIN", true,
                150, "Error Sintactico({}): No esta en una declaracion correcta (main) [Linea: #, Caracter: %]");

       g.show();
    }
    
    private void sentencia(Grammar g){
        
        g.group("SENT_IFELSE", "IF PARENTESIS_A BOLEANA PARENTESIS_C LLAVE_A (SENTENCIA)* LLAVE_C "
                    + "ELSE LLAVE_A (SENTENCIA)* LLAVE_C", true);
        g.group("SENT_IF", "IF PARENTESIS_A BOLEANA PARENTESIS_C LLAVE_A (SENTENCIA)* LLAVE_C", true);
        g.group("SENT_FOR", "SENTENCIA BOLEANA PUNTO_COMA DECL_FOR", true);
        g.group("FOR_C", "FOR PARENTESIS_A SENT_FOR PARENTESIS_C LLAVE_A (SENTENCIA)* LLAVE_C", true);
        g.group("WHILE_C", "WHILE PARENTESIS_A BOLEANA PARENTESIS_C LLAVE_A (SENTENCIA)* LLAVE_C", true);
        
        g.group("SENTENCIA", "(SENTENCIA | DECLARACION | SENT_IF | SENT_IFELSE | FOR_C | WHILE_C | LLAMADA_FUNCT)*", true);
    }
}
