
package Code;

import FileControl.AbrirArchivo;
import FileControl.GenerarRuta;
import compilerTools.ErrorLSSL;
import compilerTools.Functions;
import compilerTools.Grammar;
import compilerTools.Production;
import compilerTools.Token;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;


public class PrincipalFrame extends javax.swing.JFrame {
    
    //"GenerarRuta" es la clase que ayuda a crear la ruta en la que se guardara el archivo
    GenerarRuta gr;
    //"AbrirArchivo" es las clase que permite abrir un archivo y leerlo para cargarlo en el editor
    AbrirArchivo open;
    String resultado = "";
    DefaultTableModel lexModel;
    
    private ArrayList<Token> tokens;
    private ArrayList<ErrorLSSL> errors;
    private ArrayList<Production> productions;
    
    public PrincipalFrame() {
        initComponents();
        
        Path file = Paths.get("prueba.txt");
        open = new AbrirArchivo();
        //open.leer("C:/Users/AnthonySandoval/Desktop/prueba.txt");
        open.leer(file.toAbsolutePath().toString());
        textAnalysis.setText(open.getData());
        init();
        
    }
    
    private void init(){
        Functions.setLineNumberOnJTextComponent(textAnalysis);
        tokens = new ArrayList<>();
        errors = new ArrayList<>();
        productions = new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollAnalysis = new javax.swing.JScrollPane();
        textAnalysis = new javax.swing.JTextPane();
        btnAnalysis = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        textSintax = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableLex = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuOpen = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        scrollAnalysis.setViewportView(textAnalysis);

        btnAnalysis.setText("Analizar");
        btnAnalysis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalysisActionPerformed(evt);
            }
        });

        jScrollPane3.setViewportView(textSintax);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Texto de Analisis");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Analisis Lexico");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Analisis Sintactico");

        tableLex.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Token", "Lex", "Linea, Caracter"
            }
        ));
        jScrollPane1.setViewportView(tableLex);

        jMenu1.setText("Archivo");

        menuOpen.setText("Abrir");
        menuOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOpenActionPerformed(evt);
            }
        });
        jMenu1.add(menuOpen);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 794, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrollAnalysis, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAnalysis, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addComponent(jScrollPane1))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(scrollAnalysis)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAnalysis, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAnalysisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalysisActionPerformed
        tokens.clear();
        errors.clear();
        productions.clear();
        textSintax.setText("");
        try {
            lexAnalysis();
            addToLexTable();
        } catch (IOException ex) {
            Logger.getLogger(PrincipalFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sintaxAnalysis();
        semanticAnalysis();
        showOnConsole();
    }//GEN-LAST:event_btnAnalysisActionPerformed

    private void menuOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOpenActionPerformed
        gr = new GenerarRuta();
        gr.crearRuta(JFileChooser.FILES_ONLY, true);
        gr.separarNomRuta();
        String ruta = gr.getRutaArchivo();
        open = new AbrirArchivo();
        
        if(!ruta.equals("")){
            //Se utiliza el objeto de la clase "AbrirArchivo", se utiliza su metodo "leer" y se le manda la ruta del archivo
            open.leer(ruta);

            //establezco el texto en el objeto obtenido que pertenece al TextPane
            textAnalysis.setText(open.getData());
        }
    }//GEN-LAST:event_menuOpenActionPerformed

    private void addToLexTable(){
        lexModel = (DefaultTableModel) tableLex.getModel();
        lexModel.setRowCount(0);
        
        tokens.forEach(token -> {
            Object[] data = new Object[]{token.getLexicalComp(), token.getLexeme(), "[" + token.getLine() + ", " + token.getColumn() + "]"};
            Functions.addRowDataInTable(tableLex, data);
        });
    }
    
    private void showOnConsole(){
        int sizeErrors = errors.size();
        if (sizeErrors > 0) {
            Functions.sortErrorsByLineAndColumn(errors);
            String strErrors = "\n";
            for(int i = 0; i < errors.size(); i++){
                String strError = String.valueOf(errors.get(i));
                strErrors += strError + "\n";
            }
            textSintax.setText("Compilación Finalizada.\n" + strErrors + "\nLa compilación finalizo con errores...");
            textSintax.setForeground(Color.RED);
        } else {
            textSintax.setText("Compilación Finalizada." + "\n" + textSintax.getText());
            textSintax.setForeground(Color.BLUE);
        }
        textSintax.setCaretPosition(0);
    }
    
    private void lexAnalysis() throws IOException{
        
        tokens.clear();
        errors.clear();
        Lexer lexer;
        try {
            File codigo = new File("code.encrypter");
            FileOutputStream output = new FileOutputStream(codigo);
            byte[] bytesText = textAnalysis.getText().getBytes();
            output.write(bytesText);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(new FileInputStream(codigo), "UTF8"));
            lexer = new Lexer(entrada);
            while (true) {
                Token token = lexer.yylex();
                if (token == null) {
                    break;
                }
                tokens.add(token);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("El archivo no pudo ser encontrado... " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error al escribir en el archivo... " + ex.getMessage());
        }
        
    }
    
    private void sintaxAnalysis(){
        Grammar grammar = new Grammar(tokens, errors);
        SintacticAnalysis sintax = new SintacticAnalysis();
        sintax.analysis(grammar, productions);
    }
    
    private void semanticAnalysis(){
        SemanticAnalysis s = new SemanticAnalysis();
        s.analysis(productions, errors, textSintax);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnalysis;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JMenuItem menuOpen;
    private javax.swing.JScrollPane scrollAnalysis;
    private javax.swing.JTable tableLex;
    private javax.swing.JTextPane textAnalysis;
    private javax.swing.JTextPane textSintax;
    // End of variables declaration//GEN-END:variables

}
