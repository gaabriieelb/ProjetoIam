/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iamSoftware.Interfaces;

import iamSoftware.Classes.APagar;
import iamSoftware.Classes.AReceber;
import iamSoftware.Classes.Agenda;
import iamSoftware.Classes.ConexaoBD;
import iamSoftware.Classes.Horario;
import iamSoftware.Classes.ProdutosData;
import iamSoftware.Classes.teste;
import static iamSoftware.Interfaces.ContasPagar.conveter;
import java.awt.Color;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextArea;

/**
 *
 * @author ga_br
 */
public class Inicial extends javax.swing.JFrame {
    
    int agenda;
    double apagar;
    double areceber;
    double vendadiaria;
    
     public Inicial() {
        initComponents();
       
     }
    /**
     * Creates new form Inicial
     */
    public Inicial(String nivel) {
        initComponents();
        
        if(nivel.equals("Operador de Caixa")){
            btnAgenda.setEnabled(false);
            btnInventario.setEnabled(false);
            btnPesquisaVendas.setEnabled(false);
            btnConfiguracao.setEnabled(false);
            btnContasPagar.setEnabled(false);
            btnContasReceber.setEnabled(false);
            btnRelatorio.setEnabled(false);
            btnGrafico.setEnabled(false);
        }
        if(nivel.equals("Supervisor")){
            btnRelatorio.setEnabled(false);
            btnGrafico.setEnabled(false);
        }
         
        preencheEmpresa();
         
         
        //System.out.println(jButton1.getSize());
           
        this.getContentPane().setBackground(new Color(60,65,103));
        //this.getContentPane().setBackground(new Color(255,255,255));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        // data/hora atual
        LocalDateTime agora = LocalDateTime.now();


        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        String dataFormatada = formatterData.format(agora);


        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        String horaFormatada = formatterHora.format(agora);
        
        Horario.start(lblData);
        
        
        ///preenchimento compromisso
        try {        
        DefaultListModel modelPagar;
        modelPagar = new DefaultListModel();
        
        String sql = "SELECT * FROM `agenda` WHERE data='"+dataFormatada+"'";
        
        Connection conn = ConexaoBD.Conectar();
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();       
             
        while(rs.next()){          
            String horario = rs.getString("horario");
            String data = rs.getString("data");
            String local = rs.getString("local"); 
            String assunto = rs.getString("assunto");
            modelPagar.addElement(new Agenda(horario, data, local, assunto));
            agenda+=1;
        }
        lblCompromisso.setText(lblCompromisso.getText()+": "+agenda);
        listCompromisso.setModel(modelPagar);
        listCompromisso.setCellRenderer(new RendererCompromisso());
        stmt.close();        
        conn.close();
        
        } catch (SQLException ex) {
            Logger.getLogger(Inicial.class.getName()).log(Level.SEVERE, null, ex);
        }
        ////////////////////////////////////
        
        DecimalFormat df = new DecimalFormat("#,###.00");
        ///prenchimento contas a pagar
        try {        
        DefaultListModel modelPagar;
        modelPagar = new DefaultListModel();
        
        String sql = "SELECT * FROM `contaspagar` WHERE vencimento=STR_TO_DATE('"+dataFormatada+"', '%d/%m/%Y') AND status='Em Aberto' ORDER BY vencimento ASC";
        
        Connection conn = ConexaoBD.Conectar();
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();       
             
        while(rs.next()){          
            String fornecedor = rs.getString("fornecedor");
            Double v = Double.parseDouble(rs.getString("valor").replace(",", "."));
            String valor = df.format(v);
            modelPagar.addElement(new APagar(fornecedor,valor));
            apagar+=v;
        }
        String somapagar = df.format(apagar);
        lblapagar.setText(lblapagar.getText()+": "+somapagar);
                
        listPagar.setModel(modelPagar);
        listPagar.setCellRenderer(new RendererAPagar());
        stmt.close();
        conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Inicial.class.getName()).log(Level.SEVERE, null, ex);
        }
        ////////////////////////////////////
        
        
        ///preenchimento contas a receber
        try {        
        DefaultListModel modelPagar;
        modelPagar = new DefaultListModel();
        
        String sql = "SELECT * FROM `contasreceber` WHERE datapagamento='"+dataFormatada+"' AND status= 'Em Aberto'";
        
        Connection conn = ConexaoBD.Conectar();
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();       
        
        double somaDinheiro = 0.0;
        double somaDebito = 0.0;
        double somaCredito = 0.0;
        double somaCheque = 0.0;
        while(rs.next()){          
            String formapagamento = rs.getString("formapagamento");
            double v  = Double.parseDouble(rs.getString("valor"));
            String valor = df.format(v);
            
            if(formapagamento.equalsIgnoreCase("À Prazo") || formapagamento.equalsIgnoreCase("Parcelado")){
                String cliente = rs.getString("cliente");
                modelPagar.addElement(new AReceber(formapagamento+"("+cliente+")",valor));
            }else{
                if(formapagamento.contains("Dinheiro")){
                    somaDinheiro+=v;
                }
                if(formapagamento.contains("Débito")){
                    somaDebito+=v;
                }
                if(formapagamento.contains("Crédito")){
                    somaCredito+=v;
                }
                if(formapagamento.contains("Cheque")){
                    somaCheque+=v;
                }
                
            }
            
            
            areceber+=v;
        }
        
        modelPagar.addElement(new AReceber("Crédito: ",df.format(somaCredito)));
        modelPagar.addElement(new AReceber("Débito: ",df.format(somaDebito)));
        modelPagar.addElement(new AReceber("Dinheiro: ",df.format(somaDinheiro)));
        modelPagar.addElement(new AReceber("Cheque: ",df.format(somaCheque)));
        
        String somareceber = df.format(areceber);
        lblareceber.setText(lblareceber.getText()+": "+somareceber);
        
        listReceber.setModel(modelPagar);
        listReceber.setCellRenderer(new RendererAReceber());
        stmt.close();
        conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Inicial.class.getName()).log(Level.SEVERE, null, ex);
        }
        ////////////////////////////////////
        
        
        ///preenchimento vendas diárias
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy"); 		 
	Date dataDoSistema = new Date();		
	String dataEmTexto = formatador.format(dataDoSistema);  
        
        try {        
        DefaultListModel modelPagar;
        modelPagar = new DefaultListModel();
        
        String sql = "SELECT * FROM `contasreceber` WHERE datapagamento= '"+dataEmTexto+"'";
        
        Connection conn = ConexaoBD.Conectar();
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();       
        
        double dinheiro = 0.0;
        double prazo = 0.0;
        double cheque = 0.0;
        double parcelado = 0.0;
        double credito = 0.0;
        double debito = 0.0; 
        
        while(rs.next()){          
            String formapagamento = rs.getString("formapagamento");
            double v = Double.parseDouble(rs.getString("valor"));
            String valor = df.format(v);
            //modelPagar.addElement(new AReceber(formapagamento,valor));
            
            if(formapagamento.contains("Dinheiro")){
                dinheiro+=v;
            }
            if(formapagamento.contains("À Prazo")){
                prazo+=v;
            }
            if(formapagamento.contains("Cheque")){
                cheque+=v;
            }
            if(formapagamento.contains("Parcelado")){
                parcelado+=v;
            }
            if(formapagamento.contains("Crédito")){
                credito+=v;
            }            
            if(formapagamento.contains("Débito")){
                debito+=v;
            }
                        
            vendadiaria+=v;
        }
        
        modelPagar.addElement(new AReceber("Dinheiro: ",df.format(dinheiro)));
        modelPagar.addElement(new AReceber("À Prazo: ",df.format(prazo)));
        modelPagar.addElement(new AReceber("Cheque: ",df.format(cheque)));
        modelPagar.addElement(new AReceber("Parcelado: ",df.format(parcelado)));
        modelPagar.addElement(new AReceber("Crédito: ",df.format(credito)));
        modelPagar.addElement(new AReceber("Débito: ",df.format(debito)));
        
        String venda = df.format(vendadiaria);
        lblvenda.setText(lblvenda.getText()+": "+venda);
        
        listVenda.setModel(modelPagar);
        listVenda.setCellRenderer(new RendererAReceber());
        stmt.close();
        conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Inicial.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ////////////////////////////////////
        
    }
    
     private class RendererCompromisso extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(
                JList list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            Agenda label = (Agenda) value;
            String hora = label.getHorario();
            String data = label.getData();
            String local = label.getLocal();
            String assunto = label.getAssunto();
           
            String labelText = "<html>Local: " + local + "<br/>Assunto: " + assunto + "<br/>Data: " + data + "<br/>Hora: "+hora;
            setText(labelText);

            return this;
        }

    }
     
    private class RendererAPagar extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(
                JList list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            APagar label = (APagar) value;
            String fornecedor = label.getForncedor();
            String valor = label.getValor();
            
           
            String labelText = "<html>"+fornecedor+" R$:"+ valor;
            setText(labelText);

            return this;
        }
    }
    
    private class RendererAReceber extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(
                JList list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            AReceber label = (AReceber) value;
            String formapagamento = label.getFormapagamento();
            String valor = label.getValor();
            
           
            String labelText = "<html>"+formapagamento+" R$:"+ valor;
            setText(labelText);

            return this;
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

        jPanel1 = new javax.swing.JPanel();
        lblNome = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        lblCNPJ = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lblCompromisso = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        lblapagar = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listPagar = new javax.swing.JList<>();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listReceber = new javax.swing.JList<>();
        jPanel10 = new javax.swing.JPanel();
        lblareceber = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        listVenda = new javax.swing.JList<>();
        jPanel12 = new javax.swing.JPanel();
        lblvenda = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        listCompromisso = new javax.swing.JList<>();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnPDVCaixa = new javax.swing.JButton();
        btnPDVComanda = new javax.swing.JButton();
        btnPesquisaVendas = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        btnContasPagar = new javax.swing.JButton();
        btnContasReceber = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        btnRelatorio = new javax.swing.JButton();
        btnAgenda = new javax.swing.JButton();
        btnInventario = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        btnGrafico = new javax.swing.JButton();
        btnConfiguracao = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(60, 65, 103));

        lblNome.setBackground(new java.awt.Color(255, 255, 255));
        lblNome.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        lblNome.setForeground(new java.awt.Color(255, 255, 255));
        lblNome.setText("Empresa Teste");

        lblData.setBackground(new java.awt.Color(255, 255, 255));
        lblData.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        lblData.setForeground(new java.awt.Color(255, 255, 255));
        lblData.setText("12/08/2019 17:45");

        lblCNPJ.setBackground(new java.awt.Color(255, 255, 255));
        lblCNPJ.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        lblCNPJ.setForeground(new java.awt.Color(255, 255, 255));
        lblCNPJ.setText("01.856.98925/0001-98");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(lblNome)
                .addGap(18, 18, 18)
                .addComponent(lblCNPJ)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblData)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome)
                    .addComponent(lblCNPJ)
                    .addComponent(lblData))
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

        jPanel3.setBackground(new java.awt.Color(245, 135, 66));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel8))
        );

        jPanel5.setBackground(new java.awt.Color(60, 65, 103));

        lblCompromisso.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblCompromisso.setForeground(new java.awt.Color(255, 255, 255));
        lblCompromisso.setText("Compromisso");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(lblCompromisso)
                .addContainerGap(92, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblCompromisso, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jPanel7.setBackground(new java.awt.Color(60, 65, 103));

        lblapagar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblapagar.setForeground(new java.awt.Color(255, 255, 255));
        lblapagar.setText("À Pagar");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(lblapagar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblapagar, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        listPagar.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.light"));
        listPagar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jScrollPane2.setViewportView(listPagar);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        listReceber.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.light"));
        listReceber.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jScrollPane3.setViewportView(listReceber);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel10.setBackground(new java.awt.Color(60, 65, 103));

        lblareceber.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblareceber.setForeground(new java.awt.Color(255, 255, 255));
        lblareceber.setText("À Receber");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(lblareceber)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblareceber, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        listVenda.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.light"));
        listVenda.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jScrollPane4.setViewportView(listVenda);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel12.setBackground(new java.awt.Color(60, 65, 103));

        lblvenda.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblvenda.setForeground(new java.awt.Color(255, 255, 255));
        lblvenda.setText("Venda Diária");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(lblvenda)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblvenda, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jButton14.setBackground(new java.awt.Color(255, 255, 255));
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arrow.png"))); // NOI18N
        jButton14.setBorderPainted(false);
        jButton14.setFocusPainted(false);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        listCompromisso.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.light"));
        listCompromisso.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jScrollPane6.setViewportView(listCompromisso);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Vendas");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnPDVCaixa.setBackground(new java.awt.Color(95, 189, 197));
        btnPDVCaixa.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnPDVCaixa.setForeground(new java.awt.Color(255, 255, 255));
        btnPDVCaixa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/basket.png"))); // NOI18N
        btnPDVCaixa.setText("PDV-Caixa");
        btnPDVCaixa.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnPDVCaixa.setFocusPainted(false);
        btnPDVCaixa.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPDVCaixa.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPDVCaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDVCaixaActionPerformed(evt);
            }
        });

        btnPDVComanda.setBackground(new java.awt.Color(242, 173, 82));
        btnPDVComanda.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnPDVComanda.setForeground(new java.awt.Color(255, 255, 255));
        btnPDVComanda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/notepad.png"))); // NOI18N
        btnPDVComanda.setText("PDV-Comanda");
        btnPDVComanda.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnPDVComanda.setFocusPainted(false);
        btnPDVComanda.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPDVComanda.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPDVComanda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDVComandaActionPerformed(evt);
            }
        });

        btnPesquisaVendas.setBackground(new java.awt.Color(95, 189, 197));
        btnPesquisaVendas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnPesquisaVendas.setForeground(new java.awt.Color(255, 255, 255));
        btnPesquisaVendas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        btnPesquisaVendas.setText("Pesq. de Vendas");
        btnPesquisaVendas.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnPesquisaVendas.setFocusPainted(false);
        btnPesquisaVendas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPesquisaVendas.setIconTextGap(1);
        btnPesquisaVendas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPesquisaVendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaVendasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnPDVComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPDVCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(0, 0, 0)
                .addComponent(btnPesquisaVendas, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(0, 0, 0)
                .addComponent(btnPDVCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnPDVComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisaVendas, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnPDVComanda.getAccessibleContext().setAccessibleName("Comanda");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Financeiro");

        jButton4.setBackground(new java.awt.Color(95, 189, 197));
        jButton4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/receipt.png"))); // NOI18N
        jButton4.setText("Entrada NF");
        jButton4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton4.setFocusPainted(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        btnContasPagar.setBackground(new java.awt.Color(242, 173, 82));
        btnContasPagar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnContasPagar.setForeground(new java.awt.Color(255, 255, 255));
        btnContasPagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/loss.png"))); // NOI18N
        btnContasPagar.setText("Contas a Pagar");
        btnContasPagar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnContasPagar.setFocusPainted(false);
        btnContasPagar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnContasPagar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnContasPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContasPagarActionPerformed(evt);
            }
        });

        btnContasReceber.setBackground(new java.awt.Color(95, 189, 197));
        btnContasReceber.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnContasReceber.setForeground(new java.awt.Color(255, 255, 255));
        btnContasReceber.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/revenue.png"))); // NOI18N
        btnContasReceber.setText("Contas a Receber");
        btnContasReceber.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnContasReceber.setFocusPainted(false);
        btnContasReceber.setHideActionText(true);
        btnContasReceber.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnContasReceber.setIconTextGap(1);
        btnContasReceber.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnContasReceber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContasReceberActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnContasPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(0, 0, 0)
                .addComponent(btnContasReceber, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(0, 0, 0)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnContasPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnContasReceber, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Cadastro");

        jButton7.setBackground(new java.awt.Color(95, 189, 197));
        jButton7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delivery-truck.png"))); // NOI18N
        jButton7.setText("Fornecedor");
        jButton7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton7.setFocusPainted(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(242, 173, 82));
        jButton8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/network.png"))); // NOI18N
        jButton8.setText("Cliente");
        jButton8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton8.setFocusPainted(false);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(95, 189, 197));
        jButton9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/barcode.png"))); // NOI18N
        jButton9.setText("Produtos");
        jButton9.setBorderPainted(false);
        jButton9.setFocusPainted(false);
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton9.setIconTextGap(1);
        jButton9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(0, 0, 0)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(0, 0, 0)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Gestão");

        btnRelatorio.setBackground(new java.awt.Color(95, 189, 197));
        btnRelatorio.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnRelatorio.setForeground(new java.awt.Color(255, 255, 255));
        btnRelatorio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/report.png"))); // NOI18N
        btnRelatorio.setText("Relatório");
        btnRelatorio.setBorderPainted(false);
        btnRelatorio.setFocusPainted(false);
        btnRelatorio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRelatorio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRelatorioActionPerformed(evt);
            }
        });

        btnAgenda.setBackground(new java.awt.Color(242, 173, 82));
        btnAgenda.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnAgenda.setForeground(new java.awt.Color(255, 255, 255));
        btnAgenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/diary.png"))); // NOI18N
        btnAgenda.setText("Agenda");
        btnAgenda.setBorderPainted(false);
        btnAgenda.setFocusPainted(false);
        btnAgenda.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAgenda.setIconTextGap(1);
        btnAgenda.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAgenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgendaActionPerformed(evt);
            }
        });

        btnInventario.setBackground(new java.awt.Color(95, 189, 197));
        btnInventario.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnInventario.setForeground(new java.awt.Color(255, 255, 255));
        btnInventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/inventory.png"))); // NOI18N
        btnInventario.setText("Inventário");
        btnInventario.setBorderPainted(false);
        btnInventario.setFocusPainted(false);
        btnInventario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnInventario.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInventarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(0, 0, 0)
                .addComponent(btnInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(0, 0, 0)
                .addComponent(btnRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Operacional");

        jButton12.setBackground(new java.awt.Color(95, 189, 197));
        jButton12.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/customer-service.png"))); // NOI18N
        jButton12.setText("Suporte");
        jButton12.setBorderPainted(false);
        jButton12.setFocusPainted(false);
        jButton12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton12.setIconTextGap(1);
        jButton12.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        btnGrafico.setBackground(new java.awt.Color(242, 173, 82));
        btnGrafico.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnGrafico.setForeground(new java.awt.Color(255, 255, 255));
        btnGrafico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/graph.png"))); // NOI18N
        btnGrafico.setText("Gráfico");
        btnGrafico.setBorderPainted(false);
        btnGrafico.setFocusPainted(false);
        btnGrafico.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGrafico.setIconTextGap(1);
        btnGrafico.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGrafico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGraficoActionPerformed(evt);
            }
        });

        btnConfiguracao.setBackground(new java.awt.Color(95, 189, 197));
        btnConfiguracao.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnConfiguracao.setForeground(new java.awt.Color(255, 255, 255));
        btnConfiguracao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/settings.png"))); // NOI18N
        btnConfiguracao.setText("Configuração");
        btnConfiguracao.setBorderPainted(false);
        btnConfiguracao.setFocusPainted(false);
        btnConfiguracao.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnConfiguracao.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnConfiguracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfiguracaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(0, 0, 0)
                .addComponent(btnConfiguracao, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(0, 0, 0)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConfiguracao, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(73, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(125, 125, 125)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(125, 125, 125)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(66, 66, 66)
                            .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPDVComandaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDVComandaActionPerformed
        PDVComanda pdvcomanda = new PDVComanda();
        pdvcomanda.setVisible(true);
    }//GEN-LAST:event_btnPDVComandaActionPerformed

    private void btnPesquisaVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaVendasActionPerformed
       PesquisaVendas pesquisaVendas = new PesquisaVendas();
       pesquisaVendas.setVisible(true);
    }//GEN-LAST:event_btnPesquisaVendasActionPerformed

    private void btnContasPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContasPagarActionPerformed
       ContasPagar contaspagar = new ContasPagar();
       contaspagar.setVisible(true);
    }//GEN-LAST:event_btnContasPagarActionPerformed

    private void btnContasReceberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContasReceberActionPerformed
        ContasReceber contasreceber = new ContasReceber();
        contasreceber.setVisible(true);
              
    }//GEN-LAST:event_btnContasReceberActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        Clientes clientes = new Clientes();
        clientes.setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
       Produtos produtos = new Produtos();
       produtos.setVisible(true);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void btnInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInventarioActionPerformed
        Inventario inventario = new Inventario();
        inventario.setVisible(true);
    }//GEN-LAST:event_btnInventarioActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void btnRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRelatorioActionPerformed
       Relatorios relatorios = new Relatorios();
       relatorios.setVisible(true);
    }//GEN-LAST:event_btnRelatorioActionPerformed

    private void btnAgendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgendaActionPerformed
        AgendaView agendaview = new AgendaView();
        agendaview.setVisible(true);
    }//GEN-LAST:event_btnAgendaActionPerformed

    private void btnGraficoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGraficoActionPerformed
        Graficos graficos = new Graficos();
        graficos.setVisible(true);
    }//GEN-LAST:event_btnGraficoActionPerformed

    private void btnPDVCaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDVCaixaActionPerformed
         try {
             ConsultaCaixa();
         } catch (SQLException ex) {
             Logger.getLogger(Inicial.class.getName()).log(Level.SEVERE, null, ex);
         }
    }//GEN-LAST:event_btnPDVCaixaActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        Login login = new Login();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        Fornecedores fornecedores = new Fornecedores();
        fornecedores.setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        NotaFiscal notafiscal = new NotaFiscal();
        notafiscal.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnConfiguracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfiguracaoActionPerformed
        Configuracao configuracao = new Configuracao();
        configuracao.setVisible(true);
    }//GEN-LAST:event_btnConfiguracaoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws SQLException {
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
            java.util.logging.Logger.getLogger(Inicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicial().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgenda;
    private javax.swing.JButton btnConfiguracao;
    private javax.swing.JButton btnContasPagar;
    private javax.swing.JButton btnContasReceber;
    private javax.swing.JButton btnGrafico;
    private javax.swing.JButton btnInventario;
    private javax.swing.JButton btnPDVCaixa;
    private javax.swing.JButton btnPDVComanda;
    private javax.swing.JButton btnPesquisaVendas;
    private javax.swing.JButton btnRelatorio;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lblCNPJ;
    private javax.swing.JLabel lblCompromisso;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblapagar;
    private javax.swing.JLabel lblareceber;
    private javax.swing.JLabel lblvenda;
    private javax.swing.JList<String> listCompromisso;
    private javax.swing.JList<String> listPagar;
    private javax.swing.JList<String> listReceber;
    private javax.swing.JList<String> listVenda;
    // End of variables declaration//GEN-END:variables

    public void ConsultaCaixa() throws SQLException{
        
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy"); 		 
	Date dataDoSistema = new Date();		
	String dataEmTexto = formatador.format(dataDoSistema);
        //Registro Compra
        
        String sql1 = "SELECT * FROM `caixa` WHERE STR_TO_DATE(data, '%d/%m/%Y') BETWEEN STR_TO_DATE('"+dataEmTexto+"', '%d/%m/%Y')AND STR_TO_DATE('"+dataEmTexto+"', '%d/%m/%Y') ORDER BY STR_TO_DATE(data, '%d/%m/%Y') ASC";
      
        
        Connection conn = ConexaoBD.Conectar();           
        PreparedStatement stmt = conn.prepareStatement(sql1);
        ResultSet rs = stmt.executeQuery();
        
        boolean existe = false;
             
        while(rs.next()){            
            String tipo = rs.getString("tipo");
            
            if(tipo.equals("Abertura")){
                existe = true;
            }
                             
        }
        
        if(existe == false){
            AberturaCaixa abertura = new AberturaCaixa();
            abertura.setVisible(true);
        }
        if(existe == true){
            PDVCaixa pdv = new PDVCaixa();
            pdv.setVisible(true);
        }
    }
    
    
    public void preencheEmpresa(){
        String Empresa = "";
        String CNPJ = "";
        
        String path = System.getProperty("user.dir")+"/";
         int cont = 0;
         
        BufferedReader buffRead = null;
        try {
            
            buffRead = new BufferedReader(new FileReader(path+"dados.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        String linha = "";
        while (true) {
            if (linha != null) {
                cont++;
                
                
                
            } else
                break;
            try {
                linha = buffRead.readLine();
                if(cont == 3){
                   Empresa = linha; 
                }
                if(cont == 4){
                   CNPJ = linha; 
                }
                
            } catch (IOException ex) {
                Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            buffRead.close();
        } catch (IOException ex) {
            Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        lblNome.setText(Empresa);
        lblCNPJ.setText(CNPJ);
    
    }
    
}
