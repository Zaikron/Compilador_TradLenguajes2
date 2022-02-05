
package Forms;

import Utilities.Analysis;
import Utilities.Loader;
import Utilities.Token;
import javax.swing.table.DefaultTableModel;

public class Principal extends javax.swing.JFrame {

    DefaultTableModel modelToken;
    
    public Principal() {
        initComponents();
        modelToken = (DefaultTableModel) tableTokens.getModel();
        addTokensToTable();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableTokens = new javax.swing.JTable();
        btnCheck = new javax.swing.JButton();
        btnOne = new javax.swing.JButton();
        fieldToken = new javax.swing.JTextField();
        textObservation = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tableTokens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Token", "Atributo", "Observaciones"
            }
        ));
        jScrollPane1.setViewportView(tableTokens);

        btnCheck.setText("Comprobar Todos");
        btnCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckActionPerformed(evt);
            }
        });

        btnOne.setText("Comprobar Uno");
        btnOne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOneActionPerformed(evt);
            }
        });

        fieldToken.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        textObservation.setText("*");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnOne)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldToken, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textObservation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCheck)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCheck)
                    .addComponent(btnOne)
                    .addComponent(fieldToken, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textObservation))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckActionPerformed
        modelToken.setRowCount(0);
        Loader loader = new Loader();
        Analysis analysis = new Analysis(loader.tokens);
        
        for(int i = 0; i < analysis.tokens.size(); i++){
            addToTokenTable(analysis.tokens.get(i), analysis.action(analysis.tokens.get(i).name));
        }
        
    }//GEN-LAST:event_btnCheckActionPerformed

    private void btnOneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOneActionPerformed

        Loader loader = new Loader();
        Analysis analysis = new Analysis(loader.tokens);
        textObservation.setText(analysis.action(fieldToken.getText()));
        //fieldToken.setText("");
    }//GEN-LAST:event_btnOneActionPerformed

    private void addTokensToTable(){
        modelToken.setRowCount(0);
        Loader loader = new Loader();
        Analysis analysis = new Analysis(loader.tokens);
        
        for(int i = 0; i < analysis.tokens.size(); i++){
            addToTokenTable(analysis.tokens.get(i), "");
        }
    }
    
    private void addToTokenTable(Token token, String observation){
        modelToken.addRow(new Object[]{token.name, token.attribute, observation});
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCheck;
    private javax.swing.JButton btnOne;
    private javax.swing.JTextField fieldToken;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableTokens;
    private javax.swing.JLabel textObservation;
    // End of variables declaration//GEN-END:variables
}
