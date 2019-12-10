/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iamSoftware.Interfaces;

import iamSoftware.Classes.ConexaoBD;
import static iamSoftware.Interfaces.CadastroCartao.tblProdutos;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ga_br
 */
public class Cartao extends javax.swing.JFrame {
    
    String forma;
    String cartao;
    String prazo;
    Double taxa;
    int v = 0;
    int tela; 
    /**
     * Creates new form Mensagem
     */
    public Cartao(int tela) {
        initComponents();
        this.getContentPane().setBackground(Color.white);
        this.setLocationRelativeTo(null);  
        
        this.tela = tela;
        
        try {
            PreencherTabela();
        } catch (SQLException ex) {
            Logger.getLogger(Cartao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String dia = "05";
        
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy"); 		 
        Date dataDoSistema = new Date();		
        String dataEmTexto = formatador.format(dataDoSistema);
        char[] data = dataEmTexto.toCharArray();
        
        int m1 = Integer.parseInt(String.valueOf(data[3]));
        int m2 = Integer.parseInt(String.valueOf(data[4]));
        int m3 = Integer.parseInt(String.valueOf(m1+""+m2));
        
        int a1 = Integer.parseInt(String.valueOf(data[8]));
        int a2 = Integer.parseInt(String.valueOf(data[9]));
        int a3 = Integer.parseInt(String.valueOf(a1+""+a2));
        
        if(m3 == 12){
            data[3] = 0;
            data[4] = 1;
            if(a3==19){
                data[8] = 2;
                data[9] = 0;
            }else{
                int a4 = a2+1;                
                data[9] = (char) a4;
            }            
        }
        else{
            m3+=1;
            char[] dataMes = String.valueOf(m3).toCharArray();
            data[3] = dataMes[0];
            data[4] = dataMes[1];
        }
        
        //lblPagamento.setText(dia+"/"+m3+"/20"+data[8]+""+data[9]);
    }
    
    public Cartao() {
        initComponents();
        this.getContentPane().setBackground(Color.white);
        this.setLocationRelativeTo(null);  
        
       
        
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
        textMensagem = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        comboFormaPagamento = new javax.swing.JComboBox<>();
        textMensagem1 = new javax.swing.JLabel();
        comboCartao = new javax.swing.JComboBox<>();
        textMensagem2 = new javax.swing.JLabel();
        textMensagem4 = new javax.swing.JLabel();
        lblTaxa = new javax.swing.JLabel();
        textMensagem5 = new javax.swing.JLabel();
        fieldMes = new javax.swing.JTextField();
        textMensagem6 = new javax.swing.JLabel();
        fieldAno = new javax.swing.JTextField();
        textMensagem3 = new javax.swing.JLabel();
        lblPagamento = new javax.swing.JLabel();
        fieldDia = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        textMensagem.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        textMensagem.setText("Forma:");

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check.png"))); // NOI18N
        jButton5.setText("Confirmar ");
        jButton5.setFocusPainted(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(255, 255, 255));
        jButton6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png"))); // NOI18N
        jButton6.setText("Cancelar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        comboFormaPagamento.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        comboFormaPagamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cr�dito", "D�bito" }));
        comboFormaPagamento.setOpaque(false);
        comboFormaPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboFormaPagamentoActionPerformed(evt);
            }
        });

        textMensagem1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        textMensagem1.setText("Cart�o:");

        comboCartao.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        comboCartao.setOpaque(false);
        comboCartao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCartaoActionPerformed(evt);
            }
        });

        textMensagem2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        textMensagem2.setText("Pagamento:");

        textMensagem4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        textMensagem4.setText("Taxa:");

        lblTaxa.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        textMensagem5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        textMensagem5.setText("/");

        fieldMes.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        textMensagem6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        textMensagem6.setText("/");

        fieldAno.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        textMensagem3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        textMensagem3.setText("Prazo:");

        lblPagamento.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblPagamento.setText("NN");

        fieldDia.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(textMensagem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(comboFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(textMensagem3)
                                    .addComponent(textMensagem1))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboCartao, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblPagamento)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(textMensagem4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTaxa, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(textMensagem2)
                                .addGap(9, 9, 9)
                                .addComponent(fieldDia, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textMensagem5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fieldMes, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textMensagem6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fieldAno, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textMensagem1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboCartao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textMensagem3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textMensagem2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textMensagem5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(fieldMes, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(textMensagem6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(fieldAno, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(fieldDia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textMensagem4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTaxa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String dia = fieldDia.getText();
        String mes = fieldMes.getText();
        String ano = fieldAno.getText();
        
        
        forma = String.valueOf(comboFormaPagamento.getSelectedItem());
        cartao = String.valueOf(comboCartao.getSelectedItem());
        prazo = dia+"/"+mes+"/"+ano;
        //String taxa2 = lblTaxa.getText();        
        //System.out.println(taxa2);
        taxa = Double.parseDouble(lblTaxa.getText());
        //taxa.replace(",", ".");
        
        if(tela == 1){
            PDVCaixa.pagamentoCartao = forma+" "+cartao;
            PDVCaixa.prazo=prazo;
            PDVCaixa.taxa = taxa;
        }
        if(tela == 2){
            PDVComanda.pagamentoCartao = forma+" "+cartao;
            PDVComanda.prazo=prazo;
            PDVComanda.taxa = taxa;
        }
        if(tela == 3){
            CadastroContasReceber.pagamentoCartao = forma+" "+cartao;
            CadastroContasReceber.prazo=prazo;
            CadastroContasReceber.taxa = taxa;
            
        }
        
        this.dispose();
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if(tela == 1){
            PDVCaixa.comboFormaPagamento.setSelectedIndex(0);
            PDVCaixa.comboFormaPagamento2.setSelectedIndex(0);
        }
        if(tela == 2){
            PDVComanda.comboFormaPagamento.setSelectedIndex(0);
            PDVComanda.comboFormaPagamento2.setSelectedIndex(0);
        }
        this.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void comboFormaPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboFormaPagamentoActionPerformed
      
    }//GEN-LAST:event_comboFormaPagamentoActionPerformed

    private void comboCartaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCartaoActionPerformed
       
        String bandeira = (String) comboCartao.getSelectedItem();
       
        try {
            PreencherDados(bandeira);
        } catch (SQLException ex) {
            Logger.getLogger(Cartao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_comboCartaoActionPerformed

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
            java.util.logging.Logger.getLogger(Cartao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cartao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cartao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cartao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cartao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JComboBox<String> comboCartao;
    public static javax.swing.JComboBox<String> comboFormaPagamento;
    private javax.swing.JTextField fieldAno;
    private javax.swing.JTextField fieldDia;
    private javax.swing.JTextField fieldMes;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblPagamento;
    private javax.swing.JLabel lblTaxa;
    private javax.swing.JLabel textMensagem;
    private javax.swing.JLabel textMensagem1;
    private javax.swing.JLabel textMensagem2;
    private javax.swing.JLabel textMensagem3;
    private javax.swing.JLabel textMensagem4;
    private javax.swing.JLabel textMensagem5;
    private javax.swing.JLabel textMensagem6;
    // End of variables declaration//GEN-END:variables
public void PreencherTabela() throws SQLException {
        

        String sql = "SELECT * FROM `cartao`";

        Connection conn = ConexaoBD.Conectar();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        comboCartao.addItem("Selecione a Bandeira");
        
        while (rs.next()) {
                       
            String bandeira = rs.getString("bandeira");
                    
            comboCartao.addItem(bandeira);
            
           
        }

    }



public void PreencherDados(String bandeira) throws SQLException {
        

        String sql = "SELECT * FROM `cartao` WHERE bandeira='"+bandeira+"'";

        Connection conn = ConexaoBD.Conectar();

        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        String prazo="", taxa="";
        
        while (rs.next()) {
                       
            prazo = rs.getString("prazo");
            taxa = rs.getString("taxacomissao");              
           
        }
        lblPagamento.setText(prazo);
        lblTaxa.setText(taxa);
    }
}
