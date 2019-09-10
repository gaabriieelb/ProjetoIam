/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iamSoftware.Interfaces;

import iamSoftware.Classes.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author ga_br
 */
public class PesquisarProduto extends javax.swing.JFrame {
    
    
    
    /**
     * Creates new form PDVCaixa
     */
    public PesquisarProduto() {
        initComponents();
        this.getContentPane().setBackground(Color.white);
        this.setLocationRelativeTo(null);
        radioContem.setSelected(true);
        radioID.setSelected(true);
    }
    
    public PesquisarProduto(String tela) {
        initComponents();
        this.getContentPane().setBackground(Color.white);
        this.setLocationRelativeTo(null);
        radioContem.setSelected(true);
        radioID.setSelected(true);
        
        
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblProdutos = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        fieldNome = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        radioIgual = new javax.swing.JRadioButton();
        radioContem = new javax.swing.JRadioButton();
        radioInicio = new javax.swing.JRadioButton();
        radioFim = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        radioNome = new javax.swing.JRadioButton();
        radioCodigo = new javax.swing.JRadioButton();
        radioID = new javax.swing.JRadioButton();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(0, 51, 255));

        tblProdutos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Produto", "C�d.", "Valor Unit.", "Medida"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblProdutos);
        if (tblProdutos.getColumnModel().getColumnCount() > 0) {
            tblProdutos.getColumnModel().getColumn(0).setMaxWidth(50);
            tblProdutos.getColumnModel().getColumn(3).setPreferredWidth(13);
        }

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        jButton5.setText("Excluir Produto");
        jButton5.setFocusPainted(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(255, 255, 255));
        jButton6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/shuffle.png"))); // NOI18N
        jButton6.setText("Alterar Produto");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(79, 129, 199));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Pesquisa de Produto");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(245, 135, 66));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel1.setText("Palavra Chave:");

        fieldNome.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jButton8.setBackground(new java.awt.Color(255, 255, 255));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/find.png"))); // NOI18N
        jButton8.setFocusPainted(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        radioIgual.setBackground(new java.awt.Color(255, 255, 255));
        radioIgual.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        radioIgual.setText("Igual");
        radioIgual.setFocusPainted(false);
        radioIgual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioIgualActionPerformed(evt);
            }
        });

        radioContem.setBackground(new java.awt.Color(255, 255, 255));
        radioContem.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        radioContem.setText("Cont�m");
        radioContem.setFocusPainted(false);
        radioContem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioContemActionPerformed(evt);
            }
        });

        radioInicio.setBackground(new java.awt.Color(255, 255, 255));
        radioInicio.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        radioInicio.setText("In�cio");
        radioInicio.setFocusPainted(false);
        radioInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioInicioActionPerformed(evt);
            }
        });

        radioFim.setBackground(new java.awt.Color(255, 255, 255));
        radioFim.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        radioFim.setText("Fim");
        radioFim.setFocusPainted(false);
        radioFim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioFimActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel2.setText("Forma de Busca:");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel3.setText("Campo de Busca:");

        radioNome.setBackground(new java.awt.Color(255, 255, 255));
        radioNome.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        radioNome.setText("Nome");
        radioNome.setFocusPainted(false);
        radioNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioNomeActionPerformed(evt);
            }
        });

        radioCodigo.setBackground(new java.awt.Color(255, 255, 255));
        radioCodigo.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        radioCodigo.setText("C�digo");
        radioCodigo.setFocusPainted(false);
        radioCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioCodigoActionPerformed(evt);
            }
        });

        radioID.setBackground(new java.awt.Color(255, 255, 255));
        radioID.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        radioID.setText("ID");
        radioID.setFocusPainted(false);
        radioID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioIDActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(255, 255, 255));
        jButton7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png"))); // NOI18N
        jButton7.setText("Fechar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(radioContem)
                                .addGap(18, 18, 18)
                                .addComponent(radioIgual)
                                .addGap(18, 18, 18)
                                .addComponent(radioInicio)
                                .addGap(18, 18, 18)
                                .addComponent(radioFim))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(radioID)
                                .addGap(62, 62, 62)
                                .addComponent(radioCodigo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(radioNome))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioCodigo)
                    .addComponent(radioNome)
                    .addComponent(radioID)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioIgual)
                    .addComponent(radioContem)
                    .addComponent(radioInicio)
                    .addComponent(radioFim)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(fieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void radioIgualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioIgualActionPerformed
        radioContem.setSelected(false);
        radioFim.setSelected(false);
        radioInicio.setSelected(false);
    }//GEN-LAST:event_radioIgualActionPerformed

    private void radioContemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioContemActionPerformed
        radioIgual.setSelected(false);
        radioFim.setSelected(false);
        radioInicio.setSelected(false);
    }//GEN-LAST:event_radioContemActionPerformed

    private void radioInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioInicioActionPerformed
        radioContem.setSelected(false);
        radioFim.setSelected(false);
        radioIgual.setSelected(false);
    }//GEN-LAST:event_radioInicioActionPerformed

    private void radioFimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioFimActionPerformed
        radioContem.setSelected(false);
        radioIgual.setSelected(false);
        radioInicio.setSelected(false);
    }//GEN-LAST:event_radioFimActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        String sql ="";
        String nome = fieldNome.getText();
        String campo = "";
        
        if(radioID.isSelected()){
            campo = "id";
        }
        if(radioCodigo.isSelected()){
            campo = "codigo";
        }
        if(radioNome.isSelected()){
            campo = "nome";
        }
               
        
        if(radioIgual.isSelected()){
            if(campo.equals("nome")){
                sql = "SELECT * FROM produtos WHERE "+campo+" LIKE '"+nome+"'";
            }else{
                sql = "SELECT * FROM produtos WHERE "+campo+" LIKE "+nome+"";
            }           
        }
        if(radioContem.isSelected()){
            if(campo.equals("nome")){
                sql = "SELECT * FROM produtos WHERE "+campo+" LIKE '%"+nome+"%'";
            }else{
                sql = "SELECT * FROM produtos WHERE "+campo+" LIKE CONCAT('%',"+nome+",'%')";
            }           
        }
        if(radioInicio.isSelected()){
            if(campo.equals("nome")){
                sql = "SELECT * FROM produtos WHERE "+campo+" LIKE '"+nome+"%'";
            }else{
                sql = "SELECT * FROM produtos WHERE "+campo+" LIKE CONCAT("+nome+",'%')";
            }            
        }
        if(radioFim.isSelected()){
            if(campo.equals("nome")){
                sql = "SELECT * FROM produtos WHERE "+campo+" LIKE '%"+nome+"'";
            }else{
                sql = "SELECT * FROM produtos WHERE "+campo+" LIKE CONCAT('%',"+nome+")";
            }        
        }
        
        try {
            PreencherTabela(sql);
        } catch (SQLException ex) {
            Logger.getLogger(PesquisarProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        ProdutosData produtos = new ProdutosData();
        DefaultTableModel tabela = (DefaultTableModel) tblProdutos.getModel();
        int row = tblProdutos.getSelectedRow();
        int id = (int) tabela.getValueAt(row, 0);

        try {
            produtos.Remover(id);
            PreencherTabela();
            String msg = "Registro Excluido com Sucesso!";
            Mensagem mensagem = new Mensagem(msg);
            mensagem.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Produtos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        DefaultTableModel tabela = (DefaultTableModel) tblProdutos.getModel();
        int row = tblProdutos.getSelectedRow();
        int id = (int) tabela.getValueAt(row, 0);
        String nome = (String) tabela.getValueAt(row, 1);
        String codigo = (String) tabela.getValueAt(row, 2);       
        double valorVenda = (double) tabela.getValueAt(row, 3);        
        String unidade = (String) tabela.getValueAt(row, 4);

        ProdutosAlterar produtosalterar = new ProdutosAlterar(nome, codigo, valorVenda, unidade, id);
        produtosalterar.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void radioNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioNomeActionPerformed
        radioID.setSelected(false);
        radioCodigo.setSelected(false);
    }//GEN-LAST:event_radioNomeActionPerformed

    private void radioCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioCodigoActionPerformed
        radioID.setSelected(false);
        radioNome.setSelected(false);
    }//GEN-LAST:event_radioCodigoActionPerformed

    private void radioIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioIDActionPerformed
        radioCodigo.setSelected(false);
        radioNome.setSelected(false);
    }//GEN-LAST:event_radioIDActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {
            Produtos.atualizar();
        } catch (SQLException ex) {
            Logger.getLogger(PesquisarProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
    }//GEN-LAST:event_jButton7ActionPerformed
        
    
    
    
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
            java.util.logging.Logger.getLogger(PesquisarProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PesquisarProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PesquisarProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PesquisarProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PesquisarProduto().setVisible(true);
            }
        });
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField fieldNome;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton radioCodigo;
    private javax.swing.JRadioButton radioContem;
    private javax.swing.JRadioButton radioFim;
    private javax.swing.JRadioButton radioID;
    private javax.swing.JRadioButton radioIgual;
    private javax.swing.JRadioButton radioInicio;
    private javax.swing.JRadioButton radioNome;
    private javax.swing.JTable tblProdutos;
    // End of variables declaration//GEN-END:variables
    
   
    
    
    public void PreencherTabela(String sql) throws SQLException {
        DefaultTableModel tabela = (DefaultTableModel) tblProdutos.getModel();

        tabela.setRowCount(0);
        
        Connection conn = ConexaoBD.Conectar();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        List<ProdutosData> produtoslist = new ArrayList<ProdutosData>();

        while (rs.next()) {
            Object[] dados = new Object[5];
            dados[0] = rs.getInt("id");
            dados[1] = rs.getString("codigo");
            dados[2] = rs.getString("nome");            
            dados[3] = rs.getDouble("valorVenda");
            dados[4] = rs.getString("medida");
            
            tabela.addRow(dados);
        }

    }
    
    public void PreencherTabela() throws SQLException {
        DefaultTableModel tabela = (DefaultTableModel) tblProdutos.getModel();

        tabela.setRowCount(0);

        String sql = "SELECT * FROM `produtos`";

        Connection conn = ConexaoBD.Conectar();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        List<ProdutosData> produtoslist = new ArrayList<ProdutosData>();

        while (rs.next()) {
            Object[] dados = new Object[5];
            dados[0] = rs.getInt("id");
            dados[1] = rs.getString("nome");
            dados[2] = rs.getString("codigo");            
            dados[3] = rs.getDouble("valorVenda");           
            dados[4] = rs.getString("medida");

            tabela.addRow(dados);
        }

    }

}


