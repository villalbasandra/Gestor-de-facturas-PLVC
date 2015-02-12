package facturas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alex
 */
public class actFactura extends javax.swing.JInternalFrame {
    private String nomUsuario;
    private String ruc="";
    private Double educacion, vivienda, alimentacion, vestimenta, salud, otro, ice, descuento;
    private Double ivaEducacion=0.0,  ivaAlimentacion=0.0, ivaVivienda=0.0, ivaSalud=0.0, ivaVestimenta=0.0, ivaOtro=0.0, iva=0.0, total;
            
    Operaciones operar=new Operaciones();
    DefaultTableModel modelo;
    /**
     * Creates new form actFactura
     */
    public actFactura() {
        initComponents();
       // combo();
       // combo2();
        String cabecera[]={"Emisor","# Factura", "Fecha", "Descuento","IVA", "Total"};
        String datos[][]={};
        modelo=new DefaultTableModel(datos,cabecera);
        jTable1.setModel(modelo);
        nomUsuario = operar.Usuario(aplicacion.user);
        jTextField1.setText(nomUsuario);
        jTextField1.setEditable(false);
        jTDescuento.setEditable(false);
        jTIVA.setEditable(false);
        jTEducacion.setEditable(false);
        jTVivienda.setEditable(false);
        jTAlimentacion.setEditable(false);
        jTVestimenta.setEditable(false);
        jTSalud.setEditable(false);
        jTOtro.setEditable(false);
        jTIce.setEditable(false);
        jTTotal.setEditable(false);
        operar.ComboRucRazon("razProveedor", jComboBox1);
        operar.TablaFactura("select p.razProveedor, f.numFactura, f.fecFactura, " +
                     "f.descFactura, f.ivaFactura, f.totFactura from proveedor p, factura f, usuario u " +
                    "where p.rucProveedor = f.rucProveedor and u.cedUsuario = f.cedUsuario and u.cedUsuario='"+aplicacion.user+"'", modelo);
    }
        
    private void consultar(){
        conectar con=new  conectar();
        Connection reg=con.Conectar();
        String razon, numero;
        razon = (String)jComboBox1.getSelectedItem();
        numero = (String)jComboBox4.getSelectedItem();
        Statement st;
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        try {      
            st = reg.createStatement();
            ResultSet rs=st.executeQuery("select fecFactura, eduFactura, ivaEFactura, saldFactura, ivaSFactura, vestFactura, ivaVTFactura, "
                    + "vivFactura, ivaVFactura, alimFactura, ivaAFactura, otroFactura, ivaOFactura, iceFactura, descFactura, ivaFactura, "
                    + "totFactura from factura f, proveedor p "
                    + "where f.rucProveedor=p.rucProveedor and p.razProveedor='"+razon+"' and f.numFactura='"+numero+"'");
            while(rs.next()){
                String a=rs.getString("fecFactura");
                String b=rs.getString("eduFactura");
                String c=rs.getString("ivaEFactura");
                String d=rs.getString("saldFactura");
                String e=rs.getString("ivaSFactura");
                String f=rs.getString("vestFactura"); 
                String g=rs.getString("ivaVTFactura");
                String h=rs.getString("vivFactura");
                String i=rs.getString("ivaVFactura");
                String j=rs.getString("alimFactura");
                String k=rs.getString("ivaAFactura");
                String l=rs.getString("otroFactura");
                String m=rs.getString("ivaOFactura");
                String n=rs.getString("iceFactura");
                String o=rs.getString("descFactura");
                String p=rs.getString("ivaFactura");
                String q=rs.getString("totFactura");
                try {
                    jDateChooser1.setDate(date.parse(a));
                }
                catch(ParseException es) {
                }
                jTEducacion.setText(b);
                if(!c.equals("0.00"))
                    jCEducacion.setSelected(true);
                jTSalud.setText(d);
                if(!e.equals("0.00"))
                    jCSalud.setSelected(true);
                jTVestimenta.setText(f);
                if(!g.equals("0.00"))
                    jCVestimenta.setSelected(true);
                jTVivienda.setText(h);
                if(!i.equals("0.00"))
                    jCVestimenta.setSelected(true);
                jTAlimentacion.setText(j);
                if(!k.equals("0.00"))
                    jCAlimentacion.setSelected(true);
                jTOtro.setText(l);
                if(!m.equals("0.00"))
                    jCOtro.setSelected(true);
                jTIce.setText(n);
                jTDescuento.setText(o);
                jTIVA.setText(p);
                jTTotal.setText(q);
            }   
        }
        catch (SQLException ex) {
            Logger.getLogger(elProveedor.class.getName()).log(Level.SEVERE, null, ex);
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTDescuento = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTIVA = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTEducacion = new javax.swing.JTextField();
        jTIce = new javax.swing.JTextField();
        jTTotal = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jComboBox4 = new javax.swing.JComboBox();
        jTextField1 = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jCEducacion = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        jTAlimentacion = new javax.swing.JTextField();
        jCAlimentacion = new javax.swing.JCheckBox();
        jLabel12 = new javax.swing.JLabel();
        jTSalud = new javax.swing.JTextField();
        jCSalud = new javax.swing.JCheckBox();
        jLabel13 = new javax.swing.JLabel();
        jTVivienda = new javax.swing.JTextField();
        jCVivienda = new javax.swing.JCheckBox();
        jLabel14 = new javax.swing.JLabel();
        jTVestimenta = new javax.swing.JTextField();
        jCVestimenta = new javax.swing.JCheckBox();
        jLabel15 = new javax.swing.JLabel();
        jTOtro = new javax.swing.JTextField();
        jCOtro = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jButton3 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Actualizar Factura");

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel1.setText("# Factura:");

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel2.setText("Fecha");

        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel3.setText("Proveedor");

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel4.setText("Usuario");

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel5.setText("Educación:");

        jTDescuento.setText("0.00");
        jTDescuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTDescuentoActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel7.setText("Total");

        jTIVA.setText("0.00");

        jLabel10.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel10.setText("Valor ICE");

        jLabel11.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel11.setText("Descuento");

        jTEducacion.setText("0.00");

        jTIce.setText("0.00");

        jTTotal.setText("0.00");
        jTTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTTotalActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/set-1/opened_folder.gif"))); // NOI18N
        jButton1.setText("Cargar Información");
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
        jTable1.setCellSelectionEnabled(true);
        jTable1.setFocusCycleRoot(true);
        jScrollPane1.setViewportView(jTable1);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/set-1/save.gif"))); // NOI18N
        jButton2.setText("Actualizar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jDateChooser1.setDateFormatString("yyyy-MM-dd");

        jCEducacion.setText("I.V.A.");

        jLabel9.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel9.setText("Alimentación:");

        jTAlimentacion.setText("0.00");

        jCAlimentacion.setText("I.V.A.");

        jLabel12.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel12.setText("Salud");

        jTSalud.setText("0.00");

        jCSalud.setText("I.V.A.");

        jLabel13.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel13.setText("Vivienda:");

        jTVivienda.setText("0.00");

        jCVivienda.setText("I.V.A.");

        jLabel14.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel14.setText("Vestimenta:");

        jTVestimenta.setText("0.00");

        jCVestimenta.setText("I.V.A.");

        jLabel15.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel15.setText("Otro");

        jTOtro.setText("0.00");

        jCOtro.setText("I.V.A.");

        jButton3.setText("I.V.A");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel14)
                                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jCOtro, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jCVestimenta, javax.swing.GroupLayout.Alignment.TRAILING)))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(layout.createSequentialGroup()
                                                            .addComponent(jLabel9)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addGroup(layout.createSequentialGroup()
                                                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addGap(27, 27, 27)))
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jTAlimentacion)
                                                        .addComponent(jTEducacion, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(layout.createSequentialGroup()
                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel12)
                                                                .addComponent(jLabel13))
                                                            .addGap(36, 36, 36))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING))
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jTIce, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(jTVivienda)
                                                            .addComponent(jTSalud)
                                                            .addComponent(jTVestimenta, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jTOtro, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(jTIVA, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jTTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addGap(37, 37, 37)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jCSalud)
                                                .addComponent(jCAlimentacion)
                                                .addComponent(jCVivienda)
                                                .addComponent(jCEducacion)))
                                        .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGap(10, 10, 10))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField1)
                                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(123, 123, 123)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(103, 103, 103)
                                .addComponent(jButton2)
                                .addGap(0, 181, Short.MAX_VALUE)))
                        .addGap(10, 10, 10))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(88, 88, 88)
                                .addComponent(jTDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addGap(21, 21, 21))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTEducacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCEducacion))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jTAlimentacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCAlimentacion))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jTSalud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCSalud))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jTVivienda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCVivienda))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jTVestimenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCVestimenta))
                        .addGap(1, 1, 1)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jTOtro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCOtro))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTIce, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jTDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTIVA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTTotalActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jTDescuento.setEditable(true);
        jTEducacion.setEditable(true);
        jTVivienda.setEditable(true);
        jTAlimentacion.setEditable(true);
        jTVestimenta.setEditable(true);
        jTSalud.setEditable(true);
        jTOtro.setEditable(true);
        jTIce.setEditable(true);
        consultar();       // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(JOptionPane.showConfirmDialog(null, "Seguro que quiere actualizar la informacion de la Factua?", "Confirmar cambio de datos", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
            String num="",fec="",razon="",ruc;
            razon = (String)jComboBox1.getSelectedItem();
            ruc=operar.getRucProveedor(razon);
            num=(String)jComboBox4.getSelectedItem();
            fec=jDateChooser1.getCalendar().get(Calendar.YEAR)+"-"+(jDateChooser1.getCalendar().get(Calendar.MONTH)+1)+"-"+jDateChooser1.getCalendar().get(Calendar.DATE);
            Date now = new Date();
            Statement st;
            if(now.compareTo(jDateChooser1.getDate())<0)
                JOptionPane.showMessageDialog(null, "La Fecha de Emisión no puede ser mayor que la fecha actual", "Fecha incorrecta", JOptionPane.ERROR_MESSAGE);
            else {
                conectar con=new  conectar();
                Connection reg=con.Conectar();
                try {
                    st = reg.createStatement();
                    String sql="update factura set fecFactura='"+fec+"', eduFactura="+educacion+", ivaEFactura="+ivaEducacion+","
                            + " saldFactura="+salud+", ivaSFactura="+ivaSalud+", vestFactura="+vestimenta+", ivaVTFactura="+ivaVestimenta+", "
                            + "vivFactura="+vivienda+", ivaVFactura="+ivaVivienda+", alimFactura="+alimentacion+", ivaAFactura="+ivaAlimentacion+", "
                            + "otroFactura="+otro+", ivaOFactura="+ivaOtro+"descFactura="+descuento+", ivaFactura="+iva+","
                            + " iceFactura="+ice+", totFactura="+total
                    + " where numFactura='"+num+"' and rucProveedor='"+ruc+"'";
                        
                        int rs=st.executeUpdate(sql);
                        if(rs>0){
                            JOptionPane.showMessageDialog(null, "Valores actualizados correctamente");
                            operar.TablaFactura("select p.razProveedor, f.numFactura, f.fecFactura, " +
                     "f.descFactura, f.ivaFactura, f.iceFactura, f.totFactura from proveedor p, factura f, usuario u " +
                    "where p.rucProveedor = f.rucProveedor and u.cedUsuario = f.cedUsuario and u.cedUsuario='"+aplicacion.user+"'", modelo);
                        }
                        else{
                                JOptionPane.showMessageDialog(null, "error de coneccion");
                        }
                // TODO add your handling code here:
                    }
                    catch (SQLException ex) {
                        Logger.getLogger(actFactura.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        jComboBox4.removeAllItems();
        conectar con=new  conectar();
        Connection reg=con.Conectar();
        Statement st;
        String razon;
        razon = (String) jComboBox1.getSelectedItem();
        try {
            st = reg.createStatement();
            ResultSet rs=st.executeQuery("select f.numFactura from factura f, proveedor p where p.rucProveedor=f.rucProveedor and"
                    + " razProveedor='"+razon+"' and f.cedUsuario='"+aplicacion.user+"'" ); //faltaría seleccionar sólo las del usuario de la sesión
            while(rs.next()) {
                String f=rs.getString("numFactura");        
                //Object datos[]={d,f,a,b,c,e};
                jComboBox4.addItem(f);          
            }
        }
        catch (SQLException ex) {  
            Logger.getLogger(elProveedor.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error\n"+ex);
        }
// TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTDescuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTDescuentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTDescuentoActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String educacionS, viviendaS, alimentacionS, vestimentaS, saludS, otroS, descuentoS, iceS;
        educacionS = jTEducacion.getText();
        viviendaS = jTVivienda.getText();
        alimentacionS = jTAlimentacion.getText();
        vestimentaS = jTVestimenta.getText();
        otroS = jTOtro.getText();
        saludS = jTSalud.getText();
        iceS= jTIce.getText();
        descuentoS= jTDescuento.getText();
        try {
            educacion = Double.parseDouble(educacionS);
            vivienda = Double.parseDouble(viviendaS);
            alimentacion = Double.parseDouble(alimentacionS);
            vestimenta = Double.parseDouble(vestimentaS);
            salud = Double.parseDouble(saludS);
            otro = Double.parseDouble(otroS);
            ice = Double.parseDouble(iceS);
            descuento = Double.parseDouble(descuentoS);
            if(jCEducacion.isSelected())
                ivaEducacion = (educacion * 12)/100;
            if(jCVivienda.isSelected())
                ivaVivienda = (vivienda * 12)/100;
            if(jCVestimenta.isSelected())
                ivaVestimenta = (vestimenta * 12)/100;
            if(jCSalud.isSelected())
                ivaSalud = (salud * 12)/100;
            if(jCAlimentacion.isSelected())
                ivaAlimentacion = (alimentacion * 12)/100;
            if(jCOtro.isSelected())
                ivaOtro = (otro * 12)/100;
            iva = ivaEducacion+ivaAlimentacion+ivaVestimenta+ivaVivienda+ivaSalud+ivaOtro;
            jTIVA.setText(iva+"");
            total = educacion+salud+vivienda+vestimenta+alimentacion+otro+ice-descuento+iva;
            jTTotal.setText(total+"");
            jButton1.setEnabled(true);
        }
        catch (ArithmeticException ex){
            JOptionPane.showMessageDialog(null, "Los campos 'Eduación', 'Alimentación', 'Vivienda', 'Salud', 'Vestimenta', "
                    + "'Otros' y 'I.C.E.' deben tener sólo números y decimales.\nIngrese el punto (.) para separar la parte decimal",
                    "Error de ingreso", JOptionPane.ERROR_MESSAGE);
        }
// TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCAlimentacion;
    private javax.swing.JCheckBox jCEducacion;
    private javax.swing.JCheckBox jCOtro;
    private javax.swing.JCheckBox jCSalud;
    private javax.swing.JCheckBox jCVestimenta;
    private javax.swing.JCheckBox jCVivienda;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox4;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTextField jTAlimentacion;
    private javax.swing.JTextField jTDescuento;
    private javax.swing.JTextField jTEducacion;
    private javax.swing.JTextField jTIVA;
    private javax.swing.JTextField jTIce;
    private javax.swing.JTextField jTOtro;
    private javax.swing.JTextField jTSalud;
    private javax.swing.JTextField jTTotal;
    private javax.swing.JTextField jTVestimenta;
    private javax.swing.JTextField jTVivienda;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
