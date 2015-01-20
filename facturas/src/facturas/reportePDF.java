/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
//import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Alex
 */
public class reportePDF extends javax.swing.JInternalFrame {
DefaultTableModel modelo;
    /**
     * Creates new form reportePDF
     */
    public reportePDF() {
        initComponents();
        String cabecera[]={"# Factura","Fecha","SubTotal","Descuento","IVA","Total","Responsable","tipo","ice","propietario","Emisor"};
        String datos[][]={};
        modelo=new DefaultTableModel(datos,cabecera);
        jTable1.setModel(modelo);
        mostrar();
    }
private void mostrar(){
        
for (int i = 0; i < modelo.getRowCount(); i++) {
           modelo.removeRow(i);
           i-=1;
       }
conectar con=new  conectar();
Connection reg=con.conexion();


        Statement st;
    try {
        st = reg.createStatement();
         ResultSet rs=st.executeQuery("select*from factura ");
        while(rs.next()){
/*,,,,,,,,,*/  
            String num=rs.getString("idFactura");
            String a=rs.getString("fecFactura");
            String b=rs.getString("subtFactura");
            String c=rs.getString("descFactura");
            String d=rs.getString("ivaFactura");
            String e=rs.getString("totFactura");
            String f=rs.getString("respFactura");
            String g=rs.getString("tipFactura");  
            String h=rs.getString("iceFactura"); 
            String i=rs.getString("Usuario_cedUsuario");  
            String j=rs.getString("Proveedor_rucProveedor");  
            Object datos[]={num,a,b,c,d,e,f,g,h,i,j};
            modelo.addRow(datos);
            }
    } catch (SQLException ex) {
        Logger.getLogger(elProveedor.class.getName()).log(Level.SEVERE, null, ex);
    }
}

//fuente: http://freedomfloss.blogspot.com/2014/08/generar-reporte-excel-pdf-powerpoint.html
/*public void generarReport() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) ctx.getExternalContext().getResponse();
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("periodo", periodo);//parametro para el query que genera mis datos de reporte
        response.setContentType("application/pdf");
/*Explico la variable tipoDocumento  se compone de un evento que el usuario ejecuta, es decir,  que el tipo de documento puede tomar lo siguientes formatos: - xlsx -pdf -docx -odt -ppt
        response.setHeader("Content-Disposition", "attachment; filename=\"Facturas" + tipoDocumento + "\";");
//        response.setHeader("Content-Disposition", "attachment; filename=\"nombre_del_reporte.pdf\";");
//        response.setHeader("Content-Disposition", "attachment; filename=\"nombre_del_reporte.xlsx\";");
//        response.setHeader("Content-Disposition", "attachment; filename=\"nombre_del_reporte.docx\";");
//        response.setHeader("Content-Disposition", "attachment; filename=\"nombre_del_reporte.pptx\";");
//        response.setHeader("Content-Disposition", "attachment; filename=\"nombre_del_reporte.odt\";");
        try {
            JasperReport reporte;
            JasperPrint jasperPrint = null;
            ServletOutputStream out = response.getOutputStream();
            try {
                reporte = (JasperReport) JRLoader.loadObject(ctx.getExternalContext().getRealPath("WEB-INF/reportes/BalanceFinal.jasper"));
                jasperPrint = JasperFillManager.fillReport(reporte, parametros, new JREmptyDataSource());
            } catch (JRException ex) {
                Logger.getLogger(JasperReportViewer.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                 //Generar reporte a pdf
                if (tipoDocumento.equals("pdf")) {
                    JRExporter exporter;
                    exporter = new JRPdfExporter();
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                    exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
                    exporter.exportReport();
                } 
                 //Generar reporte a excel
                 else if (tipoDocumento.equals("xlsx")) {
                    JRXlsxExporter exporter = new JRXlsxExporter();
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                    exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
                    exporter.exportReport();
                }
                 //Generar reporte a powerPoint
                 else if (tipoDocumento.equals("pptx")) {
         JRPptxExporter exporter=new JRPptxExporter();
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                    exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
                    exporter.exportReport();
                }
               //Generar reporte a word
                 else if (tipoDocumento.equals("docx")) {
                    JRDocxExporter exporter=new JRDocxExporter();
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                    exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
                    exporter.exportReport();
                }
                 //Generar reporte a ODT
                 else if (tipoDocumento.equals("odt")) {
                    JROdtExporter exporter=new JROdtExporter();
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                    exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
                    exporter.exportReport();
                }
                out.flush();
                out.close();
                ctx.responseComplete();

                /*
                 JRXlsxExporter docxExporter=new JRXlsxExporter();  
                 docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);  
                 docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);  
                 docxExporter.exportReport();  
                 FacesContext.getCurrentInstance().responseComplete();  
                
            } catch (JRException ex) {
                Logger.getLogger(ReportesView.class.getName()).log(Level.SEVERE, null, ex);
            }


        } catch (IOException ex) {
            Logger.getLogger(ReportesView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    String tipoDocumento;
public void tipoDoc(String tipoDoc) {
        tipoDocumento = tipoDoc; 
        //Donde el parametro de llegada puede ser - xlsx -pdf -docx -odt -ppt
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Generar Reporte en PDF");

        jButton1.setText("Generar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(195, 195, 195)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
        conectar con=new  conectar();
        //Connection reg=con.conexion();
<<<<<<< HEAD
        /* "C:\\Users\\Alex\\Documents\\NetBeansProjects\\Gestor-de-facturas-PLVC\\facturas\\src\\facturas\\reportFacturas.jrxml" */
        String dir="..\\facturas\\src\\facturas\\reportFacturas.jrxml";
=======
<<<<<<< HEAD
        /* "C:\\Users\\Alex\\Documents\\NetBeansProjects\\Gestor-de-facturas-PLVC\\facturas\\src\\facturas\\reportFacturas.jrxml" */
        String dir="..\\facturas\\src\\facturas\\reportFacturas.jrxml";
=======
        String dir="C:\\Users\\Alex\\Documents\\GitHub\\Gestor-de-facturas-PLVC\\facturas\\src\\facturas\\reportFactura.jrxml";
>>>>>>> origin/scriptMySQL
>>>>>>> origin/scriptMySQL
        JasperReport reporteFacts = JasperCompileManager.compileReport(dir);
        JasperPrint mostarReporte = JasperFillManager.fillReport(reporteFacts, null, con.conexion());
                JasperViewer.viewReport(mostarReporte);
        }
        catch(JRException e) {
            Logger.getLogger(porProveedor.class.getName()).log(Level.SEVERE, null, e);
        }
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
