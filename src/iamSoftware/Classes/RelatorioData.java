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
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import static iamSoftware.Interfaces.ContasPagar.conveter;
import static iamSoftware.Interfaces.ContasPagar.data;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import javax.swing.SwingConstants;

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
            
            Paragraph p = new Paragraph("Relatório");
            p.setAlignment(1);
            doc.add(p);
            p = new Paragraph(" ");
            doc.add(p);
            
            PdfPTable table = new PdfPTable(7);
            
            
            
            PdfPCell cell1 = new PdfPCell(new Paragraph("Cód."));
            
            PdfPCell cell2 = new PdfPCell(new Paragraph("Nome"));
            PdfPCell cell3 = new PdfPCell(new Paragraph("CNPJ"));
            PdfPCell cell4 = new PdfPCell(new Paragraph("Data Emissão"));
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
            
            DecimalFormat df = new DecimalFormat("#,###.00");
            
            
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
                cell7 = new PdfPCell(new Paragraph(df.format(valorNF)+""));

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
            
            p = new Paragraph("Valor total: "+df.format(valorfinal)+"");
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
        DecimalFormat df = new DecimalFormat("#,###.00");
        
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(arquivoPDF));
            doc.open();
            
            doc.setMargins(0, 0, 0, 0);
            
            Paragraph p = new Paragraph("Relatório");
            p.setAlignment(1);
            doc.add(p);
            p = new Paragraph(" ");
            doc.add(p);
            
            PdfPTable table = new PdfPTable(6);
            
            
            
            PdfPCell cell1 = new PdfPCell(new Paragraph("Cód."));            
            PdfPCell cell2 = new PdfPCell(new Paragraph("Fonecedor"));
            PdfPCell cell3 = new PdfPCell(new Paragraph("N° Doc."));
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
                
               
                
                Double valor = Double.parseDouble(rs.getString("valor"));                
                String status = rs.getString("status");
                
                valorfinal+=valor;
                
               
                cell1 = new PdfPCell(new Paragraph(id+""));
                cell2 = new PdfPCell(new Paragraph(nome+""));
                cell3 = new PdfPCell(new Paragraph(numDoc+""));
                cell4 = new PdfPCell(new Paragraph(vencimento+""));
                cell5 = new PdfPCell(new Paragraph(df.format(valor)+""));
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
            
            p = new Paragraph("Valor total: "+df.format(valorfinal)+"");
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
                
            }
            if(Status.equals("Em aberto")){
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
        
        DecimalFormat df = new DecimalFormat("#,###.00");
        
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(arquivoPDF));
            doc.open();
            
            doc.setMargins(0, 0, 0, 0);
            
            Paragraph p = new Paragraph("Relatório Contas a Receber");
            p.setAlignment(1);
            doc.add(p);
            p = new Paragraph(" ");
            doc.add(p);
            
            PdfPTable table = new PdfPTable(5);          
            
            PdfPCell cell1 = new PdfPCell(new Paragraph("Cód."));            
            PdfPCell cell2 = new PdfPCell(new Paragraph("Cliente"));
            //PdfPCell cell3 = new PdfPCell(new Paragraph("N° Doc."));
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
                cell4 = new PdfPCell(new Paragraph(df.format(valor)+""));
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
            
            p = new Paragraph("Valor total: "+df.format(valorfinal)+"");
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
        DecimalFormat df = new DecimalFormat("#,###.00");
               
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(arquivoPDF));
            doc.open();
            
            doc.setMargins(0, 0, 0, 0);
            
            Paragraph p = new Paragraph("Relatório de Produtos");
            p.setAlignment(1);
            doc.add(p);
            p = new Paragraph(" ");
            doc.add(p);
            
            PdfPTable table = new PdfPTable(5);
            
            PdfPCell cell1 = new PdfPCell(new Paragraph("Cód"));            
            PdfPCell cell2 = new PdfPCell(new Paragraph("Produto"));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Un. Medida"));
            PdfPCell cell4 = new PdfPCell(new Paragraph("Valor Compra"));
            PdfPCell cell5 = new PdfPCell(new Paragraph("Valor Venda"));
                       
            
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
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
                
                String sql2= "SELECT valorcompra FROM notas WHERE nomeproduto='"+nome+"'";
                PreparedStatement stmt2 = conn.prepareStatement(sql2);
                ResultSet rs2 = stmt2.executeQuery();
                
                while(rs2.next()){
                    double valorCompra = rs2.getDouble("valorcompra");
                    cell4 = new PdfPCell(new Paragraph(df.format(valorCompra)+""));
                }
                
                cell5 = new PdfPCell(new Paragraph(df.format(valorVenda)+""));
               
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
    
    public void gerarRankingMaiorValor() throws SQLException{
        
        String sql= "SELECT * FROM produtos ORDER BY valorVenda DESC LIMIT 10";
        
        Connection conn = ConexaoBD.Conectar();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        Document doc = new Document();
        String arquivoPDF = "relatorio-ranking.pdf";
        DecimalFormat df = new DecimalFormat("#,###.00");
        
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(arquivoPDF));
            doc.open();
            
            doc.setMargins(0, 0, 0, 0);
            
            Paragraph p = new Paragraph("Relatório de Ranking de Produtos");
            p.setAlignment(1);
            doc.add(p);
            p = new Paragraph(" ");
            doc.add(p);
            
            PdfPTable table = new PdfPTable(3);
            
            PdfPCell cell1 = new PdfPCell(new Paragraph("Produto"));            
            PdfPCell cell2 = new PdfPCell(new Paragraph("Cód. de Barra"));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Valor"));
            
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            
                        
            //entra for
            while (rs.next()) {
                            
                String nome = rs.getString("nome");
                String codigo = rs.getString("codigo");
                Double valorVenda = rs.getDouble("valorVenda");
                                
                cell1 = new PdfPCell(new Paragraph(nome+""));
                cell2 = new PdfPCell(new Paragraph(codigo+""));
                cell3 = new PdfPCell(new Paragraph(df.format(valorVenda)+""));
                               
                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                
            }
            
            
            float[] columnWidths = new float[]{20f, 20f, 10f};
            table.setWidths(columnWidths);
            
            table.setWidthPercentage(110);
            doc.add(table);
         
            
            doc.close();           
            Desktop.getDesktop().open(new File(arquivoPDF));
            
           
            
        } catch (Exception e) {
        }
    
    }
    
    public void gerarRankingMaiorVendas() throws SQLException{
        
        String sql= "SELECT *, SUM(quantidade) FROM itenscompras GROUP BY produto";
        
        Connection conn = ConexaoBD.Conectar();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        Document doc = new Document();
        String arquivoPDF = "relatorio-ranking-produtos-mais-vendidos.pdf";
        DecimalFormat df = new DecimalFormat("#,###.00");
        
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(arquivoPDF));
            doc.open();
            
            doc.setMargins(0, 0, 0, 0);
            
            Paragraph p = new Paragraph("Relatório de Ranking de Produtos Mais Vendidos");
            p.setAlignment(1);
            doc.add(p);
            p = new Paragraph(" ");
            doc.add(p);
            
            PdfPTable table = new PdfPTable(3);
            
            PdfPCell cell1 = new PdfPCell(new Paragraph("Produto"));            
            PdfPCell cell2 = new PdfPCell(new Paragraph("Quantidade"));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Valor"));
            
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            
                        
            //entra for
            while (rs.next()) {
                            
                String nome = rs.getString("produto");
                String quantidade = rs.getString("SUM(quantidade)");
                //Double valorVenda = rs.getDouble("valorVenda");
                
                String codigo = rs.getString("codigoproduto");
                
                cell1 = new PdfPCell(new Paragraph(nome+""));
                cell2 = new PdfPCell(new Paragraph(quantidade+""));
                
                               
                table.addCell(cell1);
                table.addCell(cell2);
               
                
                String sql2= "SELECT * FROM produtos WHERE codigo='"+codigo+"'";
                PreparedStatement stmt2 = conn.prepareStatement(sql2);
                ResultSet rs2 = stmt2.executeQuery();
                
                while(rs2.next()){
                    double valor = rs2.getDouble("valorVenda");
                    cell3 = new PdfPCell(new Paragraph(df.format(valor)+""));
                }
                
                table.addCell(cell3);
                
            }
            
            
            float[] columnWidths = new float[]{20f, 20f, 10f};
            table.setWidths(columnWidths);
            
            table.setWidthPercentage(110);
            doc.add(table);
         
            
            doc.close();           
            Desktop.getDesktop().open(new File(arquivoPDF));
            
           
            
        } catch (Exception e) {
        }
    
    }
    
    public void gerarFaturamento(String ano) throws SQLException{
        int mes = 1;
        Double valorfinal=0.0;
        String strMes = "";
                
        Document doc = new Document();
        String arquivoPDF = "relatorio-faturamento.pdf";
        DecimalFormat df = new DecimalFormat("#,###.00");       
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(arquivoPDF));
            doc.open();
            
            doc.setMargins(0, 0, 0, 0);
            
            Paragraph p = new Paragraph("Relatório de Faturamento");
            p.setAlignment(1);
            doc.add(p);
            p = new Paragraph(" ");
            doc.add(p);
            
            PdfPTable table = new PdfPTable(3);
            
            PdfPCell cell1 = new PdfPCell(new Paragraph("Ano"));            
            PdfPCell cell2 = new PdfPCell(new Paragraph("Mês"));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Valor"));
            
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            
            while(mes <=12){
                
                switch(mes){
                    case 1: 
                        strMes="Janeiro";
                        break;
                        
                    case 2: 
                        strMes="Fevereiro";
                        break;    
                    
                    case 3: 
                        strMes="Março";
                        break;
                        
                    case 4: 
                        strMes="Abril";
                        break;
                        
                    case 5: 
                        strMes="Maio";
                        break;
                        
                    case 6: 
                        strMes="Junho";
                        break;
                        
                    case 7: 
                        strMes="Julho";
                        break;
                        
                    case 8: 
                        strMes="Agosto";
                        break;    
                    
                    case 9: 
                        strMes="Setembro";
                        break;
                        
                    case 10: 
                        strMes="Outubro";
                        break;
                        
                    case 11: 
                        strMes="Novembro";
                        break;
                        
                    case 12: 
                        strMes="Dezembro";
                        break;
                }
                
                
                String sql= "SELECT * FROM `contasreceber` WHERE STR_TO_DATE(datapagamento, '%d/%m/%Y') BETWEEN STR_TO_DATE('01/"+mes+"/"+ano+"', '%d/%m/%Y') AND STR_TO_DATE('31/"+mes+"/"+ano+"', '%d/%m/%Y') ORDER BY STR_TO_DATE(datapagamento, '%d/%m/%Y') ASC";

                Connection conn = ConexaoBD.Conectar();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                
                cell1 = new PdfPCell(new Paragraph(ano+""));
                cell2 = new PdfPCell(new Paragraph(strMes+""));
                
                table.addCell(cell1);
                table.addCell(cell2); 
                
                //entra for
                while (rs.next()) {
                         
                    Double valor = Double.parseDouble(rs.getString("valor"));                    
                    valorfinal+=valor;
       
                }
                
                cell3 = new PdfPCell(new Paragraph(df.format(valorfinal)+""));     
                table.addCell(cell3); 
                
                valorfinal=0.0;
                mes+=1;
            }
            
            
            float[] columnWidths = new float[]{20f, 20f, 10f};
            table.setWidths(columnWidths);
            
            table.setWidthPercentage(110);
            doc.add(table);
         
            
            doc.close();           
            Desktop.getDesktop().open(new File(arquivoPDF));
            
           
            
        } catch (Exception e) {
        }
    
    }
    
    public void gerarFaturamentoPeriodo(String dataInicial, String dataFinal) throws SQLException{
        
                
        Document doc = new Document();
        String arquivoPDF = "relatorio-faturamento-periodo.pdf";
        DecimalFormat df = new DecimalFormat("#,###.00");       
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(arquivoPDF));
            doc.open();
            
            doc.setMargins(0, 0, 0, 0);
            
            Paragraph p = new Paragraph("Relatório de Faturamento por Período");
            p.setAlignment(1);
            doc.add(p);
            p = new Paragraph(" ");
            doc.add(p);
            
            double dinheiro = 0;
            double prazo = 0;
            double credito = 0;
            double debito = 0;
            double cheque = 0;
            
            
                
                
                String sql= "SELECT * FROM `contasreceber` WHERE STR_TO_DATE(datapagamento, '%d/%m/%Y') BETWEEN STR_TO_DATE('"+dataInicial+"', '%d/%m/%Y') AND STR_TO_DATE('"+dataFinal+"', '%d/%m/%Y') ORDER BY STR_TO_DATE(datapagamento, '%d/%m/%Y') ASC";

                Connection conn = ConexaoBD.Conectar();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                
               
                
                //entra for
                while (rs.next()) {
                    
                    String forma = rs.getString("formapagamento");
                    Double valor = rs.getDouble("valor");
                    
                    if(forma.contains("Dinheiro")){
                        System.out.println("1");
                        dinheiro+=valor;
                    }
                    if(forma.contains("Prazo")){
                        System.out.println("2");
                        prazo+=valor;
                    }
                    if(forma.contains("Crédito")){
                        System.out.println("3");
                        credito+=valor;
                    }
                    if(forma.contains("Débito")){
                        System.out.println("4");
                        debito+=valor;
                    }
                    if(forma.contains("Cheque")){
                        System.out.println("5");
                        cheque+=valor;
                    }
                    
                }
                
                
            p = new Paragraph("Dinheiro: "+dinheiro);
            p.setAlignment(1);
            doc.add(p);
            
            p = new Paragraph("Prazo: "+prazo);
            p.setAlignment(1);
            doc.add(p);
            
            p = new Paragraph("Crédito: "+credito);
            p.setAlignment(1);
            doc.add(p);
            
            p = new Paragraph("Débito: "+debito);
            p.setAlignment(1);
            doc.add(p);
            
            p = new Paragraph("Cheque: "+cheque);
            p.setAlignment(1);
            doc.add(p);    
            
            
            doc.close();           
            Desktop.getDesktop().open(new File(arquivoPDF));
            
           
        }   
         catch (Exception e) {
        }
    
    }
    
    public void gerarRankingMaiorCompra() throws SQLException{
        
        String sql= "SELECT * FROM notas ORDER BY quantidade DESC LIMIT 10";
        
        Connection conn = ConexaoBD.Conectar();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        Document doc = new Document();
        String arquivoPDF = "relatorio-ranking-compra.pdf";
        DecimalFormat df = new DecimalFormat("#,###.00");       
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(arquivoPDF));
            doc.open();
            
            doc.setMargins(0, 0, 0, 0);
            
            Paragraph p = new Paragraph("Relatório de Ranking de Maiores Compras");
            p.setAlignment(1);
            doc.add(p);
            p = new Paragraph(" ");
            doc.add(p);
            
            PdfPTable table = new PdfPTable(3);
            
            PdfPCell cell1 = new PdfPCell(new Paragraph("Produto"));            
            PdfPCell cell2 = new PdfPCell(new Paragraph("Quantidade"));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Valor"));
            
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            
                        
            //entra for
            while (rs.next()) {
                            
                String nome = rs.getString("nomeproduto");
                Double codigo = rs.getDouble("quantidade");
                Double valorVenda = rs.getDouble("valorcompra");
                                
                cell1 = new PdfPCell(new Paragraph(nome+""));
                cell2 = new PdfPCell(new Paragraph(codigo+""));
                cell3 = new PdfPCell(new Paragraph(df.format(valorVenda)+""));
                               
                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                
            }
            
            
            float[] columnWidths = new float[]{20f, 20f, 10f};
            table.setWidths(columnWidths);
            
            table.setWidthPercentage(110);
            doc.add(table);
         
            
            doc.close();           
            Desktop.getDesktop().open(new File(arquivoPDF));
            
           
            
        } catch (Exception e) {
        }
    
    }
    
    public void gerarRankingMaiorNota() throws SQLException{
        
        String sql= "SELECT * FROM notas ORDER BY valorcompra DESC LIMIT 10";
        
        Connection conn = ConexaoBD.Conectar();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        Document doc = new Document();
        String arquivoPDF = "relatorio-ranking-notas.pdf";
        DecimalFormat df = new DecimalFormat("#,###.00");       
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(arquivoPDF));
            doc.open();
            
            doc.setMargins(0, 0, 0, 0);
            
            Paragraph p = new Paragraph("Relatório de Ranking de Maiores Notas");
            p.setAlignment(1);
            doc.add(p);
            p = new Paragraph(" ");
            doc.add(p);
            
            PdfPTable table = new PdfPTable(3);
            
            PdfPCell cell1 = new PdfPCell(new Paragraph("Fornecedor"));            
            PdfPCell cell2 = new PdfPCell(new Paragraph("Data"));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Valor"));
            
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            
                        
            //entra for
            while (rs.next()) {
                            
                String nome = rs.getString("nomefornecedor");
                String codigo = rs.getString("dataregistro");
                Double valorVenda = rs.getDouble("valorcompra");
                                
                cell1 = new PdfPCell(new Paragraph(nome+""));
                cell2 = new PdfPCell(new Paragraph(codigo+""));
                cell3 = new PdfPCell(new Paragraph(df.format(valorVenda)+""));
                               
                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                
            }
            
            
            float[] columnWidths = new float[]{20f, 20f, 10f};
            table.setWidths(columnWidths);
            
            table.setWidthPercentage(110);
            doc.add(table);
         
            
            doc.close();           
            Desktop.getDesktop().open(new File(arquivoPDF));
            
           
            
        } catch (Exception e) {
        }
    
    }
    
    public void gerarRankingMaiorContasPagar() throws SQLException{
        
        String sql= "SELECT * FROM contaspagar ORDER BY valor DESC LIMIT 10";
        
        Connection conn = ConexaoBD.Conectar();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        Document doc = new Document();
        String arquivoPDF = "relatorio-ranking-contas-pagar.pdf";
        DecimalFormat df = new DecimalFormat("#,###.00");       
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(arquivoPDF));
            doc.open();
            
            doc.setMargins(0, 0, 0, 0);
            
            Paragraph p = new Paragraph("Relatório de Ranking de Maiores Contas a Pagar");
            p.setAlignment(1);
            doc.add(p);
            p = new Paragraph(" ");
            doc.add(p);
            
            PdfPTable table = new PdfPTable(3);
            
            PdfPCell cell1 = new PdfPCell(new Paragraph("Fornecedor"));            
            PdfPCell cell2 = new PdfPCell(new Paragraph("Data"));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Valor"));
            
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            
                        
            //entra for
            while (rs.next()) {
                Object[] dados = new Object[1];            
                String nome = rs.getString("fornecedor");
                
                dados[0]= rs.getString("vencimento");
                String vencimento = (String) dados[0];
                char[] dataArray = vencimento.toCharArray();
                vencimento = converter(dataArray);
                
                Double valorVenda = rs.getDouble("valor");
                                
                cell1 = new PdfPCell(new Paragraph(nome+""));
                cell2 = new PdfPCell(new Paragraph(vencimento+""));
                cell3 = new PdfPCell(new Paragraph(df.format(valorVenda)+""));
                               
                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                
            }
            
            
            float[] columnWidths = new float[]{20f, 20f, 10f};
            table.setWidths(columnWidths);
            
            table.setWidthPercentage(110);
            doc.add(table);
         
            
            doc.close();           
            Desktop.getDesktop().open(new File(arquivoPDF));
            
           
            
        } catch (Exception e) {
        }
    
    }
    
    public void gerarRankingMaiorContasReceber() throws SQLException{
        
        String sql= "SELECT * FROM contasreceber ORDER BY valor DESC LIMIT 10";
        
        Connection conn = ConexaoBD.Conectar();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        Document doc = new Document();
        String arquivoPDF = "relatorio-ranking-contas-receber.pdf";
        DecimalFormat df = new DecimalFormat("#,###.00");       
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(arquivoPDF));
            doc.open();
            
            doc.setMargins(0, 0, 0, 0);
            
            Paragraph p = new Paragraph("Relatório de Ranking de Maiores Contas a Receber");
            p.setAlignment(1);
            doc.add(p);
            p = new Paragraph(" ");
            doc.add(p);
            
            PdfPTable table = new PdfPTable(3);
            
            PdfPCell cell1 = new PdfPCell(new Paragraph("Cliente"));            
            PdfPCell cell2 = new PdfPCell(new Paragraph("Data"));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Valor"));
            
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            
                        
            //entra for
            while (rs.next()) {
                          
                String nome = rs.getString("cliente");
                String vencimento = rs.getString("datapagamento");
                Double valorVenda = rs.getDouble("valor");
                                
                cell1 = new PdfPCell(new Paragraph(nome+""));
                cell2 = new PdfPCell(new Paragraph(vencimento+""));
                cell3 = new PdfPCell(new Paragraph(df.format(valorVenda)+""));
                               
                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                
            }
            
            
            float[] columnWidths = new float[]{20f, 20f, 10f};
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
            
            Paragraph p = new Paragraph("Relatório de Fornecedores");
            p.setAlignment(1);
            doc.add(p);
            p = new Paragraph(" ");
            doc.add(p);
            
            PdfPTable table = new PdfPTable(4);
            
            PdfPCell cell1 = new PdfPCell(new Paragraph("Cód."));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            PdfPCell cell2 = new PdfPCell(new Paragraph("Fornecedor"));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            PdfPCell cell3 = new PdfPCell(new Paragraph("CPF/CNPJ"));
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            //PdfPCell cell4 = new PdfPCell(new Paragraph("Valor Compra"));
            PdfPCell cell5 = new PdfPCell(new Paragraph("Ramo de Atividade"));
            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
                       
            
            
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
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                cell2 = new PdfPCell(new Paragraph(nome+""));
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                cell3 = new PdfPCell(new Paragraph(cpfcnpj+""));
                cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                //cell4 = new PdfPCell(new Paragraph(valorCompra+""));
                cell5 = new PdfPCell(new Paragraph(ramo+""));
                cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
               
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
    }  //OK 
    
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
            
            Paragraph p = new Paragraph("Relatório de Clientes");
            p.setAlignment(1);
            doc.add(p);
            p = new Paragraph(" ");
            doc.add(p);
            
            PdfPTable table = new PdfPTable(5);
            
            PdfPCell cell1 = new PdfPCell(new Paragraph("Cód.")); 
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            PdfPCell cell2 = new PdfPCell(new Paragraph("Cliente"));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            PdfPCell cell3 = new PdfPCell(new Paragraph("CPF/CNPJ"));
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER); 
            
            PdfPCell cell4 = new PdfPCell(new Paragraph("Prazo"));
            cell4.setHorizontalAlignment(Element.ALIGN_CENTER); 
            
            PdfPCell cell5 = new PdfPCell(new Paragraph("Limite"));
            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
                       
            
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
                
                DecimalFormat df = new DecimalFormat("#,###.00");
                //df.format(1234.36); 
                                
                cell1 = new PdfPCell(new Paragraph(id+""));
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                cell2 = new PdfPCell(new Paragraph(nome+""));
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                cell3 = new PdfPCell(new Paragraph(cpfcnpj+""));
                cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                cell4 = new PdfPCell(new Paragraph(prazo+""));
                cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                cell5 = new PdfPCell(new Paragraph(df.format(limite)+""));
                cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
               
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
    } //OK
    
    public void gerarAgenda(String datainicial, String datafinal) throws SQLException{
        
        //String sql= "SELECT * FROM produtos, notas WHERE produtos.nome=notas.nomeproduto";
        String sql= "";
        
        if(datainicial.equals("") && datafinal.equals("")){
            sql = "SELECT * FROM `agenda`";
        }else{
            sql= "SELECT * FROM `agenda`WHERE STR_TO_DATE(data, '%d/%m/%Y') BETWEEN STR_TO_DATE('"+datainicial+"', '%d/%m/%Y') AND STR_TO_DATE('"+datafinal+"', '%d/%m/%Y') ORDER BY STR_TO_DATE(data, '%d/%m/%Y') ASC";
        }
        
        //WHERE STR_TO_DATE(datapagamento, '%d/%m/%Y') BETWEEN STR_TO_DATE('"+datainicial+"', '%d/%m/%Y') AND STR_TO_DATE('"+datafinal+"', '%d/%m/%Y') ORDER BY STR_TO_DATE(datapagamento, '%d/%m/%Y') ASC";
        
        Connection conn = ConexaoBD.Conectar();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        Document doc = new Document();
        String arquivoPDF = "relatorio-agenda.pdf";
               
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(arquivoPDF));
            doc.open();
            
            doc.setMargins(0, 0, 0, 0);
            
            Paragraph p = new Paragraph("Relatório de Agenda");
            p.setAlignment(1);
            doc.add(p);
            p = new Paragraph(" ");
            doc.add(p);
            
            PdfPTable table = new PdfPTable(4);
            
            PdfPCell cell1 = new PdfPCell(new Paragraph("Data"));
            PdfPCell cell2 = new PdfPCell(new Paragraph("Hora"));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Local"));
            PdfPCell cell4 = new PdfPCell(new Paragraph("Assunto"));
                  
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            
                        
            //entra for
            while (rs.next()) {
                 
                String data = rs.getString("data");
                String horario = rs.getString("horario");                
                String local = rs.getString("local");
                String assunto = rs.getString("assunto");                
                
                cell1 = new PdfPCell(new Paragraph(data+""));
                cell2 = new PdfPCell(new Paragraph(horario+""));
                cell3 = new PdfPCell(new Paragraph(local+""));
                cell4 = new PdfPCell(new Paragraph(assunto+""));
               
                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                table.addCell(cell4);
                
            }
            
            
            float[] columnWidths = new float[]{20f, 20f, 20f, 20f};
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
        
        Double totalVenda = 0.0;
        Double totalCompra = 0.0;
        Double valorVenda = 0.0;
        Double valorCompra = 0.0;
        
        Connection conn = ConexaoBD.Conectar();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        Document doc = new Document();
        String arquivoPDF = "relatorio-composicao-produto.pdf";
        DecimalFormat df = new DecimalFormat("#,###.00");       
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(arquivoPDF));
            doc.open();
            
            doc.setMargins(0, 0, 0, 0);
            
            Paragraph p = new Paragraph("Relatório de Composição de Produto");
            p.setAlignment(1);
            doc.add(p);
            p = new Paragraph(" ");
            doc.add(p);
            
            PdfPTable table = new PdfPTable(6);
            
            PdfPCell cell1 = new PdfPCell(new Paragraph("Cód."));            
            PdfPCell cell2 = new PdfPCell(new Paragraph("Produto"));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Quantidade"));
            PdfPCell cell4 = new PdfPCell(new Paragraph("Un. Med."));
            PdfPCell cell5 = new PdfPCell(new Paragraph("Valor de Compra"));
            PdfPCell cell6 = new PdfPCell(new Paragraph("Valor de Venda"));
                  
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
            table.addCell(cell6);
                        
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
                    
                    valorVenda = rs2.getDouble("valorVenda");
                    cell6 = new PdfPCell(new Paragraph(df.format(valorVenda)+""));
                   
                    
                    
                    String sql3= "SELECT valorcompra FROM `notas` WHERE nomeproduto='"+nome+"'";
                    
                    PreparedStatement stmt3 = conn.prepareStatement(sql3);
                    ResultSet rs3 = stmt3.executeQuery();
                
                    while (rs3.next()) {
                        
                        valorCompra = rs3.getDouble("valorcompra");
                        cell5 = new PdfPCell(new Paragraph(df.format(valorCompra)+""));
                      
                    }
                }
                
                totalVenda = valorVenda + totalVenda;
                totalCompra = valorCompra + totalCompra;
                
                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                table.addCell(cell4);
                table.addCell(cell5);
                table.addCell(cell6);
            }    
                    
            
            cell1 = new PdfPCell(new Paragraph("Total"));
            cell2 = new PdfPCell(new Paragraph(""));
            cell3 = new PdfPCell(new Paragraph(""));
            cell4 = new PdfPCell(new Paragraph(""));
            cell5 = new PdfPCell(new Paragraph(df.format(totalCompra)+""));
            cell6 = new PdfPCell(new Paragraph(df.format(totalVenda)+""));
            
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
            table.addCell(cell6);
    
            
            float[] columnWidths = new float[]{20f, 20f, 20f, 20f, 20f, 20f};
            table.setWidths(columnWidths);
            
            table.setWidthPercentage(110);
            doc.add(table);
            
            doc.close();           
            Desktop.getDesktop().open(new File(arquivoPDF));
            
            
        } catch (Exception e) {
        }
    }
    
     public void gerarCartao(String datainicial, String datafinal, String Status) throws SQLException{
        
        //String sql= "SELECT * FROM produtos, notas WHERE produtos.nome=notas.nomeproduto";
        //String sql= "SELECT * FROM `contasreceber` WHERE STR_TO_DATE(datapagamento, '%d/%m/%Y') BETWEEN STR_TO_DATE('"+datainicial+"', '%d/%m/%Y') AND STR_TO_DATE('"+datafinal+"', '%d/%m/%Y') ORDER BY STR_TO_DATE(datapagamento, '%d/%m/%Y') ASC";
        String sql = null;
        
        if(Status.equals("Todos")){
            sql= "SELECT * FROM `contasreceber` INNER JOIN compras ON contasreceber.idcompra=compras.id WHERE STR_TO_DATE(datapagamento, '%d/%m/%Y') BETWEEN STR_TO_DATE('"+datainicial+"', '%d/%m/%Y') AND STR_TO_DATE('"+datafinal+"', '%d/%m/%Y') ORDER BY STR_TO_DATE(datapagamento, '%d/%m/%Y') ASC";
        
        }
        if(Status.equals("Em aberto")){
            sql = "SELECT * FROM `contasreceber` WHERE STR_TO_DATE(datapagamento, '%d/%m/%Y') BETWEEN STR_TO_DATE('"+datainicial+"', '%d/%m/%Y') AND STR_TO_DATE('"+datafinal+"', '%d/%m/%Y') AND status='Em Aberto' ORDER BY STR_TO_DATE(datapagamento, '%d/%m/%Y') ASC";
               
        }
        if(Status.equals("Liquidado")){
            sql = "SELECT * FROM `contasreceber` WHERE STR_TO_DATE(datapagamento, '%d/%m/%Y') BETWEEN STR_TO_DATE('"+datainicial+"', '%d/%m/%Y') AND STR_TO_DATE('"+datafinal+"', '%d/%m/%Y') AND status='Liquidado' ORDER BY STR_TO_DATE(datapagamento, '%d/%m/%Y') ASC";
                
        }
        
        Connection conn = ConexaoBD.Conectar();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        Document doc = new Document();
        String arquivoPDF = "relatorio-cartao.pdf";
        DecimalFormat df = new DecimalFormat("#,###.00");       
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(arquivoPDF));
            doc.open();
            
            doc.setMargins(0, 0, 0, 0);
            
            Paragraph p = new Paragraph("Relatório de Cartão de Crédito e Débito");
            p.setAlignment(1);
            doc.add(p);
            p = new Paragraph(" ");
            doc.add(p);
            
            PdfPTable table = new PdfPTable(6);
            
            PdfPCell cell1 = new PdfPCell(new Paragraph("Data Venda"));            
            PdfPCell cell2 = new PdfPCell(new Paragraph("Valor de Venda")); //ok
            PdfPCell cell3 = new PdfPCell(new Paragraph("Data Depósito")); //ok
            PdfPCell cell4 = new PdfPCell(new Paragraph("Valor Depósito")); //ok
            PdfPCell cell5 = new PdfPCell(new Paragraph("Valor Comissão")); //ok
            PdfPCell cell6 = new PdfPCell(new Paragraph("Status")); //ok
                       
            
            table.addCell(cell1);
            table.addCell(cell2); //ok
            table.addCell(cell3); //ok
            table.addCell(cell4); //ok
            table.addCell(cell5); //ok
            table.addCell(cell6); //ok
                        
            //entra for
            while (rs.next()) {
                    
                String dataRegistro = rs.getString("dataregistro");
                Double valorVenda = Double.parseDouble(rs.getString("valor")); //ok
                String dataDeposito = rs.getString("datapagamento");      // ok                            
                String status = rs.getString("status");
                String formaPagamento = rs.getString("formapagamento");
                
                cell1 = new PdfPCell(new Paragraph(dataRegistro+""));
                cell2 = new PdfPCell(new Paragraph(df.format(valorVenda)+""));
                cell3 = new PdfPCell(new Paragraph(dataDeposito+""));
                cell6 = new PdfPCell(new Paragraph(status+"")); 
                
                String credito = "Crédito ";
                String debito = "Débito ";
                String resultFormaPagamento = "";     
                
                if(formaPagamento.contains(credito) || formaPagamento.contains(debito)){
                    
                    if(formaPagamento.contains("Crédito")){
                        resultFormaPagamento = formaPagamento.replaceFirst(credito,"");
                         
                    }
                    if(formaPagamento.contains("Débito")){
                        resultFormaPagamento = formaPagamento.replaceFirst(debito,"");
                         
                    }
                    
                    String sql2= "SELECT * FROM `cartao` WHERE bandeira='"+resultFormaPagamento+"'";                    
                    PreparedStatement stmt2 = conn.prepareStatement(sql2);
                    ResultSet rs2 = stmt2.executeQuery();
                    
                    while(rs2.next()){
                        Double comissao = rs2.getDouble("taxacomissao");
                        comissao = valorVenda * (comissao/100);                        
                        cell5 = new PdfPCell(new Paragraph(df.format(comissao)+""));
                        
                        Double valorDeposito = valorVenda - comissao;
                        cell4 = new PdfPCell(new Paragraph(df.format(valorDeposito)+""));
                    }
                    
                    
                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                table.addCell(cell4);
                table.addCell(cell5);
                table.addCell(cell6);
                    
                }
                 
                
            }
            
            
            float[] columnWidths = new float[]{15f, 15f, 15f, 15f, 15f, 15f};
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
