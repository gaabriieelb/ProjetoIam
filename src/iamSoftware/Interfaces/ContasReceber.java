/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iamSoftware.Interfaces;

import iamSoftware.Classes.ClientesData;
import iamSoftware.Classes.ConexaoBD;
import iamSoftware.Classes.ContasPagarData;
import iamSoftware.Classes.ContasReceberData;
import iamSoftware.Classes.FornecedorData;
import iamSoftware.Classes.NotaFiscalData;
import iamSoftware.Classes.ProdutosData;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ga_br
 */
public class ContasReceber extends javax.swing.JFrame {
    
    public static DefaultTableModel tblstatic;
    public static String data;
    String status = "Todos";
    /**
     * Creates new form Clientes
     */
    public ContasReceber() {
        initComponents();
        this.getContentPane().setBackground(Color.white);
        this.setLocationRelativeTo(null);
        jScrollPane1.getViewport().setBackground(Color.white);
        tblFornecedores.setBackground(Color.white);
        
        try {
            PreencherTabela();
        } catch (SQLException ex) {
            Logger.getLogger(ContasReceber.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        tblFornecedores = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        buttonCadastrar2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        radioTodos = new javax.swing.JRadioButton();
        radioEmAberto = new javax.swing.JRadioButton();
        radioLiquidado = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        fieldPeriodoInicial = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        fieldPeriodoFinal = new javax.swing.JFormattedTextField();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblFornecedores.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblFornecedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Forma de Pagamento", "Cliente", "Data do Pagamento", "Valor", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblFornecedores.setGridColor(new java.awt.Color(0, 0, 0));
        tblFornecedores.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblFornecedores);
        if (tblFornecedores.getColumnModel().getColumnCount() > 0) {
            tblFornecedores.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        jButton6.setBackground(new java.awt.Color(255, 255, 255));
        jButton6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        jButton6.setText("Excluir");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(79, 129, 199));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Contas a Receber");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
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

        buttonCadastrar2.setBackground(new java.awt.Color(255, 255, 255));
        buttonCadastrar2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        buttonCadastrar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cash.png"))); // NOI18N
        buttonCadastrar2.setText("Liquidar");
        buttonCadastrar2.setFocusPainted(false);
        buttonCadastrar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCadastrar2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Filtrar Status:");

        radioTodos.setBackground(new java.awt.Color(255, 255, 255));
        radioTodos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        radioTodos.setText("Todos");
        radioTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioTodosActionPerformed(evt);
            }
        });

        radioEmAberto.setBackground(new java.awt.Color(255, 255, 255));
        radioEmAberto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        radioEmAberto.setText("Em Aberto");
        radioEmAberto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioEmAbertoActionPerformed(evt);
            }
        });

        radioLiquidado.setBackground(new java.awt.Color(255, 255, 255));
        radioLiquidado.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        radioLiquidado.setText("Liquidado");
        radioLiquidado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioLiquidadoActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Per�odo:");

        try {
            fieldPeriodoInicial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        fieldPeriodoInicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldPeriodoInicialActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("a");

        try {
            fieldPeriodoFinal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        fieldPeriodoFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldPeriodoFinalActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(255, 255, 255));
        jButton7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/find.png"))); // NOI18N
        jButton7.setText("Buscar");
        jButton7.setFocusPainted(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(255, 255, 255));
        jButton8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clipboard.png"))); // NOI18N
        jButton8.setText("Cadastrar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldPeriodoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fieldPeriodoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 709, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(51, 51, 51)
                            .addComponent(buttonCadastrar2, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(radioTodos)
                        .addGap(18, 18, 18)
                        .addComponent(radioEmAberto)
                        .addGap(18, 18, 18)
                        .addComponent(radioLiquidado)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(radioTodos)
                    .addComponent(radioEmAberto)
                    .addComponent(radioLiquidado))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldPeriodoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jButton7)
                    .addComponent(fieldPeriodoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCadastrar2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        ContasReceberData contasReceberData = new ContasReceberData();
        DefaultTableModel tabela = (DefaultTableModel) tblFornecedores.getModel();
        int row = tblFornecedores.getSelectedRow();
        int id = (int) tabela.getValueAt(row, 0);
         
        try {
            contasReceberData.Remover(id);
            PreencherTabela();
            Mensagem msg = new Mensagem("Registro excluido com sucesso!");
            msg.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Produtos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void buttonCadastrar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCadastrar2ActionPerformed
        ContasReceberData contasreceber = new ContasReceberData();
        
        DefaultTableModel tabela = (DefaultTableModel) tblFornecedores.getModel();
        int row = tblFornecedores.getSelectedRow();
        int id = (int) tabela.getValueAt(row, 0);
        
        try {
            contasreceber.Liquidar(id);
            PreencherTabela();
            Mensagem msg = new Mensagem("Conta Liquidada com sucesso!");
            msg.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(ContasReceber.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonCadastrar2ActionPerformed

    private void radioTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioTodosActionPerformed
        status = "Todos";
        radioEmAberto.setSelected(false);
        radioLiquidado.setSelected(false);
        try {
            PreencherTabela();
        } catch (SQLException ex) {
            Logger.getLogger(ContasReceber.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_radioTodosActionPerformed

    private void radioEmAbertoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioEmAbertoActionPerformed
        status = "Em Aberto";
        radioTodos.setSelected(false);
        radioLiquidado.setSelected(false);
        try {
            PreencherTabela("Em Aberto");
        } catch (SQLException ex) {
            Logger.getLogger(ContasReceber.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_radioEmAbertoActionPerformed

    private void radioLiquidadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioLiquidadoActionPerformed
        status = "Liquidado";
        radioEmAberto.setSelected(false);
        radioTodos.setSelected(false);
       
        try {
            PreencherTabela("Liquidado");
        } catch (SQLException ex) {
            Logger.getLogger(ContasReceber.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_radioLiquidadoActionPerformed

    private void fieldPeriodoInicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldPeriodoInicialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldPeriodoInicialActionPerformed

    private void fieldPeriodoFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldPeriodoFinalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldPeriodoFinalActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
       
        String periodoInicial = fieldPeriodoInicial.getText();
        String periodoFinal = fieldPeriodoFinal.getText();
        String sql = "";
        String datainicial=periodoInicial, datafinal=periodoFinal;

        if(status.equals("Todos")){
            sql= "SELECT * FROM `contasreceber` WHERE STR_TO_DATE(datapagamento, '%d/%m/%Y') BETWEEN STR_TO_DATE('"+datainicial+"', '%d/%m/%Y') AND STR_TO_DATE('"+datafinal+"', '%d/%m/%Y') ORDER BY STR_TO_DATE(datapagamento, '%d/%m/%Y') ASC";
        }
        if(status.equals("Em Aberto")){
            sql = "SELECT * FROM `contasreceber` WHERE STR_TO_DATE(datapagamento, '%d/%m/%Y') BETWEEN STR_TO_DATE('"+datainicial+"', '%d/%m/%Y') AND STR_TO_DATE('"+datafinal+"', '%d/%m/%Y') AND status='Em Aberto' ORDER BY STR_TO_DATE(datapagamento, '%d/%m/%Y') ASC";
        }
        if(status.equals("Liquidado")){
            sql = "SELECT * FROM `contasreceber` WHERE STR_TO_DATE(datapagamento, '%d/%m/%Y') BETWEEN STR_TO_DATE('"+datainicial+"', '%d/%m/%Y') AND STR_TO_DATE('"+datafinal+"', '%d/%m/%Y') AND status='Liquidado' ORDER BY STR_TO_DATE(datapagamento, '%d/%m/%Y') ASC";
        }
        
        try {
            BuscaFiltrada(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ContasReceber.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        CadastroContasReceber cadastroContasReceber = new CadastroContasReceber();
        cadastroContasReceber.setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

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
            java.util.logging.Logger.getLogger(ContasReceber.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ContasReceber.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ContasReceber.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ContasReceber.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new ContasReceber().setVisible(true);
            }
        });
    }
    
    public void PreencherTabela() throws SQLException{
        DefaultTableModel tabela = (DefaultTableModel) tblFornecedores.getModel();
        
        tabela.setRowCount(0);
        
        String sql = "SELECT * FROM `contasreceber`";
        
        Connection conn = ConexaoBD.Conectar();           
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        List<ProdutosData> produtoslist = new ArrayList<ProdutosData>();
        
        DecimalFormat df = new DecimalFormat("#,###.00");
        
        while(rs.next()){
            Object[] dados = new Object[7];
            dados[0] = rs.getInt("id");
            dados[1] = rs.getString("formapagamento");
            dados[2] = rs.getString("cliente");
            dados[3] = rs.getString("datapagamento");
            Double valor = Double.parseDouble(rs.getString("valor"));
            dados[4] = df.format(valor);
            dados[5] = rs.getString("status");
            
            
            tabela.addRow(dados);
        }       
    }
    
    public void PreencherTabela(String Status) throws SQLException{
        DefaultTableModel tabela = (DefaultTableModel) tblFornecedores.getModel();
        
        tabela.setRowCount(0);
        
        String sql = "SELECT * FROM `contasreceber` WHERE status= '"+Status+"'";
        
        Connection conn = ConexaoBD.Conectar();           
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        List<ProdutosData> produtoslist = new ArrayList<ProdutosData>();
        
        DecimalFormat df = new DecimalFormat("#,###.00");
        while(rs.next()){
            Object[] dados = new Object[7];
            dados[0] = rs.getInt("id");
            dados[1] = rs.getString("formapagamento");
            dados[2] = rs.getString("cliente");
            dados[3] = rs.getString("datapagamento");
            Double valor = Double.parseDouble(rs.getString("valor"));
            dados[4] = df.format(valor);
            dados[5] = rs.getString("status");       
            
            tabela.addRow(dados);
        }       
    }
    
    public void BuscaFiltrada(String sql) throws SQLException{
        DefaultTableModel tabela = (DefaultTableModel) tblFornecedores.getModel();
        
        tabela.setRowCount(0);      
               
        Connection conn = ConexaoBD.Conectar();           
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        List<ProdutosData> produtoslist = new ArrayList<ProdutosData>();
        DecimalFormat df = new DecimalFormat("#,###.00"); 
        while(rs.next()){
            Object[] dados = new Object[7];
            dados[0] = rs.getInt("id");
            dados[1] = rs.getString("formapagamento");
            dados[2] = rs.getString("cliente");
            dados[3] = rs.getString("datapagamento");
            Double valor = Double.parseDouble(rs.getString("valor"));
            dados[4] = df.format(valor);
            dados[5] = rs.getString("status");         
            
            tabela.addRow(dados);
        }       
    }
    
    public static void Atualizar() throws SQLException{
        tblstatic = (DefaultTableModel) tblFornecedores.getModel();
        
        tblstatic.setRowCount(0);
        
        
        String sql = "SELECT * FROM `contasreceber`";
        
        Connection conn = ConexaoBD.Conectar();           
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        List<ProdutosData> produtoslist = new ArrayList<ProdutosData>();
        
        DecimalFormat df = new DecimalFormat("#,###.00");
        
        while(rs.next()){
            Object[] dados = new Object[7];
            dados[0] = rs.getInt("id");
            dados[1] = rs.getString("formapagamento");
            dados[2] = rs.getString("cliente");
            dados[3] = rs.getString("datapagamento");
            Double valor = Double.parseDouble(rs.getString("valor"));
            dados[4] = df.format(valor);
            dados[5] = rs.getString("status");
            
            
            tblstatic.addRow(dados);
        }       
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
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCadastrar2;
    private javax.swing.JFormattedTextField fieldPeriodoFinal;
    private javax.swing.JFormattedTextField fieldPeriodoInicial;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton radioEmAberto;
    private javax.swing.JRadioButton radioLiquidado;
    private javax.swing.JRadioButton radioTodos;
    public static javax.swing.JTable tblFornecedores;
    // End of variables declaration//GEN-END:variables
}
