/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iamSoftware.Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import javax.swing.text.Document;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import static iamSoftware.Interfaces.ContasPagar.conveter;
import static iamSoftware.Interfaces.ContasPagar.data;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;

/**
 *
 * @author ga_br
 */
public class RelatorioData {
    
    //SELECT * FROM notas WHERE STR_TO_DATE(dataemissao, '%d/%m/%Y') BETWEEN STR_TO_DATE('27/08/2019', '%d/%m/%Y') AND STR_TO_DATE('27/03/2019', '%d/%m/%Y');
    public void gerarNotaFical(String Fornecedor, String datainicial, String datafinal) throws SQLException{
    
        Double valorfinal=0.0;
        String sql;
        
        if(Fornecedor.equals("")){
            sql = "SELECT * FROM notas WHERE STR_TO_DATE(dataemissao, '%d/%m/%Y') BETWEEN STR_TO_DATE('"+datainicial+"', '%d/%m/%Y') AND STR_TO_DATE('"+datafinal+"', '%d/%m/%Y') ORDER BY STR_TO_DATE(dataemissao, '%d/%m/%Y') ASC";
        }else{
            sql = "SELECT * FROM notas WHERE STR_TO_DATE(dataemissao, '%d/%m/%Y') BETWEEN STR_TO_DATE('"+datainicial+"', '%d/%m/%Y') AND STR_TO_DATE('"+datafinal+"', '%d/%m/%Y') AND nomefornecedor='"+Fornecedor+"' ORDER BY STR_TO_DATE(dataemissao, '%d/%m/%Y') ASC";
        }
        
        Connection conn = ConexaoBD.Conectar();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        
        Document doc = new Document();
        String arquivoPDF = "relatorio-nota-fiscal.pdf";

        try {
            PdfWriter.getInstance(doc, new FileOutputStream(arquivoPDF));
            doc.open();
            
            doc.setMargins(0, 0, 0, 0);
            
            Paragraph p = new Paragraph("Relat�rio");
            p.setAlignment(1);
            doc.add(p);
            p = new Paragraph(" ");
            doc.add(p);
            
            PdfPTable table = new PdfPTable(7);
            
            
            
            PdfPCell cell1 = new PdfPCell(new Paragraph("ID"));
            
            PdfPCell cell2 = new PdfPCell(new Paragraph("Nome"));
            PdfPCell cell3 = new PdfPCell(new Paragraph("CNPJ"));
            PdfPCell cell4 = new PdfPCell(new Paragraph("Data Emiss�o"));
            PdfPCell cell5 = new PdfPCell(new Paragraph("Data Entrada"));
            PdfPCell cell6 = new PdfPCell(new Paragraph("Numero do Documento"));
            PdfPCell cell7 = new PdfPCell(new Paragraph("Valor NF"));
            
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
            table.addCell(cell6);
            table.addCell(cell7);
            
            
            
            //entra for
            while (rs.next()) {
                
                int id = rs.getInt("id");
                String nome = rs.getString("nomefornecedor");
                String cnpj = rs.getString("cnpj");
                String dataEmissao = rs.getString("dataemissao");
                String dataEntrada = rs.getString("dataregistro");
                String numDoc = rs.getString("numeronota");
                Double valorNF = rs.getDouble("valorcompra");

                valorfinal+=valorNF;
                
                cell1 = new PdfPCell(new Paragraph(id+""));
                cell2 = new PdfPCell(new Paragraph(nome+""));
                cell3 = new PdfPCell(new Paragraph(cnpj+""));
                cell4 = new PdfPCell(new Paragraph(dataEmissao+""));
                cell5 = new PdfPCell(new Paragraph(dataEntrada+""));
                cell6 = new PdfPCell(new Paragraph(numDoc+""));
                cell7 = new PdfPCell(new Paragraph(valorNF+""));

                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                table.addCell(cell4);
                table.addCell(cell5);
                table.addCell(cell6);
                table.addCell(cell7);
            }
            
            float[] columnWidths = new float[]{10f, 20f, 20f, 20f, 20f, 20f, 10f};
            table.setWidths(columnWidths);
            
            table.setWidthPercentage(110);
            doc.add(table);
            
            p = new Paragraph("Valor total: "+valorfinal+"");
            p.setAlignment(1);
            doc.add(p);
            p = new Paragraph(" ");
            doc.add(p);
            
            doc.close();
            Desktop.getDesktop().open(new File(arquivoPDF));
            
        } catch (Exception e) {
        }
    }
        
    public void gerarContasPagar(String Status, String Fornecedor, String datainicial, String datafinal) throws SQLException{
        String data;
        System.out.println(datainicial);
        
        Double valorfinal=0.0;
        String sql = null;
        
        if(Fornecedor.equals("")){
            if(Status.equals("Todos")){
                sql= "SELECT * FROM `contaspagar` WHERE vencimento BETWEEN '"+datainicial+"' AND '"+datafinal+"' ORDER BY vencimento ASC";
                System.out.println(sql);
            }
            if(Status.equals("Em Aberto")){
                sql = "SELECT * FROM `contaspagar` WHERE vencimento BETWEEN '"+datainicial+"' AND '"+datafinal+"' AND status='Em Aberto' ORDER BY vencimento ASC";
                System.out.println("entrou 02");
            }
            if(Status.equals("Liquidado")){
                sql = "SELECT * FROM `contaspagar` WHERE vencimento BETWEEN '"+datainicial+"' AND '"+datafinal+"' AND status='Liquidado' ORDER BY vencimento ASC";
                System.out.println("entrou 03");
            }
            
            //sql = "SELECT * FROM notas WHERE STR_TO_DATE(dataemissao, '%d/%m/%Y') BETWEEN STR_TO_DATE('"+datainicial+"', '%d/%m/%Y') AND STR_TO_DATE('"+datafinal+"', '%d/%m/%Y')";
        }else{
            //sql = "SELECT * FROM notas WHERE STR_TO_DATE(dataemissao, '%d/%m/%Y') BETWEEN STR_TO_DATE('"+datainicial+"', '%d/%m/%Y') AND STR_TO_DATE('"+datafinal+"', '%d/%m/%Y') AND nomefornecedor='"+Fornecedor+"'";
            if(Status.equals("Todos")){
                sql = "SELECT * FROM `contaspagar` WHERE vencimento BETWEEN '"+datainicial+"' AND '"+datafinal+"' AND fornecedor='"+Fornecedor+"' ORDER BY vencimento ASC";
                System.out.println("entrou 04");
            }
            if(Status.equals("Em Aberto")){
                sql = "SELECT * FROM `contaspagar` WHERE vencimento BETWEEN '"+datainicial+"' AND '"+datafinal+"' AND fornecedor='"+Fornecedor+"' AND status='Em Aberto' ORDER BY vencimento ASC";
                System.out.println("entrou 05");
            }
            if(Status.equals("Liquidado")){
                sql = "SELECT * FROM `contaspagar` WHERE vencimento BETWEEN '"+datainicial+"' AND '"+datafinal+"' AND fornecedor='"+Fornecedor+"' AND status='Liquidado' ORDER BY vencimento ASC";
                System.out.println("entrou 06");
            }
        }
        
        Connection conn = ConexaoBD.Conectar();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        System.out.println("passou aqui");
        
        Document doc = new Document();
        String arquivoPDF = "relatorio-contas-pagar.pdf";
        
        System.out.println("passou aqui 2");
        
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(arquivoPDF));
            doc.open();
            
            doc.setMargins(0, 0, 0, 0);
            
            Paragraph p = new Paragraph("Relat�rio");
            p.setAlignment(1);
            doc.add(p);
            p = new Paragraph(" ");
            doc.add(p);
            
            PdfPTable table = new PdfPTable(6);
            
            
            
            PdfPCell cell1 = new PdfPCell(new Paragraph("ID"));            
            PdfPCell cell2 = new PdfPCell(new Paragraph("Fonecedor"));
            PdfPCell cell3 = new PdfPCell(new Paragraph("N� Doc."));
            PdfPCell cell4 = new PdfPCell(new Paragraph("Vencimento"));
            PdfPCell cell5 = new PdfPCell(new Paragraph("Valor"));
            PdfPCell cell6 = new PdfPCell(new Paragraph("Status"));
            
            
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
            table.addCell(cell6);
            
            //entra for
            while (rs.next()) {
                Object[] dados = new Object[1];
                
                int id = rs.getInt("id");                
                String nome = rs.getString("fornecedor");                
                String numDoc = rs.getString("numerodocumento");
                
                dados[0]= rs.getString("vencimento");
                String vencimento = (String) dados[0];
                char[] dataArray = vencimento.toCharArray();
                vencimento = converter(dataArray);
                System.out.println(dados[0]);
               
                
                Double valor = Double.parseDouble(rs.getString("valor"));                
                String status = rs.getString("status");
                
                valorfinal+=valor;
                
               
                cell1 = new PdfPCell(new Paragraph(id+""));
                cell2 = new PdfPCell(new Paragraph(nome+""));
                cell3 = new PdfPCell(new Paragraph(numDoc+""));
                cell4 = new PdfPCell(new Paragraph(vencimento+""));
                cell5 = new PdfPCell(new Paragraph(valor+""));
                cell6 = new PdfPCell(new Paragraph(status+""));
                
                
                
                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                table.addCell(cell4);
                table.addCell(cell5);
                table.addCell(cell6);
                
            }
            
            float[] columnWidths = new float[]{10f, 20f, 20f, 20f, 20f, 10f};
            table.setWidths(columnWidths);
            
            table.setWidthPercentage(110);
            doc.add(table);
            
            p = new Paragraph("Valor total: "+valorfinal+"");
            p.setAlignment(1);
            doc.add(p);
            p = new Paragraph(" ");
            doc.add(p);
            
            doc.close();
            Desktop.getDesktop().open(new File(arquivoPDF));
            
        } catch (Exception e) {
        }
    }
    
    public void gerarContasReceber(String Status, String datainicial, String datafinal) throws SQLException{
        String data;
        System.out.println(datainicial);
        
        Double valorfinal=0.0;
        String sql = null;
        
        
        //if(Fornecedor.equals("")){
        
            if(Status.equals("Todos")){
                sql= "SELECT * FROM `contasreceber` WHERE STR_TO_DATE(datapagamento, '%d/%m/%Y') BETWEEN STR_TO_DATE('"+datainicial+"', '%d/%m/%Y') AND STR_TO_DATE('"+datafinal+"', '%d/%m/%Y') ORDER BY STR_TO_DATE(datapagamento, '%d/%m/%Y') ASC";
                System.out.println(sql);
            }
            if(Status.equals("Em Aberto")){
                sql = "SELECT * FROM `contasreceber` WHERE STR_TO_DATE(datapagamento, '%d/%m/%Y') BETWEEN STR_TO_DATE('"+datainicial+"', '%d/%m/%Y') AND STR_TO_DATE('"+datafinal+"', '%d/%m/%Y') AND status='Em Aberto' ORDER BY STR_TO_DATE(datapagamento, '%d/%m/%Y') ASC";
               
            }
            if(Status.equals("Liquidado")){
                sql = "SELECT * FROM `contasreceber` WHERE STR_TO_DATE(datapagamento, '%d/%m/%Y') BETWEEN STR_TO_DATE('"+datainicial+"', '%d/%m/%Y') AND STR_TO_DATE('"+datafinal+"', '%d/%m/%Y') AND status='Liquidado' ORDER BY STR_TO_DATE(datapagamento, '%d/%m/%Y') ASC";
                
            }
           
            //sql = "SELECT * FROM notas WHERE STR_TO_DATE(dataemissao, '%d/%m/%Y') BETWEEN STR_TO_DATE('"+datainicial+"', '%d/%m/%Y') AND STR_TO_DATE('"+datafinal+"', '%d/%m/%Y')";
        //}else
        /*
            //sql = "SELECT * FROM notas WHERE STR_TO_DATE(dataemissao, '%d/%m/%Y') BETWEEN STR_TO_DATE('"+datainicial+"', '%d/%m/%Y') AND STR_TO_DATE('"+datafinal+"', '%d/%m/%Y') AND nomefornecedor='"+Fornecedor+"'";
            if(Status.equals("Todos")){
                sql = "SELECT * FROM `contaspagar` WHERE STR_TO_DATE(dataemissao, '%d/%m/%Y') BETWEEN STR_TO_DATE('"+datainicial+"', '%d/%m/%Y') AND STR_TO_DATE('"+datafinal+"', '%d/%m/%Y') AND fornecedor='"+Fornecedor+"' ORDER BY vencimento ASC";
                System.out.println("entrou 04");
            }
            if(Status.equals("Em Aberto")){
                sql = "SELECT * FROM `contaspagar` WHERE STR_TO_DATE(dataemissao, '%d/%m/%Y') BETWEEN STR_TO_DATE('"+datainicial+"', '%d/%m/%Y') AND STR_TO_DATE('"+datafinal+"', '%d/%m/%Y') AND fornecedor='"+Fornecedor+"' AND status='Em Aberto' ORDER BY vencimento ASC";
                System.out.println("entrou 05");
            }
            if(Status.equals("Liquidado")){
                sql = "SELECT * FROM `contaspagar` WHERE STR_TO_DATE(dataemissao, '%d/%m/%Y') BETWEEN STR_TO_DATE('"+datainicial+"', '%d/%m/%Y') AND STR_TO_DATE('"+datafinal+"', '%d/%m/%Y') AND fornecedor='"+Fornecedor+"' AND status='Liquidado' ORDER BY vencimento ASC";
                System.out.println("entrou 06");
            }
        //}
        */
        
        Connection conn = ConexaoBD.Conectar();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        
        
        Document doc = new Document();
        String arquivoPDF = "relatorio-contas-receber.pdf";
        
        
        
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(arquivoPDF));
            doc.open();
            
            doc.setMargins(0, 0, 0, 0);
            
            Paragraph p = new Paragraph("Relat�rio Contas a Receber");
            p.setAlignment(1);
            doc.add(p);
            p = new Paragraph(" ");
            doc.add(p);
            
            PdfPTable table = new PdfPTable(5);          
            
            PdfPCell cell1 = new PdfPCell(new Paragraph("ID"));            
            PdfPCell cell2 = new PdfPCell(new Paragraph("Cliente"));
            //PdfPCell cell3 = new PdfPCell(new Paragraph("N� Doc."));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Vencimento"));
            PdfPCell cell4 = new PdfPCell(new Paragraph("Valor"));
            PdfPCell cell5 = new PdfPCell(new Paragraph("Status"));
                        
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
            //table.addCell(cell6);
            
            //entra for
            while (rs.next()) {
                
                int id = rs.getInt("id");                
                String nome = rs.getString("cliente");                
                String vencimento= rs.getString("datapagamento");
                Double valor = Double.parseDouble(rs.getString("valor"));                
                String status = rs.getString("status");
                
                valorfinal+=valor;
                
               
                cell1 = new PdfPCell(new Paragraph(id+""));
                cell2 = new PdfPCell(new Paragraph(nome+""));
                cell3 = new PdfPCell(new Paragraph(vencimento+""));
                cell4 = new PdfPCell(new Paragraph(valor+""));
                cell5 = new PdfPCell(new Paragraph(status+""));
                //cell6 = new PdfPCell(new Paragraph(status+""));
                
                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                table.addCell(cell4);
                table.addCell(cell5);
                //table.addCell(cell6);
                
            }
            
            float[] columnWidths = new float[]{10f, 20f, 20f, 10f, 15f};
            table.setWidths(columnWidths);
            
            table.setWidthPercentage(110);
            doc.add(table);
            
            p = new Paragraph("Valor total: "+valorfinal+"");
            p.setAlignment(1);
            doc.add(p);
            p = new Paragraph(" ");
            doc.add(p);
            
            doc.close();
            Desktop.getDesktop().open(new File(arquivoPDF));
            
        } catch (Exception e) {
        }
    }
    
    public void gerarProdutos() throws SQLException{
        
        //String sql= "SELECT * FROM produtos, notas WHERE produtos.nome=notas.nomeproduto";
        String sql= "SELECT * FROM `produtos`";
        
        Connection conn = ConexaoBD.Conectar();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        Document doc = new Document();
        String arquivoPDF = "relatorio-produtos.pdf";
               
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(arquivoPDF));
            doc.open();
            
            doc.setMargins(0, 0, 0, 0);
            
            Paragraph p = new Paragraph("Relat�rio de Produtos");
            p.setAlignment(1);
            doc.add(p);
            p = new Paragraph(" ");
            doc.add(p);
            
            PdfPTable table = new PdfPTable(4);
            
            PdfPCell cell1 = new PdfPCell(new Paragraph("C�d"));            
            PdfPCell cell2 = new PdfPCell(new Paragraph("Produto"));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Un. Medida"));
            //PdfPCell cell4 = new PdfPCell(new Paragraph("Valor Compra"));
            PdfPCell cell5 = new PdfPCell(new Paragraph("Valor Venda"));
                       
            
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            //table.addCell(cell4);
            table.addCell(cell5);
                        
            //entra for
            while (rs.next()) {
               
                int id = rs.getInt("id");                
                String nome = rs.getString("nome");                
                String medida = rs.getString("medida");
                //Double valorCompra = rs.getDouble("valorcompra");                
                Double valorVenda = rs.getDouble("valorVenda");
                                
                cell1 = new PdfPCell(new Paragraph(id+""));
                cell2 = new PdfPCell(new Paragraph(nome+""));
                cell3 = new PdfPCell(new Paragraph(medida+""));
                //cell4 = new PdfPCell(new Paragraph(valorCompra+""));
                cell5 = new PdfPCell(new Paragraph(valorVenda+""));
               
                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                //table.addCell(cell4);
                table.addCell(cell5);
                
            }
            
            
            float[] columnWidths = new float[]{10f, 20f, 20f, 20f};
            table.setWidths(columnWidths);
            
            table.setWidthPercentage(110);
            doc.add(table);
            
            doc.close();           
            Desktop.getDesktop().open(new File(arquivoPDF));
            
            
        } catch (Exception e) {
        }
    }
    
    public void gerarFornecedor() throws SQLException{
        
        //String sql= "SELECT * FROM produtos, notas WHERE produtos.nome=notas.nomeproduto";
        String sql= "SELECT * FROM `fornecedores`";
        
        Connection conn = ConexaoBD.Conectar();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        Document doc = new Document();
        String arquivoPDF = "relatorio-fornecedores.pdf";
               
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(arquivoPDF));
            doc.open();
            
            doc.setMargins(0, 0, 0, 0);
            
            Paragraph p = new Paragraph("Relat�rio de Fornecedores");
            p.setAlignment(1);
            doc.add(p);
            p = new Paragraph(" ");
            doc.add(p);
            
            PdfPTable table = new PdfPTable(4);
            
            PdfPCell cell1 = new PdfPCell(new Paragraph("C�d"));            
            PdfPCell cell2 = new PdfPCell(new Paragraph("Fornecedor"));
            PdfPCell cell3 = new PdfPCell(new Paragraph("CPF/CNPJ"));
            //PdfPCell cell4 = new PdfPCell(new Paragraph("Valor Compra"));
            PdfPCell cell5 = new PdfPCell(new Paragraph("Ramo de Atividade"));
                       
            
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            //table.addCell(cell4);
            table.addCell(cell5);
                        
            //entra for
            while (rs.next()) {
               
                int id = rs.getInt("id");                
                String nome = rs.getString("nome");                
                String cpfcnpj = rs.getString("cpfcnpj");
                //Double valorCompra = rs.getDouble("valorcompra");                
                String ramo = rs.getString("ramoatividade");
                                
                cell1 = new PdfPCell(new Paragraph(id+""));
                cell2 = new PdfPCell(new Paragraph(nome+""));
                cell3 = new PdfPCell(new Paragraph(cpfcnpj+""));
                //cell4 = new PdfPCell(new Paragraph(valorCompra+""));
                cell5 = new PdfPCell(new Paragraph(ramo+""));
               
                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                //table.addCell(cell4);
                table.addCell(cell5);
                
            }
            
            
            float[] columnWidths = new float[]{10f, 20f, 20f, 20f};
            table.setWidths(columnWidths);
            
            table.setWidthPercentage(110);
            doc.add(table);
            
            doc.close();           
            Desktop.getDesktop().open(new File(arquivoPDF));
            
            
        } catch (Exception e) {
        }
    }
    
    public void gerarCliente() throws SQLException{
        
        //String sql= "SELECT * FROM produtos, notas WHERE produtos.nome=notas.nomeproduto";
        String sql= "SELECT * FROM `clientes`";
        
        Connection conn = ConexaoBD.Conectar();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        Document doc = new Document();
        String arquivoPDF = "relatorio-cliente.pdf";
               
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(arquivoPDF));
            doc.open();
            
            doc.setMargins(0, 0, 0, 0);
            
            Paragraph p = new Paragraph("Relat�rio de Clientes");
            p.setAlignment(1);
            doc.add(p);
            p = new Paragraph(" ");
            doc.add(p);
            
            PdfPTable table = new PdfPTable(5);
            
            PdfPCell cell1 = new PdfPCell(new Paragraph("C�d"));            
            PdfPCell cell2 = new PdfPCell(new Paragraph("Cliente"));
            PdfPCell cell3 = new PdfPCell(new Paragraph("CPF/CNPJ"));
            PdfPCell cell4 = new PdfPCell(new Paragraph("Prazo"));
            PdfPCell cell5 = new PdfPCell(new Paragraph("Limite"));
                       
            
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
                        
            //entra for
            while (rs.next()) {
               
                int id = rs.getInt("id");                
                String nome = rs.getString("nome");                
                String cpfcnpj = rs.getString("cpfcnpj");
                String prazo = rs.getString("prazo");                
                Double limite = rs.getDouble("limitecredito");
                                
                cell1 = new PdfPCell(new Paragraph(id+""));
                cell2 = new PdfPCell(new Paragraph(nome+""));
                cell3 = new PdfPCell(new Paragraph(cpfcnpj+""));
                cell4 = new PdfPCell(new Paragraph(prazo+""));
                cell5 = new PdfPCell(new Paragraph(limite+""));
               
                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                table.addCell(cell4);
                table.addCell(cell5);
                
            }
            
            
            float[] columnWidths = new float[]{10f, 20f, 20f, 20f, 20f};
            table.setWidths(columnWidths);
            
            table.setWidthPercentage(110);
            doc.add(table);
            
            doc.close();           
            Desktop.getDesktop().open(new File(arquivoPDF));
            
            
        } catch (Exception e) {
        }
    }
    
    public void gerarAgenda() throws SQLException{
        
        //String sql= "SELECT * FROM produtos, notas WHERE produtos.nome=notas.nomeproduto";
        String sql= "SELECT * FROM `agenda`";
        
        Connection conn = ConexaoBD.Conectar();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        Document doc = new Document();
        String arquivoPDF = "relatorio-agenda.pdf";
               
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(arquivoPDF));
            doc.open();
            
            doc.setMargins(0, 0, 0, 0);
            
            Paragraph p = new Paragraph("Relat�rio de Agenda");
            p.setAlignment(1);
            doc.add(p);
            p = new Paragraph(" ");
            doc.add(p);
            
            PdfPTable table = new PdfPTable(3);
            
            PdfPCell cell1 = new PdfPCell(new Paragraph("Data/Hora"));            
            PdfPCell cell2 = new PdfPCell(new Paragraph("Local"));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Assunto"));
                  
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            
                        
            //entra for
            while (rs.next()) {
                         
                String horario = rs.getString("horario");                
                String local = rs.getString("local");
                String assunto = rs.getString("assunto");                
                            
                cell1 = new PdfPCell(new Paragraph(horario+""));
                cell2 = new PdfPCell(new Paragraph(local+""));
                cell3 = new PdfPCell(new Paragraph(assunto+""));
               
                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                
            }
            
            
            float[] columnWidths = new float[]{20f, 20f, 20f};
            table.setWidths(columnWidths);
            
            table.setWidthPercentage(110);
            doc.add(table);
            
            doc.close();           
            Desktop.getDesktop().open(new File(arquivoPDF));
            
            
        } catch (Exception e) {
        }
    }
    
    public static void gerarProdutoComposto(int id) throws SQLException{
       
        //String sql= "SELECT * FROM produtos, notas WHERE produtos.nome=notas.nomeproduto";
        String sql= "SELECT * FROM `composicao` WHERE id_produto="+id;
        
        Connection conn = ConexaoBD.Conectar();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        Document doc = new Document();
        String arquivoPDF = "relatorio-composicao-produto.pdf";
               
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(arquivoPDF));
            doc.open();
            
            doc.setMargins(0, 0, 0, 0);
            
            Paragraph p = new Paragraph("Relat�rio de Composi��o de Produto");
            p.setAlignment(1);
            doc.add(p);
            p = new Paragraph(" ");
            doc.add(p);
            
            PdfPTable table = new PdfPTable(5);
            
            PdfPCell cell1 = new PdfPCell(new Paragraph("C�d."));            
            PdfPCell cell2 = new PdfPCell(new Paragraph("Produto"));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Quantidade"));
            PdfPCell cell4 = new PdfPCell(new Paragraph("Un. Med."));
            PdfPCell cell5 = new PdfPCell(new Paragraph("Valor Compra"));
                  
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
            
                        
            //entra for
            while (rs.next()) {
                         
                String codigo = rs.getString("id_composicao");                
                String nome = rs.getString("nome");
                Double quantidade = rs.getDouble("quantidade");                
                            
                cell1 = new PdfPCell(new Paragraph(codigo+""));
                cell2 = new PdfPCell(new Paragraph(nome+""));
                cell3 = new PdfPCell(new Paragraph(quantidade+""));
               
                
                
                String sql2= "SELECT * FROM `produtos` WHERE id="+codigo;        
                PreparedStatement stmt2 = conn.prepareStatement(sql2);
                ResultSet rs2 = stmt2.executeQuery();
                
                while (rs2.next()) {
                     
                    String medida = rs2.getString("medida");
                    cell4 = new PdfPCell(new Paragraph(medida+""));
                    
                   
                    String sql3= "SELECT valorcompra FROM `notas` WHERE nomeproduto='"+nome+"'";
                    
                    PreparedStatement stmt3 = conn.prepareStatement(sql3);
                    ResultSet rs3 = stmt3.executeQuery();
                
                    while (rs3.next()) {
                        
                        Double valorCompra = rs3.getDouble("valorcompra");
                        cell5 = new PdfPCell(new Paragraph(valorCompra+""));
                        
                       
                    }
                }
                
                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                table.addCell(cell4);
                table.addCell(cell5);
            }    
                    
                
                
            
    
            
            float[] columnWidths = new float[]{20f, 20f, 20f, 20f, 20f};
            table.setWidths(columnWidths);
            
            table.setWidthPercentage(110);
            doc.add(table);
            
            doc.close();           
            Desktop.getDesktop().open(new File(arquivoPDF));
            
            
        } catch (Exception e) {
        }
    }
    
    public static String converter(char[] dataArray){
    
        String dataString = dataArray[8]+""+dataArray[9]+"/"+dataArray[5]+""+dataArray[6]+"/20"+dataArray[2]+dataArray[3];  
        
    return dataString;    
    }
    
   
}
