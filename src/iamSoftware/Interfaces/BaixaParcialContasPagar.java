/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iamSoftware.Interfaces;

import iamSoftware.Classes.CaixaData;
import iamSoftware.Classes.ConexaoBD;
import iamSoftware.Classes.ContasPagarData;
import iamSoftware.Classes.ContasReceberData;
import iamSoftware.Classes.ProdutosData;
import iamSoftware.Classes.RelatorioData;
import static iamSoftware.Interfaces.ContasPagar.conveter;
import static iamSoftware.Interfaces.ContasReceber.tblFornecedores;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ga_br
 */
public class BaixaParcialContasPagar extends javax.swing.JFrame {
    
    int id;
    /**
     * Creates new form Mensagem
     */
    public BaixaParcialContasPagar() {
        initComponents();
        this.getContentPane().setBackground(Color.white);
        this.setLocationRelativeTo(null);
    }
    
    public BaixaParcialContasPagar(int id) {
        initComponents();
        this.getContentPane().setBackground(Color.white);
        this.setLocationRelativeTo(null);
        this.id = id;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        fieldValor = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        fieldData = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(79, 129, 199));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Baixa Parcial Contas a Pagar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        fieldValor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("Valor:");

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check.png"))); // NOI18N
        jButton5.setText("Confirmar");
        jButton5.setFocusPainted(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        try {
            fieldData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        fieldData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldDataActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setText("Pr�ximo Pagamento:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldValor, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldData, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(fieldValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       double valor = Double.parseDouble(fieldValor.getText().replace(",","."));
       String data = fieldData.getText();
        try {
            BaixaParcial(id, valor, data);
            Mensagem msg = new Mensagem("Baixa Parcial realizada com sucesso!");
            msg.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(BaixaParcialContasPagar.class.getName()).log(Level.SEVERE, null, ex);
        }
       this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void fieldDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldDataActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BaixaParcialContasPagar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BaixaParcialContasPagar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BaixaParcialContasPagar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BaixaParcialContasPagar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BaixaParcialContasPagar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField fieldData;
    public static javax.swing.JTextField fieldValor;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    
    
    public void BaixaParcial(int id, double novoValor, String dataNovoPagamento) throws SQLException{
        
        ContasPagarData contaspagar = new ContasPagarData();
        
        
        
        String numeroDocumento = "";
        String fornecedor = "";
        double valor = 0.0;
        String numeroParcela = "";
        String vencimento = "";
        String status = "";
        
        DecimalFormat df = new DecimalFormat("#,###.00");
        
        String sql = "SELECT * FROM `contaspagar` WHERE id="+id;
        
        Connection conn = ConexaoBD.Conectar();           
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        List<ProdutosData> produtoslist = new ArrayList<ProdutosData>();
             
        while(rs.next()){
            
            numeroDocumento = rs.getString("numerodocumento");
            fornecedor = rs.getString("fornecedor");
            valor = Double.parseDouble(rs.getString("valor"));
            numeroParcela = rs.getString("numeroparcela");
            vencimento = rs.getString("vencimento");
            
            char[] dataArray = vencimento.toCharArray();
            vencimento = conveter(dataArray);
            
            status = rs.getString("status");
        }
        
        //atualiza cadastro atual
        double saldoDevedor= valor-novoValor;
        
        String sql2 = "UPDATE `contaspagar` SET `valor` = '"+novoValor+"' WHERE `contaspagar`.`id` ="+id;
        stmt = conn.prepareStatement(sql2);               
        stmt.executeUpdate();
        //liquida atual com valor
        contaspagar.Liquidar(id);
        
        //cria uma nova conta com valor restante
        stmt = conn.prepareStatement("INSERT INTO contaspagar (numerodocumento, fornecedor, valor, numeroparcela, vencimento, status)VALUES(?,?,?,?,?,?)");
        
        String dataBanco = converterToDataBase(dataNovoPagamento);
        
        stmt.setString(1,numeroDocumento);
        stmt.setString(2,fornecedor);
        stmt.setString(3,String.valueOf(saldoDevedor));
        stmt.setString(4,numeroParcela);
        stmt.setString(5,dataBanco);
        stmt.setString(6,"Em Aberto");
        
        stmt.executeUpdate();
        
        ContasPagar.Atualizar();
    }
    
    
    public static String conveter(char[] dataArray){
    
        String dataString = dataArray[8]+""+dataArray[9]+"/"+dataArray[5]+""+dataArray[6]+"/20"+dataArray[2]+dataArray[3];  
        
    return dataString;    
    }
    
    public String converterToDataBase(String data){
        char [] dataArray = data.toCharArray();
        data = dataArray[6]+""+dataArray[7]+""+dataArray[8]+""+dataArray[9]+"-"+dataArray[3]+""+dataArray[4]+"-"+dataArray[0]+""+dataArray[1];
        return data;        
    }

}
