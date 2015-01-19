/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.io.StringReader;

/**
 *
 * @author Alex
 */
public class AProveedor extends javax.swing.JInternalFrame {

    /**
     * Creates new form AProveedor
     */
    public AProveedor() {
        initComponents();
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
        jTextField1.requestFocus();
    }
    
    public boolean VerificarRuc(String ruc) {
            //Ruc de Persona Natural
            int tamanoLongitudRuc = 13;
           /* const string establecimiento = "001";*/
            int numeroProvincias = 24;
            int modulo = 11;
            int total = 0;
            if (ruc.matches("[0-9]*") && ruc.length()== tamanoLongitudRuc)
            {
                int numeroProvincia = Integer.parseInt(ruc.charAt(0) + ""+ ruc.charAt(1));
                int sociedad_Persona = Integer.parseInt(ruc.charAt(2)+"");
                if (numeroProvincia >= 1 && numeroProvincia <= numeroProvincias) 
                {
                    if (sociedad_Persona >= 0 && sociedad_Persona < 6)
                    {
                        //Ruc de Persona Natural
                        JOptionPane.showMessageDialog(null, ruc.substring(10, 13));
                        JOptionPane.showMessageDialog(null, ruc.substring(0, 10));
                        int a;
                        a=ruc.substring(10, 13).compareTo("001");
                        JOptionPane.showMessageDialog(null, a);
                        if(a==0)
                            return VerificarCedula(ruc.substring(0, 10));
                        else
                            return false;
                    }
                    else /* const int tercerDigito = 6; const string establecimiento = "0001"; */
                        if (sociedad_Persona == 6 && ruc.substring(9, 13) == "0001")
                        {
                            //Ruc de Personas Públicas/Entidades Estatales
                            int[] coeficientes = { 3, 2, 7, 6, 5, 4, 3, 2 };
                            int digitoVerificadorRecibido = Integer.parseInt(ruc.charAt(8)+"");
                            for (int i = 0; i < coeficientes.length; i++)
                            {
                                total = total + (coeficientes[i] * Integer.parseInt(ruc.charAt(i)+""));
                            }
                            int digitoVerificadorObtenido = modulo - (total % modulo);
                            return digitoVerificadorObtenido == digitoVerificadorRecibido;
                        }
                        else /* const int tercerDigito = 9; const string establecimiento = "001"; */
                            if (sociedad_Persona == 9 && ruc.substring(10, 13) == "001")
                            {
                                //Ruc de sociedades privadas y extranjeros sin cédula
                                int[] coeficientes = { 4, 3, 2, 7, 6, 5, 4, 3, 2 };
                                int digitoVerificadorRecibido = Integer.parseInt(ruc.charAt(9)+"");
                                for (int i = 0; i < coeficientes.length; i++)
                                {
                                    total = total + (coeficientes[i] * Integer.parseInt(ruc.charAt(i)+""));
                                }
                                int digitoVerificadorObtenido = (total % modulo) == 0 ?
                                                                        0 :
                                                                    modulo - (total % modulo);
                                return digitoVerificadorObtenido == digitoVerificadorRecibido;
                            }
                            else return false;
                }
                else return false;
            }
            else
                return false;
}
    
    public boolean VerificarCedula(String cedula) {
            int total = 0;
            int tamanoLongitudCedula = 10;
            int[] coeficientes = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
            int numeroProvincias = 24;
            int tercerDigito = 6;
            if (cedula.matches("[0-9]*") && cedula.length() == tamanoLongitudCedula) {
                int provincia = Integer.parseInt(cedula.charAt(0) + "" + cedula.charAt(1));
                int digitoTres = Integer.parseInt(cedula.charAt(2) + "");
                if ((provincia > 0 && provincia <= numeroProvincias) && digitoTres < tercerDigito) {
                    int digitoVerificadoRecibido = Integer.parseInt(cedula.charAt(9) + "");
                    for (int i = 0; i < coeficientes.length; i++) {
                        int valor = Integer.parseInt(coeficientes[i] + "") * Integer.parseInt(cedula.charAt(i) + "");
                        total = valor >= 10 ? total + (valor - 9) : total + valor;
                    }
                    int digitoVerificadorObtenido = total >= 10 ?
                                                        (total % 10) != 0 ?
                                                            10 - (total % 10) :
                                                        (total % 10) :
                                                    total;
                    return digitoVerificadorObtenido == digitoVerificadoRecibido;
                }
                else
                    return false;
            }
            else
                return false;
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField6 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField7 = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();

        jTextField6.setText("jTextField6");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Agregar Proveedor ");
        setAutoscrolls(true);
        setDoubleBuffered(true);
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }

        jLabel1.setText("Razon social:");

        jLabel2.setText("Direcciòn:");

        jLabel3.setText("Telefono:");

        jLabel4.setText("Nombre Comercial:");

        jLabel5.setText("RUC:");

        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField7))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
conectar con=new  conectar();
Connection reg=con.conexion();
String RUC,nom,tel,raz,dir;
RUC=jTextField1.getText();
raz=jTextField2.getText();
dir=jTextField3.getText();
tel=jTextField4.getText();
nom=jTextField7.getText();
     String sql;
  //   if(VerificarRuc(RUC)==true) {
sql="insert into proveedor(rucProveedor,razonProveedor,dirProveedor,telProveedor,nomComProveedor)values(?,?,?,?,?);";   
        try {
            PreparedStatement path =reg.prepareCall(sql);
             path.setString(1, RUC);
            path.setString(2, raz);
            path.setString(3, dir);
            path.setString(4, tel);
            path.setString(5, nom);
            int n=path.executeUpdate();
            if(n>0){
    JOptionPane.showMessageDialog(null, "Valores Agrgeados correctamente");
    }else{
        JOptionPane.showMessageDialog(null, "Los valores no fueron agregados");
    }
            
        } catch (SQLException ex) {
            Logger.getLogger(AProveedor.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Los valores no fueron agregados por que ya existe el usuario "+ex);
        }
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
        jTextField1.requestFocus();
     //}
    /* else
         JOptionPane.showMessageDialog(null, "El Ruc ingresado no es válido");*/
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables
}
